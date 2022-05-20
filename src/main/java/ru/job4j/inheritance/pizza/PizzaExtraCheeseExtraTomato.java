package ru.job4j.inheritance.pizza;

public class PizzaExtraCheeseExtraTomato extends PizzaExtraCheese {

    @Override
    public String name() {
        String name = " + extra tomato";
        return super.name() + name;
    }
}
