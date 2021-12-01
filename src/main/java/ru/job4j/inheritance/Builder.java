package ru.job4j.inheritance;

public class Builder extends Engineer {
     private String builderRank;

    public Builder(String name, String surname, String education, String birthday, String department, String builderRank) {
        super(name, surname, education, birthday, department);
        this.builderRank = builderRank;
    }

    public int calculateTheEstimate(Project project) {
        return 0;
    }

}
