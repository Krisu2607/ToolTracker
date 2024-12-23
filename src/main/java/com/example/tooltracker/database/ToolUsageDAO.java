package com.example.tooltracker.database;

import com.example.tooltracker.model.ToolUsage;
import com.example.tooltracker.model.tools.Tool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToolUsageDAO {

    private static final String SELECT_INFO_BY_ORDER_NUM = "SELECT info FROM used_tools WHERE order_num = ?";
    private static final String UPDATE_ORDER_INFO = "UPDATE used_tools SET info = ? WHERE order_num = ?";

    private static final String SELECT_FROM_ORDER_NUM = "SELECT * FROM USED_TOOLS WHERE ORDER_NUM =?";
    private static final String SELECT_ALL = "SELECT * FROM USED_TOOLS";
    private static final String SELECT_FROM_PART_NUM = "SELECT * FROM USED_TOOLS WHERE PART_NUM =?";
    private static final String UPDATE_TOOL_INSERTS_BY_ORDER = "UPDATE used_tools SET tool_inserts = tool_inserts + 1 WHERE order_num = ?";
    private static final String INSERT_TOOL_USAGE = "INSERT INTO used_tools (order_num, part_num, parts_qty, machine) VALUES (?, ?, ?,?)";

    private static final String INCREMENT_TOOL_USAGE = "UPDATE used_tools SET %s = %s + 1 WHERE ORDER_NUM = ?";
    private static final String ADD_EXPENSE_TO_ORDER = "UPDATE used_tools SET total_expenses = total_expenses + ? WHERE order_num = ?";



    public void addExpenseToOrder(String orderNum, BigDecimal amount) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_EXPENSE_TO_ORDER)) {
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setString(2, orderNum);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<ToolUsage> getAllUsedToolsFromPartNum(String partNumber) {
        List<ToolUsage> toolUsages = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PART_NUM)){
            preparedStatement.setString(1, partNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);




            while (resultSet.next()) {
                String orderNum = resultSet.getString("order_num");
                String part_num = resultSet.getString("part_num");
                int partsQty = resultSet.getInt("parts_qty");
                int toolInserts = resultSet.getInt("tool_inserts");
                int tap = resultSet.getInt("tap");
                int em = resultSet.getInt("em");
                int bm = resultSet.getInt("bm");
                int sm = resultSet.getInt("sm");
                int threaddie = resultSet.getInt("threaddie");
                int cm = resultSet.getInt("cm");
                int reamers = resultSet.getInt("reamer");
                int drillHss = resultSet.getInt("drill_hss");
                int drillVhm = resultSet.getInt("drill_vhm");
                int drillBlades = resultSet.getInt("drill_blades");
                int faceGroove = resultSet.getInt("face_groove");
                int spotdrill = resultSet.getInt("spotdrill");
                int latheOd = resultSet.getInt("lathe_od");
                int latheId = resultSet.getInt("lathe_id");
                String info = resultSet.getString("info");
                String machine = resultSet.getString("machine");


                ToolUsage toolUsage = new ToolUsage(part_num, orderNum, toolInserts,
                        partsQty, tap, em, sm, threaddie,
                        cm, latheId, latheOd, reamers, drillHss,
                        drillVhm, drillBlades, faceGroove, spotdrill, bm, machine, info);

                toolUsages.add(toolUsage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolUsages;
    }







    public List<ToolUsage> getAllUsedTools() {
        List<ToolUsage> toolUsages = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();




            while (resultSet.next()) {
                String orderNum = resultSet.getString("order_num");
                String part_num = resultSet.getString("part_num");
                int partsQty = resultSet.getInt("parts_qty");
                int toolInserts = resultSet.getInt("tool_inserts");
                int tap = resultSet.getInt("tap");
                int em = resultSet.getInt("em");
                int bm = resultSet.getInt("bm");
                int sm = resultSet.getInt("sm");
                int threaddie = resultSet.getInt("threaddie");
                int cm = resultSet.getInt("cm");
                int reamers = resultSet.getInt("reamer");
                int drillHss = resultSet.getInt("drill_hss");
                int drillVhm = resultSet.getInt("drill_vhm");
                int drillBlades = resultSet.getInt("drill_blades");
                int faceGroove = resultSet.getInt("face_groove");
                int spotdrill = resultSet.getInt("spotdrill");
                int latheOd = resultSet.getInt("lathe_od");
                int latheId = resultSet.getInt("lathe_id");
                String info = resultSet.getString("info");
                String machine = resultSet.getString("machine");


                ToolUsage toolUsage = new ToolUsage(part_num, orderNum, toolInserts,
                        partsQty, tap, em, sm, threaddie,
                        cm, latheId, latheOd, reamers, drillHss,
                        drillVhm, drillBlades, faceGroove, spotdrill, bm, machine, info);

                toolUsages.add(toolUsage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolUsages;
    }







    public void incrementToolUsageColumn(String columnName, String orderNum) throws SQLException {
        String query = String.format(INCREMENT_TOOL_USAGE, columnName, columnName);
        System.out.println(query);

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, orderNum);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isOrderNumPresent(String orderNumber) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ORDER_NUM)) {
            preparedStatement.setString(1, orderNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Optionally, handle exception differently
        }
    }

    public void incrementToolInsertsByOrder(String orderNumber) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOL_INSERTS_BY_ORDER)) {
            preparedStatement.setString(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToolUsage(String orderNumber, String partNumber, int partsQty, String machineName) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOOL_USAGE)) {
            preparedStatement.setString(1, orderNumber);
            preparedStatement.setString(2, partNumber);
            preparedStatement.setInt(3, partsQty);
            preparedStatement.setString(4, machineName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderInfo(String orderNum, String message) {
        try (Connection connection = DatabaseUtil.getConnection()) {

            String currentInfo = null;
            try (PreparedStatement selectStatement = connection.prepareStatement(SELECT_INFO_BY_ORDER_NUM)) {
                selectStatement.setString(1, orderNum);
                ResultSet resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    currentInfo = resultSet.getString("info");
                }
            }

            // Zaktualizuj wartość info, dodając nową wiadomość
            String updatedInfo = currentInfo == null || currentInfo.isEmpty()
                    ? message
                    : currentInfo + " - " + message;

            // Zaktualizuj rekord w bazie danych
            try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_ORDER_INFO)) {
                System.out.println(updatedInfo);
                System.out.println(orderNum);
                updateStatement.setString(1, updatedInfo);
                updateStatement.setString(2, orderNum);
                updateStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
