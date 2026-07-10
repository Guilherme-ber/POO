package com.mycompany.main.model.validations;

// Models
import com.mycompany.main.model.exceptions.StudentException;
import com.mycompany.main.model.entities.Student;

/**
 *
 * @author guilh
 */
public class ValidateStudent extends BaseValidator {
    public Student validate(int id, String name, char sex, int age, String cpf, String registration, int entryYear) throws StudentException, Exception {
        // Validate common data
        validateBaseData(id, name, sex, age, cpf);
        
        if(registration.isEmpty()) throw new StudentException("Error - Campo vazio: 'Matricula'");
        if(entryYear < 2000) throw new StudentException("Error - Ano de entrada inválido");
        
        return new Student(id, name, sex, age, cpf, registration, entryYear);
    }
}
