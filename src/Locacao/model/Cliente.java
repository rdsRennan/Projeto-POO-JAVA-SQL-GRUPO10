package Locacao.model;

public class Cliente {

    private int id;
    private String nome;

    public Cliente(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "SemNome";
            System.out.println("Nome inválido, definido como padrão.");
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public Cliente(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNome() {
        return nome;
    }
        
    public int getId() {
        return id;
    }

    public void setNome(String novoNome) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
