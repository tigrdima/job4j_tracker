package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int a1 = Integer.parseInt(o1.replaceAll("\\D", "").substring(0, 1));
        int b1 = Integer.parseInt(o2.replaceAll("\\D", "").substring(0, 1));

        return b1 == a1 ? o1.compareTo(o2) : Integer.compare(b1, a1);

    }
}

