package DAO;

import Conexion.CConexion;
import Modelo.Arrendamientos;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ArrendamientosDAO {
    public void InsertarCliente(Arrendamientos arrendamientos, JTextField paramNombre, JTextField paramDireccion, JTextField paramRuc, JTextField paramTelefono, JTextField paramContacto1, JTextField paramContacto2, JTextField paramPropietario, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramTelefono_propietario, JTextField paramCelular, JDateChooser txtNacimiento, JTextField paramDni_propietario, JTextField paramCorreo, JTextField paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad) {
        arrendamientos.setNombre(paramNombre.getText());
        arrendamientos.setDireccion(paramDireccion.getText());
        arrendamientos.setRuc(paramRuc.getText());
        arrendamientos.setTelefono(paramTelefono.getText());
        arrendamientos.setContacto1(paramContacto1.getText());
        arrendamientos.setContacto2(paramContacto2.getText());
        arrendamientos.setPropietario(paramPropietario.getText());
        arrendamientos.setDireccion_propietario(paramDireccion_propietario.getText());
        arrendamientos.setTelefono_propietario(paramTelefono_propietario.getText());
        arrendamientos.setCelular(Integer.parseInt(paramCelular.getText()));
        arrendamientos.setNacimiento(new Date(paramNacimiento.getDate().getTime()));
        arrendamientos.setDni_propietario(paramDni_propietario.getText());
        arrendamientos.setCorreo(paramCorreo.getText());
        arrendamientos.setEstado_civil(paramEstado_civil.getText());
        arrendamientos.setConyuge(paramConyuge.getText());
        arrendamientos.setDni_conyuge(paramDni_conyuge.getText());
        arrendamientos.setCiudad(paramCiudad.getText());
        java.util.Date fechaIngresoUtil = paramFecha_ingreso.getDate();
        arrendamientos.setFechaIngreso(new Timestamp(fechaIngresoUtil.getTime()));

        CConexion objetoConexion = new CConexion();
        String consulta = "insert into datos_cli_prov (nombre, direccion, ruc, telefono, contacto1, contacto2, propietario, direccion_propietario, telefono_propietario, celular, nacimiento, dni_propietario, fecha_ingreso, correo, estado_civil, conyuge, dni_conyuge, ciudad) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";    
        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, arrendamientos.getNombre());
            cs.setString(2, arrendamientos.getDireccion());
            cs.setString(3, arrendamientos.getRuc());
            cs.setString(4, arrendamientos.getTelefono());
            cs.setString(5, arrendamientos.getContacto1());
            cs.setString(6, arrendamientos.getContacto2());
            cs.setString(7, arrendamientos.getPropietario());
            cs.setString(8, arrendamientos.getDireccion_propietario());
            cs.setString(9, arrendamientos.getTelefono_propietario());
            cs.setInt(10, arrendamientos.getCelular());
            cs.setDate( 11, arrendamientos.getNacimiento());
            cs.setString(12, arrendamientos.getDni_propietario());
            cs.setTimestamp(13, arrendamientos.getFechaIngreso());
            cs.setString(14, arrendamientos.getCorreo());
            cs.setString(15, arrendamientos.getEstado_civil());
            cs.setString(16, arrendamientos.getConyuge());
            cs.setString(17, arrendamientos.getDni_conyuge());
            cs.setString(18, arrendamientos.getCiudad());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
        }
    }
}
