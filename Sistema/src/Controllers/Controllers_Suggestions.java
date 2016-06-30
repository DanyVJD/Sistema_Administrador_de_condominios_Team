package Controllers;

import Acciones.Eliminar_datos_suggestions;
import Acciones.Modified_data_suggestions;
import Acciones.New_data_suggestions;
import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sac_sistema_administrador_de_condominios.Interfaces.Delete_suggestions;
import sac_sistema_administrador_de_condominios.Interfaces.Modified_suggestions;
import sac_sistema_administrador_de_condominios.Interfaces.New_suggestions;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Quejas_y_Sugerencias;

public class Controllers_Suggestions implements ActionListener, Runnable {

    private final Windows_Quejas_y_Sugerencias object;
    private final ControllerWindows obj;
    private final Windows_Interfaz ob;
    Thread a = new Thread(this);

    Controllers_Suggestions(Windows_Quejas_y_Sugerencias object, ControllerWindows obj, Windows_Interfaz ob) {
        this.object = object;
        this.obj = obj;
        this.ob = ob;
        this.object.Suggestions_table.setEnabled(false);
        a.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Eliminar":
                Delete_suggestions view1 = new Delete_suggestions(this.object.mysql);
                Eliminar_datos_suggestions controller1 = new Eliminar_datos_suggestions(view1, ob);
                view1.controller(controller1);
                view1.setVisible(true);
                break;
            case "Modificar":
                Modified_suggestions view2 = new Modified_suggestions(this.object.mysql);
                Modified_data_suggestions controller2 = new Modified_data_suggestions(view2, ob);
                view2.Controller(controller2);
                view2.setVisible(true);
                break;
            case "Guardar":
                New_suggestions view3 = new New_suggestions(this.object.mysql);
                New_data_suggestions controller3 = new New_data_suggestions(view3, ob);
                view3.Controller(controller3);
                view3.setVisible(true);
                break;
            case "Cancelar":
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
                String Titles[] = {"Fecha y hora", "Queja o Sugerencia", "Residente"};
                DefaultTableModel tbl = new DefaultTableModel(null, Titles);
                String fila[] = new String[2];
                result = this.getMysql().querySQL("CALL accessQS();");
                while (result.next()) {
                    fila[0] = result.getString(1);
                    fila[1] = result.getString(2);
                    fila[2] = result.getString(3) + " " + result.getString(4);
                    tbl.addRow(fila);
                }
                this.object.Suggestions_table.setModel(tbl);
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
