package com.test.lab4.utils;

import com.test.lab4.employee.Employee;
import com.test.lab4.exception.InvalidEmployeeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataLoader {

    public static List<Employee> loadEmployees(String filePath) throws IOException, InvalidEmployeeException {
        List<Employee> employees = new ArrayList<>();
        // Utilisation de try-with-resources pour une bonne gestion des fichiers
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Chaque ligne doit contenir : id,name,department,salary
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue; // Ignorer la ligne si le format n'est pas respecté
                }
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String department = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                Employee employee = new Employee(id, name, department, salary);
                employees.add(employee);
            }
        }
        return employees;
    }

    public static void createEmployee(Employee employee, String filePath) throws InvalidEmployeeException, IOException {
        try(
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))
                ){
            String line  = employee.getId() + "," + employee.getName() + "," + employee.getDepartment() + "," + employee.getSalary()+"\n";
            writer.append(line);
        }
    }
}
