package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String org : origin) {
            for (String txt : text) {
                check.add(org);
                if (check.contains(txt)) {
                    break;
                }
                rsl = false;
            }
        }
        return rsl;
    }
}
