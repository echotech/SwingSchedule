/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class TestConnection {

    private static String serverName = "jdbc:mysql://52.206.157.109:3306";
    private static String databaseName = "U03q1A";
    private static String username = "U03q1A";
    private static String password = "53688053834";
    
    private static Connection connection;
    
    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(serverName, username, password);
    }
    
    public static Connection getConnection() throws Exception {
        if (connection == null) {
            connect();
        }
        return connection;
    }

}
