package com.mycompany.json;

import com.mycompany.json.model.*;
import com.mycompany.json.manager.*;

/**
 *
 * @author guilh
 */
public class Main {
    public static void jsonSerializerMusic() {
        MusicManager manager = new MusicManager();

        Music m1 = new Music("A", "Guilherme", 200, 1.15);
        Music m2 = new Music("B", "Joao", 290, 1.00);
        Music m3 = new Music("C", "Matheus", 350, 1.30);

        manager.addMusic(m1);
        manager.addMusic(m2);
        manager.addMusic(m3);

        String url = "musicas.json";
        manager.saveFile(url);

        manager.openFile(url);

        System.out.println(manager.toString());
    }
    
    public static void main(String[] args) {
        jsonSerializerMusic();
    }
}