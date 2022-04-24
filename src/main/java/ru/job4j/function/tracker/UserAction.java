package ru.job4j.function.tracker;

public interface UserAction {
    String name();

    boolean execute(Input input, SqlTracker tracker);
}
