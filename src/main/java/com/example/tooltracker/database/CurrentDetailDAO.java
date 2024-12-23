package com.example.tooltracker.database;

import com.example.tooltracker.model.ToolInsert;
import com.example.tooltracker.model.tools.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrentDetailDAO {

    private static final String UPDATE_ORDER_INFO = "UPDATE current_detail SET  order_num=?, part_num=?, parts_qty=? WHERE machine_name=?";
    private static final String SELECT_FROM_MACHINE = "SELECT * FROM CURRENT_DETAIL WHERE machine_name =?";


    public void updateCurrentDetail(CurrentDetail currentDetail) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_INFO)) {
            preparedStatement.setString(1, currentDetail.getOrder_num());
            preparedStatement.setString(2, currentDetail.getPart_num());
            preparedStatement.setInt(3, currentDetail.getParts_qty());
            preparedStatement.setString(4, currentDetail.getMachine_name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CurrentDetail getCurrentDetail(String machineName) {
            CurrentDetail currentDetail = new CurrentDetail("", "", "", 0);
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_MACHINE)){
            preparedStatement.setString(1, machineName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                currentDetail.setOrder_num(resultSet.getString("order_num"));
                currentDetail.setPart_num(resultSet.getString("part_num"));
                currentDetail.setMachine_name(resultSet.getString("machine_name"));
                currentDetail.setParts_qty(resultSet.getInt("parts_qty"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentDetail;
    }






}
