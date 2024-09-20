package org.albert.ergon_graphos.repository.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
    private static final String URL = "jdbc:mysql://localhost:3306/ergon_graphos";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection INSTANCE;

    public static Connection getConnection() throws SQLException
    {
        if (INSTANCE == null)
        {
            INSTANCE = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return INSTANCE;
    }

    public static Connection getNewConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}