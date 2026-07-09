package com.mycompany.main.controller;

// Model
import com.mycompany.main.model.entities.Student;

// IDAO
import com.mycompany.main.model.dao.interfaces.IDAO;

// Validations
import com.mycompany.main.model.validations.ValidateStudent;

/**
 *
 * @author guilh
 */
public class StudentController {
    private IDAO repository;
    
    // Construcotr
    public StudentController(IDAO repository) {
        this.repository = repository;
    }
    
    public void addStudent(String name, char sex, int age, String cpf, String registration, int entryYear) {
        try {
            // Validations
            ValidateStudent valid = new ValidateStudent();
            Student newStudent = valid.validate(name, sex, age, cpf, registration, entryYear);

            // Add
            repository.save(newStudent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeStudent(int id) {
        // Remove
        repository.delete(id);
    }
    
    public void updateStudent(int id, String name, char sex, int age, String cpf, String registration, int entryYear) {
        // Remove
        removeStudent(id);
        
        // Add
        addStudent(name, sex, age, cpf, registration, entryYear);
    }
    
    public Student findStudent(int id) {
        try {
            Student sd = repository.searchById(id);
            return sd;
        } catch (Exception e) {
            return null;
        }
    }
}