package com.mycompany.hotelreservationsystem.views;

import com.mycompany.hotelreservationsystem.managers.HotelManager;
import com.mycompany.hotelreservationsystem.models.Guest;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

/**
 * Diálogo modal para seleção de um hóspede cadastrado.
 */
public class DlgSelecionarHospede extends JDialog {

    private Guest guestSelecionado = null;

    private JTextField txtFiltro;
    private JTable tableGuests;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    private JButton btnSelecionar;
    private JButton btnCancelar;
    private JLabel lblStatus;

    private final List<Guest> guests;

    /**
     * @param owner        
     * @param hotelManager 
     */
    public DlgSelecionarHospede(Window owner, HotelManager hotelManager) {
        super(owner, "Selecionar Hóspede", ModalityType.APPLICATION_MODAL);
        this.guests = hotelManager.getGuests();
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(560, 420));

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);

        JPanel filterPanel = new JPanel(new BorderLayout(6, 0));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));
        filterPanel.add(new JLabel("Filtrar:"), BorderLayout.WEST);
        txtFiltro = new JTextField();
        txtFiltro.setToolTipText("Digite para filtrar por nome ou documento");
        filterPanel.add(txtFiltro, BorderLayout.CENTER);
        mainPanel.add(filterPanel, BorderLayout.NORTH);

        String[] cols = {"Nome", "Documento", "Contato", "Endereço"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableGuests = new JTable(tableModel);
        tableGuests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGuests.setRowHeight(22);
        tableGuests.getTableHeader().setReorderingAllowed(false);
        tableGuests.setFillsViewportHeight(true);

        tableGuests.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableGuests.getColumnModel().getColumn(1).setPreferredWidth(110);
        tableGuests.getColumnModel().getColumn(2).setPreferredWidth(110);
        tableGuests.getColumnModel().getColumn(3).setPreferredWidth(180);

        sorter = new TableRowSorter<>(tableModel);
        tableGuests.setRowSorter(sorter);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Hóspedes Cadastrados",
                TitledBorder.LEFT, TitledBorder.TOP));
        tablePanel.add(new JScrollPane(tableGuests), BorderLayout.CENTER);

        lblStatus = new JLabel(" ");
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.ITALIC, 11f));
        tablePanel.add(lblStatus, BorderLayout.SOUTH);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));

        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setMnemonic('S');
        btnSelecionar.setToolTipText("Confirmar seleção do hóspede (Alt+S)");
        btnSelecionar.setEnabled(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic('C');
        btnCancelar.setToolTipText("Cancelar seleção (Alt+C)");

        btnPanel.add(btnSelecionar);
        btnPanel.add(btnCancelar);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        txtFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { applyFilter(); }
        });

        tableGuests.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                btnSelecionar.setEnabled(tableGuests.getSelectedRow() >= 0);
            }
        });

        tableGuests.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) confirmarSelecao();
            }
        });

        btnSelecionar.addActionListener(e -> confirmarSelecao());
        btnCancelar.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(btnSelecionar);

        pack();
        setSize(580, 420);
        setLocationRelativeTo(getOwner());
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        for (Guest g : guests) {
            tableModel.addRow(new Object[]{
                g.getName(), g.getDocument(), g.getContact(), g.getAddress()
            });
        }
        lblStatus.setText(guests.size() + " hóspede(s) disponível(is).");
        lblStatus.setForeground(new Color(60, 60, 60));
    }

    private void applyFilter() {
        String text = txtFiltro.getText().trim();
        if (text.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1));
        }
        int visible = tableGuests.getRowCount();
        lblStatus.setText(visible + " hóspede(s) encontrado(s).");
    }

    private void confirmarSelecao() {
        int viewRow = tableGuests.getSelectedRow();
        if (viewRow < 0) return;

        int modelRow = tableGuests.convertRowIndexToModel(viewRow);
        String document = (String) tableModel.getValueAt(modelRow, 1);

        guestSelecionado = guests.stream()
                .filter(g -> g.getDocument().equals(document))
                .findFirst()
                .orElse(null);

        dispose();
    }

    public Guest getGuestSelecionado() {
        return guestSelecionado;
    }
}
