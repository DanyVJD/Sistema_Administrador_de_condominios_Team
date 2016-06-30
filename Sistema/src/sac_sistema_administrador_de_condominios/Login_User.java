package sac_sistema_administrador_de_condominios;

import User_Login.ControllerSesionUser;
import User_Login.Login_Windows_User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class Login_User {

    private static final Logger LOG = Logger.getLogger(SAC_Sistema_Administrador_de_Condominios.class.getName());
    private static final long serialVersionUID = 2012201552509950L;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            LOG.info("Inicio del SAC FL-AndruAnnohomy\n");
            Login_Windows_User view = new Login_Windows_User();
            ControllerSesionUser controller = new ControllerSesionUser(view);
            view.controller(controller);
            view.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SAC_Sistema_Administrador_de_Condominios.class.getName()).log(Level.SEVERE, "Error de Ejecucion");
        }
    }

    @Override
    public String toString() {
        return "Created by FL- AndruAnnohomy smart pataforma: Start_Lockers_Manager{" + '}';

    }
}
