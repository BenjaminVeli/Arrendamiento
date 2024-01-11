package Modelo;


public class CalcularAlquiler {
    int id;
    Arrendamientos cliente;
    int rent;
    int total;
    int totalRent;
    Piso piso;
    String cuarto;

    public CalcularAlquiler() {
        // Inicializa el objeto cliente en el constructor
        this.cliente = new Arrendamientos();
    }
    
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

    public void setTotalRent() {
        this.totalRent =  this.rent * this.total;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }
    
    public String getCuarto() {
        return cuarto;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }
}
