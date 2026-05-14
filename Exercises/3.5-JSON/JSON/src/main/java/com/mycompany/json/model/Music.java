package com.mycompany.json.model;

public class Music {
    private String title;
    private String artist;
    private int duration;
    private double price;

    public Music() {
        this.title = "";
        this.artist = "";
        this.duration = 0;
        this.price = 0.0;
    }

    public Music(String title, String artist, int duration, double price) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.price = price;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Musica{titulo=" + title + ", artista=" + artist + ", duracao=" + duration + ", preco=" + price + "}";
    }
}
