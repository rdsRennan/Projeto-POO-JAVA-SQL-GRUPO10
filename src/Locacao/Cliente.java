package locacao;

public class Cliente {

    private String nome;

    public Cliente(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "SemNome";
            System.out.println("Nome inválido, definido como padrão.");
        }
    }

    public String getNome() {
        return nome;
    }
}
