package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
    static Statement st;
    static Connection connection;

    public Sql() {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/money";
        String username = "root";
        String password = "rraarreess1015";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            st = connection.createStatement();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
    public void ClosSql()
    {
        try {
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error! Couldn't close database connection!");
            e.printStackTrace();
        }


    }


}
