package com.mycompany.music.manager.model;

// Utils
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class Album {
    private String name;
    private int yearOfRelease;
    private List<Music> musicsList;

    // Constructors
    public Album() {
        this.name = "";
        this.yearOfRelease = 0;
        this.musicsList = new ArrayList<>();
    }
    public Album(String name, int yearOfRelease) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.musicsList = new ArrayList<>();
    }

    // Copy
    public void copy(Album other) {
        this.name = other.getName();
        this.yearOfRelease = other.getyearOfRelease();
        
        for(int i = 0; i <= other.musicsList.size() - 1; i++){
            Music mi = other.musicsList.get(i);
            
            Music nova = new Music();
            nova.copy(mi);
            
            this.musicsList.add(nova); 
            
        }           
    }
    
    // Print
    public void print(){
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        return "Album{" 
                + "Name: " + name 
                + ", Ano de lancamento: " + yearOfRelease 
                + ", Musicas: " + musicsList 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.yearOfRelease;
        hash = 37 * hash + Objects.hashCode(this.musicsList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (this.yearOfRelease != other.yearOfRelease) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.musicsList, other.musicsList);
    }
    
    // Add
    public void addMusic(Music music){
        Music nova = new Music();
        nova.copy(music);
        this.musicsList.add(nova);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public int getyearOfRelease() {
        return yearOfRelease;
    }
    public List<Music> getMusicsList() {
        return musicsList;   
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setyearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}