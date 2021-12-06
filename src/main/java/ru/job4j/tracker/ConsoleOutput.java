package ru.job4j.tracker;

public class ConsoleOutput implements Output {
    @Override
    public void printLn(Object object) {
        System.out.println(object);
    }
}
