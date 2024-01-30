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

public class CalcularAlquilerDAO {
   
    private double sumaInteresAcumulativa = 0;
    
    private double sumaCapitalAcumulativa = 0;
    
    public double obtenerSumaInteresAcumulativa() {
        return sumaInteresAcumulativa;
    }
    
    public double obtenerSumaCapitalAcumulativa() {
        return sumaCapitalAcumulativa;
    }
    
    public double calcularPorPagar(double total_rent, int cuotas) {
        return  (sumaInteresAcumulativa + total_rent) / cuotas;
    }
    
    public double calcularSumaMensual(double porPagar, int cuotas) {
        return porPagar * cuotas;
    }
    
    public double calcularImporteDiario(double total_rent, int cuotas) {
        return total_rent / (cuotas * 30);
    }
    
    public double calcularImporteSemanal(double total_rent, int cuotas) {
        return total_rent / (cuotas * 4);
    }
    
    public double calcularImporteQuincenal(double total_rent, int cuotas) {
        return total_rent / (cuotas * 2);
    }
    
    public double calcularImporteMensual(double total_rent, int cuotas) {
        return total_rent / cuotas;
    }
    
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
    
    /********************************** OPERACIONES CRUD **********************************/
    public void insertarCalculoAlquiler(JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramInteres, JTextField paramTotal, JDateChooser paramFecha, JDateChooser paramFechaIngreso, JTextField paramMensual) {
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
        
        if (cuartoEstaOcupado(room_id)) {
        JOptionPane.showMessageDialog(null, "Error: El cuarto seleccionado ya está ocupado.", "Error", JOptionPane.ERROR_MESSAGE);
        return; // No se permite la inserción
    }
        
        ca.getCliente().setCodigo(clientId);
        ca.setRent(new BigDecimal(paramRent.getText()));
        ca.setGarantia(new BigDecimal(paramGarantia.getText()));
        ca.setInteres(new BigDecimal(paramInteres.getText()));
        ca.setTotal(Integer.parseInt(paramTotal.getText()));
        ca.setTotalRent();
        ca.getPiso().setCodigo(floorId);
        ca.setCuarto((String) paramNombreCuarto.getSelectedItem());
        ca.setFecha(new Date(paramFecha.getDate().getTime()));
        ca.setFechaIngreso(new Date(paramFechaIngreso.getDate().getTime()));
        ca.setMensual(new BigDecimal(paramMensual.getText()));

        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO rent_calculation (client_id, rent, garantia, total, total_rent, floor_id, room_id,interes,mensual, fecha, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?)";

        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setInt(1, ca.getCliente().getCodigo());
            cs.setBigDecimal(2, ca.getRent());
            cs.setBigDecimal(3, ca.getGarantia());
            cs.setInt(4, ca.getTotal());
            cs.setBigDecimal(5, ca.getTotalRent()); //rent * total
            cs.setInt(6, ca.getPiso().getCodigo());
            cs.setInt(7, room_id);
            cs.setBigDecimal(8, ca.getInteres());
            cs.setBigDecimal(9, ca.getMensual());
            cs.setDate( 10, ca.getFecha());
            cs.setDate( 11, ca.getFechaIngreso());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Cálculo de alquiler insertado exitosamente");
            
            refrescarComboBoxPisos(paramNombrePiso);   
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

    String[] columnasMostradas = {"Id", "Cliente", "Alquiler","Cuotas", "Piso", "Cuarto"};
    String[] columnasBD = {"id", "nombre_cliente", "rent", "total",  "nombre_piso", "numcuarto", "interes","mensual", "fecha", "fecha_ingreso" ,"total_rent",  "garantia"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    tbTotalCalculo.setModel(modelo);

    String sql = "SELECT rent_calculation.id, datos_cli_prov.nombre as nombre_cliente, rent, garantia,interes,mensual, fecha, rent_calculation.fecha_ingreso, total, total_rent, piso.piso as nombre_piso, cuarto.numcuarto FROM rent_calculation INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id INNER JOIN piso ON rent_calculation.floor_id = piso.id INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id";

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
    
    public void MostrarCalculos(JTable tbCalculoAlquiler, int cuotas, Date fecha, double total_rent, double interes  ){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Ord");
        modelo.addColumn("Fecha");
        modelo.addColumn("Saldo");
        modelo.addColumn("Capital");
        modelo.addColumn("Interes");
        modelo.addColumn("Por Pagar");
        
        for (int i = 1; i <= cuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza al mes siguiente la fecha
            calendar.add(Calendar.MONTH, i);
            
            // Esto ajustar el día al último día del mes
            int ultimoDiaMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int diaSeleccionado = calendar.get(Calendar.DAY_OF_MONTH);
            int diaAjustado = Math.min(ultimoDiaMes, diaSeleccionado);
            calendar.set(Calendar.DAY_OF_MONTH, diaAjustado);
            
            double cociente = (total_rent / cuotas) * (i - 1);
            double cocienteRedondeado = Math.round(cociente * 100.0) / 100.0;
            
            // Calcular el monto restante por pagar en cada cuota
            double saldo = total_rent - cocienteRedondeado;
            double saldoRedondeado = Math.round(saldo * 100.0) / 100.0;
            
            // Calcular el Interes multiplicando el saldo por el Interes (dividido por 100)
            double interesCalculado = saldoRedondeado * (interes / 100);
            double interesCalculadoRedondeado = Math.round(interesCalculado * 100.0) / 100.0;
            
            sumaInteresAcumulativa += interesCalculadoRedondeado;
            sumaInteresAcumulativa = Math.round(sumaInteresAcumulativa * 100.0) / 100.0;
            
            //Formatear la fecha en dia mes año
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            
            modelo.addRow(new Object[] { 
                i, 
                dateFormat.format(calendar.getTime()), 
                saldoRedondeado, 
                "",
                interesCalculadoRedondeado, 
                ""
            });
        }
        
        double porPagar = calcularPorPagar(total_rent, cuotas);
        porPagar = Math.round(porPagar * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(porPagar, i, 5); // 5 es el índice de la columna "Por Pagar"
        }
        
        // Bucle para calcular y actualizar la columna "Capital"
        for (int i = 0; i < modelo.getRowCount(); i++) {
            
            double porPagarActual = (double) modelo.getValueAt(i, 5); // 5 es el índice de la columna "Por Pagar"
            double interesActual = (double) modelo.getValueAt(i, 4); // 4 es el índice de la columna "Interes"

            // Calcular el valor de la columna "Capital"
            double capital = porPagarActual - interesActual;
            capital = Math.round(capital * 100.0) / 100.0;
            sumaCapitalAcumulativa += capital;
            sumaCapitalAcumulativa = Math.round(sumaCapitalAcumulativa * 100.0) / 100.0;
            
            modelo.setValueAt(capital, i, 3); // 3 es el índice de la columna "Capital"
        }
        
        tbCalculoAlquiler.setModel(modelo);
    }

    public void MostrarImporteDiario(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Orden");
        modelo.addColumn("Fecha");
        modelo.addColumn("Importe");
        
        double totalCuotas = cuotas * 30;
        
        for (int i = 0; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza al mes siguiente la fecha
            calendar.add(Calendar.DAY_OF_YEAR, i);
            
            //Formatear la fecha en dia mes año
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            
            modelo.addRow(new Object[] { 
                i, 
                dateFormat.format(calendar.getTime()), 
                "",
            });
            
        }
        
        double importeDiario = calcularImporteDiario(total_rent, cuotas);
        importeDiario = Math.round(importeDiario * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeDiario, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeDiario :" + importeDiario);
        
        tbCalculoImporte.setModel(modelo);
    }
    
    public void MostrarImporteSemanal(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Orden");
        modelo.addColumn("Fecha");
        modelo.addColumn("Importe");
        
        double totalCuotas = cuotas * 4;
        
        for (int i = 0; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza de semana a semana
            calendar.add(Calendar.WEEK_OF_YEAR, i);
            
            //Formatear la fecha en dia mes año
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            
            modelo.addRow(new Object[] { 
                i, 
                dateFormat.format(calendar.getTime()), 
                "",
            });
            
        }
        
        double importeSemanal = calcularImporteSemanal(total_rent, cuotas);
        importeSemanal = Math.round(importeSemanal * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeSemanal, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeSemanal :" + importeSemanal);
        
        tbCalculoImporte.setModel(modelo);
    }
        
    public void MostrarImporteQuincenal(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Orden");
        modelo.addColumn("Fecha");
        modelo.addColumn("Importe");
        
        double totalCuotas = cuotas * 2;
        
         for (int i = 0; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            // Esto avanza de 15 en 15 días
            int diasAgregados = i * 15;
            calendar.add(Calendar.DAY_OF_YEAR, diasAgregados);
            
            //Formatear la fecha en dia mes año
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            
            modelo.addRow(new Object[] { 
                i, 
                dateFormat.format(calendar.getTime()), 
                "",
            });
            
        }
        
        double importeQuincenal = calcularImporteQuincenal(total_rent, cuotas);
        importeQuincenal = Math.round(importeQuincenal * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeQuincenal, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeQuincenal :" + importeQuincenal);
        
        tbCalculoImporte.setModel(modelo);
    }
    
     public void MostrarImporteMensual(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Orden");
        modelo.addColumn("Fecha");
        modelo.addColumn("Importe");
        
         for (int i = 0; i <= cuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza al mes siguiente la fecha
            calendar.add(Calendar.MONTH, i);
            
            // Esto ajustar el día al último día del mes
            int ultimoDiaMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int diaSeleccionado = calendar.get(Calendar.DAY_OF_MONTH);
            int diaAjustado = Math.min(ultimoDiaMes, diaSeleccionado);
            calendar.set(Calendar.DAY_OF_MONTH, diaAjustado);
            
            //Formatear la fecha en dia mes año
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            
            modelo.addRow(new Object[] { 
                i, 
                dateFormat.format(calendar.getTime()), 
                "",
            });
            
        }
        
        double importeMensual = calcularImporteMensual(total_rent, cuotas);
        importeMensual = Math.round(importeMensual * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeMensual, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeMensual :" + importeMensual);
        
        tbCalculoImporte.setModel(modelo);
    }
    
    public void SeleccionarCalculoAlquiler(JTable paramTablaCalculosAlquiler, JTextField paramId, JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramInteres, JTextField paramTotal, JTextField paramTotalAlquiler, JDateChooser paramFecha, JDateChooser paramFechaIngreso, JTextField paramMensual) {
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
                paramInteres.setText(rs.getString("interes"));
                paramTotal.setText(rs.getString("total"));
                paramTotalAlquiler.setText(rs.getString("total_rent"));

                // Obtener el nombre del cliente y establecerlo en el JComboBox
                String nombreCliente = rs.getString("nombre");
                paramNombreCliente.setSelectedItem(nombreCliente);

                // Obtener el nombre del piso y establecerlo en el JComboBox
                String nombrePiso = rs.getString("piso");
                paramNombrePiso.setSelectedItem(nombrePiso);

                // Obtener el nombre del cuarto y establecerlo en el JComboBox
                String nombreCuarto = rs.getString("numcuarto");
                paramNombreCuarto.setSelectedItem(nombreCuarto);
                
                paramMensual.setText(rs.getString("mensual"));
                
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

    public void EliminarCalculoAlquiler(JTextField id) {
    CConexion objetoConexion = new CConexion();
    String obtenerDatosCuarto = "SELECT floor_id, room_id FROM rent_calculation WHERE id=?";
    
    try {
        // Obtener los datos del cuarto asociado al cálculo
        PreparedStatement psDatosCuarto = objetoConexion.estableceConexion().prepareStatement(obtenerDatosCuarto);
        psDatosCuarto.setInt(1, Integer.parseInt(id.getText()));
        ResultSet rsDatosCuarto = psDatosCuarto.executeQuery();

        int floorId = 0;
        int roomId = 0;

        if (rsDatosCuarto.next()) {
            floorId = rsDatosCuarto.getInt("floor_id");
            roomId = rsDatosCuarto.getInt("room_id");
        }

        // Eliminar el cálculo de alquiler
        String eliminarCalculo = "DELETE FROM rent_calculation WHERE id=?";
        PreparedStatement psEliminarCalculo = objetoConexion.estableceConexion().prepareStatement(eliminarCalculo);
        psEliminarCalculo.setInt(1, Integer.parseInt(id.getText()));
        psEliminarCalculo.executeUpdate();

        // Liberar el cuarto asociado (marcar como desocupado)
        String liberarCuarto = "UPDATE cuarto SET ocupado=false WHERE id=?";
        PreparedStatement psLiberarCuarto = objetoConexion.estableceConexion().prepareStatement(liberarCuarto);
        psLiberarCuarto.setInt(1, roomId);
        psLiberarCuarto.executeUpdate();

        JOptionPane.showMessageDialog(null, "Se eliminó correctamente el cálculo de alquiler y se liberó el cuarto asociado.");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el cálculo de alquiler: " + e.toString());
    }
}

    public void ModificarCalculoAlquiler(JTable paramTablaCalculosAlquiler, JTextField paramId, JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramInteres, JTextField paramTotal, JTextField paramTotalAlquiler, JDateChooser paramFecha, JDateChooser paramFechaIngreso, JTextField paramMensual) {
    try {
        int fila = paramTablaCalculosAlquiler.getSelectedRow();
        if (fila >= 0) {
            // Obtén el ID de la fila seleccionada
            String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();

            // Obtén el ID del cuarto actualmente ocupado
            int room_id_actual = obtenerIdCuartoPorCalculoAlquiler(Integer.parseInt(idSeleccionado));

            // Actualizar el cálculo de alquiler
            CConexion objetoConexion = new CConexion();
            String actualizarCalculo = "UPDATE rent_calculation SET client_id=?, rent=?, garantia=?, total=?, total_rent=?, floor_id=?, room_id=?, interes=?, mensual=?, fecha=?, fecha_ingreso=? WHERE id=?";

            PreparedStatement psActualizarCalculo = objetoConexion.estableceConexion().prepareStatement(actualizarCalculo);

            psActualizarCalculo.setInt(1, obtenerIdNombre((String) paramNombreCliente.getSelectedItem()));
            psActualizarCalculo.setBigDecimal(2, new BigDecimal(paramRent.getText()));
            psActualizarCalculo.setBigDecimal(3, new BigDecimal(paramGarantia.getText()));
            psActualizarCalculo.setInt(4, Integer.parseInt(paramTotal.getText()));
            psActualizarCalculo.setBigDecimal(5, new BigDecimal(paramTotalAlquiler.getText())); 
            psActualizarCalculo.setInt(6, obtenerIdPiso((String) paramNombrePiso.getSelectedItem()));
            psActualizarCalculo.setInt(7, obtenerIdCuartoPorPiso((String) paramNombreCuarto.getSelectedItem(), obtenerIdPiso((String) paramNombrePiso.getSelectedItem())));
            psActualizarCalculo.setBigDecimal(8, new BigDecimal(paramInteres.getText()));
            psActualizarCalculo.setBigDecimal(9, new BigDecimal(paramMensual.getText()));
            psActualizarCalculo.setDate(10, new Date(paramFecha.getDate().getTime()));
            psActualizarCalculo.setDate(11, new Date(paramFechaIngreso.getDate().getTime()));
            psActualizarCalculo.setInt(12, Integer.parseInt(idSeleccionado));

            psActualizarCalculo.executeUpdate();

            // Liberar el cuarto actualmente ocupado (marcar como desocupado)
            liberarCuarto(room_id_actual);

            // Obtener el ID del nuevo cuarto
            int room_id_nuevo = obtenerIdCuartoPorPiso((String) paramNombreCuarto.getSelectedItem(), obtenerIdPiso((String) paramNombrePiso.getSelectedItem()));

            // Actualizar el estado del nuevo cuarto (marcar como ocupado)
            actualizarEstadoCuarto(room_id_nuevo);
            
            
            JOptionPane.showMessageDialog(null, "Cálculo de alquiler actualizado exitosamente");

        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al modificar el cálculo de alquiler: " + e.toString());
    }
}
    
    public void FiltrarRentCalculation(JTable paramtbTotalCalculo, String searchText) {
            CConexion objetoConexion = new CConexion();
            DefaultTableModel modelo = (DefaultTableModel) paramtbTotalCalculo.getModel();
            TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
            paramtbTotalCalculo.setRowSorter(ordenarTabla);

            String sql = "";
            modelo.setRowCount(0); // Limpiar filas existentes en el modelo

            if (!searchText.isEmpty()) {
                sql = "SELECT rent_calculation.id, datos_cli_prov.nombre as nombre_cliente, rent, total, piso.piso as nombre_piso, cuarto.numcuarto FROM rent_calculation " +
                      "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                      "INNER JOIN piso ON rent_calculation.floor_id = piso.id " +
                      "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
                      "WHERE datos_cli_prov.nombre LIKE '%" + searchText + "%'";
            } else {
                sql = "SELECT rent_calculation.id, datos_cli_prov.nombre as nombre_cliente, rent, total, piso.piso as nombre_piso, cuarto.numcuarto FROM rent_calculation " +
                      "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                      "INNER JOIN piso ON rent_calculation.floor_id = piso.id " +
                      "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id";
            }

            try (Statement st = objetoConexion.estableceConexion().createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    String[] datos = new String[6];
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    datos[5] = rs.getString(6);

                    modelo.addRow(datos);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
            }
    }

    /******************************************************************************************************/
    
    public int obtenerNumeroCuotas(String totalText) {
        int cuotas = 0;
        try {
            cuotas = Integer.parseInt( totalText);
        } catch (NumberFormatException e) {
         // Manejar el caso en el que el texto no sea un número
         e.printStackTrace();
         }
        return cuotas;
    }
    
    private int obtenerIdCuartoPorCalculoAlquiler(int idCalculoAlquiler) {
        CConexion objetoConexion = new CConexion();
        String sql = "SELECT room_id FROM rent_calculation WHERE id=?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, idCalculoAlquiler);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("room_id");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del cuarto por cálculo de alquiler: " + e.toString());
        }

        return -1; // Retorno por defecto en caso de error
    }

    private void liberarCuarto(int room_id) {
        CConexion objetoConexion = new CConexion();
        String liberarCuarto = "UPDATE cuarto SET ocupado=false WHERE id=?";

        try {
            java.sql.CallableStatement cs = objetoConexion.estableceConexion().prepareCall(liberarCuarto);
            cs.setInt(1, room_id);
            cs.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al liberar el cuarto: " + e.toString());
        }
    }

    private boolean cuartoEstaOcupado(int room_id) {
    CConexion objetoConexion = new CConexion();
    String consulta = "SELECT ocupado FROM cuarto WHERE id = ?";

    try {
        PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(consulta);
        pst.setInt(1, room_id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getBoolean("ocupado");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al verificar si el cuarto está ocupado: " + e.toString());
    }

    return true; // Retorno por defecto en caso de error
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
