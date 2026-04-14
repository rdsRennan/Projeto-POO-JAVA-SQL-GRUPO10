package locacao;

public class Contrato {

    private Cliente cliente;
    private ItemLocavel item;

    public Contrato(Cliente cliente, ItemLocavel item) {
        this.cliente = cliente;
        this.item = item;
        item.setDisponivel(false);
    }

    public ItemLocavel getItem() {
        return item;
    }

    public void devolver(int diasAtraso) {
        double multa = item.calcularMultaAtraso(diasAtraso);
        System.out.println("Multa: " + multa);
        item.setDisponivel(true);
    }
}
