package Controllers;

import Acciones.New_Register;
import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import sac_sistema_administrador_de_condominios.Interfaces.New_register;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Finanzas;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public class Controller_finance implements ActionListener, Runnable {

    public Windows_Finanzas object;
    public Windows_Interfaz ob;
    Thread a = new Thread(this);

    public Controller_finance(Windows_Finanzas object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        if (!this.getUser().equals("ADMIN")) {
            this.object.Registro.setEnabled(false);
        }
        a.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Buscar":
                String fech = "";
                try {
                    String formato = this.object.Dia_fec.getDateFormatString();
                    Date date = this.object.Dia_fec.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat(formato);
                    fech = sdf.format(date);
                } catch (NullPointerException ex) {
                }
                TableRowSorter trsFiltro = new TableRowSorter(this.object.Table_Finance.getModel());
                this.object.Table_Finance.setRowSorter(trsFiltro);
                trsFiltro.setRowFilter(RowFilter.regexFilter(fech, 0));
                break;
            case "Registro":
                New_register view = new New_register(this.object.mysql);
                New_Register controller = new New_Register(view, ob);
                view.Controller(controller);
                view.setVisible(true);
                break;
            case "Salir":
                this.object.dispose();
                break;
        }
    }

    public String getUser() {
        return this.ob.User.getText();
    }

    @Override
    public void run() {
        Thread m = Thread.currentThread();
        while (m == a) {
            try {
                ResultSet result;
                ResultSet res;
                String Titles[] = {"Descripción", "Cuota", "Fecha de pago"};
                DefaultTableModel tbl = new DefaultTableModel(null, Titles);
                String fila[] = new String[2];
                result = this.getMysql().querySQL("CALL selectfinace ('" + this.getUser() + "');");
                while (result.next()) {
                    fila[2] = result.getString(2);
                    res = this.getMysql().querySQL("CALL selectfinace1 (" + result.getString(1) + ");");
                    while (res.next()) {
                        fila[0] = res.getString(1);
                        fila[1] = res.getString(2);
                    }
                    tbl.addRow(fila);
                }
                this.object.Table_Finance.setModel(tbl);
                a.sleep(3000);
            } catch (SQLException | ClassNotFoundException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "" + ex.getMessage());
            }
        }
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
