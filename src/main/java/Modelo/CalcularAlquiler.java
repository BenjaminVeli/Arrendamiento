package Modelo;


public class CalcularAlquiler {
    int id;
    Arrendamientos cliente;
    int rent;
    int total;
    int totalRent;
    Piso piso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Arrendamientos getCliente() {
        return cliente;
    }

    public void setCliente(Arrendamientos cliente) {
        this.cliente = cliente;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(int totalRent) {
        this.totalRent = totalRent;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }
}
