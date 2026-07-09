package com.mycompany.main.model.file;

// Jackson
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

// Exeptions
import java.io.IOException;

// Models
import com.mycompany.main.model.entities.Student;

// Utils
import java.util.List;

/**
 *
 * @author guilh
 */
public class SerializerJSON {
    // Serializa um objeto
    public String toFile(List<Student> students) {
        try {
            // Convertendo objeto filme para JSON 
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(students);

            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Desserializa JSON em formato String
    public List<Student> fromFile(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
             
            List<Student> students = mapper.readValue(jsonString, new TypeReference<List<Student>>() {});
            
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
