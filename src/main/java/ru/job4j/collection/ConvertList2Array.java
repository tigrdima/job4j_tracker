package ru.job4j.collection;

import java.util.*;

public class ConvertList2Array {
    public static int[][] toArray(List<Integer> list, int cells) {
        int groups = (int) Math.ceil((double) list.size() / cells);
        int[][] array = new int[groups][cells];

        int row = 0, cell = 0;
        for (Integer num : list) {
            array[row][cell] = num;
            if (cell < cells - 1) {
                cell += 1;
            } else {
                cell = 0;
                row += 1;
            }
        }
        return array;
    }
}