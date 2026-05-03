package Locacao.dao;

import Locacao.connection.Conexao;
import Locacao.model.ItemLocavel;
import Locacao.model.Notebook;
import Locacao.model.Roteador;

import java.sql.*;
import java.util.ArrayList;

public class ItemLocavelDAO {

    // ================= INSERT =================
    public void inserir(ItemLocavel item) {
        String sql = "INSERT INTO item (nome, disponivel, tipo) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getNome());
            stmt.setBoolean(2, item.isDisponivel());
            stmt.setString(3, item.getTipo());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
                System.out.println("Item inserido com ID: " + item.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= LISTAR =================
    public ArrayList<ItemLocavel> listar() {
        ArrayList<ItemLocavel> lista = new ArrayList<>();
        String sql = "SELECT * FROM item";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                String nome = rs.getString("nome");
                boolean disponivel = rs.getBoolean("disponivel");
                String tipo = rs.getString("tipo");
                int id = rs.getInt("id");

                ItemLocavel item = criarItem(tipo, nome);

                item.setId(id);
                item.setDisponivel(disponivel);

                lista.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================= BUSCAR POR ID =================
    public ItemLocavel buscarPorId(int id) {
        String sql = "SELECT * FROM item WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                boolean disponivel = rs.getBoolean("disponivel");

                ItemLocavel item = criarItem(tipo, nome); // 🔥 reutiliza

                item.setId(id);
                item.setDisponivel(disponivel);

                return item;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= UPDATE =================
    public boolean atualizar(ItemLocavel item) {
        String sql = "UPDATE item SET nome = ?, disponivel = ?, tipo = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setBoolean(2, item.isDisponivel());
            stmt.setString(3, item.getTipo());
            stmt.setInt(4, item.getId());

            int linhas = stmt.executeUpdate();

            return linhas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // ================= DELETE =================
    public boolean remover(int id) {
        String sql = "DELETE FROM item WHERE id = ?";

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

    // ================= FABRICA DE OBJETOS =================
    private ItemLocavel criarItem(String tipo, String nome) {

        if (tipo == null || tipo.trim().isEmpty()) {
            System.out.println("⚠️ Tipo nulo, assumindo roteador");
            return new Roteador(nome);
        }

        switch (tipo.toLowerCase()) {
            case "notebook":
                return new Notebook(nome);

            case "roteador":
                return new Roteador(nome);

            default:
                System.out.println("⚠️ Tipo desconhecido: " + tipo);
                return new Roteador(nome);
        }
    }
}