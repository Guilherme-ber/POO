package com.mycompany.hotelreservationsystem.views;

import com.mycompany.hotelreservationsystem.managers.HotelManager;
import com.mycompany.hotelreservationsystem.models.Room;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de cadastro e listagem de quartos.
 */
public class RoomFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(RoomFrame.class.getName());

    private final HotelManager hotelManager;

    private javax.swing.JTextField txtRoomNumber;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JTextField txtPrice;

    private javax.swing.JTable tableRooms;
    private DefaultTableModel tableModel;

    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnBack;

    private javax.swing.JLabel lblStatus;

    public RoomFrame(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
        initComponents();
        refreshTable();
    }

    public RoomFrame() {
        this(new HotelManager());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        setTitle("Gerenciamento de Quartos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(620, 500));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Dados do Quarto",
                TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(new JLabel("Número do Quarto:"), gbc);
        txtRoomNumber = new JTextField(8);
        txtRoomNumber.setToolTipText("Número inteiro único que identifica o quarto");
        gbc.gridx = 1; gbc.weightx = 0.4;
        formPanel.add(txtRoomNumber, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Tipo:"), gbc);
        cmbType = new JComboBox<>(new String[]{
            "Solteiro", "Duplo", "Triplo", "Suite", "Suite Presidencial"
        });
        gbc.gridx = 3; gbc.weightx = 0.6;
        formPanel.add(cmbType, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Preço por Noite (R$):"), gbc);
        txtPrice = new JTextField(10);
        txtPrice.setToolTipText("Use ponto como separador decimal (ex: 150.00)");
        gbc.gridx = 1; gbc.weightx = 0.4;
        formPanel.add(txtPrice, gbc);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 6));

        btnAdd    = new JButton("Adicionar Quarto");
        btnRemove = new JButton("Remover Selecionado");
        btnClear  = new JButton("Limpar Campos");
        btnBack   = new JButton("Voltar");

        btnAdd.setMnemonic('A');
        btnRemove.setMnemonic('R');
        btnClear.setMnemonic('L');
        btnBack.setMnemonic('V');

        btnPanel.add(btnAdd);
        btnPanel.add(btnRemove);
        btnPanel.add(btnClear);
        btnPanel.add(btnBack);

        mainPanel.add(btnPanel, BorderLayout.CENTER);

        String[] columns = {"Nº Quarto", "Tipo", "Preço / Noite (R$)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableRooms = new JTable(tableModel);
        tableRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableRooms.setRowHeight(22);
        tableRooms.getTableHeader().setReorderingAllowed(false);
        tableRooms.setFillsViewportHeight(true);

        tableRooms.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableRooms.getColumnModel().getColumn(1).setPreferredWidth(160);
        tableRooms.getColumnModel().getColumn(2).setPreferredWidth(160);

        JPanel tablePanel = new JPanel(new BorderLayout(0, 4));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Quartos Cadastrados",
                TitledBorder.LEFT, TitledBorder.TOP));
        tablePanel.add(new JScrollPane(tableRooms), BorderLayout.CENTER);

        lblStatus = new JLabel(" ");
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.ITALIC));
        tablePanel.add(lblStatus, BorderLayout.SOUTH);

        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addRoom());
        btnRemove.addActionListener(e -> removeRoom());
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> dispose());

        tableRooms.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) fillFieldsFromSelection();
        });

        pack();
        setSize(640, 490);
        setLocationRelativeTo(null);
    }

    private void addRoom() {
        String numberStr = txtRoomNumber.getText().trim();
        String type      = (String) cmbType.getSelectedItem();
        String priceStr  = txtPrice.getText().trim().replace(",", ".");

        if (numberStr.isEmpty() || priceStr.isEmpty()) {
            setStatus("Número do quarto e preço são obrigatórios.", true);
            return;
        }

        int roomNumber;
        double price;

        try {
            roomNumber = Integer.parseInt(numberStr);
        } catch (NumberFormatException ex) {
            setStatus("Número do quarto inválido — informe apenas dígitos.", true);
            return;
        }

        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            setStatus("Preço inválido — use ponto como separador decimal (ex: 150.00).", true);
            return;
        }

        Room room = new Room(roomNumber, type, price);
        boolean added = hotelManager.addRoom(room);

        if (added) {
            setStatus("Quarto nº " + roomNumber + " (" + type + ") cadastrado com sucesso.", false);
            clearFields();
            refreshTable();
        } else {
            setStatus("Já existe um quarto com o número " + roomNumber + ".", true);
        }
    }

    private void removeRoom() {
        int selectedRow = tableRooms.getSelectedRow();
        if (selectedRow < 0) {
            setStatus("Selecione um quarto na tabela para remover.", true);
            return;
        }

        int roomNumber = (int) tableModel.getValueAt(selectedRow, 0);
        String type    = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Deseja remover o quarto nº " + roomNumber + " (" + type + ")?\n"
                + "As reservas associadas a este quarto também serão removidas.",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            hotelManager.removeRoom(roomNumber);
            setStatus("Quarto nº " + roomNumber + " removido.", false);
            clearFields();
            refreshTable();
        }
    }

    private void clearFields() {
        txtRoomNumber.setText("");
        txtPrice.setText("");
        cmbType.setSelectedIndex(0);
        tableRooms.clearSelection();
        txtRoomNumber.requestFocus();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Room r : hotelManager.getRooms()) {
            tableModel.addRow(new Object[]{
                r.getRoomNumber(),
                r.getType(),
                String.format("%.2f", r.getPricePerNight())
            });
        }
        setStatus(tableModel.getRowCount() + " quarto(s) cadastrado(s).", false);
    }

    private void fillFieldsFromSelection() {
        int row = tableRooms.getSelectedRow();
        if (row < 0) return;
        txtRoomNumber.setText(String.valueOf(tableModel.getValueAt(row, 0)));
        cmbType.setSelectedItem(tableModel.getValueAt(row, 1));
        txtPrice.setText(String.valueOf(tableModel.getValueAt(row, 2)));
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
        java.awt.EventQueue.invokeLater(() -> new RoomFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
