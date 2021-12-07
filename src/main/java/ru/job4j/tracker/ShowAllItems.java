package ru.job4j.tracker;

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
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                output.printLn(item);
            }
        } else {
            output.printLn("Хранилище еще не содержит заявок");
        }
        return true;
    }
}
