package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

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
        List<UserAction> actions = List.of(
                new CreateAction(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
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

        List<UserAction> actions = List.of(
                new EditItem(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("new item"));
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

        List<UserAction> actions = List.of(
                new DeleteItem(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = List.of(
                new EditItem(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is(
                "Menu" + ln
                        + "0. Edit item" + ln
                        + "1. Exit" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu" + ln
                        + "0. Edit item" + ln
                        + "1. Exit" + ln
        ));
    }

    @Test
    public void whenShowAllItemsTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        List<UserAction> actions = List.of(
                new ShowAllItems(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is(
                "Menu" + ln
                        + "0. Show all items" + ln
                        + "1. Exit" + ln
                        + "=== Show all items ===" + ln
                        + "Хранилище еще не содержит заявок" + ln
                        + "Menu" + ln
                        + "0. Show all items" + ln
                        + "1. Exit" + ln
        ));
    }

    @Test
    public void whenShowFindItemsByNameTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("test"));
        String nameItem = "test1";
        Input in = new StubInput(
                new String[]{"0", nameItem, "1"}
        );
        List<UserAction> actions = List.of(
                new FindItemsByName(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is(
                "Menu" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit" + ln
                        + "=== Find items by name ===" + ln
                        + "Заявки с именем: " + nameItem + " не найдены." + ln
                        + "Menu" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit" + ln
        ));
    }

    @Test
    public void whenShowFindItemByIdTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item("test");
        tracker.add(item);
        String idItem = "2";
        Input in = new StubInput(
                new String[]{"0", idItem, "1"}
        );
        List<UserAction> actions = List.of(
                new FindItemById(output),
                new Exit(output)
        );
        new StartUI(output).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is(
                "Menu" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit" + ln
                        + "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + idItem + " не найден" + ln
                        + "Menu" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"7", "0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = List.of(
                new Exit(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu" + ln
                                + "0. Exit" + ln
                                + "Wrong input, you can select:  0 .. 0" + ln
                                + "Menu" + ln
                                + "0. Exit" + ln
                )
        );
    }
}