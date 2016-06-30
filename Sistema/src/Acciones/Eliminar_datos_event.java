package Acciones;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Delete_event;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public final class Eliminar_datos_event implements ActionListener {

    private final Delete_event object;
    private final Windows_Interfaz ob;

    public Eliminar_datos_event(Delete_event object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        ResultSet result;
        try {
            result = this.getMysql().querySQL("CALL accessevent('" + this.getUser() + "');");
            while (result.next()) {
                this.object.Events.addItem(result.getString(3));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());

        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Acept":
                if (this.object.Events.getSelectedIndex() != -1) {
                    int datos = 0;
                    try {
                        datos = this.getMysql().SQL("CALL deletevent ('" + this.getEvent() + "', '" + this.getUser() + "');");
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "" + ex.getMessage());
                    }
                    if (datos != 0) {
                        JOptionPane.showMessageDialog(null, "Datos del evento eliminados satisfactoriamente");
                        this.object.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe existir algun evento registrado");
                    this.object.dispose();
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    public String getEvent() {
        return this.object.Events.getSelectedItem().toString();
    }

    public String getUser() {
        return this.ob.User.getText();
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
