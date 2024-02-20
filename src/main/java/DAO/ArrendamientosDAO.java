package DAO;

import Conexion.CConexion;
import Modelo.Arrendamientos;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ArrendamientosDAO {
    public void InsertarCliente(JTextField paramNombre, JTextField paramRuc, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramCelular, JDateChooser txtNacimiento1, JTextField paramDni_propietario, JTextField paramCorreo, JComboBox<String> paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad, JTextField paramCelularConyuge, JTextField paramProvincia, JTextField paramDepartamento, JTextField paramDistrito) {
    CConexion objetoConexion = new CConexion();
    String consulta = "INSERT INTO datos_cli_prov (nombre, ruc, direccion_propietario, celular, nacimiento, dni_propietario, fecha_ingreso, correo, estado_civil, conyuge, dni_conyuge, ciudad, celular_conyuge, provincia, departamento, distrito) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

        cs.setString(1, paramNombre.getText());
        if (!paramRuc.getText().isEmpty()) {
            cs.setString(2, paramRuc.getText());
        } else {
            cs.setNull(2, java.sql.Types.VARCHAR);
        }
        cs.setString(3, paramDireccion_propietario.getText());
        cs.setInt(4, Integer.parseInt(paramCelular.getText()));
        cs.setDate(5, new java.sql.Date(paramNacimiento.getDate().getTime()));
        if (!paramDni_propietario.getText().isEmpty()) {
            cs.setString(6, paramDni_propietario.getText());
        } else {
            cs.setNull(6, java.sql.Types.VARCHAR);
        }
        cs.setTimestamp(7, new java.sql.Timestamp(paramFecha_ingreso.getDate().getTime()));
        cs.setString(8, paramCorreo.getText());
        cs.setString(9, (String) paramEstado_civil.getSelectedItem());
        cs.setString(10, paramConyuge.getText());
        if (!paramDni_conyuge.getText().isEmpty()) {
            cs.setString(11, paramDni_conyuge.getText());
        } else {
            cs.setNull(11, java.sql.Types.VARCHAR);
        }
        cs.setString(12, paramCiudad.getText());
        cs.setInt(13, Integer.parseInt(paramCelularConyuge.getText()));
        cs.setString(14, paramProvincia.getText());
        cs.setString(15, paramDepartamento.getText());
        cs.setString(16, paramDistrito.getText());

        cs.execute();
        JOptionPane.showMessageDialog(null, "Se insertó correctamente");
    } catch (SQLIntegrityConstraintViolationException e) {
        JOptionPane.showMessageDialog(null, "El DNI o RUC ya están ingresados", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
    }
}



    
    public void MostrarCliente(JTable paramTablaTotalClientes) {
    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    paramTablaTotalClientes.setRowSorter(ordenarTabla);

    String[] columnasMostradas = {"Id", "Nombre", "Dirección", "Ruc", "Celular"};
    String[] columnasBD = {"id", "nombre",  "direccion_propietario", "ruc",  "celular", "nacimiento", "dni_propietario", "fecha_ingreso", "correo", "estado_civil", "conyuge", "dni_conyuge", "ciudad", "celular_conyuge", "provincia", "departamento", "distrito"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    paramTablaTotalClientes.setModel(modelo);

    String sql = "SELECT * FROM datos_cli_prov";
    Statement st;

    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] datos = new String[columnasBD.length];

            for (int i = 0; i < columnasBD.length; i++) {
                datos[i] = rs.getString(columnasBD[i]);
            }

            modelo.addRow(datos);
        }

        paramTablaTotalClientes.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}

    public void SeleccionarCliente(JTable paramTablaClientes, JTextField paramId, JTextField paramNombre, JTextField paramRuc, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramCelular, JDateChooser txtNacimiento1, JTextField paramDni_propietario, JTextField paramCorreo, JComboBox<String> paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad, JTextField paramCelularConyuge, JTextField paramProvincia, JTextField paramDepartamento, JTextField paramDistrito) {
    try {
        int fila = paramTablaClientes.getSelectedRow();
        if (fila >= 0) {
            // Obtén el ID de la fila seleccionada
            String idSeleccionado = paramTablaClientes.getValueAt(fila, 0).toString();

            // Recupera toda la información de la base de datos para el ID seleccionado
            String sql = "SELECT * FROM datos_cli_prov WHERE id = " + idSeleccionado;
            CConexion objetoConexion = new CConexion();
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                paramId.setText(rs.getString("id"));
                paramNombre.setText(rs.getString("nombre"));
                paramRuc.setText(rs.getString("ruc"));
                paramDireccion_propietario.setText(rs.getString("direccion_propietario"));
                paramNacimiento.setDate(rs.getDate("nacimiento"));
                Timestamp fechaIngreso = rs.getTimestamp("fecha_ingreso");
                if (fechaIngreso != null) {
                    paramFecha_ingreso.setDate(fechaIngreso);
                }
                paramCelular.setText(rs.getString("celular"));
                paramDni_propietario.setText(rs.getString("dni_propietario"));
                paramCorreo.setText(rs.getString("correo"));
                String estadoCivil = rs.getString("estado_civil");
                int index = -1;
                
                for (int i = 0; i < paramEstado_civil.getItemCount(); i++) {
                    if (paramEstado_civil.getItemAt(i).equals(estadoCivil)) {
                        index = i;
                        break;
                    }
                }
                paramEstado_civil.setSelectedIndex(index);
                paramConyuge.setText(rs.getString("conyuge"));
                paramDni_conyuge.setText(rs.getString("dni_conyuge"));
                paramCiudad.setText(rs.getString("ciudad"));
                paramCelularConyuge.setText(rs.getString("celular_conyuge"));
                paramProvincia.setText(rs.getString("provincia"));
                paramDepartamento.setText(rs.getString("departamento"));
                paramDistrito.setText(rs.getString("distrito"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}

    public void ModificarCliente(JTextField paramCodigo, JTextField paramNombre, JTextField paramRuc, JTextField paramDireccion_propietario, JDateChooser paramNacimiento, JDateChooser paramFecha_ingreso, JTextField paramCelular, JDateChooser txtNacimiento1, JTextField paramDni_propietario, JTextField paramCorreo, JComboBox<String> paramEstado_civil, JTextField paramConyuge, JTextField paramDni_conyuge, JTextField paramCiudad, JTextField paramCelularConyuge, JTextField paramProvincia, JTextField paramDepartamento, JTextField paramDistrito) {
    Arrendamientos arrendamientos = new Arrendamientos();
    arrendamientos.setCodigo(Integer.parseInt(paramCodigo.getText()));
    arrendamientos.setNombre(paramNombre.getText());
    arrendamientos.setRuc(paramRuc.getText());
    arrendamientos.setDireccion_propietario(paramDireccion_propietario.getText());
    arrendamientos.setCelular(Integer.parseInt(paramCelular.getText()));
    arrendamientos.setNacimiento(new java.sql.Date(paramNacimiento.getDate().getTime()));
    arrendamientos.setDni_propietario(paramDni_propietario.getText());
    arrendamientos.setCorreo(paramCorreo.getText());
    arrendamientos.setEstado_civil((String) paramEstado_civil.getSelectedItem());
    arrendamientos.setConyuge(paramConyuge.getText());
    arrendamientos.setDni_conyuge(paramDni_conyuge.getText());
    arrendamientos.setCiudad(paramCiudad.getText());
    java.util.Date fechaIngresoUtil = paramFecha_ingreso.getDate();
    arrendamientos.setFechaIngreso(new Timestamp(fechaIngresoUtil.getTime()));
    arrendamientos.setCelular_conyuge(Integer.parseInt(paramCelularConyuge.getText()));
    arrendamientos.setProvincia(paramProvincia.getText());
    arrendamientos.setDepartamento(paramDepartamento.getText());
    arrendamientos.setDistrito(paramDistrito.getText());

    CConexion objetoConexion = new CConexion();

    String consulta = "UPDATE datos_cli_prov SET nombre=?,  ruc=?,  direccion_propietario=?,  celular=?, nacimiento=?, dni_propietario=?, fecha_ingreso=?, correo=?, estado_civil=?, conyuge=?, dni_conyuge=?, ciudad=?, celular_conyuge=?, provincia=?, departamento=?, distrito=? WHERE id=?";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

        cs.setString(1, arrendamientos.getNombre());
        if (!paramRuc.getText().isEmpty()) {
            cs.setString(2, arrendamientos.getRuc());
        } else {
            cs.setNull(2, java.sql.Types.VARCHAR);
        }
        cs.setString(3, arrendamientos.getDireccion_propietario());
        cs.setInt(4, arrendamientos.getCelular());
        cs.setDate(5, arrendamientos.getNacimiento());
        if (!paramDni_propietario.getText().isEmpty()) {
            cs.setString(6, arrendamientos.getDni_propietario());
        } else {
            cs.setNull(6, java.sql.Types.VARCHAR);
        }
        cs.setTimestamp(7, arrendamientos.getFechaIngreso());
        cs.setString(8, arrendamientos.getCorreo());
        cs.setString(9, arrendamientos.getEstado_civil());
        cs.setString(10, arrendamientos.getConyuge());
        if (!paramDni_conyuge.getText().isEmpty()) {
            cs.setString(11, arrendamientos.getDni_conyuge());
        } else {
            cs.setNull(11, java.sql.Types.VARCHAR);
        }
        cs.setString(12, arrendamientos.getCiudad());
        cs.setInt(13, arrendamientos.getCelular_conyuge());
        cs.setString(14, arrendamientos.getProvincia());
        cs.setString(15, arrendamientos.getDepartamento());
        cs.setString(16, arrendamientos.getDistrito());
        cs.setInt(17, arrendamientos.getCodigo());

        int filasAfectadas = cs.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, el cliente con el ID proporcionado no existe.");
        }

    } catch (SQLIntegrityConstraintViolationException e) {
        JOptionPane.showMessageDialog(null, "No es posible modificar al cliente, ya que el DNI o RUC ingresado ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
    }
}



    
    public void EliminarClientes(JTextField paramCodigo) {
        Arrendamientos arrendamientos = new Arrendamientos();
        arrendamientos.setCodigo(Integer.parseInt(paramCodigo.getText()));

        CConexion objetoConexion = new CConexion();
        
        try {
            // Obtener los cuartos asociados al cliente_proveedor a eliminar
            String consultaCuarto = "SELECT floor_id, room_id FROM rent_calculation WHERE client_id=?";
            java.sql.CallableStatement csCuarto = objetoConexion.estableceConexion().prepareCall(consultaCuarto);
            csCuarto.setInt(1, arrendamientos.getCodigo());
            ResultSet rsCuarto = csCuarto.executeQuery();
            
            boolean clienteTieneCuartos = false;

            while (rsCuarto.next()) {
                clienteTieneCuartos = true;
                int floorId = rsCuarto.getInt("floor_id");
                int roomId = rsCuarto.getInt("room_id");

                // Eliminar el cliente_proveedor
                String consultaEliminarCliente = "DELETE FROM datos_cli_prov WHERE id=?";
                java.sql.CallableStatement csEliminarCliente = objetoConexion.estableceConexion().prepareCall(consultaEliminarCliente);
                csEliminarCliente.setInt(1, arrendamientos.getCodigo());
                csEliminarCliente.execute();

                // Marcar el cuarto como desocupado
                String consultaActualizarCuarto = "UPDATE cuarto SET ocupado=false WHERE id=?";
                java.sql.CallableStatement csActualizarCuarto = objetoConexion.estableceConexion().prepareCall(consultaActualizarCuarto);
                csActualizarCuarto.setInt(1, roomId);
                csActualizarCuarto.execute();
            }
            
            if (!clienteTieneCuartos) {
                // Si el cliente no tiene cuartos, eliminar directamente
                String consultaEliminarCliente = "DELETE FROM datos_cli_prov WHERE id=?";
                java.sql.CallableStatement csEliminarCliente = objetoConexion.estableceConexion().prepareCall(consultaEliminarCliente);
                csEliminarCliente.setInt(1, arrendamientos.getCodigo());
                csEliminarCliente.execute();
            }
                
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el cliente y se marcaron los cuartos como desocupados");
                
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente, error: " + e.toString());
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
    modelo.addColumn("Celular");


    paramTablaTotalClientes.setModel(modelo);

    if (!searchText.isEmpty()) {
        sql = "SELECT * FROM datos_cli_prov WHERE nombre LIKE '%" + searchText + "%' OR direccion_propietario LIKE '%" + searchText + "%' OR ruc LIKE '%" + searchText + "%' OR celular LIKE '%" + searchText + "%'";
    } else {
        sql = "SELECT * FROM datos_cli_prov";
    }

    String[] datos = new String[5];
    Statement st;

    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            datos[0] = rs.getString(1); 
            datos[1] = rs.getString(2); 
            datos[2] = rs.getString(4); 
            datos[3] = rs.getString(3); 
            datos[4] = rs.getString(5); 


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
