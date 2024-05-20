package com.example.tooltracker.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseUtil {


        private static final String URL = "jdbc:mysql://localhost:3306/tools";
        private static final String USER = "root";
        private static final String PASSWORD = "admin";

        // Metoda do nawiązywania połączenia z bazą danych
        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace(); // Tutaj możesz obsłużyć błąd w odpowiedni sposób
                throw new RuntimeException("Błąd podczas nawiązywania połączenia z bazą danych.");
            }
        }

        // Metoda do zamykania połączenia z bazą danych
        public static void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Tutaj możesz obsłużyć błąd w odpowiedni sposób
                }
            }
        }
    }

