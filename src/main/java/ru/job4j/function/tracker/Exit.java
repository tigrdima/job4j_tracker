package ru.job4j.function.tracker;
public class Exit implements UserAction {
    private  final Output output;

    public Exit(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, SqlTracker tracker) {
        return false;
    }
}
