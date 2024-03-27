/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.CConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class DatosCreditoDAO {
    
public void MostrarDatosCredito(JTable tbCalculoAlquilerMen, int rent_calculation_id) {
    CConexion objetoConexion = new CConexion();

    DefaultTableModel modelo = new DefaultTableModel();
    String sql = "";
    modelo.addColumn("Ord");
    modelo.addColumn("Fecha");
    modelo.addColumn("Saldo");
    modelo.addColumn("Capital");
    modelo.addColumn("Interes");
    modelo.addColumn("Por Pagar");

    tbCalculoAlquilerMen.setModel(modelo);

    sql = "SELECT * FROM importe_mensual WHERE rent_calculation_id = ?";

    try {
        PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
        pst.setInt(1, rent_calculation_id);
        ResultSet rs = pst.executeQuery();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        while (rs.next()) {
            Object[] datos = {
                rs.getInt("ord"),
                sdf.format(rs.getDate("fecha")), // Formatear la fecha
                rs.getBigDecimal("saldo"),
                rs.getBigDecimal("capital"),
                rs.getBigDecimal("interes"),
                rs.getBigDecimal("por_pagar"),
            };
            modelo.addRow(datos);
        }
        tbCalculoAlquilerMen.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}


    public void MostrarDatosCreditoTipo(JTable tbCalculoAlquilerTipo, int rent_calculation_id) {
    CConexion objetoConexion = new CConexion();

    DefaultTableModel modelo = new DefaultTableModel();
    String sql = "";
    modelo.addColumn("Ord");
    modelo.addColumn("Fecha");
    modelo.addColumn("Importe");

    tbCalculoAlquilerTipo.setModel(modelo);

    sql = "SELECT ord, fecha, importe FROM importe_variado WHERE rent_calculation_id = ?";

    try {
        PreparedStatement pst = objetoConexion.estableceConexion().prepareStatement(sql);
        pst.setInt(1, rent_calculation_id);
        ResultSet rs = pst.executeQuery();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        while (rs.next()) {
            Object[] datos = {
                rs.getInt("ord"),
                sdf.format(rs.getDate("fecha")), // Formatear la fecha
                rs.getBigDecimal("importe"),
            };
            modelo.addRow(datos);
        }
        tbCalculoAlquilerTipo.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}


    
}
