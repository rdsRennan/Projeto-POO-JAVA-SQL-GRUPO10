package locacao;

public class Roteador extends ItemLocavel {

    public Roteador(String nome) {
        super(nome);
    }

    @Override
    public double calcularCaucao() {
        return 150;
    }

    @Override
    public double calcularMultaAtraso(int dias) {
        return dias * 10;
    }
}
