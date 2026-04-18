package com.mycompany.music.manager.model;

// Utils
import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class Music {
    private String title;
    private double duration;
    
    // Constructors
    public Music() {
        this.title = "";
        this.duration = 0.0;
    }
    public Music(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }
    
    // Copy
    public void copy(Music other) {
        this.title = other.getTitle();
        this.duration = other.getDuration();
    }
    
    // Print
    public void print(){
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        return "Musica {" + 
                "Title: " + title + 
                ", Duracao: " + duration + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.duration) ^ (Double.doubleToLongBits(this.duration) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Music other = (Music) obj;
        if (Double.doubleToLongBits(this.duration) != Double.doubleToLongBits(other.duration)) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    public double getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
}
