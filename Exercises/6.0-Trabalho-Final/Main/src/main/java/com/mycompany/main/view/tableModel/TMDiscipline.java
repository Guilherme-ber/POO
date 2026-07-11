package com.mycompany.main.view.tableModel;

// Entities
import com.mycompany.main.model.entities.Discipline;

// Utils
import java.util.List;

// Java Swing
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guilh
 */
public class TMDiscipline extends AbstractTableModel {
    private List<Discipline> list;

    // Columns
    private final int COL_ID = 0;
    private final int COL_NAME = 1;
    private final int COL_SEMESTER = 2;
    private final int COL_TIME = 3;
    private final int COL_TEACHER = 4;
    private final int COL_STUDENTS_LIST = 5;
    
    public TMDiscipline(List<Discipline> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return this.list.size();
    }
    
    @Override
    public int getColumnCount() {
        return 6;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Discipline std = this.list.get(row);
        switch (col) {
            case COL_ID -> {
                return std.getId();
            }
            case COL_NAME -> {
                return std.getName();
            }
            case COL_SEMESTER -> {
                return std.getSemester();
            }
            case COL_TIME -> {
                return std.getTime();
            }
            case COL_TEACHER -> {
                return (std.getTeacher() != null) ? std.getTeacher().getName() : "Sem professor";
            }
            case COL_STUDENTS_LIST -> {

                int qtd = (std.getStudentList() != null) ? std.getStudentList().size() : 0;
                return qtd + " aluno(s)";
            }
            default -> {
            }
        }
        return "-";
    }
    
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case COL_ID -> {
                return "ID";
            }
            case COL_NAME -> {
                return "Nome";
            }
            case COL_SEMESTER -> {
                return "Semestre";
            }
            case COL_TIME -> {
                return "Horário";
            }
            case COL_TEACHER -> {
                return "Professor";
            }
            case COL_STUDENTS_LIST -> {
                return "Lista de estudantes";
            }
            default -> {
            }
        }
        return "";
    }
    
    public void updateList(List<Discipline> newList) {
        this.list = newList;
        this.fireTableDataChanged();
    }
}