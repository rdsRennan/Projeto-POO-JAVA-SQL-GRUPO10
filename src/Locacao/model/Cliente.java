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

    public String getNome() {
        return nome;
    }
        
    public int getId() {
        return id;
    }

}
