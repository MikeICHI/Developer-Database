package org.example.company.employer;

import org.example.company.ITCompany;

import javax.persistence.Id;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employers")
@Inheritance(strategy = InheritanceType.JOINED)//наследование
public class Employer<T> implements Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;//могут использовать наследники
    @Column(name = "age")
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private T role;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private ITCompany company;

    public Employer() {
    }

    public Employer(String name, int age, T role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(T role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return maskName(name);
    }

    private String maskName(String name) {
        //mask name
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public T getRole() {
        return role;
    }

    public void getEmployer() {
        System.out.println("Employer{name='" + this.name + "', age=" + this.age + ", role='" + this.getRole() + "'}");
    }

    public static void someMethod() {
        System.out.println("method1");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer<?> employer = (Employer<?>) o;
        return age == employer.age && Objects.equals(name, employer.name) && Objects.equals(role, employer.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, role);
    }

//    public Employer<T> toEmployer() {
//
//    }

    public void setCompany(ITCompany company) {
        this.company= company;
    }
}
