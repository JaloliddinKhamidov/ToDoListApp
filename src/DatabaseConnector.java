import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnector {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // loading and registering the Driver using Class.forName
            String url = "jdbc:mysql://localhost:3306/todolist_db"; // Update with your database name
            String username = "root";
            String password = "omon2134ADA$";
            /*Establish a connection to the MySQL database using the provided URL, username, and password.*/
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
