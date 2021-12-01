package ru.job4j.tracker;

public interface Transport {
    void go();

    void numberOfPassengers(int number);

    double priceRefuel(double fuel);
}
