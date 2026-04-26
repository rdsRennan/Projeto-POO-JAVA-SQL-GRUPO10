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
        String sql = "INSERT INTO item (nome, disponivel) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getNome());
            stmt.setBoolean(2, item.isDisponivel());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
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

                //decide o tipo
                ItemLocavel item = new Notebook(nome); // ou Roteador
                item.setDisponivel(disponivel);
                item.setId(rs.getInt("id"));

                lista.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================= UPDATE =================
    public void atualizar(ItemLocavel item) {
        String sql = "UPDATE item SET nome = ?, disponivel = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setBoolean(2, item.isDisponivel());
            stmt.setInt(3, item.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void remover(int id) {
        String sql = "DELETE FROM item WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}