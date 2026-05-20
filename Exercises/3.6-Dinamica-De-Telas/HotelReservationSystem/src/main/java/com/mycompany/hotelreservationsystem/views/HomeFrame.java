package com.mycompany.hotelreservationsystem.views;

import com.mycompany.hotelreservationsystem.managers.HotelManager;
import com.mycompany.hotelreservationsystem.models.Reservation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela principal do Hotel Reservation System.
 */
public class HomeFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomeFrame.class.getName());

    private final HotelManager hotelManager;

    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblSubtitle;

    private javax.swing.JLabel lblGuestCount;
    private javax.swing.JLabel lblRoomCount;
    private javax.swing.JLabel lblReservationCount;

    private javax.swing.JButton btnGuests;
    private javax.swing.JButton btnRooms;
    private javax.swing.JButton btnReservations;
    private javax.swing.JButton btnExit;

    private javax.swing.JTable tableRecent;
    private DefaultTableModel tableModel;

    private javax.swing.JLabel lblStatus;

    public HomeFrame() {
        this.hotelManager = new HotelManager();
        initComponents();
        refreshDashboard();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        setTitle("Hotel Reservation System — Home");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 580));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(new Color(245, 247, 250));
        setContentPane(root);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(30, 80, 160));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        lblTitle = new JLabel("🏨  Hotel Reservation System");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);

        lblSubtitle = new JLabel("Gerencie hóspedes, quartos e reservas com facilidade.");
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(200, 220, 255));

        JPanel headerText = new JPanel(new GridLayout(2, 1, 0, 4));
        headerText.setOpaque(false);
        headerText.add(lblTitle);
        headerText.add(lblSubtitle);
        headerPanel.add(headerText, BorderLayout.CENTER);

        root.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 12));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 0, 16));

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 14, 0));
        cardsPanel.setOpaque(false);
        cardsPanel.add(buildCard("Hóspedes",  "0", new Color(52, 152, 219), "lblGuestCount"));
        cardsPanel.add(buildCard("Quartos",   "0", new Color(46, 204, 113), "lblRoomCount"));
        cardsPanel.add(buildCard("Reservas",  "0", new Color(231, 76, 60),  "lblReservationCount"));
        centerPanel.add(cardsPanel, BorderLayout.NORTH);

        JPanel navPanel = new JPanel(new GridLayout(1, 4, 14, 0));
        navPanel.setOpaque(false);
        navPanel.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));

        btnGuests = buildNavButton("👤  Hóspedes", new Color(52, 152, 219));
        btnRooms = buildNavButton("🛏  Quartos", new Color(46, 204, 113));
        btnReservations = buildNavButton("📋  Reservas", new Color(155, 89, 182));
        btnExit = buildNavButton("🚪  Sair", new Color(149, 165, 166));

        navPanel.add(btnGuests);
        navPanel.add(btnRooms);
        navPanel.add(btnReservations);
        navPanel.add(btnExit);

        centerPanel.add(navPanel, BorderLayout.CENTER);
        root.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout(0, 4));
        southPanel.setOpaque(false);
        southPanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Reservas Recentes", TitledBorder.LEFT, TitledBorder.TOP));
        tableWrapper.setOpaque(false);

        String[] cols = {"Hóspede", "Documento", "Quarto", "Tipo", "Check-in", "Check-out"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableRecent = new JTable(tableModel);
        tableRecent.setRowHeight(22);
        tableRecent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableRecent.getTableHeader().setReorderingAllowed(false);
        tableRecent.setFillsViewportHeight(true);

        tableRecent.getColumnModel().getColumn(0).setPreferredWidth(140);
        tableRecent.getColumnModel().getColumn(1).setPreferredWidth(110);
        tableRecent.getColumnModel().getColumn(2).setPreferredWidth(70);
        tableRecent.getColumnModel().getColumn(3).setPreferredWidth(90);
        tableRecent.getColumnModel().getColumn(4).setPreferredWidth(90);
        tableRecent.getColumnModel().getColumn(5).setPreferredWidth(90);

        JScrollPane scroll = new JScrollPane(tableRecent);
        scroll.setPreferredSize(new Dimension(0, 200));
        tableWrapper.add(scroll, BorderLayout.CENTER);

        lblStatus = new JLabel("  Sistema carregado com sucesso.");
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.ITALIC, 12f));
        lblStatus.setForeground(new Color(80, 80, 80));

        southPanel.add(tableWrapper, BorderLayout.CENTER);
        southPanel.add(lblStatus, BorderLayout.SOUTH);
        root.add(southPanel, BorderLayout.SOUTH);

        btnGuests.addActionListener(e -> openGuests());
        btnRooms.addActionListener(e -> openRooms());
        btnReservations.addActionListener(e -> openReservations());
        btnExit.addActionListener(e -> {
            int c = JOptionPane.showConfirmDialog(this, "Deseja sair do sistema?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (c == JOptionPane.YES_OPTION) System.exit(0);
        });

        pack();
        setSize(760, 600);
        setLocationRelativeTo(null);
    }

    private JPanel buildCard(String title, String value, Color color, String fieldName) {
        JPanel card = new JPanel(new GridLayout(3, 1, 0, 4));
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));

        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblValue.setForeground(Color.WHITE);

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTitle.setForeground(new Color(230, 240, 255));

        JLabel lblCaption = new JLabel("cadastrado(s)", SwingConstants.CENTER);
        lblCaption.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblCaption.setForeground(new Color(210, 230, 255));

        card.add(lblValue);
        card.add(lblTitle);
        card.add(lblCaption);

        switch (fieldName) {
            case "lblGuestCount" -> lblGuestCount = lblValue;
            case "lblRoomCount" -> lblRoomCount  = lblValue;
            case "lblReservationCount" -> lblReservationCount = lblValue;
        }

        return card;
    }

    private JButton buildNavButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 54));

        Color hover = bg.darker();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            @Override public void mouseExited (java.awt.event.MouseEvent e) { btn.setBackground(bg); }
        });

        return btn;
    }

    private void openGuests() {
        GuestFrame frame = new GuestFrame(hotelManager);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent e) { refreshDashboard(); }
        });
        frame.setVisible(true);
    }

    private void openRooms() {
        RoomFrame frame = new RoomFrame(hotelManager);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent e) { refreshDashboard(); }
        });
        frame.setVisible(true);
    }

    private void openReservations() {
        ReservationFrame frame = new ReservationFrame(hotelManager);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent e) { refreshDashboard(); }
        });
        frame.setVisible(true);
    }

    private void refreshDashboard() {
        int guests = hotelManager.getGuests().size();
        int rooms = hotelManager.getRooms().size();
        int reservations = hotelManager.getReservations().size();

        lblGuestCount.setText(String.valueOf(guests));
        lblRoomCount.setText(String.valueOf(rooms));
        lblReservationCount.setText(String.valueOf(reservations));

        tableModel.setRowCount(0);
        List<Reservation> list = hotelManager.getReservations();
        int start = Math.max(0, list.size() - 10);
        for (int i = list.size() - 1; i >= start; i--) {
            Reservation r = list.get(i);
            tableModel.addRow(new Object[]{
                r.getGuest().getName(),
                r.getGuest().getDocument(),
                r.getRoom().getRoomNumber(),
                r.getRoom().getType(),
                r.getCheckInDate(),
                r.getCheckOutDate()
            });
        }

        setStatus(String.format("Dashboard atualizado — %d hóspede(s), %d quarto(s), %d reserva(s).", guests, rooms, reservations), false);
    }

    private void setStatus(String msg, boolean error) {
        lblStatus.setText("  " + msg);
        lblStatus.setForeground(error ? Color.RED : new Color(60, 60, 60));
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new HomeFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
