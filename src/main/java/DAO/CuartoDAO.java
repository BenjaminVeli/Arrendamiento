package DAO;

import Conexion.CConexion;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CuartoDAO {
         
         int idPiso;

         public void establecerIdPiso(int idPiso) {
            this.idPiso = idPiso;
         }

         public void MostrarPisoCombo(JComboBox comboPiso){
            CConexion objetoConexion = new CConexion();
            String sql = "SELECT * FROM piso";
            Statement st;

            try {
                st = objetoConexion.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                comboPiso.removeAllItems();

                while (rs.next()) {
                    String nombrePiso = rs.getString("piso");
                    this.establecerIdPiso(rs.getInt("id")); 

                    comboPiso.addItem(nombrePiso);
                    comboPiso.putClientProperty(nombrePiso, sql);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar piso: " + e.toString());
            } 
        }
        
         public void InsertarCuarto(JComboBox comboPiso, JTextField numcuarto, JTextField metraje, File foto) {
    CConexion objetoConexion = new CConexion();

    String consulta = "INSERT INTO cuarto (piso_id, numcuarto, metraje, foto) VALUES (?, ?, ?, ?)";

    try {
        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

        int selectedIndex = comboPiso.getSelectedIndex();

        if (selectedIndex != -1) {
            int idPiso = selectedIndex + 1; 
            cs.setInt(1, idPiso);
            cs.setString(2, numcuarto.getText());
            cs.setString(3, metraje.getText());
            
            if (foto != null) {
                FileInputStream fis = new FileInputStream(foto);
                cs.setBinaryStream(4, fis, (int)foto.length());
            } else {
                cs.setNull(4, Types.BLOB); 
            }
            
            cs.execute();

            JOptionPane.showMessageDialog(null, "Cuarto insertado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un piso antes de insertar el cuarto");
        }

    } catch (SQLIntegrityConstraintViolationException e) {
        JOptionPane.showMessageDialog(null, "El cuarto ingresado ya está registrado. ", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se insertó correctamente, error: " + e.toString());
    }
}

         
         public void MostrarCuartos(JTable tbTotalCuartos) {
        CConexion objetoConexion = new CConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "";
        modelo.addColumn("id");
        modelo.addColumn("Piso");
        modelo.addColumn("Cuarto");
        modelo.addColumn("Metraje");
        modelo.addColumn("Foto");

        tbTotalCuartos.setModel(modelo);

        sql = "SELECT cuarto.id, piso.piso as nombre_piso, numcuarto, metraje , foto FROM cuarto INNER JOIN piso ON cuarto.piso_id = piso.id";

        try {
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String nombre_piso = rs.getString("nombre_piso");
                String numcuarto = rs.getString("numcuarto");
                String metraje = rs.getString("metraje");

                byte [] imageBytes = rs.getBytes("foto");
                Image foto = null;
                if (imageBytes !=null) {
                    try{
                        ImageIcon imageIcon = new ImageIcon(imageBytes);
                        foto = imageIcon.getImage();
                    } catch (Exception e ) {

                        JOptionPane.showMessageDialog(null,"Error:"+e.toString());

                    }
                }

                modelo.addRow(new Object[]{id, nombre_piso, numcuarto, metraje , foto});
            }

            tbTotalCuartos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar cuartos, error: " + e.toString());
        }
    }

         public void SeleccionarCuartos(JTable tbTotalCuartos, JTextField id, JComboBox comboPiso, JTextField numcuarto, JTextField metraje, JLabel foto){
                  int fila=  tbTotalCuartos.getSelectedRow();
                  
                  if (fila>=0) {
                        id.setText(tbTotalCuartos.getValueAt(fila,0).toString());
                         comboPiso.setSelectedItem(tbTotalCuartos.getValueAt(fila, 1).toString());
                        numcuarto.setText(tbTotalCuartos.getValueAt(fila,2).toString());
                        metraje.setText(tbTotalCuartos.getValueAt(fila,3).toString());
                        Image imagen = (Image) tbTotalCuartos.getValueAt(fila, 4);
                        ImageIcon originalIcon = new ImageIcon(imagen);
                        int lblanchura = foto.getWidth();
                        int lblaltura = foto.getHeight();
                        
                        Image ImagenEscalada= originalIcon.getImage().getScaledInstance(lblanchura, lblaltura, Image.SCALE_SMOOTH);
                        foto.setIcon(new ImageIcon(ImagenEscalada));
                  }
                  
                  try {
                      
                  } catch (Exception e) {
                  JOptionPane.showMessageDialog(null,"Error al seleccionar, error "+ e.toString());
                  }
         }
         
         public void ModificarCuartos(JTable tbTotalCuartos, JTextField id, JComboBox comboPiso, JTextField numcuarto, JTextField metraje, File foto) {
            CConexion objetoConexion = new CConexion();
            String consulta = "UPDATE cuarto SET piso_id=?, numcuarto=?, metraje=?, foto=? WHERE id=?";

            try {
                FileInputStream fis = new FileInputStream(foto);
                CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

                int selectedIndex = comboPiso.getSelectedIndex();

                if (selectedIndex != -1) {
                    int idPiso = selectedIndex + 1;
                    cs.setInt(1, idPiso);
                    cs.setString(2, numcuarto.getText());
                    cs.setString(3, metraje.getText());
                    cs.setBinaryStream(4, fis, (int) foto.length());
                    cs.setInt(5, Integer.parseInt(id.getText()));
                        
                    cs.execute();

                    JOptionPane.showMessageDialog(null, "Se modificó correctamente");
                }
            } catch (SQLIntegrityConstraintViolationException e) {
        JOptionPane.showMessageDialog(null, "No es posible modificar el cuarto, ya que está registrado. ", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se modificó correctamente, error: " + e.toString());
    }
}
         
         public void EliminarCuartos(JTextField id) {
        CConexion objetoConexion = new CConexion();
        String consulta = "DELETE FROM cuarto WHERE id=?";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt(id.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se eliminó correctamente, error: " + e.toString());
        }
    }
         
         
        public void LimpiarCuartos(JTextField id, JTextField numcuarto, JTextField metraje, JTextField rutaimagen, JLabel imagencontenido){
            id.setText("");
            numcuarto.setText("");
            
        }
         
}
