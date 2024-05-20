package com.example.tooltracker.database;

import com.example.tooltracker.model.ToolUsage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToolUsageDAO {
    private static final String INSERT_USED_INSERT = "INSERT INTO Insert_Usage  (part_number, order_number, insert_index, insert_qty, parts_qty ) VALUES (?, ?, ?,?,?)";
    private static final String SELECT_ALL_USED_INSERT = "SELECT * FROM Insert_Usage";
    public static final  String UPDATE_USED_INSERT_QTY = "UPDATE Insert_Usage SET insert_qty=? WHERE insert_index=? AND order_number=?";


    public void addUsedInsert(ToolUsage toolUsage) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USED_INSERT)) {
            preparedStatement.setString(1, toolUsage.getPartNumber());
            preparedStatement.setString(2, toolUsage.getOrderNumber());
            preparedStatement.setString(3, toolUsage.getInsertIndex());
            preparedStatement.setInt(4, toolUsage.getInsertQty());
            preparedStatement.setInt(5, toolUsage.getPartsQty());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ToolUsage> getAllUsedInserts() {
        List<ToolUsage> toolUsages = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USED_INSERT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ToolUsage toolUsage = new ToolUsage();
                toolUsage.setPartNumber(resultSet.getString("part_number"));
                toolUsage.setOrderNumber(resultSet.getString("order_number"));
                toolUsage.setInsertIndex(resultSet.getString("insert_index"));
                toolUsage.setInsertQty(resultSet.getInt("insert_qty"));
                toolUsage.setPartsQty(resultSet.getInt("parts_qty"));


                toolUsages.add(toolUsage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolUsages;
    }

    public void updateUsedInsertQty(ToolUsage toolUsage) {


        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USED_INSERT_QTY)) {
            preparedStatement.setInt(1, toolUsage.getInsertQty());
            preparedStatement.setString(2, toolUsage.getInsertIndex());
            preparedStatement.setString(3, toolUsage.getOrderNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }











}
