package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
       int rsl = 0;
       int sizeO1 = o1.length();
       int sizeO2 = o2.length();

        if (sizeO1 < sizeO2) {
            o1 += " ";
        } else if (sizeO1 > sizeO2) {
            o2 += " ";
        }

        for (int i = 0; i < sizeO1; i++) {
            for (int j = 0; j < sizeO2; j++) {
                rsl += Integer.compare(o1.charAt(i), o2.charAt(j));
                i++;
            }
            }
        return rsl;
        }

    public static void main(String[] args) {
        StringCompare compare = new StringCompare();
        int rsl = compare.compare("Patrova", "Petrov");
        System.out.println(rsl);
    }
}
