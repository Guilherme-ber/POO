package com.mycompany.main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

// Entities & Models
import com.mycompany.main.model.entities.*;
import com.mycompany.main.view.tableModel.TMDiscipline;
import com.mycompany.main.controller.*;

/**
 * Disciplines panel
 * @author guilh
 */
public class PnlDisciplines extends javax.swing.JPanel {
    private boolean editing = false;
    private DisciplineController disciplineController;
    private TeacherController teacherController;
    private StudentController studentController;
    private List<Discipline> disciplineList;
    private JTable grdDisciplines;
    private TMDiscipline tmDiscipline;
    private JTextField txtSearch;
    private JLabel lblTotalCount;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSemester;
    private JTextField txtTime;
    private JComboBox<Teacher> cbTeacher;
    private JList<Student> listStudents;
    private DefaultListModel<Student> listModelStudents;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnCancel;

    public PnlDisciplines() {
        this.disciplineList = new ArrayList<>();
        initComponentsCustom();
        setupListeners();
        setFormEnabled(false);
    }

    /**
     *
     * @param discCtrl
     * @param teachCtrl
     * @param studCtrl
     */
    public void initControllers(DisciplineController discCtrl, TeacherController teachCtrl, StudentController studCtrl) {
        this.disciplineController = discCtrl;
        this.teacherController = teachCtrl;
        this.studentController = studCtrl;
        updateTableAndSelectors();
    }

    private void initComponentsCustom() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel pnlMaster = new JPanel(new BorderLayout(10, 10));
        
        JPanel pnlSearch = new JPanel(new BorderLayout(5, 0));
        pnlSearch.add(new JLabel("Pesquisar Disciplina: "), BorderLayout.WEST);
        txtSearch = new JTextField();
        pnlSearch.add(txtSearch, BorderLayout.CENTER);
        pnlMaster.add(pnlSearch, BorderLayout.NORTH);

        tmDiscipline = new TMDiscipline(disciplineList);
        grdDisciplines = new JTable(tmDiscipline);
        grdDisciplines.setRowHeight(26);
        grdDisciplines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollTable = new JScrollPane(grdDisciplines);
        pnlMaster.add(scrollTable, BorderLayout.CENTER);

        lblTotalCount = new JLabel("Total de disciplinas: 0");
        lblTotalCount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        pnlMaster.add(lblTotalCount, BorderLayout.SOUTH);

        JPanel pnlDetail = new JPanel(new BorderLayout(10, 10));
        pnlDetail.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Dados da Disciplina", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        pnlDetail.setPreferredSize(new Dimension(380, 0));

        JPanel pnlFormArea = new JPanel(new BorderLayout(5, 10));
        pnlFormArea.setBorder(new EmptyBorder(10, 15, 5, 15));

        JPanel pnlFields = new JPanel(new GridLayout(5, 2, 8, 8));
        
        pnlFields.add(new JLabel("ID (Auto ou Manual):"));
        txtId = new JTextField();
        pnlFields.add(txtId);

        pnlFields.add(new JLabel("Nome da Disciplina:"));
        txtName = new JTextField();
        pnlFields.add(txtName);

        pnlFields.add(new JLabel("Semestre (ex: 1, 2):"));
        txtSemester = new JTextField();
        pnlFields.add(txtSemester);

        pnlFields.add(new JLabel("Horário (ex: 08:00):"));
        txtTime = new JTextField();
        pnlFields.add(txtTime);

