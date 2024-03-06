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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    
    public void insertarAmortizacion(int id_seleccionado,String num_amortizacion, double importe, String detalle, Timestamp fechaHoraSQL){
        CConexion objetoConexion = new CConexion();
        PagoAlquilerDAO paDAO = new PagoAlquilerDAO();
        
        try { 
            String num_amortizacion_nuevo = paDAO.obtenerUltimoNumeroAmortizacion();
            
            // Sentencia SQL UPDATE para actualizar los registros en la base de datos
            String sqlUpdate = "UPDATE importe_variado SET numero_amortizacion = ?, fecha_amortizacion = ?, pago = ?, detalle = ? WHERE id = ?";
            
            PreparedStatement psSqlUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate);
            
            psSqlUpdate.setString(1,num_amortizacion_nuevo);
            psSqlUpdate.setTimestamp(2,fechaHoraSQL);
            psSqlUpdate.setBigDecimal(3,new BigDecimal(importe));
            psSqlUpdate.setString(4,detalle);
            psSqlUpdate.setInt(5,id_seleccionado);
            
            psSqlUpdate.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar el registro en importe_variado para la amortización: " + e.toString());
        }
    }
    
    // Método para obtener el número de cuarto basado en el ID de importe_variado
    public String obtenerNumeroCuartoPorImporteVariado(int idImporteVariado) {
        CConexion objetoConexion = new CConexion();
        String sql = "SELECT cuarto.numcuarto FROM importe_variado " +
                     "INNER JOIN cuarto ON importe_variado.cuarto_id = cuarto.id " +
                     "WHERE importe_variado.id = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, idImporteVariado);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("numcuarto");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de cuarto: " + e.toString());
        }

        return null; // Retorno por defecto en caso de error
    }
    
    public String obtenerNombreClientePorImporteVariado(int idImporteVariado) {
    CConexion objetoConexion = new CConexion();
    String nombreCliente = "";

    String sql = "SELECT datos_cli_prov.nombre " +
                 "FROM importe_variado " +
                 "INNER JOIN rent_calculation ON importe_variado.rent_calculation_id = rent_calculation.id " +
                 "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                 "WHERE importe_variado.id = ?";

    try (PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql)) {
        pst.setInt(1, idImporteVariado);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                nombreCliente = rs.getString("nombre");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener el nombre del cliente: " + e.toString());
    }

    return nombreCliente;
}

    
    public String obtenerUltimoNumeroAmortizacion() {
        CConexion objetoConexion = new CConexion();
        String ultimoNumero = "";

        try {
            String sql = "SELECT numero_amortizacion FROM importe_variado WHERE fecha_amortizacion IS NOT NULL ORDER BY fecha_amortizacion DESC LIMIT 1";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ultimoNumero = rs.getString("numero_amortizacion");
            }

            // Incrementar el último número en 1 y formatearlo con ceros a la izquierda
            int siguienteNumero = 1;
            if (!ultimoNumero.isEmpty()) {
                siguienteNumero = Integer.parseInt(ultimoNumero) + 1;
            }
            ultimoNumero = String.format("%07d", siguienteNumero);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al obtener el último número de amortización: " + e.toString());
        }

        return ultimoNumero;
    }
    
    public void actualizarSaldo(int idImporteVariado, double nuevoSaldo) {
        CConexion objetoConexion = new CConexion();
        
        try {
            String sql = "UPDATE importe_variado SET saldos = ? WHERE id = ?";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setDouble(1, nuevoSaldo);
            pst.setInt(2, idImporteVariado);
            
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar el saldo: " + e.toString());
        }
    }
    
    public void actualizarEstado(int idImporteVariado, boolean nuevoEstado) {
        CConexion objetoConexion = new CConexion();
        
        try {
            String sql = "UPDATE importe_variado SET estado = ? WHERE id = ?";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setBoolean(1, nuevoEstado);
            pst.setInt(2, idImporteVariado);
            
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar el estado: " + e.toString());
        }
    }
    
    public void reiniciarSaldosSubsiguientes(int idImporteVariado, double nuevoSaldo, double importe) {
        CConexion objetoConexion = new CConexion();
        
        try {
            // Consulta SQL para obtener las filas subsiguientes al ID proporcionado
            String sql = "SELECT id FROM importe_variado WHERE id > ? AND estado = 0 ORDER BY id";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, idImporteVariado);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int idFila = rs.getInt("id");
                String sqlUpdate = "UPDATE importe_variado SET saldos = ? WHERE id = ?";
                PreparedStatement psSqlUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate);
                psSqlUpdate.setDouble(1, nuevoSaldo);
                psSqlUpdate.setInt(2, idFila);
                psSqlUpdate.executeUpdate();
                
                nuevoSaldo += importe; // Sumar el importe al saldo para la siguiente fila
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al reiniciar los saldos subsiguientes: " + e.toString());
        }
    }
}