package com.mycompany.project.enterprise.model;

// Managers
import com.mycompany.project.enterprise.manager.*;

// Utils
import java.util.Objects;

/**
 *
 * @author guilh
 */
public class Employee {
    private String id;
    private String name;
    private SelfProjectsManager spManager;

    // Constructors
    public Employee() {
        this.spManager = new SelfProjectsManager();
    }
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
        this.spManager = new SelfProjectsManager();
    }

    // To String
    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", spManager=" + spManager + '}';
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.id, other.id)) { 
            return false; 
        } 
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.spManager, other.spManager);
    }
    
    // Hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.spManager);
        return hash;
    }
    
    // Add project
    public void addProject(Project p) {
        spManager.addProject(p);
    }
    
    // Remove project
    public void removeProject(int i) {
        spManager.removeProject(i);
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public SelfProjectsManager getspManager() {
        return spManager;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setspManager(SelfProjectsManager spManager) {
        this.spManager = spManager;
    } 
}
