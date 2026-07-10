package com.mycompany.main.model.dao;

// Entities
import com.mycompany.main.model.entities.Student;

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

/**
 *
 * @author guilh
 */
public class StudentDAOSQLite implements IDAO<Student>  {
    private Connection connection;

    public StudentDAOSQLite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO student (id, name, sex, age, cpf, registration, entryYear) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, String.valueOf(student.getSex())); 
            stmt.setInt(4, student.getAge());
            stmt.setString(5, student.getCpf());
            stmt.setString(6, student.getRegistration());
            stmt.setInt(7, student.getEntryYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, sex = ?, age = ?, cpf = ?, registration = ?, entryYear = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, String.valueOf(student.getSex()));
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getCpf());
            stmt.setString(5, student.getRegistration());
            stmt.setInt(6, student.getEntryYear());
            stmt.setInt(7, student.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    @Override
    public void delete(int idStudent) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idStudent);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }

    @Override
    public Student findById(int idStudent) {
        String sql = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idStudent);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sex").charAt(0), 
                        rs.getInt("age"),
                        rs.getString("cpf"),
                        rs.getString("registration"),
                        rs.getInt("entryYear")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("sex").charAt(0), 
                    rs.getInt("age"),
                    rs.getString("cpf"),
                    rs.getString("registration"),
                    rs.getInt("entryYear")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        return students;
    }
}
