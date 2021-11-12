package ru.job4j.oop;

public class Cat {
    private String food;
    private String name;

    public void show() {
        System.out.println("There are " + this.name + " food.");
        System.out.println(this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public void giveNick(String nick) {
        this.name = nick;
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        cat1.giveNick("Мурка");
        cat1.eat("kotleta");
        cat1.show();
        Cat cat2 = new Cat();
        cat2.giveNick("Васька");
        cat2.eat("fish");
        cat2.show();
    }
}
