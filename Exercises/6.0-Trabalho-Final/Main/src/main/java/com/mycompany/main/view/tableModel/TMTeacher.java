package com.mycompany.main.view.tableModel;

// Entities
import com.mycompany.main.model.entities.Teacher;

// Utils
import java.util.List;

// Java Swing
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guilh
 */
public class TMTeacher extends AbstractTableModel {
    private List<Teacher> list;

    // Columns
    private final int COL_ID = 0;
    private final int COL_NAME = 1;
    private final int COL_SEX = 2;
    private final int COL_AGE = 3;
    private final int COL_CPF = 4;
    
    public TMTeacher(List<Teacher> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return this.list.size();
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Teacher std = this.list.get(row);
        switch (col) {
            case COL_ID -> {
                return std.getId();
            }
            case COL_NAME -> {
                return std.getName();
            }
            case COL_SEX -> {
                return std.getSex();
            }
            case COL_AGE -> {
                return std.getAge();
            }
            case COL_CPF -> {
                return std.getCpf();
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
            case COL_SEX -> {
                return "Sexo";
            }
            case COL_AGE -> {
                return "Idade";
            }
            case COL_CPF -> {
                return "CPF";
            }
            default -> {
            }
        }
        return "";
    }
    
    public void updateList(List<Teacher> newList) {
        this.list = newList;
        this.fireTableDataChanged();
    }
}
