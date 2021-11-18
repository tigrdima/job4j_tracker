package ru.job4j.inheritance;

public class Dentist extends Doctor {
    private String qualification;

    public Dentist(String name, String surname, String education, String birthday, String department, String qualification) {
        super(name, surname, education, birthday, department);
        this.qualification = qualification;
    }

    public boolean xRay(Diagnosis diagnosis) {

    }
}
