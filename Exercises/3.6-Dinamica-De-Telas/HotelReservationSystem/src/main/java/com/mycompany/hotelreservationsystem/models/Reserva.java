package com.mycompany.hotelreservationsystem.models;

import java.io.Serializable;

public class Reserva implements Serializable {

    private Hospede hospede;
    private Quarto quarto;
    private String checkin;
    private String checkout;

    public Reserva(Hospede hospede, Quarto quarto, String checkin, String checkout) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}