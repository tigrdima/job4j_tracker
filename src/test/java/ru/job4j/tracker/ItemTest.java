package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {

   @Test
    public void aZ() {
       List<Item> items = Arrays.asList(
               new Item("Заяц"),
               new Item("Белка"),
               new Item("Волк")
       );

       Collections.sort(items, new ItemAscByName());
       List<Item> expected = Arrays.asList(
               new Item("Белка"),
               new Item("Волк"),
               new Item("Заяц")
       );
       assertThat(items,  is(expected));
    }

    @Test
    public void zA() {
        List<Item> items = Arrays.asList(
                new Item("Заяц"),
                new Item("Белка"),
                new Item("Волк")
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item("Заяц"),
                new Item("Волк"),
                new Item("Белка")
        );
        assertThat(items, is(expected));
    }
}