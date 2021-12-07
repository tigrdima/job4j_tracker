package ru.job4j.tracker;

public class DeleteItem implements UserAction {
    private final Output output;

    public DeleteItem(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        output.printLn("=== Delete item ===");
        int id = input.askInt("Enter Id");
        if (tracker.delete(id)) {
            output.printLn("Заявка удалена успешно.");
        } else {
            output.printLn("Ошибка удаления заявки.");
        }
        return true;
    }
}
