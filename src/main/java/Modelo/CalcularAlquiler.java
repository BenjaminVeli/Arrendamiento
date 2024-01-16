package Modelo;
import java.sql.Date;

public class CalcularAlquiler {
    int id;
    Arrendamientos cliente;
    int rent;
    int garantia;
    int total;
    int totalRent;
    Piso piso;
    String cuarto;
    int cuotas;
    Date fecha;
    Date fechaIngreso;

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CalcularAlquiler() {
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
    
    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
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
