package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Functions {

    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> rsl = new ArrayList<>();
        Consumer<Double> con = rsl::add;
        BiPredicate<Integer, Integer> pr = (s, e) -> s < e;

        while (pr.test(start, end)) {
            con.accept(func.apply((double) start++));
        }
        return rsl;
    }
}
