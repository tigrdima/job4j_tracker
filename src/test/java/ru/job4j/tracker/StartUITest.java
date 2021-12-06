package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                 new CreateAction(output),
                new Exit()
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenEditItem() {
        Item item = new Item("test");
        Tracker tracker = new Tracker();
        tracker.add(item);
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "new item", "1"}
        );

        UserAction[] actions = {
                new EditItem(output),
                new Exit()
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("new item"));
    }

    @Test
    public void whenDeleteItem() {
        Item item = new Item("test");
        Tracker tracker = new Tracker();
        tracker.add(item);
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );

        UserAction[] actions = {
                new DeleteItem(output),
                new Exit()
        };
        new StartUI(output).init(in, tracker, actions);
        assertNull(tracker.findById(item.getId()));
    }

}