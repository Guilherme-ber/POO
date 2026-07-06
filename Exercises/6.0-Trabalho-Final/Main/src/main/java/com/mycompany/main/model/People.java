package com.mycompany.main.model;

// Utils
import java.util.Objects;

/**
 *
 * @author guilh
 */
public class People {
    private String name;
    private char sex;
    private int age;
    private String cpf;
    
    // Constructors
    public People() {
    }
    public People(String name, char sex, int age, String cpf) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.cpf = cpf;
    }
    
    // Hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.sex;
        hash = 67 * hash + this.age;
        hash = 67 * hash + Objects.hashCode(this.cpf);
        return hash;
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
        final People other = (People) obj;
        if (this.sex != other.sex) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.cpf, other.cpf);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(name);
        sb.append(" | Sexo: ").append(sex);
        sb.append(" | Idade: ").append(age);
        sb.append(" | CPF: ").append(cpf);
        return sb.toString();
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public char getSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    public String getCpf() {
        return cpf;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
