package ru.job4j.function.tracker;

import java.util.List;

public class FindItemsByName implements UserAction {
    private final Output output;

    public FindItemsByName(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, SqlTracker tracker) {
        output.printLn("=== Find items by name ===");
        String name = input.askStr("Enter name: ");
        List<Item> byName = tracker.findByName(name);
        if (byName.size() > 0) {
            for (Item item : byName) {
                output.printLn(item);
            }
        } else {
            output.printLn("Заявки с именем: " + name + " не найдены.");
        }

        return true;
    }
}
