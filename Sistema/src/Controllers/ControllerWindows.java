package Controllers;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sac_sistema_administrador_de_condominios.Interfaces.Load_Residentes;
import sac_sistema_administrador_de_condominios.Interfaces.Login_Windows_Sesion;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Cuotas;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Eventos;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Finanzas;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Quejas_y_Sugerencias;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Registros;

public final class ControllerWindows implements ActionListener, Runnable {

    private final Windows_Interfaz object;
    private MySQL mysql;
    Thread hilo = new Thread(this);

    public ControllerWindows(Windows_Interfaz object) {
        this.object = object;
        acciones(false);
        this.mysql = new MySQL("localhost", "3306", "software_administrator_condo", "root", "root");
        try {
            mysql.openConnection();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas de Conexion con la base de datos", "", JOptionPane.ERROR_MESSAGE);
        }
        hilo.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "session":
                if ("Cerrar".equals(this.object.session.getText())) {
                    if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar sesión?", "", JOptionPane.YES_NO_OPTION) == 0) {
                        this.object.User.setText("Usuario");
                        this.object.session.setText("Sesión");
                        acciones(false);
                    }
                } else {
                    Login_Windows_Sesion view1 = new Login_Windows_Sesion(mysql);
                    ControlladorSession controller1 = new ControlladorSession(view1, this.object, this);
                    view1.controller(controller1);
                    view1.setVisible(true);
                }
                break;
            case "Dues":
                Windows_Cuotas view2 = new Windows_Cuotas(mysql);
                Controller_quotas controller2 = new Controller_quotas(view2, this.object);
                view2.Controller(controller2);
                view2.setVisible(true);
                break;
            case "Events":
                Windows_Eventos view3 = new Windows_Eventos(mysql);
                ControllerEvents controller3 = new ControllerEvents(view3, this.object);
                view3.controller(controller3);
                view3.setVisible(true);
                break;
            case "Residents":
                Load_Residentes view4 = new Load_Residentes(mysql);
                ControllerResident controller4 = new ControllerResident(view4, this);
                view4.controller(controller4);
                view4.setVisible(true);
                break;
            case "finances":
                Windows_Finanzas view5 = new Windows_Finanzas(mysql);
                Controller_finance controller5 = new Controller_finance(view5, this.object);
                view5.Controller(controller5);
                view5.setVisible(true);
                break;
            case "records":
                Windows_Registros view6 = new Windows_Registros(mysql);
                Controller_Registros controller6 = new Controller_Registros(view6, this);
                view6.controller(controller6);
                view6.setVisible(true);
                break;
            case "suggestions":
                Windows_Quejas_y_Sugerencias view7 = new Windows_Quejas_y_Sugerencias(mysql);
                Controllers_Suggestions controller7 = new Controllers_Suggestions(view7, this, this.object);
                view7.Controller(controller7);
                view7.setVisible(true);
                break;
        }
    }

    public void acciones(Boolean privilegio) {
        this.object.Dues.setEnabled(privilegio);
        this.object.Events.setEnabled(privilegio);
        this.object.Residents.setEnabled(privilegio);
        this.object.finances.setEnabled(privilegio);
        this.object.records.setEnabled(privilegio);
        this.object.suggestions.setEnabled(privilegio);
    }

    @Override
    public void run() {
        Thread hilo1 = Thread.currentThread();
        while (hilo1 == hilo) {
            String coments = "Bienvenido al sistema SAC\nestos son los comentarios y eventos del día:";
            ResultSet res;
            try {
                String Titles[] = {"Fecha", "Evento", "Organizador"};
                DefaultTableModel tbl = new DefaultTableModel(null, Titles);
                String fila[] = new String[2];
                res = this.getMysql().querySQL("CALL accesseventsFechas('" + this.getFec() + "');");
                while (res.next()) {
                    fila[0] = res.getString(1);
                    fila[1] = res.getString(2);
                    fila[2] = res.getString(3) + " " + res.getString(4);
                    tbl.addRow(fila);
                    coments = coments + "\n\n\n" + res.getString(2);
                }
                this.object.Tb_Events.setModel(tbl);
                this.object.coments.setText(coments);
                if (Datos().equals("0")) {
                    hilo.sleep(60000);
                } else {
                    hilo.sleep(5000);
                }
            } catch (SQLException | ClassNotFoundException | InterruptedException e) {
                JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setMysql(MySQL mysql) {
        this.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.mysql;
    }

    public String getFec() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return hourdateFormat.format(date);
    }

    public String Datos() {
        int result = 0;
        ResultSet res;
        try {
            result = this.getMysql().SQL("CALL accessResident()");
            if (result != 0) {
                if (result == -1) {
                    this.object.Num_res.setText("" + (result + 2));
                } else {
                    this.object.Num_res.setText("" + result);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningun usuario registrado ingrese un nuevo usuario");
                JOptionPane.showMessageDialog(null, "En el campo de número de casa ingrese el total de las casas existentes\nEn el campo de usuario coloque ADMIN para poder tener todos los privilegios");
                Load_Residentes view4 = new Load_Residentes(mysql);
                ControllerResident controller4 = new ControllerResident(view4, this);
                view4.controller(controller4);
                view4.setVisible(true);
            }
            res = this.getMysql().querySQL("CALL accessCasas()");
            while (res.next()) {
                this.object.Num_cas.setText(res.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }
        return this.object.Num_res.getText();
    }
}
