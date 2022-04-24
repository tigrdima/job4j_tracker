package ru.job4j.function.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app_tracker.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement pr = cn.prepareStatement("insert into items (name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            pr.setString(1, item.getName());
            pr.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            pr.execute();
            try (ResultSet resultKey = pr.getGeneratedKeys()) {
                if (resultKey.next()) {
                    item.setId(resultKey.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement pr =
                     cn.prepareStatement("update items set name = ? where id = ?")) {
            pr.setString(1, item.getName());
            pr.setInt(2, id);
            rsl = pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement pr = cn.prepareStatement("delete from items where id = ?")) {
            pr.setInt(1, id);
            rsl = pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        try (PreparedStatement pr = cn.prepareStatement("select * from items")) {
            ResultSet itemPr = pr.executeQuery();

            while (itemPr.next()) {
                item.setCreated(itemPr.getTimestamp("created").toLocalDateTime());
                item.setId(itemPr.getInt("id"));
                item.setName(itemPr.getString("name"));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        Item item = new Item();

        try (PreparedStatement pr = cn.prepareStatement("select * from items where name = ?")) {
            pr.setString(1, key);
            ResultSet itemPr = pr.executeQuery();

            while (itemPr.next()) {
                if (itemPr.getString("name").equals(key)) {
                    item.setCreated(itemPr.getTimestamp("created").toLocalDateTime());
                    item.setId(itemPr.getInt("id"));
                    item.setName(itemPr.getString("name"));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();

        try (PreparedStatement pr = cn.prepareStatement("select * from items where id = ? ")) {
            pr.setInt(1, id);
            ResultSet itemPr = pr.executeQuery();
                    if (itemPr.next()) {
                        item.setCreated(itemPr.getTimestamp("created").toLocalDateTime());
                        item.setId(itemPr.getInt("id"));
                        item.setName(itemPr.getString("name"));
                    }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
