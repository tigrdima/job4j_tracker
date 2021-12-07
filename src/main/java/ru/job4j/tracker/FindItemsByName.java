package ru.job4j.tracker;

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
    public boolean execute(Input input, Tracker tracker) {
        output.printLn("=== Find items by name ===");
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                output.printLn(item);
            }
        } else {
            output.printLn("Заявки с именем: " + name + " не найдены.");
        }
        return true;
    }
}
