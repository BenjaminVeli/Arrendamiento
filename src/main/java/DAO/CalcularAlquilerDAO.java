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
    
    // Este es para las filas de Por Pagar
    public double calcularPorPagar(double total_rent, int cuotas) {
        return  (sumaInteresAcumulativa + total_rent) / cuotas;
    }
    
    //Este es la suma de esas filas de Por Pagar
    public double calcularSumaMensual(double porPagar, int cuotas) {
        return porPagar * cuotas;
    }
    
    // Método para obtener el valor de "por_pagar" desde la base de datos de la tabla importe_mensual
    public double obtenerPorPagar(int clientRentId, int ord) {
        CConexion objetoConexion = new CConexion();
        try {
            String sql = "SELECT por_pagar FROM importe_mensual WHERE rent_calculation_id = ? AND ord = ?";
            try (PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql)) {
                pst.setInt(1, clientRentId);
                pst.setInt(2, ord);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("por_pagar");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al obtener el valor de 'por_pagar': " + e.toString());
        }
        return 0.0; // Valor predeterminado si hay algún error
    }

    // Método para obtener el valor de "interes" desde la base de datos de la tabla importe_mensual
    public double obtenerInteres(int clientRentId, int ord) {
        CConexion objetoConexion = new CConexion();
        try {
            String sql = "SELECT interes FROM importe_mensual WHERE rent_calculation_id = ? AND ord = ?";
            try (PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql)) {
                pst.setInt(1, clientRentId);
                pst.setInt(2, ord);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("interes");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al obtener el valor de 'interes': " + e.toString());
        }
        return 0.0; // Valor predeterminado si hay algún error
    }
    
    public double Importe(double sumaCapital, double sumaInteres){
        return sumaCapital + sumaInteres;
    }
    
    public double calcularImporteDiario(double sumaCapital, double sumaInteres, int cuotas) {
        return (sumaCapital + sumaInteres) / (cuotas * 30);
    }
    
    public double calcularImporteSemanal(double sumaCapital, double sumaInteres, int cuotas) {
        return  (sumaCapital + sumaInteres) / (cuotas * 4);
    }
    
    public double calcularImporteQuincenal(double sumaCapital, double sumaInteres, int cuotas) {
        return (sumaCapital + sumaInteres) / (cuotas * 2);
    }
    
    public double calcularImporteMensual(double sumaCapital, double sumaInteres, int cuotas) {
        return  (sumaCapital + sumaInteres) / cuotas;
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
    public void insertarCalculoAlquiler(JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramInteres, JTextField paramTotal, JDateChooser paramFecha, JDateChooser paramFechaIngreso, JTextField paramMensual, JComboBox paramtipoPago, JTextField parampagoDiario, JTextField parampagoSem, JTextField paramQuincenal) {
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
        ca.setTipoPago((String) paramtipoPago.getSelectedItem());
        
        ca.setPagoDiario(new BigDecimal(parampagoDiario.getText()));
        ca.setPagoSem(new BigDecimal(parampagoSem.getText()));
        ca.setQuincenal(new BigDecimal(paramQuincenal.getText()));

        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO rent_calculation (client_id, rent, garantia, total, total_rent, floor_id, room_id,interes,mensual, fecha, fecha_ingreso, tipo_pago, pago_diario, pago_sem, quincenal) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)";

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
            cs.setString( 12, ca.getTipoPago());
            cs.setBigDecimal(13, ca.getPagoDiario());
            cs.setBigDecimal(14, ca.getPagoSem());
            cs.setBigDecimal(15, ca.getQuincenal());

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

    String[] columnasMostradas = {"Id", "Cliente","Alquiler", "Cuotas", "Piso", "Cuarto"};
    String[] columnasBD = {"id", "nombre_cliente", "rent", "total",  "nombre_piso", "numcuarto", "interes","mensual", "fecha", "fecha_ingreso" ,"total_rent",  "garantia" ,  "tipo_pago" , "pago_diario" , "pago_sem" , "quincenal","dni_propietario","ruc","direccion_propietario","celular"};

    for (int i = 0; i < columnasMostradas.length; i++) {
        modelo.addColumn(columnasMostradas[i]);
    }

    tbTotalCalculo.setModel(modelo);

    String sql = "SELECT rent_calculation.id, datos_cli_prov.nombre as nombre_cliente, datos_cli_prov.dni_propietario, datos_cli_prov.ruc, datos_cli_prov.direccion_propietario, datos_cli_prov.celular, rent, garantia,interes,mensual, pago_diario, tipo_pago, pago_sem, quincenal, fecha, rent_calculation.fecha_ingreso, total, total_rent, piso.piso as nombre_piso, cuarto.numcuarto FROM rent_calculation INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id INNER JOIN piso ON rent_calculation.floor_id = piso.id INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id";

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
    
    public void insertarImporteMensual(String nombreCliente, int cuotas, Date fecha, double total_rent, double interes, JTextField txtSumCapital, JTextField txtSumInteres, JTextField txtSumMensual){
        
        CConexion objetoConexion = new CConexion();
        
        try {
             // Obtener el id de rent_calculation
            int clientRentId = obtenerIdRentCalculation(nombreCliente);
            
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
            
            // Obtener la fecha como java.sql.Date
            java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
            
            // Calcular el monto restante por pagar en cada cuota
            double cociente = (total_rent / cuotas) * (i - 1);
            double cocienteRedondeado = Math.round(cociente * 100.0) / 100.0;
            double saldo = total_rent - cocienteRedondeado;
            double saldoRedondeado = Math.round(saldo * 100.0) / 100.0;
            
            // Calcular el Interes multiplicando el saldo por el Interes (dividido por 100)
            double interesCalculado = saldoRedondeado * (interes / 100);
            double interesCalculadoRedondeado = Math.round(interesCalculado * 100.0) / 100.0;
            
            sumaInteresAcumulativa += interesCalculadoRedondeado;
            sumaInteresAcumulativa = Math.round(sumaInteresAcumulativa * 100.0) / 100.0;
            
            // Insertar a la base de datos
            String sql = "INSERT INTO importe_mensual (rent_calculation_id, ord, fecha, saldo, capital, interes, por_pagar, sum_capital, sum_interes, sum_porPagar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try {
                PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);

                pst.setInt(1, clientRentId); // Esta inserción esta bien
                pst.setInt(2, i); // Esta inserción esta bien
                pst.setDate(3, fechaSQL); // Esta inserción esta bien
                pst.setBigDecimal(4, new BigDecimal(saldoRedondeado)); // Esta inserción esta bien
                pst.setBigDecimal(5, new BigDecimal(0.0));
                pst.setBigDecimal(6, new BigDecimal(interesCalculadoRedondeado)); // Esta inserción esta bien
                pst.setBigDecimal(7, new BigDecimal(0.0)); // Puedes establecer a 0 inicialmente, ya que se calculará después del Bucle A
                pst.setBigDecimal(8, new BigDecimal(saldoRedondeado));
                pst.setBigDecimal(9, new BigDecimal(sumaInteresAcumulativa));
                pst.setBigDecimal(10, new BigDecimal(0.0)); // Puedes establecer a 0 inicialmente, ya que se calculará después del Bucle A

                int resultado = pst.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_mensual.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_mensual: " + e.toString());
            }
            
        }
            
            // Después del Bucle A, calcular valores constantes en el Bucle B
            for (int i = 1; i <= cuotas; i++) {
                double porPagar = (sumaInteresAcumulativa + total_rent) / cuotas;
                porPagar = Math.round(porPagar * 100.0) / 100.0;

                // Calcular la suma total de porPagar
                double sumPorPagar = calcularSumaMensual(porPagar, cuotas);
                
                // Obtener el valor actualizado de sumaInteresAcumulativa
                double sumInteresAcumulativaActualizado = obtenerSumaInteresAcumulativa();
                
                // Actualizar los registros en la base de datos con los valores constantes calculados
                actualizarRegistrosConstantes(clientRentId, i, porPagar, sumPorPagar, sumInteresAcumulativaActualizado, total_rent);
                
                // Bucle para calcular y actualizar la columna "Capital"
                for (int j = 0; j < i; j++) {
                    double porPagarActual = obtenerPorPagar(clientRentId, j + 1); // Asegúrate de tener un método similar para obtener el valor de "por_pagar" desde la base de datos
                    double interesActual = obtenerInteres(clientRentId, j + 1); // Asegúrate de tener un método similar para obtener el valor de "interes" desde la base de datos

                    // Calcular el valor de la columna "Capital"
                    double capital = porPagarActual - interesActual;
                    capital = Math.round(capital * 100.0) / 100.0;

                    // Actualizar el registro en la base de datos con el nuevo valor de "capital"
                    actualizarCapital(clientRentId, j + 1, capital);
                }
                
            }

        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error general al insertar el registro en importe_mensual: " + e.toString());
        }
    }
    
    // Método para actualizar los registros en la base de datos con valores constantes de la tabla importe_mensual
    public void actualizarRegistrosConstantes(int clientRentId, int ord, double porPagar, double sumPorPagar, double sumInteresAcumulativa, double total_rent) {
        CConexion objetoConexion = new CConexion();
        try {
            // Sentencia SQL UPDATE para actualizar los registros en la base de datos
            String sqlUpdate = "UPDATE importe_mensual SET por_pagar = ?, sum_porPagar = ?, sum_interes = ?, sum_capital = ? WHERE rent_calculation_id = ? AND ord = ?";

            try (PreparedStatement pstUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate)) {
                pstUpdate.setBigDecimal(1, new BigDecimal(porPagar));
                pstUpdate.setBigDecimal(2, new BigDecimal(sumPorPagar));
                pstUpdate.setBigDecimal(3, new BigDecimal(sumInteresAcumulativa));
                pstUpdate.setBigDecimal(4, new BigDecimal(total_rent));
                pstUpdate.setInt(5, clientRentId);
                pstUpdate.setInt(6, ord);

                int resultadoUpdate = pstUpdate.executeUpdate();

                if (resultadoUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Registros constantes actualizados correctamente en importe_mensual.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los registros constantes en importe_mensual.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar los registros constantes en importe_mensual: " + e.toString());
        }
    }
    
    // Método para actualizar el campo "capital" en la base de datos de la tabla importe_mensual
    public void actualizarCapital(int clientRentId, int ord, double capital) {
        CConexion objetoConexion = new CConexion();
        try {
            // Sentencia SQL UPDATE para actualizar el campo "capital" en la base de datos
            String sqlUpdate = "UPDATE importe_mensual SET capital = ? WHERE rent_calculation_id = ? AND ord = ?";

            try (PreparedStatement pstUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate)) {
                pstUpdate.setBigDecimal(1, new BigDecimal(capital));
                pstUpdate.setInt(2, clientRentId);
                pstUpdate.setInt(3, ord);

                int resultadoUpdate = pstUpdate.executeUpdate();

                if (resultadoUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Registro del campo capital actualizado correctamente en importe_mensual.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el campo capital de los registros en importe_mensual.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar el campo capital de los registros en importe_mensual: " + e.toString());
        }
    }
    
    public DefaultTableModel  MostrarCalculos(JTextField txtSumCapital, JTextField txtSumInteres, 
                                                                             JTextField txtSumMensual, int cuotas, Date fecha, double total_rent, double interes){
        
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
        
        // Asignar valores a los campos del formulario
        double interesAcumulativa = obtenerSumaInteresAcumulativa();
        interesAcumulativa = Math.round(interesAcumulativa * 100.0) / 100.0;
        txtSumInteres.setText(String.valueOf(interesAcumulativa));

        double sumaMensual = calcularSumaMensual(porPagar, cuotas);
        sumaMensual = Math.round(sumaMensual * 100.0) / 100.0;
        txtSumMensual.setText(String.valueOf(sumaMensual));

        double sumaCapital = obtenerSumaCapitalAcumulativa();
        sumaCapital = Math.round(sumaCapital * 100.0) / 100.0;
        txtSumCapital.setText(String.valueOf(sumaCapital));
        
        return modelo;
        
    }

    public void MostrarImporteDiario(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Orden");
        modelo.addColumn("Fecha");
        modelo.addColumn("Importe");
        
        double totalCuotas = cuotas * 30;
        
        for (int i = 0; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza dia a dia  siguiente la fecha
            calendar.add(Calendar.DAY_OF_YEAR, i);
            
            //Formatear la fecha en dia mes año
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            
            modelo.addRow(new Object[] { 
                i, 
                dateFormat.format(calendar.getTime()), 
                "",
            });
            
        }
        
        double importeDiario = calcularImporteDiario(sumaCapital, sumaInteres, cuotas);
        importeDiario = Math.round(importeDiario * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeDiario, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeDiario :" + importeDiario);
        
        tbCalculoImporte.setModel(modelo);
    }
    
    public void MostrarImporteSemanal(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        
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
        
        double importeSemanal = calcularImporteSemanal(sumaCapital, sumaInteres, cuotas);
        importeSemanal = Math.round(importeSemanal * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeSemanal, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeSemanal :" + importeSemanal);
        
        tbCalculoImporte.setModel(modelo);
    }
        
    public void MostrarImporteQuincenal(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        
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
        
        double importeQuincenal = calcularImporteQuincenal(sumaCapital, sumaInteres, cuotas);
        importeQuincenal = Math.round(importeQuincenal * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeQuincenal, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeQuincenal :" + importeQuincenal);
        
        tbCalculoImporte.setModel(modelo);
    }
    
     public void MostrarImporteMensual(JTable tbCalculoImporte, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        
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
        
        double importeMensual = calcularImporteMensual(sumaCapital, sumaInteres, cuotas);
        importeMensual = Math.round(importeMensual * 100.0) / 100.0;
        
        // Actualizar la columna "Por Pagar" en cada fila
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(importeMensual, i, 2); // 2 es el índice de la columna "importe"
        }
        
        System.out.println("importeMensual :" + importeMensual);
        
        tbCalculoImporte.setModel(modelo);
    }
    
    public void SeleccionarCalculoAlquiler(JTable paramTablaCalculosAlquiler,JTextField paramId, JTextField paramDni, JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramInteres, JTextField paramTotal, JTextField paramTotalAlquiler, JDateChooser paramFecha, JDateChooser paramFechaIngreso, JTextField paramMensual , JComboBox paramtipoPago, JTextField parampagoDiario, JTextField parampagoSem, JTextField paramQuincenal, JTextField paramRuc, JTextField paramDireccion, JTextField paramCelular) {
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
                String dniPropietario = rs.getString("dni_propietario");
                paramDni.setText(dniPropietario);
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
                
                String tipoPago = rs.getString("tipo_pago").trim();
                int index = -1;
                
                for (int i = 0; i < paramtipoPago.getItemCount(); i++) {
                    if (paramtipoPago.getItemAt(i).equals(tipoPago)) {
                        index = i;
                        break;
                    }
                }
                
                System.out.println("tipoPago: " + tipoPago);
                System.out.println("index: " + index);
                
                paramtipoPago.setSelectedItem(index);
                
                parampagoDiario.setText( rs.getString("pago_diario"));
                parampagoSem.setText( rs.getString("pago_sem"));
                paramQuincenal.setText( rs.getString("quincenal"));
                String Ruc = rs.getString("ruc");
                paramRuc.setText(Ruc);
                
                // Manejo de fechas
                java.util.Date fecha = rs.getDate("fecha");
                java.util.Date fechaIngreso = rs.getDate("fecha_ingreso");
                paramFecha.setDate(fecha);
                paramFechaIngreso.setDate(fechaIngreso);
                String direccionPropietario = rs.getString("direccion_propietario");
                paramDireccion.setText(direccionPropietario);
                String celular = rs.getString("celular");
                paramCelular.setText(celular);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}

    public void ModificarCalculoAlquiler(JTable paramTablaCalculosAlquiler, JTextField paramId, JComboBox<String> paramNombreCliente, JTextField paramRent, JTextField paramGarantia, JComboBox<String> paramNombrePiso, JComboBox<String> paramNombreCuarto, JTextField paramInteres, JTextField paramTotal, JTextField paramTotalAlquiler, JDateChooser paramFecha, JDateChooser paramFechaIngreso, JTextField paramMensual , JComboBox paramtipoPago, JTextField parampagoDiario, JTextField parampagoSem, JTextField paramQuincenal) {
    try {
        int fila = paramTablaCalculosAlquiler.getSelectedRow();
        if (fila >= 0) {
            // Obtén el ID de la fila seleccionada
            String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();

            // Obtén el ID del cuarto actualmente ocupado
            int room_id_actual = obtenerIdCuartoPorCalculoAlquiler(Integer.parseInt(idSeleccionado));

            // Actualizar el cálculo de alquiler
            CConexion objetoConexion = new CConexion();
            String actualizarCalculo = "UPDATE rent_calculation SET client_id=?, rent=?, garantia=?, total=?, total_rent=?, floor_id=?, room_id=?, interes=?, mensual=?, fecha=?, fecha_ingreso=?, tipo_pago=?, pago_diario=?, pago_sem=?, quincenal=? WHERE id=?";

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
            psActualizarCalculo.setString(12, (String) paramtipoPago.getSelectedItem());
            psActualizarCalculo.setBigDecimal(13, new BigDecimal(parampagoDiario.getText()));
            psActualizarCalculo.setBigDecimal(14, new BigDecimal(parampagoSem.getText()));
            psActualizarCalculo.setBigDecimal(15, new BigDecimal(paramQuincenal.getText()));
            psActualizarCalculo.setInt(16, Integer.parseInt(idSeleccionado));

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
    
    public void recalcularImporteMensual(JTable paramTablaCalculosAlquiler, String nombreCliente, int nuevaCuotas , Date fecha, double total_rent, double interes, JTextField txtSumCapital, JTextField txtSumInteres, JTextField txtSumMensual, JTextField totaltxt){
        CConexion objetoConexion = new CConexion();
        
        try {
            int fila = paramTablaCalculosAlquiler.getSelectedRow();
            if (fila >= 0) {
            
            // Obtener el id de rent_calculation
            int clientRentId = obtenerIdRentCalculation(nombreCliente);
            
            // Punto 1: Verificar el valor de totaltxt
            System.out.println("Valor de totaltxt: " + totaltxt.getText());
    
            // Obtener la cantidad original de cuotas
            int cuotasOriginal = obtenerCantidadCuotas(clientRentId);
            System.out.println("Valor de cuotas original: " + cuotasOriginal);
            
            // Eliminar todos los cálculos de alquiler asociados al ID seleccionado
            eliminarCalculosAlquiler(clientRentId);

            // Si la nueva cantidad de cuotas es mayor que la original, sucede esto:
            if (nuevaCuotas > cuotasOriginal) {
                System.out.println("Entrando en el bloque del caso de mayor cantidad de cuotas");
                for (int i = 1; i <= nuevaCuotas; i++) {
                    // Dentro de este bucle for, se encargara de insertar los nuevos calculos de alquiler a "importe_mensual"
                    
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fecha);

                    //Esto avanza al mes siguiente la fecha
                    calendar.add(Calendar.MONTH, i);

                    // Esto ajustar el día al último día del mes
                    int ultimoDiaMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int diaSeleccionado = calendar.get(Calendar.DAY_OF_MONTH);
                    int diaAjustado = Math.min(ultimoDiaMes, diaSeleccionado);
                    calendar.set(Calendar.DAY_OF_MONTH, diaAjustado);
                    
                    // Obtener la fecha como java.sql.Date
                    java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());

                    // Calcular el monto restante por pagar en cada cuota
                    double cociente = (total_rent / nuevaCuotas) * (i - 1);
                    double cocienteRedondeado = Math.round(cociente * 100.0) / 100.0;
                    double saldo = total_rent - cocienteRedondeado;
                    double saldoRedondeado = Math.round(saldo * 100.0) / 100.0;

                    // Calcular el Interes multiplicando el saldo por el Interes (dividido por 100)
                    double interesCalculado = saldoRedondeado * (interes / 100);
                    double interesCalculadoRedondeado = Math.round(interesCalculado * 100.0) / 100.0;

                    sumaInteresAcumulativa += interesCalculadoRedondeado;
                    sumaInteresAcumulativa = Math.round(sumaInteresAcumulativa * 100.0) / 100.0;

                    // Insertar a la base de datos
                    String sql = "INSERT INTO importe_mensual (rent_calculation_id, ord, fecha, saldo, capital, interes, por_pagar, sum_capital, sum_interes, sum_porPagar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);

                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(saldoRedondeado)); // Esta inserción esta bien
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            pst.setBigDecimal(6, new BigDecimal(interesCalculadoRedondeado)); // Esta inserción esta bien
                            pst.setBigDecimal(7, new BigDecimal(0.0)); // Puedes establecer a 0 inicialmente, ya que se calculará después del Bucle A
                            pst.setBigDecimal(8, new BigDecimal(saldoRedondeado));
                            pst.setBigDecimal(9, new BigDecimal(sumaInteresAcumulativa));
                            pst.setBigDecimal(10, new BigDecimal(0.0)); // Puedes establecer a 0 inicialmente, ya que se calculará después del Bucle A

                            int resultado = pst.executeUpdate();

                            if (resultado > 0) {
                                JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_mensual.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_mensual: " + e.toString());
                        }
                    
                    System.out.println("Iteración " + (i + 1) + " del bucle para las filas de la nueva cuota");
                    System.out.println("Cantidad de cuotas en el caso mayor = " + nuevaCuotas);
                }
                
                // Después del Bucle A, calcular valores constantes en el Bucle B
                for (int i = 1; i <= nuevaCuotas; i++) {
                    double porPagar = (sumaInteresAcumulativa + total_rent) / nuevaCuotas;
                    porPagar = Math.round(porPagar * 100.0) / 100.0;

                    // Calcular la suma total de porPagar
                    double sumPorPagar = calcularSumaMensual(porPagar, nuevaCuotas);

                    // Obtener el valor actualizado de sumaInteresAcumulativa
                    double sumInteresAcumulativaActualizado = obtenerSumaInteresAcumulativa();

                    // Actualizar los registros en la base de datos con los valores constantes calculados
                    actualizarRegistrosConstantes(clientRentId, i, porPagar, sumPorPagar, sumInteresAcumulativaActualizado, total_rent);

                    // Bucle para calcular y actualizar la columna "Capital"
                    for (int j = 0; j < i; j++) {
                        double porPagarActual = obtenerPorPagar(clientRentId, j + 1); // Asegúrate de tener un método similar para obtener el valor de "por_pagar" desde la base de datos
                        double interesActual = obtenerInteres(clientRentId, j + 1); // Asegúrate de tener un método similar para obtener el valor de "interes" desde la base de datos

                        // Calcular el valor de la columna "Capital"
                        double capital = porPagarActual - interesActual;
                        capital = Math.round(capital * 100.0) / 100.0;

                        // Actualizar el registro en la base de datos con el nuevo valor de "capital"
                        actualizarCapital(clientRentId, j + 1, capital);
                    }

                }
                
            }
            // Si la nueva cantidad de cuotas es menor que la original, sucede esto:
            else if (nuevaCuotas < cuotasOriginal) {
                System.out.println("Entrando en el bloque del caso de menor cantidad de cuotas");
                for (int i = 1; i <= nuevaCuotas; i++) {
                     // Dentro de este bucle for, se encargara de insertar los nuevos calculos de alquiler a "importe_mensual"
                    
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fecha);

                    //Esto avanza al mes siguiente la fecha
                    calendar.add(Calendar.MONTH, i);

                    // Esto ajustar el día al último día del mes
                    int ultimoDiaMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int diaSeleccionado = calendar.get(Calendar.DAY_OF_MONTH);
                    int diaAjustado = Math.min(ultimoDiaMes, diaSeleccionado);
                    calendar.set(Calendar.DAY_OF_MONTH, diaAjustado);
                    
                    // Obtener la fecha como java.sql.Date
                    java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());

                    // Calcular el monto restante por pagar en cada cuota
                    double cociente = (total_rent / nuevaCuotas) * (i - 1);
                    double cocienteRedondeado = Math.round(cociente * 100.0) / 100.0;
                    double saldo = total_rent - cocienteRedondeado;
                    double saldoRedondeado = Math.round(saldo * 100.0) / 100.0;

                    // Calcular el Interes multiplicando el saldo por el Interes (dividido por 100)
                    double interesCalculado = saldoRedondeado * (interes / 100);
                    double interesCalculadoRedondeado = Math.round(interesCalculado * 100.0) / 100.0;

                    sumaInteresAcumulativa += interesCalculadoRedondeado;
                    sumaInteresAcumulativa = Math.round(sumaInteresAcumulativa * 100.0) / 100.0;

                    // Insertar a la base de datos
                    String sql = "INSERT INTO importe_mensual (rent_calculation_id, ord, fecha, saldo, capital, interes, por_pagar, sum_capital, sum_interes, sum_porPagar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);

                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(saldoRedondeado)); // Esta inserción esta bien
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            pst.setBigDecimal(6, new BigDecimal(interesCalculadoRedondeado)); // Esta inserción esta bien
                            pst.setBigDecimal(7, new BigDecimal(0.0)); // Puedes establecer a 0 inicialmente, ya que se calculará después del Bucle A
                            pst.setBigDecimal(8, new BigDecimal(saldoRedondeado));
                            pst.setBigDecimal(9, new BigDecimal(sumaInteresAcumulativa));
                            pst.setBigDecimal(10, new BigDecimal(0.0)); // Puedes establecer a 0 inicialmente, ya que se calculará después del Bucle A

                            int resultado = pst.executeUpdate();

                            if (resultado > 0) {
                                JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_mensual.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_mensual: " + e.toString());
                        }
                    System.out.println("Iteración " + (i + 1) + " del bucle para las filas de la nueva cuota");
                    System.out.println("Cantidad de cuotas en el caso menor = " + nuevaCuotas);
                }
                
                // Después del Bucle A, calcular valores constantes en el Bucle B
                for (int i = 1; i <= nuevaCuotas; i++) {
                    double porPagar = (sumaInteresAcumulativa + total_rent) / nuevaCuotas;
                    porPagar = Math.round(porPagar * 100.0) / 100.0;

                    // Calcular la suma total de porPagar
                    double sumPorPagar = calcularSumaMensual(porPagar, nuevaCuotas);

                    // Obtener el valor actualizado de sumaInteresAcumulativa
                    double sumInteresAcumulativaActualizado = obtenerSumaInteresAcumulativa();

                    // Actualizar los registros en la base de datos con los valores constantes calculados
                    actualizarRegistrosConstantes(clientRentId, i, porPagar, sumPorPagar, sumInteresAcumulativaActualizado, total_rent);

                    // Bucle para calcular y actualizar la columna "Capital"
                    for (int j = 0; j < i; j++) {
                        double porPagarActual = obtenerPorPagar(clientRentId, j + 1); // Asegúrate de tener un método similar para obtener el valor de "por_pagar" desde la base de datos
                        double interesActual = obtenerInteres(clientRentId, j + 1); // Asegúrate de tener un método similar para obtener el valor de "interes" desde la base de datos

                        // Calcular el valor de la columna "Capital"
                        double capital = porPagarActual - interesActual;
                        capital = Math.round(capital * 100.0) / 100.0;

                        // Actualizar el registro en la base de datos con el nuevo valor de "capital"
                        actualizarCapital(clientRentId, j + 1, capital);
                    }

                }
            }
            
            else if (nuevaCuotas == 0) {
                System.out.println("Entrando en el bloque de que no hay diferencia de cuotas");
                for (int i = 0; i < Math.abs(nuevaCuotas); i++) {
                    // Eliminar la fila correspondiente en la tabla "importe_mensual"
                    JOptionPane.showMessageDialog(null, "No hay cantidad de cuotas");
                }
            }
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al recalcular la tabla importe_mensual: " + e.toString());
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
    
    private int obtenerCantidadCuotas(int rentCalculationId) {
        int cantidadCuotas = -1;
        CConexion objetoConexion = new CConexion();

        String sql = "SELECT COUNT(*) AS total FROM importe_mensual WHERE rent_calculation_id = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, rentCalculationId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cantidadCuotas = rs.getInt("total");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la cantidad de cuotas: " + e.toString());
        }

        return cantidadCuotas;
    }
    
    private void eliminarCalculosAlquiler(int rentCalculationId) {
        CConexion objetoConexion = new CConexion();

        String sql = "DELETE FROM importe_mensual WHERE rent_calculation_id = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, rentCalculationId);

            // Ejecutar la sentencia DELETE
            pst.executeUpdate();

            System.out.println("Se eliminaron los cálculos de alquiler asociados al ID: " + rentCalculationId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los cálculos de alquiler: " + e.toString());
        }
    }

    private int obtenerIdRentCalculation(String nombreClienteRenta) {
        int rentCalculationId = -1;
        CConexion objetoConexion = new CConexion();

        // Intentamos obtener el ID de la fila más reciente que coincide con el nombre del cliente y la fecha *NUEVO*
        String sqlSelect = "SELECT id FROM rent_calculation WHERE client_id IN (SELECT id FROM datos_cli_prov WHERE nombre = ?) ORDER BY fecha DESC LIMIT 1";

        try (PreparedStatement pstSelect = objetoConexion.estableceConexion().prepareStatement(sqlSelect)) {
            pstSelect.setString(1, nombreClienteRenta);

            ResultSet rs = pstSelect.executeQuery();

            if (rs.next()) {
                rentCalculationId = rs.getInt("id");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID de rent_calculation: " + e.toString());
        }

        return rentCalculationId;
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