/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codes.MySQL;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dany-PC
 */
public class ConectionBaseData {

    private MySQL mysql;

    public ConectionBaseData() {
        this.mysql = new MySQL("localhost", "3306", "software_administrator_condo", "root", "root");
        try {
            mysql.openConnection();

        } catch (ClassNotFoundException | SQLException e) {

            JOptionPane.showMessageDialog(null, "Problemas de Conexion con la base de datos", "", JOptionPane.ERROR_MESSAGE);

        }
    }
}
