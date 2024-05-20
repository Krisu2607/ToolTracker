package com.example.tooltracker.database;

import com.example.tooltracker.model.ToolAction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActionDAO {


    private static final String INSERT_ACTION = "INSERT INTO ToolAction (tIndex, tAction, actionDatetime ) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_ACTION = "SELECT * FROM ToolAction";
    private static final String SELECT_ALL_ACTION_WITH_INDEX = "SELECT * FROM ToolAction WHERE tIndex=?";








    public void addAction(ToolAction toolaction) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACTION)) {
            preparedStatement.setString(1, toolaction.gettIndex());
            preparedStatement.setString(2, toolaction.gettAction());
            LocalDateTime now = LocalDateTime.now();
            // MUSIMY UZYC TIMESTAMP BO TO JEST ODPOWIEDNIK DATETIME W SQL
            Timestamp timestamp = Timestamp.valueOf(now);
            preparedStatement.setTimestamp(3, timestamp);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public List<ToolAction> getAllActions() {
        List<ToolAction> toolActions = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION);
             ResultSet resultSet = preparedStatement.executeQuery()) {




            while (resultSet.next()) {
                ToolAction action = new ToolAction();

               String index = resultSet.getString("tIndex");
                String actionMade = resultSet.getString("tAction");
               LocalDateTime actionTime = resultSet.getTimestamp("actionDateTime").toLocalDateTime(); // Konwersja z Timestamp do LocalDateTime
                String user = "Jurek";



                toolActions.add(new ToolAction(index, actionMade, actionTime, user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolActions;
    }




        public List<ToolAction> getAllActionsWithIndex(String tIndex) {
            List<ToolAction> actions = new ArrayList<>();

            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION_WITH_INDEX)) {
                preparedStatement.setString(1, tIndex);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ToolAction action = new ToolAction();

                        action.settIndex(resultSet.getString("tIndex"));
                        action.settAction(resultSet.getString("tAction"));
                        action.setActionDateTime(resultSet.getTimestamp("actionDateTime").toLocalDateTime()); // Konwersja z Timestamp do LocalDateTime

                        actions.add(action);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return actions;
        }





}
