package Acciones;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Delete_quota;
import sac_sistema_administrador_de_condominios.Interfaces.Interfaz_New;
import sac_sistema_administrador_de_condominios.Interfaces.Modified_quotas;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public class New_Window implements ActionListener {

    public Interfaz_New object;
    public Windows_Interfaz ob;

    public New_Window(Interfaz_New object, Windows_Interfaz ob) {
        this.object = object;
        this.ob = ob;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Eliminated":
                Delete_quota view1 = new Delete_quota(this.object.mysql);
                Eliminar_datos_quota controller1 = new Eliminar_datos_quota(view1, this.ob);
                view1.controller(controller1);
                view1.setVisible(true);
                break;
            case "Modified":
                Modified_quotas view2 = new Modified_quotas(this.object.mysql);
                Modified_data_quotas controller2 = new Modified_data_quotas(view2,this.ob);
                view2.Controller(controller2);
                view2.setVisible(true);
                break;
            case "Save":
                try {
                    if (!this.getDescription().equals("") && !this.getFF().equals("") && !this.getFI().equals("") && !this.getPass().equals("") && !this.getCuota().equals("")) {
                        int result = 0;
                        result = this.getMysql().SQL("call newQTAS('" + this.getFI() + "', '" + this.getCuota() + "', '" + this.getDescription()+ "', '" + this.getType() + "', '" + this.getFF() + "');");
                        if (result != 0) {
                            JOptionPane.showMessageDialog(null, "Cuota Registrada");
                            this.object.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifica si los datos de la cuota son correctos", "", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                }
                break;
            case "Cancel":
                this.object.dispose();
                break;
        }
    }
    
    public String getType() {
        return this.object.type.getSelectedItem().toString();
    }

    public String getDescription() {
        return this.object.N_M_E.getText();
    }

    public String getFI() {
        String fech = "";
        try {
            String formato = this.object.Dateinit.getDateFormatString();
            Date date = this.object.Dateinit.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fech = sdf.format(date);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        return fech;
    }

    public String getFF() {
        String fech = "";
        try {
            String formato = this.object.Datefinish.getDateFormatString();
            Date date = this.object.Datefinish.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fech = sdf.format(date);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        return fech;
    }

    public String getCuota(){
        return this.object.Cuota.getText();
    }
    
    public String getPass(){
        return this.object.Cuota.getText();
    }
    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }
}
