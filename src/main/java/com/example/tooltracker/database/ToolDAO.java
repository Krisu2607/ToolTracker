package com.example.tooltracker.database;

import com.example.tooltracker.model.tools.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolDAO {
    private static final String INSERT_TOOL = "INSERT INTO tools (toolName, toolIndex, toolQty, toolType, toolDiameter, toolInfo, toolStatus, insertMatching ) VALUES (?, ?, ?, ?, ?, ? ,?,?)";
    private static final String SELECT_ALL_TOOLS = "SELECT * FROM tools";
    private static final String UPDATE_TOOL = "UPDATE tools SET toolName=?, toolIndex=?, toolQty=?,  toolType=?, toolDiameter=?, toolInfo=?, toolStatus=? WHERE toolIndex=?";
    private static final String UPDATE_TOOLSTATUS = "UPDATE tools SET  toolStatus=? WHERE toolIndex=?";
    private static final String UPDATE_TOOLINFO = "UPDATE tools SET  toolInfo=? WHERE toolIndex=?";
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


    public void deleteTool(String toolIndex) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOOL)) {
            preparedStatement.setString(1, toolIndex);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
