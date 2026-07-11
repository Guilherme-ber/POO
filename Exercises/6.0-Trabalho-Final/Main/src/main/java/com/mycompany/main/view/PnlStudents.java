package com.mycompany.main.view;

import com.mycompany.main.model.entities.Student;
import com.mycompany.main.view.tableModel.TMStudent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author guilh
 */
public class PnlStudents extends javax.swing.JPanel {
// Componentes da Tabela (Lado Esquerdo - Master)
    private JTable grdStudents;
    private TMStudent tmStudent;
    private JTextField txtSearch;
    private JLabel lblTotalCount;

    // Componentes do Formulário (Lado Direito - Detail)
    private JTextField txtName;
    private JTextField txtCpf;
    private JTextField txtRegistration;
    private JTextField txtEntryYear;
    private JTextField txtAge;
    private JComboBox<String> cbSex;

    // Botões de Ação
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnCancel;

    public PnlStudents() {
        initComponents(); // Mantém a chamada do NetBeans intacta
        initCustomLayout(); // Nosso layout customizado e limpo
        loadInitialData();  // Carrega os dados na tabela
    }

    private void initCustomLayout() {
        // 1. Configuração do Painel Principal
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(15, 15, 15, 15)); // Padding externo da tela

        // ====================================================================
        // LADO ESQUERDO: TABELA E PESQUISA (MASTER)
        // ====================================================================
        JPanel pnlMaster = new JPanel(new BorderLayout(10, 10));

        // Topo da Esquerda: Barra de Pesquisa
        JPanel pnlSearch = new JPanel(new BorderLayout(5, 0));
        txtSearch = new JTextField();
        txtSearch.putClientProperty("JTextField.placeholderText", "🔍 Pesquisar aluno por nome ou matrícula...");
        txtSearch.putClientProperty("JTextField.showClearButton", true); // Botão 'X' nativo do FlatLaf
        txtSearch.setPreferredSize(new Dimension(0, 35));
        pnlSearch.add(txtSearch, BorderLayout.CENTER);

        // Centro da Esquerda: Tabela de Alunos
        grdStudents = new JTable();
        grdStudents.setRowHeight(28); // Linhas mais altas para um visual moderno
        grdStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        grdStudents.getTableHeader().setReorderingAllowed(false);
        
