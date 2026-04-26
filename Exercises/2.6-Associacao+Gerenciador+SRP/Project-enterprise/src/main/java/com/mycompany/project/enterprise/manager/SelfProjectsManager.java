package com.mycompany.project.enterprise.manager;

// Models
import com.mycompany.project.enterprise.model.*;

// Utils
import java.util.List;
import java.util.ArrayList;

public class SelfProjectsManager {
    private List<Project> projectList;

    // Constructor
    public SelfProjectsManager() {
        this.projectList = new ArrayList<>();
    }

    // Add projects
    public void addProject(Project p) {
        projectList.add(p);
    }

    // Remove projects
    public void removeProject(int i) {
        if (i >= 0 && i < projectList.size()) {
            projectList.remove(i);
        } else {
            System.out.println("Indice invalido.");
        }
    }

    // Count projects and return
    public int countProjects() {
        return projectList.size();
    }

    // To String
    @Override
    public String toString() {
        return "SelfProjectsManager{projectList=" + projectList + '}';
    }
    
    // Getters and Setters
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}