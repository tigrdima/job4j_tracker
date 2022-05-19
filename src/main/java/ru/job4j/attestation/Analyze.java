package ru.job4j.attestation;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream
               .map(Pupil::getSubjects)
                .flatMap(List::stream)
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0);
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(p -> new Tuple(p.getName(), p.getSubjects()
                        .stream()
                                .mapToInt(Subject::getScore)
                                .average()
                                .orElse(0)))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .map(Pupil::getSubjects)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Subject::getName, LinkedHashMap::new, Collectors.averagingDouble(Subject::getScore)))
                        .entrySet()
                .stream()
                .map(v -> new Tuple(v.getKey(), v.getValue()))
                .collect(Collectors.toList());

    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
         return stream
                 .map(p -> new Tuple(p.getName(), p.getSubjects()
                         .stream()
                         .mapToInt(Subject::getScore)
                         .sum()))
                 .toList()
                 .stream().max(Comparator.comparing(Tuple::getScore))
                 .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
         return stream
                 .map(Pupil::getSubjects)
                 .flatMap(List::stream)
                 .collect(Collectors.groupingBy(Subject::getName, LinkedHashMap::new, Collectors.summingDouble(Subject::getScore)))
                 .entrySet()
                 .stream()
                 .map(v -> new Tuple(v.getKey(), v.getValue())).toList()
                 .stream().max(Comparator.comparing(Tuple::getScore))
                 .orElse(null);
    }
}
