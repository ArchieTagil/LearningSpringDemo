package my.course.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Solution {
    private static final String URL = "jdbc:mysql://localhost:3306/people?serverTimezone=Asia/Yekaterinburg";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static void main(String[] args) {
        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection was established successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection to database wasn't established!");
        }
    }
}
