package com.mycompany.csv.model;

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

    public void copy(Music other) {
        this.title = other.getTitle();
        this.artist = other.getArtist();
        this.duration = other.getDuration();
        this.price = other.getPrice();
    }

    @Override
    public String toString() {
        return "Musica{title=" + title + ", artist=" + artist + ", duration=" + duration + ", price=" + price + "}";
    }
    
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDuration() { return duration; }
    public double getPrice() { return price; }

    public void setTitle(String title) { this.title = title; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setPrice(double price) { this.price = price; }
}
