/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Codes.MySQL.MySQL;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Modified_suggestions;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

/**
 *
 * @author Dany-PC
 */
public class Modified_data_suggestions implements ActionListener {

    private Modified_suggestions object;
    private Windows_Interfaz ob;
    public ResultSet result;

    public Modified_data_suggestions(Modified_suggestions object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
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
                    try {
                        int res = 0;
                        res = this.getMysql().SQL("CALL modifiedSQ ('" + this.getSuggestions() + "', '" + this.getModSugggestions() + "', '" + this.getUser() + "');");
                        if (res != 0) {
                            JOptionPane.showMessageDialog(null, "Modificado correctamente");
                            this.object.dispose();
                        }
                    } catch (SQLException | ClassNotFoundException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe existir alguna queja o sugerencia registrada");
                    this.object.dispose();
                }
                break;
            case "Suggestions":
                try {
                    result = this.getMysql().querySQL("CALL accessQS2('" + this.getSuggestions() + "', '" + this.getUser() + "');");
                    while (result.next()) {
                        this.object.Modified.setText(result.getString(3));
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "" + ex.getMessage());
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    public String getUser() {
        return this.ob.User.getText();
    }

    public String getSuggestions() {
        return this.object.Suggestions.getSelectedItem().toString();
    }

    public String getModSugggestions() {
        return this.object.Modified.getText();
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }

}
