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

    protected static void viewCurrentEntry(int id){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228")){
            PreparedStatement viewTableStatement = connection.prepareStatement("SELECT * FROM main.users WHERE id = ?");
            viewTableStatement.setInt(1, id);
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
                "root", "PizzaEater228")){
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

    protected static void delete(int i){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228")){
            PreparedStatement createStatement = connection.prepareStatement("DELETE FROM main.users WHERE id = ?");
            createStatement.setInt(1, i);
            createStatement.execute();
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    protected static boolean isRecordAvailable(int recordId){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228")){
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(id) as id FROM main.users WHERE id = ?");
            statement.setInt(1, recordId);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1) != 0;
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }

    protected static void update(int id, String option, String value){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228")){
            PreparedStatement createStatement;
            if(option.equals("username")){
                createStatement = connection.prepareStatement("UPDATE main.users SET username = ? WHERE id = ?");
            } else if (option.equals("password")){
                createStatement = connection.prepareStatement("UPDATE main.users SET password = ? WHERE id = ?");
            } else if (option.equals("email")){
                createStatement = connection.prepareStatement("UPDATE main.users SET email = ? WHERE id = ?");
            } else {
                throw new SQLException();
            }
            createStatement.setString(1, value);
            createStatement.setString(2, id + "");
            createStatement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        viewCurrentEntry(id);
    }
}
