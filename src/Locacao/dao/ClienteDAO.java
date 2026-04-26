package Locacao.dao;

import Locacao.connection.Conexao;
import Locacao.model.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    // ================= INSERT =================
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= LISTAR =================
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("nome"));
                c.setId(rs.getInt("id"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================= UPDATE =================
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void remover(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}