package com.mycompany.main.model.validations;

// Entities
import com.mycompany.main.model.entities.Teacher;

// Exception
import com.mycompany.main.model.exceptions.TeacherException;

/**
 *
 * @author guilh
 */
public class ValidateTeacher extends BaseValidator {
    public Teacher validate(int id, String name, char sex, int age, String cpf) throws TeacherException, Exception {
        try {
            validateBaseData(id, name, sex, age, cpf);
        } catch (Exception e) {
            throw new TeacherException(e.getMessage());
        }
        return new Teacher(id, name, sex, age, cpf);
    }
}
