import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //public Connection connect;

    public Connection DBConnect(Connection connect) {
        try {
            String url = "jdbc:mysql://localhost:3306/graphiccards";
            String userDB = "root";
            String password = "root123";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, userDB, password);
            System.out.println("Connection success");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
