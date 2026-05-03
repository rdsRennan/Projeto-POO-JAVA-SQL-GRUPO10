package Locacao.dao;

import Locacao.connection.Conexao;
import Locacao.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // ================= CREATE =================
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
                System.out.println("Cliente inserido com ID: " + cliente.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= BUSCAR POR ID =================
    public Cliente buscarPorId(int id) {
        String sql = "SELECT id, nome FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    // ================= READ =================
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT id, nome FROM cliente";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================= UPDATE =================
    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ? WHERE id = ?";

        System.out.println("Tentando atualizar ID: " + cliente.getId());
        System.out.println("Novo nome: " + cliente.getNome());

        try (Connection conn = Conexao.getConnection()) {

            // verifica se o cliente existe antes
            String checkSql = "SELECT id FROM cliente WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, cliente.getId());
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("ID não encontrado no banco.");
                    return false;
                }
            }
            // faz o update
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setInt(2, cliente.getId());

                int linhasAfetadas = stmt.executeUpdate();

                System.out.println("Linhas afetadas: " + linhasAfetadas);

                return linhasAfetadas > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE =================
    public boolean remover(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            System.out.println("Removendo ID: " + id);
            System.out.println("Linhas afetadas: " + linhasAfetadas);

            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}