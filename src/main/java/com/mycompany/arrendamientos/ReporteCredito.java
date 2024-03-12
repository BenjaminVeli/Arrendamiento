package com.mycompany.arrendamientos;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReporteCredito extends javax.swing.JFrame {

    public ReporteCredito() {
        initComponents();
        
        java.util.Date fechaInicio = txtInicio.getDate();
        java.util.Date fechaFin = txtFin.getDate();
        
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar el comportamiento de cierre

        // Deshabilitar el botón de cerrar (X) y la maximización
        setResizable(false); // Deshabilitar la maximización
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
         btnImprimir.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 Date fechaInicio = txtInicio.getDate();
                 Date fechaFin = txtFin.getDate();
                 exportarAExcelCobranzaGlobal(fechaInicio, fechaFin);
             }
         });
         
         
         
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtInicio = new com.toedter.calendar.JDateChooser();
        txtFin = new com.toedter.calendar.JDateChooser();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Crear cuarto");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Reporte de credito ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Fecha Inicio :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Fecha Fin :");

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFin, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir)
                    .addComponent(btnSalir))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed
    
    private PagoAlquiler v1;
    
    public void setV1(PagoAlquiler v1){
        this.v1 = v1;
    }
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
         v1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

 
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
            java.util.logging.Logger.getLogger(ReporteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteCredito().setVisible(true);
            }
        });
    }
    
    
   public static void exportarAExcelCobranzaGlobal(Date fechaInicio, Date fechaFin) {
        try {
            // Conexión a la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/arrendamientos", "root", "");
            String consultaCompleta = "SELECT rent_calculation.id, datos_cli_prov.nombre AS cliente_nombre, cuarto.numcuarto, importe_variado.fecha , importe_variado.pago , importe_variado.fecha_amortizacion " +
                    "FROM rent_calculation " +
                    "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                    "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
                    "INNER JOIN importe_variado ON rent_calculation.id = importe_variado.rent_calculation_id " +
                    "WHERE importe_variado.fecha_amortizacion BETWEEN ? AND ? AND importe_variado.estado = 1";
            
            PreparedStatement preparedStatement = connection.prepareStatement(consultaCompleta);
            preparedStatement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Lista Global de Cobranza");

            XSSFCellStyle estiloProforma = workbook.createCellStyle();
            XSSFFont fontProforma = workbook.createFont();
            fontProforma.setFontHeightInPoints((short) 20);
            fontProforma.setBold(true);
            fontProforma.setUnderline(FontUnderline.SINGLE);
            estiloProforma.setFont(fontProforma);

            Row proformaRow = sheet.createRow(0);
            Cell proformaCellC = proformaRow.createCell(0);
            proformaCellC.setCellValue("LISTA GLOBAL DE COBRANZA");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
            proformaCellC.setCellStyle(estiloProforma);

            CellStyle estiloHeadersRow = workbook.createCellStyle();
            estiloHeadersRow.setAlignment(HorizontalAlignment.CENTER);
            estiloHeadersRow.setBorderBottom(BorderStyle.THIN);
            estiloHeadersRow.setBorderTop(BorderStyle.THIN);
            estiloHeadersRow.setBorderRight(BorderStyle.THIN);
            estiloHeadersRow.setBorderLeft(BorderStyle.THIN);

            XSSFFont fontHeader = workbook.createFont();
            fontHeader.setFontHeightInPoints((short) 13);

            CellStyle estiloInfoRow = workbook.createCellStyle();
            estiloInfoRow.setAlignment(HorizontalAlignment.CENTER);
            estiloInfoRow.setBorderBottom(BorderStyle.DASHED);

            Row headersRow = sheet.createRow(2);
            headersRow.createCell(0).setCellValue("ID");
            headersRow.createCell(1).setCellValue("Fecha");
            headersRow.createCell(2).setCellValue("Cuarto");
            headersRow.createCell(3).setCellValue("Cliente");
            headersRow.createCell(4).setCellValue("Total_Pagos");
            headersRow.createCell(5).setCellValue("Detalle");
            for (Cell cell : headersRow) {
                cell.setCellStyle(estiloHeadersRow);
                estiloHeadersRow.setFont(fontHeader);
            }

            int rowNum = 4;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(resultSet.getInt("id"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(resultSet.getTimestamp("fecha_amortizacion"));
                row.createCell(1).setCellValue(formattedDate);
                String cuartoNombre = resultSet.getString("numcuarto");
                String textoCuarto = "CUARTO # " + cuartoNombre;
                row.createCell(2).setCellValue(textoCuarto);
                row.createCell(3).setCellValue(resultSet.getString("cliente_nombre"));
                row.createCell(4).setCellValue(resultSet.getString("pago"));
                String fechaVencimiento = resultSet.getString("fecha");
                String textoVencimiento = "PAGO DEL " + fechaVencimiento;
                row.createCell(5).setCellValue(textoVencimiento);
                for (int i = 4; i < rowNum; i++) {
                    Row dataRow = sheet.getRow(i);
                    for (Cell cell : dataRow) {
                        cell.setCellStyle(estiloInfoRow);
                    }
                }
            }

            for (int i = 0; i < headersRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            File tempFile = File.createTempFile("detalle", ".xlsx");
            try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Datos exportados correctamente a Excel.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al exportar a Excel: " + e.toString());
            }

            Desktop.getDesktop().open(tempFile);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser txtFin;
    private com.toedter.calendar.JDateChooser txtInicio;
    // End of variables declaration//GEN-END:variables
}
