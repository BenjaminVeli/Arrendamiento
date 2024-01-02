package DAO;

import com.mycompany.arrendamientos.CalculoAlquiler;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDAO {
    public boolean  ValidarUsuario(JTextField user, JPasswordField password){
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;

            Conexion.CConexion objetoCConexion = new Conexion.CConexion();

            String consulta="select * from users where users.user =(?) and users.password=(?);";
            ps=objetoCConexion.estableceConexion().prepareStatement(consulta);

            String pass = String.valueOf(password.getPassword());

            ps.setString(1, user.getText());
            ps.setString(2,pass);

            rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "El Usuario es Correcto");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El Usuario es Incorrecto, vuelva a intentar");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
            return false;
        }
    }
}
