import java.util.ArrayList;
import java.util.List;
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
    
    // To string
    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + title + '\'' +
                ", artista='" + director + '\'' +
                ", duracao=" + duration +
                ", preco=" + price +
                '}';
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
    public static int menu() {
        System.out.println("-------- Menu -------- ");
        System.out.println("1 - Adicionar filme");
        System.out.println("2 - Remover filme");
        System.out.println("3 - Pesquisar filme (nome ou diretor)");        
        System.out.println("4 - Mostrar todos os filmes");        
        System.out.println("5 - Sair");
        
        Scanner read = new Scanner(System.in);
        System.out.println("Escolha uma opcao: ");
        int choice = read.nextInt();
        return choice;
    } 
    
    /**
     *
     * @param movieList
     */
    public static void addMovie(List<Movie> movieList) {
        Movie a = new Movie();
        a.fill();
        movieList.add(a);
    }
    
    public static void removeMovie(List<Movie> movieList) {
        Scanner read = new Scanner(System.in);
        
        System.out.println("Informe a posicao do filme: ");
        int index = read.nextInt();
        movieList.remove(index);
    }
    
    public static void searchMovie(List<Movie> movieList) {
        Scanner read = new Scanner(System.in);
        
        System.out.println("Procurar por: ");
        System.out.println("1 - Posicao/Indice ");
        System.out.println("2 - Nome do filme ");
        System.out.println("3 - Nome do diretor ");
        int choice = read.nextInt();
        read.nextLine();
        
        if(choice == 1) {
            System.out.println("Informe a posicao: ");
            int index = read.nextInt();
            
            Movie playingMovie = movieList.get(index);
            System.out.println(playingMovie);
        } else if(choice == 2) {
            System.out.println("Informe o nome do filme: ");
            String name = read.nextLine();
            boolean found = false;
            for(int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                if(name.equalsIgnoreCase(movie.getTitle())) {
                    System.out.println(movie);
                    found = true;
                    break;
                }
            }
            if(!found) System.out.println("filme nao encontrado!");
        } else if(choice == 3) {
            System.out.println("Informe o nome do diretor dos filmes: ");
            String name = read.nextLine();
            boolean found = false;
            for(int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                if(name.equalsIgnoreCase(movie.getTitle())) {
                    System.out.println(movie);
                    found = true;
                }
            }
            if(!found) System.out.println("filmes nao encontrados!");
        } else {
            System.out.println("Opcao Invalida. Tente novamente!");
        }
    }
    
    public static void showMovies(List<Movie> movieList) {
        if(movieList.isEmpty()) {
            System.out.println("Voce ainda nao adicionou nenhum filme.");
        } else {
            System.out.println("-------- Mostrando todos os filmes --------");
            for(int i = 0; i < movieList.size(); i++) {
                Movie currentlyMovie = movieList.get(i);
                System.out.println(currentlyMovie);
                System.out.println("-----------------------------");
            }
        }
    }
    
    public static void main(String args[]) {
        List<Movie> movieList = new ArrayList<>();
        
        for(int i = 0; i >= 0; i++) {
            int choice = menu();
            switch(choice) {
                case 1:
                    addMovie(movieList);
                    break;
                case 2:
                    removeMovie(movieList);
                    break;
                case 3:
                    searchMovie(movieList);
                    break;
                case 4:
                    showMovies(movieList);
                    break;
                default:
                    System.out.println("Saindo...");
                    i = -2;
                    break;
            }
        }
    }
}
