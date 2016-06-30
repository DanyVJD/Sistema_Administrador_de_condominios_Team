package Acciones;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.New_suggestions;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public class New_data_suggestions implements ActionListener {

    private final New_suggestions object;
    private final Windows_Interfaz ob;

    public New_data_suggestions(New_suggestions object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Acept":
                if (!this.getQandS().equals("")) {
                    int result = 0;
                    try {
                        result = this.getMysql().SQL("CALL newSQ('"+this.getFecyH()+"','"+this.getQandS()+"','"+this.getResident()+"');");
                        if (result != 0) {
                            JOptionPane.showMessageDialog(null, "Queja o sugerencia ingresada correctamente");
                            this.object.dispose();
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "" + ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No se ingreso queja o sugerencia, intentelo de nuevo");
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    public String getQandS() {
        return this.object.Queja_Sugerencia.getText();
    }

    public String getFecyH() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        return hourdateFormat.format(date);
    }

    public String getResident() {
        return this.ob.User.getText();
    }
    
    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
