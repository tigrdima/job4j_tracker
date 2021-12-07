package ru.job4j.ooa;

public class Airport {

    public static void main(String[] args) {
        final Airbus airbus = new Airbus("A325");
        airbus.printModel();
        airbus.printCountEngine(airbus);

        airbus.setName("A380");
        airbus.printModel();
        airbus.printCountEngine(airbus);

        airbus.setName("A390");
        airbus.printModel();
        airbus.printCountEngine(airbus);
    }
}
