package com.mycompany.music.manager;

// Managers
import com.mycompany.music.manager.manager.MusicManager;

// Models
import com.mycompany.music.manager.model.*;

/**
 *
 * @author guilherme
 */
public class Main {
    public static void main(String[] args) {
        Artist a1 = new Artist("M4rkin");
        Album ab1 = new Album("Blue Lock muscis", 2025);
        Album ab2 = new Album("Bleach musics", 2025);
        Album ab3 = new Album("Solo Leveling musics", 2025);
        
        Artist a2 = new Artist ("Luan Santana");
        Album alb1 = new Album("Chuva de arroz", 2011);
        Album alb2 = new Album("Turne Brasil", 2012);
        
        a1.addAlbums(ab1);
        a1.addAlbums(ab2);
        a1.addAlbums(ab3);
        
        // Create musics and add on albums
        Music Ulquiorra = new Music("Ulquiorra", 5.30);
        Music Ubers = new Music("Ubers", 8.00);
        Music Bastard = new Music("Bastard", 7.10);
        Music Mutile = new Music("Mutile", 4.10);
        Music Monarca = new Music("Monarca", 3.40);
        
        a1.addMusic(ab1.getName(), Ulquiorra);
        a1.addMusic(ab1.getName(), Ubers);
        a1.addMusic(ab1.getName(), Ubers);
        a1.addMusic(ab2.getName(), Mutile);
        a1.addMusic(ab3.getName(), Bastard);
        
        a2.addAlbums(alb1);
        a2.addAlbums(alb2);
        
        Music m1 = new Music("M1", 2.30);
        Music m2 = new Music("M2", 2.55);
        
        a2.addMusic(alb1.getName(), m1);
        a2.addMusic(alb1.getName(), m2);
        
        // Manager
        MusicManager manager = new MusicManager();
        manager.addArtist(a1);
        manager.addArtist(a2);

        Artist artistsWithMoreAlbums = manager.artistWithMoreAlbums();
        System.out.println("Artista com mais albuns: " + artistsWithMoreAlbums.getName());

        Album albumWithMoreMusics = manager.albumWithMoreMusics();
        System.out.println("Album com mais musicas: " + albumWithMoreMusics.getName());
    }
}
