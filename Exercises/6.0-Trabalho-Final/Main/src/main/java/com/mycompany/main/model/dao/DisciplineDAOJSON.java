package com.mycompany.main.model.dao;

// Interface
import com.mycompany.main.model.dao.interfaces.IDAO;

// Entities
import com.mycompany.main.model.entities.Discipline;

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
public class DisciplineDAOJson implements IDAO<Discipline> {
    private final String pathname;
    private final FilePersistenceJSON persistence;
    private final SerializerJSON serializer;
    
    // Constructor
    public DisciplineDAOJson(String pathname) {
        this.pathname = pathname;
        this.persistence = new FilePersistenceJSON();
        this.serializer = new SerializerJSON();
    }
    
    @Override
    public void save(Discipline discipline) throws Exception {
        List<Discipline> list = findAll();
        list.add(discipline);

        // Serializer
        String jsonString = serializer.toFile(list);

        // Persistence
        persistence.saveToFile(jsonString, this.pathname);
    }
    
    @Override
    public void delete(int id) throws Exception {
        Discipline disciplineToRemove = findById(id);

        if(disciplineToRemove != null) {
            List<Discipline> list = findAll();
            list.remove(disciplineToRemove);

            String disciplineString = serializer.toFile(list);
            persistence.saveToFile(disciplineString, pathname);
        }
    }
    
    @Override
    public void update(Discipline discipline) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Discipline findById(int id) throws Exception {
        List<Discipline> list = findAll();

        if(!list.isEmpty()) {
            for(Discipline d : list) {
                if(d.getId() == id){
                    return d;
                }
            }
        }
        
        return null;
    }
    
    @Override
    public List<Discipline> findAll() throws Exception {
        String json = persistence.loadFromFile(this.pathname);

        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }

        return serializer.fromFile(json, Discipline.class);
    }
}
