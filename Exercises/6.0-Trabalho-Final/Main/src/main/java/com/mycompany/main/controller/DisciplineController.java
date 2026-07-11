package com.mycompany.main.controller;

// Entities
import com.mycompany.main.model.entities.*;

// Interface
import com.mycompany.main.model.dao.interfaces.IDAO;

// Validations
import com.mycompany.main.model.validations.ValidateDiscipline;

// Exception
import com.mycompany.main.model.exceptions.DisciplineException;

// Util
import java.util.List;

/**
 *
 * @author guilh
 */
public class DisciplineController {
    private IDAO<Discipline> repository;
    
    // Constructor
    public DisciplineController(IDAO repository) {
        this.repository = repository;
    }
    
    // Add
    public void addDiscipline(int id, String name, int semester, String time, Teacher teacher, List<Student> studentsList) {
        try {
            ValidateDiscipline valid = new ValidateDiscipline();
            Discipline newDiscipline = valid.validate(id, name, semester, time, teacher, studentsList);

            repository.save(newDiscipline);
        } catch (DisciplineException e) {
            System.out.println("Alerta: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: não foi possível adicionar a disciplina. Tente novamente mais tarde");
        }
    }
    
    // Remove
    public void removeDiscipline(int id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            System.out.println("Erro: não foi possível excluir a disciplina");
        }
    }
    
    // Update
    public void updateDiscipline(int id, String name, int semester, String time, Teacher teacher, List<Student> studentsList) {
        try {
            // Validate first
            ValidateDiscipline valid = new ValidateDiscipline();
            Discipline updated = valid.validate(id, name, semester, time, teacher, studentsList);
        
            // Delete and Create 
            removeDiscipline(id);    
            repository.save(updated);
        } catch (Exception e) {
            System.out.println("Erro: não foi possível atualizar a disciplina");
        }
    }
    
    // Find by ID
    public Discipline findDisciplineById(int id) {
        try {
            Discipline d = repository.findById(id);
            
            if(d != null) return d;
        } catch (Exception e) {
            System.out.println("Erro: não foi possível encontrar a disciplina pelo ID");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    // Find all
    public List<Discipline> getAllDisciplines() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            System.out.println("Error ao buscar todos os alunos");
        }
        return null;
    }
}