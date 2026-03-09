import java.util.Scanner;

class Music {
    private String title;
    private String artist;
    private int duration;
    private double price;
    
    // Constructor
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
    
    // Music Methods
    public void playSong() {
        System.out.println("-> Ligar musica.");
        System.out.println("--> Tocando: " + this.title);
    }
    public void turnOffMusic() {
        System.out.println("-> Desligar musica.");
    }

    // Fill
    public void fill() {
        Scanner readLine = new Scanner(System.in);
        
        System.out.println("--- Adicionar musica ---");        
        
        System.out.println("Digite o titulo: ");
        String iTitle = readLine.nextLine();
        System.out.println("Digite o artista: ");
        String iArtist = readLine.nextLine();
        System.out.println("Digite a duracao: ");
        int iDuration = readLine.nextInt();
        System.out.println("Digite o preco: ");
        double iPrice = readLine.nextDouble();
        
        this.title = iTitle;
        this.artist = iArtist;
        this.duration = iDuration;
        this.price = iPrice;
    }
    
    // Print
    public void print() {
        System.out.println("--- Ver Musica ---");
        System.out.println("Titulo: " + this.title);
        System.out.println("Artista: " + this.artist);
        System.out.println("Duracao: " + this.duration);
        System.out.println("Preco: " + this.price);
    }
    
    // Getter's and Setter's
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public int getDuration() {
        return duration;
    }
    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setPrice(double price) {
        this.price = price;
    }    
}

public class Musica {
    public static void main(String args[]) {
        Music a = new Music();
        a.fill();
        a.print();
        a.playSong();
        a.turnOffMusic();
    }
}
