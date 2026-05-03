package Locacao.service;

import Locacao.dao.ClienteDAO;
import Locacao.dao.ItemLocavelDAO;
import Locacao.dao.ContratoDAO;

import Locacao.model.Cliente;
import Locacao.model.ItemLocavel;
import Locacao.model.Contrato;

import java.util.List;
import java.util.ArrayList;

public class SistemaLocacao {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ItemLocavelDAO itemDAO = new ItemLocavelDAO();
    private ContratoDAO contratoDAO = new ContratoDAO();

    // ================= CLIENTES =================
    public void adicionarCliente(Cliente c) {
        clienteDAO.inserir(c);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public String listarClientesTexto() {
        StringBuilder sb = new StringBuilder();
        List<Cliente> lista = clienteDAO.listar();

        for (Cliente c : lista) {
            sb.append(c.getId())
              .append(" - ")
              .append(c.getNome())
              .append("\n");
        }

        return sb.toString();
    }

    public String atualizarClienteTexto(Cliente c){
        boolean atualizado = clienteDAO.atualizar(c);

        return atualizado
            ? "✅ Cliente atualizado: " + c.getNome()
            : "❌ Cliente não encontrado.";
    }

    public String removerClienteTexto(int id) {
        boolean removido = clienteDAO.remover(id);

        return removido
            ? "✅ Cliente removido com sucesso."
            : "❌ Cliente não encontrado.";
    }

    // ================= ITENS =================
    public void adicionarItem(ItemLocavel item) {
        itemDAO.inserir(item);
    }

    public List<ItemLocavel> listarItens() {
        return itemDAO.listar();
    }

    public String listarItensTexto() {
        StringBuilder sb = new StringBuilder();

        for (ItemLocavel item : itemDAO.listar()) {
            sb.append(item.getId())
              .append(" - ")
              .append(item.getNome())
              .append(" - ")
              .append(item.isDisponivel() ? "Livre" : "Locado")
              .append("\n");
        }

        return sb.toString();
    }

    public String atualizarItemTexto(int id, String novoNome) {
        ItemLocavel item = itemDAO.buscarPorId(id);

        if (item == null) return "❌ Item não encontrado.";

        item.setNome(novoNome);
        itemDAO.atualizar(item);

        return "✅ Item atualizado.";
    }

    public String removerItemTexto(int id) {
        ItemLocavel item = itemDAO.buscarPorId(id);

        if (item == null) return "❌ Item não encontrado.";
        if (!item.isDisponivel()) return "❌ Item está locado.";

        boolean removido = itemDAO.remover(id);

        return removido
            ? "✅ Item removido."
            : "❌ Erro ao remover.";
    }

    // ================= CONTRATOS =================
    public String criarContratoTexto(int clienteId, int itemId, int dias) {

        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        ItemLocavel item = itemDAO.buscarPorId(itemId);

        if (cliente == null || item == null)
            return "❌ Cliente ou item não encontrado.";

        if (!item.isDisponivel())
            return "❌ Item indisponível.";
        
        if (dias <= 0) {
            return "❌ Dias inválidos.";
        }
        contratoDAO.inserir(clienteId, itemId, dias);

        item.setDisponivel(false);
        itemDAO.atualizar(item);

        return "✅ Contrato criado!";
    }
    
    public List<Contrato> listarContratos() {
        return contratoDAO.listar();
    }

    public String listarContratosTexto() {
        StringBuilder sb = new StringBuilder();

        for (Contrato c : contratoDAO.listar()) {
            sb.append(c.getId())
              .append(" - Cliente: ")
              .append(c.getCliente().getNome())
              .append(" | Item: ")
              .append(c.getItem().getNome())
              .append("\n")
              .append(" | Dias: ")
              .append(c.getDias())
              .append("\n");
        }

        return sb.toString();
    }

    public String atualizarContratoTexto(int id, int dias) {
        Contrato c = contratoDAO.buscarPorId(id);

        if (c == null) return "❌ Contrato não encontrado.";

        c.setDias(dias);
        contratoDAO.atualizar(c);

        return "✅ Contrato atualizado.";
    }

    public String devolverItemTexto(int contratoId, int dias) {

        Contrato c = contratoDAO.buscarPorId(contratoId);

        if (c == null) return "❌ Contrato não encontrado.";

        ItemLocavel item = c.getItem();

        double multa = item.calcularMultaAtraso(dias);

        item.setDisponivel(true);
        itemDAO.atualizar(item);

        contratoDAO.finalizar(contratoId);

        return "✅ Devolução concluída\nMulta: R$ " + multa;
    }
}