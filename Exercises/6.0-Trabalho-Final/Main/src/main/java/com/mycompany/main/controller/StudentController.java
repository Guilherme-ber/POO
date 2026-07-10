package com.mycompany.main.controller;

// Entities
import com.mycompany.main.model.entities.Student;

// IDAO
import com.mycompany.main.model.dao.interfaces.IDAO;

// Validations
import com.mycompany.main.model.validations.ValidateStudent;

// Exception
import com.mycompany.main.model.exceptions.StudentException;

/**
 *
 * @author guilh
 */
public class StudentController {
    private IDAO<Student> repository;
    
    // Constructor
    public StudentController(IDAO repository) {
        this.repository = repository;
    }
    
    // Add
    public void addStudent(int id, String name, char sex, int age, String cpf, String registration, int entryYear) {
        try {
            // Validations
            ValidateStudent valid = new ValidateStudent();
            Student newStudent = valid.validate(id, name, sex, age, cpf, registration, entryYear);

            // Add
            repository.save(newStudent);
        } catch (StudentException e) {
            System.out.println("Atenção: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: não foi possível adicionar este estudante. Tente novamente mais tarde");
            System.out.println(e.getMessage());
        }
    }
    
    // Remove
    public void removeStudent(int id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            System.out.println("Erro: não foi possível remover este estudante");
            System.out.println(e.getMessage());
        }
    }
    
    // Update
    public void updateStudent(int id, String name, char sex, int age, String cpf, String registration, int entryYear) {
        try {
            // Validate first
            ValidateStudent valid = new ValidateStudent();
            Student updated = valid.validate(id, name, sex, age, cpf, registration, entryYear);
        
            // Delete and Create 
            removeStudent(id);    
            repository.save(updated);
        } catch (Exception e) {
            System.out.println("Erro: não foi possível atualizar os dados do estudante");
            System.out.println(e.getMessage());
        }
    }
  
    // Find by ID
    public Student findStudent(int id) {
        try {
            Student sd = repository.findById(id);
            return sd;
        } catch (Exception e) {
            System.out.println("Erro: não foi possível encontrar o estudante pelo ID");
            System.out.println(e.getMessage());
        }
        return null;
    }
}