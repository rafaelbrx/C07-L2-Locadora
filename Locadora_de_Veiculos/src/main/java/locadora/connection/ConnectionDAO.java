package locadora.connection;

import java.sql.*;

public abstract class ConnectionDAO {

    public Connection connection;

    public PreparedStatement pst;
    public Statement st;
    public ResultSet rs;

    String database = "LOCADORA";
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public Connection connectToDb() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return null;
    }
}