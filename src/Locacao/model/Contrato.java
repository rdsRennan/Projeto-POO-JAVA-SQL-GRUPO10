package Locacao.model;

public class Contrato {

    private int id;
    private Cliente cliente;
    private ItemLocavel item;

    public Contrato(Cliente cliente, ItemLocavel item) {
        this.cliente = cliente;
        this.item = item;
        this.item.setDisponivel(false);
    }

    // ===== ID =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ===== CLIENTE =====
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // ===== ITEM =====
    public ItemLocavel getItem() {
        return item;
    }

    public void setItem(ItemLocavel item) {
        this.item = item;
    }

    // ===== REGRAS =====
    public void devolver(int diasAtraso) {
        double multa = item.calcularMultaAtraso(diasAtraso);
        System.out.println("Multa: " + multa);
        item.setDisponivel(true);
    }
}