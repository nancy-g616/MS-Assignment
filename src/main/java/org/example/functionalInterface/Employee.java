package org.example.functionalInterface;

import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private LocalDate dateOfBirth;
    private double salary;
    private String dept;

    public Employee(String firstName, String lastName, int id, LocalDate dateOfBirth, double salary, String dept) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.dept = dept;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getDept() {
        return dept;
    }


    public double getSalary() {
        return salary;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name=" + firstName + " " + lastName +
                ", dob=" + dateOfBirth +
                ", salary=" + salary +
                ", dept=" + dept +
                '}';
    }
}
