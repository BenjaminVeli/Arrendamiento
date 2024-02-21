
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
    
    public void MostrarArrendadorCombo(JComboBox comboArrendador, JTextField txtdireccionArrendador, JTextField txtDniArrendador, JTextField txtTeleArrendador, JTextField txtProvincia, JTextField txtDepartamento, JTextField txtDistrito) {
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
            String provincia = rs.getString("provincia");
            String departamento = rs.getString("departamento");
            String distrito = rs.getString("distrito");
            
            comboArrendador.addItem(nombreArrendador);
            comboArrendador.putClientProperty(nombreArrendador, idArrendador);
        }
        
        comboArrendador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) comboArrendador.getSelectedItem();
                if (selectedName != null) {
                    // Realizar consulta para obtener los detalles del arrendador seleccionado
                    String query = "SELECT direccion, dni, celular , provincia, departamento, distrito FROM mantenimiento WHERE nombre = ?";
                    try (PreparedStatement statement = objetoConexion.estableceConexion().prepareStatement(query)) {
                        statement.setString(1, selectedName);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            txtdireccionArrendador.setText(resultSet.getString("direccion"));
                            txtDniArrendador.setText(resultSet.getString("dni"));
                            txtTeleArrendador.setText(resultSet.getString("celular"));
                            txtProvincia.setText(resultSet.getString("provincia"));
                            txtDepartamento.setText(resultSet.getString("departamento"));
                            txtDistrito.setText(resultSet.getString("distrito"));

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
    
    public void MostrarArrendatario(JComboBox comboArrendatario, JTextField txtdireccionPropietario, JTextField txtDniPropietario, JTextField txtTelePropietario, JTextField txtMensualidad, JTextField txtFecha, JTextField txtNombrePiso, JTextField txtNombreCuarto, JTextField txtGarantia, JTextField txtMetraje, JTextField txtConyuge, JTextField txtDniConyuge, JTextField txtCelularConyuge, JTextField txtCiudad, JTextField txtProvincia, JTextField txtDepartamento, JTextField txtDistrito, JTextField txtFechaFinal , JTextField txtEstadoCivil) {
    CConexion objetoConexion = new CConexion();

    String sql = "SELECT datos_cli_prov.nombre, datos_cli_prov.direccion_propietario, datos_cli_prov.dni_propietario, datos_cli_prov.provincia, datos_cli_prov.departamento, datos_cli_prov.estado_civil, datos_cli_prov.distrito, datos_cli_prov.dni_conyuge, datos_cli_prov.celular_conyuge, datos_cli_prov.ciudad, datos_cli_prov.celular, datos_cli_prov.conyuge, rent_calculation.id, rent_calculation.mensual, rent_calculation.fecha, rent_calculation.fechafinal, piso.piso AS nombre_piso, cuarto.numcuarto AS nombre_cuarto, rent_calculation.garantia, cuarto.metraje AS area " +
            "FROM datos_cli_prov " +
            "INNER JOIN rent_calculation ON datos_cli_prov.id = rent_calculation.client_id " +
            "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
            "INNER JOIN piso ON cuarto.piso_id = piso.id";

    try {
        Statement st = objetoConexion.estableceConexion().createStatement();
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
                    String query = "SELECT datos_cli_prov.direccion_propietario, datos_cli_prov.dni_propietario, datos_cli_prov.celular, datos_cli_prov.dni_conyuge, datos_cli_prov.celular_conyuge, datos_cli_prov.provincia, datos_cli_prov.departamento, datos_cli_prov.estado_civil ,datos_cli_prov.distrito, datos_cli_prov.ciudad, datos_cli_prov.conyuge, rent_calculation.fechafinal, rent_calculation.mensual, rent_calculation.fecha, piso.piso AS nombre_piso, cuarto.numcuarto AS nombre_cuarto, rent_calculation.garantia, cuarto.metraje AS area " +
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
                            txtConyuge.setText(resultSet.getString("conyuge"));
                            txtDniConyuge.setText(resultSet.getString("dni_conyuge"));
                            txtCelularConyuge.setText(resultSet.getString("celular_conyuge"));
                            txtCiudad.setText(resultSet.getString("ciudad"));
                            txtProvincia.setText(resultSet.getString("provincia"));
                            txtDepartamento.setText(resultSet.getString("departamento"));
                            txtDistrito.setText(resultSet.getString("distrito"));
                            txtFechaFinal.setText(resultSet.getString("fechafinal"));
                            txtEstadoCivil.setText(resultSet.getString("estado_civil"));
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
    
    /*************************************   OPERACIONES CRUD   *********************************************/
    
public void InsertarContrato(JComboBox comboArrendador, JComboBox comboArrendatario, JComboBox comboGarante, JTextField paramPersona) {
    CConexion objetoConexion = new CConexion();

    String consulta = "INSERT INTO contrato (id_rent_calculation, id_mantenimiento_arrendador, id_mantenimiento_garante, personas) VALUES (?, ?, ?, ?)";

    try {
        int idArrendatario = (int) comboArrendatario.getClientProperty(comboArrendatario.getSelectedItem());
        int idArrendador = (int) comboArrendador.getClientProperty(comboArrendador.getSelectedItem());
        Integer idGarante = null;

        if (comboGarante.getSelectedItem() != null) {
            idGarante = (int) comboGarante.getClientProperty(comboGarante.getSelectedItem());
        }

        Integer personas = null;
        if (!paramPersona.getText().isEmpty()) {
            personas = Integer.parseInt(paramPersona.getText());
        } else {
            personas = null;
        }

        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setInt(1, idArrendatario);
        cs.setInt(2, idArrendador);
        if (idGarante != null) {
            cs.setInt(3, idGarante);
        } else {
            cs.setNull(3, java.sql.Types.INTEGER);
        }
        if (personas != null) {
            cs.setInt(4, personas); 
        } else {
            cs.setNull(4, java.sql.Types.INTEGER); // Si personas es null, inserta un valor NULL en la base de datos.
        }
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

    String[] columnasMostradas = {"ID", "Arrendatario", "Arrendador",  "Garante", "Personas"}; 
    String[] columnasBD = {"id", "arrendatario", "arrendador",  "garante", "personas"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    tbAlquiler.setModel(modelo);

    String sql = "SELECT contrato.id, " +
                    "mantenimiento_arrendador.nombre AS arrendador, " +
                    "mantenimiento_garante.nombre AS garante, " +
                    "datos_cli_prov.nombre AS arrendatario, " +
                    "contrato.personas " + // Agregar personas
                    "FROM contrato " +
                    "INNER JOIN rent_calculation ON contrato.id_rent_calculation = rent_calculation.id " +
                    "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                    "INNER JOIN mantenimiento AS mantenimiento_arrendador ON contrato.id_mantenimiento_arrendador = mantenimiento_arrendador.id " +
                    "LEFT JOIN mantenimiento AS mantenimiento_garante ON contrato.id_mantenimiento_garante = mantenimiento_garante.id";

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

 public void SeleccionarContrato(JTable tbAlquiler, JTextField id, JComboBox comboArrendador, JComboBox comboArrendatario, JComboBox comboGarante, JTextField paramPersona) {
    try {
        int fila = tbAlquiler.getSelectedRow();
        if (fila >= 0) {
            // Obtener los valores de la fila seleccionada
            String[] valoresFila = new String[5];
            for (int i = 0; i < 5; i++) {
                Object valorCelda = tbAlquiler.getValueAt(fila, i);
                valoresFila[i] = (valorCelda != null) ? valorCelda.toString() : "";
            }
            
            // Establecer valores en los campos correspondientes
            id.setText(valoresFila[0]);
            comboArrendatario.setSelectedItem(valoresFila[1]);
            comboArrendador.setSelectedItem(valoresFila[2]);
            comboGarante.setSelectedItem(valoresFila[3]);
            paramPersona.setText(valoresFila[4]);
        } else {
            JOptionPane.showMessageDialog(null, "Error al seleccionar contrato: No se ha seleccionado ningún contrato");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}

   public void ModificarContrato(JTable tbAlquiler, JTextField id, JComboBox comboArrendador, JComboBox comboArrendatario,  JComboBox comboGarante, JTextField paramPersona) {
        CConexion objetoConexion = new CConexion();
        String consulta = "UPDATE contrato SET id_rent_calculation=?, id_mantenimiento_arrendador=?,  id_mantenimiento_garante=? , personas=? WHERE id=?";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            int fila = tbAlquiler.getSelectedRow();

            if (fila >= 0) {
                int idContrato = Integer.parseInt(id.getText());
                int idArrendador = (int) comboArrendador.getClientProperty(comboArrendador.getSelectedItem());
                int idArrendatario = (int) comboArrendatario.getClientProperty(comboArrendatario.getSelectedItem());
                int idGarante = (int) comboGarante.getClientProperty(comboGarante.getSelectedItem());
                int personas = Integer.parseInt(paramPersona.getText());
                
                cs.setInt(1, idArrendatario);
                cs.setInt(2, idArrendador);
                cs.setInt(3, idGarante);
                cs.setInt(4,personas);
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
                    "contrato.personas " + // Agregar personas
                    "FROM contrato " +
                    "INNER JOIN rent_calculation ON contrato.id_rent_calculation = rent_calculation.id " +
                    "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                    "INNER JOIN mantenimiento AS mantenimiento_arrendador ON contrato.id_mantenimiento_arrendador = mantenimiento_arrendador.id " +
                    "LEFT JOIN mantenimiento AS mantenimiento_garante ON contrato.id_mantenimiento_garante = mantenimiento_garante.id";

    modelo.setRowCount(0); // Limpiar filas existentes en el modelo

    if (!searchText.isEmpty()) {
        sql += " WHERE datos_cli_prov.nombre LIKE '%" + searchText + "%'";
    }

    try (Statement st = objetoConexion.estableceConexion().createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String[] datos = new String[5]; 
            datos[0] = rs.getString("id");
            datos[1] = rs.getString("arrendatario");
            datos[2] = rs.getString("arrendador");
            datos[3] = rs.getString("garante");
            datos[4] = rs.getString("personas");
            
            modelo.addRow(datos);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al filtrar clientes: " + e.toString());
    }
}

}
