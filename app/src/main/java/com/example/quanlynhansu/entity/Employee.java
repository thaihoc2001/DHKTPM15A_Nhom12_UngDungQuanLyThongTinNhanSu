package com.example.quanlynhansu.entity;

public class Employee {
    private int id;
    private String name;
    private String position;
    private String image;
    private int age;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee(String name, String position, String image, int age, String description) {
        this.name = name;
        this.position = position;
        this.image = image;
        this.age = age;
        this.description = description;
    }

    public Employee(String name, String position, int age, String description) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.description = description;
    }

    public Employee() {
    }

    public Employee(int id, String name, String position, String image, int age, String description) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.image = image;
        this.age = age;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", image='" + image + '\'' +
                ", age=" + age +
                '}';
    }
}
