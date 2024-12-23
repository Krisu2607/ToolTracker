package com.example.tooltracker.database;

import com.example.tooltracker.model.InsertAction;
import com.example.tooltracker.model.LoggedUser;
import com.example.tooltracker.model.ToolAction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InsertActionDAO {

    private static final String INSERT_ACTION = "INSERT INTO InsertAction (insert_indeks, action, actionDate, user ) VALUES (?, ?, ?,?)";
    private static final String SELECT_ALL_ACTION = "SELECT * FROM InsertAction";
    private static final String SELECT_ALL_ACTION_WITH_INDEX = "SELECT * FROM InsertAction WHERE insert_Indeks=?";
    private String username = LoggedUser.getUser().getUsername();








    public void addAction(InsertAction insertAction) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACTION)) {
            preparedStatement.setString(1, insertAction.getInsertIndex());
            preparedStatement.setString(2, insertAction.getInsertAction());
            LocalDateTime now = LocalDateTime.now();
            // MUSIMY UZYC TIMESTAMP BO TO JEST ODPOWIEDNIK DATETIME W SQL
            Timestamp timestamp = Timestamp.valueOf(now);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setString(4, username);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public List<InsertAction> getAllActions() {
        List<InsertAction> toolActions = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION);
             ResultSet resultSet = preparedStatement.executeQuery()) {




            while (resultSet.next()) {
                InsertAction action = new InsertAction();

                String index = resultSet.getString("insert_indeks");
                String actionMade = resultSet.getString("action");
                LocalDateTime actionTime = resultSet.getTimestamp("actionDate").toLocalDateTime(); // Konwersja z Timestamp do LocalDateTime
                String userr = resultSet.getString("user");





                toolActions.add(new InsertAction(index, actionMade, actionTime, userr));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolActions;
    }




    public List<InsertAction> getAllActionsWithIndex(String tIndex) {
        List<InsertAction> actions = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION_WITH_INDEX)) {
            preparedStatement.setString(1, tIndex);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    InsertAction action = new InsertAction();

                    action.setInsertIndex(resultSet.getString("tIndex"));
                    action.setInsertAction(resultSet.getString("tAction"));
                    action.setActionDateTime(resultSet.getTimestamp("actionDateTime").toLocalDateTime()); // Konwersja z Timestamp do LocalDateTime
                    action.setUser(username);

                    actions.add(action);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actions;
    }


}
