package DAO;

import Conexion.CConexion;
import java.sql.PreparedStatement;
import Modelo.CalcularAlquiler;
import Modelo.Piso;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

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
    
    public void insertarCalculoAlquiler(JComboBox<String> paramNombreCliente, JTextField paramRent,JTextField paramGarantia, JTextField paramTotal, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto) {
        CalcularAlquiler ca = new CalcularAlquiler();
       
        // Obtener el ID del cliente seleccionado
        String nombreCliente = (String) paramNombreCliente.getSelectedItem();
        int clientId = obtenerIdNombre(nombreCliente);

        // Obtener el ID del piso seleccionado
        String nombrePiso = (String) paramNombrePiso.getSelectedItem();
        int floorId = obtenerIdPiso(nombrePiso);
        
        // Obtener el ID del cuarto seleccionado
        String nombreCuarto = (String) paramNombreCuarto.getSelectedItem();
        int room_id = obtenerIdCuartoPorPiso(nombreCuarto, floorId);

        // Verificar si getPiso() devuelve null y crear un nuevo objeto Piso si es necesario
        if (ca.getPiso() == null) {
            ca.setPiso(new Piso());
        }
        
        // Configurar el objeto CalcularAlquiler con los datos obtenidos
        ca.getCliente().setCodigo(clientId);
        ca.setRent(Integer.parseInt(paramRent.getText()));
        ca.setGarantia(Integer.parseInt(paramGarantia.getText()));
        ca.setTotal(Integer.parseInt(paramTotal.getText()));
        ca.setTotalRent();
        ca.getPiso().setCodigo(floorId);
        ca.setCuarto((String) paramNombreCuarto.getSelectedItem());

        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO rent_calculation (client_id, rent, garantia, total, total_rent, floor_id, room_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setInt(1, ca.getCliente().getCodigo());
            cs.setInt(2, ca.getRent());
            cs.setInt(3, ca.getGarantia());
            cs.setInt(4, ca.getTotal());
            cs.setInt(5, ca.getTotalRent()); //rent * total
            cs.setInt(6, ca.getPiso().getCodigo()); 
            cs.setInt(7, room_id);

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

    DefaultTableModel modelo = new DefaultTableModel();
    String sql = "";
    modelo.addColumn("id");
    modelo.addColumn("Cliente");
    modelo.addColumn("Rent");
    modelo.addColumn("Garantía");
    modelo.addColumn("Total");
    modelo.addColumn("Total Rent");
    modelo.addColumn("Piso");
    modelo.addColumn("Cuarto");

    tbTotalCalculo.setModel(modelo);

    // Ajustar la consulta para incluir el campo 'garantia'
    sql = "SELECT rent_calculation.id, datos_cli_prov.nombre as nombre_cliente, rent, garantia, total, total_rent, piso.piso as nombre_piso, cuarto.numcuarto FROM rent_calculation INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id INNER JOIN piso ON rent_calculation.floor_id = piso.id INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id";

    try (Statement st = objetoConexion.estableceConexion().createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String id = rs.getString("id");
            String nombreCliente = rs.getString("nombre_cliente");
            String rent = rs.getString("rent");
            String garantia = rs.getString("garantia");
            String total = rs.getString("total");
            String totalRent = rs.getString("total_rent");
            String nombrePiso = rs.getString("nombre_piso");
            String numCuarto = rs.getString("numcuarto");

            modelo.addRow(new Object[]{id, nombreCliente, rent, garantia, total, totalRent, nombrePiso, numCuarto});
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar cálculos de alquiler, error: " + e.toString());
        e.printStackTrace(); // Imprimir la pila de excepciones para obtener más detalles
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
