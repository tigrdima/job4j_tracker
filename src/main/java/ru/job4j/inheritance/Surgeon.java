package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    private String qualification;

    public Surgeon(String name, String surname, String education, String birthday, String department, String qualification) {
        super(name, surname, education, birthday, department);
        this.qualification = qualification;
    }

    public String tritment(Diagnosis diagnosis) {

        return "amputation";
    }
}


