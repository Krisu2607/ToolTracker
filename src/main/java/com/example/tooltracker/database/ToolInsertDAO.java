package com.example.tooltracker.database;

import com.example.tooltracker.model.ToolInsert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolInsertDAO {


    private static final String INSERT_TOOLINSERT = "INSERT INTO toolInserts (insertName, insertIndex, insertType, toolsMatch, additionalInfo, insertQty) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_TOOLSINSERT = "SELECT * FROM toolInserts";
    private static final String SELECT_ALL_INSERTS_FROM_PART = " SELECT toolInserts.* FROM toolinserts JOIN part_inserts ON toolinserts.insertIndex = part_inserts.insertIndex WHERE part_inserts.part_number = ?";

    private static final String UPDATE_TOOLINSERT = "UPDATE toolInserts SET insertName=?, insertIndex=?, insertType=?, toolsMatch=?, additionalInfo=?, insertQty=? WHERE insertIndex=?";
    private static final String UPDATE_TOOLINSERTQTY = "UPDATE toolInserts SET  insertQty=? WHERE insertIndex=?";
    private static final String DELETE_TOOLINSERT = "DELETE FROM toolInserts WHERE insertIndex=?";
    public static final String INSERT_TOOLINSERT_TO_PART = "INSERT INTO part_inserts (part_number, insertIndex) VALUES (?,?)";
    public static final String CHECK_FOR_EXISTING_TOOLINSERT_PART = "SELECT COUNT(*) FROM part_inserts WHERE part_number = ? AND insertIndex = ?";



    public void addInserts(ToolInsert toolInsert) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOOLINSERT)) {
            preparedStatement.setString(1, toolInsert.getInsertName());
            preparedStatement.setString(2, toolInsert.getInsertIndex());
            preparedStatement.setString(3, toolInsert.getInsertType());
            preparedStatement.setString(4, toolInsert.getToolsMatch());
            preparedStatement.setString(5, toolInsert.getAdditionalInfo());
            preparedStatement.setInt(6, toolInsert.getInsertQty());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addInsertToPart(String partNumber, String insertIndex) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOOLINSERT_TO_PART)) {
            preparedStatement.setString(1, partNumber);
            preparedStatement.setString(2, insertIndex);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<ToolInsert> getAllToolInserts() {
        List<ToolInsert> toolInserts = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TOOLSINSERT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ToolInsert toolInsert = new ToolInsert();
                toolInsert.setInsertName(resultSet.getString("insertName"));
                toolInsert.setInsertIndex(resultSet.getString("insertIndex"));
                toolInsert.setInsertType(resultSet.getString("insertType"));
                toolInsert.setToolsMatch(resultSet.getString("toolsMatch"));
                toolInsert.setAdditionalInfo(resultSet.getString("additionalInfo"));
                toolInsert.setInsertQty(resultSet.getInt("insertQty"));

                toolInserts.add(toolInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolInserts;
    }


    public List<ToolInsert> getAllToolInsertsFromPart(String partNumber) {
        List<ToolInsert> toolInserts = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSERTS_FROM_PART)){
            preparedStatement.setString(1, partNumber);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ToolInsert toolInsert = new ToolInsert();
                toolInsert.setInsertName(resultSet.getString("insertName"));
                toolInsert.setInsertIndex(resultSet.getString("insertIndex"));
                toolInsert.setInsertType(resultSet.getString("insertType"));
                toolInsert.setToolsMatch(resultSet.getString("toolsMatch"));
                toolInsert.setAdditionalInfo(resultSet.getString("additionalInfo"));
                toolInsert.setInsertQty(resultSet.getInt("insertQty"));

                toolInserts.add(toolInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolInserts;
    }


    public void updateToolInsert(ToolInsert toolInsert, String oldInsertIndex) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOLINSERT)) {
            preparedStatement.setString(1, toolInsert.getInsertName());
            preparedStatement.setString(2, toolInsert.getInsertIndex());
            preparedStatement.setString(3, toolInsert.getInsertType());
            preparedStatement.setString(4, toolInsert.getToolsMatch());
            preparedStatement.setString(5, toolInsert.getAdditionalInfo());
            preparedStatement.setInt(6, toolInsert.getInsertQty());
            preparedStatement.setString(7, oldInsertIndex);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateToolInsertQty(ToolInsert toolInsert) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOLINSERTQTY)) {
            preparedStatement.setInt(1, toolInsert.getInsertQty());
            preparedStatement.setString(2, toolInsert.getInsertIndex());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteToolInsert(String toolInsertIndex) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOOLINSERT)) {
            preparedStatement.setString(1, toolInsertIndex);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isToolInsertExists(String partNumber, String indexName) {


        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_FOR_EXISTING_TOOLINSERT_PART)) {
            preparedStatement.setString(1, partNumber);
            preparedStatement.setString(2, indexName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}