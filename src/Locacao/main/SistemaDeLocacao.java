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
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnListarClientes = new JButton("Listar Cliente");
        JButton btnAddCliente = new JButton("Adicionar Cliente");
        JButton btnRemoverCliente = new JButton("Remover Cliente");
        
        JButton btnListarItens = new JButton("Listar Itens");
        JButton btnAddItem = new JButton("Adicionar Item");
        JButton btnRemoverItem = new JButton("Remover Item");
        
        JButton btnListarContrato = new JButton("Listar Contratos");
        JButton btnCriarContrato = new JButton("Criar Contrato");
        JButton btnDevolver = new JButton("Devolver Item");
        JButton btnSair = new JButton("Sair");
        
        

        // ================= AÇÕES =================
        //Clientes
        btnListarClientes.addActionListener(e -> {
            sistema.listarClientes();
            JOptionPane.showMessageDialog(null, sistema.listarClientesTexto());
        });
        
        btnAddCliente.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome do cliente:");
            if (nome != null) {
                sistema.adicionarCliente(new Cliente(nome));
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao criar cliente.");
            }    
            
        });
        
        btnRemoverCliente.addActionListener(e -> {
            try {
                int index = Integer.parseInt(
                    JOptionPane.showInputDialog("Índice do Cliente para remover:")
                );

                String resultado = sistema.removerClienteTexto(index);
                JOptionPane.showMessageDialog(null, resultado);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover cliente.");
            }
        });

        //Itens
        btnListarItens.addActionListener(e -> {
            sistema.listarItens();
            JOptionPane.showMessageDialog(null, sistema.listarItensTexto());
        });
        
        btnAddItem.addActionListener(e -> {
            String[] opcoes = {"Notebook", "Roteador"};
            int tipo = JOptionPane.showOptionDialog(null, "Tipo de item:", "Escolha",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            String nome = JOptionPane.showInputDialog("Nome do item:");

            if (nome != null) {
                if (tipo == 0)
                    sistema.adicionarItem(new Notebook(nome));
                else
                    sistema.adicionarItem(new Roteador(nome));
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao adicionar item.");
            }  
        });
        
        btnRemoverItem.addActionListener(e -> {
            try {
                int index = Integer.parseInt(
                    JOptionPane.showInputDialog("Índice do item para remover:")
                );

                String resultado = sistema.removerItemTexto(index);
                JOptionPane.showMessageDialog(null, resultado);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover item.");
            }
        });

        //Contratos
        btnListarContrato.addActionListener(e -> {
            sistema.listarContratosTexto();
            JOptionPane.showMessageDialog(null, sistema.listarContratosTexto());
        });
        
        btnCriarContrato.addActionListener(e -> {
            try {
                int c = Integer.parseInt(JOptionPane.showInputDialog("Índice do cliente:"));
                int i = Integer.parseInt(JOptionPane.showInputDialog("Índice do item:"));

                String resultado = sistema.criarContratoTexto(c, i);
                JOptionPane.showMessageDialog(null, resultado);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar contrato");
            }
        });

        btnDevolver.addActionListener(e -> {
            try {
                int ct = Integer.parseInt(JOptionPane.showInputDialog("Índice do contrato:"));
                int dias = Integer.parseInt(JOptionPane.showInputDialog("Dias de atraso:"));

                String resultado = sistema.devolverItemTexto(ct, dias);
                JOptionPane.showMessageDialog(null, resultado);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro na devolução");
            }
        });
        
        btnSair.addActionListener(e -> System.exit(0));

        // ================= UI =================
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(btnListarClientes);
        panel.add(btnAddCliente);
        panel.add(btnRemoverCliente);
        
        panel.add(btnListarItens);
        panel.add(btnAddItem);
        panel.add(btnRemoverItem);
        
        panel.add(btnListarContrato);
        panel.add(btnCriarContrato);
        panel.add(btnDevolver);
        
        panel.add(btnSair);

        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new SistemaDeLocacao();
    }

}