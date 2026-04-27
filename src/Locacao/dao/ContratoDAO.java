package Locacao.dao;

import Locacao.connection.Conexao;
import Locacao.model.Cliente;
import Locacao.model.Contrato;
import Locacao.model.ItemLocavel;
import Locacao.model.Notebook;

import java.sql.*;
import java.util.ArrayList;

public class ContratoDAO {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // ================= INSERT =================
    public void inserir(int clienteId, int itemId) {
        String sql = "INSERT INTO contrato (cliente_id, item_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= LISTAR =================
    public ArrayList<Contrato> listar() {
        ArrayList<Contrato> lista = new ArrayList<>();

        String sql = """
            SELECT c.id,
                   cl.id as cliente_id, cl.nome as cliente_nome,
                   i.id as item_id, i.nome as item_nome, i.disponivel
            FROM contrato c
            JOIN cliente cl ON c.cliente_id = cl.id
            JOIN item i ON c.item_id = i.id
            WHERE c.finalizado = false;
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                // 🔹 Cliente
                Cliente cliente = new Cliente(rs.getString("cliente_nome"));
                cliente.setId(rs.getInt("cliente_id"));

                // 🔹 Item (simplificado)
                ItemLocavel item = new Notebook(rs.getString("item_nome"));
                item.setId(rs.getInt("item_id"));
                item.setDisponivel(rs.getBoolean("disponivel"));

                // 🔹 Contrato
                Contrato contrato = new Contrato(cliente, item);
                contrato.setId(rs.getInt("id"));

                lista.add(contrato);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================= FINALIZAR =================
    public void finalizar(int id) {
        String sql = "UPDATE contrato SET finalizado = true WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= REMOVER =================
    public void remover(int id) {
        String sql = "DELETE FROM contrato WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}