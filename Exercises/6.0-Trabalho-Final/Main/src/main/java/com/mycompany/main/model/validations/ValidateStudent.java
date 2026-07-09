package com.mycompany.main.model.validations;

// Models
import com.mycompany.main.model.exceptions.StudentException;
import com.mycompany.main.model.entities.Student;

/**
 *
 * @author guilh
 */
public class ValidateStudent {
    public Student validate(String name, char sex, int age, String cpf, String registration, int entryYear) throws StudentException {
        Student s = new Student();
        
        if(name.isEmpty()) throw new StudentException("Error - Campo vazio: 'Nome'");
        s.setName(name);
        
        if(sex == '\0') throw new StudentException("Error - Campo vazio: 'Sexo'");
        if(sex != 'M' || sex != 'F') throw new StudentException("Error - Campo vazio: 'Sexo'");
        s.setSex(sex);
        
        if(age <= 0) throw new StudentException("Error - Idade invalida");
        s.setAge(age);
        
        if(cpf.isEmpty()) throw new StudentException("Error - Campo vazio: 'CPF'");
        s.setCpf(cpf);
        
        if(registration.isEmpty()) throw new StudentException("Error - Campo vazio: 'Matricula'");
        s.setRegistration(registration);
        
        if(entryYear < 2000 && entryYear > 3000) throw new StudentException("Error - Ano de entrada inválido");
        s.setEntryYear(entryYear);
        
        return s;
    }
}
