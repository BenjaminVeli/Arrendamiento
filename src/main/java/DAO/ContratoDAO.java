
package DAO;

import Conexion.CConexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class ContratoDAO {

    int idArrendador;
    
    public void establecerIdArrendador(int idArrendador) {
        this.idArrendador = idArrendador;
    }
    
    public void MostrarArrendadorCombo(JComboBox comboArrendador, JTextField txtdireccionArrendador, JTextField txtDniArrendador, JTextField txtTeleArrendador) {
    CConexion objetoConexion = new CConexion(); 
    
    String sql = "SELECT * FROM mantenimiento WHERE rol = 'Arrendador'";
    Statement st;
    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);
        comboArrendador.removeAllItems();
        
        while (rs.next()) {
            String nombreArrendador = rs.getString("nombre");
            int idArrendador = rs.getInt("id");
            String direccion = rs.getString("direccion");
            String dni = rs.getString("dni");
            String celular = rs.getString("celular");
            
            comboArrendador.addItem(nombreArrendador);
            comboArrendador.putClientProperty(nombreArrendador, idArrendador);
        }
        
        comboArrendador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) comboArrendador.getSelectedItem();
                if (selectedName != null) {
                    // Realizar consulta para obtener los detalles del arrendador seleccionado
                    String query = "SELECT direccion, dni, celular FROM mantenimiento WHERE nombre = ?";
                    try (PreparedStatement statement = objetoConexion.estableceConexion().prepareStatement(query)) {
                        statement.setString(1, selectedName);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            txtdireccionArrendador.setText(resultSet.getString("direccion"));
                            txtDniArrendador.setText(resultSet.getString("dni"));
                            txtTeleArrendador.setText(resultSet.getString("celular"));
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al obtener detalles del arrendador: " + ex.toString());
                    }
                }
            }
        });
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar al arrendador: " + e.toString());
    }
}

   
    
    int idGarante;
    
    public void establecertIdGarante(int idGarante) {
        this.idGarante = idGarante;
    }
    
    public void MostrarGaranteCombo(JComboBox comboGarante, JTextField txtdireccionGarante, JTextField txtDniGarante, JTextField txtTeleGarante) {
        CConexion objetoConexion = new CConexion(); 

        String sql = "SELECT * FROM mantenimiento WHERE rol = 'Garante'";
        Statement st;
        try {
            st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            comboGarante.removeAllItems();

            while (rs.next()) {
                String nombreGarante = rs.getString("nombre");
                int idGarante = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String dni = rs.getString("dni");
                String celular = rs.getString("celular");

                comboGarante.addItem(nombreGarante);
                comboGarante.putClientProperty(nombreGarante, idGarante);
            }

            comboGarante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedName = (String) comboGarante.getSelectedItem();
                    if (selectedName != null) {
                        // Realizar consulta para obtener los detalles del garante seleccionado
                        String query = "SELECT direccion, dni, celular FROM mantenimiento WHERE nombre = ?";
                        try (PreparedStatement statement = objetoConexion.estableceConexion().prepareStatement(query)) {
                            statement.setString(1, selectedName);
                            ResultSet resultSet = statement.executeQuery();
                            if (resultSet.next()) {
                                txtdireccionGarante.setText(resultSet.getString("direccion"));
                                txtDniGarante.setText(resultSet.getString("dni"));
                                txtTeleGarante.setText(resultSet.getString("celular"));
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error al obtener detalles del garante: " + ex.toString());
                        }
                    }
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar al garante: " + e.toString());
        }
    }


   
    int idArrendatario;
    
    public void establecerIdArrendatario(int idArrendatario) {
        this.idArrendatario = idArrendatario;
    }
    
    public void MostrarArrendatario(JComboBox comboArrendatario, JTextField txtdireccionPropietario, JTextField txtDniPropietario, JTextField txtTelePropietario, JTextField txtMensualidad, JTextField txtFecha, JTextField txtNombrePiso, JTextField txtNombreCuarto, JTextField txtGarantia, JTextField txtMetraje) {
    CConexion objetoConexion = new CConexion(); 
    
    String sql = "SELECT datos_cli_prov.nombre, datos_cli_prov.direccion_propietario, datos_cli_prov.dni_propietario, datos_cli_prov.celular, rent_calculation.id, rent_calculation.mensual, rent_calculation.fecha " +
                 "FROM datos_cli_prov " +
                 "INNER JOIN rent_calculation ON datos_cli_prov.id = rent_calculation.client_id";
    Statement st;
    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);
        comboArrendatario.removeAllItems();
        
        while (rs.next()) {
            String nombreArrendatario = rs.getString("nombre");
            int idArrendatario = rs.getInt("id");
            comboArrendatario.addItem(nombreArrendatario);
            comboArrendatario.putClientProperty(nombreArrendatario, idArrendatario);
        }
        
        comboArrendatario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) comboArrendatario.getSelectedItem();
                if (selectedName != null) {
                    // Realizar consulta para obtener los detalles del arrendatario seleccionado
                    String query = "SELECT datos_cli_prov.direccion_propietario, datos_cli_prov.dni_propietario, datos_cli_prov.celular, rent_calculation.mensual, rent_calculation.fecha, piso.piso AS nombre_piso, cuarto.numcuarto AS nombre_cuarto, rent_calculation.garantia, cuarto.metraje AS area " +
                                            "FROM datos_cli_prov " +
                                            "INNER JOIN rent_calculation ON datos_cli_prov.id = rent_calculation.client_id " +
                                            "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
                                            "INNER JOIN piso ON cuarto.piso_id = piso.id " +
                                            "WHERE datos_cli_prov.nombre = ?";
                    try (PreparedStatement statement = objetoConexion.estableceConexion().prepareStatement(query)) {
                        statement.setString(1, selectedName);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            txtdireccionPropietario.setText(resultSet.getString("direccion_propietario"));
                            txtDniPropietario.setText(resultSet.getString("dni_propietario"));
                            txtTelePropietario.setText(resultSet.getString("celular"));
                            txtMensualidad.setText(resultSet.getString("mensual"));
                            txtFecha.setText(resultSet.getString("fecha"));
                            txtNombrePiso.setText(resultSet.getString("nombre_piso"));
                            txtNombreCuarto.setText(resultSet.getString("nombre_cuarto"));
                            txtGarantia.setText(resultSet.getString("garantia"));
                            txtMetraje.setText(resultSet.getString("area"));
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al obtener detalles del arrendatario: " + ex.toString());
                    }
                }
            }
        });
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar al arrendador: " + e.toString());
    }
}


    int idConyuge;
    
    public void establecerIdConyuge(int idConyuge){
        this.idConyuge = idConyuge;
    }
    
    public void MostrarConyuge(JComboBox comboConyuge, JTextField txtciudad, JTextField txtDniConyuge) {
    CConexion objetoConexion = new CConexion();

    String sql = "SELECT id, conyuge, ciudad, dni_conyuge FROM datos_cli_prov WHERE conyuge IS NOT NULL";
    Statement st;
    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);
        comboConyuge.removeAllItems();

        while (rs.next()) {
            int idConyuge = rs.getInt("id");
            String conyuge = rs.getString("conyuge");
            String ciudad = rs.getString("ciudad");
            String dniConyuge = rs.getString("dni_conyuge");

            comboConyuge.addItem(conyuge);
            comboConyuge.putClientProperty(conyuge, idConyuge); // Almacenar el ID del cónyuge como la propiedad del cliente
        }

        comboConyuge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedConyuge = (String) comboConyuge.getSelectedItem();
                if (selectedConyuge != null) {
                    // Obtener el ID del cónyuge
                    int idConyuge = (int) comboConyuge.getClientProperty(selectedConyuge);
                    
                    // Realizar consulta para obtener los detalles del cónyuge seleccionado
                    String query = "SELECT ciudad, dni_conyuge FROM datos_cli_prov WHERE id = ?";
                    try (PreparedStatement statement = objetoConexion.estableceConexion().prepareStatement(query)) {
                        statement.setInt(1, idConyuge);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            txtciudad.setText(resultSet.getString("ciudad"));
                            txtDniConyuge.setText(resultSet.getString("dni_conyuge"));
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al obtener detalles del cónyuge: " + ex.toString());
                    }
                }
            }
        });

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar al cónyuge: " + e.toString());
    }
}
    
    
    /*************************************   OPERACIONES CRUD   *********************************************/
    
    public void InsertarContrato(JComboBox comboArrendador, JComboBox comboArrendatario,  JComboBox comboGarante, JComboBox comboConyuge) {
    CConexion objetoConexion = new CConexion();

    String consulta = "INSERT INTO contrato (id_rent_calculation, id_mantenimiento_arrendador,  id_mantenimiento_garante, id_datos_cli_prov_conyuge) VALUES (?, ?, ?, ?)";

    try {
        int idArrendatario = (int) comboArrendatario.getClientProperty(comboArrendatario.getSelectedItem());
        int idArrendador = (int) comboArrendador.getClientProperty(comboArrendador.getSelectedItem());
        int idGarante = (int) comboGarante.getClientProperty(comboGarante.getSelectedItem());
        int idConyuge = (int) comboConyuge.getClientProperty(comboConyuge.getSelectedItem());

        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setInt(1, idArrendatario);
        cs.setInt(2, idArrendador);
        cs.setInt(3, idGarante);
        cs.setInt(4, idConyuge);
        cs.executeUpdate();

        JOptionPane.showMessageDialog(null, "Contrato insertado exitosamente");

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al insertar el contrato: " + e.toString());
    }
}

    
    public void MostrarContrato(JTable tbAlquiler) {
    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    tbAlquiler.setRowSorter(ordenarTabla);

    String[] columnasMostradas = {"ID", "Arrendatario", "Arrendador", "Garante", "Conyuge"};
    String[] columnasBD = {"id", "arrendatario", "arrendador", "garante", "conyuge"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    tbAlquiler.setModel(modelo);

    String sql = "SELECT contrato.id, " +
            "mantenimiento_arrendador.nombre AS arrendador, " +
            "mantenimiento_garante.nombre AS garante, " +
            "datos_cli_prov.nombre AS arrendatario, " +
            "datos_cli_prov.conyuge " +
            "FROM contrato " +
            "INNER JOIN rent_calculation ON contrato.id_rent_calculation = rent_calculation.id " +
            "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
            "INNER JOIN mantenimiento AS mantenimiento_arrendador ON contrato.id_mantenimiento_arrendador = mantenimiento_arrendador.id " +
            "INNER JOIN mantenimiento AS mantenimiento_garante ON contrato.id_mantenimiento_garante = mantenimiento_garante.id";

    try (Statement st = objetoConexion.estableceConexion().createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String[] datos = new String[columnasBD.length];

            for (int i = 0; i < columnasBD.length; i++) {
                datos[i] = rs.getString(columnasBD[i]);
            }

            modelo.addRow(datos);
        }

        tbAlquiler.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar contratos: " + e.toString());
        e.printStackTrace();
    }
}




   public void SeleccionarContrato(JTable tbAlquiler, JTextField id, JComboBox comboArrendador, JComboBox comboArrendatario, JComboBox comboGarante, JComboBox comboConyuge) {
    int fila = tbAlquiler.getSelectedRow();

    if (fila >= 0) {
        id.setText(tbAlquiler.getValueAt(fila, 0).toString());
        comboArrendatario.setSelectedItem(tbAlquiler.getValueAt(fila, 1).toString());
        comboArrendador.setSelectedItem(tbAlquiler.getValueAt(fila, 2).toString());
        comboGarante.setSelectedItem(tbAlquiler.getValueAt(fila, 3).toString());
        comboConyuge.setSelectedItem(tbAlquiler.getValueAt(fila, 4).toString());
    } else {
        JOptionPane.showMessageDialog(null, "Error al seleccionar contrato: No se ha seleccionado ningún contrato");
    }
}


   
   public void ModificarContrato(JTable tbAlquiler, JTextField id, JComboBox comboArrendador, JComboBox comboArrendatario, JComboBox comboGarante, JComboBox comboConyuge) {
    CConexion objetoConexion = new CConexion();
    String consulta = "UPDATE contrato SET id_rent_calculation=?, id_mantenimiento_arrendador=?, id_mantenimiento_garante=?, id_datos_cli_prov_conyuge=? WHERE id=?";

    try {
        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

        int fila = tbAlquiler.getSelectedRow();

        if (fila >= 0) {
            int idContrato = Integer.parseInt(id.getText());
            int idArrendador = (int) comboArrendador.getClientProperty(comboArrendador.getSelectedItem());
            int idArrendatario = (int) comboArrendatario.getClientProperty(comboArrendatario.getSelectedItem());
            int idGarante = (int) comboGarante.getClientProperty(comboGarante.getSelectedItem());
            int idConyuge = (int) comboConyuge.getClientProperty(comboConyuge.getSelectedItem());

            cs.setInt(1, idArrendatario);
            cs.setInt(2, idArrendador);
            cs.setInt(3, idGarante);
            cs.setInt(4, idConyuge);
            cs.setInt(5, idContrato);

            cs.executeUpdate();

            JOptionPane.showMessageDialog(null, "Contrato modificado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar contrato: No se ha seleccionado ningún contrato");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al modificar contrato: " + e.toString());
    }
}




   public void EliminarContrato(JTextField id) {
        CConexion objetoConexion = new CConexion();
        String consulta = "DELETE FROM contrato WHERE id=?";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt(id.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "Contrato eliminado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar contrato: " + e.toString());
        }
    }

    
   
   public void FiltrarClientes(JTable tbAlquiler, String searchText) {
    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = (DefaultTableModel) tbAlquiler.getModel();
    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    tbAlquiler.setRowSorter(ordenarTabla);

    String sql = "SELECT contrato.id, " +
                 "mantenimiento_arrendador.nombre AS arrendador, " +
                 "mantenimiento_garante.nombre AS garante, " +
                 "datos_cli_prov.nombre AS arrendatario, " +
                 "datos_cli_prov.conyuge " +
                 "FROM contrato " +
                 "INNER JOIN rent_calculation ON contrato.id_rent_calculation = rent_calculation.id " +
                 "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                 "INNER JOIN mantenimiento AS mantenimiento_arrendador ON contrato.id_mantenimiento_arrendador = mantenimiento_arrendador.id " +
                 "INNER JOIN mantenimiento AS mantenimiento_garante ON contrato.id_mantenimiento_garante = mantenimiento_garante.id";

    modelo.setRowCount(0); // Limpiar filas existentes en el modelo

    if (!searchText.isEmpty()) {
        sql += " WHERE datos_cli_prov.nombre LIKE '%" + searchText + "%'";
    }

    try (Statement st = objetoConexion.estableceConexion().createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String[] datos = new String[5]; // Agregado el cónyuge, por eso tamaño 5
            datos[0] = rs.getString("id");
            datos[1] = rs.getString("arrendatario");
            datos[2] = rs.getString("arrendador");
            datos[3] = rs.getString("garante");
            datos[4] = rs.getString("conyuge"); // Columna del cónyuge

            modelo.addRow(datos);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al filtrar clientes: " + e.toString());
    }
}

}
