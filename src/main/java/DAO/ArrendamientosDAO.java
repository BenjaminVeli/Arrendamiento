package DAO;

import Conexion.CConexion;
import Modelo.Arrendamientos;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    
    public void MostrarCliente(JTable paramTablaTotalClientes) {
    
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalClientes.setRowSorter(OrdenarTabla);
        
        String sql = "";
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dirección");
        modelo.addColumn("Ruc");
        modelo.addColumn("Telefono");
        modelo.addColumn("Contacto1");
        modelo.addColumn("Contacto2");
        modelo.addColumn("propietario");
        modelo.addColumn("direccion_propietario");
        modelo.addColumn("telefono_propietario");
        modelo.addColumn("celular");
        modelo.addColumn("nacimiento");
        modelo.addColumn("dni_propietario");
        modelo.addColumn("fecha_ingreso");
        modelo.addColumn("correo");
        modelo.addColumn("estado_civil");
        modelo.addColumn("conyuge");
        modelo.addColumn("dni_conyuge");
        modelo.addColumn("ciudad");
        

        paramTablaTotalClientes.setModel(modelo);

        sql = "select * from datos_cli_prov";

        String[] datos = new String[19];
        Statement st;

        try {

            st = objetoConexion.estableceConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(4);
                    datos[5] = rs.getString(6);
                    datos[6] = rs.getString(7);
                    datos[7] = rs.getString(8);
                    datos[8] = rs.getString(9);
                    datos[9] = rs.getString(10);
                    datos[10] = rs.getString(11);
                    datos[11] = rs.getString(12);
                    datos[12] = rs.getString(13);
                    datos[13] = rs.getString(14);
                    datos[14] = rs.getString(15);
                    datos[15] = rs.getString(16);
                    datos[16] = rs.getString(17);
                    datos[17] = rs.getString(18);
                    datos[18] = rs.getString(19);

                modelo.addRow(datos);
            }

            paramTablaTotalClientes.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error :  " + e.toString());

        }

}
    
    public void SeleccionarCliente(JTable paramTablaClientes, JTextField paramId, JTextField paramNombre, JTextField paramDireccion, JTextField paramRuc, JTextField paramTelefono, JTextField paramContacto1, JTextField paramContacto2, JTextField paramPropietario, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramTelefono_propietario, JTextField paramCelular, JDateChooser txtNacimiento, JTextField paramDni_propietario, JTextField paramCorreo, JTextField paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad) {
    try {
        int fila = paramTablaClientes.getSelectedRow();
        if (fila >= 0) {
            paramId.setText(paramTablaClientes.getValueAt(fila, 0).toString());
            paramNombre.setText(paramTablaClientes.getValueAt(fila, 1).toString());
            paramDireccion.setText(paramTablaClientes.getValueAt(fila, 2).toString());
            paramRuc.setText(paramTablaClientes.getValueAt(fila, 3).toString());
            paramTelefono.setText(paramTablaClientes.getValueAt(fila, 4).toString());
            paramContacto1.setText(paramTablaClientes.getValueAt(fila, 5).toString());
            paramContacto2.setText(paramTablaClientes.getValueAt(fila, 6).toString());
            paramPropietario.setText(paramTablaClientes.getValueAt(fila, 7).toString());
            paramDireccion_propietario.setText(paramTablaClientes.getValueAt(fila, 8).toString());
            paramTelefono_propietario.setText(paramTablaClientes.getValueAt(fila, 9).toString());
            paramCelular.setText(paramTablaClientes.getValueAt(fila, 10).toString());
            String nacimientoString = paramTablaClientes.getValueAt(fila, 11).toString();
            java.util.Date nacimientoDate = new SimpleDateFormat("yyyy-MM-dd").parse(nacimientoString);
            paramNacimiento.setDate(nacimientoDate);
            paramDni_propietario.setText(paramTablaClientes.getValueAt(fila, 12).toString());
            String fechaIngresoString = paramTablaClientes.getValueAt(fila, 13).toString();
            java.util.Date fechaIngresoDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIngresoString);
            paramFecha_ingreso.setDate(fechaIngresoDate);
            paramCorreo.setText(paramTablaClientes.getValueAt(fila, 14).toString());
            paramEstado_civil.setText(paramTablaClientes.getValueAt(fila, 15).toString());
            paramConyuge.setText(paramTablaClientes.getValueAt(fila, 16).toString());
            paramDni_conyuge.setText(paramTablaClientes.getValueAt(fila, 17).toString());
            paramCiudad.setText(paramTablaClientes.getValueAt(fila, 18).toString());

        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}
    
    public void ModificarCliente(JTextField paramCodigo, JTextField paramNombre, JTextField paramDireccion, JTextField paramRuc, JTextField paramTelefono, JTextField paramContacto1, JTextField paramContacto2, JTextField paramPropietario, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramTelefono_propietario, JTextField paramCelular, JDateChooser txtNacimiento, JTextField paramDni_propietario, JTextField paramCorreo, JTextField paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad) {
    Arrendamientos arrendamientos = new Arrendamientos();
    arrendamientos.setCodigo(Integer.parseInt(paramCodigo.getText()));
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
    arrendamientos.setNacimiento(new java.sql.Date(paramNacimiento.getDate().getTime()));
    arrendamientos.setDni_propietario(paramDni_propietario.getText());
    arrendamientos.setCorreo(paramCorreo.getText());
    arrendamientos.setEstado_civil(paramEstado_civil.getText());
    arrendamientos.setConyuge(paramConyuge.getText());
    arrendamientos.setDni_conyuge(paramDni_conyuge.getText());
    arrendamientos.setCiudad(paramCiudad.getText());
    java.util.Date fechaIngresoUtil = paramFecha_ingreso.getDate();
    arrendamientos.setFechaIngreso(new Timestamp(fechaIngresoUtil.getTime()));



    CConexion objetoConexion = new CConexion();

    String consulta = "UPDATE datos_cli_prov SET nombre=?, direccion=?, ruc=?, telefono=?, contacto1=?, contacto2=?, propietario=?, direccion_propietario=?, telefono_propietario=?, celular=?, nacimiento=?, dni_propietario=?, fecha_ingreso=?, correo=?, estado_civil=?, conyuge=?, dni_conyuge=?, ciudad=? WHERE id=?";

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
        cs.setDate(11, arrendamientos.getNacimiento());
        cs.setString(12, arrendamientos.getDni_propietario());
        cs.setTimestamp(13, arrendamientos.getFechaIngreso());
        cs.setString(14, arrendamientos.getCorreo());
        cs.setString(15, arrendamientos.getEstado_civil());
        cs.setString(16, arrendamientos.getConyuge());
        cs.setString(17, arrendamientos.getDni_conyuge());
        cs.setString(18, arrendamientos.getCiudad());
        cs.setInt(19, arrendamientos.getCodigo());

        int filasAfectadas = cs.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, el cliente con el ID proporcionado no existe.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
    }
}
    
   public void EliminarClientes(JTextField paramCodigo) {
    Arrendamientos arrendamientos = new Arrendamientos();
    arrendamientos.setCodigo(Integer.parseInt(paramCodigo.getText()));

    CConexion objetoConexion = new CConexion(); 

    String consulta = "DELETE FROM datos_cli_prov WHERE datos_cli_prov.id=?";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setInt(1, arrendamientos.getCodigo());
        cs.execute();

        JOptionPane.showMessageDialog(null, "Se eliminó correctamente el producto");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto, error: " + e.toString());
    }
}
   
   public void FiltrarClientes(JTable paramTablaTotalClientes, String searchText) {
    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
    paramTablaTotalClientes.setRowSorter(OrdenarTabla);

    String sql = "";
    modelo.addColumn("Id");
    modelo.addColumn("Nombre");
    modelo.addColumn("Dirección");
    modelo.addColumn("Ruc");
    modelo.addColumn("Telefono");
    modelo.addColumn("Contacto1");
    modelo.addColumn("Contacto2");
    modelo.addColumn("propietario");
    modelo.addColumn("direccion_propietario");
    modelo.addColumn("telefono_propietario");
    modelo.addColumn("celular");
    modelo.addColumn("nacimiento");
    modelo.addColumn("dni_propietario");
    modelo.addColumn("fecha_ingreso");
    modelo.addColumn("correo");
    modelo.addColumn("estado_civil");
    modelo.addColumn("conyuge");
    modelo.addColumn("dni_conyuge");
    modelo.addColumn("ciudad");

    paramTablaTotalClientes.setModel(modelo);

    if (!searchText.isEmpty()) {
        sql = "SELECT * FROM datos_cli_prov WHERE nombre LIKE '%" + searchText + "%' OR direccion LIKE '%" + searchText + "%' OR ruc LIKE '%" + searchText + "%' OR telefono LIKE '%" + searchText + "%' OR contacto1 LIKE '%" + searchText + "%' OR contacto2 LIKE '%" + searchText + "%' OR propietario LIKE '%" + searchText + "%' OR direccion_propietario LIKE '%" + searchText + "%' OR telefono_propietario LIKE '%" + searchText + "%' OR celular LIKE '%" + searchText + "%' OR nacimiento LIKE '%" + searchText + "%' OR dni_propietario LIKE '%" + searchText + "%' OR fecha_ingreso LIKE '%" + searchText + "%' OR correo LIKE '%" + searchText + "%' OR estado_civil LIKE '%" + searchText + "%' OR conyuge LIKE '%" + searchText + "%' OR dni_conyuge LIKE '%" + searchText + "%' OR ciudad LIKE '%" + searchText + "%'";
    } else {
        sql = "SELECT * FROM datos_cli_prov";
    }

    String[] datos = new String[19];
    Statement st;

    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            datos[3] = rs.getString(4);
            datos[4] = rs.getString(5);
            datos[5] = rs.getString(6);
            datos[6] = rs.getString(7);
            datos[7] = rs.getString(8);
            datos[8] = rs.getString(9);
            datos[9] = rs.getString(10);
            datos[10] = rs.getString(11);
            datos[11] = rs.getString(12);
            datos[12] = rs.getString(13);
            datos[13] = rs.getString(14);
            datos[14] = rs.getString(15);
            datos[15] = rs.getString(16);
            datos[16] = rs.getString(17);
            datos[17] = rs.getString(18);
            datos[18] = rs.getString(19);

            modelo.addRow(datos);
        }

        paramTablaTotalClientes.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error :  " + e.toString());
    }
}
   
       public ArrayList<String> obtenerNombresClientes() {
        ArrayList<String> nombres = new ArrayList<>();
        CConexion objetoConexion = new CConexion();
        String sql = "SELECT nombre FROM datos_cli_prov";

        try {
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener los nombres de los clientes, error :  " + e.toString());
        }

        return nombres;
    }
}
