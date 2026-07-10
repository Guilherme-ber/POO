package com.mycompany.main.model.file;

// Jackson
import com.fasterxml.jackson.databind.ObjectMapper;

// Utils
import java.util.List;

/**
 *
 * @author guilh
 */
public class SerializerJSON {
    // Serializa um objeto
    public <T> String toFile(List<T> list) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(list);

        return jsonString;
    }

    // Desserializa JSON em formato String
    public <T> List<T> fromFile(String jsonString, Class<T> type) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<T> list = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, type));

        return list;
    }
}