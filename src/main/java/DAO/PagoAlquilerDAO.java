package DAO;

import Conexion.CConexion;
import java.sql.PreparedStatement;
import Modelo.CalcularAlquiler;
import Modelo.ImporteVariado;
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
        
    public void SeleccionaryMostrarImporteVariado(JTable tbMostrarAlquileres, JTable tbImporteVariado) {
        try {
            int fila = tbMostrarAlquileres.getSelectedRow();

            if (fila >= 0) {
                String idSeleccionado = tbMostrarAlquileres.getValueAt(fila, 0).toString();
                CConexion objetoConexion = new CConexion();

                String sql = "SELECT id, ord, fecha, importe, pago, saldos, estado FROM importe_variado WHERE rent_calculation_id = ?";

                PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                pst.setString(1, idSeleccionado);

                ResultSet rs = pst.executeQuery();

                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("Ord");
                modelo.addColumn("Fecha");
                modelo.addColumn("Importe");
                modelo.addColumn("Pago");
                modelo.addColumn("Saldos");
                modelo.addColumn("Estado");

                while (rs.next()) {
                    Object[] filaDatos = {
                        rs.getString("id"),
                        rs.getString("ord"),
                        rs.getString("fecha"),
                        rs.getString("importe"),
                        rs.getString("pago"),
                        rs.getString("Saldos"),
                        rs.getBoolean("estado") ? "Cancelado" : "No cancelado" // Modificación aquí
                    };
                    modelo.addRow(filaDatos);
                }

                tbImporteVariado.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede mostrar los registros de importe variado del cliente, error: " + e.toString());
        }
    }

    public void SeleccionaryAmortización(JTable tbImporteVariado){
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        ImporteVariado variado = new ImporteVariado();
        
        try {
            int fila = tbImporteVariado.getSelectedRow();

            if (fila >= 0) {
                String idSeleccionado = tbImporteVariado.getValueAt(fila, 0).toString();
                
                // Obtén el ID del cuarto actualmente ocupado
                int room_id_actual = obtenerCuartoidPorImporteVariado(Integer.parseInt(idSeleccionado));
                
                System.err.println("EL numero de orden seleccionado es: " + idSeleccionado);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede mostrar los registros de importe variado del cliente, error: " + e.toString());
        }
    }
    
        public int obtenerCuartoidPorImporteVariado(int idImporteVariado) {
            CConexion objetoConexion = new CConexion();
            String sql = "SELECT cuarto_id FROM importe_variado WHERE id=?";

            try {
                PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                pst.setInt(1, idImporteVariado);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    return rs.getInt("cuarto_id");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al obtener el ID del cuarto de la tabla ImporteVariado: " + e.toString());
            }

            return -1; // Retorno por defecto en caso de error
    }
    
}
