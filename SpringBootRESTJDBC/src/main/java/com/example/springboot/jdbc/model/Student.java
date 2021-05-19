package com.example.springboot.jdbc.model;

public class Student {

    private int id;
    private String name;
    private int age;
    private int weight;

    public Student() {
    }

    public Student(int id, String name, int age, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Student[id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ']';
    }
}
