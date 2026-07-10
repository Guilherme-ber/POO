package com.mycompany.main.model.entities;

/**
 *
 * @author guilh
 */
public class Teacher extends People {
    // Constructors
    public Teacher() {
    }
    public Teacher(int id, String name, char sex, int age, String cpf) {
        super(id, name, sex, age, cpf);
    }
    
    // ToString
    @Override
    public String toString() {
        return super.toString();
    }
}