        // Evento: Quando clicar em uma linha da tabela, preenche o formulário
        grdStudents.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromSelectedRow();
            }
        });

        JScrollPane scrollTable = new JScrollPane(grdStudents);
        scrollTable.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        // Rodapé da Esquerda: Contador de registros
        lblTotalCount = new JLabel("Total: 0 aluno(s) registrado(s).");
        lblTotalCount.setFont(new Font("Segoe UI", Font.BOLD, 12));

        pnlMaster.add(pnlSearch, BorderLayout.NORTH);
        pnlMaster.add(scrollTable, BorderLayout.CENTER);
        pnlMaster.add(lblTotalCount, BorderLayout.SOUTH);

        // ====================================================================
        // LADO DIREITO: FORMULÁRIO DE CADASTRO (DETAIL)
        // ====================================================================
        JPanel pnlDetail = new JPanel(new BorderLayout(10, 10));
        pnlDetail.setPreferredSize(new Dimension(340, 0)); // Largura fixa de 340px para o formulário
        pnlDetail.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)), 
                " DETALHES DO REGISTRO ", 
                0, 0, new Font("Segoe UI", Font.BOLD, 13)
        ));

        // Campos do Formulário usando GridBagLayout para alinhamento perfeito
        JPanel pnlFormFields = new JPanel(new GridBagLayout());
        pnlFormFields.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0); // Espaçamento vertical entre os campos
        gbc.weightx = 1.0;

        // Instanciando os campos
        txtName = new JTextField();
        txtCpf = new JTextField();
        txtRegistration = new JTextField();
        txtEntryYear = new JTextField();
        txtAge = new JTextField();
        cbSex = new JComboBox<>(new String[]{"M", "F", "Outro"});

        // Placeholders elegantes via FlatLaf
        txtName.putClientProperty("JTextField.placeholderText", "Ex: João da Silva");
        txtCpf.putClientProperty("JTextField.placeholderText", "000.000.000-00");
        txtRegistration.putClientProperty("JTextField.placeholderText", "2026001");
        txtEntryYear.putClientProperty("JTextField.placeholderText", "2026");
        txtAge.putClientProperty("JTextField.placeholderText", "Ex: 18");

        // Adicionando os campos na coluna direita (empilhados)
        addFormField(pnlFormFields, "Nome Completo:", txtName, gbc, 0);
        addFormField(pnlFormFields, "CPF:", txtCpf, gbc, 2);
        addFormField(pnlFormFields, "Matrícula:", txtRegistration, gbc, 4);
        addFormField(pnlFormFields, "Ano de Entrada:", txtEntryYear, gbc, 6);
        addFormField(pnlFormFields, "Sexo:", cbSex, gbc, 8);
        addFormField(pnlFormFields, "Idade:", txtAge, gbc, 10);

        // Painel de Botões (Rodapé do Formulário)
        JPanel pnlButtons = new JPanel(new GridLayout(2, 2, 8, 8)); // Grid 2x2 com espaçamento de 8px
        pnlButtons.setBorder(new EmptyBorder(10, 15, 15, 15));

        btnNew = new JButton("+ Novo");
        btnSave = new JButton("💾 Salvar");
        btnDelete = new JButton("🗑️ Excluir");
        btnCancel = new JButton("❌ Cancelar");

        // Estilizando botão Salvar como destaque (FlatLaf)
        btnSave.putClientProperty("JButton.buttonType", "roundRect");
        btnSave.setBackground(new Color(40, 130, 220));
        btnSave.setForeground(Color.WHITE);
        btnDelete.setForeground(new Color(200, 50, 50)); // Texto vermelho no excluir

        pnlButtons.add(btnNew);
        pnlButtons.add(btnSave);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnCancel);

        // Montando o Lado Direito
        pnlDetail.add(pnlFormFields, BorderLayout.NORTH);
        pnlDetail.add(pnlButtons, BorderLayout.SOUTH);

        // ====================================================================
        // ADICIONANDO AS DUAS COLUNAS NA TELA PRINCIPAL
        // ====================================================================
        this.add(pnlMaster, BorderLayout.CENTER);
        this.add(pnlDetail, BorderLayout.EAST);

        // Configurando eventos dos botões
        setupActions();
    }

    // Método auxiliar para adicionar labels e inputs de forma limpa
    private void addFormField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(label, gbc);

        gbc.gridy = row + 1;
        field.setPreferredSize(new Dimension(0, 30)); // Altura padrão para todos os inputs
        panel.add(field, gbc);
    }

    private void setupActions() {
        btnNew.addActionListener(e -> clearForm());
        
        btnCancel.addActionListener(e -> {
            clearForm();
            grdStudents.clearSelection();
        });

        // Aqui você chamará o seu StudentController no futuro!
        btnSave.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidade de salvar será ligada ao Controller!");
        });

        btnDelete.addActionListener(e -> {
            if (grdStudents.getSelectedRow() != -1) {
                JOptionPane.showMessageDialog(this, "Funcionalidade de exclusão será ligada ao Controller!");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void fillFormFromSelectedRow() {
        int row = grdStudents.getSelectedRow();
        if (row != -1) {
            // Pega os dados da tabela via TableModel (Colunas na ordem que você definiu no TMStudent)
            txtName.setText(grdStudents.getValueAt(row, 1).toString());
            cbSex.setSelectedItem(grdStudents.getValueAt(row, 2).toString());
            txtAge.setText(grdStudents.getValueAt(row, 3).toString());
            txtCpf.setText(grdStudents.getValueAt(row, 4).toString());
            txtRegistration.setText(grdStudents.getValueAt(row, 5).toString());
            txtEntryYear.setText(grdStudents.getValueAt(row, 6).toString());
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtCpf.setText("");
        txtRegistration.setText("");
        txtEntryYear.setText("");
        txtAge.setText("");
        cbSex.setSelectedIndex(0);
        txtName.requestFocus(); // Joga o cursor piscando para o campo Nome
    }

    private void loadInitialData() {
        // Inicializa a tabela com uma lista vazia (ou dados de teste)
        List<Student> listaInicial = new ArrayList<>();
        
        // Exemplo de dado mockado só para você ver a tabela preenchida na tela:
        listaInicial.add(new Student(1, "Guilherme Silva", 'M', 20, "111.222.333-44", "2026001", 2026));
        listaInicial.add(new Student(2, "Ana Souza", 'F', 19, "555.666.777-88", "2026002", 2026));

        tmStudent = new TMStudent(listaInicial);
        grdStudents.setModel(tmStudent);
        
        // Atualiza o contador do rodapé
        lblTotalCount.setText("Total: " + listaInicial.size() + " aluno(s) registrado(s).");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Alunos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 335, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 266, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
