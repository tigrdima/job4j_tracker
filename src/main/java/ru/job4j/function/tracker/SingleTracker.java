package ru.job4j.function.tracker;

import java.util.List;

public final class SingleTracker implements Store {
    private final MemTracker tracker = new MemTracker();
    private static SingleTracker instance = null;

    private SingleTracker() {
    }

    public static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    @Override
    public Item add(Item item) {
        return tracker.add(item);
    }

    @Override
    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    @Override
    public boolean delete(int id) {
        return tracker.delete(id);
    }

    @Override
    public List<Item> findAll() {
        return tracker.findAll();
    }

    @Override
    public List<Item> findByName(String key) {
        return tracker.findByName(key);
    }

    @Override
    public Item findById(int id) {
        return tracker.findById(id);
    }
}
