package Modelo;
import java.math.BigDecimal;
import java.sql.Date;

public class CalcularAlquiler {
    int id;
    Arrendamientos cliente;
    BigDecimal rent;
    BigDecimal garantia;
    int total;
    BigDecimal totalRent;
    Piso piso;
    String cuarto;
    BigDecimal interes;
    BigDecimal mensual;
    Date fecha;
    Date fechaIngreso;
    String tipoPago;
    BigDecimal pagoDiario;
    BigDecimal pagoSem;
    BigDecimal Quincenal;
    String FechaFinal;
    
    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getPagoDiario() {
        return pagoDiario;
    }

    public void setPagoDiario(BigDecimal pagoDiario) {
        this.pagoDiario = pagoDiario;
    }

    public BigDecimal getPagoSem() {
        return pagoSem;
    }

    public void setPagoSem(BigDecimal pagoSem) {
        this.pagoSem = pagoSem;
    }

    public BigDecimal getQuincenal() {
        return Quincenal;
    }

    public void setQuincenal(BigDecimal Quincenal) {
        this.Quincenal = Quincenal;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getGarantia() {
        return garantia;
    }

    public void setGarantia(BigDecimal garantia) {
        this.garantia = garantia;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
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
    
    public BigDecimal getTotalRent() {
        return totalRent;
    }

    public void setTotalRent() {
    BigDecimal totalBigDecimal = BigDecimal.valueOf(this.total);
    this.totalRent = this.rent.multiply(totalBigDecimal);
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
    
    public BigDecimal getMensual() {
        return mensual;
    }

    public void setMensual(BigDecimal mensual) {
        this.mensual = mensual;
    }
    
    public String getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(String FechaFinal) {
        this.FechaFinal = FechaFinal;
    }
}