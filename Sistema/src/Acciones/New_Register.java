package Acciones;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.New_register;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public class New_Register implements ActionListener {

    public New_register object;
    public Windows_Interfaz ob;

    public New_Register(New_register object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        ResultSet result;
        try {
            result = this.getMysql().querySQL("call selectQTAS();");
            while (result.next()) {
                this.object.Cuotas.addItem(result.getString(4));
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        try {
            result = this.getMysql().querySQL("call accessResident();");
            while (result.next()) {
                this.object.Residents.addItem(result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Acept":
                if (this.object.Cuotas.getSelectedIndex() != -1 && this.object.Residents.getSelectedIndex() != -1) {
                    int res = 0;
                    try {
                        res = this.getMysql().SQL("CALL insertfinace ('" + this.getUser() + "', '" + this.getCuota() + "', '" + this.getFecyH() + "');");
                        if (res != 0) {
                            JOptionPane.showMessageDialog(null, "Cuota registrada para el residente " + this.getUser());
                        }else{
                            JOptionPane.showMessageDialog(null, "Ya a registrado cuota para el residente "+ this.getUser());
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(null, ""+ e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe existir algun dato registrado");
                    this.object.dispose();
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }

    public String getCuota() {
        return this.object.Cuotas.getSelectedItem().toString();
    }

    public String getUser() {
        return this.object.Residents.getSelectedItem().toString();
    }

    public String getFecyH() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return hourdateFormat.format(date);
    }
}
