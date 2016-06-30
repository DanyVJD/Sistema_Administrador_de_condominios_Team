package Controllers;

import Acciones.Eliminar_datos_event;
import Acciones.Modified_data_event;
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
import sac_sistema_administrador_de_condominios.Interfaces.Delete_event;
import sac_sistema_administrador_de_condominios.Interfaces.Modified_event;
import sac_sistema_administrador_de_condominios.Interfaces.New_Event;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Eventos;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public final class ControllerEvents implements ActionListener, Runnable {

    private final Windows_Eventos object;
    private final Windows_Interfaz ob;
    Thread a = new Thread(this);

    public ControllerEvents(Windows_Eventos object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
        this.object.Events_Table.setEnabled(false);
        a.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Eliminate":
                Delete_event accion1 = new Delete_event(this.object.mysql);
                Eliminar_datos_event controller1 = new Eliminar_datos_event(accion1, ob);
                accion1.controller(controller1);
                accion1.setVisible(true);
                break;
            case "Modified":
                Modified_event accion2 = new Modified_event(this.object.mysql);
                Modified_data_event contoller2 = new Modified_data_event(accion2, ob);
                accion2.Controller(contoller2);
                accion2.setVisible(true);
                break;
            case "Save":
                New_Event accion3 = new New_Event(this.object.mysql);
                Controller_Datos controller3 = new Controller_Datos(accion3, this, ob);
                accion3.controller(controller3);
                accion3.setVisible(true);
                break;
            case "Buscar":
                String fech = "";
                try {
                    String formato = this.object.Data_date.getDateFormatString();
                    Date date = this.object.Data_date.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat(formato);
                    fech = sdf.format(date);
                } catch (NullPointerException ex) {
                }
                TableRowSorter trsFiltro = new TableRowSorter(this.object.Events_Table.getModel());
                this.object.Events_Table.setRowSorter(trsFiltro);
                trsFiltro.setRowFilter(RowFilter.regexFilter(fech, 0));
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
                String Titles[] = {"Fecha", "Evento", "Organizador"};
                DefaultTableModel tbl = new DefaultTableModel(null, Titles);
                String fila[] = new String[2];
                result = this.getMysql().querySQL("CALL accessevent3();");
                while (result.next()) {
                    fila[0] = result.getString(1);
                    fila[1] = result.getString(2);
                    fila[2] = result.getString(3) + " " + result.getString(4);
                    tbl.addRow(fila);
                }
                this.object.Events_Table.setModel(tbl);
                a.sleep(3000);
            } catch (SQLException | ClassNotFoundException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "" + ex.getMessage());
            }
        }
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
