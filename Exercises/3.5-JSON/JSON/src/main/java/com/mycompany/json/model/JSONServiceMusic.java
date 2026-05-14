package com.mycompany.json.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONServiceMusic {
    private ObjectMapper mapper = new ObjectMapper();

    public String musicsToJSON(Musics musicas) {
        try {
            return mapper.writeValueAsString(musicas);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Musics JSONToMusics(String jsonString) {
        try {
            return mapper.readValue(jsonString, Musics.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}