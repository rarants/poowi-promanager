package br.rarants.inf.ufsm.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5433/promanager";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}