package Locacao.connection;
import Locacao.main.SistemaDeLocacao;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/locacao";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage());
        }
    }
}
