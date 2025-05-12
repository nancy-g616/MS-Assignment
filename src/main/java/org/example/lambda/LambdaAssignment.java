package org.example.lambda;

import java.time.LocalDate;
import java.util.*;

// Employee class
class Employee {
    String name;
    LocalDate dateOfBirth;

    public Employee(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String toString() {
        return name + " (" + dateOfBirth + ")";
    }
}

// User class
class User {
    String username;

    public User(String username) {
        this.username = username;
    }

    public String toString() {
        return username;
    }
}

// Functional Interface
@FunctionalInterface
interface UserNameGenerator {
    String generate(String firstName, String lastName, int yearOfBirth, int id);
}

public class LambdaAssignment {

    public static void main(String[] args) {
        // 1. Sort employees by month in dateOfBirth using lambda
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", LocalDate.of(1990, 5, 15)),
                new Employee("Bob", LocalDate.of(1988, 2, 20)),
                new Employee("Charlie", LocalDate.of(1992, 5, 10)),
                new Employee("David", LocalDate.of(1995, 12, 1))
        );

        System.out.println("=== Sorted Employees by Month ===");
        employees.sort((e1, e2) -> Integer.compare(
                e1.dateOfBirth.getMonthValue(),
                e2.dateOfBirth.getMonthValue()
        ));
        employees.forEach(System.out::println);

        // 2 & 3. Username Generator using Lambda
        UserNameGenerator generator = (firstName, lastName, year, id) ->
                firstName + (year % 100) + "_" + id;

        List<User> users = Arrays.asList(
                new User(generator.generate("Alice", "Smith", 1990, 101)),
                new User(generator.generate("Bob", "Brown", 1988, 102))
        );

        // 2. Threads using Lambda
        Thread employeeThread = new Thread(() -> {
            System.out.println("\n=== Employee Thread ===");
            employees.forEach(System.out::println);
        });

        Thread userThread = new Thread(() -> {
            System.out.println("\n=== User Thread ===");
            users.forEach(System.out::println);
        });

        // Start threads
        employeeThread.start();
        userThread.start();

        // Wait for threads to complete
        try {
            employeeThread.join();
            userThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
