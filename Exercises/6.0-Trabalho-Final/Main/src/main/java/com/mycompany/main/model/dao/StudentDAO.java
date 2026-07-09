package com.mycompany.main.model.dao;

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
    private final String pathname;
    private final FilePersistenceJSON persistence;
    private final SerializerJSON serializer;

    // Constructor
    public StudentDAO(String pathname) {
        this.pathname = pathname;
        this.persistence = new FilePersistenceJSON();
        this.serializer = new SerializerJSON();
    }
    
    @Override
    public void save(Student student) throws Exception {
        try {
            List<Student> studentsList = searchAll();
            studentsList.add(student);

            // Serializer
            String studentString = serializer.toFile(studentsList);

            // Persistence
            persistence.saveToFile(studentString, this.pathname);
        } catch (Exception e) {
            
        }  
    }
    
    @Override
    public void delete(int id) throws Exception {
        try {
            Student studentToRemove = searchById(id);

            if(studentToRemove != null) {
                List<Student> studentsList = searchAll();
                studentsList.remove(studentToRemove);

                String studentString = serializer.toFile(studentsList);
                persistence.saveToFile(studentString, pathname);
            }
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void update(Student student) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Student searchById(int id) throws Exception {
        try {
            List<Student> studentsList = searchAll();
        
            if(!studentsList.isEmpty()) {
                for(Student sd : studentsList) {
                    if(sd.getId() == id){
                        return sd;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Student> searchAll() throws Exception {
            try {
                String json = persistence.loadFromFile(this.pathname);

            if (json == null || json.isEmpty()) {
                return new ArrayList<>();
            }

            return serializer.fromFile(json);
        } catch (Exception e) {
            return null;
        }
    }
}
