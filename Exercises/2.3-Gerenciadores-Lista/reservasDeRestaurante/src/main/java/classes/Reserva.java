package classes;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilherme
 */
public class Reserva {
    private String name;
    private int people_num;
    private String date_hour;
    
    public Reserva() {
    }
    public Reserva(String name, int people_num, String date_hour) {
        this.name = name;
        this.people_num = people_num;
        this.date_hour = date_hour;
    }
    public void copy(Reserva other) {
        this.name = other.getName();
        this.people_num = other.getPeople_num();
        this.date_hour = other.getDate_hour();
    }

    @Override
    public String toString() {
        return String.format("Nome da reserva | Número de pessoas | Data e hora da reserva",
                            name, people_num, date_hour);
    }
    
    public void fill() {
        Scanner read = new Scanner(System.in);
        
        System.out.println("Nome do reservista: ");
        this.name = read.nextLine();
        
        System.out.println("Número de pessoas na mesa: ");
        this.people_num = read.nextInt();
        
        System.out.println("Data e hora da reserva (dd/MM/yy - HH/mm): ");
        this.date_hour = read.nextLine();
    }
    
    public String getName() {
        return name;
    }
    public int getPeople_num() {
        return people_num;
    }
    public String getDate_hour() {
        return date_hour;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }
    public void setDate_hour(String date_hour) {
        this.date_hour = date_hour;
    }
}
