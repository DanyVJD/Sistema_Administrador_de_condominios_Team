package Controllers;

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
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Registros;

public class Controller_Registros implements ActionListener {

    private final Windows_Registros object;
    private final ControllerWindows ob;
    public String Type;
    public String CALL;
    public int val = 6;
    public String fila[] = new String[val];
    public static DefaultTableModel tbl;

    Controller_Registros(Windows_Registros object, ControllerWindows ob) {
        this.object = object;
        this.ob = ob;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "RUsuarios":
                String Titles_1[] = {"Nombre(s)", "Apellidos", "Fecha de nacimiento", "Telefono", "Dirección", "Numero de casa"};
                tbl = new DefaultTableModel(null, Titles_1);
                Type = "Residente";
                this.CALL = "CALL accessResident();";
                datos(this.CALL);
                break;
            case "RCuotas":
                String Titles_2[] = {"Inicio", "Descripción", "Cuota $", "Finalización"};
                tbl = new DefaultTableModel(null, Titles_2);
                Type = "Cuota";
                this.CALL = "CALL accessQTAS();";
                datos(this.CALL);
                break;
            case "REventos":
                String Titles_3[] = {"Dia", "Evento", "Residente"};
                tbl = new DefaultTableModel(null, Titles_3);
                Type = "Eventos";
                this.CALL = "CALL accessevents();";
                datos(this.CALL);
                break;
            case "RQuejasySugerencias":
                String Titles_4[] = {"Dia y hora", "Queja o sugerencia", "Residente"};
                tbl = new DefaultTableModel(null, Titles_4);
                Type = "Quejas o sugerencias";
                this.CALL = "CALL accessQS();";
                datos(this.CALL);
                break;
            case "Buscar":
                String fech = "";
                try {
                    String formato = this.object.Fecha.getDateFormatString();
                    Date date = this.object.Fecha.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat(formato);
                    fech = sdf.format(date);
                } catch (NullPointerException ex) {
                }
                TableRowSorter trsFiltro = new TableRowSorter(this.object.Table_datos.getModel());
                this.object.Table_datos.setRowSorter(trsFiltro);
                trsFiltro.setRowFilter(RowFilter.regexFilter(fech, 0));
                break;
        }
    }

    public void datos(String CALL) {
        try {
            ResultSet result;
            result = this.getMysql().querySQL(CALL);
            while (result.next()) {
                switch (Type) {
                    case "Residente":
                        fila[0] = result.getString(2);
                        fila[1] = result.getString(3);
                        fila[2] = result.getString(4);
                        fila[3] = result.getString(6);
                        fila[4] = result.getString(7);
                        fila[5] = result.getString(9);
                        break;
                    case "Cuota":
                        fila[0] = result.getString(2);
                        fila[1] = result.getString(4);
                        fila[2] = result.getString(3);
                        fila[3] = result.getString(6);
                        break;
                    case "Eventos":
                        fila[0] = result.getString(1);
                        fila[1] = result.getString(2);
                        fila[2] = (result.getString(3)+" "+result.getString(4));
                        break;
                    case "Quejas o sugerencias":
                        fila[0] = result.getString(1);
                        fila[1] = result.getString(2);
                        fila[2] = (result.getString(3)+" "+result.getString(4));
                        break;
                }
                tbl.addRow(fila);
            }
            this.object.Table_datos.setModel(tbl);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "" + ex.getMessage());
        }
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
