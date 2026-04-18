package com.mycompany.music.manager.model;

// Utils
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class Artist {
    private String name;
    List<Album> albumsList;
    
    // Contructors
    public Artist() {
        this.name = "";
        this.albumsList = new ArrayList<>();
    }
    public Artist(String name) {
        this.name = name;
        this.albumsList = new ArrayList<>();
    }

    // Print
    public void print(){
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        return "Artista {" 
                + "Name: " + name 
                + ", Albuns:" + albumsList 
                + '}';
    }
    
    // Add Album
    public void addAlbums(Album album) {
        this.albumsList.add(album);  
       }
    
    // Add Music
    public void addMusic(String nameAlbum, Music music){
        for (Album album : albumsList){
            if(album.getName().equals(nameAlbum)){
                album.addMusic(music);
                return;
            }
        }
        System.out.println("Album: '" + nameAlbum + "' nao encontrado.");
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public List<Album> getAlbums() {
        return albumsList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
