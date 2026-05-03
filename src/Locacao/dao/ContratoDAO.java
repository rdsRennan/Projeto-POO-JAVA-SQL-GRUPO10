package Locacao.dao;

import Locacao.connection.Conexao;
import Locacao.model.Contrato;
import Locacao.model.Cliente;
import Locacao.model.ItemLocavel;

import java.sql.*;
import java.util.ArrayList;

public class ContratoDAO {

    // ================= CREATE =================
    public void inserir(int clienteId, int itemId, int dias) {
        String sql = "INSERT INTO contrato (cliente_id, item_id, dias) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, clienteId);
            stmt.setInt(2, itemId);
            stmt.setInt(3, dias);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Contrato criado com ID: " + rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= READ =================
    public ArrayList<Contrato> listar() {
        ArrayList<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contrato";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ClienteDAO clienteDAO = new ClienteDAO();
            ItemLocavelDAO itemDAO = new ItemLocavelDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                int itemId = rs.getInt("item_id");
                int dias = rs.getInt("dias");

                Cliente cliente = clienteDAO.buscarPorId(clienteId);
                ItemLocavel item = itemDAO.buscarPorId(itemId);

                // segurança
                if (cliente == null || item == null) {
                    System.out.println("Erro ao montar contrato ID: " + id);
                    continue;
                }

                Contrato c = new Contrato(cliente, item);
                c.setId(id);
                c.setDias(dias); // 🔥 CORREÇÃO IMPORTANTE

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================= BUSCAR POR ID =================
    public Contrato buscarPorId(int id) {
        String sql = "SELECT * FROM contrato WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int clienteId = rs.getInt("cliente_id");
                int itemId = rs.getInt("item_id");
                int dias = rs.getInt("dias");

                ClienteDAO clienteDAO = new ClienteDAO();
                ItemLocavelDAO itemDAO = new ItemLocavelDAO();

                Cliente cliente = clienteDAO.buscarPorId(clienteId);
                ItemLocavel item = itemDAO.buscarPorId(itemId);

                if (cliente == null || item == null) {
                    return null;
                }

                Contrato c = new Contrato(cliente, item);
                c.setId(id);
                c.setDias(dias); // 🔥 CORREÇÃO

                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= UPDATE =================
    public boolean atualizar(Contrato c) {
        String sql = "UPDATE contrato SET dias = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, c.getDias());
            stmt.setInt(2, c.getId());

            int linhas = stmt.executeUpdate();

            return linhas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE =================
    public boolean remover(int id) {
        String sql = "DELETE FROM contrato WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            return linhas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= FINALIZAR =================
    public void finalizar(int id) {
        remover(id); // reaproveita lógica
    }
}