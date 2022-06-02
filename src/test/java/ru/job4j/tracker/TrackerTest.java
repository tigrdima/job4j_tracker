package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.function.tracker.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item();
        item.setName("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindById() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        Item item = tracker.add(bug);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindAll() {
        MemTracker tracker = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        Item result = tracker.findAll().get(0);
        assertThat(result.getName(), is(first.getName()));

    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        MemTracker tracker = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        tracker.add(new Item("First"));
        List<Item> result = tracker.findByName(first.getName());
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        MemTracker tracker = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        tracker.add(new Item("First"));
        List<Item> result = tracker.findByName(second.getName());
        assertThat(result.get(1).getName(), is(second.getName()));
    }

    @Test
    public void whenReplace() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteMockito() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        DeleteItem deleteItem = new DeleteItem(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        deleteItem.execute(input, tracker);

        assertThat(out.toString(), is("=== Delete item ===" + ln + "Заявка удалена успешно." + ln));
        assertThat(tracker.findById(bug.getId()), is(nullValue()));
    }

    @Test
    public void whenEditItemMockito() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String edit = "new Bug";
        EditItem editItem = new EditItem(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(edit);

        editItem.execute(input, tracker);

        assertThat(out.toString(), is("=== Edit item ===" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findById(bug.getId()).getName(), is(edit));
    }

    @Test
    public void whenFindItemByIdMockito() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        FindItemById findItemById = new FindItemById(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        findItemById.execute(input, tracker);

        assertThat(out.toString(), is("=== Find item by id ===" + ln + bug + ln));
        assertThat(tracker.findById(bug.getId()).getName(), is("Bug"));
    }

    @Test
    public void whenFindItemsByNameMockito() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        FindItemsByName findItemsByName = new FindItemsByName(out);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Bug");

        findItemsByName.execute(input, tracker);

        assertThat(out.toString(), is("=== Find items by name ===" + ln + bug + ln));
        assertThat(tracker.findAll().get(0), is(bug));
    }
}