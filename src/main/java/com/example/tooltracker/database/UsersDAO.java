package com.example.tooltracker.database;

import com.example.tooltracker.model.Users;
import com.example.tooltracker.model.tools.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {


    public Users getUserByUsername(String username) {
        Users user = null;
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                user = new Users(username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
