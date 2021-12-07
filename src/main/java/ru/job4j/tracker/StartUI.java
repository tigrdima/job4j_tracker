package ru.job4j.tracker;

public class StartUI {
    private final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select > actions.length - 1) {
                output.printLn("Wrong input, you can select:  0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        output.printLn("Menu");
        for (int i = 0; i < actions.length; i++) {
            output.printLn(i + ". " + actions[i].name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(output),
                                new ShowAllItems(output),
                                new EditItem(output),
                                new DeleteItem(output),
                                new FindItemById(output),
                                new FindItemsByName(output),
                                new Exit(output)
        };
        new StartUI(output).init(input, tracker, actions);
    }
}
