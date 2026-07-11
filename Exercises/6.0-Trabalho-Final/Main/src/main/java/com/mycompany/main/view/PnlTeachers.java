package com.mycompany.main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

// Entities & Models
import com.mycompany.main.model.entities.Teacher;
import com.mycompany.main.view.tableModel.TMTeacher;
import com.mycompany.main.controller.TeacherController;

/**
 * Teachers panel
 * @author guilh
 */
public class PnlTeachers extends javax.swing.JPanel {
    private boolean editing = false;
    private TeacherController controller;
    private List<Teacher> teacherList;
    private JTable grdTeachers;
    private TMTeacher tmTeacher;
    private JTextField txtSearch;
    private JLabel lblTotalCount;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtCpf;
    private JComboBox<String> cbSex;
    private JTextField txtAge;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnCancel;

    public PnlTeachers() {
        this.teacherList = new ArrayList<>();
        initComponentsCustom();
        setupListeners();
        setFormEnabled(false);
    }

    public void initController(TeacherController controller) {
        this.controller = controller;
        updateTable();
    }

    private void initComponentsCustom() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel pnlMaster = new JPanel(new BorderLayout(10, 10));
        
        JPanel pnlSearch = new JPanel(new BorderLayout(5, 0));
        pnlSearch.add(new JLabel("Pesquisar Professor: "), BorderLayout.WEST);
        txtSearch = new JTextField();
        pnlSearch.add(txtSearch, BorderLayout.CENTER);
        pnlMaster.add(pnlSearch, BorderLayout.NORTH);

        tmTeacher = new TMTeacher(teacherList);
        grdTeachers = new JTable(tmTeacher);
        grdTeachers.setRowHeight(26);
        grdTeachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollTable = new JScrollPane(grdTeachers);
        pnlMaster.add(scrollTable, BorderLayout.CENTER);

        lblTotalCount = new JLabel("Total de professores: 0");
        lblTotalCount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        pnlMaster.add(lblTotalCount, BorderLayout.SOUTH);

        JPanel pnlDetail = new JPanel(new BorderLayout(10, 10));
        pnlDetail.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Dados do Professor", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        pnlDetail.setPreferredSize(new Dimension(360, 0));

        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 8, 12));
        pnlForm.setBorder(new EmptyBorder(15, 15, 5, 15));

        pnlForm.add(new JLabel("ID (Auto ou Manual):"));
        txtId = new JTextField();
        pnlForm.add(txtId);

        pnlForm.add(new JLabel("Nome Completo:"));
        txtName = new JTextField();
        pnlForm.add(txtName);

        pnlForm.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        pnlForm.add(txtCpf);

        pnlForm.add(new JLabel("Sexo:"));
        cbSex = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        pnlForm.add(cbSex);

        pnlForm.add(new JLabel("Idade:"));
        txtAge = new JTextField();
        pnlForm.add(txtAge);

        pnlDetail.add(pnlForm, BorderLayout.NORTH);

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
        grdTeachers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && grdTeachers.getSelectedRow() != -1) {
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
            grdTeachers.clearSelection();
        });

        btnSave.addActionListener(e -> {
            if (!validateFields()) return;
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                String cpf = txtCpf.getText().trim();
                char sex = cbSex.getSelectedIndex() == 0 ? 'M' : 'F';
                int age = Integer.parseInt(txtAge.getText().trim());

                if (controller != null) {
                    if (editing) {
                        controller.updateTeacher(id, name, sex, age, cpf);
                        JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!");
                    } else {
                        if (idExists(id)) {
                            JOptionPane.showMessageDialog(this, "O ID " + id + " já está em uso!", "Erro de ID", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        controller.addTeacher(id, name, sex, age, cpf);
                        JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!");
                    }
                    updateTable();
                    clearForm();
                    setFormEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique se ID e Idade contêm apenas números válidos.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = grdTeachers.getSelectedRow();
            if (selectedRow == -1) return;
            if (JOptionPane.showConfirmDialog(this, "Deseja excluir este professor?", "Excluir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int id = (int) tmTeacher.getValueAt(selectedRow, 0);
                controller.removeTeacher(id);
                updateTable();
                clearForm();
                setFormEnabled(false);
            }
        });

        btnCancel.addActionListener(e -> {
            clearForm();
            setFormEnabled(false);
            grdTeachers.clearSelection();
            editing = false;
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { filterTable(txtSearch.getText().trim()); }
        });
    }

    private int generateNextId() {
        int maxId = 0;
        for (Teacher t : teacherList) {
            if (t.getId() > maxId) maxId = t.getId();
        }
        return maxId + 1;
    }

    private boolean idExists(int id) {
        for (Teacher t : teacherList) {
            if (t.getId() == id) return true;
        }
        return false;
    }

    private void fillFormFromSelectedRow() {
        int row = grdTeachers.getSelectedRow();
        if (row != -1) {
            txtId.setText(String.valueOf(tmTeacher.getValueAt(row, 0)));
            txtName.setText(String.valueOf(tmTeacher.getValueAt(row, 1)));
            char sex = (char) tmTeacher.getValueAt(row, 2);
            cbSex.setSelectedIndex((sex == 'M' || sex == 'm') ? 0 : 1);
            txtAge.setText(String.valueOf(tmTeacher.getValueAt(row, 3)));
            txtCpf.setText(String.valueOf(tmTeacher.getValueAt(row, 4)));
        }
    }

    private boolean validateFields() {
        if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() ||
            txtCpf.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void updateTable() {
        if (controller != null && tmTeacher != null) {
            this.teacherList = controller.getAllTeachers();
            tmTeacher.updateList(this.teacherList);
            lblTotalCount.setText("Total de professores: " + teacherList.size());
        }
    }

    private void filterTable(String query) {
        if (query.isEmpty()) {
            tmTeacher.updateList(teacherList);
        } else {
            List<Teacher> filtered = new ArrayList<>();
            for (Teacher t : teacherList) {
                if (t.getName().toLowerCase().contains(query.toLowerCase()) || t.getCpf().contains(query)) {
                    filtered.add(t);
                }
            }
            tmTeacher.updateList(filtered);
        }
    }

    private void clearForm() {
        txtId.setText(""); txtName.setText(""); txtCpf.setText("");
        txtAge.setText(""); cbSex.setSelectedIndex(0);
    }

    private void setFormEnabled(boolean enabled) {
        txtId.setEnabled(enabled); txtName.setEnabled(enabled); txtCpf.setEnabled(enabled);
        cbSex.setEnabled(enabled); txtAge.setEnabled(enabled); btnSave.setEnabled(enabled);
        btnCancel.setEnabled(enabled); btnDelete.setEnabled(grdTeachers.getSelectedRow() != -1);
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
        jLabel2 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Professores");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 301, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 266, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
