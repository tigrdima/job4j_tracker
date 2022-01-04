package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> combaneName = person -> person.getName().contains(key);
        Predicate<Person> combaneSurname = person -> person.getSurname().contains(key);
        Predicate<Person> combanePhone = person -> person.getPhone().contains(key);
        Predicate<Person> combaneAddress = person -> person.getAddress().contains(key);
        var combine = combaneName.or(combaneAddress.or(combaneSurname.or(combanePhone)));

        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}

