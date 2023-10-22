package org.example.Gui;

import org.example.Controller.ExelController;
import org.example.Controller.SqlController;
import org.example.Connection.SqlConnection;

import java.util.Scanner;

public class UsersGui {
    private boolean isRequest = true;
    SqlController sqlController;

    public UsersGui(SqlConnection sqlConnection) {
        sqlController = new SqlController(sqlConnection.getConnection());
    }

    public void mainProcess() {
        //sqlController.addStudent();
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
                    System.out.println("Введите дату");
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
                    System.out.println("Введите Фамилию");
                    String lastName = scanner.next();
                    System.out.println("Введите дату");
                    String date = '"' + scanner.next() + '"';
                    sqlController.mention(lastName, date);
                    break;
                }
                case 4: {
                    ExelController exelController = new ExelController();
                    exelController.write(" ", sqlController.getResultSetMetaData(), sqlController.getResult());
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
