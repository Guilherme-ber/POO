package com.mycompany.json.model;

import java.util.ArrayList;
import java.util.List;

public class Musics {
    private List<Music> musics = new ArrayList<>();

    public List<Music> getMusics() { return musics; }
    public void setMusics(List<Music> musics) { this.musics = musics; }

    public void addMusic(Music m) {
        this.musics.add(m);
    }
}