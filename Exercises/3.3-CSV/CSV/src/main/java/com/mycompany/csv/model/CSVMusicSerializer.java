package com.mycompany.csv.model;

import java.util.ArrayList;
import java.util.List;

public class CSVMusicSerializer {
    public String toCSV(List<Music> musicsList) {
        String csv = "titulo;artista;duracao;preco\n";
        for (Music m : musicsList) {
            csv += m.getTitle() + ";"
                + m.getArtist() + ";"
                + m.getDuration() + ";"
                + m.getPrice() + "\n";
        }
        return csv;
    }

    public List<Music> fromCSV(String data) {
    List<Music> musicsList = new ArrayList<>();
    if (data == null || data.isEmpty()) return musicsList;

    String[] lines = data.split("\n");
    for (int i = 1; i < lines.length; i++) {
        String[] slice = lines[i].split(";");
        if (slice.length >= 4) {
            Music m = new Music();
            m.setTitle(slice[0]);
            m.setArtist(slice[1]);
            m.setDuration(Integer.parseInt(slice[2].trim()));
            m.setPrice(Double.parseDouble(slice[3].trim()));
            musicsList.add(m);
        }
    }
    return musicsList;
}
}
