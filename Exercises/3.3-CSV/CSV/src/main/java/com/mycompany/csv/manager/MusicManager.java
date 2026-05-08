package com.mycompany.csv.manager;

import com.mycompany.csv.FilePersistence;
import com.mycompany.csv.model.Music;
import com.mycompany.csv.model.CSVMusicSerializer;
import java.util.ArrayList;
import java.util.List;

public class MusicManager {
    private List<Music> musicsList;

    public MusicManager() {
        this.musicsList = new ArrayList<>();
    }

    public void addMusic(Music music) {
        musicsList.add(music);
        System.out.println("Musica " + music.getTitle() + " adicionada com sucesso!");
    }

    public boolean removeMusic(String title) {
        for (Music m : musicsList) {
            if (m.getTitle().equals(title)) {
                musicsList.remove(m);
                System.out.println("Musica removida com sucesso!");
                return true;
            }
        }
        System.out.println("Musica nao encontrada.");
        return false;
    }

    public Music searchMusic(String title) {
        for (Music m : musicsList) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }

    public void saveMusic(Music music) {
        for (int i = 0; i < musicsList.size(); i++) {
            if (musicsList.get(i).getTitle().equals(music.getTitle())) {
                musicsList.set(i, music);
                return;
            }
        }
        musicsList.add(music);
    }

    public void updateMusic(String currentTitle, Music newMusic) {
        for (int i = 0; i < musicsList.size(); i++) {
            if (musicsList.get(i).getTitle().equals(currentTitle)) {
                musicsList.set(i, newMusic);
                System.out.println("Musica atualizada com sucesso!");
                return;
            }
        }
        System.out.println("Musica nao encontrada.");
    }

    public void saveFile(String src) {
        CSVMusicSerializer serializer = new CSVMusicSerializer();
        String csvData = serializer.toCSV(musicsList);
        FilePersistence filePersistence = new FilePersistence();
        filePersistence.saveToFile(csvData, src);
        System.out.println("Musicas salvas com sucesso em " + src);
    }

    public void openFile(String src) {
        FilePersistence filePersistence = new FilePersistence();
        String csvData = filePersistence.loadFromFile(src);
        CSVMusicSerializer serializer = new CSVMusicSerializer();
        this.musicsList = serializer.fromCSV(csvData);
        System.out.println("Musicas carregadas com sucesso de " + src);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Music m : musicsList) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
}
