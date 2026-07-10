package com.mycompany.main.model.dao;

// Entities
import com.mycompany.main.model.entities.Teacher;

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

public class TeacherDAOSQLite implements IDAO<Teacher> {
    private Connection connection;

    public TeacherDAOSQLite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Teacher teacher) {
        String sql = "INSERT INTO teacher (id, name, sex, age, cpf) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, teacher.getId());
            stmt.setString(2, teacher.getName());
            stmt.setString(3, String.valueOf(teacher.getSex())); 
            stmt.setInt(4, teacher.getAge());
            stmt.setString(5, teacher.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar professor: " + e.getMessage());
        }
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE teacher SET name = ?, sex = ?, age = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.setString(2, String.valueOf(teacher.getSex()));
            stmt.setInt(3, teacher.getAge());
            stmt.setString(4, teacher.getCpf());
            stmt.setInt(5, teacher.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    @Override
    public void delete(int idTeacher) {
        String sql = "DELETE FROM teacher WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTeacher);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
        }
    }

    @Override
    public Teacher findById(int idTeacher) {
        String sql = "SELECT * FROM teacher WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTeacher);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sex").charAt(0), 
                        rs.getInt("age"),
                        rs.getString("cpf")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT * FROM teacher";
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                teachers.add(new Teacher(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("sex").charAt(0), 
                    rs.getInt("age"),
                    rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }
        return teachers;
    }
}