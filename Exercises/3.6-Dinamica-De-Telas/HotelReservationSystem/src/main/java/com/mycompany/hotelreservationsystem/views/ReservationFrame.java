package com.mycompany.hotelreservationsystem.views;

import com.mycompany.hotelreservationsystem.managers.HotelManager;
import com.mycompany.hotelreservationsystem.models.Guest;
import com.mycompany.hotelreservationsystem.models.Reservation;
import com.mycompany.hotelreservationsystem.models.Room;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de cadastro e listagem de reservas.
 *
 * <p>A seleção de hóspede e quarto é feita via JDialogs modais
 * ({@link DlgSelecionarHospede} e {@link DlgSelecionarQuarto}),
 * garantindo que a escolha seja finalizada antes de prosseguir.
 */
public class ReservationFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(ReservationFrame.class.getName());

    private final HotelManager hotelManager;

    // ── Estado do formulário ──
    private Guest guestSelecionado = null;
    private Room  roomSelecionada  = null;

    // ── Componentes do formulário ──
    private JTextField txtGuestDisplay;
    private JTextField txtRoomDisplay;
    private JTextField txtCheckIn;
    private JTextField txtCheckOut;

    private JButton btnSelecionarHospede;
    private JButton btnSelecionarQuarto;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnClear;
    private JButton btnBack;

    // ── Tabela ──
    private JTable tableReservations;
    private DefaultTableModel tableModel;

    // ── Status ──
    private JLabel lblStatus;

    public ReservationFrame(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
        initComponents();
        refreshTable();
    }

    public ReservationFrame() {
        this(new HotelManager());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        setTitle("Gerenciamento de Reservas");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(700, 580));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);

        // ══════════════════════════════════════════════════════════════════
        // NORTE — Formulário de reserva
        // ══════════════════════════════════════════════════════════════════
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Nova Reserva",
                TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ── Linha 0 — Hóspede ──
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(new JLabel("Hóspede:"), gbc);

        txtGuestDisplay = new JTextField(22);
        txtGuestDisplay.setEditable(false);
        txtGuestDisplay.setBackground(UIManager.getColor("TextField.background"));
        txtGuestDisplay.setToolTipText("Clique em 'Selecionar' para escolher o hóspede");
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(txtGuestDisplay, gbc);

        btnSelecionarHospede = new JButton("Selecionar Hóspede…");
        btnSelecionarHospede.setMnemonic('H');
        btnSelecionarHospede.setToolTipText("Abre o diálogo de seleção de hóspede (Alt+H)");
        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(btnSelecionarHospede, gbc);

        // ── Linha 1 — Quarto ──
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Quarto:"), gbc);

        txtRoomDisplay = new JTextField(22);
        txtRoomDisplay.setEditable(false);
        txtRoomDisplay.setBackground(UIManager.getColor("TextField.background"));
        txtRoomDisplay.setToolTipText("Clique em 'Selecionar' para escolher o quarto");
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(txtRoomDisplay, gbc);

        btnSelecionarQuarto = new JButton("Selecionar Quarto…");
        btnSelecionarQuarto.setMnemonic('Q');
        btnSelecionarQuarto.setToolTipText("Abre o diálogo de seleção de quarto (Alt+Q)");
        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(btnSelecionarQuarto, gbc);

        // ── Linha 2 — Datas ──
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Check-in (DD/MM/AAAA):"), gbc);

        txtCheckIn = new JTextField(12);
        txtCheckIn.setToolTipText("Data de entrada, ex: 15/06/2025");
        gbc.gridx = 1; gbc.weightx = 0.5;
        formPanel.add(txtCheckIn, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        formPanel.add(new JLabel("Check-out (DD/MM/AAAA):"), gbc);

        txtCheckOut = new JTextField(12);
        txtCheckOut.setToolTipText("Data de saída, ex: 20/06/2025");
        gbc.gridx = 1; gbc.weightx = 0.5;
        formPanel.add(txtCheckOut, gbc);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // ══════════════════════════════════════════════════════════════════
        // CENTRO — Botões de ação
        // ══════════════════════════════════════════════════════════════════
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 6));

        btnAdd    = new JButton("Confirmar Reserva");
        btnRemove = new JButton("Cancelar Selecionada");
        btnClear  = new JButton("Limpar Campos");
        btnBack   = new JButton("Voltar");

        btnAdd.setMnemonic('C');
        btnRemove.setMnemonic('X');
        btnClear.setMnemonic('L');
        btnBack.setMnemonic('V');

        btnAdd.setToolTipText("Salvar reserva com os dados preenchidos (Alt+C)");
        btnRemove.setToolTipText("Cancelar a reserva selecionada na tabela (Alt+X)");
        btnClear.setToolTipText("Limpar campos do formulário (Alt+L)");
        btnBack.setToolTipText("Fechar esta janela (Alt+V)");

        // Destaque visual no botão principal
        btnAdd.setBackground(new Color(46, 134, 193));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setOpaque(true);

        btnPanel.add(btnAdd);
        btnPanel.add(btnRemove);
        btnPanel.add(btnClear);
        btnPanel.add(btnBack);

        mainPanel.add(btnPanel, BorderLayout.CENTER);

        // ══════════════════════════════════════════════════════════════════
        // SUL — Tabela de reservas cadastradas
        // ══════════════════════════════════════════════════════════════════
        String[] columns = {
            "#", "Hóspede", "Documento", "Quarto", "Tipo", "Preço/Noite (R$)", "Check-in", "Check-out"
        };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableReservations = new JTable(tableModel);
        tableReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableReservations.setRowHeight(22);
        tableReservations.getTableHeader().setReorderingAllowed(false);
        tableReservations.setFillsViewportHeight(true);

        tableReservations.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableReservations.getColumnModel().getColumn(1).setPreferredWidth(140);
        tableReservations.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableReservations.getColumnModel().getColumn(3).setPreferredWidth(60);
        tableReservations.getColumnModel().getColumn(4).setPreferredWidth(90);
        tableReservations.getColumnModel().getColumn(5).setPreferredWidth(110);
        tableReservations.getColumnModel().getColumn(6).setPreferredWidth(90);
        tableReservations.getColumnModel().getColumn(7).setPreferredWidth(90);

        JPanel tablePanel = new JPanel(new BorderLayout(0, 4));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Reservas Cadastradas",
                TitledBorder.LEFT, TitledBorder.TOP));
        tablePanel.add(new JScrollPane(tableReservations), BorderLayout.CENTER);

        lblStatus = new JLabel(" ");
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.ITALIC));
        tablePanel.add(lblStatus, BorderLayout.SOUTH);

        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        // ── Listeners ─────────────────────────────────────────────────────
        btnSelecionarHospede.addActionListener(e -> abrirDialogoHospede());
        btnSelecionarQuarto.addActionListener(e -> abrirDialogoQuarto());
        btnAdd.addActionListener(e -> addReservation());
        btnRemove.addActionListener(e -> removeReservation());
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> dispose());

        pack();
        setSize(760, 600);
        setLocationRelativeTo(null);
    }

    // ── Abertura dos diálogos modais ───────────────────────────────────────

    /**
     * Abre {@link DlgSelecionarHospede} de forma modal.
     * O fluxo só continua após o usuário fechar o diálogo (escolha ou cancelamento).
     */
    private void abrirDialogoHospede() {
        if (hotelManager.getGuests().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum hóspede cadastrado.\nCadastre hóspedes antes de criar uma reserva.",
                    "Sem Hóspedes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DlgSelecionarHospede dlg = new DlgSelecionarHospede(this, hotelManager);
        dlg.setVisible(true);  // bloqueia até fechar (modal)

        Guest escolhido = dlg.getGuestSelecionado();
        if (escolhido != null) {
            guestSelecionado = escolhido;
            txtGuestDisplay.setText(escolhido.getName() + "  [" + escolhido.getDocument() + "]");
        }
    }

    /**
     * Abre {@link DlgSelecionarQuarto} de forma modal.
     */
    private void abrirDialogoQuarto() {
        if (hotelManager.getRooms().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum quarto cadastrado.\nCadastre quartos antes de criar uma reserva.",
                    "Sem Quartos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DlgSelecionarQuarto dlg = new DlgSelecionarQuarto(this, hotelManager);
        dlg.setVisible(true);  // bloqueia até fechar (modal)

        Room escolhido = dlg.getRoomSelecionado();
        if (escolhido != null) {
            roomSelecionada = escolhido;
            txtRoomDisplay.setText("Nº " + escolhido.getRoomNumber()
                    + "  " + escolhido.getType()
                    + "  — R$ " + String.format("%.2f", escolhido.getPricePerNight()) + "/noite");
        }
    }

    // ── Ações da tela ──────────────────────────────────────────────────────

    private void addReservation() {
        if (guestSelecionado == null) {
            setStatus("Selecione um hóspede antes de confirmar a reserva.", true);
            return;
        }
        if (roomSelecionada == null) {
            setStatus("Selecione um quarto antes de confirmar a reserva.", true);
            return;
        }

        String checkIn  = txtCheckIn.getText().trim();
        String checkOut = txtCheckOut.getText().trim();

        if (checkIn.isEmpty() || checkOut.isEmpty()) {
            setStatus("Preencha as datas de check-in e check-out.", true);
            return;
        }

        // Validação simples de formato DD/MM/AAAA
        if (!checkIn.matches("\\d{2}/\\d{2}/\\d{4}") || !checkOut.matches("\\d{2}/\\d{2}/\\d{4}")) {
            setStatus("Datas inválidas — use o formato DD/MM/AAAA.", true);
            return;
        }

        Reservation reservation = new Reservation(guestSelecionado, roomSelecionada, checkIn, checkOut);
        boolean added = hotelManager.addReservation(reservation);

        if (added) {
            setStatus("Reserva criada: " + guestSelecionado.getName()
                    + " — Quarto nº " + roomSelecionada.getRoomNumber()
                    + " (" + checkIn + " → " + checkOut + ")", false);
            clearFields();
            refreshTable();
        } else {
            setStatus("Já existe uma reserva idêntica (mesmo hóspede, quarto e check-in).", true);
        }
    }

    private void removeReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow < 0) {
            setStatus("Selecione uma reserva na tabela para cancelar.", true);
            return;
        }

        String guestName  = (String) tableModel.getValueAt(selectedRow, 1);
        int    roomNumber = (int)    tableModel.getValueAt(selectedRow, 3);
        String checkIn    = (String) tableModel.getValueAt(selectedRow, 6);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Deseja cancelar a reserva de \"" + guestName
                        + "\" no quarto nº " + roomNumber
                        + " (check-in: " + checkIn + ")?",
                "Cancelar Reserva",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            // O índice da linha na view corresponde ao índice real na lista
            // (tabela sem sorter, então view == model index)
            hotelManager.removeReservation(selectedRow);
            setStatus("Reserva cancelada.", false);
            refreshTable();
        }
    }

    private void clearFields() {
        guestSelecionado = null;
        roomSelecionada  = null;
        txtGuestDisplay.setText("");
        txtRoomDisplay.setText("");
        txtCheckIn.setText("");
        txtCheckOut.setText("");
        tableReservations.clearSelection();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        int i = 1;
        for (Reservation r : hotelManager.getReservations()) {
            tableModel.addRow(new Object[]{
                i++,
                r.getGuest().getName(),
                r.getGuest().getDocument(),
                r.getRoom().getRoomNumber(),
                r.getRoom().getType(),
                String.format("%.2f", r.getRoom().getPricePerNight()),
                r.getCheckInDate(),
                r.getCheckOutDate()
            });
        }
        setStatus(tableModel.getRowCount() + " reserva(s) cadastrada(s).", false);
    }

    private void setStatus(String message, boolean isError) {
        lblStatus.setText(message);
        lblStatus.setForeground(isError ? Color.RED : new Color(0, 128, 0));
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
        java.awt.EventQueue.invokeLater(() -> new ReservationFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
