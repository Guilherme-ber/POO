package com.mycompany.hotelreservationsystem.views;

import com.mycompany.hotelreservationsystem.managers.HotelManager;
import com.mycompany.hotelreservationsystem.models.Room;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

/**
 * Diálogo modal para seleção de um quarto cadastrado.
 *
 * <p>Uso típico:
 * <pre>
 *   DlgSelecionarQuarto dlg = new DlgSelecionarQuarto(owner, hotelManager);
 *   dlg.setVisible(true);
 *   Room escolhido = dlg.getRoomSelecionado(); // null se cancelado
 * </pre>
 */
public class DlgSelecionarQuarto extends JDialog {

    private Room roomSelecionado = null;

    private JTextField txtFiltro;
    private JTable tableRooms;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    private JButton btnSelecionar;
    private JButton btnCancelar;
    private JLabel lblStatus;

    private final List<Room> rooms;

    /**
     * @param owner        janela pai
     * @param hotelManager gerenciador compartilhado do hotel
     */
    public DlgSelecionarQuarto(Window owner, HotelManager hotelManager) {
        super(owner, "Selecionar Quarto", ModalityType.APPLICATION_MODAL);
        this.rooms = hotelManager.getRooms();
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(480, 380));

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(mainPanel);

        // ── Filtro de busca ───────────────────────────────────────────────
        JPanel filterPanel = new JPanel(new BorderLayout(6, 0));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));
        filterPanel.add(new JLabel("Filtrar:"), BorderLayout.WEST);
        txtFiltro = new JTextField();
        txtFiltro.setToolTipText("Digite para filtrar por número ou tipo");
        filterPanel.add(txtFiltro, BorderLayout.CENTER);
        mainPanel.add(filterPanel, BorderLayout.NORTH);

        // ── Tabela de quartos ─────────────────────────────────────────────
        String[] cols = {"Nº Quarto", "Tipo", "Preço / Noite (R$)"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableRooms = new JTable(tableModel);
        tableRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableRooms.setRowHeight(22);
        tableRooms.getTableHeader().setReorderingAllowed(false);
        tableRooms.setFillsViewportHeight(true);

        tableRooms.getColumnModel().getColumn(0).setPreferredWidth(90);
        tableRooms.getColumnModel().getColumn(1).setPreferredWidth(160);
        tableRooms.getColumnModel().getColumn(2).setPreferredWidth(160);

        sorter = new TableRowSorter<>(tableModel);
        tableRooms.setRowSorter(sorter);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Quartos Disponíveis",
                TitledBorder.LEFT, TitledBorder.TOP));
        tablePanel.add(new JScrollPane(tableRooms), BorderLayout.CENTER);

        lblStatus = new JLabel(" ");
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.ITALIC, 11f));
        tablePanel.add(lblStatus, BorderLayout.SOUTH);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // ── Botões ────────────────────────────────────────────────────────
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));

        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setMnemonic('S');
        btnSelecionar.setToolTipText("Confirmar seleção do quarto (Alt+S)");
        btnSelecionar.setEnabled(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic('C');
        btnCancelar.setToolTipText("Cancelar seleção (Alt+C)");

        btnPanel.add(btnSelecionar);
        btnPanel.add(btnCancelar);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // ── Listeners ─────────────────────────────────────────────────────
        txtFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { applyFilter(); }
        });

        tableRooms.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                btnSelecionar.setEnabled(tableRooms.getSelectedRow() >= 0);
            }
        });

        // Duplo-clique seleciona diretamente
        tableRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) confirmarSelecao();
            }
        });

        btnSelecionar.addActionListener(e -> confirmarSelecao());
        btnCancelar.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(btnSelecionar);

        pack();
        setSize(500, 380);
        setLocationRelativeTo(getOwner());
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        for (Room r : rooms) {
            tableModel.addRow(new Object[]{
                r.getRoomNumber(),
                r.getType(),
                String.format("%.2f", r.getPricePerNight())
            });
        }
        lblStatus.setText(rooms.size() + " quarto(s) disponível(is).");
        lblStatus.setForeground(new Color(60, 60, 60));
    }

    private void applyFilter() {
        String text = txtFiltro.getText().trim();
        if (text.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Filtra nas colunas Nº (0) e Tipo (1)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1));
        }
        lblStatus.setText(tableRooms.getRowCount() + " quarto(s) encontrado(s).");
    }

    private void confirmarSelecao() {
        int viewRow = tableRooms.getSelectedRow();
        if (viewRow < 0) return;

        int modelRow = tableRooms.convertRowIndexToModel(viewRow);
        int roomNumber = (int) tableModel.getValueAt(modelRow, 0);

        roomSelecionado = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);

        dispose();
    }

    /**
     * Retorna o quarto selecionado, ou {@code null} se o diálogo foi cancelado.
     */
    public Room getRoomSelecionado() {
        return roomSelecionado;
    }
}
