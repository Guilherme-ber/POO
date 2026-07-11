package com.mycompany.main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

// Entities/Models
import com.mycompany.main.model.entities.Student;
import com.mycompany.main.view.tableModel.TMStudent;
import com.mycompany.main.controller.StudentController;

/**
 * Students panel
 * @author guilh
 */
public class PnlStudents extends javax.swing.JPanel {
    private boolean editing = false;
    private StudentController controller;
    private List<Student> studentList;
    private JTable grdStudents;
    private TMStudent tmStudent;
    private JTextField txtSearch;
    private JLabel lblTotalCount;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtCpf;
    private JComboBox<String> cbSex;
    private JTextField txtAge;
    private JTextField txtRegistration;
    private JTextField txtEntryYear;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnCancel;

    public PnlStudents() {
        this.studentList = new ArrayList<>();
        initComponentsCustom();
        setupListeners();
        setFormEnabled(false);
    }

    public void initController(StudentController controller) {
        this.controller = controller;
        updateTable();
    }

    private void initComponentsCustom() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel pnlMaster = new JPanel(new BorderLayout(10, 10));
        Dimension inputSize = new Dimension(150, 5);
        
        JPanel pnlSearch = new JPanel(new BorderLayout(5, 0));
        pnlSearch.add(new JLabel("Pesquisar Aluno: "), BorderLayout.WEST);
        txtSearch = new JTextField();
        pnlSearch.add(txtSearch, BorderLayout.CENTER);
        pnlMaster.add(pnlSearch, BorderLayout.NORTH);

        tmStudent = new TMStudent(studentList);
        grdStudents = new JTable(tmStudent);
        grdStudents.setRowHeight(26);
        grdStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollTable = new JScrollPane(grdStudents);
        pnlMaster.add(scrollTable, BorderLayout.CENTER);

        lblTotalCount = new JLabel("Total de alunos: 0");
        lblTotalCount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        pnlMaster.add(lblTotalCount, BorderLayout.SOUTH);

        JPanel pnlDetail = new JPanel(new BorderLayout(10, 10));
        pnlDetail.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Dados do Aluno", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        pnlDetail.setPreferredSize(new Dimension(360, 0));

        JPanel pnlForm = new JPanel(new GridLayout(7, 2, 8, 12));
        pnlForm.setBorder(new EmptyBorder(15, 15, 5, 15));

        pnlForm.add(new JLabel("ID (Auto ou Manual):"));
        txtId = new JTextField();
        txtId.setPreferredSize(inputSize);
        pnlForm.add(txtId);

        pnlForm.add(new JLabel("Nome Completo:"));
        txtName = new JTextField();
        txtName.setPreferredSize(inputSize);
        pnlForm.add(txtName);

        pnlForm.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        txtCpf.setPreferredSize(inputSize);
        pnlForm.add(txtCpf);

        pnlForm.add(new JLabel("Sexo:"));
        cbSex = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        cbSex.setPreferredSize(inputSize);
        pnlForm.add(cbSex);

        pnlForm.add(new JLabel("Idade:"));
        txtAge = new JTextField();
        txtAge.setPreferredSize(inputSize);
        pnlForm.add(txtAge);

        pnlForm.add(new JLabel("Matrícula:"));
        txtRegistration = new JTextField();
        txtRegistration.setPreferredSize(inputSize);
        pnlForm.add(txtRegistration);

        pnlForm.add(new JLabel("Ano de Entrada:"));
        txtEntryYear = new JTextField();
        txtEntryYear.setPreferredSize(inputSize);
        pnlForm.add(txtEntryYear);

        pnlDetail.add(pnlForm, BorderLayout.CENTER);

        JPanel pnlActions = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlActions.setBorder(new EmptyBorder(10, 15, 15, 15));
        pnlActions.setPreferredSize(new Dimension(0, 95));

        btnNew = new JButton("+ Novo");
        btnSave = new JButton("💾 Salvar");
        btnDelete = new JButton("🗑️ Excluir");
        btnCancel = new JButton("❌ Cancelar");

        pnlActions.add(btnNew);
        pnlActions.add(btnSave);
        pnlActions.add(btnDelete);
        pnlActions.add(btnCancel);

        pnlDetail.add(pnlActions, BorderLayout.SOUTH);

