package Controllers;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.New_Event;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public final class Controller_Datos implements ActionListener {

    private final New_Event object;
    private final ControllerEvents obj;
    private final Windows_Interfaz ob;

    public Controller_Datos(New_Event object, ControllerEvents obj, Windows_Interfaz ob) {
        this.object = object;
        this.obj = obj;
        this.ob = ob;
        this.object.Residentes.setText(this.ob.User.getText());
        this.object.Residentes.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Acept":
                if (!this.getfecha().equals("") || !this.getDescription().equals("")) {
                    int datos = 0;
                    try {
                        datos = this.getMysql().SQL("CALL newEvent('" + this.getfecha() + "', '" + this.getDescription() + "', '" + this.getResident() + "');");
                        if (datos != 0) {
                            JOptionPane.showMessageDialog(null, "Nuevo evento registrado");
                            this.object.dispose();
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa algun dato valido");
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    public String getfecha() {
        String fech = "";
        try {
            String formato = this.object.Fecha.getDateFormatString();
            Date date = this.object.Fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fech = sdf.format(date);
        } catch (NullPointerException ex) {
        }
        return fech;
    }

    public String getDescription() {
        return this.object.Evento.getText();
    }

    public String getResident() {
        return this.object.Residentes.getText();
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
