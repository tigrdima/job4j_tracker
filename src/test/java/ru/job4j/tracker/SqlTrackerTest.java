package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.function.tracker.Item;
import ru.job4j.function.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenAddItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenReplaceItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item newItem = new Item("newItem");
        tracker.add(item);
        int id = item.getId();
        tracker.replace(id, newItem);
        assertThat(tracker.findById(id), is(newItem));
    }

    @Test
    public void whenDeleteItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        assertNull(tracker.findById(id));
    }

    @Test
    public void whenFindAllItem() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> expected = List.of(
                new Item("item1"),
                new Item("item2"),
                new Item("item3")
        );
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));

        List<Item> rsl = tracker.findAll();

        assertThat(rsl.size(), is(expected.size()));
        assertTrue(expected.containsAll(rsl));
    }

    @Test
    public void whenFindByNameItem() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> expected = List.of(
                new Item("item3"),
                new Item("item3")
        );
        Item item = new Item("item");
        tracker.add(new Item("item3"));
        tracker.add(item);
        tracker.add(new Item("item3"));

        List<Item> rsl = tracker.findByName("item3");

        assertThat(expected.size(), is(rsl.size()));
        assertTrue(expected.containsAll(rsl));
        assertFalse(rsl.contains(item));
    }

    @Test
    public void whenFindByIdItem() {
        SqlTracker tracker = new SqlTracker(connection);

        Item expected = new Item("item");
        tracker.add(expected);
        Item rsl = tracker.findById(expected.getId());
        Item rslNull = tracker.findById(2);

        assertThat(rsl, is(expected));
        assertNull(rslNull);
    }
}
