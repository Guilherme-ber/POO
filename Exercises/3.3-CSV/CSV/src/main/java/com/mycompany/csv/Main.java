package com.mycompany.csv;

import com.mycompany.csv.model.Music;
import com.mycompany.csv.manager.MusicManager;

/**
 *
 * @author guilh
 */
public class Main {
    public static void csvMusicSerializer() {
        MusicManager musicManager = new MusicManager();

        Music m1 = new Music("A", "Guilherme", 200, 1.25);
        Music m2 = new Music("B", "João", 290, 1.00);

        musicManager.addMusic(m1);
        musicManager.addMusic(m2);

        String src = "musics.csv";
        musicManager.saveFile(src);
        musicManager.openFile(src);

        System.out.println(musicManager.toString());
    }
    
    public static void main(String[] args) {
        csvMusicSerializer();
    }
}