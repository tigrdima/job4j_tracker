package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;

public class ListToMap {

    public static Map<String, Student> convert(List<Student> list) {
        list.sort(Comparator.comparing(Student::getSurname));

        return list.stream()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        student -> student,
                        (s1, s2) -> s1
                ));
    }

    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student(30, "Ivanov"),
                new Student(40, "Petrov"),
                new Student(50, "Ivanov"),
                new Student(50, "Sidorov")
        );
        System.out.println(convert(list));
    }
}
