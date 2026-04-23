package com.mycompany.library.manager;

// Models
import com.mycompany.library.model.*;

// Utils
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class LibraryManager {
    private List<Book> booksList;
    
    // Constructor
    public LibraryManager() {
        this.booksList = new ArrayList<>();
    }
    
    // Add book
    public void addBook(Book b) {
        if(!booksList.contains(b))
            booksList.add(b);
        else System.out.println("Esse livro já existe na biblioteca.");
    }
    
    // Remove book
    public void removeBook(String bookName) {
        if(booksList.size() > 0) {
            for(int i = 0; i < booksList.size(); i++) {
                if(booksList.get(i).getName().contains(bookName)) {
                    booksList.remove(i);
                    i--;
                    System.out.println("O Livro: " + bookName + " foi removido.");
                }
            }
        } else {
            System.out.println("A bilbioteca não possui livros cadastrados.");
        }
    }
    
    // Show all books
    public void showAllBooks() {
        System.out.println("----- Livros na biblioteca -----");
        if(booksList.size() > 0) {
            for(int i = 0; i < booksList.size(); i++) {
                System.out.println("Livro " + (i+1) + ": " + booksList.get(i));
            }
        } else {
            System.out.println("A bilbioteca não possui livros cadastrados.");
        }
    }
    
    // Search book by name
    public int searchByName(String bookName) {
        if(booksList.size() > 0) {
            for(int i = 0; i < booksList.size(); i++) {
                if(booksList.get(i).getName().contains(bookName)) {
                    return i;
                }
            }
        } else {
            System.out.println("A bilbioteca não possui livros cadastrados.");
        }
        return -1;
    }
    
    // Search book by author
    public void searchByAuthor(String author) {
        System.out.println("----- Livros de " + author + " -----");
        if(booksList.size() > 0) {
            for(int i = 0; i < booksList.size(); i++) {
                if(booksList.get(i).getAuthor().contains(author)) {
                    System.out.println("Livro " + (i+1) + ": " + booksList.get(i).getName());
                }
            }
        } else {
            System.out.println("A bilbioteca não possui livros cadastrados.");
        }
    }
    
    // Search author by hometown
    public void searchByHometown(String hometown) {
        boolean found = false;
        System.out.println("----- Autores de " + hometown + " -----");
        if(booksList.size() > 0) {
            for(int i = 0; i < booksList.size(); i++) {
                if(booksList.get(i).getAuthor().getHometown().contains(hometown)) {
                    System.out.println("Autor " + (i+1) + ": " + booksList.get(i).getAuthor().getName());
                    found = true;
                }
            }
            if(!found) System.out.println("Nenhum autor encontrado em " + hometown + ".");
        } else {
            System.out.println("Nenhum autor cadastrado foi encontrado.");
        }
    }
}