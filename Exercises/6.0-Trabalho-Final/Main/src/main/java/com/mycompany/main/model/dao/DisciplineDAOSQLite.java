package com.mycompany.main.model.dao;

// Entities
import com.mycompany.main.model.entities.*;

// Interfaces
import com.mycompany.main.model.dao.interfaces.IDAO;

// Sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Utils
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAOSQLite implements IDAO<Discipline>  {
    private Connection connection;
    private TeacherDAOSQLite teacherDAO;
    private StudentDAOSQLite studentDAO;

    public DisciplineDAOSQLite(Connection connection) {
        this.connection = connection;
        this.teacherDAO = new TeacherDAOSQLite(connection);
        this.studentDAO = new StudentDAOSQLite(connection);
    }

    // Helpers
    private List<Student> getStudentsList(int idDiscipline) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id_student FROM discipline_student WHERE id_discipline = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDiscipline);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idStudent = rs.getInt("id_student");
                    Student student = studentDAO.findById(idStudent);
                    if (student != null) {
                        students.add(student);
                    }
                }
            }
        }
        return students;
    }
    
    private void saveStudentsList(int idDiscipline, List<Student> students) throws SQLException {
        if (students == null || students.isEmpty()) return;
        
        String sql = "INSERT INTO discipline_student (id_discipline, id_student) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Student student : students) {
                stmt.setInt(1, idDiscipline);
                stmt.setInt(2, student.getId());
                stmt.executeUpdate();
            }
        }
    }

    private void deleteStudentsList(int idDiscipline) throws SQLException {
        String sql = "DELETE FROM discipline_student WHERE id_discipline = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDiscipline);
            stmt.executeUpdate();
        }
    }
    
    // Common methods
    @Override
    public void save(Discipline discipline) {
        String sql = "INSERT INTO discipline (id, name, semester, time, id_teacher) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, discipline.getId());
            stmt.setString(2, discipline.getName());
            stmt.setInt(3, discipline.getSemester()); 
            stmt.setString(4, discipline.getTime());
            
            if (discipline.getTeacher() != null) {
                stmt.setInt(5, discipline.getTeacher().getId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            stmt.executeUpdate();
            saveStudentsList(discipline.getId(), discipline.getStudentList()); 
        } catch (SQLException e) {
            System.out.println("Erro ao salvar disciplina: " + e.getMessage());
        }
    }   
    
    @Override
    public void update(Discipline discipline) {
        String sql = "UPDATE discipline SET name = ?, semester = ?, time = ?, id_teacher = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, discipline.getName());
            stmt.setInt(2, discipline.getSemester());
            stmt.setString(3, discipline.getTime());
            
            if (discipline.getTeacher() != null) {
                stmt.setInt(4, discipline.getTeacher().getId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            stmt.setInt(5, discipline.getId());
            
            stmt.executeUpdate();
            
            deleteStudentsList(discipline.getId());
            saveStudentsList(discipline.getId(), discipline.getStudentList());
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    @Override
    public void delete(int idDiscipline) {
        try {
            deleteStudentsList(idDiscipline);
            
            String sql = "DELETE FROM discipline WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idDiscipline);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar disciplina: " + e.getMessage());
        }
    }

    @Override
    public Discipline findById(int idDiscipline) {
        String sql = "SELECT * FROM discipline WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDiscipline);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idTeacher = rs.getInt("id_teacher");
                    Teacher teacher = teacherDAO.findById(idTeacher); 
                    List<Student> students = getStudentsList(idDiscipline);

                    return new Discipline(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("semester"), 
                        rs.getString("time"),
                        teacher,
                        students
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplina: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Discipline> findAll() {
        String sql = "SELECT * FROM discipline";
        List<Discipline> disciplines = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idTeacher = rs.getInt("id_teacher");
                Teacher teacher = teacherDAO.findById(idTeacher); 

                int disciplineId = rs.getInt("id");
                List<Student> students = getStudentsList(disciplineId);
                
                disciplines.add(new Discipline(
                    disciplineId,
                    rs.getString("name"),
                    rs.getInt("semester"), 
                    rs.getString("time"),
                    teacher,
                    students
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar disciplinas: " + e.getMessage());
        }
        return disciplines;
    }
}