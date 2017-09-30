package com.aaron.java8example;

public class Person {
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name,Integer age) {
        this.name = name;
        this.age = age;
    }

    private Integer age;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
