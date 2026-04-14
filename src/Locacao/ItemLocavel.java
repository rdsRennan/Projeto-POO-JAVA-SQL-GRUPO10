package locacao;

public abstract class ItemLocavel {

    private String nome;
    private boolean disponivel = true;

    public ItemLocavel(String nome) {
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

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public abstract double calcularCaucao();

    public abstract double calcularMultaAtraso(int dias);
}
