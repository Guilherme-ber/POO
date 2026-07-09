package com.mycompany.main.model.entities;

/**
 *
 * @author guilh
 */
public class Teacher extends People {
    // Constructor
    public Teacher (String name, char sex, int age, String cpf) {
        super(name, sex, age, cpf);
    }
    
    // ToString
    @Override
    public String toString() {
        return super.toString();
    }
}
