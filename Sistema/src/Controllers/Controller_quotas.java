package Controllers;

import Acciones.New_Window;
import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import sac_sistema_administrador_de_condominios.Interfaces.Interfaz_New;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Cuotas;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public class Controller_quotas implements ActionListener, Runnable {

    private Windows_Cuotas object;
    private Windows_Interfaz ob;
    Thread a = new Thread(this);

    public Controller_quotas(Windows_Cuotas object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        this.object.info.setEnabled(false);
        if (!("ADMIN").equals(this.getUser())) {
            this.object.Nueva.setEnabled(false);
        }
        a.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Acept":
                String fech = "";
                try {
                    Date date = this.object.Fecha.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fech = sdf.format(date);
                } catch (NullPointerException ex) {
                }
                TableRowSorter trsFiltro = new TableRowSorter(this.object.Table_data.getModel());
                this.object.Table_data.setRowSorter(trsFiltro);
                trsFiltro.setRowFilter(RowFilter.regexFilter(fech, 0));
                break;
            case "New":
                Interfaz_New view = new Interfaz_New(this.object.mysql);
                New_Window controller2 = new New_Window(view, this.ob);
                view.Controller(controller2);
                view.setVisible(true);
                break;

            case "Cancel":
                this.object.dispose();
                break;
        }
    }

    @Override
    public void run() {
        Thread m = Thread.currentThread();
        while (m == a) {
            try {
                ResultSet result;
                String Titles[] = {"Fecha de inicio", "Tipo de cuota", "Descripción", "Cargo", "Fecha de finalización"};
                DefaultTableModel tbl = new DefaultTableModel(null, Titles);
                String fila[] = new String[4];
                result = this.getMysql().querySQL("call accessQTAS;");
                while (result.next()) {
                    fila[0] = result.getString(2);
                    fila[1] = result.getString(5);
                    fila[2] = result.getString(4);
                    fila[3] = result.getString(3);
                    fila[4] = result.getString(6);
                    tbl.addRow(fila);
                }
                this.object.Table_data.setModel(tbl);
                String mensage = "";
                result = this.getMysql().querySQL("call newQTASFechas('"+this.getFec()+"');");
                while (result.next()) {
                 mensage = mensage + result.getString(1)+"\n";
                }
                this.object.info.setText(mensage);
                a.sleep(3000);
            } catch (SQLException | ClassNotFoundException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "" + ex.getMessage());
            }
        }
    }

    public String getFec() {
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return hourdateFormat.format(date);
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
