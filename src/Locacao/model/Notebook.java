package Locacao.model;

public class Notebook extends ItemLocavel {

    public Notebook(String nome) {
        super(nome);
    }

    @Override
    public double calcularCaucao() {
        return 500;
    }

    @Override
    public double calcularMultaAtraso(int dias) {
        return dias * 30;
    }
}
