package com.example.tooltracker.database;

import com.example.tooltracker.model.FactoryWishAction;
import com.example.tooltracker.model.InsertAction;
import com.example.tooltracker.model.LoggedUser;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WishActionDAO {

    private static final String INSERT_ACTION = "INSERT INTO wishAction (wishid, actiontime, user, wishaction, cost, where_ordered, producent, qty ) VALUES (?, ?, ?,?,?,?,?,?)";
    private static final String SELECT_ALL_ACTION = "SELECT * FROM wishaction";
    private static final String SELECT_ALL_ACTION_WITH_INDEX = "SELECT * FROM wishaction WHERE wishid=?";
    private String username = LoggedUser.getUser().getUsername();








    public void addAction(FactoryWishAction wishAction) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACTION)) {
            preparedStatement.setInt(1, wishAction.getWishId());
            LocalDateTime now = LocalDateTime.now();
            // MUSIMY UZYC TIMESTAMP BO TO JEST ODPOWIEDNIK DATETIME W SQL
            Timestamp timestamp = Timestamp.valueOf(now);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, wishAction.getWishAction());
            preparedStatement.setBigDecimal(5, wishAction.getPrice());
            preparedStatement.setString(6, wishAction.getProducent());
            preparedStatement.setString(7, wishAction.getWhereOrdered());
            preparedStatement.setInt(8, wishAction.getQty());


            preparedStatement.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public List<FactoryWishAction> getAllActions() {
        List<FactoryWishAction> wishActions = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION);
             ResultSet resultSet = preparedStatement.executeQuery()) {




            while (resultSet.next()) {

                LocalDateTime actionTime = resultSet.getTimestamp("actiontime").toLocalDateTime(); // Konwersja z Timestamp do LocalDateTime
                String userr = resultSet.getString("user");
                String actionMade = resultSet.getString("wishaction");
                BigDecimal cost = resultSet.getBigDecimal("cost");
                String whereordered = resultSet.getString("where_ordered");
                String producent = resultSet.getString("producent");
                int qty = resultSet.getInt("qty");
                int wishid = resultSet.getInt("wishid");







                wishActions.add(new FactoryWishAction(wishid, actionMade, producent, userr, qty, whereordered, actionTime, cost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishActions;
    }




    public List<FactoryWishAction> getAllActionsWithWishId(int wishId) {
        List<FactoryWishAction> actions = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTION_WITH_INDEX)) {
            preparedStatement.setInt(1, wishId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    LocalDateTime actionTime = resultSet.getTimestamp("actiontime").toLocalDateTime(); // Konwersja z Timestamp do LocalDateTime
                    String userr = resultSet.getString("user");
                    String actionMade = resultSet.getString("wishaction");
                    BigDecimal cost = resultSet.getBigDecimal("cost");
                    String whereordered = resultSet.getString("where_ordered");
                    String producent = resultSet.getString("producent");
                    int qty = resultSet.getInt("qty");
                    int wishid = resultSet.getInt("wishid");







                    actions.add(new FactoryWishAction(wishid, actionMade, producent,
                            userr, qty, whereordered, actionTime, cost));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actions;
    }
}
