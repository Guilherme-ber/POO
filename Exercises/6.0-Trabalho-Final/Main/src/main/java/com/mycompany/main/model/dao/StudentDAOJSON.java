package com.mycompany.main.model.dao;

// Interface
import com.mycompany.main.model.dao.interfaces.IDAO;

// Entities
import com.mycompany.main.model.entities.Student;

// File
import com.mycompany.main.model.file.FilePersistenceJSON;
import com.mycompany.main.model.file.SerializerJSON;

// Utils
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilh
 */
public class StudentDAOJson implements IDAO<Student> {
    private final String pathname;
    private final FilePersistenceJSON persistence;
    private final SerializerJSON serializer;

    // Constructor
    public StudentDAOJson(String pathname) {
        this.pathname = pathname;
        this.persistence = new FilePersistenceJSON();
        this.serializer = new SerializerJSON();
    }
    
    @Override
    public void save(Student student) throws Exception {
        List<Student> list = findAll();
        list.add(student);

        // Serializer
        String studentString = serializer.toFile(list);

        // Persistence
        persistence.saveToFile(studentString, this.pathname);
    }
    
    @Override
    public void delete(int id) throws Exception {
        Student studentToRemove = findById(id);

        if(studentToRemove != null) {
            List<Student> list = findAll();
            list.remove(studentToRemove);

            String studentString = serializer.toFile(list);
            persistence.saveToFile(studentString, pathname);
        }
    }
    
    @Override
    public void update(Student student) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Student findById(int id) throws Exception {
        List<Student> list = findAll();

        if(!list.isEmpty()) {
            for(Student s : list) {
                if(s.getId() == id){
                    return s;
                }
            }
        }
        
        return null;
    }
    
    @Override
    public List<Student> findAll() throws Exception {
        String json = persistence.loadFromFile(this.pathname);

        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }

        return serializer.fromFile(json, Student.class);
    }
}
