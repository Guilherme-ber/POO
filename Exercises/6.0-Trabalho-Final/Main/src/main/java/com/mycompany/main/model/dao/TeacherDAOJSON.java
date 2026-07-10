package com.mycompany.main.model.dao;

// Interface
import com.mycompany.main.model.dao.interfaces.IDAO;

// Entities
import com.mycompany.main.model.entities.Teacher;

// File
import com.mycompany.main.model.file.FilePersistenceJSON;
import com.mycompany.main.model.file.SerializerJSON;

// Utils
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author guilh
 */
public class TeacherDAOJson implements IDAO<Teacher> {
    private final String pathname;
    private final FilePersistenceJSON persistence;
    private final SerializerJSON serializer;
    
    // Constructor
    public TeacherDAOJson(String pathname) {
        this.pathname = pathname;
        this.persistence = new FilePersistenceJSON();
        this.serializer = new SerializerJSON();
    }
    
    @Override
    public void save(Teacher teacher) throws Exception {
        List<Teacher> list = findAll();
        list.add(teacher);

        // Serializer
        String jsonString = serializer.toFile(list);

        // Persistence
        persistence.saveToFile(jsonString, this.pathname);
    }
    
    @Override
    public void delete(int id) throws Exception {
        Teacher teacherToRemove = findById(id);

        if(teacherToRemove != null) {
            List<Teacher> list = findAll();
            list.remove(teacherToRemove);

            String teacherString = serializer.toFile(list);
            persistence.saveToFile(teacherString, pathname);
        }
    }
    
    @Override
    public void update(Teacher teacher) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Teacher findById(int id) throws Exception {
        List<Teacher> list = findAll();

        if(!list.isEmpty()) {
            for(Teacher t : list) {
                if(t.getId() == id){
                    return t;
                }
            }
        }
        
        return null;
    }
    
    @Override
    public List<Teacher> findAll() throws Exception {
        String json = persistence.loadFromFile(this.pathname);

        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }

        return serializer.fromFile(json, Teacher.class);
    }
}
