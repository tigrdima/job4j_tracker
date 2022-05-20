package ru.job4j.inheritance.pizza;

public class PizzaExtraCheese extends Pizza {

    @Override
    public String name() {
        String name = " + extra pizza";
        return super.name() + name;
    }

}
