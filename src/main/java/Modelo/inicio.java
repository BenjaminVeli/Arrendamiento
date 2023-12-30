package Modelo;

import Modelo.Arrendamientos;
import com.mycompany.arrendamientos.RegistroDatosClientes;
import com.mycompany.arrendamientos.RegistroDatosClientes;


public class inicio {

    public static void main(String[] args) {
        Arrendamientos objetoFormulario = new Arrendamientos();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistroDatosClientes formularioClientes = new RegistroDatosClientes();
                formularioClientes.setVisible(true);
            }
        });
    }
}