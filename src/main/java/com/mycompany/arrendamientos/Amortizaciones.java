package com.mycompany.arrendamientos;

import DAO.PagoAlquilerDAO;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Amortizaciones extends javax.swing.JFrame {

    private String idSeleccionado;
    private int room_id_actual;
    private String numeroCuarto;
    private double saldos;
    private double importes;
    private double pagos;
    
    public Amortizaciones(String idSeleccionado, int room_id_actual, String numeroCuarto, double saldos, String nombreCliente, double importes,double pagos, String nombreArrendador) {
        initComponents();
        
       btnImprimir.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String contenidoCliente = Clientetxt.getText();
            String contenidoCuarto = cuartoTxt.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String contenidoFecha = sdf.format(fechaTxt.getDate());
            SimpleDateFormat ftms = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String contenidoFechaHora = ftms.format(fechaTxt.getDate());
            String contenidoSoles = importeTxt.getText();
            String contenidoNumero = numeroTxt.getText();
            String contenidoDetalle = detalleTxtArea.getText();
            String contenidoArrendador = Arrendadortxt.getText();
            exportarBoletaExcel(contenidoCliente,contenidoCuarto,contenidoFecha,contenidoSoles,contenidoFechaHora,contenidoNumero,contenidoDetalle,contenidoArrendador);
        }
    });
        
        this.setLocationRelativeTo(null);
        this.idSeleccionado = idSeleccionado;
        this.room_id_actual = room_id_actual;
        this.numeroCuarto = numeroCuarto;
        this.saldos = saldos;
        this.importes = importes;
        
        // Configurar el formato deseado para la fecha y hora
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatoFechaHora);
        
        // Establecer la fecha y hora en el JDateChooser
        try {
            java.util.Date fechaDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaHoraFormateada);
            fechaTxt.setDate(fechaDate);
            cuartoTxt.setText(numeroCuarto);
            Clientetxt.setText(nombreCliente);
            Arrendadortxt.setText(nombreArrendador);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        
        // Obtener y mostrar el último número de amortización
        obtenerUltimoNumeroAmortizacion();
    }
    
    private void obtenerUltimoNumeroAmortizacion() {
        PagoAlquilerDAO paDAO = new PagoAlquilerDAO();
        String ultimoNumero = paDAO.obtenerUltimoNumeroAmortizacion();
        numeroTxt.setText(ultimoNumero);
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
        btnImprimir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Clientetxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Arrendadortxt = new javax.swing.JTextField();

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

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImprimir.setText("Imprimir");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Cliente:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Arrendador:");

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
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(36, 36, 36))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(20, 20, 20)))
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(DocumentoJCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(Cuarto)
                                                .addGap(18, 18, 18)
                                                .addComponent(cuartoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(Cuarto1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                        .addComponent(agregarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Clientetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                            .addComponent(Arrendadortxt)
                                            .addComponent(fechaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnImprimir)
                                            .addComponent(cancelarBtn))))))
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
                            .addComponent(DocumentoJCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cuarto1)
                            .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Clientetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Arrendadortxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addContainerGap(20, Short.MAX_VALUE))
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
        
        pa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        PagoAlquiler pa = new PagoAlquiler();
        PagoAlquilerDAO paDAO = new PagoAlquilerDAO();
        
        // Obtener los campos del formulario
        String num_amortizacion = numeroTxt.getText();
        java.util.Date fechaHora = fechaTxt.getDate();
        double importe = Double.parseDouble(importeTxt.getText());
        String detalle = detalleTxtArea.getText();
        int id_seleccionado =  Integer.parseInt(idSeleccionado);
        double importes_tbImporteVariado = importes;
        double pagos_tbImporteVariado = pagos;
        double nuevoSaldo = saldos - importe;    
        boolean nuevoEstado = (nuevoSaldo == 0); // Si el nuevo saldo es cero, el estado es true (cancelado), de lo contrario es false (no cancelado)
        
        // Si el pago seleccionado es mayor a cero, sumarlo al importe
        if (pagos_tbImporteVariado > 0) {
            importe += pagos_tbImporteVariado;
        }
        
        // Convertir de java.util.Date a java.sql.Timestamp
        java.sql.Timestamp fechaHoraSQL = new java.sql.Timestamp(fechaHora.getTime());
        
        paDAO.actualizarSaldo(Integer.parseInt(idSeleccionado), nuevoSaldo);
        paDAO.actualizarEstado(Integer.parseInt(idSeleccionado), nuevoEstado);
        paDAO.reiniciarSaldosSubsiguientes(Integer.parseInt(idSeleccionado), importes_tbImporteVariado);
        
        if (PosicionJRBTN.isSelected()) {
            System.err.println("El id seleccionado en selección de posición es: " + id_seleccionado);
            System.err.println("El id del cuarto en selección de posición es: " + room_id_actual);
            System.err.println("El numero del cuarto en selección de posición es: " + numeroCuarto);
            
            pa.setVisible(true);
            this.setVisible(false);
        } else if (CursorJRBTN.isSelected()) {
            System.err.println("El id seleccionado en selección de cursor es: " + id_seleccionado);
            System.err.println("El id del cuarto en selección de cursor es: " + room_id_actual);
            System.err.println("El numero del cuarto en selección de cursor es: " + numeroCuarto);
            System.err.println("El saldo seleccionado es: " + nuevoSaldo);
            System.err.println("El importe seleccionado de mi tabla tbImporteVariado es: " + importes_tbImporteVariado);
            System.err.println("El pago seleccionado de mi tabla tbImporteVariado es: " + pagos_tbImporteVariado); //Este es el valor obtenido del campo pago
            
            paDAO.insertarAmortizacion(id_seleccionado, num_amortizacion, importe, detalle, fechaHoraSQL);
            
            pa.setVisible(true);
            this.setVisible(false);
        } else if (!CursorJRBTN.isSelected() & !PosicionJRBTN.isSelected()) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción de, posición o hasta el cursor.");
        }
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
    
    public static void exportarBoletaExcel(String contenidoCliente, String contenidoCuarto, String contenidoFecha, String contenidoSoles,String contenidoFechaHora, String contenidoNumero,String contenidoDetalle, String contenidoArrendador) {
    try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Voucher");

        XSSFCellStyle estiloCelda = workbook.createCellStyle();
        estiloCelda.setBorderBottom(BorderStyle.THIN);
        estiloCelda.setBorderTop(BorderStyle.THIN);
        estiloCelda.setBorderRight(BorderStyle.THIN);
        estiloCelda.setBorderLeft(BorderStyle.THIN);

        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        estiloCelda.setFont(font);

        XSSFCellStyle estiloCeldaSinBorde = workbook.createCellStyle();

        XSSFFont fontSBorde = workbook.createFont();
        fontSBorde.setFontHeightInPoints((short) 14);
        estiloCeldaSinBorde.setFont(fontSBorde);
        
        XSSFCellStyle estiloCeldaAjustado = workbook.createCellStyle();

        XSSFFont fontAjustado = workbook.createFont();
        fontAjustado.setFontHeightInPoints((short) 14);
        estiloCeldaAjustado.setFont(fontAjustado);
        estiloCeldaAjustado.setWrapText(true);
        estiloCeldaAjustado.setVerticalAlignment(VerticalAlignment.TOP);
        
         XSSFCellStyle estiloCeldaDerecho = workbook.createCellStyle();
         estiloCeldaDerecho.setBorderTop(BorderStyle.THIN);
        estiloCeldaDerecho.setBorderBottom(BorderStyle.THIN);
        estiloCeldaDerecho.setBorderLeft(BorderStyle.THIN);
        estiloCeldaDerecho.setBorderRight(BorderStyle.THIN);
         
        XSSFFont fontDerecho = workbook.createFont();
        fontDerecho.setFontHeightInPoints((short) 14);
        estiloCeldaDerecho.setFont(fontDerecho);
        fontDerecho.setBold(true);
        estiloCeldaDerecho.setWrapText(true);
        estiloCeldaDerecho.setAlignment(HorizontalAlignment.RIGHT);
        estiloCeldaDerecho.setVerticalAlignment(VerticalAlignment.CENTER);
        
        Row voucherRow = sheet.createRow(0);
        for (int i = 0; i <= 5; i++) {
            Cell cell = voucherRow.createCell(i);
            cell.setCellValue(contenidoArrendador);
            cell.setCellStyle(estiloCelda);
        }
        Cell contenidoArrendadorCell = voucherRow.createCell(0);
        contenidoArrendadorCell.setCellValue(contenidoArrendador+"             VOUCHER");
        contenidoArrendadorCell.setCellStyle(estiloCelda);
        sheet.addMergedRegion(new CellRangeAddress(
            0, // Desde la fila 1
            0, // Hasta la fila 1 (misma fila)
            0, // Desde la columna 1
            5  // Hasta la columna 6
        ));
        voucherRow.setHeightInPoints(20);
        
        Row numeroRow = sheet.createRow(1);

        Cell numeroLabelCell = numeroRow.createCell(0);
        numeroLabelCell.setCellValue("NUMERO");
        numeroLabelCell.setCellStyle(estiloCeldaSinBorde);

        Cell contenidoNumeroCell = numeroRow.createCell(1);
        contenidoNumeroCell.setCellValue(contenidoNumero); 
        contenidoNumeroCell.setCellStyle(estiloCeldaSinBorde);
        
        
        Cell cuartoLabelCell = numeroRow.createCell(3);
        cuartoLabelCell.setCellValue("CUARTO # "+contenidoCuarto);
        cuartoLabelCell.setCellStyle(estiloCeldaSinBorde);
        
         Row nombreRow = sheet.createRow(2);

        Cell nombreLabelCell = nombreRow.createCell(0);
        nombreLabelCell.setCellValue("NOMBRE");
        nombreLabelCell.setCellStyle(estiloCeldaSinBorde);

        Cell contenidoClienteCell = nombreRow.createCell(1);
        contenidoClienteCell.setCellValue(contenidoCliente);
        contenidoClienteCell.setCellStyle(estiloCeldaSinBorde);
        sheet.addMergedRegion(new CellRangeAddress(
    nombreRow.getRowNum(),   // Desde la fila actual
    nombreRow.getRowNum(),   // Hasta la fila actual (misma fila)
    1,                       // Desde la columna 2 (0-indexed)
    5                        // Hasta la columna 6 (0-indexed)
        ));
        
        Row fechaRow = sheet.createRow(3);

        Cell fechaLabelCell = fechaRow.createCell(0);
        fechaLabelCell.setCellValue("FECHA");
        fechaLabelCell.setCellStyle(estiloCeldaSinBorde);

        Cell contenidoFechaCell = fechaRow.createCell(1);
        contenidoFechaCell.setCellValue(contenidoFecha);
        contenidoFechaCell.setCellStyle(estiloCeldaSinBorde);

        Row pagoRow = sheet.createRow(4);

        Cell pagoLabelCell = pagoRow.createCell(0);
        pagoLabelCell.setCellValue("SOLES");
        pagoLabelCell.setCellStyle(estiloCeldaSinBorde);

        Cell contenidoSolesCell = pagoRow.createCell(1);
        contenidoSolesCell.setCellValue("S/." + contenidoSoles);
        contenidoSolesCell.setCellStyle(estiloCeldaSinBorde);
        
        Row fechayhoraRow = sheet.createRow(7);
        
        for (int i = 0; i <= 5; i++) {
            Cell cell = fechayhoraRow.createCell(i);
            cell.setCellValue(contenidoFechaHora);
            cell.setCellStyle(estiloCeldaDerecho);
        }
        
        Cell fechayhoraLabelCell = fechayhoraRow.createCell(0);
        fechayhoraLabelCell.setCellValue(contenidoFechaHora);
        fechayhoraLabelCell.setCellStyle(estiloCeldaDerecho);
        sheet.addMergedRegion(new CellRangeAddress(
            7, // Desde la fila 8
            7, // Hasta la fila 8 (misma fila)
            0, // Desde la columna 1
            5  // Hasta la columna 6
        ));
        fechayhoraRow.setHeightInPoints(20);
        
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
        
        Row detalleRow = sheet.createRow(5);

        Cell detalleLabelCell = detalleRow.createCell(0);
        detalleLabelCell.setCellValue("DETALLE : "+contenidoDetalle);
        detalleLabelCell.setCellStyle(estiloCeldaAjustado);
        sheet.addMergedRegion(new CellRangeAddress(
            5, // Desde la fila 6
            5, // Hasta la fila 6 (misma fila)
            0, // Desde la columna 1
            5  // Hasta la columna 6
        ));
        detalleRow.setHeightInPoints(65);
        
        // Guardar el libro en un archivo temporal
        File tempFile = File.createTempFile("detalle", ".xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Datos exportados correctamente a Excel.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar a Excel: " + e.toString());
        }

        // Abrir el archivo Excel recién creado
        Desktop.getDesktop().open(tempFile);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.toString());
    }
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Arrendadortxt;
    private javax.swing.JTextField Clientetxt;
    private javax.swing.JLabel Cuarto;
    private javax.swing.JLabel Cuarto1;
    private javax.swing.JRadioButton CursorJRBTN;
    private javax.swing.JComboBox<String> DocumentoJCBOX;
    private javax.swing.JRadioButton PosicionJRBTN;
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cuartoTxt;
    private javax.swing.JTextArea detalleTxtArea;
    private com.toedter.calendar.JDateChooser fechaTxt;
    private javax.swing.JTextField importeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JComboBox<String> posicion1JCBOX;
    private javax.swing.JComboBox<String> posicion2JCBOX;
    // End of variables declaration//GEN-END:variables
}
