package com.mycompany.project.enterprise.manager;

// Models
import com.mycompany.project.enterprise.model.*;

// Utils
import java.util.List;
import java.util.ArrayList;

public class EmployeeListManager {
    private List<Employee> employeeList;

    // Constructor
    public EmployeeListManager() {
        this.employeeList = new ArrayList<>();
    }

    // Add Employee
    public void addEmployee(Employee e) {
        employeeList.add(e);
    }

    // Remove Employee
    public void removeEmployee(int i) {
        if (i >= 0 && i < employeeList.size()) {
            employeeList.remove(i);
        } else {
            System.out.println("Indice invalido.");
        }
    }

    // To String
    @Override
    public String toString() {
        return "EmployeeListManager{employeeList=" + employeeList + '}';
    }
    
    // Getters and Setters
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}