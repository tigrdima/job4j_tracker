package ru.job4j.function.tracker;

import java.util.List;

public class StartUI {
    private final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

    public void init(Input input, SqlTracker tracker, List<UserAction> actions) {
        boolean run = true;

        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select > actions.size() - 1) {
                output.printLn("Wrong input, you can select:  0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        output.printLn("Menu");
        for (int i = 0; i < actions.size(); i++) {
            output.printLn(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();

            List<UserAction> actions = List.of(new CreateAction(output),
                    new ShowAllItems(output),
                    new EditItem(output),
                    new DeleteItem(output),
                    new FindItemById(output),
                    new FindItemsByName(output),
                    new Exit(output)
            );
            new StartUI(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
