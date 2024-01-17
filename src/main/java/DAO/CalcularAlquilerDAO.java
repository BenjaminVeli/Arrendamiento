package DAO;

import Conexion.CConexion;
import java.sql.PreparedStatement;
import Modelo.CalcularAlquiler;
import Modelo.Piso;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CalcularAlquilerDAO {

    public ArrayList<String> obtenerNombresClientes() {
        ArrayList<String> clientes = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT nombre FROM datos_cli_prov";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                clientes.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los nombres de los clientes: " + e.toString());
        }

        return clientes;
    }

    public ArrayList<String> obtenerPisos() {
        ArrayList<String> pisos = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT piso FROM piso";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                pisos.add(rs.getString("piso"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los pisos: " + e.toString());
        }
        return pisos;
    }
    
    public ArrayList<String> obtenerCuartosPorPiso(String nombrePiso) {
        ArrayList<String> cuartos = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT cuarto.numcuarto FROM cuarto INNER JOIN piso ON cuarto.piso_id = piso.id WHERE piso.piso = ? AND cuarto.ocupado = false";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, nombrePiso);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                cuartos.add(rs.getString("numcuarto"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los cuartos: " + e.toString());
        }
        return cuartos;
    }
    
    public void insertarCalculoAlquiler(JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JTextField paramCuotas, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramTotal, JDateChooser paramFecha, JDateChooser paramFechaIngreso) {
        CalcularAlquiler ca = new CalcularAlquiler();
       
        String nombreCliente = (String) paramNombreCliente.getSelectedItem();
        int clientId = obtenerIdNombre(nombreCliente);

        String nombrePiso = (String) paramNombrePiso.getSelectedItem();
        int floorId = obtenerIdPiso(nombrePiso);
        
        String nombreCuarto = (String) paramNombreCuarto.getSelectedItem();
        int room_id = obtenerIdCuartoPorPiso(nombreCuarto, floorId);

        if (ca.getPiso() == null) {
            ca.setPiso(new Piso());
        }
        
        ca.getCliente().setCodigo(clientId);
        ca.setRent(Integer.parseInt(paramRent.getText()));
        ca.setGarantia(Integer.parseInt(paramGarantia.getText()));
        ca.setCuotas(Integer.parseInt(paramCuotas.getText()));
        ca.setTotal(Integer.parseInt(paramTotal.getText()));
        ca.setTotalRent();
        ca.getPiso().setCodigo(floorId);
        ca.setCuarto((String) paramNombreCuarto.getSelectedItem());
        ca.setFecha(new Date(paramFecha.getDate().getTime()));
        ca.setFechaIngreso(new Date(paramFechaIngreso.getDate().getTime()));

        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO rent_calculation (client_id, rent, garantia, total, total_rent, floor_id, room_id, cuotas, fecha, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";

        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setInt(1, ca.getCliente().getCodigo());
            cs.setInt(2, ca.getRent());
            cs.setInt(3, ca.getGarantia());
            cs.setInt(4, ca.getTotal());
            cs.setInt(5, ca.getTotalRent()); //rent * total
            cs.setInt(6, ca.getPiso().getCodigo());
            cs.setInt(7, room_id);
            cs.setInt(8, ca.getCuotas());           
            cs.setDate( 9, ca.getFecha());
            cs.setDate( 10, ca.getFechaIngreso());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Cálculo de alquiler insertado exitosamente");
            
            refrescarComboBoxPisos(paramNombrePiso);   
            // Actualizar el estado del cuarto seleccionado
            actualizarEstadoCuarto(room_id);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el cálculo de alquiler: " + e.toString());
        }
    }
    
    public void MostrarAlquiler(JTable tbTotalCalculo) {
    CConexion objetoConexion = new CConexion();

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    tbTotalCalculo.setRowSorter(ordenarTabla);

    String[] columnasMostradas = {"Id", "Cliente", "Rent", "Garantía", "Total", "Total Rent", "Piso", "Cuarto"};
    String[] columnasBD = {"id", "nombre_cliente", "rent", "garantia", "total", "total_rent", "nombre_piso", "numcuarto", "cuotas", "fecha", "fecha_ingreso"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    tbTotalCalculo.setModel(modelo);

    String sql = "SELECT rent_calculation.id, datos_cli_prov.nombre as nombre_cliente, rent, garantia, cuotas, fecha, rent_calculation.fecha_ingreso, total, total_rent, piso.piso as nombre_piso, cuarto.numcuarto FROM rent_calculation INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id INNER JOIN piso ON rent_calculation.floor_id = piso.id INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id";

    try (Statement st = objetoConexion.estableceConexion().createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String[] datos = new String[columnasBD.length];

            for (int i = 0; i < columnasBD.length; i++) {
                datos[i] = rs.getString(columnasBD[i]);
            }

            modelo.addRow(datos);
        }

        tbTotalCalculo.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar cálculos de alquiler, error: " + e.toString());
        e.printStackTrace(); // Imprimir la pila de excepciones para obtener más detalles
    }
}

    public void SeleccionarCalculoAlquiler(JTable paramTablaCalculosAlquiler, JTextField paramId, JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JTextField paramCuotas, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramTotal, JDateChooser paramFecha, JDateChooser paramFechaIngreso) {
    try {
        int fila = paramTablaCalculosAlquiler.getSelectedRow();
        if (fila >= 0) {
            // Obtén el ID de la fila seleccionada
            String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();

            String sql = "SELECT * FROM rent_calculation " +
                         "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                         "INNER JOIN piso ON rent_calculation.floor_id = piso.id " +
                         "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
                         "WHERE rent_calculation.id = " + idSeleccionado;

            CConexion objetoConexion = new CConexion();
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                paramId.setText(rs.getString("id"));
                paramRent.setText(rs.getString("rent"));
                paramGarantia.setText(rs.getString("garantia"));
                paramCuotas.setText(rs.getString("cuotas"));
                paramTotal.setText(rs.getString("total"));

                // Obtener el nombre del cliente y establecerlo en el JComboBox
                String nombreCliente = rs.getString("nombre");
                paramNombreCliente.setSelectedItem(nombreCliente);

                // Obtener el nombre del piso y establecerlo en el JComboBox
                String nombrePiso = rs.getString("piso");
                paramNombrePiso.setSelectedItem(nombrePiso);

                // Obtener el nombre del cuarto y establecerlo en el JComboBox
                String nombreCuarto = rs.getString("numcuarto");
                paramNombreCuarto.setSelectedItem(nombreCuarto);

                // Manejo de fechas
                java.util.Date fecha = rs.getDate("fecha");
                java.util.Date fechaIngreso = rs.getDate("fecha_ingreso");
                paramFecha.setDate(fecha);
                paramFechaIngreso.setDate(fechaIngreso);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}


    private void refrescarComboBoxPisos(JComboBox<String> paramNombrePiso) {
        var nombresPisos = obtenerPisos();

        paramNombrePiso.removeAllItems();

        for (String nombrePiso : nombresPisos) {
            paramNombrePiso.addItem(nombrePiso);
        }

        paramNombrePiso.setSelectedItem(null);
    }
    
    private int obtenerIdNombre(String nombreCliente) {
        int clientId = -1;
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT id FROM datos_cli_prov WHERE nombre = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, nombreCliente);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                clientId = rs.getInt("id");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del cliente: " + e.toString());
        }

        return clientId;
    }
    
    private int obtenerIdPiso(String nombrePiso) {
        int floorId = -1;
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT id FROM piso WHERE piso = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, nombrePiso);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                floorId = rs.getInt("id");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del piso: " + e.toString());
        }

        return floorId;
    }
    
    private int obtenerIdCuartoPorPiso(String nombreCuarto, int idPiso) {
        int cuartoId = -1;
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT id FROM cuarto WHERE numcuarto = ? AND piso_id = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, nombreCuarto);
            pst.setInt(2, idPiso);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cuartoId = rs.getInt("id");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del cuarto: " + e.toString());
        }
        return cuartoId;
    }
    
    private void actualizarEstadoCuarto(int room_id) {
        CConexion objetoConexion = new CConexion();
        String consulta = "UPDATE cuarto SET ocupado = true WHERE id = ?";

        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, room_id);
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado del cuarto: " + e.toString());
        }
    }
    
}
