package ru.job4j.ooa;

public final class Airbus {
    private static final int COUNT_ENGINE = 2;
    private String name;

    public Airbus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printModel() {
        System.out.println("Модель самолета: " + name);
    }

    public void printCountEngine() {
        int motors = "A380".equals(this.getName()) ?  4 :  COUNT_ENGINE;
        System.out.println("Количество двигателей равно: " + motors);
    }

    @Override
    public String toString() {
        return "Airbus{"
                + "name='" + name + '\''
                + '}';
    }
}
