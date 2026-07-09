package com.mycompany.main.model.dao;

// Jackson
import com.fasterxml.jackson.databind.ObjectMapper;

// Models
import com.mycompany.main.model.entities.Student;
import com.mycompany.main.model.dao.interfaces.IDAO;

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
public class StudentDAO implements IDAO<Student> {
    private String pathname;
    private ObjectMapper mapper;
    private FilePersistenceJSON persistence;
    private SerializerJSON serializer;

    // Constructor
    public StudentDAO(String pathname) {
        this.pathname = pathname;
        this.persistence = new FilePersistenceJSON();
        this.serializer = new SerializerJSON();
    }
    
    public void save(Student student) throws Exception {
        List<Student> studentsList = searchAll();
        studentsList.add(student);
        
        // Serializer
        String studentString = serializer.toFile(studentsList);
        
        // Persistence
        persistence.saveToFile(studentString, this.pathname);
    }
    
    public void update(Student student) throws Exception {
        
    }
    
    public void delete(int id) throws Exception {
        
    }
    
    public Student searchById(int id) throws Exception {
        
    }

    public List<Student> searchAll() throws Exception {
        String json = persistence.loadFromFile(this.pathname);
        
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        
        return serializer.fromFile(json);
    }
}
