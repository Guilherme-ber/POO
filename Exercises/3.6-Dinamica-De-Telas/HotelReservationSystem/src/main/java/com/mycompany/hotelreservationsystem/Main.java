package com.mycompany.hotelreservationsystem;

import com.mycompany.hotelreservationsystem.views.HomeFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeFrame home = new HomeFrame();
            home.setVisible(true);
        });
    }
}