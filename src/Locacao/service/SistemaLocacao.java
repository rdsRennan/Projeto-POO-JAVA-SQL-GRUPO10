package Locacao.service;

import Locacao.dao.ClienteDAO;
import Locacao.dao.ItemLocavelDAO;
import Locacao.dao.ContratoDAO;

import Locacao.model.Cliente;
import Locacao.model.ItemLocavel;
import Locacao.model.Contrato;

import java.util.ArrayList;

public class SistemaLocacao {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ItemLocavelDAO itemDAO = new ItemLocavelDAO();
    private ContratoDAO contratoDAO = new ContratoDAO();

    // ================= CLIENTES =================

    public void adicionarCliente(Cliente c) {
        clienteDAO.inserir(c);
    }

    public ArrayList<Cliente> listarClientes() {
        return clienteDAO.listar();
    }
    
    public String listarClientesTexto() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Cliente> lista = clienteDAO.listar();

        for (int i = 0; i < lista.size(); i++) {
            sb.append(i)
              .append(" - ")
              .append(lista.get(i).getNome())
              .append("\n");
        }

        return sb.toString();
    }

    public String removerClienteTexto(int index) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Cliente> lista = clienteDAO.listar();

        // validação
        if (index < 0 || index >= lista.size()) {
            return "❌ Índice inválido.";
        }

        Cliente cliente = lista.get(index);

        clienteDAO.remover(cliente.getId());

        return "✅ Cliente removido: " + cliente.getNome();
    }

    public int totalClientes() {
        return clienteDAO.listar().size();
    }

    // ================= ITENS =================

    public void adicionarItem(ItemLocavel item) {
        itemDAO.inserir(item);
    }

    public ArrayList<ItemLocavel> listarItens() {
        return itemDAO.listar();
    }

    public String listarItensTexto() {
        StringBuilder sb = new StringBuilder();
        ArrayList<ItemLocavel> lista = itemDAO.listar();

        for (int i = 0; i < lista.size(); i++) {
            ItemLocavel item = lista.get(i);

            sb.append(i)
              .append(" - ")
              .append(item.getNome())
              .append(" - ")
              .append(item.isDisponivel() ? "Livre" : "Locado")
              .append("\n");
        }

        return sb.toString();
    }

    public void editarItem(int index, String novoNome) {
        ArrayList<ItemLocavel> lista = itemDAO.listar();
        ItemLocavel item = lista.get(index);

        item.setNome(novoNome);
        itemDAO.atualizar(item);
    }

    public String removerItemTexto(int index) {
        ArrayList<ItemLocavel> lista = itemDAO.listar();

        // validação
        if (index < 0 || index >= lista.size()) {
            return "❌ Índice inválido.";
        }

        ItemLocavel item = lista.get(index);

        // regra opcional: não remover se estiver alugado
        if (!item.isDisponivel()) {
            return "❌ Item está locado e não pode ser removido.";
        }

        itemDAO.remover(item.getId());

        return "✅ Item removido: " + item.getNome();
    }

    public int totalItens() {
        return itemDAO.listar().size();
    }

    // ================= CONTRATOS =================

    public String criarContratoTexto(int clienteIndex, int itemIndex) {

        ArrayList<Cliente> clientes = clienteDAO.listar();
        ArrayList<ItemLocavel> itens = itemDAO.listar();

        // validação básica
        if (clienteIndex < 0 || clienteIndex >= clientes.size() ||
            itemIndex < 0 || itemIndex >= itens.size()) {
            return "❌ Índice inválido.";
        }

        Cliente cliente = clientes.get(clienteIndex);
        ItemLocavel item = itens.get(itemIndex);

        if (!item.isDisponivel()) {
            return "❌ Item indisponível.";
        }

        double caucao = item.calcularCaucao();

        contratoDAO.inserir(cliente.getId(), item.getId());

        item.setDisponivel(false);
        itemDAO.atualizar(item);

        // retorno em formato de texto
        return "✅ Contrato criado com sucesso!\n" +
               "Cliente: " + cliente.getNome() + "\n" +
               "Item: " + item.getNome() + "\n" +
               "Caução: R$ " + caucao;
        }

    public String devolverItemTexto(int contratoIndex, int dias) {

        ArrayList<Contrato> contratos = contratoDAO.listar();

        //validação
        if (contratoIndex < 0 || contratoIndex >= contratos.size()) {
            return "❌ Índice de contrato inválido.";
        }

        if (dias < 0) {
            return "❌ Dias de atraso inválidos.";
        }

        Contrato c = contratos.get(contratoIndex);
        ItemLocavel item = c.getItem();

        // calcula multa antes de devolver
        double multa = item.calcularMultaAtraso(dias);

        //regra de negócio
        c.devolver(dias);              // também libera o item internamente
        item.setDisponivel(true);      // garantia extra
        itemDAO.atualizar(item);
        contratoDAO.finalizar(c.getId());

        //retorno
        return "Devolução concluída!\n" +
               "Cliente: " + c.getCliente().getNome() + "\n" +
               "Item: " + item.getNome() + "\n" +
               "Dias de atraso: " + dias + "\n" +
               "Multa: R$ " + String.format("%.2f", multa);
    }

    public void cancelarContrato(int index) {

        ArrayList<Contrato> contratos = contratoDAO.listar();
        Contrato c = contratos.get(index);

        ItemLocavel item = c.getItem();
        item.setDisponivel(true);
        itemDAO.atualizar(item);

        contratoDAO.remover(c.getId());
    }

    public String listarContratosTexto() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Contrato> lista = contratoDAO.listar();

        for (int i = 0; i < lista.size(); i++) {
            Contrato c = lista.get(i);

            sb.append(i)
              .append(" - Cliente: ")
              .append(c.getCliente().getNome())
              .append(" | Item: ")
              .append(c.getItem().getNome())
              .append("\n");
        }

        return sb.toString();
    }
}