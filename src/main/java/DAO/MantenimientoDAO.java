package DAO;

import Conexion.CConexion;
import Modelo.Mantenimiento;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class MantenimientoDAO {
    public void InsertarMantenimiento(JTextField paramNombre, JTextField paramDireccion, JTextField paramDni, JTextField paramCelular, JComboBox paramRol) {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setNombre(paramNombre.getText());
        mantenimiento.setDireccion(paramDireccion.getText());
        mantenimiento.setDni(paramDni.getText());
        mantenimiento.setCelular(Integer.parseInt(paramCelular.getText()));
        mantenimiento.setRol((String) paramRol.getSelectedItem());

        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO mantenimiento (nombre, direccion, dni, celular, rol) VALUES (?, ?, ?, ?, ?)";
        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, mantenimiento.getNombre());
            cs.setString(2, mantenimiento.getDireccion());
            cs.setString(3, mantenimiento.getDni());
            cs.setInt(4, mantenimiento.getCelular());
            cs.setString(5, mantenimiento.getRol());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente");
        } catch (SQLIntegrityConstraintViolationException e) {
        JOptionPane.showMessageDialog(null, "El DNI ingresado ya esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
    }
}
    
    public void MostrarMantenimiento(JTable paramTablaMantenimiento) {
    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    paramTablaMantenimiento.setRowSorter(ordenarTabla);

    String[] columnasMostradas = {"Id","Nombre", "DNI","rol"};
    String[] columnasBD = {"id", "nombre", "dni","rol", "celular","direccion",};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    paramTablaMantenimiento.setModel(modelo);

    String sql = "SELECT * FROM mantenimiento";
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

        paramTablaMantenimiento.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}
    
    public void SeleccionarMantenimiento(JTable paramTablaMantenimiento, JTextField paramId, JTextField paramNombre, JTextField paramDireccion, JTextField paramDni, JTextField paramCelular, JComboBox paramRol) {
    try {
        int fila = paramTablaMantenimiento.getSelectedRow();
        if (fila >= 0) {
            String idSeleccionado = paramTablaMantenimiento.getValueAt(fila, 0).toString();

            String sql = "SELECT * FROM mantenimiento WHERE id = " + idSeleccionado;
            CConexion objetoConexion = new CConexion();
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                paramId.setText(rs.getString("id"));
                paramNombre.setText(rs.getString("nombre"));
                paramDireccion.setText(rs.getString("direccion"));
                paramDni.setText(rs.getString("dni"));
                paramCelular.setText(rs.getString("celular"));
                
                String rol = rs.getString("rol"); 
                int index = -1;
                
                for (int i = 0; i < paramRol.getItemCount(); i++) {
                    if (paramRol.getItemAt(i).equals(rol)) {
                        index = i;
                        break;
                    }
                }
                paramRol.setSelectedIndex(index);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}


    public void ModificarMantenimiento(JTextField paramCodigo, JTextField paramNombre, JTextField paramDireccion, JTextField paramDni, JTextField paramCelular, JComboBox paramRol) {
    Mantenimiento mantenimiento = new Mantenimiento();
    mantenimiento.setCodigo(Integer.parseInt(paramCodigo.getText()));
    mantenimiento.setNombre(paramNombre.getText());
    mantenimiento.setDireccion(paramDireccion.getText());
    mantenimiento.setDni(paramDni.getText());
    mantenimiento.setCelular(Integer.parseInt(paramCelular.getText()));
    mantenimiento.setRol((String) paramRol.getSelectedItem());

    CConexion objetoConexion = new CConexion();

    String consulta = "UPDATE mantenimiento SET nombre=?, direccion=?, dni=?, celular=?, rol=? WHERE id=?";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

        cs.setString(1, mantenimiento.getNombre());
        cs.setString(2, mantenimiento.getDireccion());
        cs.setString(3, mantenimiento.getDni());
        cs.setInt(4, mantenimiento.getCelular());
        cs.setString(5 , mantenimiento.getRol());
        cs.setInt(6, mantenimiento.getCodigo());

        int filasAfectadas = cs.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, el registro con el ID proporcionado no existe.");
        }

    } catch (SQLIntegrityConstraintViolationException e) {
        JOptionPane.showMessageDialog(null, "No es posible modificar al cliente , ya que el DNI  ya esta registrado.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
    }
}
    
    public void EliminarMantenimiento(JTextField paramCodigo) {
    Mantenimiento mantenimiento = new Mantenimiento();
    mantenimiento.setCodigo(Integer.parseInt(paramCodigo.getText()));

    CConexion objetoConexion = new CConexion(); 

    String consulta = "DELETE FROM mantenimiento WHERE id=?";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setInt(1, mantenimiento.getCodigo());
        cs.execute();

        JOptionPane.showMessageDialog(null, "Se eliminó correctamente el registro de mantenimiento");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro de mantenimiento, error: " + e.toString());
    }
}


}
