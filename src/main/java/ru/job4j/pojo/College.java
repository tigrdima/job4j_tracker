package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student a = new Student();
        a.setFio("Иванов Иван Иванович");
        a.setGroup(12);
        a.setDateIn("12.12.80");

        System.out.println("ФИО -  " + a.getFio() + " : " + "Группа - " + a.getGroup() + " Дата поступления - " + a.getDateIn());
    }
}
