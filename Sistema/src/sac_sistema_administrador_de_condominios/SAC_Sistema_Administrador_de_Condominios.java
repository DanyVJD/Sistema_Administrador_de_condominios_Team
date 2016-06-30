package sac_sistema_administrador_de_condominios;

import Sockets.Socket_Server;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import sac_sistema_administrador_de_condominios.Interfaces.Load_Windows_Interfaz;

public class SAC_Sistema_Administrador_de_Condominios {

    private static final Logger LOG = Logger.getLogger(SAC_Sistema_Administrador_de_Condominios.class.getName());
    private static final long serialVersionUID = 2012201552509950L;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            LOG.info("Inicio del SAC FL-AndruAnnohomy\n");
            new Thread(new Load_Windows_Interfaz()).start();
            Socket_Server conect = new Socket_Server();
            conect.run();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SAC_Sistema_Administrador_de_Condominios.class.getName()).log(Level.SEVERE, "Error de Ejecucion");
        }
    }

    @Override
    public String toString() {

        return "Created by FL- AndruAnnohomy smart pataforma: Start_Lockers_Manager{" + '}';

    }

}
