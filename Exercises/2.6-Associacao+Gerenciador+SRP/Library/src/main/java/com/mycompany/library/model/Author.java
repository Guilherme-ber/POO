package com.mycompany.library.model;

// Managers
import com.mycompany.library.manager.*;

/**
 *
 * @author guilherme
 */
public class Author {
    private String name;
    private String hometown;
    private AuthorManager authorManager;
    
    // Constructor
    public Author() {
        this.authorManager = new AuthorManager();
    }
    public Author(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
        this.authorManager = new AuthorManager();
    }
    
    //
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getHometown() {
        return hometown;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}