package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
       String[] splitO1 = o1.split("\\.");
       String[] splitO2 = o2.split("\\.");
       return Integer.compare(Integer.parseInt(splitO1[0]), Integer.parseInt(splitO2[0]));
    }
}

