package org.example.stream;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeStreamDemo {

static class Employee {
    private String firstName;
    private String department;
    private double salary;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;

    public Employee(String firstName, String department, double salary, LocalDate dateOfBirth, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.department = department;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return firstName + " | " + department + " | " + salary + " | " +
                dateOfBirth + " | " + dateOfJoining;

    }
}

public static void main(String[] args) {
    List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 70000, LocalDate.of(1990, 5, 20), LocalDate.of(2023, 1, 10)),
            new Employee("Bob", "HR", 60000, LocalDate.of(1985, 3, 15), LocalDate.of(2022, 4, 12)),
            new Employee("Charlie", "Sales", 75000, LocalDate.of(1992, 8, 9), LocalDate.of(2023, 6, 5)),
            new Employee("David", "IT", 80000, LocalDate.of(1988, 2, 28), LocalDate.of(2021, 3, 8)),
            new Employee("Eva", "Finance", 72000, LocalDate.of(1993, 11, 11), LocalDate.of(2020, 9, 1))
    );
    System.out.println("1. Employees joined in 2023:");
    employees.stream()
            .filter(e -> e.getDateOfJoining().getYear() == 2023)
            .map(Employee::getFirstName)
            .forEach(System.out::println);

    // 2. Count, Min, Max, Sum, Average salary in a department
    System.out.println("\n2. Salary stats in 'IT' department:");
    DoubleSummaryStatistics stats = employees.stream()
            .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
            .mapToDouble(Employee::getSalary)
            .summaryStatistics();

    System.out.println("Count: " + stats.getCount());
    System.out.println("Min: " + stats.getMin());
    System.out.println("Max: " + stats.getMax());
    System.out.println("Sum: " + stats.getSum());
    System.out.println("Average: " + stats.getAverage());

    // 3. Sorted employees by first name, excluding HR
    System.out.println("\n3. Sorted employees (excluding HR):");
    employees.stream()
            .filter(e -> !e.getDepartment().equalsIgnoreCase("HR"))
            .sorted(Comparator.comparing(Employee::getFirstName))
            .forEach(System.out::println);

    // 4. Increment salary by 10% in 'IT' department
    System.out.println("\n4. Salaries after 10% increment in IT:");
    employees.stream()
            .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
            .forEach(e -> e.setSalary(e.getSalary() * 1.10));
    employees.stream()
            .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
            .forEach(System.out::println);

    // 5. 50 odd numbers after 100
    System.out.println("\n5. First 50 odd numbers after 100:");
    Stream.iterate(101, n -> n + 2)
            .limit(50)
            .forEach(System.out::println);

    // 6. Comma-separated first names ordered by date of birth
    System.out.println("\n6. Comma-separated first names by DOB:");
    String namesByDOB = employees.stream()
            .sorted(Comparator.comparing(Employee::getDateOfBirth))
            .map(Employee::getFirstName)
            .collect(Collectors.joining(", "));
    System.out.println(namesByDOB);
}
}


