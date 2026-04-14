package locacao;

import java.util.ArrayList;

public class SistemaLocacao {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<ItemLocavel> itens = new ArrayList<>();
    private ArrayList<Contrato> contratos = new ArrayList<>();

    // ================= CLIENTES =================
    public void adicionarCliente(Cliente c) {
        clientes.add(c);
    }

    public void listarClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i).getNome());
        }
    }

    public void editarCliente(int index, String novoNome) {
        clientes.set(index, new Cliente(novoNome));
    }

    public void removerCliente(int index) {
        clientes.remove(index);
    }

    public int totalClientes() {
        return clientes.size();
    }

    // ================= ITENS =================
    public void adicionarItem(ItemLocavel item) {
        itens.add(item);
    }

    public void listarItens() {
        for (int i = 0; i < itens.size(); i++) {
            ItemLocavel item = itens.get(i);
            System.out.println(i + " - " + item.getNome() + " - " + (item.isDisponivel() ? "Livre" : "Locado"));
        }
    }

    public void editarItem(int index, String novoNome) {
        ItemLocavel item = itens.get(index);

        if (item instanceof Notebook) {
            itens.set(index, new Notebook(novoNome));
        } else {
            itens.set(index, new Roteador(novoNome));
        }
    }

    public void removerItem(int index) {
        itens.remove(index);
    }

    public int totalItens() {
        return itens.size();
    }

    // ================= CONTRATOS =================
    public void criarContrato(int clienteIndex, int itemIndex) {
        ItemLocavel item = itens.get(itemIndex);

        if (!item.isDisponivel()) {
            System.out.println("❌ Item indisponível.");
            return;
        }

        System.out.println("Caução: " + item.calcularCaucao());
        contratos.add(new Contrato(clientes.get(clienteIndex), item));
    }

    public void devolverItem(int contratoIndex, int dias) {
        contratos.get(contratoIndex).devolver(dias);
    }

    public void cancelarContrato(int index) {
        Contrato c = contratos.get(index);
        c.getItem().setDisponivel(true);
        contratos.remove(index);
        System.out.println("Contrato cancelado.");
    }

    public int totalContratos() {
        return contratos.size();
    }

    // ================= RELATÓRIOS =================
    public void relatorioItens() {
        listarItens();
    }

    public void relatorioAtrasos() {
        System.out.println("Relatório de atrasos (simulado):");
        for (int i = 0; i < contratos.size(); i++) {
            System.out.println("Contrato " + i + " pode ter atraso (simulação)");
        }
    }
}