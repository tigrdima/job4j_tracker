package ru.job4j.oop;

public class Calculator {
    private static  int x = 5;

    public static int sum(int a) {
        return x + a;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
            return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int s) {
        return sum(s) + multiply(s) + minus(s) + divide(s);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        System.out.println(calculator.multiply(5));
        System.out.println(minus(4));
        System.out.println(calculator.divide(25));
        System.out.println(calculator.sumAllOperation(10));
    }
}
