/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac_sistema_administrador_de_condominios.Interfaces;

import Acciones.New_Window;
import Codes.MySQL.MySQL;
import Extras.Letter_Mayus;
import Extras.only_letters;
import Extras.only_num;
import Extras.space_Letters;

/**
 *
 * @author Dany-PC
 */
public class Interfaz_New extends javax.swing.JFrame {

    public MySQL mysql;

    /**
     * Creates new form Interfaz_New
     *
     * @param mysql
     */
    public Interfaz_New(MySQL mysql) {
        initComponents();
        this.mysql = mysql;
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Descripcion = new javax.swing.JLabel();
        N_M_E = new javax.swing.JTextField();
        Descripcion1 = new javax.swing.JLabel();
        Dateinit = new com.toedter.calendar.JDateChooser();
        Descripcion2 = new javax.swing.JLabel();
        Datefinish = new com.toedter.calendar.JDateChooser();
        Descripcion3 = new javax.swing.JLabel();
        Cuota = new javax.swing.JTextField();
        Eliminar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Descripcion5 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo, Modificar o Eliminar Cuota.");

        Descripcion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Descripcion.setText("Descripción de la Cuota:");

        Descripcion1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Descripcion1.setText("Fechas:");

        Dateinit.setDateFormatString("yyyy-MM-d");

        Descripcion2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Descripcion2.setText("a");

        Datefinish.setDateFormatString("yyyy-MM-d");

        Descripcion3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Descripcion3.setText("Cuota: $");

        Eliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Eliminar.setText("Eliminar");

        Modificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Modificar.setText("Modificar");

        Guardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Guardar.setText("Guardar");

        Cancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Cancelar.setText("Cancelar");

        Descripcion5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Descripcion5.setText("Tipo de cuota:");

        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AGUA", "GAS", "LUZ", "SERVICIOS DE MANTENIMIENTO", "SERVICIOS DE JARDIN" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(N_M_E)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Descripcion1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Dateinit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Descripcion2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Datefinish, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Descripcion3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cuota))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Descripcion)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Descripcion5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Descripcion2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Descripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(N_M_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Descripcion1)
                            .addComponent(Dateinit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Datefinish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Descripcion3)
                    .addComponent(Cuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Descripcion5)
                    .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Eliminar)
                    .addComponent(Modificar)
                    .addComponent(Guardar)
                    .addComponent(Cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Cancelar;
    public javax.swing.JTextField Cuota;
    public com.toedter.calendar.JDateChooser Datefinish;
    public com.toedter.calendar.JDateChooser Dateinit;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JLabel Descripcion1;
    private javax.swing.JLabel Descripcion2;
    private javax.swing.JLabel Descripcion3;
    private javax.swing.JLabel Descripcion5;
    public javax.swing.JButton Eliminar;
    public javax.swing.JButton Guardar;
    public javax.swing.JButton Modificar;
    public javax.swing.JTextField N_M_E;
    public javax.swing.JComboBox type;
    // End of variables declaration//GEN-END:variables

    public void Controller(New_Window event) {
        Eliminar.addActionListener(event);
        Eliminar.setActionCommand("Eliminated");

        Modificar.addActionListener(event);
        Modificar.setActionCommand("Modified");

        Guardar.addActionListener(event);
        Guardar.setActionCommand("Save");

        Cancelar.addActionListener(event);
        Cancelar.setActionCommand("Cancel");
        
        Cuota.addKeyListener(new only_num());
        N_M_E.addKeyListener(new space_Letters());
        N_M_E.addKeyListener(new Letter_Mayus());

    }
}
