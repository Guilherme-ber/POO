package com.mycompany.music.manager.manager;

// Models
import com.mycompany.music.manager.model.*;

// Utils
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class MusicManager {
    private List<Artist> artistsList;
    
    // Constructor
    public MusicManager() {
        this.artistsList = new ArrayList<>();
    }

    // Add
    public void addArtist(Artist artist) {
        this.artistsList.add(artist);
    }  
    
    public Artist artistWithMoreAlbums(){
        Artist aWithMoreAlbums = null;
        int numAlbums = 0;
        
        for(Artist artist : artistsList){
            int quant = artist.getAlbums().size();
            
            if(quant > numAlbums){
                numAlbums = quant;
                aWithMoreAlbums = artist;
            }
        }
        return aWithMoreAlbums;
    }
    
    public Album albumWithMoreMusics(){
        Album aMoreMusics = null;
        int numMusics = 0;
        
        for(Artist artist : artistsList){
            for(Album album : artist.getAlbums()){
                
            int quant = album.getMusicsList().size();
            
                if(quant > numMusics){
                    numMusics = quant;
                    aMoreMusics = album;
            }
            }
        }
        return aMoreMusics;
    }   
    
    // Getters and Setters
    public List<Artist> getArtists() {
        return artistsList;
    }
}