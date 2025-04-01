package com.test.lab4;

import com.test.lab4.employee.Employee;
import com.test.lab4.employee.EmployeeService;
import com.test.lab4.utils.EmployeeDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

public class Lab4Application {

    public static void main(String[] args) {

        int option;
        do {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

            EmployeeService service = new EmployeeService();
            switch (option) {
                case 1:
                    System.out.println("Entrer les infos de l'employé.");
                    Employee employee = new Employee();

                    System.out.println("Id : ");
                    employee.setId(scanner.nextInt());
                    scanner.nextLine(); // Ajout pour consommer le retour à la ligne

                    System.out.println("Name : ");
                    String name = scanner.nextLine();
                    employee.setName(name);

                    System.out.println("Departement : ");
                    employee.setDepartment(scanner.nextLine());

                    System.out.println("Salary : ");
                    employee.setSalary(scanner.nextDouble());

                    service.create(employee);
                    break;
                case 2:
                    System.out.println(service.getEmployees());
                    break;
                case 3:
                    System.out.println("Entrer le nom du departement : ");
                    String departement = scanner.nextLine();
                    System.out.println(service.getEmployeesByDepartement(departement));
                    break;
                case 4:
                    System.out.println(service.getEmployeesSortBySalary());
                    break;
                case 5:
                    System.out.println(service.getAverageSalary());
                    break;
                case 6:
                    System.out.println(service.findHighestPaidEmployee());
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide, veuillez choisir une option valide.");
            }
        } while (option != 7);
    }

    private static void displayMenu() {
        System.out.println("Menu :");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Afficher tous les employés");
        System.out.println("3. Filtrer par département");
        System.out.println("4. Trier par salaire décroissant");
        System.out.println("5. Calculer la moyenne des salaires");
        System.out.println("6. Afficher l'employé le mieux payé");
        System.out.println("7. Quitter");
        System.out.print("Choisissez une option : ");
    }
}
