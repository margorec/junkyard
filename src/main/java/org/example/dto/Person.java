package org.example.dto;

import java.util.Objects;
import java.util.UUID;

public final class Person {

    private final UUID id;
    private final String name;
    private final String gender;
    private final int age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return age == person.age && Objects.equals(id, person.id) && Objects.equals(name, person.name) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age);
    }

    public Person(String name, String gender, int age) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.gender = gender;
        this.age = age;
    }



}
