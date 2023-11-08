package org.example;


import org.example.dto.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InterviewTask1 {

    static List<String> input = List.of("KAROLINA,K,22", "ANNA,K,32", "PAWEŁ,M,41", "KAMIL,M,26", "AGATA,K,19", "MATEUSZ,M,35", "MAGDA,K,29", "JAKUB,M,24", "EWA,K,31", "DAWID,M,40", "NATALIA,K,25", "ROBERT,M,38", "KATARZYNA,K,22", "GRZEGORZ,M,30", "JOANNA,K,35", "MARCIN,M,28", "PAULINA,K,24", "TOMASZ,M,37");
    private static final String DELIMITER = ",";

    public static void main(String[] args) {

        interviewTask(input);

    }

    private static void interviewTask(List<String> sourceUserList) {
        // TODO
        // 1. Create class Person with fields: id, name, gender and age (data types are up to you)
        // 2. Use parameter sourceUserList and create collection that contains all persons with unique IDs.
        List<Person> peopleList = sourceUserList.stream()
                .map(person -> {
                    var personSplitted = person.split(DELIMITER);
                    return new Person(personSplitted[0], personSplitted[1], Integer.valueOf(personSplitted[2]));
                }).collect(Collectors.toList());

        // 3. Group collection of Persons by gender (K is for female, M is for male)
        Map<String, List<Person>> groupedByGender = peopleList.stream().
                collect(Collectors.groupingBy(p -> p.getGender()));

        Map<String, Double> groupedByGender1 = peopleList.stream().
                collect(Collectors.groupingBy(Person::getGender, Collectors.averagingDouble(Person::getAge)));


        System.out.println(groupedByGender1);
    }



}