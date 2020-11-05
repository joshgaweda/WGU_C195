package DBUtils;
/**
 * Handles opening and closing the database connection
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Josh Gaweda
 */
public class DBConnection {
    private static final String DBNAME = "WJ06uqW";
    private static final String URL = "jdbc:mysql://wgudb.ucertify.com/" + DBNAME;
    private static final String USER = "U06uqW";
    private static final String PASS = "53688875766";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 
    private static Connection conn;
    
    public DBConnection() {}
    
    // Connect to database
    public static void connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected to MySQL Database");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()); 
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    
    // Close Database connection
    public static void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected From MySQL Database");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    // Returns database connection
    public static Connection getConnection() {
        return conn;
    }

}
