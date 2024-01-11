package DAO;

import Conexion.CConexion;
import Modelo.Piso;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class PisoDAO {
    public void InsertarPiso(JTextField paramPiso) {
        Piso piso = new Piso();
        piso.setPiso(paramPiso.getText());

        CConexion objetoConexion = new CConexion();
        String consulta = "insert into piso (piso) values (?)";    
        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, piso.getPiso());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
        }
    }
    
    public void MostrarPiso(JTable paramTablaTotalPisos) {

    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    paramTablaTotalPisos.setRowSorter(ordenarTabla);

    String[] columnasMostradas = {"Id", "Piso"};
    String[] columnasBD = {"id", "piso"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    paramTablaTotalPisos.setModel(modelo);

    String sql = "select * from piso";
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

        paramTablaTotalPisos.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}
    
    public void SeleccionarPiso(JTable paramTablaPisos, JTextField paramId, JTextField paramPiso) {
    try {
        int fila = paramTablaPisos.getSelectedRow();
        if (fila >= 0) {
            String idSeleccionado = paramTablaPisos.getValueAt(fila, 0).toString();

            String sql = "SELECT * FROM piso WHERE id = " + idSeleccionado;
            CConexion objetoConexion = new CConexion();
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {

                paramId.setText(rs.getString("id"));
                paramPiso.setText(rs.getString("piso"));
               
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}
    
    public void ModificarPiso(JTextField paramCodigo, JTextField paramPiso) {
    Piso piso = new Piso();
    piso.setCodigo(Integer.parseInt(paramCodigo.getText()));
    piso.setPiso(paramPiso.getText());

    CConexion objetoConexion = new CConexion();

    String consulta = "UPDATE piso SET piso=? WHERE id=?";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

        cs.setString(1, piso.getPiso());
        cs.setInt(2, piso.getCodigo());

        int filasAfectadas = cs.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, el piso con el ID proporcionado no existe.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
    }
}
    
    public void EliminarPiso(JTextField paramCodigo) {
    Piso piso = new Piso();
    piso.setCodigo(Integer.parseInt(paramCodigo.getText()));

    CConexion objetoConexion = new CConexion(); 

    String consulta = "DELETE FROM piso WHERE piso.id=?";

    try {
        java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setInt(1, piso.getCodigo());
        cs.execute();

        JOptionPane.showMessageDialog(null, "Se eliminó correctamente el piso");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el piso, error: " + e.toString());
    }
}
    
}
