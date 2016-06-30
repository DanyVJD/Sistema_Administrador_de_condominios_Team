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
import sac_sistema_administrador_de_condominios.Interfaces.Modified_event;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public final class Modified_data_event implements ActionListener {

    private Modified_event object;
    private Windows_Interfaz ob;
    public ResultSet result;

    public Modified_data_event(Modified_event object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        try {
            result = this.getMysql().querySQL("CALL accessevent('" + this.getUser() + "');");
            while (result.next()) {
                this.object.Eventos.addItem(result.getString(3));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Acept":
                if (this.object.Eventos.getSelectedIndex() != -1) {
                    try {
                        int res = 0;
                        res = this.getMysql().SQL("CALL modifiedvent ('" + this.Fnac() + "', '" + this.getEvent() + "', '" + this.getModEvent() + "', '" + this.getUser() + "');");
                        if (res != 0) {
                            JOptionPane.showMessageDialog(null, "Evento Modificado correctamente");
                            this.object.dispose();
                        }
                    } catch (SQLException | ClassNotFoundException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe existir algun evento registrado");
                    this.object.dispose();
                }
                break;
            case "Eventos":
                String fecha;
                try {
                    result = this.getMysql().querySQL("CALL accessevent2('" + this.getEvent() + "', '" + this.getUser() + "');");
                    while (result.next()) {
                        fecha = result.getString(2);
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-d");
                        Date fechaDate = formato.parse(fecha);
                        this.object.Modified.setText(result.getString(3));
                        this.object.Fecha.setDate(fechaDate);
                    }
                } catch (SQLException | ClassNotFoundException | ParseException ex) {
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

    public String getEvent() {
        return this.object.Eventos.getSelectedItem().toString();
    }

    public String getModEvent() {
        return this.object.Modified.getText();
    }

    public String Fnac() {
        String fech = "";
        try {
            String formato = this.object.Fecha.getDateFormatString();
            Date date = this.object.Fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fech = sdf.format(date);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        return fech;
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
