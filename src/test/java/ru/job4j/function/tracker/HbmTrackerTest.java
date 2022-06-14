package ru.job4j.function.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class HbmTrackerTest {

    @Test
    public void whenCreate() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Item1");
        hbmTracker.add(item);
        List<Item> all = hbmTracker.findAll();
        assertEquals(item, all.get(0));
    }

    @Test
    public void whenFindAll() {
        HbmTracker hbmTracker = new HbmTracker();
       Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
       hbmTracker.add(item1);
       hbmTracker.add(item2);
        assertEquals(List.of(item1, item2), hbmTracker.findAll());
    }

    @Test
    public void whenReplace() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Item1");
        Item itemReplace = new Item("Item2");
        hbmTracker.add(item);
        hbmTracker.replace(item.getId(), itemReplace);
        assertThat("Item2", is(hbmTracker.findById(item.getId()).getName()));
    }

    @Test
    public void whenDelete() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Item1");
        hbmTracker.add(item);
        hbmTracker.delete(item.getId());
        List<Item> rsl = hbmTracker.findAll();
        assertThat(rsl.size(), is(0));
    }

    @Test
    public void whenFindById() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Item1");
        hbmTracker.add(item);

        assertThat("Item1", is(hbmTracker.findById(1).getName()));
    }

    @Test
    public void whenFindByName() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        Item item3 = new Item("Item2");

        hbmTracker.add(item1);
        hbmTracker.add(item2);
        hbmTracker.add(item3);

        List<Item> rsl = hbmTracker.findByName("Item2");

        assertThat(List.of(item2, item3), is(rsl));
    }

}