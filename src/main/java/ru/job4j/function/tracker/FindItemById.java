package ru.job4j.function.tracker;

import java.sql.SQLException;

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
    public boolean execute(Input input, Store tracker) {
        output.printLn("=== Find item by id ===");
        int id = input.askInt("Enter Id: ");
        Item item = null;
        try {
            item = tracker.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (item != null) {
            output.printLn(item);
        } else {
            output.printLn("Заявка с введенным id: " + id + " не найден");
        }
        return true;
    }
}
