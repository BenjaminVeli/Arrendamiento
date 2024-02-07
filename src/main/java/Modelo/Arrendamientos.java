package Modelo;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.Timestamp;
import javax.swing.JTextField;

public class Arrendamientos {
        
        int codigo;
        String nombre;
        String ruc;
        String propietario;
        String direccion_propietario;
        int celular;
        Date nacimiento;
        String dni_propietario;
        Timestamp fechaIngreso;
        String correo;
        String estado_civil;
        String conyuge;
        String dni_conyuge;
        String ciudad;
        
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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
    
}