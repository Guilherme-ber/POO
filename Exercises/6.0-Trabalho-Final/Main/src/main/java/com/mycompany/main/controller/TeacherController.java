package com.mycompany.main.controller;

// Entities
import com.mycompany.main.model.entities.Teacher;

// IDAO
import com.mycompany.main.model.dao.interfaces.IDAO;

// Exceptions
import com.mycompany.main.model.exceptions.TeacherException;

// Validations
import com.mycompany.main.model.validations.ValidateTeacher;

// Utils
import java.util.List;

/**
 *
 * @author guilh
 */
public class TeacherController {
    private IDAO<Teacher> repository;
    
    // Construcotr
    public TeacherController(IDAO repository) {
        this.repository = repository;
    }
    
    // Add
    public void addTeacher(int id, String name, char sex, int age, String cpf) {
        try {
            // Validator
            ValidateTeacher valid = new ValidateTeacher();
            Teacher newTeacher = valid.validate(id, name, sex, age, cpf);

            // Add
            repository.save(newTeacher);
        } catch (TeacherException e) {
            System.out.println("Atenção: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: não foi possível adicionar este professor. Tente novamente mais tarde");
            System.out.println(e.getMessage());
        }
    }
    
    // Remove
    public void removeTeacher(int id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            System.out.println("Erro: não foi possível remover este professor");
            System.out.println(e.getMessage());
        }
    }
    
    // Update
    public void updateTeacher(int id, String name, char sex, int age, String cpf) {
        try {
            // Validate first
            ValidateTeacher valid = new ValidateTeacher();
            Teacher updated = valid.validate(id, name, sex, age, cpf);
        
            // Delete and Create 
            removeTeacher(id);    
            repository.save(updated);
        } catch (TeacherException e) {
            System.out.println("Atenção: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: não foi possível atualizar os dados do professor");
            System.out.println(e.getMessage());
        }
    }
    
    // Find by ID
    public Teacher findTeacherById(int id) {
        try {
            Teacher t = repository.findById(id);
            return t;
        } catch (Exception e) {
            System.out.println("Erro: não foi possível encontrar o professor pelo ID");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    // Find all
    public List<Teacher> getAllTeachers() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            System.out.println("Error ao buscar todos os alunos");
        }
        return null;
    }
}
