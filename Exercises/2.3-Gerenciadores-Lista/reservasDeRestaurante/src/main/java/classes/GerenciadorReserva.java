package classes;

// Utils
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class GerenciadorReserva {
    private List<Reserva> reserveManage = new ArrayList<>();
    
    public void addReserve() {
        Reserva reserve = new Reserva();
        reserve.fill();
        
        reserveManage.add(reserve);
    }
    
    public void removeReserve(Reserva reserve) {
        reserveManage.remove(reserve);
    }
    
    public void listReserves() {
        System.out.println("---------- Listar reservas ----------");
        for(Reserva reserve : reserveManage) {
            System.out.println("Reserva de: " + reserve.getName());
            System.out.println("Numero de pessoas: " + reserve.getPeople_num());
            System.out.println("Data e hora: " + reserve.getDate_hour());
            System.out.println("--------------------------------------------");
        }
    }
    
    public void searchReserve(String reserveName) {
        for(int i = 0; i <= reserveManage.size(); i++) {
            if(reserveManage.get(i).getName() == reserveName) {
                System.out.println("Nome da reserva na posição " + i + " da lista.");
            }
        }
    }
}
