package ru.job4j.tracker;

import java.util.List;

public class ShowAllItems implements UserAction {
    private final Output output;

    public ShowAllItems(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.printLn("=== Show all items ===");
        List<Item> all = tracker.findAll();
        if (all.size() > 0) {
            for (Item item : all) {
                output.printLn(item);
            }

        } else {
            output.printLn("Хранилище еще не содержит заявок");
        }
        return true;
    }
}
