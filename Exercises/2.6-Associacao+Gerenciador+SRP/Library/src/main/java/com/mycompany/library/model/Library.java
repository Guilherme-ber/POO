package com.mycompany.library.model;

// Managers
import com.mycompany.library.manager.*;

// Utils
import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class Library {
    private String name;
    private LibraryManager libraryManager;
    
    // Constructor
    public Library() {
        this.libraryManager = new LibraryManager();
    }
    public Library(String name) {
        this.name = name;
        this.libraryManager = new LibraryManager();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        if (obj == null || getClass() != obj.getClass()) return false;

        Book other = (Book) obj;

        return Objects.equals(this.name, other.name) && 
               Objects.equals(this.author, other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }
    
    // Add book
    public void addBook(Book b) {
        libraryManager.addBook(b);
    }
    
    // Remove book by name
    public void removeBook(String bookName) {
        libraryManager.removeBook(bookName);
    }
    
    // Show all books
    public void showAllBooks() {
        libraryManager.showAllBooks();
    }
    
    // Search book by name
    public int searchByName(String bookName) {
        return libraryManager.searchByName(bookName);
    }
    
    // Search book by author
    public void searchByAuthor(String author) {
        libraryManager.searchByAuthor(author);
    }
    
    // Search author by hometown
    public void searchByHometown(String hometown) {
        libraryManager.searchByHometown(hometown);
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
