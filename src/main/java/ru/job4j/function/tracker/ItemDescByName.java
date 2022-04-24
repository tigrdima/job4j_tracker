package ru.job4j.function.tracker;

import java.util.Comparator;

public class ItemDescByName implements Comparator<Item> {
    @Override
    public int compare(Item i1, Item i2) {
        return i2.getName().compareTo(i1.getName());
    }

}
