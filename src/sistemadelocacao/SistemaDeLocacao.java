package sistemadelocacao;

import java.util.Scanner;
import locacao.Cliente;
import locacao.Notebook;
import locacao.Roteador;
import locacao.SistemaLocacao;

public class SistemaDeLocacao {

    public static int lerInt(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.println("❌ Entrada inválida! Digite um número.");
                sc.nextLine();
            }
        }
    }

    public static boolean confirmar(Scanner sc, String mensagem) {
        System.out.print(mensagem + " (S/N): ");
        String resp = sc.nextLine();
        return resp.equalsIgnoreCase("S") || resp.equalsIgnoreCase("SIM");
    }

    public static int lerIndiceValido(Scanner sc, String mensagem, int tamanho) {
        int index;
        while (true) {
            index = lerInt(sc, mensagem);
            if (index >= 0 && index < tamanho) {
                return index;
            }
            System.out.println("❌ Índice inválido! Tente novamente.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaLocacao sistema = new SistemaLocacao();

        int op;

        do {
            System.out.println("\n1- Cliente\n2- Item\n3- Contrato\n4- Devolver\n5- Relatorio\n6- Remover Cliente\n7- Remover Item\n8- Editar Cliente\n9- Editar Item\n10- Cancelar Contrato\n11- Relatorio Atrasos\n0- Sair");

            op = lerInt(sc, "Escolha: ");

            switch (op) {

                case 1:
                    System.out.print("Nome cliente: ");
                    sistema.adicionarCliente(new Cliente(sc.nextLine()));
                    break;

                case 2:
                    System.out.println("1- Notebook | 2- Roteador");
                    int tipo = lerInt(sc, "Tipo: ");

                    System.out.print("Nome item: ");
                    String nomeItem = sc.nextLine();

                    if (tipo == 1)
                        sistema.adicionarItem(new Notebook(nomeItem));
                    else
                        sistema.adicionarItem(new Roteador(nomeItem));
                    break;

                case 3:
                    if (sistema.totalClientes() == 0 || sistema.totalItens() == 0) {
                        System.out.println("Cadastre cliente e item primeiro.");
                        break;
                    }

                    sistema.listarClientes();
                    sistema.listarItens();

                    int c = lerIndiceValido(sc, "Cliente: ", sistema.totalClientes());
                    int i = lerIndiceValido(sc, "Item: ", sistema.totalItens());

                    if (confirmar(sc, "Confirmar criação do contrato?")) {
                        sistema.criarContrato(c, i);
                    }
                    break;

                case 4:
                    if (sistema.totalContratos() == 0) {
                        System.out.println("Nenhum contrato disponível.");
                        break;
                    }

                    int ct = lerIndiceValido(sc, "Contrato: ", sistema.totalContratos());
                    int d = lerInt(sc, "Dias atraso: ");

                    sistema.devolverItem(ct, d);
                    break;

                case 5:
                    sistema.relatorioItens();
                    break;

                case 6:
                    if (sistema.totalClientes() == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    sistema.listarClientes();
                    int idx = lerIndiceValido(sc, "Index cliente: ", sistema.totalClientes());

                    if (confirmar(sc, "Deseja remover este cliente?")) {
                        sistema.removerCliente(idx);
                    }
                    break;

                case 7:
                    if (sistema.totalItens() == 0) {
                        System.out.println("Nenhum item cadastrado.");
                        break;
                    }

                    sistema.listarItens();
                    int idxItem = lerIndiceValido(sc, "Index item: ", sistema.totalItens());

                    if (confirmar(sc, "Deseja remover este item?")) {
                        sistema.removerItem(idxItem);
                    }
                    break;

                case 8:
                    if (sistema.totalClientes() == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    sistema.listarClientes();
                    int ec = lerIndiceValido(sc, "Index: ", sistema.totalClientes());

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();

                    if (confirmar(sc, "Deseja alterar o cliente?")) {
                        sistema.editarCliente(ec, novoNome);
                    }
                    break;

                case 9:
                    if (sistema.totalItens() == 0) {
                        System.out.println("Nenhum item cadastrado.");
                        break;
                    }

                    sistema.listarItens();
                    int ei = lerIndiceValido(sc, "Index: ", sistema.totalItens());

                    System.out.print("Novo nome: ");
                    String novoNomeItem = sc.nextLine();

                    if (confirmar(sc, "Deseja alterar o item?")) {
                        sistema.editarItem(ei, novoNomeItem);
                    }
                    break;

                case 10:
                    if (sistema.totalContratos() == 0) {
                        System.out.println("Nenhum contrato disponível.");
                        break;
                    }

                    int contrato = lerIndiceValido(sc, "Contrato: ", sistema.totalContratos());

                    if (confirmar(sc, "Deseja cancelar o contrato?")) {
                        sistema.cancelarContrato(contrato);
                    }
                    break;

                case 11:
                    sistema.relatorioAtrasos();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }

        } while (op != 0);

        sc.close();
    }
}