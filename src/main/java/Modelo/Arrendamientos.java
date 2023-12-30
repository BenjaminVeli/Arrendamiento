package Modelo;

import Conexion.CConexion;
import com.mysql.cj.jdbc.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Arrendamientos {

        String nombre;
        String direccion;
        String ruc;
        String telefono;
        String contacto1;
        String contacto2;
        String propietario;
        String direccion_propietario;
        String telefono_propietario;
        int celular;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContacto1() {
        return contacto1;
    }

    public void setContacto1(String contacto1) {
        this.contacto1 = contacto1;
    }

    public String getContacto2() {
        return contacto2;
    }

    public void setContacto2(String contacto2) {
        this.contacto2 = contacto2;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDireccion_propietario() {
        return direccion_propietario;
    }

    public void setDireccion_propietario(String direccion_propietario) {
        this.direccion_propietario = direccion_propietario;
    }

    public String getTelefono_propietario() {
        return telefono_propietario;
    }

    public void setTelefono_propietario(String telefono_propietario) {
        this.telefono_propietario = telefono_propietario;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    
    public void InsertarCliente(JTextField paramNombre, JTextField paramDireccion, JTextField paramRuc, JTextField paramTelefono, JTextField paramContacto1, JTextField paramContacto2, JTextField paramPropietario, JTextField paramDireccion_propietario, JTextField paramTelefono_propietario, JTextField paramCelular ) {
            setNombre(paramNombre.getText());
            setDireccion(paramDireccion.getText());
            setRuc(paramRuc.getText());
            setTelefono(paramTelefono.getText());
            setContacto1(paramContacto1.getText());
            setContacto2(paramContacto2.getText());
            setPropietario(paramPropietario.getText());
            setDireccion_propietario(paramDireccion_propietario.getText());
            setTelefono_propietario(paramTelefono_propietario.getText());
            setCelular(Integer.parseInt(paramCelular.getText()));

        CConexion objetoConexion = new CConexion();

        String consulta = "insert into datos_cli_prov (nombre, direccion, ruc, telefono, contacto1, contacto2, propietario, direccion_propietario, telefono_propietario, celular) values (?,?,?,?,?,?,?,?,?,?)"; 

        try {

            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

                    cs.setString(1, getNombre());
                    cs.setString(2, getDireccion());
                    cs.setString(3, getRuc());
                    cs.setString(4, getTelefono());
                    cs.setString(5, getContacto1());
                    cs.setString(6, getContacto2());
                    cs.setString(7, getPropietario());
                    cs.setString(8, getDireccion_propietario());
                    cs.setString(9, getTelefono_propietario());
                    cs.setInt(10, getCelular());
            

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
        }
    }
    
}
