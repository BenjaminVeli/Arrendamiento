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
    BigDecimal saldos;
    BigDecimal pago;
    boolean estado;
    Date fecha_amortizacion;
    Piso piso;
    String cuarto;
    
    public BigDecimal getSaldos() {
        return saldos;
    }

    public void setSaldos(BigDecimal saldos) {
        this.saldos = saldos;
    }

    public BigDecimal getPago() {
        return pago;
    }

    public void setPago(BigDecimal pago) {
        this.pago = pago;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFecha_amortizacion() {
        return fecha_amortizacion;
    }

    public void setFecha_amortizacion(Date fecha_amortizacion) {
        this.fecha_amortizacion = fecha_amortizacion;
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
