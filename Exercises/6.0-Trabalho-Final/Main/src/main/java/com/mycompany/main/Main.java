package com.mycompany.main;

// SQLite
import com.mycompany.main.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

// Controller
import com.mycompany.main.controller.*;

// View
import com.mycompany.main.view.FrHome;

// Daos
import com.mycompany.main.model.dao.*;

public class Main {
    public static void main(String[] args) {
        // Save with SQLite
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
        
        StudentDAOSQLite sdtDao = new StudentDAOSQLite(connection);
        TeacherDAOSQLite tchDao = new TeacherDAOSQLite(connection);
        DisciplineDAOSQLite discDao = new DisciplineDAOSQLite(connection);

        // Save with JSON
        /* String studentPath = "data/alunos.json";
        String teacherPath = "data/professores.json";
        String disciplinePath = "data/disciplinas.json"; */
        
        /* StudentDAOJson sdtDao = new StudentDAOJson(studentPath); 
        TeacherDAOJson tchDao = new TeacherDAOJson(teacherPath);
        DisciplineDAOJson discDao = new DisciplineDAOJson(disciplinePath); */
        
        // Controllers
        StudentController sdtController = new StudentController(sdtDao);
        TeacherController tchController = new TeacherController(tchDao);
        DisciplineController discController = new DisciplineController(discDao);
        
        
        FrHome view = new FrHome(sdtController, tchController, discController);
        view.setVisible(true);
    }
}