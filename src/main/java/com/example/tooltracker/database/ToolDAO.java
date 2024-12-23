package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.DrillHSS;
import com.example.tooltracker.model.tools.Tool;
import com.example.tooltracker.model.tools.Tool1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ToolDAO {
    private static final String INSERT_TOOL = "INSERT INTO tools (toolName, toolIndex, toolQty, toolType, toolDiameter, toolInfo, toolStatus, insertMatching ) VALUES (?, ?, ?, ?, ?, ? ,?,?)";
    private static final String SELECT_ALL_TOOLS = "SELECT * FROM tools";
    private static final String UPDATE_TOOL = "UPDATE tools SET toolName=?, toolIndex=?, toolQty=?,  toolType=?, toolDiameter=?, toolInfo=?, toolStatus=? WHERE toolIndex=?";
    private static final String UPDATE_TOOLSTATUS = "UPDATE tools SET  toolStatus=? WHERE toolIndex=?";
    private static final String UPDATE_TOOLINFO = "UPDATE tools SET  toolInfo=? WHERE toolIndex=?";

    private static final String[] TABLES = {"shellmill", "drillhss", "drillvhm", "reamer", "idturning", "odturning", "tapsk", "tappr", "threaddie", "chamfer", "drillblade", "spotdrill"};
    private static final String SELECT_TOOL_BY_INDEX = "SELECT * FROM %s WHERE toolIndex=?";
    private static final String UPDATE_TOOLSTATUSS = "UPDATE %s SET toolStatus=? WHERE toolIndex=?";
    private static final String SELECT_ALL_TOOLS_FROM_PART = " SELECT tools.* FROM tools JOIN part_tools ON tools.toolIndex = part_tools.toolIndex WHERE part_tools.part_number = ?";

    private static final String DELETE_TOOL = "DELETE FROM tools WHERE toolIndex=?";







    public void addTool(Tool tool) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOOL)) {
            preparedStatement.setString(1, tool.getToolName());
            preparedStatement.setString(2, tool.getToolIndex());
            preparedStatement.setInt(3, tool.getToolQty());
            preparedStatement.setString(4, tool.getToolType());
            preparedStatement.setDouble(5, tool.getToolDiameter());
            preparedStatement.setString(6, tool.getToolInfo());
            preparedStatement.setString(7, tool.getToolStatus());
            preparedStatement.setString(8, tool.getInsertMatching());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tool> getAllTools() {
        List<Tool> tools = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TOOLS);
             ResultSet resultSet = preparedStatement.executeQuery()) {




            while (resultSet.next()) {
                Tool tool = new Tool();
                String L1 = String.valueOf(Double.valueOf(resultSet.getDouble("L1")));
                String L2 = String.valueOf(Double.valueOf(resultSet.getDouble("L2")));
                String D1 = String.valueOf(Double.valueOf(resultSet.getDouble("D1")));
                String D2 = String.valueOf(Double.valueOf(resultSet.getDouble("D2")));
                String toolSize = L1+"/" + L2 + "/" + D1 + "/" + D2;
                tool.setToolName(resultSet.getString("toolName"));
                tool.setToolIndex(resultSet.getString("toolIndex"));
                tool.setToolQty(resultSet.getInt("toolQty"));
                tool.setToolType(resultSet.getString("toolType"));
                tool.setToolDiameter(resultSet.getDouble("toolDiameter"));
                tool.setToolInfo(resultSet.getString("toolInfo"));
                tool.setToolStatus(resultSet.getString("toolStatus"));
                tool.setInsertMatching(resultSet.getString("insertMatching"));
                String toolType = resultSet.getString("toolType");
                if(toolType.toLowerCase().contains("frez") || toolType.toLowerCase().contains("wiert")) {

                    tool.setToolSizes(toolSize);
                }
                else {
                    tool.setToolSizes("");
                }

                System.out.println(tool.getToolSizes());

                tools.add(tool);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tools;
    }





    public List<Tool> getAllToolsFromPart(String partNumber) {
        List<Tool> tools = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TOOLS_FROM_PART)){
             preparedStatement.setString(1, partNumber);
             ResultSet resultSet = preparedStatement.executeQuery();




            while (resultSet.next()) {
                Tool tool = new Tool();
                tool.setToolName(resultSet.getString("toolName"));
                tool.setToolIndex(resultSet.getString("toolIndex"));
                tool.setToolQty(resultSet.getInt("toolQty"));
                tool.setToolType(resultSet.getString("toolType"));
                tool.setToolDiameter(resultSet.getDouble("toolDiameter"));
                tool.setToolInfo(resultSet.getString("toolInfo"));
                tool.setToolStatus(resultSet.getString("toolStatus"));
                tool.setInsertMatching(resultSet.getString("insertMatching"));

                tools.add(tool);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tools;
    }

    //    toolName VARCHAR(50),
//    toolIndex VARCHAR(50),
//    toolQty INT,
//    toolType VARCHAR(50),
//    toolDiameter DOUBLE,
//    toolInfo VARCHAR(255),
//    toolStatus VARCHAR(255)

    public void updateTool(Tool tool, String oldToolIndex) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOL)) {
            preparedStatement.setString(1, tool.getToolName());
            preparedStatement.setString(2, tool.getToolIndex());
            preparedStatement.setInt(3, tool.getToolQty());
            preparedStatement.setString(4, tool.getToolType());
            preparedStatement.setDouble(5, tool.getToolDiameter());
            preparedStatement.setString(6, tool.getToolInfo());
            preparedStatement.setString(7, tool.getToolStatus());
            preparedStatement.setString(8, oldToolIndex);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateToolStatus(Tool tool) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOLSTATUS)) {
            preparedStatement.setString(1, tool.getToolStatus());
            preparedStatement.setString(2, tool.getToolIndex());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateToolInfo(Tool tool) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOOLINFO)) {
            preparedStatement.setString(1, tool.getToolInfo());
            preparedStatement.setString(2, tool.getToolIndex());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteTool(String toolIndex, String tableName) {
         String DELETE_TOOL = "DELETE FROM " + tableName+  " WHERE toolIndex=?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOOL)) {
            preparedStatement.setString(1, toolIndex);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private static final Map<String, String> prefixToTableMap = new HashMap<>();
    static {
        prefixToTableMap.put("TS-P", "tappr");
        prefixToTableMap.put("TT-P", "tapsk");
        prefixToTableMap.put("TC-P", "tapinch");
        prefixToTableMap.put("LT-IE", "facegroove");
        prefixToTableMap.put("LT-I-", "turningid");
        prefixToTableMap.put("LT-E-", "turningod");
        prefixToTableMap.put("CM", "chamfer");
        prefixToTableMap.put("CD", "spotdrill");
        prefixToTableMap.put("DR-HSS", "drillhss");
        prefixToTableMap.put("DR-VHM", "drillvhm");
        prefixToTableMap.put("H-DR", "drillblades");
        prefixToTableMap.put("DM", "threaddie");
        prefixToTableMap.put("EM-N", "emalu");
        prefixToTableMap.put("EM-P", "emmet");
        prefixToTableMap.put("EM-R", "emr");
        prefixToTableMap.put("BM", "emr");
        prefixToTableMap.put("H-FF", "shellmill");
        prefixToTableMap.put("H-NM", "shellmill");
        prefixToTableMap.put("R", "reamer");
    }






    private static final String UPDATE_TOOLSTATUSSS = "UPDATE %s SET toolStatus=? WHERE toolIndex=?";
    private static final String SELECT_FROM_TOOLINDEX = "SELECT*FROM %s";

    public String getTableName(String toolIndex) {
        for (Map.Entry<String, String> entry : prefixToTableMap.entrySet()) {
            if (toolIndex.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }




    public boolean toolExists(String toolIndex) {
        for (String tableName : prefixToTableMap.values()) {
            if (toolExistsInTable(toolIndex, tableName)) {
                return true;
            }
        }
        return false;
    }

    private boolean toolExistsInTable(String toolIndex, String tableName) {
        String query = "SELECT 1 FROM " + tableName + " WHERE toolIndex = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toolIndex);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public BigDecimal getCostByToolIndex(String toolIndex) {
        String tableName = getTableName(toolIndex);
        if (tableName == null) {
            return null;
        }

        String query = String.format(SELECT_FROM_TOOLINDEX, tableName) + " WHERE toolIndex = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toolIndex);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    BigDecimal cost = resultSet.getBigDecimal("price");
                    return cost;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }



    public void updateToolStatus(String toolIndex, String newStatus) {
        String tableName = getTableName(toolIndex);
        System.out.println(tableName);
        if (tableName != null & tableName.equalsIgnoreCase("drillhss")) {

            String query = "UPDATE DrillHSS SET qty = qty -  ? WHERE toolIndex = ?";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, 1);
                statement.setString(2, toolIndex);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableName !=null) {
            String updateQuery = String.format(UPDATE_TOOLSTATUSSS, tableName);
            System.out.println(updateQuery);
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newStatus);
                preparedStatement.setString(2, toolIndex);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



         else {
            System.out.println("Nie znaleziono odpowiedniej tabeli dla indeksu: " + toolIndex);
        }
    }



}
