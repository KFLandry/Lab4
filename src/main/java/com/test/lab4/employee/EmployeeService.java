package com.test.lab4.employee;

import com.test.lab4.exception.InvalidEmployeeException;
import com.test.lab4.utils.EmployeeDataLoader;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class EmployeeService {

    private static final String FILE_PATH = "src/main/java/com/test/lab4/utils/Employees.csv";

    public void create(Employee employee) {
        try {
            EmployeeDataLoader.createEmployee(employee, FILE_PATH);
        } catch (InvalidEmployeeException | IOException e) {
            throw new RuntimeException("Erreur lors de la création de l'employé", e);
        }
    }

    private List<Employee> loadEmployees() {
        try {
            return EmployeeDataLoader.loadEmployees(FILE_PATH);
        } catch (IOException | InvalidEmployeeException e) {
            throw new RuntimeException("Erreur lors du chargement des employés", e);
        }
    }

    public String getEmployees() {
        List<Employee> employees = loadEmployees();
        return "La liste des employés : \n" + employees;
    }

    public String getEmployeesByDepartement(String departement) {
        List<Employee> employees = loadEmployees().stream()
                .filter(e -> departement.equals(e.getDepartment()))
                .collect(Collectors.toList());
        return "Employés du département " + departement + " : \n" + employees;
    }

    public List<Employee> getEmployeesSortBySalary() {
        return loadEmployees().stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .toList();
    }

    public String getAverageSalary() {
        List<Employee> employees = loadEmployees();

        OptionalDouble averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average();

        return averageSalary.isPresent()
                ? "Le salaire moyen est de : " + averageSalary.getAsDouble()
                : "Aucun employé disponible pour calculer le salaire moyen.";
    }

    public String findHighestPaidEmployee() {
        return loadEmployees().stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .map(Employee::toString)
                .orElse("Aucun employé trouvé.");
    }
}
