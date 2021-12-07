package ru.job4j.tracker;

public class FindItemById implements UserAction {
    private  final Output output;

    public FindItemById(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.printLn("=== Find item by id ===");
        int id = input.askInt("Enter Id");
        Item item = tracker.findById(id);
        if (item != null) {
            output.printLn(item);
        } else {
            output.printLn("Заявка с введенным id: " + id + " не найден");
        }
        return true;
    }
}
