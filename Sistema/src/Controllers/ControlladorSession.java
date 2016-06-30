package Controllers;

import Codes.MySQL.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import sac_sistema_administrador_de_condominios.Interfaces.Login_Windows_Sesion;
import java.awt.HeadlessException;
import java.sql.SQLException;
import sac_sistema_administrador_de_condominios.Interfaces.Windows_Interfaz;

public final class ControlladorSession implements ActionListener {

    private final Login_Windows_Sesion object;
    private final Windows_Interfaz obj;
    private ControllerWindows init;

    public ControlladorSession(Login_Windows_Sesion object, Windows_Interfaz view, ControllerWindows init) {
        this.object = object;
        this.obj = view;
        this.init = init;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {

            case "Acept":
                try {
                    if (!this.user().equals("") && !this.pass().equals("")) {
                        int result = 0;
                        result = this.getMysql().SQL("call accessUser('" + this.user() + "', '" + this.pass() + "');");
                        evaluaciones(result);
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifica los datos de usuario o contraseña", "", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException | ClassNotFoundException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Problema con la autenticacion de usuario" + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Cancel":
                this.object.dispose();
                break;

        }

    }

    public String user() {
        return this.object.User.getText();
    }

    public String pass() {
        return this.object.password.getText();
    }

    public void setMysql(MySQL mysql) {
        this.object.mysql = mysql;
    }

    public MySQL getMysql() {
        return this.object.mysql;
    }

    private void evaluaciones(int num) throws SQLException {
        if (num != 0) {
            this.object.dispose();
            JOptionPane.showMessageDialog(null, "Bienvenido al Sistema " + this.user());
            this.obj.User.setText(this.user());
        } else {
            JOptionPane.showMessageDialog(null, "Verifica los datos de usuario o contraseña", "", JOptionPane.ERROR_MESSAGE);
        }

        if (!"Usuario".equals(this.obj.User.getText())) {
            this.obj.session.setText("Cerrar");
            acciones();
        }
    }

    public void acciones(Boolean privilegio) {
        this.obj.Dues.setEnabled(privilegio);
        this.obj.Events.setEnabled(privilegio);
        this.obj.Residents.setEnabled(privilegio);
        this.obj.finances.setEnabled(privilegio);
        this.obj.records.setEnabled(privilegio);
        this.obj.suggestions.setEnabled(privilegio);
    }

    public void acciones() {
        if ("ADMIN".equals(this.obj.User.getText())) {
            acciones(true);
        } else {
            this.obj.Dues.setEnabled(true);
            this.obj.Events.setEnabled(true);
            this.obj.finances.setEnabled(true);
            this.obj.suggestions.setEnabled(true);
        }
    }
}