        this.add(pnlMaster, BorderLayout.CENTER);
        this.add(pnlDetail, BorderLayout.EAST);
    }

    private void setupListeners() {
        grdStudents.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && grdStudents.getSelectedRow() != -1) {
                fillFormFromSelectedRow();
                setFormEnabled(true);
                txtId.setEnabled(false);
                editing = true;
            }
        });

        btnNew.addActionListener(e -> {
            clearForm();
            setFormEnabled(true);
            txtId.setEnabled(true);
            txtId.setText(String.valueOf(generateNextId()));
            txtName.requestFocus();
            editing = false;
            grdStudents.clearSelection();
        });

        btnSave.addActionListener(e -> {
            if (!validateFields()) return;
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                String cpf = txtCpf.getText().trim();
                char sex = cbSex.getSelectedIndex() == 0 ? 'M' : 'F';
                int age = Integer.parseInt(txtAge.getText().trim());
                String reg = txtRegistration.getText().trim();
                int year = Integer.parseInt(txtEntryYear.getText().trim());

                if (controller != null) {
                    if (editing) {
                        controller.updateStudent(id, name, sex, age, cpf, reg, year);
                        JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
                    } else {
                        if (idExists(id)) {
                            JOptionPane.showMessageDialog(this, "O ID " + id + " já está em uso!", "Erro de ID", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        controller.addStudent(id, name, sex, age, cpf, reg, year);
                        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                    }
                    updateTable();
                    clearForm();
                    setFormEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique se ID, Idade e Ano são apenas números válidos.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = grdStudents.getSelectedRow();
            if (selectedRow == -1) return;
            if (JOptionPane.showConfirmDialog(this, "Deseja excluir este aluno?", "Excluir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int id = (int) tmStudent.getValueAt(selectedRow, 0);
                controller.removeStudent(id);
                updateTable();
                clearForm();
                setFormEnabled(false);
            }
        });

        btnCancel.addActionListener(e -> {
            clearForm();
            setFormEnabled(false);
            grdStudents.clearSelection();
            editing = false;
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { filterTable(txtSearch.getText().trim()); }
        });
    }

    private int generateNextId() {
        int maxId = 0;
        for (Student s : studentList) {
            if (s.getId() > maxId) maxId = s.getId();
        }
        return maxId + 1;
    }

    private boolean idExists(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) return true;
        }
        return false;
    }

    private void fillFormFromSelectedRow() {
        int row = grdStudents.getSelectedRow();
        if (row != -1) {
            txtId.setText(String.valueOf(tmStudent.getValueAt(row, 0)));
            txtName.setText(String.valueOf(tmStudent.getValueAt(row, 1)));
            char sex = (char) tmStudent.getValueAt(row, 2);
            cbSex.setSelectedIndex((sex == 'M' || sex == 'm') ? 0 : 1);
            txtAge.setText(String.valueOf(tmStudent.getValueAt(row, 3)));
            txtCpf.setText(String.valueOf(tmStudent.getValueAt(row, 4)));
            txtRegistration.setText(String.valueOf(tmStudent.getValueAt(row, 5)));
            txtEntryYear.setText(String.valueOf(tmStudent.getValueAt(row, 6)));
        }
    }

    private boolean validateFields() {
        if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() ||
            txtCpf.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty() ||
            txtRegistration.getText().trim().isEmpty() || txtEntryYear.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void updateTable() {
        if (controller != null && tmStudent != null) {
            this.studentList = controller.getAllStudents();
            tmStudent.updateList(this.studentList);
            lblTotalCount.setText("Total de alunos: " + studentList.size());
        }
    }

    private void filterTable(String query) {
        if (query.isEmpty()) {
            tmStudent.updateList(studentList);
        } else {
            List<Student> filtered = new ArrayList<>();
            for (Student s : studentList) {
                if (s.getName().toLowerCase().contains(query.toLowerCase()) || s.getCpf().contains(query) || s.getRegistration().contains(query)) {
                    filtered.add(s);
                }
            }
            tmStudent.updateList(filtered);
        }
    }

    private void clearForm() {
        txtId.setText(""); txtName.setText(""); txtCpf.setText("");
        txtAge.setText(""); txtRegistration.setText(""); txtEntryYear.setText("");
        cbSex.setSelectedIndex(0);
    }

    private void setFormEnabled(boolean enabled) {
        txtId.setEnabled(enabled); txtName.setEnabled(enabled); txtCpf.setEnabled(enabled);
        cbSex.setEnabled(enabled); txtAge.setEnabled(enabled); txtRegistration.setEnabled(enabled);
        txtEntryYear.setEnabled(enabled); btnSave.setEnabled(enabled); btnCancel.setEnabled(enabled);
        btnDelete.setEnabled(grdStudents.getSelectedRow() != -1);
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
