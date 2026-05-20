package com.mycompany.hotelreservationsystem.views;

import com.mycompany.hotelreservationsystem.managers.HotelManager;
import com.mycompany.hotelreservationsystem.models.Guest;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de cadastro e listagem de hóspedes
 */
public class GuestFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GuestFrame.class.getName());

    private final HotelManager hotelManager;

    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtDocument;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtAddress;

    private javax.swing.JTable tableGuests;
    private DefaultTableModel tableModel;

    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnBack;

    private javax.swing.JLabel lblStatus;

    public GuestFrame(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
        initComponents();
        refreshTable();
    }

    public GuestFrame() {
        this(new HotelManager());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Gerenciamento de Hóspedes");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(700, 550));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados do Hóspede", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(new JLabel("Nome:"), gbc);
        txtName = new JTextField(20);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(txtName, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Documento (CPF/RG):"), gbc);
        txtDocument = new JTextField(14);
        gbc.gridx = 3; gbc.weightx = 0.5;
        formPanel.add(txtDocument, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Contato:"), gbc);
        txtContact = new JTextField(20);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(txtContact, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Endereço:"), gbc);
        txtAddress = new JTextField(20);
        gbc.gridx = 3; gbc.weightx = 1.0;
        formPanel.add(txtAddress, gbc);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 6));

        btnAdd = new JButton("Adicionar Hóspede");
        btnRemove = new JButton("Remover Selecionado");
        btnClear = new JButton("Limpar Campos");
        btnBack = new JButton("Voltar");

        btnAdd.setMnemonic('A');
        btnRemove.setMnemonic('R');
        btnClear.setMnemonic('L');
        btnBack.setMnemonic('V');

        btnAdd.setToolTipText("Cadastrar novo hóspede (Alt+A)");
        btnRemove.setToolTipText("Remove o hóspede selecionado (Alt+R)");
        btnClear.setToolTipText("Limpa os campos (Alt+L)");
        btnBack.setToolTipText("Fechar esta janela (Alt+V)");

        btnPanel.add(btnAdd);
        btnPanel.add(btnRemove);
        btnPanel.add(btnClear);
        btnPanel.add(btnBack);

        mainPanel.add(btnPanel, BorderLayout.CENTER);

        String[] columns = {"Nome", "Documento", "Contato", "Endereço"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableGuests = new JTable(tableModel);
        tableGuests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGuests.setRowHeight(22);
        tableGuests.getTableHeader().setReorderingAllowed(false);
        tableGuests.setFillsViewportHeight(true);

        tableGuests.getColumnModel().getColumn(0).setPreferredWidth(160);
        tableGuests.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableGuests.getColumnModel().getColumn(2).setPreferredWidth(120);
        tableGuests.getColumnModel().getColumn(3).setPreferredWidth(220);

        JPanel tablePanel = new JPanel(new BorderLayout(0, 4));
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Hóspedes Cadastrados", TitledBorder.LEFT, TitledBorder.TOP));
        tablePanel.add(new JScrollPane(tableGuests), BorderLayout.CENTER);

        lblStatus = new JLabel(" ");
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.ITALIC));
        tablePanel.add(lblStatus, BorderLayout.SOUTH);

        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addGuest());
        btnRemove.addActionListener(e -> removeGuest());
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> dispose());

        tableGuests.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) fillFieldsFromSelection();
        });

        pack();
        setSize(750, 520);
        setLocationRelativeTo(null);
    }
    
    private void addGuest() {
        String name     = txtName.getText().trim();
        String document = txtDocument.getText().trim();
        String contact  = txtContact.getText().trim();
        String address  = txtAddress.getText().trim();

        if (name.isEmpty() || document.isEmpty()) {
            setStatus("Nome e Documento são obrigatórios.", true);
            return;
        }

        Guest guest = new Guest(name, document, contact, address);
        boolean added = hotelManager.addGuest(guest);

        if (added) {
            setStatus("Hóspede \"" + name + "\" cadastrado com sucesso.", false);
            clearFields();
            refreshTable();
        } else {
            setStatus("Já existe um hóspede com o documento \"" + document + "\".", true);
        }
    }

    private void removeGuest() {
        int selectedRow = tableGuests.getSelectedRow();
        if (selectedRow < 0) {
            setStatus("Selecione um hóspede na tabela para remover.", true);
            return;
        }

        String document = (String) tableModel.getValueAt(selectedRow, 1);
        String name = (String) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Deseja remover o hóspede \"" + name + "\" (Doc: " + document + ")?\n"
                + "As reservas associadas a este hóspede também serão removidas.",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            hotelManager.removeGuest(document);
            setStatus("Hóspede \"" + name + "\" removido.", false);
            clearFields();
            refreshTable();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtDocument.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        tableGuests.clearSelection();
        txtName.requestFocus();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Guest g : hotelManager.getGuests()) {
            tableModel.addRow(new Object[]{
                g.getName(), g.getDocument(), g.getContact(), g.getAddress()
            });
        }
        setStatus(tableModel.getRowCount() + " hóspede(s) cadastrado(s).", false);
    }

    private void fillFieldsFromSelection() {
        int row = tableGuests.getSelectedRow();
        if (row < 0) return;
        txtName.setText((String) tableModel.getValueAt(row, 0));
        txtDocument.setText((String) tableModel.getValueAt(row, 1));
        txtContact.setText((String) tableModel.getValueAt(row, 2));
        txtAddress.setText((String) tableModel.getValueAt(row, 3));
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
        java.awt.EventQueue.invokeLater(() -> new GuestFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
