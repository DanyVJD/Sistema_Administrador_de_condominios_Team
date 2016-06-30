package Acciones;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Delete_suggestions;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public class Eliminar_datos_suggestions implements ActionListener {

    private final Delete_suggestions object;
    private final Windows_Interfaz ob;

    public Eliminar_datos_suggestions(Delete_suggestions object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        ResultSet result;
        try {
            result = this.getMysql().querySQL("CALL accessQS1('" + this.getUser() + "');");
            while (result.next()) {
                this.object.Suggestions.addItem(result.getString(3));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());

        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Acept":
                if (this.object.Suggestions.getSelectedIndex() != -1) {
                    int datos = 0;
                    try {
                        datos = this.getMysql().SQL("CALL deleteSQ ('" + this.getSuggestions() + "', '" + this.getUser() + "');");
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "" + ex.getMessage());
                    }
                    if (datos != 0) {
                        JOptionPane.showMessageDialog(null, "Datos eliminados satisfactoriamente");
                        this.object.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe existir alguna queja o sugerencia registrada");
                    this.object.dispose();
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }
    
    public String getSuggestions() {
        return this.object.Suggestions.getSelectedItem().toString();
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
