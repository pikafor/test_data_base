package org.example.Controller;

import java.sql.*;

public class SqlController {
    private Connection connection;
    public SqlController(Connection connection) {
        this.connection = connection;
    }

    private void setString(PreparedStatement preparedStatement, int id, String value) {
        try {
            preparedStatement.setString(id, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setInt(PreparedStatement preparedStatement, int id, int value) {
        try {
            preparedStatement.setInt(id, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTable() {
        String tableName = " test ";
        String sql = "create table " + tableName + " (id integer PRIMARY KEY, initials varchar(40));";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addColumn(String tableName) {
        String sql = "ALTER TABLE test ADD COLUMN " + tableName + " varchar(15) default ('Присутсвовал');";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mention(String initials, String date) {
        String sql = "update test set " +  date + " = 'Отсутсвовал' where initials = '" + initials + "'";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showTable() {
        String sql = "select * from test";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            //int count =
            while(resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(show(i, resultSetMetaData)) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addStudent() {
        String sql = "insert into test (id, initials) values (2, 'Петров Д.А.')";
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String show(int i, ResultSetMetaData resultSetMetaData) {
        try {
            return resultSetMetaData.getColumnName(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
