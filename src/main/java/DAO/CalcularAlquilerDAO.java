package DAO;

import Conexion.CConexion;
import Modelo.CalcularAlquiler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;

public class CalcularAlquilerDAO {
    
    public ArrayList<String> obtenerNombresClientes() {
        ArrayList<String> clientes = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT nombre FROM datos_cli_prov";

        try {
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                clientes.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener los nombres de los clientes, error: " + e.toString());
        }

        return clientes;
    }

    public ArrayList<String> obtenerPisos() {
        ArrayList<String> pisos = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT piso FROM piso";

        try {
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                pisos.add(rs.getString("piso"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener los pisos, error: " + e.toString());
        }

        return pisos;
    }

    public void insertarCalculoAlquiler(String nombreCliente, int rent, int total, String nombrePiso) {
        CConexion objetoConexion = new CConexion();

        try {
            
            String sql = "INSERT INTO rent_calculation (client_id, rent, total, total_rent, floor_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);

            // Obtener el ID del cliente seleccionado
            int clientId = obtenerIdNombre(nombreCliente);

            // Obtener el ID del piso seleccionado
            int floorId = obtenerIdPiso(nombrePiso);

            pst.setInt(1, clientId);
            pst.setInt(2, rent);
            pst.setInt(3, total);
            pst.setInt(4, rent * total);
            pst.setInt(5, floorId);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cálculo de alquiler insertado exitosamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el cálculo de alquiler: " + e.toString());
        }
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
}
