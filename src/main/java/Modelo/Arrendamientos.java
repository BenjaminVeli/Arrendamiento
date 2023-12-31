package Modelo;

import Conexion.CConexion;
import com.mysql.cj.jdbc.CallableStatement;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.Timestamp;
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
        Date nacimiento;
        String dni_propietario;
        Timestamp fechaIngreso;
        String correo;
        String estado_civil;
        String conyuge;
        String dni_conyuge;
        String ciudad;

    
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

        public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    public String getDni_propietario() {
        return dni_propietario;
    }

    public void setDni_propietario(String dni_propietario) {
        this.dni_propietario = dni_propietario;
    }
    
    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getConyuge() {
        return conyuge;
    }

    public void setConyuge(String conyuge) {
        this.conyuge = conyuge;
    }

    public String getDni_conyuge() {
        return dni_conyuge;
    }

    public void setDni_conyuge(String dni_conyuge) {
        this.dni_conyuge = dni_conyuge;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    

    

    
    public void InsertarCliente(JTextField paramNombre, JTextField paramDireccion, JTextField paramRuc, JTextField paramTelefono, JTextField paramContacto1, JTextField paramContacto2, JTextField paramPropietario, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramTelefono_propietario, JTextField paramCelular, JDateChooser txtNacimiento, JTextField paramDni_propietario, JTextField paramCorreo, JTextField paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad) {
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
            setNacimiento(new java.sql.Date(paramNacimiento.getDate().getTime()));
            setDni_propietario(paramDni_propietario.getText());
            setCorreo(paramCorreo.getText());
            setEstado_civil(paramEstado_civil.getText());
            setConyuge(paramConyuge.getText());
            setDni_conyuge(paramDni_conyuge.getText());
            setCiudad(paramCiudad.getText());
            java.util.Date fechaIngresoUtil = paramFecha_ingreso.getDate();
            setFechaIngreso(new Timestamp(fechaIngresoUtil.getTime()));





        CConexion objetoConexion = new CConexion();

         String consulta = "insert into datos_cli_prov (nombre, direccion, ruc, telefono, contacto1, contacto2, propietario, direccion_propietario, telefono_propietario, celular, nacimiento, dni_propietario, fecha_ingreso, correo, estado_civil, conyuge, dni_conyuge, ciudad) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";    

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
                    cs.setDate( 11, getNacimiento());
                    cs.setString(12, getDni_propietario());
                    cs.setTimestamp(13, getFechaIngreso());
                    cs.setString(14, getCorreo());
                    cs.setString(15, getEstado_civil());
                    cs.setString(16, getConyuge());
                    cs.setString(17, getDni_conyuge());
                    cs.setString(18, getCiudad());


            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
        }
    }
    
}