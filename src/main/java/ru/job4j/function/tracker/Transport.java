package ru.job4j.function.tracker;

public interface Transport {
    void go();

    void numberOfPassengers(int number);

    double priceRefuel(double fuel);
}
