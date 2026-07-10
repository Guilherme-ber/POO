package com.mycompany.main.model.validations;

// Entities
import com.mycompany.main.model.entities.*;

// Exceptions
import com.mycompany.main.model.exceptions.DisciplineException;

// Utils
import java.util.List;

/**
 *
 * @author guilh
 */
public class ValidateDiscipline {
    public Discipline validate(int id, String name, int semester, String time, Teacher teacher, List<Student> studentList) throws DisciplineException {
        Discipline d = new Discipline();
        
        if(name.isEmpty()) throw new DisciplineException("Error - Campo vazio: 'Nome'");
        d.setName(name);
        
        if(semester < 0) throw new DisciplineException("Error - Campo inválido: 'Semestre'");
        d.setSemester(semester);
        
        if(studentList.size() <= 0) throw new DisciplineException("Error - Lista de alunos não possui alunos cadastrados");
        d.setStudentList(studentList);
        
        d.setId(id);
        d.setTime(time);
        d.setTeacher(teacher);
        
        return d;
    }
}
