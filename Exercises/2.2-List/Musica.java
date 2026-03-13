import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
    public static int menu() {
        System.out.println("-------- Menu -------- ");
        System.out.println("1 - Adicionar musica");
        System.out.println("2 - Remover muscia");
        System.out.println("3 - Tocar musica");
        System.out.println("4 - Pesquisar musica");        
        System.out.println("5 - Mostrar todas as musicas");        
        System.out.println("6 - Sair");
        
        Scanner read = new Scanner(System.in);
        System.out.println("Escolha uma opcao: ");
        int choice = read.nextInt();
        return choice;
    } 
    
    /**
     *
     * @param musicList
     */
    public static void addMusic(List<Music> musicList) {
        Music a = new Music();
        a.fill();
        musicList.add(a);
    }
    
    public static void removeMusic(List<Music> musicList) {
        Scanner read = new Scanner(System.in);
        
        System.out.println("Informe a posicao da musica: ");
        int index = read.nextInt();
        musicList.remove(index);
    }
    
    public static void playMusic(List<Music> musicList) {
        Scanner read = new Scanner(System.in);
        
        System.out.println("Informe a posicao da musica: ");
        int index = read.nextInt();
        Music playingMusic = musicList.get(index);
        System.out.println("--> Tocando: " + playingMusic.getTitle() + " de " + playingMusic.getArtist());
    }
    
    public static void searchMusic(List<Music> musicList) {
        Scanner read = new Scanner(System.in);
        
        System.out.println("Procurar por: ");
        System.out.println("1 - Posicao/Indice ");
        System.out.println("2 - Nome ");
        int choice = read.nextInt();
        read.nextLine();
        
        if(choice == 1) {
            System.out.println("Informe a posicao: ");
            int index = read.nextInt();
            
            Music playingMusic = musicList.get(index);
            playingMusic.print();
        } else if(choice == 2) {
            System.out.println("Informe o nome da musica: ");
            String name = read.nextLine();
            boolean found = false;
            for(int i = 0; i < musicList.size(); i++) {
                Music music = musicList.get(i);
                if(name.equalsIgnoreCase(music.getTitle())) {
                    music.print();
                    found = true;
                    break;
                }
            }
            if(!found) System.out.println("Musica nao encontrada!");
        } else {
            System.out.println("Opcao Invalida. Tente novamente!");
        }
    }
    
    public static void showMusics(List<Music> musicList) {
        if(musicList.isEmpty()) {
            System.out.println("Voce ainda nao adicionou nenhuma musica.");
        } else {
            System.out.println("-------- Mostrando todas as musicas --------");
            for(int i = 0; i < musicList.size(); i++) {
                Music currentlyMusic = musicList.get(i);
                currentlyMusic.print();
                System.out.println("-----------------------------");
            }
        }
    }
    
    public static void main(String args[]) {
        List<Music> musicList = new ArrayList<>();
        
        for(int i = 0; i >= 0; i++) {
            int choice = menu();
            switch(choice) {
                case 1:
                    addMusic(musicList);
                    break;
                case 2:
                    removeMusic(musicList);
                    break;
                case 3:
                    playMusic(musicList);
                    break;
                case 4:
                    searchMusic(musicList);
                    break;
                case 5:
                    showMusics(musicList);
                    break;
                default:
                    System.out.println("Saindo...");
                    i = -2;
                    break;
            }
        }
    }
}