package com.mycompany.main.model.dao;

// Utils
import java.util.List;

/**
 *
 * @author guilh
 */
public interface IDAO {
    void save(T object) throws Exception;
    void update(T object) throws Exception;
    void delete(int id) throws Exception;
    List searchAll() throws Exception;
    T searchById(int id) throws Exception;
}