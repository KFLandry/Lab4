package com.test.lab4.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;

    private String name;

    private String department;

    private double salary;

    @Override
    public String toString() {
       return "Employee [id=" + id + ", name=" + name + ", department=" + department + ",salary=" + salary + "]\n";
    }
}
