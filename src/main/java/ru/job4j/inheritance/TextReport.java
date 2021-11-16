package ru.job4j.inheritance;

public class TextReport {
    public static String generate(String name, String body) {
        return name + System.lineSeparator() + body;
    }
}
