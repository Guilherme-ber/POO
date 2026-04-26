package com.mycompany.project.enterprise.manager;

// Models
import com.mycompany.project.enterprise.model.*;

// Utils
import java.util.List;
import java.util.ArrayList;

public class EmployeesContractedManager {
    private List<Employee> employeeList;

    // Constructor
    public EmployeesContractedManager() {
        this.employeeList = new ArrayList<>();
    }

    // Add employee
    public void addEmployee(Employee e) {
        employeeList.add(e);
    }

    // Remove employee
    public void removeEmployee(int i) {
        if (i >= 0 && i < employeeList.size()) {
            employeeList.remove(i);
        } else {
            System.out.println("Indice invalido.");
        }
    }

    // List employees
    public void listEmployees() {
        if (!employeeList.isEmpty()) {
            for (Employee e : employeeList) {
                System.out.println(e);
            }
        } else {
            System.out.println("Nenhum funcionario cadastrado.");
        }
    }

    // Get Employee more productive
    public Employee getEmployeeMoreProductive() {
        if (employeeList.isEmpty()) return null;

        Employee most = employeeList.get(0);
        for (Employee e : employeeList) {
            if (e.getspManager().countProjects() > most.getspManager().countProjects()) {
                most = e;
            }
        }
        return most;
    }

    // Get Employee less productive
    public Employee getEmployeeLessProductive() {
        if (employeeList.isEmpty()) return null;

        Employee least = employeeList.get(0);
        for (Employee e : employeeList) {
            if (e.getspManager().countProjects() < least.getspManager().countProjects()) {
                least = e;
            }
        }
        return least;
    }

    // Getters and Setters
    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}