package com.mycompany.arrendamientos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Amortizaciones extends javax.swing.JFrame {

    private String idSeleccionado;
    private int room_id_actual;
    private static int numeroActual = 1; // Variable estática para llevar el registro del número actual
    private boolean grabarPresionado = false;
    
    public Amortizaciones(String idSeleccionado, int room_id_actual) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.idSeleccionado = idSeleccionado;
        this.room_id_actual = room_id_actual;
        
         LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        // Configurar el formato deseado para la fecha y hora
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatoFechaHora);
        
        // Configurar el número en el JTextField solo si no se presionó "Grabar" antes
        if (!grabarPresionado) {
            numeroTxt.setText(String.format("%07d", numeroActual));
        }
        
        // Establecer la fecha y hora en el JDateChooser
        try {
            java.util.Date fechaDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaHoraFormateada);
            fechaTxt.setDate(fechaDate);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
    }

    private Amortizaciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PosicionJRBTN = new javax.swing.JRadioButton();
        CursorJRBTN = new javax.swing.JRadioButton();
        posicion1JCBOX = new javax.swing.JComboBox<>();
        posicion2JCBOX = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numeroTxt = new javax.swing.JTextField();
        cuartoTxt = new javax.swing.JTextField();
        Cuarto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DocumentoJCBOX = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Cuarto1 = new javax.swing.JLabel();
        importeTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        agregarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        fechaTxt = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalleTxtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PosicionJRBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PosicionJRBTN.setText("Posición");
        PosicionJRBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PosicionJRBTNActionPerformed(evt);
            }
        });

        CursorJRBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CursorJRBTN.setText("Hasta el cursor");
        CursorJRBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CursorJRBTNActionPerformed(evt);
            }
        });

        posicion1JCBOX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        posicion2JCBOX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(PosicionJRBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(posicion1JCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(posicion2JCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CursorJRBTN)
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PosicionJRBTN)
                    .addComponent(CursorJRBTN)
                    .addComponent(posicion1JCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posicion2JCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Numero:");

        numeroTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cuartoTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Cuarto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Cuarto.setText("Cuarto:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Docume:");

        DocumentoJCBOX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DocumentoJCBOX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vouchers" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Fecha:");

        Cuarto1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Cuarto1.setText("Importe:");

        importeTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Detalle:");

        agregarBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        agregarBtn.setText("Grabar");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        fechaTxt.setDateFormatString("d MMM y hh:mm:ss");

        detalleTxtArea.setColumns(20);
        detalleTxtArea.setRows(5);
        jScrollPane1.setViewportView(detalleTxtArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(Cuarto1))
                                            .addGap(22, 22, 22))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(20, 20, 20)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(21, 21, 21)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(Cuarto)
                                        .addGap(18, 18, 18)
                                        .addComponent(cuartoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(DocumentoJCBOX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fechaTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                        .addComponent(importeTxt, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelarBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(agregarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(agregarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cuarto)
                            .addComponent(cuartoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(DocumentoJCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cuarto1)
                    .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PosicionJRBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PosicionJRBTNActionPerformed
        if (PosicionJRBTN.isSelected()) {
            // Mostrar el contenido de slección por posición
            CursorJRBTN.setSelected(false);
            posicion1JCBOX.setVisible(true);
            posicion2JCBOX.setVisible(true);
        }
    }//GEN-LAST:event_PosicionJRBTNActionPerformed

    private void CursorJRBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CursorJRBTNActionPerformed
        if (CursorJRBTN.isSelected()) {
            // Mostrar el contenido del seleccion por cursor
            PosicionJRBTN.setSelected(false);
            posicion1JCBOX.setVisible(false);
            posicion2JCBOX.setVisible(false);
        }
    }//GEN-LAST:event_CursorJRBTNActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        PagoAlquiler pa = new PagoAlquiler();

        // Restablecer el número en el JTextField solo si se presionó "Cancelar"
        if (!grabarPresionado) {
            numeroTxt.setText(String.format("%07d", numeroActual));
        }
        
        pa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        PagoAlquiler pa = new PagoAlquiler();
    
        grabarPresionado = true; // Actualizar la bandera cuando se presiona "Grabar"
        
        // Incrementar el número solo si se presionó "Grabar"
        if (grabarPresionado) {
            numeroActual++;
        }
        
        if (PosicionJRBTN.isSelected()) {
            System.err.println("El id seleccionado en selección de posición es: " + idSeleccionado);
            System.err.println("El id del cuarto en selección de posición es: " + room_id_actual);
        } else if (CursorJRBTN.isSelected()) {
            System.err.println("El id seleccionado en selección de cursor es: " + idSeleccionado);
            System.err.println("El id del cuarto en selección de cursor es: " + room_id_actual);
        }
        
        pa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_agregarBtnActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Amortizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Amortizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Amortizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Amortizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Amortizaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cuarto;
    private javax.swing.JLabel Cuarto1;
    private javax.swing.JRadioButton CursorJRBTN;
    private javax.swing.JComboBox<String> DocumentoJCBOX;
    private javax.swing.JRadioButton PosicionJRBTN;
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cuartoTxt;
    private javax.swing.JTextArea detalleTxtArea;
    private com.toedter.calendar.JDateChooser fechaTxt;
    private javax.swing.JTextField importeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JComboBox<String> posicion1JCBOX;
    private javax.swing.JComboBox<String> posicion2JCBOX;
    // End of variables declaration//GEN-END:variables
}
