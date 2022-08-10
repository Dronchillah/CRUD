package ua.dronchillah;

import java.sql.*;
import java.util.Scanner;

public class Actions {

    protected static void viewAllEntries(){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228")){
            PreparedStatement viewTableStatement = connection.prepareStatement("SELECT * FROM main.users");
            ResultSet set = viewTableStatement.executeQuery();
            ResultSetMetaData rsmd = set.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (set.next()) {
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print(set.getString(i) + " ");
                }
                System.out.println();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    protected static void create(String username, String password, String email){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228"); Scanner scan = new Scanner(System.in)){
            PreparedStatement createStatement = connection.prepareStatement("INSERT INTO main.users (username, password, email) VALUES (?, ?, ?)");
            createStatement.setString(1, username);
            createStatement.setString(2, password);
            createStatement.setString(3, email);
            createStatement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        viewAllEntries();
    }
}
