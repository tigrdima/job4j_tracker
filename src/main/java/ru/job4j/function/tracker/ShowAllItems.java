package ru.job4j.function.tracker;

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
    public boolean execute(Input input, Store tracker) {
        output.printLn("=== Show all items ===");
        tracker.findAllByReact(output::printLn);
//        if (all.size() > 0) {
//            for (Item item : all) {
//                output.printLn(item);
//            }
//
//        } else {
//            output.printLn("Хранилище еще не содержит заявок");
//        }
        return true;
    }
}
