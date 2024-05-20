package com.example.tooltracker.database;

import com.example.tooltracker.model.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDAO {

    public static final String ADD_PART_NUMBER = "INSERT INTO PART (part_number) VALUES (?)";
    public static final String GET_ALL_PART_NUMBERS = "SELECT * FROM PART";
    public static final String CHECK_FOR_EXISTING_PART_NUM = "SELECT COUNT(*) FROM part WHERE part_number = ?";



    public void addPart(String partNumber) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PART_NUMBER)) {
            preparedStatement.setString(1, partNumber);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Part> getAllParts() {
        List<Part> partNumbers = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PART_NUMBERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Part part1 = new Part();

                part1.setPart_number(resultSet.getString("part_number"));

                partNumbers.add(part1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partNumbers;
    }


    public boolean isPartNumExists(String partNumber) {


        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_FOR_EXISTING_PART_NUM)) {
            preparedStatement.setString(1, partNumber);

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
