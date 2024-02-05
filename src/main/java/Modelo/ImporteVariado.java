package Modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class ImporteVariado {
    
    int id;
    CalcularAlquiler cliente;
    int ord;
    Date fecha;
    BigDecimal importe;
    BigDecimal sumImporte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ImporteVariado(){
        this.cliente = new CalcularAlquiler();
    }

    public CalcularAlquiler getCliente() {
        return cliente;
    }

    public void setCliente(CalcularAlquiler cliente) {
        this.cliente = cliente;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getSumImporte() {
        return sumImporte;
    }

    public void setSumImporte(BigDecimal sumImporte) {
        this.sumImporte = sumImporte;
    }
}
