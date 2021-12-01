package ru.job4j.tracker;

public class Bus implements  Transport {
    @Override
    public void go() {
        System.out.println("Едет");
    }

    @Override
    public void numberOfPassengers(int number) {
        System.out.println("Колличество пассажиров: " + number);
    }

    @Override
    public double priceRefuel(double fuel) {
        return 0;
    }
}
