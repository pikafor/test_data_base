package org.example.Gui;

import org.example.Controller.SqlController;
import org.example.Connection.SqlConnection;

import java.util.Scanner;

public class SqlGui {
    private boolean isRequest = true;
    SqlController sqlController;

    public SqlGui(SqlConnection sqlConnection) {
        sqlController = new SqlController(sqlConnection.getConnection());
    }

    public void mainProcess() {
        while (isRequest) {
            int choice = 0;

            System.out.println("1) Добавить пару");
            System.out.println("2) Показать присутсвующих");
            System.out.println("3) Отметить");
            System.out.println("4) конвертировать в exel");
            System.out.print("Выберите действие: ");

            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Вы ввели не число");
            }

            switch (choice) {
                case 1: {
                    String tableName = '"' + scanner.next() + '"';
                    sqlController.addColumn(tableName);
                    System.out.println();
                    break;
                }
                case 2: {
                    sqlController.showTable();
                    break;
                }
                case 3: {
                    System.out.println("Введите инициалы");
                    String initials = scanner.next();
                    System.out.println("Введите дату");
                    String date = '"' + scanner.next() + '"';
                    sqlController.mention(initials, date);
                    break;
                }
                case 4: {
                    break;
                }
                default: {
                    System.out.println("Нет такого действия.");
                    break;
                }
            }
        }
    }
}
