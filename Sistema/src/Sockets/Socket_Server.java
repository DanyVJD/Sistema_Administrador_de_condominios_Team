package Sockets;

import User_Login.Custom_connection;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Socket_Server {

    private ServerSocket servidor = null;
    private Socket cli = null;
    public String msg = null;

    public void run() {
        try {
            servidor = new ServerSocket(4444);
            while (true) {
                cli = servidor.accept();
                DataInputStream flujo = new DataInputStream(cli.getInputStream());
                msg = flujo.readUTF();
                Custom_connection conect = new Custom_connection();
                if (conect.setMensaje(msg) != 0) {
                        JOptionPane.showMessageDialog(null, "Bienvenido al Sistema ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifica los datos de usuario o contraseña", "", JOptionPane.ERROR_MESSAGE);
                    }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                cli.close();
                servidor.close();
            } catch (IOException ex) {
                Logger.getLogger(Socket_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
