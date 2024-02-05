package Modelo;
import java.math.BigDecimal;
import java.sql.Date;

public class ImporteMensual {
    
    int id;
    CalcularAlquiler cliente;
    int ord;
    Date fecha;
    BigDecimal saldo;
    BigDecimal capital;
    BigDecimal interes;
    BigDecimal porPagar;
    BigDecimal sumCapital;
    BigDecimal sumInteres;
    BigDecimal sumPorPagar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ImporteMensual(){
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getPorPagar() {
        return porPagar;
    }

    public void setPorPagar(BigDecimal porPagar) {
        this.porPagar = porPagar;
    }

    public BigDecimal getSumCapital() {
        return sumCapital;
    }

    public void setSumCapital(BigDecimal sumCapital) {
        this.sumCapital = sumCapital;
    }

    public BigDecimal getSumInteres() {
        return sumInteres;
    }

    public void setSumInteres(BigDecimal sumInteres) {
        this.sumInteres = sumInteres;
    }

    public BigDecimal getSumPorPagar() {
        return sumPorPagar;
    }

    public void setSumPorPagar(BigDecimal sumPorPagar) {
        this.sumPorPagar = sumPorPagar;
    }
}
