package com.mycompany.airport.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.Scanner;

public class Main {
    // Util functions
    public static int getFlightNumber(Scanner read) {
        System.out.println("Informe o numero do voo: ");
        int flightNumber = read.nextInt();
        read.nextLine();
        return flightNumber;
    }
    
    public static boolean isFlightListEmpty(Airport airport) {
        return airport.getFlightListManager().getFlightList().isEmpty();
    }

    // Main menu
    public static int menu(Scanner read) {
        System.out.println("----- Aeroporto | Bem-vindo! -----");
        System.out.println("1 - Gerenciar aeroporto");
        System.out.println("2 - Gerenciar voo");
        System.out.println("0 - Sair do sistema");
        System.out.println("--> Escolha uma opcao: ");
        int option = read.nextInt();
        read.nextLine();
        return option;
    }
    
    // Airport menu
    public static void airportMenu(Scanner read, Airport airport, Flight f) {
        int option;
        
        do {
            System.out.println("--- Gerenciar Aeroporto ---");
            System.out.println("1 - Adicionar voo");
            System.out.println("2 - Remover voo");
            System.out.println("3 - Listar voos");
            System.out.println("4 - Voos com prejuizo");
            System.out.println("5 - Iniciar um voo");
            System.out.println("6 - Mostrar esse aeroporto");
            System.out.println("0 - Voltar");
            System.out.println("--> Escolha uma opcao: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1 -> {
                    Flight newFlight = new Flight();
                    newFlight.fill(read);
                    airport.addFlight(newFlight);
                }
                
                case 2 -> {
                    if(!airport.getFlightListManager().getFlightList().isEmpty()) {
                        airport.removeFlight(getFlightNumber(read));
                        System.out.println("Voo removido com sucesso.");
                    } else {
                        System.out.println("Nenhum voo cadastrado. Cadastre um novo voo antes de remover.");
                    }
                }
                    
                case 3 -> {
                    StringBuilder sb = new StringBuilder();
                    if(!airport.getFlightListManager().getFlightList().isEmpty()) {
                        String f2 = airport.listFlights(sb);
                        System.out.println(f2);
                    }
                    else System.out.println("Nenhum voo cadastrado. Cadastre um novo voo antes de remover.");
                }

                case 4 -> {
                    if(!airport.getFlightListManager().getFlightList().isEmpty()) System.out.println(airport.getFlightsWithPrejudice());
                    else System.out.println("Nenhum voo cadastrado. Cadastre um novo voo antes de remover.");
                }
                    
                case 5 -> {
                    if(!airport.getFlightListManager().getFlightList().isEmpty()) {
                        int number = getFlightNumber(read);
                        f = airport.getFlightListManager().searchByFlightNumber(number);
                        if(!f.getStopoverManager().getStopoverList().isEmpty() && !f.getPassengerManager().getPassengerList().isEmpty()) airport.startFlight(number);
                        else System.out.println("Escalas e/ou passageiros nao preenchidos. Preencha antes de iniciar o voo.");
                    } else System.out.println("Nenhum voo cadastrado. Cadastre um novo voo antes de remover.");
                    
                }
                   
                case 6 -> System.out.println(airport); 
                
                case 0 -> {
                }

                default -> System.out.println("Opcao invalida!");
            }
        } while (option != 0);
    }
    
    // Flight menu
    public static void flightMenu(Scanner read, Airport airport, Flight f) {
        int option;
        
        do {
            System.out.println("--- Gerenciar Voos ---");
            System.out.println("1 - Adicionar passageiro");
            System.out.println("2 - Remover passageiro");
            System.out.println("3 - Adicionar escala");
            System.out.println("4 - Remover escala");
            System.out.println("5 - Alterar estado do voo");
            System.out.println("6 - Listar passageiros");
            System.out.println("0 - Voltar");
            System.out.println("--> Escolha uma opcao: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    if(f.getPassengerManager().getPassengerList().size() < f.getMaximumPassengerCapacity()) {
                        Passenger p = new Passenger();
                        p.fill(read);
                        f.addPassenger(p);
                        System.out.println("Passageiro '" + p.getName() + "' criado e adicionado ao voo '" + f.getFlightNumber() + "' com sucesso!");
                    }
                }

                case 2 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    if(!f.getPassengerManager().getPassengerList().isEmpty()) {
                        System.out.println("Digite o CPF do passageiro que sera removido: ");
                        String cpf = read.nextLine();
                        
                        f.getPassengerManager().removePassenger(cpf);
                        System.out.println("Passageiro com CPF '" + cpf + "' removido do voo '" + f.getFlightNumber() + "' com sucesso!");
                    }
                }

                case 3 -> {
                    System.out.println("Quantas escalas serao adicionadas? ");
                    int stopovers = read.nextInt();
                    read.nextLine();
                    
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    if(stopovers > 0 && stopovers <= 10) {
                        for(int i = 0; i < stopovers; i++) {
                            System.out.println((i + 1) + " - Nome da escala: ");
                            String so = read.nextLine();
                            if(f != null) {
                                f.getStopoverManager().addStopover(so); 
                                System.out.println("Escala adicionada com sucesso!");
                            } else System.out.println("Numero do voo nao encontrado.");
                        }
                    } else System.out.println("Nao e possivel adicionar mais de 10 ou menos que 0 escalas.");
                }
                    
                case 4 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    if(f != null && !f.getStopoverManager().getStopoverList().isEmpty()) {
                        System.out.println("Nome da escala para remocao: ");
                        String so = read.nextLine();
                        
                        f.getStopoverManager().removeStopover(so);
                        System.out.println("Escala removida com sucesso!");
                    } else System.out.println("Voo nao encontrado ou sem escalas cadastradas.");
                }

                case 5 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    System.out.println("Selecione o Estado de voo");
                    System.out.println("1 - Aguardando decolagem \n2 - Voando \n3 - Concluido");
                    int option2 = read.nextInt();
                    read.nextLine();
                    if(f != null) { 
                        f.switchFlightState(option2);
                        System.out.println("Status do voo alterado para '" + f.getFlightStatus() + "' com sucesso!");
                    } else {
                        System.out.println("Numero do voo nao encontrado.");
                    }
                }
                
                case 6 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    String sb = f.getPassengerManager().showPassengers();
                    System.out.println(sb);
                }

                case 0 -> {
                }

                default -> System.out.println("Opcao invalida!");
            }
        } while (option != 0);
    }
    
    // Main
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        
        // Menu option
        int option;
       
        // Airport and Flight instance
        Airport airport = new Airport();
        airport.fill(read);
        Flight flight = new Flight();
        
        do {
            option = menu(read);

            switch (option) {
                case 1 -> airportMenu(read, airport, flight);

                case 2 -> {
                    if(!isFlightListEmpty(airport)) {
                        flightMenu(read, airport, flight);
                    } else {
                        System.out.println("Nenhum voo cadastrado. Cadastre um novo voo antes de prosseguir.");
                    }
                }    
                
                case 0 -> System.out.println("Encerrando...");

                default -> System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
}