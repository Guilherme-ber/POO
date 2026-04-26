package com.mycompany.project.enterprise.model;

// Managers
import com.mycompany.project.enterprise.manager.*;

/**
 *
 * @author guilh
 */
public class Enterprise {
    private String name;
    private AvailableProjectsManager avaProjectsManager;
    private EmployeesContractedManager empContractedManager;
    
    // Constructors
    public Enterprise() {
        this.avaProjectsManager = new AvailableProjectsManager();
        this.empContractedManager = new EmployeesContractedManager();
    }
    public Enterprise(String name) {
        this.name = name;
        this.avaProjectsManager = new AvailableProjectsManager();
        this.empContractedManager = new EmployeesContractedManager();
    }
    
    // Add Projects
    public void addProject(Project p) {
        avaProjectsManager.addProject(p);
    }
    
    // Remove Projects
    public void removeProject(int i) {
        avaProjectsManager.removeProject(i);
    }
    
    // Add Employees
    public void addEmployee(Employee e) {
        empContractedManager.addEmployee(e);
    }
    
    // Remove Employees
    public void removeEmployee(int i) {
        empContractedManager.removeEmployee(i);
    }
   
    // List all projects
    public void listProjects() {
        avaProjectsManager.listProjects();
    }
    
    // List all employees
    public void listEmployees() {
        empContractedManager.listEmployees();
    }
    
    // Get Employee More Productive
    public Employee employeeMoreProductive() {
        return empContractedManager.getEmployeeMoreProductive();
    }
    
    // Get Employee Less Productive
    public Employee employeeLessProductive() {
        return empContractedManager.getEmployeeLessProductive();
    }         
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public AvailableProjectsManager getAvaProjectsManager() {
        return avaProjectsManager;
    }
    public EmployeesContractedManager getEmpContractedManager() {
        return empContractedManager;
    } 

    public void setName(String name) {
        this.name = name;
    }
    public void setAvaProjectsManager(AvailableProjectsManager avaProjectsManager) {
        this.avaProjectsManager = avaProjectsManager;
    }
    public void setEmpContractedManager(EmployeesContractedManager empContractedManager) {
        this.empContractedManager = empContractedManager;
    }       
}
