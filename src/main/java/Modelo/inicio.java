package Modelo;

import Modelo.Arrendamientos;
import com.mycompany.arrendamientos.Login;


public class inicio {

    public static void main(String[] args) {
        Arrendamientos objetoFormulario = new Arrendamientos();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login formularioClientes = new Login();
                formularioClientes.setVisible(true);
            }
        });
    }
}