package com.mycompany.main.model.entities;

/**
 *
 * @author guilh
 */
public class Student extends People {
    private String registration;
    private int entryYear;
    
    public Student() {
    }
    
    // Constructor
    public Student(String name, char sex, int age, String cpf, String registration, int entryYear) {
        super(name, sex, age, cpf);
        this.registration = registration;
        this.entryYear = entryYear;
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" | Matricula: ").append(registration);
        sb.append(" | Ano de entrada: ").append(entryYear);
        return sb.toString();
    }
    
    // Getters and Setters
    public String getRegistration() {
        return registration;
    }
    public int getEntryYear() {
        return entryYear;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    public void setEntryYear(int entryYear) {
        this.entryYear = entryYear;
    }
}
