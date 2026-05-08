package com.mycompany.json.manager;

import com.mycompany.json.model.*;
import java.util.ArrayList;
import java.util.List;

public class MusicManager {
    private List<Music> musics;

    public MusicManager() {
        this.musics = new ArrayList<>();
    }

    public void addMusic(Music music) {
        musics.add(music);
        System.out.println("Musica " + music.getTitle() + " adicionada!");
    }

    public boolean removeMusic(String title) {
        for (Music m : musics) {
            if (m.getTitle().equals(title)) {
                musics.remove(m);
                System.out.println("Musica removida com sucesso!");
                return true;
            }
        }
        System.out.println("Musica nao encontrada.");
        return false;
    }

    public Music searchMusic(String title) {
        for (Music m : musics) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Music m : musics) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public void updateMusic(String currentTitle, Music newMusic) {
        for (int i = 0; i < musics.size(); i++) {
            if (musics.get(i).getTitle().equals(currentTitle)) {
                musics.set(i, newMusic);
                System.out.println("Musica atualizada com sucesso!");
                return;
            }
        }
        System.out.println("Musica nao encontrada.");
    }

    public void saveFile(String src) {
        Musics wrapper = new Musics();
        
        for (Music m : this.musics) {
            wrapper.addMusic(m);
        }

        JSONServiceMusic jsonService = new JSONServiceMusic();
        String jsonData = jsonService.musicsToJSON(wrapper);

        if (jsonData != null) {
            FilePersistence filePersistence = new FilePersistence();
            filePersistence.saveToFile(jsonData, src);
            System.out.println("Músicas salvas em: " + src);
        }
}

    public void openFile(String src) {
        FilePersistence filePersistence = new FilePersistence();
        String jsonData = filePersistence.loadFromFile(src);

        JSONServiceMusic jsonService = new JSONServiceMusic();
        Musics obj = jsonService.JSONToMusics(jsonData);

        if (obj != null) {
            this.musics = obj.getMusics();
            System.out.println("Musicas carregadas de " + src);
        }
    }
}
