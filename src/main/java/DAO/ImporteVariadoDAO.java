package DAO;

import Conexion.CConexion;
import java.sql.PreparedStatement;
import java.sql.Connection;
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
import DAO.CalcularAlquilerDAO;
import java.sql.SQLException;

public class ImporteVariadoDAO {

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
    
    // Insertar datos hacia la tabla importe_variado
    public void actualizarRegistrosConstantes(int clientRentId, int ord, double importe, double importeTotal){
        CConexion objetoConexion = new CConexion();
        
        try {
            // Sentencia SQL UPDATE para actualizar los registros en la base de datos
            String sqlUpdate = "UPDATE importe_variado SET importe = ?, sum_importe = ? WHERE rent_calculation_id = ? AND ord = ?";
            
            try (PreparedStatement pstUpdate = objetoConexion.estableceConexion().prepareStatement(sqlUpdate)) {
                pstUpdate.setBigDecimal(1, new BigDecimal(importe));
                pstUpdate.setBigDecimal(2, new BigDecimal(importeTotal));
                pstUpdate.setInt(3, clientRentId);
                pstUpdate.setInt(4, ord);

                int resultadoUpdate = pstUpdate.executeUpdate();

                if (resultadoUpdate > 0) {
                    // JOptionPane.showMessageDialog(null, "Registros constantes actualizados correctamente en importe_variado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los registros constantes en importe_variado.");
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar los registros constantes en importe_variado: " + e.toString());
        }
    }
    
    public void insertarCalculosDiarios(String nombreCliente, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            // Obtener el id de rent_calculation
            int clientRentId = ca.obtenerIdRentCalculation(nombreCliente);
            
            double totalCuotas = cuotas * 30;
            
            String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
            // Bucle A
            for (int i = 1; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza dia a dia  siguiente la fecha
            calendar.add(Calendar.DAY_OF_YEAR, i-1);
            
            // Obtener la fecha como java.sql.Date
            java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
            
                try {
                    PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                    
                    pst.setInt(1, clientRentId); // Esta inserción esta bien
                    pst.setInt(2, i); // Esta inserción esta bien
                    pst.setDate(3, fechaSQL); // Esta inserción esta bien
                    pst.setBigDecimal(4, new BigDecimal(0.0)); // Esta inserción esta bien
                    pst.setBigDecimal(5, new BigDecimal(0.0));
                    
                    int resultado = pst.executeUpdate();
                    
                     if (resultado > 0) {
                    // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                }
                
            }
            
            //Bucle B
            for (int i = 1; i <= totalCuotas; i++) {
                double importeDiario = calcularImporteDiario(sumaCapital, sumaInteres, cuotas);
                importeDiario = Math.round(importeDiario * 100.0) / 100.0;
                
                double importeTotal = sumaCapital + sumaInteres;
                importeTotal = Math.round(importeTotal * 100.0) / 100.0;
                
                actualizarRegistrosConstantes(clientRentId, i, importeDiario, importeTotal);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al insertar el registro en la tabla importe_variado: " + e.toString());
        }
        
        System.out.println("Calculos del Importe Diario Insertado");
    }
    
    public void insertarCalculosSemanal(String nombreCliente, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            // Obtener el id de rent_calculation
            int clientRentId = ca.obtenerIdRentCalculation(nombreCliente);
            
            double totalCuotas = cuotas * 4;
            
            String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
            // Bucle A
            for (int i = 1; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza de semana a semana siguiente la fecha
            calendar.add(Calendar.WEEK_OF_YEAR, i-1);
            
            // Obtener la fecha como java.sql.Date
            java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
            
                try {
                    PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                    
                    pst.setInt(1, clientRentId); // Esta inserción esta bien
                    pst.setInt(2, i); // Esta inserción esta bien
                    pst.setDate(3, fechaSQL); // Esta inserción esta bien
                    pst.setBigDecimal(4, new BigDecimal(0.0));
                    pst.setBigDecimal(5, new BigDecimal(0.0));
                    
                    int resultado = pst.executeUpdate();
                    
                     if (resultado > 0) {
                    // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                }
                
            }
            
            //Bucle B
            for (int i = 1; i <= totalCuotas; i++) {
                double importeSemanal = calcularImporteSemanal(sumaCapital, sumaInteres, cuotas);
                importeSemanal = Math.round(importeSemanal * 100.0) / 100.0;
                
                double importeTotal = sumaCapital + sumaInteres;
                importeTotal = Math.round(importeTotal * 100.0) / 100.0;
                
                actualizarRegistrosConstantes(clientRentId, i, importeSemanal, importeTotal);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al insertar el registro en la tabla importe_variado: " + e.toString());
        }
        
        System.out.println("Calculos del Importe Semanal Insertado");
    }
    
    public void insertarCalculosQuincenal(String nombreCliente, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            // Obtener el id de rent_calculation
            int clientRentId = ca.obtenerIdRentCalculation(nombreCliente);
            
            double totalCuotas = cuotas * 2;
            
            String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
            // Bucle A
            for (int i = 1; i <= totalCuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            // Esto avanza de 15 en 15 días
            int diasAgregados = (i-1) * 15;
            calendar.add(Calendar.DAY_OF_YEAR, diasAgregados);
            
            // Obtener la fecha como java.sql.Date
            java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
            
                try {
                    PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                    
                    pst.setInt(1, clientRentId); // Esta inserción esta bien
                    pst.setInt(2, i); // Esta inserción esta bien
                    pst.setDate(3, fechaSQL); // Esta inserción esta bien
                    pst.setBigDecimal(4, new BigDecimal(0.0));
                    pst.setBigDecimal(5, new BigDecimal(0.0));
                    
                    int resultado = pst.executeUpdate();
                    
                     if (resultado > 0) {
                    // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                }
                
            }
            
            //Bucle B
            for (int i = 1; i <= totalCuotas; i++) {
                double importeQuincenal = calcularImporteQuincenal(sumaCapital, sumaInteres, cuotas);
                importeQuincenal = Math.round(importeQuincenal * 100.0) / 100.0;
                
                double importeTotal = sumaCapital + sumaInteres;
                importeTotal = Math.round(importeTotal * 100.0) / 100.0;
                
                actualizarRegistrosConstantes(clientRentId, i, importeQuincenal, importeTotal);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al insertar el registro en la tabla importe_variado: " + e.toString());
        }
        
        System.out.println("Calculos del Importe Quincenal Insertado");
    }
    
    public void insertarCalculosMensual(String nombreCliente, int cuotas, Date fecha, double total_rent, double sumaCapital, double sumaInteres) {
        
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            // Obtener el id de rent_calculation
            int clientRentId = ca.obtenerIdRentCalculation(nombreCliente);
            
            String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
            // Bucle A
            for (int i = 1; i <= cuotas; i++) {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            
            //Esto avanza al mes siguiente la fecha
            calendar.add(Calendar.MONTH, i-1);
            
            // Obtener la fecha como java.sql.Date
            java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
            
                try {
                    PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                    
                    pst.setInt(1, clientRentId); // Esta inserción esta bien
                    pst.setInt(2, i); // Esta inserción esta bien
                    pst.setDate(3, fechaSQL); // Esta inserción esta bien
                    pst.setBigDecimal(4, new BigDecimal(0.0));
                    pst.setBigDecimal(5, new BigDecimal(0.0));
                    
                    int resultado = pst.executeUpdate();
                    
                     if (resultado > 0) {
                    // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_mensual.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                }
                
            }
            
            //Bucle B
            for (int i = 1; i <= cuotas; i++) {
                double importeMensual = calcularImporteMensual(sumaCapital, sumaInteres, cuotas);
                importeMensual = Math.round(importeMensual * 100.0) / 100.0;
                
                double importeTotal = sumaCapital + sumaInteres;
                importeTotal = Math.round(importeTotal * 100.0) / 100.0;
                
                actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al insertar el registro en la tabla importe_variado: " + e.toString());
        }
        
        System.out.println("Calculos del Importe Mensual Insertado");
    }
    
    // "Actualizar" datos hacia la tabla importe_variado
    
    public void recalcularCalculosDiarios(JTable paramTablaCalculosAlquiler, String nombreCliente, int nuevaCuotas, Date fecha, double total_rent, double sumaCapital, 
                                                                    double sumaInteres, JTextField totaltxt){
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            int fila = paramTablaCalculosAlquiler.getSelectedRow();
            if (fila >= 0) {
                
                // Obtén el ID de la fila seleccionada
                String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();
                
                // Obtener el id de rent_calculation
                int clientRentId = Integer.parseInt(idSeleccionado);
                
                // Punto 1: Verificar el valor de totaltxt
                System.out.println("Valor de totaltxt en importe_variado: " + totaltxt.getText());
                
                // Obtener la cantidad original de cuotas
                int cuotasOriginal = ca.obtenerCantidadCuotas(clientRentId);
                System.out.println("Valor de cuotas original en importe_variado: " + cuotasOriginal);
                
                // Eliminar todos los cálculos de alquiler asociados al ID seleccionado
                eliminarImporteVariado(clientRentId);
                
                // Si la nueva cantidad de cuotas es mayor que la original, sucede esto:
                if (nuevaCuotas > cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de mayor cantidad de cuotas en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 30;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza dia a dia  siguiente la fecha
                        calendar.add(Calendar.DAY_OF_YEAR, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteDiario(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas < cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de menor cantidad de cuotas en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 30;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza dia a dia  siguiente la fecha
                        calendar.add(Calendar.DAY_OF_YEAR, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteDiario(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas == cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de que la cantidad de cuotas no cambie en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 30;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza dia a dia  siguiente la fecha
                        calendar.add(Calendar.DAY_OF_YEAR, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteDiario(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al recalcular la tabla importe_variado: " + e.toString());
        }
    }
            
    public void recalcularCalculosSemanal(JTable paramTablaCalculosAlquiler, String nombreCliente, int nuevaCuotas, Date fecha, double total_rent, double sumaCapital, 
                                                                    double sumaInteres, JTextField totaltxt){
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            int fila = paramTablaCalculosAlquiler.getSelectedRow();
            
            if (fila >= 0) {
                // Obtén el ID de la fila seleccionada
                String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();
                
                // Obtener el id de rent_calculation
                int clientRentId = Integer.parseInt(idSeleccionado);
                
                // Punto 1: Verificar el valor de totaltxt
                System.out.println("Valor de totaltxt en importe_variado: " + totaltxt.getText());
                
                // Obtener la cantidad original de cuotas
                int cuotasOriginal = ca.obtenerCantidadCuotas(clientRentId);
                System.out.println("Valor de cuotas original en importe_variado: " + cuotasOriginal);
                
                // Eliminar todos los cálculos de alquiler asociados al ID seleccionado
                eliminarImporteVariado(clientRentId);
                
                // Si la nueva cantidad de cuotas es mayor que la original, sucede esto:
                if (nuevaCuotas > cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de mayor cantidad de cuotas en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 4;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza de semana a semana
                        calendar.add(Calendar.WEEK_OF_YEAR, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteSemanal(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas < cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de menor cantidad de cuotas en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 4;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza de semana a semana
                        calendar.add(Calendar.WEEK_OF_YEAR, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteSemanal(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas == cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de que la cantidad de cuotas no cambie en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 4;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza de semana a semana
                        calendar.add(Calendar.WEEK_OF_YEAR, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteSemanal(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al recalcular la tabla importe_variado: " + e.toString());
        }
    }
            
    public void recalcularCalculosQuincenal(JTable paramTablaCalculosAlquiler, String nombreCliente, int nuevaCuotas, Date fecha, double total_rent, double sumaCapital, 
                                                                    double sumaInteres, JTextField totaltxt){
        
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            int fila = paramTablaCalculosAlquiler.getSelectedRow();
            
            if (fila >= 0) {
                
                // Obtén el ID de la fila seleccionada
                String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();
                
                // Obtener el id de rent_calculation
                int clientRentId =  Integer.parseInt(idSeleccionado);
                
                // Punto 1: Verificar el valor de totaltxt
                System.out.println("Valor de totaltxt en importe_variado: " + totaltxt.getText());
                
                // Obtener la cantidad original de cuotas
                int cuotasOriginal = ca.obtenerCantidadCuotas(clientRentId);
                System.out.println("Valor de cuotas original en importe_variado: " + cuotasOriginal);
                
                // Eliminar todos los cálculos de alquiler asociados al ID seleccionado
                eliminarImporteVariado(clientRentId);
                
                // Si la nueva cantidad de cuotas es mayor que la original, sucede esto:
                if (nuevaCuotas > cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de mayor cantidad de cuotas en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 2;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        // Esto avanza de 15 en 15 días
                        int diasAgregados = (i-1) * 15;
                        calendar.add(Calendar.DAY_OF_YEAR, diasAgregados);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteQuincenal(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas < cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de menor cantidad de cuotas en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 2;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        // Esto avanza de 15 en 15 días
                        int diasAgregados = (i-1) * 15;
                        calendar.add(Calendar.DAY_OF_YEAR, diasAgregados);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteQuincenal(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas == cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de que la cantidad de cuotas no cambie en importe_variado");
                    
                    double totalCuotas = nuevaCuotas * 2;
                    // Bucle A
                    for (int i = 1; i <= totalCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        // Esto avanza de 15 en 15 días
                        int diasAgregados = (i-1) * 15;
                        calendar.add(Calendar.DAY_OF_YEAR, diasAgregados);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= totalCuotas; i++) {
                        double importeMensual = calcularImporteQuincenal(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al recalcular la tabla importe_variado: " + e.toString());
        }
    }
    
    public void recalcularCalculosMensual(JTable paramTablaCalculosAlquiler, String nombreCliente, int nuevaCuotas, Date fecha, double total_rent, double sumaCapital, 
                                                                    double sumaInteres, JTextField totaltxt){
    
        CConexion objetoConexion = new CConexion();
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
        
        try {
            int fila = paramTablaCalculosAlquiler.getSelectedRow();
            
            if (fila >= 0) {
                
                // Obtén el ID de la fila seleccionada
                String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();
                
                // Obtener el id de rent_calculation
                int clientRentId = Integer.parseInt(idSeleccionado);
                
                // Punto 1: Verificar el valor de totaltxt
                System.out.println("Valor de totaltxt en importe_variado: " + totaltxt.getText());
                
                // Obtener la cantidad original de cuotas
                int cuotasOriginal = ca.obtenerCantidadCuotas(clientRentId);
                System.out.println("Valor de cuotas original en importe_variado: " + cuotasOriginal);
                
                // Eliminar todos los cálculos de alquiler asociados al ID seleccionado
                eliminarImporteVariado(clientRentId);
                
                // Si la nueva cantidad de cuotas es mayor que la original, sucede esto:
                if (nuevaCuotas > cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de mayor cantidad de cuotas en importe_variado");
                    // Bucle A
                    for (int i = 1; i <= nuevaCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza al mes siguiente la fecha
                        calendar.add(Calendar.MONTH, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= nuevaCuotas; i++) {
                        double importeMensual = calcularImporteMensual(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas < cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de menor cantidad de cuotas en importe_variado");
                    // Bucle A
                    for (int i = 1; i <= nuevaCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza al mes siguiente la fecha
                        calendar.add(Calendar.MONTH, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= nuevaCuotas; i++) {
                        double importeMensual = calcularImporteMensual(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
                else if (nuevaCuotas == cuotasOriginal) {
                    System.out.println("Entrando en el bloque del caso de que la cantidad de cuotas no cambio en importe_variado");
                    // Bucle A
                    for (int i = 1; i <= nuevaCuotas; i++){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        //Esto avanza al mes siguiente la fecha
                        calendar.add(Calendar.MONTH, i-1);

                        // Obtener la fecha como java.sql.Date
                        java.sql.Date fechaSQL = new java.sql.Date(calendar.getTimeInMillis());
                        
                        // Insertar a la base de datos
                        
                        String sql = "INSERT INTO importe_variado (rent_calculation_id, ord, fecha, importe, sum_importe) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
                            
                            pst.setInt(1, clientRentId); // Esta inserción esta bien
                            pst.setInt(2, i); // Esta inserción esta bien
                            pst.setDate(3, fechaSQL); // Esta inserción esta bien
                            pst.setBigDecimal(4, new BigDecimal(0.0));
                            pst.setBigDecimal(5, new BigDecimal(0.0));
                            
                            int resultado = pst.executeUpdate();
                            
                            if (resultado > 0) {
                                // JOptionPane.showMessageDialog(null, "Registro insertado correctamente en importe_variado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al insertar el registro en importe_variado.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error SQL al insertar el registro en importe_variado: " + e.toString());
                        }
                    }
                    
                    //Bucle B
                    for (int i = 1; i <= nuevaCuotas; i++) {
                        double importeMensual = calcularImporteMensual(sumaCapital, sumaInteres, nuevaCuotas);
                        importeMensual = Math.round(importeMensual * 100.0) / 100.0;

                        double importeTotal = sumaCapital + sumaInteres;
                        importeTotal = Math.round(importeTotal * 100.0) / 100.0;

                        actualizarRegistrosConstantes(clientRentId, i, importeMensual, importeTotal);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error general al recalcular la tabla importe_variado: " + e.toString());
        }
        
    }
    
    // Metodos de ayuda para actualizar los datos hacia la tabla importe_variado
    
    public void eliminarImporteVariado(int rentCalculationId){
        CConexion objetoConexion = new CConexion();

        String sql = "DELETE FROM importe_variado WHERE rent_calculation_id = ?";

        try {
            PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
            pst.setInt(1, rentCalculationId);

            // Ejecutar la sentencia DELETE
            pst.executeUpdate();

            System.out.println("Se eliminaron los cálculos de alquiler asociados al ID en importe_variado: " + rentCalculationId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los cálculos de alquiler: " + e.toString());
        }
    }
    
    /*public void obtenerId(JTable paramTablaCalculosAlquiler){
        CalcularAlquilerDAO ca = new CalcularAlquilerDAO();
    
        int fila = paramTablaCalculosAlquiler.getSelectedRow();
        if (fila >= 0) {
            // Obtén el ID de la fila seleccionada
            String idSeleccionado = paramTablaCalculosAlquiler.getValueAt(fila, 0).toString();
            System.out.println("El id del registro seleccionado es: " + idSeleccionado);
        } else {
            System.out.println("No se selecciono ni una fila");
        }
    }*/
}
