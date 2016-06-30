/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Codes.MySQL.MySQL;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Load_Residentes;

/**
 *
 * @author Dany-PC
 */
public class ControllerResident implements ActionListener {

    private final Load_Residentes object;
    private ControllerWindows obj;
    private MySQL mysql;

    public ControllerResident(Load_Residentes object, ControllerWindows obj) {
        this.obj = obj;
        this.object = object;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Guardar":
                if (this.object.nom.getText().length() >= 3 && this.object.APM.getText().length() >= 3 && this.object.APP.getText().length() >= 3) {
                    try {

                        if (!this.Usr().equals("") && !this.Pass().equals("") && !this.Nom().equals("") && !this.App().equals("") && !this.Apm().equals("") && !this.Fnac().equals("") && !this.Gen().equals("") && !this.Tel().equals("") && !this.Dir().equals("") && !this.Casa().equals("")) {
                            int result = 0;
                            result = this.getMysql().SQL("call newUser('" + this.Usr() + "', '" + this.Pass() + "', '" + this.Nom() + "', '" + (this.App() + " " + this.Apm()) + "', '" + this.Fnac() + "', '" + this.Gen() + "', '" + this.Tel() + "', '" + this.Dir() + "', '" + this.Casa() + "');");
                            if (result != 0) {
                                evaluaciones();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Verifica si los datos del usuario son correctos", "", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException | ClassNotFoundException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario registrado" + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese texto de mas de tres letras");
                }
                break;
            case "Cancelar":
                this.object.dispose();
                break;
        }
    }

    public String Usr() {
        return this.object.user.getText();
    }

    public String Pass() {
        return this.object.pass.getText();
    }

    public String Nom() {
        return this.object.nom.getText();
    }

    public String App() {
        return this.object.APP.getText();
    }

    public String Apm() {
        return this.object.APM.getText();
    }

    public String Fnac() {
        String fech = "";
        try {
            String formato = this.object.FN.getDateFormatString();
            Date date = this.object.FN.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            fech = sdf.format(date);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        return fech;
    }

    public String Gen() {
        String gen;
        int opc;
        opc = this.object.Genero.getSelectedIndex();
        if (opc == 0) {
            gen = "M";
        } else {
            gen = "F";
        }
        return gen;
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }

    public String Tel() {
        return this.object.T_TEL.getText();
    }

    public String Dir() {
        return this.object.dir.getText();
    }

    public String Casa() {
        return this.object.Num_cas.getText();
    }

    public void evaluaciones() throws SQLException {
        this.object.dispose();
        JOptionPane.showMessageDialog(null, "Datos de: " + this.Nom() + " " + this.App() + " " + this.Apm() + " ingresados correctamente");
    }
}
