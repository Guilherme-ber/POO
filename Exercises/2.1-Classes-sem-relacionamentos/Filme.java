import java.util.Scanner;

class Movie {
    private String title;
    private String director;
    private int duration;
    private double price;
    
    // Constructor
    public Movie() {
        this.title = "";
        this.director = "";
        this.duration = 0;
        this.price = 0.0;
    }
    public Movie(String title, String director, int duration, double price) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.price = price;
    }
    public void copy(Movie other) {
        this.title = other.getTitle();
        this.director = other.getDirector();
        this.duration = other.getDuration();
        this.price = other.getPrice();
    }
    
    // Fill
    public void fill() {
        Scanner readLine = new Scanner(System.in);
        
        System.out.println("--- Adicionar filme ---");            
        System.out.println("Digite o titulo: ");
        this.title = readLine.nextLine();
        System.out.println("Digite o diretor: ");
        this.director = readLine.nextLine();
        System.out.println("Digite a duracao: ");
        this.duration = readLine.nextInt();
        System.out.println("Digite o preco: ");
        this.price = readLine.nextDouble();
    }
    
    // Getter's and Setter's
    public String getTitle() {
        return this.title;
    }
    public String getDirector() {
        return this.director;
    }
    public int getDuration() {
        return this.duration;
    }
    public double getPrice() {
        return this.price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

public class Filme {
    public static void main(String args[]) {
    }
}
