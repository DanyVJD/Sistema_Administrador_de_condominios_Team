package User_Login;

import Codes.MySQL.MySQL;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class Custom_connection {

    private MySQL mysql;

    public Custom_connection() {
        this.mysql = new MySQL("localhost", "3306", "software_administrator_condo", "root", "root");
        try {
            mysql.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas de Conexion con la base de datos", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int setMensaje(String mesage) {
        int result = 0;
        try {
            result = this.mysql.SQL("call accessUser('" + mesage + "');");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Problema con la autenticacion de usuario" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
}
