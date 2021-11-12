package ru.job4j.oop;

public class Calculator {
    private static  int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int c) {
            return c - x;
    }

    public int divide(int d) {
        return d / x;
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
        Calculator divide = new Calculator();
        System.out.println(divide.divide(25));
        Calculator sum = new Calculator();
        System.out.println(sum.sumAllOperation(10));
    }
}
