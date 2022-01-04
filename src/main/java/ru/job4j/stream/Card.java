package ru.job4j.stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{"
                + "suit=" + suit
                + ", value=" + value
                + '}';
    }

    public static void main(String[] args) {

        Stream.of(Suit.values())
                .flatMap(suites -> Arrays.stream(Value.values())
                        .map(values -> new Card(suites, values)))
                .forEach(System.out::println);
    }
}
