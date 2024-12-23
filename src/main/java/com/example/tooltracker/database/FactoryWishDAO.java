package com.example.tooltracker.database;

import com.example.tooltracker.model.FactoryWish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactoryWishDAO {

    private static final String UPDATE_DELIVERED_WISH = "UPDATE factorywish SET  status=?, producent=? WHERE id=?";
    private static final String UPDATE_ORDERED_WISH = "UPDATE factorywish SET  status=? WHERE id=?";


    public List<FactoryWish> getAllWishes() {
        String sql = "SELECT * FROM factorywish ";
        List<FactoryWish> factoryWishes = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FactoryWish factoryWish = new FactoryWish(
                        "",

                        rs.getString("type"),
                        rs.getString("info"),
                        rs.getString("auction"),
                        rs.getString("producent"),
                        rs.getString("status"),
                        rs.getString("machine"),
                        rs.getInt("id")
                );
                factoryWishes.add(factoryWish);
            }
        } catch (SQLException e) {

        }
        return factoryWishes;
    }

    public int addWish(FactoryWish factoryWish) {
        String sql = "INSERT INTO factorywish (name, type, auction, info, producent, status, machine) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1; // Initialize the ID with an invalid value to check later

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, "");
            pstmt.setString(2, factoryWish.getType());
            pstmt.setString(3, factoryWish.getAuction());
            pstmt.setString(4, factoryWish.getInfo());
            pstmt.setString(5, factoryWish.getProducent());
            pstmt.setString(6, factoryWish.getStatus());
            pstmt.setString(7, factoryWish.getMachine());

            pstmt.executeUpdate();

            // Retrieve the generated key (ID)
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1); // Retrieve the generated ID
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(generatedId);
        return generatedId; // Return the generated ID

    }



    public void updateWish(String producent, int id) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DELIVERED_WISH)) {
            preparedStatement.setString(1, "zrealizowane");
            preparedStatement.setString(2, producent);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOrderdedWaitingWish(int id, String status) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERED_WISH)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
