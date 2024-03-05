package com.mycompany.arrendamientos;

import DAO.PagoAlquilerDAO;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Amortizaciones extends javax.swing.JFrame {

    private String idSeleccionado;
    private int room_id_actual;
    private String numeroCuarto;
    private double saldos;
    
    public Amortizaciones(String idSeleccionado, int room_id_actual, String numeroCuarto, double saldos) {
        initComponents();
        
        btnImprimir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
                exportarBoletaExcel();
            }
        });
        
        
        this.setLocationRelativeTo(null);
        this.idSeleccionado = idSeleccionado;
        this.room_id_actual = room_id_actual;
        this.numeroCuarto = numeroCuarto;
        this.saldos = saldos;
        
        // Configurar el formato deseado para la fecha y hora
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatoFechaHora);
        
        // Establecer la fecha y hora en el JDateChooser
        try {
            java.util.Date fechaDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaHoraFormateada);
            fechaTxt.setDate(fechaDate);
            cuartoTxt.setText(numeroCuarto);
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
                                    .addComponent(agregarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnImprimir)
                                        .addComponent(cancelarBtn)))))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cuarto1)
                            .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(10, Short.MAX_VALUE))
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
        double nuevoSaldo = saldos - importe;    boolean nuevoEstado = (nuevoSaldo == 0); // Si el nuevo saldo es cero, el estado es true (cancelado), de lo contrario es false (no cancelado)
        
        
        // Convertir de java.util.Date a java.sql.Timestamp
        java.sql.Timestamp fechaHoraSQL = new java.sql.Timestamp(fechaHora.getTime());
        
        paDAO.actualizarSaldo(Integer.parseInt(idSeleccionado), nuevoSaldo);
        paDAO.actualizarEstado(Integer.parseInt(idSeleccionado), nuevoEstado);
        paDAO.reiniciarSaldosSubsiguientes(Integer.parseInt(idSeleccionado), nuevoSaldo, importe);
        
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
    
    public static void exportarBoletaExcel(){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Voucher");
            
            XSSFCellStyle estiloCelda = workbook.createCellStyle();
            estiloCelda.setBorderBottom(BorderStyle.THIN);
            estiloCelda.setBorderTop(BorderStyle.THIN);
            estiloCelda.setBorderRight(BorderStyle.THIN);
            estiloCelda.setBorderLeft(BorderStyle.THIN);

            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
            estiloCelda.setFont(font);
            
            XSSFCellStyle estiloCeldaSinBorde = workbook.createCellStyle();
            
            XSSFFont fontSBorde = workbook.createFont();
            fontSBorde.setFontHeightInPoints((short) 12);
            estiloCeldaSinBorde.setFont(fontSBorde);
            
            
            Row voucherRow = sheet.createRow(0);
            
            Cell voucherLabelCell = voucherRow.createCell(0);
            voucherLabelCell.setCellValue("VOUCHER");
            voucherLabelCell.setCellStyle(estiloCelda);

            Row numeroRow = sheet.createRow(1);
            
            Cell numeroLabelCell = numeroRow.createCell(0);
            numeroLabelCell.setCellValue("NUMERO");
            numeroLabelCell.setCellStyle(estiloCeldaSinBorde);
           
          
            Row nombreRow = sheet.createRow(2);
            
            Cell nombreLabelCell = nombreRow.createCell(0);
            nombreLabelCell.setCellValue("NOMBRE");
            nombreLabelCell.setCellStyle(estiloCeldaSinBorde);
            
            Row fechaRow = sheet.createRow(3);
            
            Cell fechaLabelCell = fechaRow.createCell(0);
            fechaLabelCell.setCellValue("FECHA");
            fechaLabelCell.setCellStyle(estiloCeldaSinBorde);
            
            Row pagoRow = sheet.createRow(4);
            
            Cell pagoLabelCell = pagoRow.createCell(0);
            pagoLabelCell.setCellValue("aca va el importe");
            pagoLabelCell.setCellStyle(estiloCeldaSinBorde);
            
            Row detalleRow = sheet.createRow(5);
            
            Cell detalleLabelCell = detalleRow.createCell(0);
            detalleLabelCell.setCellValue("DETALLE PAGO DEL ");
            detalleLabelCell.setCellStyle(estiloCeldaSinBorde);

            Row fechayhoraRow = sheet.createRow(7);
            
            Cell fechayhoraLabelCell = fechayhoraRow.createCell(0);
            fechayhoraLabelCell.setCellValue(".... ");
            fechayhoraLabelCell.setCellStyle(estiloCelda);
            
            
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JComboBox<String> posicion1JCBOX;
    private javax.swing.JComboBox<String> posicion2JCBOX;
    // End of variables declaration//GEN-END:variables
}
