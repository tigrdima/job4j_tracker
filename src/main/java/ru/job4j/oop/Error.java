package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error e1 = new Error();
        Error e2 = new Error(true, 3, "Error 2");
        Error e3 = new Error(false, 23, "Error 3");
        Error e4 = new Error(true, 7, "Error 4");
        e1.printInfo();
        e2.printInfo();
        e3.printInfo();
        e4.printInfo();
    }
}
