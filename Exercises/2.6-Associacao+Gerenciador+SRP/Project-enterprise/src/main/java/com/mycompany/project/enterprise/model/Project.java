package com.mycompany.project.enterprise.model;

// Managers
import com.mycompany.project.enterprise.manager.*;
import java.util.Objects;

/**
 *
 * @author guilh
 */
public class Project {
    private String id;
    private String name;
    private String description;
    private EmployeeListManager elManager;
    
    // Constructor
    public Project() {
        this.elManager = new EmployeeListManager();
    }
    public Project(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.elManager = new EmployeeListManager();
    }
    
    // To String
    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", elManager=" + elManager + '}';
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.id, other.id)) { 
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.elManager, other.elManager);
    }
    
    // Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.elManager);
        return hash;
    }
    
    // Add Employee
    public void addEmployee(Employee e) {
        elManager.addEmployee(e);
        e.addProject(this);
    }
    
    // Remove Employee
    public void removeEmployee(int i) {
        elManager.removeEmployee(i);
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public EmployeeListManager getElManager() {
        return elManager;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setElManager(EmployeeListManager elManager) {
        this.elManager = elManager;
    }
}
