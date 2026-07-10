package com.mycompany.main.connection;

// Sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author guilh
 */
public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:sqlite:escola.db");
            initTables(connection);
        }
        return connection;
    }

    private static void initTables(Connection conn) throws SQLException {
        String[] sqls = {
            """
            CREATE TABLE IF NOT EXISTS teacher (
                id INTEGER PRIMARY KEY,
                name TEXT, sex TEXT, age INTEGER, cpf TEXT
            )""",
            """
            CREATE TABLE IF NOT EXISTS student (
                id INTEGER PRIMARY KEY,
                name TEXT, sex TEXT, age INTEGER, cpf TEXT,
                registration TEXT, entryYear INTEGER
            )""",
            """
            CREATE TABLE IF NOT EXISTS discipline (
                id INTEGER PRIMARY KEY,
                name TEXT, semester INTEGER, time TEXT,
                id_teacher INTEGER,
                FOREIGN KEY(id_teacher) REFERENCES teacher(id)
            )""",
            """
            CREATE TABLE IF NOT EXISTS discipline_student (
                id_discipline INTEGER,
                id_student INTEGER,
                FOREIGN KEY(id_discipline) REFERENCES discipline(id),
                FOREIGN KEY(id_student) REFERENCES student(id)
            )"""
        };

        try (Statement stmt = conn.createStatement()) {
            for (String sql : sqls) stmt.execute(sql);
        }
    }
}