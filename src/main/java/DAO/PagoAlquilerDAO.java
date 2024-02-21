package DAO;

import Conexion.CConexion;
import java.sql.PreparedStatement;
import Modelo.CalcularAlquiler;
import Modelo.Piso;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PagoAlquilerDAO {
    
    public ArrayList<String> obtenerNombresClientes() {
        ArrayList<String> clientes = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        // Consulta SQL
        String sql = "SELECT datos_cli_prov.nombre FROM datos_cli_prov INNER JOIN rent_calculation ON datos_cli_prov.id = rent_calculation.client_id";

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
    
    public void MostrarAlquiler(JTable tbMostrarAlquileres, String nombreClienteSeleccionado) {
        CConexion objetoConexion = new CConexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        tbMostrarAlquileres.setRowSorter(ordenarTabla);

        String[] columnasMostradas = {"Id", "Cliente", "Alquiler", "Fecha", "Cuotas", "interes", "Tipo de Pago"};
        String[] columnasBD = {"id", "nombre_cliente", "rent", "fecha",  "total", "interes", "tipo_pago"};

        for (int i = 0; i < columnasMostradas.length; i++) {
            modelo.addColumn(columnasMostradas[i]);
        }

        tbMostrarAlquileres.setModel(modelo);

        String sql = "SELECT rent_calculation.id, datos_cli_prov.nombre AS nombre_cliente, rent, fecha, total, interes, tipo_pago " +
                     "FROM rent_calculation " +
                     "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                     "WHERE datos_cli_prov.nombre = ?";
        
        try (PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql)) {
            pst.setString(1, nombreClienteSeleccionado);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String[] datos = new String[columnasBD.length];
                    for (int i = 0; i < columnasBD.length; i++) {
                        datos[i] = rs.getString(columnasBD[i]);
                    }
                    modelo.addRow(datos);
                }
                tbMostrarAlquileres.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar cálculos de alquiler, error: " + e.toString());
            e.printStackTrace(); // Imprimir la pila de excepciones para obtener más detalles
        }
    }
        
}
