package ru.job4j.function.tracker;

public interface Observe<T> {
    void receive(T model);
}