        pnlFields.add(new JLabel("Professor Responsável:"));
        cbTeacher = new JComboBox<>();
        cbTeacher.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Teacher t) {
                    setText(t.getId() + " - " + t.getName());
                } else {
                    setText("Selecione um professor...");
                }
                return this;
            }
        });
        pnlFields.add(cbTeacher);

        pnlFormArea.add(pnlFields, BorderLayout.NORTH);

        listModelStudents = new DefaultListModel<>();
        listStudents = new JList<>(listModelStudents);
        listStudents.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listStudents.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Student s) {
                    setText("🎓 " + s.getName() + " (Mat: " + s.getRegistration() + ")");
                }
                return this;
            }
        });

        JScrollPane scrollStudents = new JScrollPane(listStudents);
        scrollStudents.setBorder(BorderFactory.createTitledBorder("Alunos Matriculados (Segure Ctrl para selecionar vários)"));
        pnlFormArea.add(scrollStudents, BorderLayout.CENTER);

        pnlDetail.add(pnlFormArea, BorderLayout.CENTER);

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
        grdDisciplines.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && grdDisciplines.getSelectedRow() != -1) {
                fillFormFromSelectedRow();
                setFormEnabled(true);
                txtId.setEnabled(false);
                editing = true;
            }
        });

        btnNew.addActionListener(e -> {
            clearForm();
            updateTableAndSelectors();
            setFormEnabled(true);
            txtId.setEnabled(true);
            txtId.setText(String.valueOf(generateNextId()));
            txtName.requestFocus();
            editing = false;
            grdDisciplines.clearSelection();
        });

        btnSave.addActionListener(e -> {
            if (!validateFields()) return;
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                int semester = Integer.parseInt(txtSemester.getText().trim());
                String time = txtTime.getText().trim();
                Teacher selectedTeacher = (Teacher) cbTeacher.getSelectedItem();
                List<Student> selectedStudents = listStudents.getSelectedValuesList();

                if (disciplineController != null) {
                    if (editing) {
                        disciplineController.updateDiscipline(id, name, semester, time, selectedTeacher, selectedStudents);
                        JOptionPane.showMessageDialog(this, "Disciplina atualizada com sucesso!");
                    } else {
                        if (idExists(id)) {
                            JOptionPane.showMessageDialog(this, "O ID " + id + " já está em uso!", "Erro de ID", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        disciplineController.addDiscipline(id, name, semester, time, selectedTeacher, selectedStudents);
                        JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso!");
                    }
                    updateTableAndSelectors();
                    clearForm();
                    setFormEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique se ID e Semestre contêm apenas números válidos.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = grdDisciplines.getSelectedRow();
            if (selectedRow == -1) return;
            if (JOptionPane.showConfirmDialog(this, "Deseja excluir esta disciplina?", "Excluir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int id = (int) tmDiscipline.getValueAt(selectedRow, 0);
                disciplineController.removeDiscipline(id);
                updateTableAndSelectors();
                clearForm();
                setFormEnabled(false);
            }
        });

        btnCancel.addActionListener(e -> {
            clearForm();
            setFormEnabled(false);
            grdDisciplines.clearSelection();
            editing = false;
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { filterTable(txtSearch.getText().trim()); }
        });
    }

    private int generateNextId() {
        int maxId = 0;
        for (Discipline d : disciplineList) {
            if (d.getId() > maxId) maxId = d.getId();
        }
        return maxId + 1;
    }

    private boolean idExists(int id) {
        for (Discipline d : disciplineList) {
            if (d.getId() == id) return true;
        }
        return false;
    }

    private void fillFormFromSelectedRow() {
        int row = grdDisciplines.getSelectedRow();
        if (row != -1) {
            int id = (int) tmDiscipline.getValueAt(row, 0);
            Discipline d = disciplineController.findDisciplineById(id);
            
            if (d != null) {
                txtId.setText(String.valueOf(d.getId()));
                txtName.setText(d.getName());
                txtSemester.setText(String.valueOf(d.getSemester()));
                txtTime.setText(d.getTime());
                
                if (d.getTeacher() != null) {
                    for (int i = 0; i < cbTeacher.getItemCount(); i++) {
                        Teacher t = cbTeacher.getItemAt(i);
                        if (t != null && t.getId() == d.getTeacher().getId()) {
                            cbTeacher.setSelectedIndex(i);
                            break;
                        }
                    }
                }

                listStudents.clearSelection();
                List<Integer> indicesToSelect = new ArrayList<>();
                for (Student enrolled : d.getStudentList()) {
                    for (int i = 0; i < listModelStudents.getSize(); i++) {
                        if (listModelStudents.getElementAt(i).getId() == enrolled.getId()) {
                            indicesToSelect.add(i);
                            break;
                        }
                    }
                }
                listStudents.setSelectedIndices(indicesToSelect.stream().mapToInt(i -> i).toArray());
            }
        }
    }

    private boolean validateFields() {
        if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() ||
            txtSemester.getText().trim().isEmpty() || txtTime.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos básicos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cbTeacher.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um professor para a disciplina!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (listStudents.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos um aluno para a disciplina!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public void updateTableAndSelectors() {
        if (disciplineController != null && tmDiscipline != null) {
            this.disciplineList = disciplineController.getAllDisciplines();
            tmDiscipline.updateList(this.disciplineList);
            lblTotalCount.setText("Total de disciplinas: " + disciplineList.size());
        }

        if (teacherController != null) {
            cbTeacher.removeAllItems();
            List<Teacher> teachers = teacherController.getAllTeachers();
            for (Teacher t : teachers) {
                cbTeacher.addItem(t);
            }
        }

        if (studentController != null) {
            listModelStudents.clear();
            List<Student> students = studentController.getAllStudents();
            for (Student s : students) {
                listModelStudents.addElement(s);
            }
        }
    }

    private void filterTable(String query) {
        if (query.isEmpty()) {
            tmDiscipline.updateList(disciplineList);
        } else {
            List<Discipline> filtered = new ArrayList<>();
            for (Discipline d : disciplineList) {
                if (d.getName().toLowerCase().contains(query.toLowerCase()) || d.getTime().contains(query)) {
                    filtered.add(d);
                }
            }
            tmDiscipline.updateList(filtered);
        }
    }

    private void clearForm() {
        txtId.setText(""); txtName.setText(""); txtSemester.setText("");
        txtTime.setText(""); cbTeacher.setSelectedIndex(-1); listStudents.clearSelection();
    }

    private void setFormEnabled(boolean enabled) {
        txtId.setEnabled(enabled); txtName.setEnabled(enabled); txtSemester.setEnabled(enabled);
        txtTime.setEnabled(enabled); cbTeacher.setEnabled(enabled); listStudents.setEnabled(enabled);
        btnSave.setEnabled(enabled); btnCancel.setEnabled(enabled);
        btnDelete.setEnabled(grdDisciplines.getSelectedRow() != -1);
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
        jLabel2.setText("Disciplinas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 305, Short.MAX_VALUE))
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
