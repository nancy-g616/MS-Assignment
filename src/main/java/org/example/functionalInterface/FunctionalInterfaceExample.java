package org.example.functionalInterface;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", 1, LocalDate.of(1990, 1, 15), 3000, "IT"),
                new Employee("Jane", "Smith", 2, LocalDate.of(1985, 3, 22), 1800, "HR"),
                new Employee("Alice", "Johnson", 3, LocalDate.of(1992, 7, 10), 2500, "Finance"),
                new Employee("Bob", "Brown", 4, LocalDate.of(1988, 11, 30), 1900, "Marketing")
        );

        Consumer<Employee> printEmployee = emp -> System.out.println(emp);

        Predicate<Employee> salaryAbove2000 = emp -> emp.getSalary() > 2000;

        BiPredicate<Employee, Double> salaryAboveThreshold = (emp, threshold) -> emp.getSalary() > threshold;

        System.out.println("Employees with salary > 2000 (using Predicate):");
        employees.stream()
                .filter(salaryAbove2000)
                .forEach(printEmployee);

        System.out.println("\nEmployees with salary > 2000 (using BiPredicate):");
        double threshold = 2000;
        employees.stream()
                .filter(emp -> salaryAboveThreshold.test(emp, threshold))
                .forEach(printEmployee);

        System.out.println("\n=== Question 2: Convert Employee to User ===");

        Supplier<String> passwordSupplier = () -> {
            String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&";
            SecureRandom random = new SecureRandom();
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                password.append(charSet.charAt(random.nextInt(charSet.length())));
            }
            return password.toString();
        };

        Function<Employee, User> employeeToUser = emp -> {
            String userName = emp.getFirstName() +
                    emp.getLastName() +
                    emp.getDateOfBirth().getYear() +
                    emp.getId();
            String password = passwordSupplier.get();
            return new User(emp.getId(), userName, password);
        };

        List<User> users = employees.stream()
                .map(employeeToUser)
                .collect(Collectors.toList());

        users.forEach(System.out::println);

    }
}

