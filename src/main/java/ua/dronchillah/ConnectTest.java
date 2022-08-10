package ua.dronchillah;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class ConnectTest
{
    public static void main( String[] args )
    {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/main?serverTimezone=UTC",
                "root", "PizzaEater228")) {
            boolean isConnected = connection.isValid(0);
            System.out.println("Is connected: " + isConnected);
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM main.users WHERE username = ?");
            selectStatement.setString(1, "Moroz");
            ResultSet set = selectStatement.executeQuery();
            while(set.next()){
                String index = set.getString("id");
                String username = set.getString("username");
                String password = set.getString("password");
                String email = set.getString("email");
                System.out.println("id: "+index +
                        "\n email: " + email +
                        "\n username: " + username +
                        "\n password: " + password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

    }
}
