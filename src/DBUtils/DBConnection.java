package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Open/close the database connection
 */
public class DBConnection 
{
    private static Connection dbcon;
    private static final String DB_NAME = "WJ06uqW";
    private static final String DB_URL = "jdbc:mysql://wgudb.ucertify.com/" + DB_NAME;
    private static final String USER_NAME = "U06uqW";
    private static final String DB_PASS = "53688875766";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 

    // Returns database connection
    public static Connection getDBConnection() 
    {
        return dbcon;
    }
    
    // Connect to database
    public static void connectDB() 
    {
        try
        {
            Class.forName(DRIVER);
            dbcon = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASS);
            System.out.println("Connected to database");
        } 
        catch(SQLException e) 
        {
            System.out.println("SQL Exception: " + e.getMessage()); 
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
        catch(ClassNotFoundException e) 
        {
            System.out.println("Invalid class" + e.getMessage());
        } 
    }
    
    // Close database connection
    public static void disconnectDB() 
    {
        try 
        {
            dbcon.close();
            System.out.println("Disconnected from database");
        } 
        catch (SQLException e) 
        {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
