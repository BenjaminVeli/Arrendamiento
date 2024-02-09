
package DAO;

import Conexion.CConexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class ContratoDAO {

    int idArrendador;
    
    public void establecerIdArrendador(int idArrendador) {
        this.idArrendador = idArrendador;
    }
    
    public void MostrarArrendadorCombo(JComboBox comboArrendador){
        CConexion objetoConexion = new CConexion(); 
        
         String sql = "SELECT * FROM mantenimiento WHERE rol = 'Arrendador'";
        Statement st;
        try{
                st = objetoConexion.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                comboArrendador.removeAllItems();
                
                while (rs.next () ){
                    String nombreArrendador = rs.getString("nombre");
                    this.establecerIdArrendador(rs.getInt("id"));
                    
                    comboArrendador.addItem(nombreArrendador);
                    comboArrendador.putClientProperty(nombreArrendador,idArrendador);
                    
                }
                
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar al arrendador: " + e.toString());
        }
    }
    
    
    
    
    int idVerificador;
    
    public void establecerIdVerificador(int idVerificador) {
        this.idVerificador = idVerificador;
    }
    
    public void MostrarVerificadorCombo(JComboBox comboVerificador){
    CConexion objetoConexion = new CConexion(); 
        
        String sql = "SELECT * FROM mantenimiento WHERE rol = 'Verificador'";
        Statement st;
        try{
                st = objetoConexion.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                comboVerificador.removeAllItems();
                
                while (rs.next () ){
                    String nombreArrendador = rs.getString("nombre");
                    this.establecerIdArrendador(rs.getInt("id"));
                    
                    comboVerificador.addItem(nombreArrendador);
                    comboVerificador.putClientProperty(nombreArrendador,idVerificador);
                    
                }
                
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar al arrendador: " + e.toString());
        }
    }

    
    
    
    int idGarante;
    
    public void establecertIdGarante(int idGarante) {
        this.idGarante = idGarante;
    }
    
    public void MostrarGaranteCombo(JComboBox comboGarante){
    CConexion objetoConexion = new CConexion(); 
        
        String sql = "SELECT * FROM mantenimiento WHERE rol = 'Garante'";
        Statement st;
        try{
                st = objetoConexion.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                comboGarante.removeAllItems();
                
                while (rs.next () ){
                    String nombreArrendador = rs.getString("nombre");
                    this.establecerIdArrendador(rs.getInt("id"));
                    
                    comboGarante.addItem(nombreArrendador);
                    comboGarante.putClientProperty(nombreArrendador,idGarante);
                    
                }
                
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al mostrar al arrendador: " + e.toString());
        }
    }
}
