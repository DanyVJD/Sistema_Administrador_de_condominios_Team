package Acciones;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Delete_quota;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public final class Eliminar_datos_quota implements ActionListener {

    private final Delete_quota object;
    private final Windows_Interfaz ob;

    public Eliminar_datos_quota(Delete_quota object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        ResultSet result;
        try {
            result = this.getMysql().querySQL("CALL accessQTAS();");
            while (result.next()) {
                this.object.Events.addItem(result.getString(4));
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
                        datos = this.getMysql().SQL("CALL deleteQTAS ('" + this.getDesc() + "');");
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "" + ex.getMessage());
                    }
                    if (datos != 0) {
                        JOptionPane.showMessageDialog(null, "Datos de la cuota eliminados satisfactoriamente");
                        this.object.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe existir alguna cuota registrada");
                    this.object.dispose();
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    public String getDesc() {
        return this.object.Events.getSelectedItem().toString();
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
