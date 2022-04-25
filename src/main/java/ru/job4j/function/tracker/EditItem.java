package ru.job4j.function.tracker;

public class EditItem implements UserAction {
    private final Output output;

    public EditItem(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.printLn("=== Edit item ===");
        int id = input.askInt("Enter Id: ");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            output.printLn("Заявка изменена успешно.");
        } else {
            output.printLn("Ошибка замены заявки.");
        }
        return true;
    }
}
