package Locacao.model;

public class Cliente {

    private int id;
    private String nome;

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Cliente(String nome) {
        this.nome = nome;
    }
    
    public Cliente(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
        
    

}
