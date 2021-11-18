package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book kolobok = new Book("Колобок", 5);
        Book foxAndHeir = new Book("Лиса и заяц", 20);
        Book warAndPeace = new Book("Война и мир", 300);
        Book cleanCode = new Book("Clean code", 10);
        Book[] books = {kolobok, foxAndHeir, warAndPeace, cleanCode};

        for (Book bk : books) {
            System.out.println(bk.getName() + bk.getCountPages());
        }

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;

        for (Book bk : books) {
            System.out.println(bk.getName() + bk.getCountPages());
        }

        for (Book bk : books) {
            if (bk.getName().equals("Clean code")) {
                System.out.println(bk.getName() + bk.getCountPages());
            }
        }
    }
}