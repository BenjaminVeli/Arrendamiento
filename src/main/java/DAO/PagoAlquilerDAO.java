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
                 "WHERE datos_cli_prov.nombre = ? AND rent_calculation.estado_rent_calculation = 1"; // Agregando condición del campo estado_rent_calculation
        
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
    
    public void MostrarAlquilerTODO(JTable tbMostrarAlquileres, String nombreClienteSeleccionado) {
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
    
    public String obtenerArrendador(int clientId) {
        CConexion objetoConexion = new CConexion();
        String arrendador = "";

        String sql = "SELECT mantenimiento.nombre " +
                     "FROM contrato " +
                     "INNER JOIN mantenimiento ON contrato.id_mantenimiento_arrendador = mantenimiento.id " +
                     "INNER JOIN rent_calculation ON contrato.id_rent_calculation = rent_calculation.id " +
                     "WHERE rent_calculation.client_id = ?";

        try (PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql)) {
            pst.setInt(1, clientId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    arrendador = rs.getString("nombre");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el arrendador del cliente: " + e.toString());
        }

        return arrendador;
    }
    
    public int obtenerClienteIdPorImporteVariado(int idImporteVariado) {
        CConexion objetoConexion = new CConexion();
        int clienteId = -1;

        String sql = "SELECT rent_calculation.client_id " +
                     "FROM importe_variado " +
                     "INNER JOIN rent_calculation ON importe_variado.rent_calculation_id = rent_calculation.id " +
                     "WHERE importe_variado.id = ?";

        try (PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql)) {
            pst.setInt(1, idImporteVariado);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    clienteId = rs.getInt("client_id");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del cliente: " + e.toString());
        }

        return clienteId;
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
    
    public void reiniciarSaldosSubsiguientes(int idImporteVariado, double importes_tbImporteVariado) {
        CConexion objetoConexion = new CConexion();

        try {
            // Consulta SQL para obtener las filas subsiguientes al ID proporcionado
            String sql = "SELECT id FROM importe_variado WHERE id > ? AND estado = 0 ORDER BY id";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, idImporteVariado);
            ResultSet rs = pst.executeQuery();
            
            double saldoAcumulado = 0.00;

            while (rs.next()) {
                int idFila = rs.getInt("id");
                saldoAcumulado += importes_tbImporteVariado; // Sumar el importe actual al saldo acumulado
                String sqlUpdate = "UPDATE importe_variado SET saldos = ? WHERE id = ?";
                PreparedStatement psSqlUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate);
                psSqlUpdate.setDouble(1, saldoAcumulado);
                psSqlUpdate.setInt(2, idFila);
                psSqlUpdate.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al reiniciar los saldos subsiguientes: " + e.toString());
        }
    }
    
    public void actualizarDatosAlquilerwa(int idAlquiler, String detalleCuarto){
        CConexion objetoConexion = new CConexion();
        String obtenerDatosCuarto = "SELECT floor_id, room_id FROM rent_calculation WHERE id=?";
        
        try {
            // Obtener los datos del cuarto asociado al cálculo
            PreparedStatement psDatosCuarto = objetoConexion.estableceConexion().prepareStatement(obtenerDatosCuarto);
            psDatosCuarto.setInt(1, idAlquiler);
            ResultSet rsDatosCuarto = psDatosCuarto.executeQuery();
            
             int floorId = 0;
            int roomId = 0;

            if (rsDatosCuarto.next()) {
                floorId = rsDatosCuarto.getInt("floor_id");
                roomId = rsDatosCuarto.getInt("room_id");
            }
            
            // Liberar el cuarto asociado (marcar como desocupado)
            String liberarCuarto = "UPDATE cuarto SET ocupado=false WHERE id=?";
            PreparedStatement psLiberarCuarto = objetoConexion.estableceConexion().prepareStatement(liberarCuarto);
            psLiberarCuarto.setInt(1, roomId);
            psLiberarCuarto.executeUpdate();
            
            //Actualizar los campos detalle_cuarto con el texto ingresado en el TextArea, y los campos floor_id y room_id se establecerán en null y estado_rent_calculation a 0.
            String sql = "UPDATE rent_calculation SET detalle_cuarto = ?, floor_id = NULL, room_id = NULL, estado_rent_calculation = 0 WHERE id = ?";
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, detalleCuarto);
            pst.setInt(2, idAlquiler);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se dio de baja correctamente al cliente y se liberó el cuarto asociado.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar datos de alquiler: " + e.toString());
        }
    }
    
   /* public ArrayList<String> obtenerPosicionesDisponibles(String rentCalculationId) {
        ArrayList<String> posiciones = new ArrayList<>();
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT ord FROM importe_variado WHERE rent_calculation_id = ? AND estado = 0";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, rentCalculationId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                posiciones.add(rs.getString("ord"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener las posiciones disponibles: " + e.toString());
        }
        return posiciones;
    }
    
    public String obtenerRentCalculationIdSeleccionado(JTable tbMostrarAlquileres) {
        int fila = tbMostrarAlquileres.getSelectedRow();
        if (fila >= 0) {
            return tbMostrarAlquileres.getValueAt(fila, 0).toString(); // Devuelve el ID seleccionado
        }
        return null; // Si no se selecciona ninguna fila, devuelve null
    }

    public String obtenerRentCalculationIdPorImporteVariado(int importeVariadoId) {
        String rentCalculationId = null;
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT rent_calculation_id FROM importe_variado WHERE id = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, importeVariadoId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                rentCalculationId = rs.getString("rent_calculation_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener rent_calculation_id por importe_variado_id: " + e.toString());
        }

        return rentCalculationId;
    }
    
    public void insertarAmortizacionPorSeleccion(int rentCalculation_id, String posicion1, String posicion2, String num_amortizacion, double importe, String detalle, Timestamp fechaHoraSQL){
        CConexion objetoConexion = new CConexion();

        try { 
            String num_amortizacion_nuevo = obtenerUltimoNumeroAmortizacion();

            // Sentencia SQL UPDATE para actualizar los registros en la base de datos
            String sqlUpdate = "UPDATE importe_variado SET numero_amortizacion = ?, fecha_amortizacion = ?, pago = ?, detalle = ? WHERE rent_calculation_id = ? AND ord BETWEEN ? AND ?";

            PreparedStatement psSqlUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate);

            psSqlUpdate.setString(1, num_amortizacion_nuevo);
            psSqlUpdate.setTimestamp(2, fechaHoraSQL);
            psSqlUpdate.setBigDecimal(3, new BigDecimal(importe));
            psSqlUpdate.setString(4, detalle);
            psSqlUpdate.setInt(5, rentCalculation_id);
            psSqlUpdate.setString(6, posicion1);
            psSqlUpdate.setString(7, posicion2);

            psSqlUpdate.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar los registros en importe_variado para la amortización: " + e.toString());
        }
    }*/
    
}