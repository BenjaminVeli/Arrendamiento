package com.mycompany.arrendamientos;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import javax.swing.JOptionPane;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;



public class Contrato extends javax.swing.JFrame {

    private CalculoAlquiler ca;
    
    public Contrato() {
        initComponents();
         
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
         @Override
         public void insertUpdate(javax.swing.event.DocumentEvent e) {
            filterTable();
         }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            filterTable();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            filterTable();
        }

    });
        
        
        this.setLocationRelativeTo(null);
        
        
        
         DAO.ContratoDAO objetoContrato = new DAO.ContratoDAO();
         objetoContrato.MostrarArrendadorCombo(cbArrendador, txtdireccionArrendador, txtDniArrendador, txtTeleArrendador,txtProvinciaArrendador,txtDepartamentoArrendador,txtDistritoArrendador);
         objetoContrato.MostrarGaranteCombo(cbGarante,txtDireccionGarante,txtDniGarante,txtTeleGarante);
         objetoContrato.MostrarArrendatario(cbArrendatario,txtDireccionArrendatario,txtDniArrendatario,txtTeleArrendatario,txtmensualidad,txtfecha,txtpiso,txtcuarto,txtgarantia,txtarea,txtConyuge,txtDniConyuge,txtCelularConyuge,txtCiudad,txtProvinciaArrendatario,txtDepartamentoArrendatario,txtDistritoArrendatario,txtfechafinal,txtestadocivil);

          
         objetoContrato.MostrarContrato(tbAlquiler);
         
        Limpiar();
        idtxt.setEnabled(false);
        
        btnExportar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
                exportarAWord();
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtpersonas = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtfechafinal = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtmensualidad = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtgarantia = new javax.swing.JTextField();
        txtarea = new javax.swing.JTextField();
        btnExportar = new javax.swing.JButton();
        txtpiso = new javax.swing.JTextField();
        txtcuarto = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        idtxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlquiler = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbArrendador = new javax.swing.JComboBox<>();
        cbArrendatario = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtdireccionArrendador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccionArrendatario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDniArrendador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTeleArrendador = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDniArrendatario = new javax.swing.JTextField();
        txtTeleArrendatario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbGarante = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDniConyuge = new javax.swing.JTextField();
        txtDniGarante = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        txtTeleGarante = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtCelularConyuge = new javax.swing.JTextField();
        txtDireccionGarante = new javax.swing.JTextField();
        txtConyuge = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtDepartamentoArrendatario = new javax.swing.JTextField();
        txtProvinciaArrendatario = new javax.swing.JTextField();
        txtDepartamentoArrendador = new javax.swing.JTextField();
        txtProvinciaArrendador = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtDistritoArrendador = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtDistritoArrendatario = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtestadocivil = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CONTRATO DE ALQUILER");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Datos Ténicos");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("# Personas :");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("# Piso :");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("# Cuarto :");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Fecha inicio :");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Vence cont :");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Mensualidad :");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Garantía :");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Area :");

        btnExportar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportar.setText("Hacer contrato");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpersonas, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtpiso)
                            .addComponent(txtcuarto))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel26))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtfechafinal, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(txtfecha)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(txtmensualidad)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtarea, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtgarantia))))
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportar)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtpersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel27)
                            .addComponent(txtfechafinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(txtpiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel28)
                            .addComponent(txtmensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtgarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportar)
                .addGap(22, 22, 22))
        );

        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        idtxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tbAlquiler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAlquiler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAlquilerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAlquiler);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Datos del Arrendador");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Datos del Arrendatario");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nombre :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nombre :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Dirección :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Dirección :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("DNI :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Teléfono :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("DNI :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Teléfono :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Datos del Cónyuge ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Datos del Garante o Verificador");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Nombre :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Nombre :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("DNI :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("DNI :");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Teléfono :");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Teléfono :");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Ciudad :");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Dirección :");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Departamento :");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Provincia :");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Departamento :");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Provincia :");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Distrito :");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Distrito :");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Estado Civil :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(txtdireccionArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDepartamentoArrendador, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDniArrendador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addComponent(jLabel35)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel20)
                                    .addComponent(txtDniConyuge, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCelularConyuge, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtConyuge, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTeleArrendador)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34)
                                            .addComponent(jLabel9))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtProvinciaArrendador)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(txtDistritoArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccionArrendatario)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(cbArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGarante, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(txtDniGarante, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(txtTeleGarante, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel21)
                    .addComponent(txtDireccionGarante)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(txtDniArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(35, 35, 35))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel36)
                                    .addGap(113, 113, 113)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDistritoArrendatario, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDepartamentoArrendatario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addGap(35, 35, 35)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(txtTeleArrendatario)
                            .addComponent(jLabel32)
                            .addComponent(txtProvinciaArrendatario)
                            .addComponent(txtestadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccionArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdireccionArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTeleArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDniArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTeleArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDepartamentoArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProvinciaArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDistritoArrendatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtestadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDniArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDepartamentoArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProvinciaArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDistritoArrendador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbGarante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConyuge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTeleGarante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDniGarante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDniConyuge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelularConyuge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionGarante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(idtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void setCA(CalculoAlquiler ca){
        this.ca = ca;
    }
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        ca.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        DAO.ContratoDAO objetoContrato = new DAO.ContratoDAO();
        objetoContrato.InsertarContrato(cbArrendador, cbArrendatario,cbGarante,txtpersonas);
        objetoContrato.MostrarContrato(tbAlquiler);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tbAlquilerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlquilerMouseClicked
        DAO.ContratoDAO objetoContrato = new DAO.ContratoDAO();
        objetoContrato.SeleccionarContrato(tbAlquiler, idtxt, cbArrendador, cbArrendatario,cbGarante, txtpersonas);
    }//GEN-LAST:event_tbAlquilerMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        DAO.ContratoDAO objetoContrato = new DAO.ContratoDAO();
        objetoContrato.ModificarContrato(tbAlquiler, idtxt, cbArrendador, cbArrendatario,  cbGarante, txtpersonas);
        objetoContrato.MostrarContrato(tbAlquiler);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DAO.ContratoDAO objetoContrato = new DAO.ContratoDAO();
        objetoContrato.EliminarContrato(idtxt);
        objetoContrato.MostrarContrato(tbAlquiler);
    }//GEN-LAST:event_btnEliminarActionPerformed

    
public void exportarAWord() {
    try {
        XWPFDocument documento = new XWPFDocument();

         XWPFParagraph parrafo1 = documento.createParagraph();
        parrafo1.setSpacingBetween(1.0);
        parrafo1.setAlignment(ParagraphAlignment.CENTER);
        parrafo1.setSpacingAfter(0);
        
        int margenSuperior = 560; // 1 cm
        int margenIzquierdo = 560; // 1 cm
        int margenEncuadernacion = 0; // 0 cm
        int margenInferior = 560; // 1 cm
        int margenDerecho = 560; // 1 cm

        CTSectPr sectPr = documento.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setTop(BigInteger.valueOf(margenSuperior));
        pageMar.setLeft(BigInteger.valueOf(margenIzquierdo));
        pageMar.setGutter(BigInteger.valueOf(margenEncuadernacion));
        pageMar.setBottom(BigInteger.valueOf(margenInferior));
        pageMar.setRight(BigInteger.valueOf(margenDerecho));
        
        
        XWPFRun run1 = parrafo1.createRun();
        run1.setText("CONTRATO DE ALQUILER");
        run1.setBold(true); // Establecer negrita
        run1.setUnderline(UnderlinePatterns.SINGLE);
        run1.setFontSize(13);

        XWPFParagraph parrafo2 = documento.createParagraph();
        parrafo2.setAlignment(ParagraphAlignment.LEFT);
        parrafo2.setSpacingAfter(0);

        XWPFRun run2 = parrafo2.createRun();
        parrafo2.setSpacingBetween(1.0);
        run2.setText("Conste por el presente documento, el CONTRATO DE ARRENDAMIENTO, que celebran de una parte, el Sr. ");
        run2.setFontSize(9);

        Object arrendadorSeleccionado = cbArrendador.getSelectedItem();
        String dniArrendador = txtDniArrendador.getText(); 
        String direccionArrendador = txtdireccionArrendador.getText();
        String provinciaArrendador = txtProvinciaArrendador.getText();
        Object arrendatarioSeleccionado = cbArrendatario.getSelectedItem();  
        String dniArrendatario = txtDniArrendatario.getText();
        String direccionArrendatario = txtDireccionArrendatario.getText();
        String provinciaArrendatario = txtProvinciaArrendatario.getText();
        String estadocivilArrendatario = txtestadocivil.getText();
        String contenidoArrendatario = arrendatarioSeleccionado.toString();       
        String contenidoArrendador = arrendadorSeleccionado.toString();
        String dniConyuge = txtDniConyuge.getText();
        String nombreConyuge = txtConyuge.getText();
        String nombreCuarto = txtcuarto.getText();
        String nombreFecha = txtfecha.getText();
        String nombreFechaFinal = txtfechafinal.getText();
        String mensualidad = txtmensualidad.getText();
        String garantia = txtgarantia.getText();
        String persona = txtpersonas.getText();
        String distritoArrendador = txtDistritoArrendador.getText();
        String departamentoArrendador = txtDepartamentoArrendador.getText();
        String nombrePiso = txtpiso.getText();
        String nombreArea = txtarea.getText();

        
        if (arrendadorSeleccionado != null) {
            run2.setText(contenidoArrendador + " identificado con DNI N.° " + dniArrendador + ", con domicilio en " + direccionArrendador + " , " + provinciaArrendador + " , el cual de aquí en adelante se le denominará EL ARRENDADOR y "
                    + "por otra parte el Sr. (a)(ta) " + contenidoArrendatario + " , identificado con " + dniArrendatario +  " , con domicilio en " + direccionArrendatario + " , " + provinciaArrendatario + " , con estado civil " + estadocivilArrendatario+
                    " , quien en adelante se le denominará EL ARRENDATARIO en los términos y condiciones siguientes: ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("PRIMERO.- El ARRENDADOR, es propietario del local sito en la "+  direccionArrendador + " del distrito de " + distritoArrendador +" Provincia de "+ provinciaArrendador + " y Departamento de " + departamentoArrendador + ". ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("SEGUNDO.- Por el presente contrato EL ARRENDADOR da en calidad de Arrendamiento a EL ARRENDATARIO, una parte del local del "
                    + nombrePiso + " , indicado en la cláusula anterior, Primero, " + direccionArrendador + " , que consta de un área de " + nombreArea + ". Con una entrada quedando en el interior de la vivienda, para ser utilizado como Dormitorio y vivienda. Del mismo que EL ARRENDATARIO, declara y conocer y recibir en perfecto estado, siendo la toma del ambiente, es prueba de aceptación de las referidas condiciones y correcto funcionamiento de los servicios, Así mismo ambas partes convienen en que los servicios de Agua y Desagüe, así como de Energía Eléctrica serán cancelados por parte del Arrendatario, muy aparte de la Mereced Conductiva,  de acuerdo a su consumo, mensual, quedamos tajantemente prohibido la crianza de todo tipo de animales. ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("TERCERO.- La Merced Conductiva mensual, convenida de mutuo y común acuerdo entre las partes contratantes para el local y sus servicios cuyo objeto de este contrato es de S/ "+mensualidad+ " Soles mensuales, monto que El ARRENDATARIO los abonará con toda puntualidad por adelantado, en el caso de incumplimiento el pago de 1 mensualidad ocasionará RESCISIÓN del presente contrato comprometiéndose EL ARRENDATARIO a devolver el local y sus servicios, en perfecto estado conforme los entrego al momento del ambiente. ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("CUARTO.- El plazo del presente contrato es por tiempo de 01(UN) Año, el cual empieza el " + nombreFecha+ " el mismo que tendrá vigencia de terminó al " + nombreFechaFinal+" como a rescindir antes a solicitud de ambas partes con una anticipación de 30 días. ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("QUINTO.- El ARRENDATARIO declara que conoce que toda vez, si es que considere necesario EL ARRENDADOR y/o su representante Administradora, podrá verificar el ambiente con el consentimiento de EL ARRENDATARIO su estado de conservación y el uso del Local Arrendado. ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("SEXTO.- El ARRENDADOR recibe la cantidad de S/ "+mensualidad+ " Soles que corresponde al pago del mes adelantado, y una GARANTÍA de S/ "+garantia+" Soles, cantidad que no generara los intereses y será devuelto al momento de la entrega del local o caso contrario deducirá de este depósito por alguna de la deuda pendiente que podría presentarse en el local arrendada .-");
            run2.addBreak();
            run2.addBreak();
            run2.setText("SEPTIMO. - De conformidad al Art.5 de la Ley 30201, que modifica el Art.594 del Código Procesal Civil, El arrendatario se allana desde ya a la demanda judicial para desocupar el inmueble por las causales de vencimiento de contrato de arrendamiento o por incumplimiento del pago de la renta de 2 meses y quince días. De acuerdo con lo establecido en el art.330 y siguientes del Código Procesal Civil.");
            run2.addBreak();
            run2.addBreak();
            run2.setText("OCTAVO.- De conformidad a la Ley Nro. 30933 \" Ley  que regula el procedimiento especial de desalojo con intervención notarial \" que tiene por objeto establecer y regular  el procedimiento especial de desalojo mediante la intervención del notario dentro de la competencia funcional dentro de la provincia en que se ubica el predio arrendado y con ejecución judicial del Juez de Paz Letrado del distrito donde se ubica el inmueble, expresamente ambas partes  ( arrendador y arrendatario) nos sometemos dentro de los alcances del mismo, con capacidad de ejercicio plena y con conocimiento expreso de los alcances del mismo.");
            run2.addBreak();
            run2.addBreak();
            run2.setText("NOVENO.- Que, ambas partes convienen de mutuo acuerdo para celebrar del Local y sus servicios cuyos objetos de este contrato de arrendamiento, que el Arrendatario "
                    + "debe responder por tres meses de la merced conductiva del contrato por alguna excepción quiere retirarse del ambiente antes de 03 meses, para las prevalencias del caso. Y cuando falla  del pagos, será causal automático de  nulidad de puro derecho del presente Contrato, la falta de pago de 1 mes, con plazo de 15 días o la mala conducta de EL ARRENDATARIO, el ARRENDADOR procederá a desalojar en caso de resistencia, se procederá con todas las de Ley. ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("DECIMO.- Quedando de acuerdo con el arrendatario, que ocuparan "+ persona + " personas dicho cuarto, en caso de exceder más personas a lo tratado, se procederá a incrementar el precio del alquiler del cuarto.");
            run2.addBreak();
            run2.addBreak();
            run2.setText("DECIMO PRIMERO.- Ambas partes renunciamos al fuero de sus domicilios y se someten en caso de surgir Controversias referente del presente Contrato de Arrendamiento,"
                    + " las partes se someterán a la competencia de un centro de Arbitraje RECONOCIDOS POR ley para todos los efectos del presente Contrato. ");
            run2.addBreak();
            run2.addBreak();
            run2.setText("De conformidad en las cláusulas estipuladas en el presente Contrato de Arrendamiento firmamos por duplicado estampando nuestras huellas digitales en señal de Conformidad del presente contrato " + nombreFecha);
        }

        if (arrendadorSeleccionado != null) {


            XWPFParagraph parrafo3 = documento.createParagraph();
            parrafo3.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun run3 = parrafo3.createRun();
            run3.addBreak();
            run3.addBreak();
            run3.addBreak();
            run3.addBreak();
            run3.setText("--------------------------------------------            ------------------------------------------------------        -----------------------------------------------------");
            run3.setFontSize(9);

             XWPFParagraph parrafo4 = documento.createParagraph();
            parrafo4.setAlignment(ParagraphAlignment.LEFT);
            parrafo4.setSpacingAfter(0);

            XWPFRun run4 = parrafo4.createRun();
            run4.setText(contenidoArrendador +"                      " + contenidoArrendatario+"                      " + nombreConyuge);
            run4.setFontSize(9);
        }

        XWPFParagraph parrafo5 = documento.createParagraph();
        parrafo5.setAlignment(ParagraphAlignment.LEFT);
        parrafo5.setSpacingAfter(0);
        
        XWPFRun run5 = parrafo5.createRun();
        run5.setText("DNI: " + dniArrendador+"                                                  "+ "DNI: " + dniArrendatario+"                                                               "+ "DNI: " + dniConyuge); 
        run5.setFontSize(9);
        

        XWPFParagraph parrafo6 = documento.createParagraph();
        parrafo6.setAlignment(ParagraphAlignment.LEFT);
        parrafo6.setSpacingAfter(0);

        XWPFRun run6 = parrafo6.createRun();
        run6.setText("EL ARRENDADOR" + "                                              EL ARRENDATARIO"+"                                                        CONYUGE DEL");
        run6.setFontSize(9);
        
        XWPFParagraph parrafo7 = documento.createParagraph();
        parrafo7.setAlignment(ParagraphAlignment.LEFT);
        parrafo7.setSpacingAfter(0);
        
        XWPFRun run7 = parrafo7.createRun();
        run7.setText("CUARTO #  " + nombreCuarto + "          Se hace entrega de .");
        run7.setFontSize(9);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        documento.write(out);

        File tempFile = File.createTempFile("documento", ".docx");

        try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
            out.writeTo(fileOut);
            JOptionPane.showMessageDialog(null, "Documento exportado correctamente a Word.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el documento de Word: " + e.getMessage());
        }

        out.close();

        Desktop.getDesktop().open(tempFile);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al crear el documento de Word: " + e.getMessage());
    }
}




     private void filterTable() {
    String searchText = txtSearch.getText().trim();
    DAO.ContratoDAO objetoContrato = new DAO.ContratoDAO();
    objetoContrato.FiltrarClientes(tbAlquiler, searchText);
    }
    
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
            java.util.logging.Logger.getLogger(Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbArrendador;
    private javax.swing.JComboBox<String> cbArrendatario;
    private javax.swing.JComboBox<String> cbGarante;
    private javax.swing.JTextField idtxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbAlquiler;
    private javax.swing.JTextField txtCelularConyuge;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtConyuge;
    private javax.swing.JTextField txtDepartamentoArrendador;
    private javax.swing.JTextField txtDepartamentoArrendatario;
    private javax.swing.JTextField txtDireccionArrendatario;
    private javax.swing.JTextField txtDireccionGarante;
    private javax.swing.JTextField txtDistritoArrendador;
    private javax.swing.JTextField txtDistritoArrendatario;
    private javax.swing.JTextField txtDniArrendador;
    private javax.swing.JTextField txtDniArrendatario;
    private javax.swing.JTextField txtDniConyuge;
    private javax.swing.JTextField txtDniGarante;
    private javax.swing.JTextField txtProvinciaArrendador;
    private javax.swing.JTextField txtProvinciaArrendatario;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTeleArrendador;
    private javax.swing.JTextField txtTeleArrendatario;
    private javax.swing.JTextField txtTeleGarante;
    private javax.swing.JTextField txtarea;
    private javax.swing.JTextField txtcuarto;
    private javax.swing.JTextField txtdireccionArrendador;
    private javax.swing.JTextField txtestadocivil;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtfechafinal;
    private javax.swing.JTextField txtgarantia;
    private javax.swing.JTextField txtmensualidad;
    private javax.swing.JTextField txtpersonas;
    private javax.swing.JTextField txtpiso;
    // End of variables declaration//GEN-END:variables

    private void Limpiar() {
        cbGarante.setSelectedIndex(-1);
        cbArrendatario.setSelectedIndex(-1);
        cbArrendador.setSelectedIndex(-1);
        txtDireccionArrendatario.setText("");
        txtDniArrendatario.setText("");
        txtTeleArrendatario.setText("");
        txtDireccionGarante.setText("");
        txtDniGarante.setText("");
        txtTeleGarante.setText("");
        txtCiudad.setText("");
        txtDniConyuge.setText("");
        txtCelularConyuge.setText("");
        txtdireccionArrendador.setText("");
        txtDniArrendador.setText("");
        txtTeleArrendador.setText("");
        txtpersonas.setText("");
        txtpiso.setText("");
        txtcuarto.setText("");
        txtfecha.setText("");
        txtfechafinal.setText("");
        txtmensualidad.setText("");
        txtgarantia.setText("");
        txtarea.setText("");
        txtConyuge.setText("");
        txtDepartamentoArrendador.setText("");
        txtProvinciaArrendador.setText("");
        txtDistritoArrendador.setText("");
        txtDepartamentoArrendatario.setText("");
        txtProvinciaArrendatario.setText("");
        txtDistritoArrendatario.setText("");
        txtestadocivil.setText("");
    }
}
