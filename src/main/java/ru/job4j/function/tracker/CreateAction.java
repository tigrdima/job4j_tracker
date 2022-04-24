package ru.job4j.function.tracker;

public class CreateAction implements UserAction {
    private final Output output;

    public CreateAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Create  a new Item";
    }

    @Override
    public boolean execute(Input input, SqlTracker tracker) {
        output.printLn("=== Create a new Item ===");
        String name = input.askStr("Enter name");
        Item item = new Item(name);
        tracker.add(item);
        output.printLn("Добавленная заявка: " + item);
        return true;
    }
}
