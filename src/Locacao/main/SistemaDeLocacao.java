package Locacao.main;

import javax.swing.*;
import java.awt.GridLayout;
import Locacao.service.SistemaLocacao;
import Locacao.model.Cliente;
import Locacao.model.Notebook;
import Locacao.model.Roteador;

public class SistemaDeLocacao {

    private SistemaLocacao sistema = new SistemaLocacao();

    public SistemaDeLocacao() {
        JFrame frame = new JFrame("Sistema de Locação");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        // ================= BOTÕES =================
        JButton btnAddCliente = new JButton("Adicionar Cliente"); //Create
        JButton btnListarCliente = new JButton("Listar Cliente"); //Read
        JButton btnAtualizarCliente = new JButton("Atualizar Cliente"); //Update
        JButton btnRemoverCliente = new JButton("Remover Cliente"); //Delete

        JButton btnAddItem = new JButton("Adicionar Item"); //Create
        JButton btnListarItens = new JButton("Listar Itens"); //Read
        JButton btnAtualizarItem = new JButton("Atualizar Item"); //Update
        JButton btnRemoverItem = new JButton("Remover Item"); //Delete

        JButton btnCriarContrato = new JButton("Criar Contrato"); //Create
        JButton btnListarContrato = new JButton("Listar Contratos"); //Read
        JButton btnAtualizarContrato = new JButton("Atualizar Contrato"); //Update
        JButton btnDevolver = new JButton("Devolver Item"); //Delete

        JButton btnSair = new JButton("Sair");

        // ================= CLIENTES =================
        //CREATE
        btnAddCliente.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome do cliente:");

            if (nome != null && !nome.trim().isEmpty()) {
                sistema.adicionarCliente(new Cliente(nome));
                JOptionPane.showMessageDialog(null, "Cliente criado!");
            } else {
                JOptionPane.showMessageDialog(null, "Nome inválido.");
            }
        });
        //READ
        btnListarCliente.addActionListener(e ->
            JOptionPane.showMessageDialog(null, sistema.listarClientesTexto())
        );
        //UPDATE
        btnAtualizarCliente.addActionListener(e -> {

            var clientes = sistema.listarClientes();

            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
                return;
            }

            String[] opcoes = new String[clientes.size()];
            for (int i = 0; i < clientes.size(); i++) {
                var c = clientes.get(i);
                opcoes[i] = c.getId() + " - " + c.getNome();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null, "Selecione o cliente:", "Atualizar Cliente",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
            );

            if (escolha != null) {
                int id = Integer.parseInt(escolha.split(" - ")[0]);
                String nomeAtual = escolha.split(" - ")[1];

                String novoNome = JOptionPane.showInputDialog("Novo nome:", nomeAtual);

                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    String resultado = sistema.atualizarClienteTexto(new Cliente(id, novoNome));
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });
        //DELETE
        btnRemoverCliente.addActionListener(e -> {

            var clientes = sistema.listarClientes();

            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
                return;
            }

            String[] opcoes = new String[clientes.size()];
            for (int i = 0; i < clientes.size(); i++) {
                var c = clientes.get(i);
                opcoes[i] = c.getId() + " - " + c.getNome();
            }

            String escolha = (String) JOptionPane.showInputDialog(
                    null, "Selecione o cliente para remover:", "Remover Cliente",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
            );

            if (escolha != null) {
                int id = Integer.parseInt(escolha.split(" - ")[0]);
                String resultado = sistema.removerClienteTexto(id);
                JOptionPane.showMessageDialog(null, resultado);
            }
        });

        // ================= ITENS =================
        //CREATE
        btnAddItem.addActionListener(e -> {
            String[] opcoes = {"Notebook", "Roteador"};

            int tipo = JOptionPane.showOptionDialog(
                    null, "Tipo:", "Item",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]
            );

            String nome = JOptionPane.showInputDialog("Nome:");

            if (nome != null && !nome.trim().isEmpty()) {

                if (tipo == 0) {
                    sistema.adicionarItem(new Notebook(nome));
                } else {
                    sistema.adicionarItem(new Roteador(nome));
                }

                JOptionPane.showMessageDialog(null, "Item criado!");
            } else {
                JOptionPane.showMessageDialog(null, "Nome inválido.");
            }
        });
        //READ
        btnListarItens.addActionListener(e ->
            JOptionPane.showMessageDialog(null, sistema.listarItensTexto())
        );
        //UPDATE
        btnAtualizarItem.addActionListener(e -> {
            var itens = sistema.listarItens();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum item cadastrado.");
                return;
            }
            String[] opcoes = new String[itens.size()];
            for (int i = 0; i < itens.size(); i++) {
                var item = itens.get(i);
                opcoes[i] = item.getId() + " - " + item.getNome();
            }
            String escolha = (String) JOptionPane.showInputDialog(
                    null, "Selecione o item:", "Atualizar Item",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
            );
            if (escolha != null) {
                int id = Integer.parseInt(escolha.split(" - ")[0]);
                String nomeAtual = escolha.split(" - ")[1];

                String novoNome = JOptionPane.showInputDialog("Novo nome:", nomeAtual);

                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    String resultado = sistema.atualizarItemTexto(id, novoNome);
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });
        //DELETE
        btnRemoverItem.addActionListener(e -> {
            var itens = sistema.listarItens();
            if (itens.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum item cadastrado.");
                return;
            }
            String[] opcoes = new String[itens.size()];
            for (int i = 0; i < itens.size(); i++) {
                var item = itens.get(i);
                opcoes[i] = item.getId() + " - " + item.getNome();
            }
            String escolha = (String) JOptionPane.showInputDialog(
                    null, "Selecione o item:", "Remover Item",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
            );
            if (escolha != null) {
                int id = Integer.parseInt(escolha.split(" - ")[0]);
                String resultado = sistema.removerItemTexto(id);
                JOptionPane.showMessageDialog(null, resultado);
            }
        });

        // ================= CONTRATOS =================
        //CREATE
        btnCriarContrato.addActionListener(e -> {
            var clientes = sistema.listarClientes();
            var itens = sistema.listarItens();
            if (clientes.isEmpty() || itens.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Precisa ter cliente e item cadastrados.");
                return;
            }
            // CLIENTES
            String[] opClientes = new String[clientes.size()];
            for (int i = 0; i < clientes.size(); i++) {
                var c = clientes.get(i);
                opClientes[i] = c.getId() + " - " + c.getNome();
            }

            String escolhaCliente = (String) JOptionPane.showInputDialog(
                    null, "Selecione o cliente:",
                    "Contrato",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opClientes,
                    opClientes[0]
            );
            if (escolhaCliente == null) return;
            int clienteId = Integer.parseInt(escolhaCliente.split(" - ")[0]);

            // ITENS
            String[] opItens = new String[itens.size()];
            for (int i = 0; i < itens.size(); i++) {
                var item = itens.get(i);
                opItens[i] = item.getId() + " - " + item.getNome();
            }

            String escolhaItem = (String) JOptionPane.showInputDialog(
                    null, "Selecione o item:",
                    "Contrato",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opItens,
                    opItens[0]
            );

            if (escolhaItem == null) return;

            int itemId = Integer.parseInt(escolhaItem.split(" - ")[0]);

            // DIAS
            int dias = Integer.parseInt(
                    JOptionPane.showInputDialog("Quantidade de dias:")
            );

            String resultado = sistema.criarContratoTexto(clienteId, itemId, dias);

            JOptionPane.showMessageDialog(null, resultado);
        });
        //READ
        btnListarContrato.addActionListener(e ->
            JOptionPane.showMessageDialog(null, sistema.listarContratosTexto())
        );
        //UPDATE
        btnAtualizarContrato.addActionListener(e -> {
            var contratos = sistema.listarContratos();
            if (contratos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum contrato cadastrado.");
                return;
            }
            String[] opcoes = new String[contratos.size()];
            for (int i = 0; i < contratos.size(); i++) {
                var c = contratos.get(i);
                opcoes[i] = c.getId() + " - Cliente " + c.getCliente().getNome();
            }
            String escolha = (String) JOptionPane.showInputDialog(
                    null, "Selecione o contrato:", "Atualizar Contrato",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
            );
            if (escolha != null) {
                int id = Integer.parseInt(escolha.split(" - ")[0]);
                int dias = Integer.parseInt(JOptionPane.showInputDialog("Novos dias:"));

                String resultado = sistema.atualizarContratoTexto(id, dias);
                JOptionPane.showMessageDialog(null, resultado);
            }
        });
        //DELETE
        btnDevolver.addActionListener(e -> {
            var contratos = sistema.listarContratos();
            if (contratos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum contrato cadastrado.");
                return;
            }
            String[] opcoes = new String[contratos.size()];
            for (int i = 0; i < contratos.size(); i++) {
                var c = contratos.get(i);
                opcoes[i] = c.getId() + " - Cliente: " + c.getCliente().getNome()
                           + " | Item: " + c.getItem().getNome();
            }
            String escolha = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione o contrato:",
                    "Devolver Item",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
            if (escolha != null) {

                int id = Integer.parseInt(escolha.split(" - ")[0]);

                int dias = Integer.parseInt(
                        JOptionPane.showInputDialog("Dias de atraso:")
                );

                String resultado = sistema.devolverItemTexto(id, dias);

                JOptionPane.showMessageDialog(null, resultado);
            }
        });
        
        // ================= SAIR =================
        btnSair.addActionListener(e -> System.exit(0));

        // ================= UI =================
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("GERENCIAR CLIENTES", SwingConstants.CENTER));
        panel.add(btnAddCliente); //Create
        panel.add(btnListarCliente); //Read
        panel.add(btnAtualizarCliente); //Update
        panel.add(btnRemoverCliente); //Delete
        
        panel.add(new JLabel("GERENCIAR ITENS", SwingConstants.CENTER));
        panel.add(btnAddItem); //Create
        panel.add(btnListarItens); //Read
        panel.add(btnAtualizarItem); //Update
        panel.add(btnRemoverItem); //Delete
        
        panel.add(new JLabel("GERENCIAR CONTRATOS", SwingConstants.CENTER));
        panel.add(btnCriarContrato); //Create
        panel.add(btnListarContrato); //Read
        panel.add(btnAtualizarContrato); //Update
        panel.add(btnDevolver); //Delete
        
        panel.add(new JSeparator());
        panel.add(btnSair);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaDeLocacao();
    }
}