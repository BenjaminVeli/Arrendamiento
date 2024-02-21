
package Modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class PagoAlquiler {
    CalcularAlquiler cliente;
    
    public PagoAlquiler(){
        this.cliente = new CalcularAlquiler();
    }

    public CalcularAlquiler getCliente() {
        return cliente;
    }

    public void setCliente(CalcularAlquiler cliente) {
        this.cliente = cliente;
    }
}
