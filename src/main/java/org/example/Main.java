package org.example;

import org.example.Connection.SqlConnection;
import org.example.Gui.SqlGui;

public class Main {
    public static void main(String[] args) {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.Connection();

        SqlGui sqlGui = new SqlGui(sqlConnection);

        sqlGui.mainProcess();
    }
}