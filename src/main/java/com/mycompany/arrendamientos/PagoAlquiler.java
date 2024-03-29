package com.mycompany.arrendamientos;

import DAO.PagoAlquilerDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PagoAlquiler extends javax.swing.JFrame {
    
    ReporteCredito v2 = new ReporteCredito();
    
 public PagoAlquiler() {
        initComponents();
        PagoAlquilerDAO pa_dao = new PagoAlquilerDAO();
                
        this.setLocationRelativeTo(null);
        cargarNombresClientesActivos();
        pa_dao.MostrarRegistroAmortizacion(tbMostrarCalculos);
        
        // Aplicar el renderizador de celdas para cambiar el color de fondo según el valor de la columna "Pago"
        applyCellRenderer(tbImporteVariado);
        aplicarColorTbImporteInternet(tbImporteInternet);
        
        btnGeneral.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
                exportarAExcelGeneral();
            }
        });
        
        btnTodoCredito.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
        // Obtener el nombre del cliente seleccionado (supongamos que lo tienes en una variable llamada nombreClienteSeleccionado)
        String nombreClienteSeleccionado = (String) ListaClientesJCBox.getSelectedItem();
        // Llamar al método exportarAExcelTodoElCredito con el nombre del cliente seleccionado como argumento
        exportarAExcelTodoElCredito(nombreClienteSeleccionado);
            }
        });
        
        btnMorosos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
                exportarAExcelMorosos();
            }
        });
    }
    
    private void cargarNombresClientesActivos() {
        PagoAlquilerDAO objetoNombres = new PagoAlquilerDAO();
        ArrayList<String> cliente = objetoNombres.obtenerNombresClientesActivos();
        
        // Limpiar los elementos existentes en el JComboBox antes de agregar nuevos
        ListaClientesJCBox.removeAllItems();

        // Usar un HashSet para almacenar nombres únicos
        HashSet<String> nombresUnicos = new HashSet<>();
        for (String nombre : cliente) {
            nombresUnicos.add(nombre);
        }

        // Agregar los nombres únicos al JComboBox
        for (String nombre : nombresUnicos) {
            ListaClientesJCBox.addItem(nombre);
        }

        AutoCompleteDecorator.decorate(ListaClientesJCBox);
    }
    
    private void cargarTodosNombresClientes() {
        PagoAlquilerDAO objetoNombres = new PagoAlquilerDAO();
        ArrayList<String> cliente = objetoNombres.obtenerTodosNombresClientes();
        
        // Limpiar los elementos existentes en el JComboBox antes de agregar nuevos
        ListaClientesJCBox.removeAllItems();

        // Usar un HashSet para almacenar nombres únicos
        HashSet<String> nombresUnicos = new HashSet<>();
        for (String nombre : cliente) {
            nombresUnicos.add(nombre);
        }

        // Agregar los nombres únicos al JComboBox
        for (String nombre : nombresUnicos) {
            ListaClientesJCBox.addItem(nombre);
        }

        AutoCompleteDecorator.decorate(ListaClientesJCBox);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ListaClientesJCBox = new javax.swing.JComboBox<>();
        btnMore = new javax.swing.JButton();
        btnLess = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMetodoPago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAntes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAntes1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMostrarAlquileres = new javax.swing.JTable();
        Soles = new javax.swing.JLabel();
        txtSoles = new javax.swing.JTextField();
        Dolares = new javax.swing.JLabel();
        txtDolares = new javax.swing.JTextField();
        darBaja = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMostrarCalculos = new javax.swing.JTable();
        Dolares1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        btnRecuperar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbImporteVariado = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnReporteCreditos = new javax.swing.JButton();
        btnInteres = new javax.swing.JButton();
        btnDatosCredito = new javax.swing.JButton();
        btnObservacion = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();
        btnCobranzaGlobal = new javax.swing.JButton();
        btnCobranza = new javax.swing.JButton();
        btnMorosos = new javax.swing.JButton();
        btnTodoCredito = new javax.swing.JButton();
        btnDetallePago = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnAsignarInternet = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbImporteLuz = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbImporteInternet = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pago Alquiler");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Clientes:");

        ListaClientesJCBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ListaClientesJCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaClientesJCBoxActionPerformed(evt);
            }
        });

        btnMore.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMore.setText("+");
        btnMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoreActionPerformed(evt);
            }
        });

        btnLess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLess.setText("-");
        btnLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLessActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Forma de Pago:");

        txtMetodoPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMetodoPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Antes:");

        txtAntes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAntes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Actual:");

        txtAntes1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAntes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaClientesJCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLess)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(txtMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAntes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(txtAntes1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ListaClientesJCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMore)
                    .addComponent(btnLess)
                    .addComponent(jLabel2)
                    .addComponent(txtMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtAntes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtAntes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbMostrarAlquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbMostrarAlquileres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMostrarAlquileresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMostrarAlquileres);

        Soles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Soles.setText("Soles");

        txtSoles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Dolares.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Dolares.setText("Dolares");

        txtDolares.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDolares.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        darBaja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        darBaja.setText("Dar de baja");
        darBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(Soles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Dolares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(darBaja))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Dolares)
                        .addComponent(txtDolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(darBaja))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Soles)
                        .addComponent(txtSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Amortizaciones");

        tbMostrarCalculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbMostrarCalculos);

        Dolares1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Dolares1.setText("Total");

        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnReporte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporte.setText("Reporte");

        btnRecuperar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRecuperar.setText("Recuperar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRecuperar)
                        .addGap(33, 33, 33)
                        .addComponent(Dolares1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Dolares1)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo)
                    .addComponent(btnReporte)
                    .addComponent(btnRecuperar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbImporteVariado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbImporteVariado);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnReporteCreditos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteCreditos.setText("Reporte de Creditos");

        btnInteres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInteres.setText("Interes");

        btnDatosCredito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDatosCredito.setText("Datos del Credito");
        btnDatosCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosCreditoActionPerformed(evt);
            }
        });

        btnObservacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnObservacion.setText("Observación");

        btnGeneral.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGeneral.setText("General");

        btnCobranzaGlobal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCobranzaGlobal.setText("Cobranza Global");
        btnCobranzaGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobranzaGlobalActionPerformed(evt);
            }
        });

        btnCobranza.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCobranza.setText("Cobranza");

        btnMorosos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMorosos.setText("Morosos");

        btnTodoCredito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTodoCredito.setText("Todo el credito");

        btnDetallePago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDetallePago.setText("Detalle de Pago");

        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAsignarInternet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAsignarInternet.setText("Asignar Internet");
        btnAsignarInternet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarInternetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnReporteCreditos)
                        .addGap(71, 71, 71)
                        .addComponent(btnInteres)
                        .addGap(71, 71, 71)
                        .addComponent(btnDatosCredito)
                        .addGap(71, 71, 71)
                        .addComponent(btnAsignarInternet)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnObservacion)
                        .addGap(71, 71, 71)
                        .addComponent(btnGeneral)
                        .addGap(71, 71, 71)
                        .addComponent(btnCobranzaGlobal)
                        .addGap(71, 71, 71)
                        .addComponent(btnCobranza)
                        .addGap(71, 71, 71)
                        .addComponent(btnMorosos)
                        .addGap(71, 71, 71)
                        .addComponent(btnTodoCredito)
                        .addGap(71, 71, 71)
                        .addComponent(btnDetallePago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addGap(26, 26, 26))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteCreditos)
                    .addComponent(btnInteres)
                    .addComponent(btnDatosCredito)
                    .addComponent(btnAsignarInternet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnObservacion)
                    .addComponent(btnGeneral)
                    .addComponent(btnCobranzaGlobal)
                    .addComponent(btnCobranza)
                    .addComponent(btnMorosos)
                    .addComponent(btnTodoCredito)
                    .addComponent(btnDetallePago)
                    .addComponent(btnSalir))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbImporteLuz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fecha Anterior", "Fecha Actual", "Importe"
            }
        ));
        jScrollPane4.setViewportView(tbImporteLuz);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbImporteInternet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tbImporteInternet);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tabla de Internet");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tabla de Luz");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        CalculoAlquiler v1 = new CalculoAlquiler();
        v1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void ListaClientesJCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaClientesJCBoxActionPerformed
        PagoAlquilerDAO pa_dao = new PagoAlquilerDAO();
        // Obtener el nombre seleccionado
        String nombreSeleccionado = (String) ListaClientesJCBox.getSelectedItem();
        // Mostrar los registros de rent_calculation relacionados al nombre seleccionado
        pa_dao.MostrarAlquiler(tbMostrarAlquileres, nombreSeleccionado);
    }//GEN-LAST:event_ListaClientesJCBoxActionPerformed

    private void tbMostrarAlquileresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMostrarAlquileresMouseClicked
    // Llama al método para mostrar el importe variado cuando se hace clic en una fila de la tabla tbMostrarAlquileres
    PagoAlquilerDAO pa_dao = new PagoAlquilerDAO();
    pa_dao.SeleccionaryMostrarImporteVariado(tbMostrarAlquileres, tbImporteVariado);
    pa_dao.MostrarImporteInternet(tbMostrarAlquileres, tbImporteInternet);
    }//GEN-LAST:event_tbMostrarAlquileresMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        PagoAlquilerDAO paDAO = new PagoAlquilerDAO();
        
        int filaSeleccionada = tbImporteVariado.getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            String idSeleccionado = tbImporteVariado.getValueAt(filaSeleccionada, 0).toString();
            
            //String ordSeleccionado = tbImporteVariado.getValueAt(filaSeleccionada, 1).toString();
            
            // Obtén las fechas seleccionadas
            String fechaActual = tbImporteVariado.getValueAt(filaSeleccionada, 2).toString();
            
            // Verifica si hay una fila anterior
            int filaAnterior = filaSeleccionada - 1;
            String fechaAnterior = null;
            if (filaAnterior >= 0) {
                fechaAnterior = tbImporteVariado.getValueAt(filaAnterior, 2).toString();
            }
            
            String importeSeleccionado = tbImporteVariado.getValueAt(filaSeleccionada, 3).toString();
            double importes = Double.parseDouble(importeSeleccionado);
            
            String PagoSeleccionado = tbImporteVariado.getValueAt(filaSeleccionada, 4).toString();
            double pagos = Double.parseDouble(PagoSeleccionado);
            
            String saldosStr = tbImporteVariado.getValueAt(filaSeleccionada, 5).toString();
            double saldos = Double.parseDouble(saldosStr);
            
            // Obtén el ID del rent_calculation
            //String rentCalculationId = paDAO.obtenerRentCalculationIdPorImporteVariado(Integer.parseInt(idSeleccionado));
            
            // Obtén el ID del cuarto actualmente ocupado
            int room_id_actual = paDAO.obtenerCuartoidPorImporteVariado(Integer.parseInt(idSeleccionado));
            
            // Obtén el número de cuarto usando el ID del importe variado
            String numeroCuarto = paDAO.obtenerNumeroCuartoPorImporteVariado(Integer.parseInt(idSeleccionado));
            
            String nombreCliente = paDAO.obtenerNombreClientePorImporteVariado(Integer.parseInt(idSeleccionado));
            
            // Obtén el ID del cliente usando el ID del importe variado
           int clientId = paDAO.obtenerClienteIdPorImporteVariado(Integer.parseInt(idSeleccionado));
            
            // Obtén el arrendador asociado al cliente
           String nombreArrendador = paDAO.obtenerArrendador(clientId);
            
            // Abre el JFrame "Amortizaciones" y pasa los parámetro que se necesitan
            Amortizaciones amortizaciones = new Amortizaciones(idSeleccionado, room_id_actual, numeroCuarto, saldos, nombreCliente, importes , pagos, nombreArrendador, fechaAnterior, fechaActual, tbMostrarAlquileres);
            amortizaciones.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila primero.");
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCobranzaGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobranzaGlobalActionPerformed
        v2.setV1(this);
        v2.setVisible(true);
    }//GEN-LAST:event_btnCobranzaGlobalActionPerformed

    private void darBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darBajaActionPerformed
        int filaSeleccionada = tbMostrarAlquileres.getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            String idAlquiler = tbMostrarAlquileres.getValueAt(filaSeleccionada, 0).toString();
            
            // Abre el JFrame "Amortizaciones" y pasa los parámetro que se necesitan
            Dar_de_baja drBaja = new Dar_de_baja(idAlquiler);
            drBaja.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila primero.");
        }
    }//GEN-LAST:event_darBajaActionPerformed

    private void btnMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoreActionPerformed
        PagoAlquilerDAO pa_dao = new PagoAlquilerDAO();
        // Obtener el nombre seleccionado
        String nombreSeleccionado = (String) ListaClientesJCBox.getSelectedItem();
        // Mostrar los registros de rent_calculation relacionados al nombre seleccionado
        cargarTodosNombresClientes();
        pa_dao.MostrarAlquilerTODO(tbMostrarAlquileres, nombreSeleccionado);
    }//GEN-LAST:event_btnMoreActionPerformed

    private void btnLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLessActionPerformed
        PagoAlquilerDAO pa_dao = new PagoAlquilerDAO();
        // Obtener el nombre seleccionado
        String nombreSeleccionado = (String) ListaClientesJCBox.getSelectedItem();
        // Mostrar los registros de rent_calculation relacionados al nombre seleccionado
        cargarNombresClientesActivos();
        pa_dao.MostrarAlquiler(tbMostrarAlquileres, nombreSeleccionado);
    }//GEN-LAST:event_btnLessActionPerformed

    private void btnAsignarInternetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarInternetActionPerformed
        Asignar_Internet asignar_Internet = new Asignar_Internet(tbMostrarAlquileres);
        
        asignar_Internet.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAsignarInternetActionPerformed

    private void btnDatosCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosCreditoActionPerformed
        PagoAlquilerDAO paDAO = new PagoAlquilerDAO();
        
        int filaSeleccionada = tbMostrarAlquileres.getSelectedRow();

        if (filaSeleccionada >= 0) {
            String idSeleccionado = tbMostrarAlquileres.getValueAt(filaSeleccionada, 0).toString();

            // Obtén el ID del cuarto actualmente ocupado
            int room_id_actual = paDAO.obtenerCuartoIdDatos(Integer.parseInt(idSeleccionado));
            
            // Obtén el número de cuarto usando el ID del importe variado
            String numeroCuarto = paDAO.obtenerNumeroCuartoDatos(Integer.parseInt(idSeleccionado));
            
            BigDecimal garantia = paDAO.obtenerGarantia(Integer.parseInt(idSeleccionado));
        
        // Obtén la rent
            BigDecimal rent = paDAO.obtenerRent(Integer.parseInt(idSeleccionado));
            
            BigDecimal interesImporteMensual = paDAO.obtenerInteresImporteMensual(Integer.parseInt(idSeleccionado));
            
            BigDecimal total = paDAO.obtenerTotal(Integer.parseInt(idSeleccionado));
        
             String nombrePiso = paDAO.obtenerNombrePiso(Integer.parseInt(idSeleccionado));

            BigDecimal cuotas = paDAO.obtenerCuotas(Integer.parseInt(idSeleccionado));
            
            BigDecimal mensual = paDAO.obtenerMensual(Integer.parseInt(idSeleccionado));

            Date fecha = paDAO.obtenerFecha(Integer.parseInt(idSeleccionado));
            
            Date fechaIngreso = paDAO.obtenerFechaIngreso(Integer.parseInt(idSeleccionado));
        
            String tipoPago = paDAO.obtenerTipoPago(Integer.parseInt(idSeleccionado));

            BigDecimal pagoDiario = paDAO.obtenerPagoDiario(Integer.parseInt(idSeleccionado));
        
            BigDecimal pagoSem = paDAO.obtenerPagoSem(Integer.parseInt(idSeleccionado));
        
            BigDecimal quincenal = paDAO.obtenerQuincenal(Integer.parseInt(idSeleccionado));
            
            BigDecimal sumaCapital = paDAO.obtenerSumaCapital(Integer.parseInt(idSeleccionado));
            BigDecimal sumaInteres = paDAO.obtenerSumaInteres(Integer.parseInt(idSeleccionado));
            BigDecimal sumaPorPagar = paDAO.obtenerSumaPorPagar(Integer.parseInt(idSeleccionado));
            BigDecimal sumaImporte = paDAO.obtenerSumaImporteVariado(Integer.parseInt(idSeleccionado));
            
            DatosCredito v3 = new DatosCredito(idSeleccionado, room_id_actual, numeroCuarto, garantia, rent, interesImporteMensual, total, nombrePiso, cuotas, mensual, fecha, fechaIngreso, tipoPago, pagoDiario, pagoSem, quincenal, sumaCapital, sumaInteres, sumaPorPagar,sumaImporte);
            v3.setV1(this);
            v3.setVisible(true);
            this.setVisible(true);
            v3.toFront();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila primero.");
        }
    }//GEN-LAST:event_btnDatosCreditoActionPerformed

    private void applyCellRenderer(JTable table) {
        
        Color customRed = new Color(255, 117, 112); // Por ejemplo, un rojo brillante
        
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Obtiene el valor de "Saldos"
                String saldosStr = (String) table.getModel().getValueAt(row, 5);

                // Convierte el valor de "Saldos" a un double
                double saldos = Double.parseDouble(saldosStr);

                // Cambia el color de la fila basándote en el valor de "Saldos"
                if (saldos == 0.00) {
                    c.setBackground(Color.WHITE); // Fila blanca si los saldos son 0
                } else {
                    c.setBackground(customRed); // Fila roja si los saldos son diferentes de 0
                }

                return c;
            }
        });
    }

    private void aplicarColorTbImporteInternet(JTable table) {
        Color rojo = new Color(255, 117, 112); // Color rojo
        Color verde = new Color(119, 221, 119); // Color verde

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Obtener los valores de las columnas "Estado" y "Cancelado" de la fila actual
                String estadoInternetStr = (String) table.getValueAt(row, table.getColumn("Estado").getModelIndex());
                String canceladoInternetStr = (String) table.getValueAt(row, table.getColumn("Cancelado").getModelIndex());

                // Convertir las cadenas de texto a booleanos
                boolean estadoInternet = estadoInternetStr.equals("Pagar");
                boolean canceladoInternet = canceladoInternetStr.equals("Cancelado");

                // Aplicar el color adecuado según los valores de las columnas
                if (estadoInternet && !canceladoInternet) {
                    c.setBackground(rojo); // Si estado_internet es verdadero y cancelado_internet es falso, aplicar color rojo
                } else if (estadoInternet && canceladoInternet) {
                    c.setBackground(verde); // Si estado_internet es verdadero y cancelado_internet es verdadero, aplicar color verde
                } else {
                    c.setBackground(table.getBackground()); // De lo contrario, mantener el color de fondo predeterminado
                }

                return c;
            }
        });
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(PagoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagoAlquiler().setVisible(true);
            }
        });
    }   
    
    public static void exportarAExcelGeneral() {
    try {
        // Conexión a la base de datos
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/arrendamientos", "root", "");
        Statement statement = connection.createStatement();
        String consultaCompleta = "SELECT rent_calculation.id, datos_cli_prov.nombre AS cliente_nombre, cuarto.numcuarto,  importe_variado.fecha , importe_variado.saldos " +
    "FROM rent_calculation " +
    "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
    "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
    "INNER JOIN importe_variado ON rent_calculation.id = importe_variado.rent_calculation_id " +
    "WHERE importe_variado.fecha = (" +
    "   SELECT fecha " +
    "   FROM importe_variado " +
    "   WHERE rent_calculation_id = rent_calculation.id " +
    "   AND estado = 0 " +
    "   AND id > (SELECT id FROM importe_variado WHERE rent_calculation_id = rent_calculation.id AND estado = 1 LIMIT 1) " +
    "   ORDER BY id ASC " +
    "   LIMIT 1" +
    ")";
        ResultSet resultSet = statement.executeQuery(consultaCompleta);


        XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Alquiler Global");

            XSSFCellStyle estiloProforma = workbook.createCellStyle();
            XSSFFont fontProforma = workbook.createFont();
            fontProforma.setFontHeightInPoints((short) 20);
            fontProforma.setBold(true);
            fontProforma.setUnderline(FontUnderline.SINGLE);
            estiloProforma.setFont(fontProforma);
            
            
            Row proformaRow = sheet.createRow(0);
            Cell proformaCellC = proformaRow.createCell(0);
            proformaCellC.setCellValue("ALQUILER GLOBAL");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            proformaCellC.setCellStyle(estiloProforma);

            // Estilos para encabezado
            CellStyle estiloHeadersRow = workbook.createCellStyle();
            estiloHeadersRow.setAlignment(HorizontalAlignment.CENTER);
            estiloHeadersRow.setBorderBottom(BorderStyle.THIN);
            estiloHeadersRow.setBorderTop(BorderStyle.THIN);
            
            XSSFFont fontHeader = workbook.createFont();
            fontHeader.setFontHeightInPoints((short) 13);

            
            // Estilos para filas de informacióm
            CellStyle estiloInfoRow = workbook.createCellStyle();
            estiloInfoRow.setAlignment(HorizontalAlignment.CENTER);
            estiloInfoRow.setBorderBottom(BorderStyle.DASHED);
            

           
            // Encabezados de columnas
            Row headersRow = sheet.createRow(2);
            headersRow.createCell(0).setCellValue("ID");
            headersRow.createCell(1).setCellValue("Cuarto");
            headersRow.createCell(2).setCellValue("Cliente");
            headersRow.createCell(3).setCellValue("Saldo");
            headersRow.createCell(4).setCellValue("F.Vencimiento");
            for (Cell cell : headersRow) {
            cell.setCellStyle(estiloHeadersRow);
            estiloHeadersRow.setFont(fontHeader);

            }
            
            int rowNum = 4; // Empieza a escribir los datos desde la fila 3
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(resultSet.getInt("id"));
                String numCuarto = resultSet.getString("numcuarto");
                String textoCompleto = "CUARTO # " + numCuarto;
                row.createCell(1).setCellValue(textoCompleto);
                row.createCell(2).setCellValue(resultSet.getString("cliente_nombre"));
                row.createCell(3).setCellValue(resultSet.getString("saldos"));
                row.createCell(4).setCellValue(resultSet.getString("fecha"));
                for (int i = 4; i < rowNum; i++) { // Suponiendo que las filas de datos comienzan desde la fila 4
            Row dataRow = sheet.getRow(i);
            for (Cell cell : dataRow) {
                cell.setCellStyle(estiloInfoRow);
            }
        }
            }

            // Autoajustar el ancho de las columnas
            for (int i = 0; i < headersRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

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
    
    public static void exportarAExcelTodoElCredito(String nombreClienteSeleccionado) {
        try {
            // Conexión a la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/arrendamientos", "root", "");
            Statement statement = connection.createStatement();
            String consultaCompleta = "SELECT rent_calculation.*, datos_cli_prov.direccion_propietario, datos_cli_prov.celular, datos_cli_prov.ruc, datos_cli_prov.dni_propietario, cuarto.numcuarto AS nombre_cuarto, rent_calculation.tipo_pago,"
                + " importe_variado.ord, importe_variado.fecha_amortizacion, importe_variado.importe, importe_variado.pago , importe_variado.fecha" +
                " FROM rent_calculation " +
                "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
                "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
                "INNER JOIN importe_variado ON rent_calculation.id = importe_variado.rent_calculation_id " +
                "WHERE datos_cli_prov.nombre = '" + nombreClienteSeleccionado + "' ORDER BY importe_variado.ord";

            ResultSet resultSet = statement.executeQuery(consultaCompleta);

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Todo el credito");
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActual = formatoFecha.format(new Date());
            
            DecimalFormat formatoDecimal = new DecimalFormat("#0.00");
            
            XSSFCellStyle estiloAlquiler = workbook.createCellStyle();
            XSSFFont fontAlquiler = workbook.createFont();
            fontAlquiler.setFontHeightInPoints((short) 20);
            fontAlquiler.setBold(true);
            fontAlquiler.setUnderline(FontUnderline.SINGLE);
            estiloAlquiler.setFont(fontAlquiler);

            XSSFCellStyle estiloCelda = workbook.createCellStyle();
            estiloCelda.setAlignment(HorizontalAlignment.CENTER);
            estiloCelda.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCelda.setBorderTop(BorderStyle.THIN);
            estiloCelda.setBorderLeft(BorderStyle.THIN);
            estiloCelda.setBorderRight(BorderStyle.THIN);
            estiloCelda.setBorderBottom(BorderStyle.THIN);
            
            CellStyle estiloCeldaCentrado = workbook.createCellStyle();
            estiloCeldaCentrado.setAlignment(HorizontalAlignment.CENTER);
            estiloCeldaCentrado.setVerticalAlignment(VerticalAlignment.CENTER);

            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            estiloCelda.setFont(font);

            Row proformaRow = sheet.createRow(0);
            Cell proformaCellC = proformaRow.createCell(0);
            proformaCellC.setCellValue("ALQUILER");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            proformaCellC.setCellStyle(estiloAlquiler);

            String nombre = "";
            String direccion = "";
            String telefono = "";
            String ruc = "";
            String dni = "";
            String tpago = "";
            String cuarto = "";

            // Recuperar los datos del cliente
            if (resultSet.next()) {
                nombre = nombreClienteSeleccionado;
                direccion = resultSet.getString("direccion_propietario");
                telefono = resultSet.getString("celular");
                ruc = resultSet.getString("ruc");
                dni = resultSet.getString("dni_propietario");
                tpago = resultSet.getString("tipo_pago");
                cuarto = resultSet.getString("nombre_cuarto");
            }

            Row fila3 = sheet.createRow(2);
            Cell celda1 = fila3.createCell(0);
            celda1.setCellValue("NOMBRE : ");

            Cell celda1nm = fila3.createCell(1);
            celda1nm.setCellValue(nombre);

            Cell celda1fc = fila3.createCell(3);
            celda1fc.setCellValue("Fecha : ");
            
            Cell celdaFechaValor = fila3.createCell(4);
        celdaFechaValor.setCellValue(fechaActual);
            
            Row fila4 = sheet.createRow(3);
            Cell celda2 = fila4.createCell(0);
            celda2.setCellValue("DIRECCION : ");

            Cell celda2fc = fila4.createCell(1);
            celda2fc.setCellValue(direccion);

            Cell celda2tl = fila4.createCell(3);
            celda2tl.setCellValue("Télef : ");

            Cell celda2tlf = fila4.createCell(4);
            celda2tlf.setCellValue(telefono);

            Row fila5 = sheet.createRow(4);
            Cell celda3 = fila5.createCell(0);
            celda3.setCellValue("R.U.C : ");

            Cell celda3ruc = fila5.createCell(1);
            celda3ruc.setCellValue(ruc);

            Cell celda3DNI = fila5.createCell(3);
            celda3DNI.setCellValue("DNI : ");

            Cell celda3dnis = fila5.createCell(4);
            celda3dnis.setCellValue(dni);

            Row fila6 = sheet.createRow(5);
            Cell celda4 = fila6.createCell(0);
            celda4.setCellValue("CUARTO # ");

            Cell celda4ct = fila6.createCell(1);
            celda4ct.setCellValue(cuarto);

            Cell celda4tpg = fila6.createCell(3);
            celda4tpg.setCellValue("Tipo de Pago : ");

            Cell celda4tpgs = fila6.createCell(4);
            celda4tpgs.setCellValue(tpago);

            sheet.autoSizeColumn(0); 
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            String[] encabezados = {"ORD", "FECHA VENCIMIENTO", "FECHA PAGO", "IMPORTE", "PAGO"};

            // Agregar encabezados de la tabla de importe_variado
            int rowNum = 8;
            Row headersRow = sheet.createRow(rowNum++);
            for (int i = 0; i < encabezados.length; i++) {
                Cell cell = headersRow.createCell(i);
                cell.setCellValue(encabezados[i]);
                cell.setCellStyle(estiloCelda);
            }

        do {
            Row filaImporteVariado = sheet.createRow(rowNum++);
            for (int i = 0; i < 5; i++) {
                Cell celda = filaImporteVariado.createCell(i);
                celda.setCellStyle(estiloCeldaCentrado);
                switch (i) {
                    case 0:
                        celda.setCellValue(resultSet.getInt("importe_variado.ord"));
                        break;
                    case 1:
                        Date fecha = resultSet.getDate("importe_variado.fecha");
                        String fechaFormateada = formatoFecha.format(fecha);
                        celda.setCellValue(fechaFormateada);
                        break;
                    case 2:
                        // Formatear la fecha "fecha_amortizacion"
                        Date fechaAmortizacion = resultSet.getDate("importe_variado.fecha_amortizacion");
                        String fechaAmortizacionFormateada = (fechaAmortizacion != null) ? formatoFecha.format(fechaAmortizacion) : "";
                        celda.setCellValue(fechaAmortizacionFormateada);
                        break;
                    case 3:
                        // Formatear el importe
                        double importe = resultSet.getDouble("importe_variado.importe");
                        String importeFormateado = formatoDecimal.format(importe);
                        celda.setCellValue(importeFormateado);
                        break;
                    case 4:
                        // Formatear el pago
                        double pago = resultSet.getDouble("importe_variado.pago");
                        String pagoFormateado = formatoDecimal.format(pago);
                        celda.setCellValue(pagoFormateado);
                        break;
                    default:
                        break;
                }
            }
        } while (resultSet.next());
            
            for (int i = 0; i < encabezados.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
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
    
    public static void exportarAExcelMorosos(){
    
        try {
        // Conexión a la base de datos
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/arrendamientos", "root", "");
        Statement statement = connection.createStatement();
        String consultaCompleta = "SELECT rent_calculation.id, datos_cli_prov.nombre AS cliente_nombre, cuarto.numcuarto,  importe_variado.fecha , importe_variado.saldos " +
    "FROM rent_calculation " +
    "INNER JOIN datos_cli_prov ON rent_calculation.client_id = datos_cli_prov.id " +
    "INNER JOIN cuarto ON rent_calculation.room_id = cuarto.id " +
    "INNER JOIN importe_variado ON rent_calculation.id = importe_variado.rent_calculation_id " +
    "WHERE importe_variado.estado = 0 AND CURDATE() > importe_variado.fecha;";

        ResultSet resultSet = statement.executeQuery(consultaCompleta);


        XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Alquiler Global");

            XSSFCellStyle estiloProforma = workbook.createCellStyle();
            XSSFFont fontProforma = workbook.createFont();
            fontProforma.setFontHeightInPoints((short) 20);
            fontProforma.setBold(true);
            fontProforma.setUnderline(FontUnderline.SINGLE);
            estiloProforma.setFont(fontProforma);
            
            
            Row proformaRow = sheet.createRow(0);
            Cell proformaCellC = proformaRow.createCell(0);
            proformaCellC.setCellValue("LISTA DE MOROSOS");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            proformaCellC.setCellStyle(estiloProforma);

            // Estilos para encabezado
            CellStyle estiloHeadersRow = workbook.createCellStyle();
            estiloHeadersRow.setAlignment(HorizontalAlignment.CENTER);
            estiloHeadersRow.setBorderBottom(BorderStyle.THIN);
            estiloHeadersRow.setBorderTop(BorderStyle.THIN);
            estiloHeadersRow.setBorderRight(BorderStyle.THIN);
            estiloHeadersRow.setBorderLeft(BorderStyle.THIN);
            
            XSSFFont fontHeader = workbook.createFont();
            fontHeader.setFontHeightInPoints((short) 13);

            
            // Estilos para filas de informacióm
            CellStyle estiloInfoRow = workbook.createCellStyle();
            estiloInfoRow.setAlignment(HorizontalAlignment.CENTER);
            estiloInfoRow.setBorderBottom(BorderStyle.DASHED);
            

           
            // Encabezados de columnas
            Row headersRow = sheet.createRow(2);
            headersRow.createCell(0).setCellValue("ID");
            headersRow.createCell(1).setCellValue("Cuarto");
            headersRow.createCell(2).setCellValue("Cliente");
            headersRow.createCell(3).setCellValue("Saldo");
            headersRow.createCell(4).setCellValue("F.Vencimiento");
            for (Cell cell : headersRow) {
            cell.setCellStyle(estiloHeadersRow);
            estiloHeadersRow.setFont(fontHeader);

            }
            
            sheet.setColumnWidth(0, 7 * 256); // Columna A (ID)
            sheet.setColumnWidth(1, 15 * 256); // Columna B (Cuarto)
            sheet.setColumnWidth(2, 40 * 256); // Columna C (Cliente)
            sheet.setColumnWidth(3, 10 * 256); // Columna D (Saldo)
            sheet.setColumnWidth(4, 15 * 256); // Columna E (F.Vencimiento)
            
            int rowNum = 4; // Empieza a escribir los datos desde la fila 3
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(resultSet.getInt("id"));
                String numCuarto = resultSet.getString("numcuarto");
                String textoCompleto = "CUARTO # " + numCuarto;
                row.createCell(1).setCellValue(textoCompleto);
                row.createCell(2).setCellValue(resultSet.getString("cliente_nombre"));
                row.createCell(3).setCellValue(resultSet.getString("saldos"));
                row.createCell(4).setCellValue(resultSet.getString("fecha"));
                for (int i = 4; i < rowNum; i++) { // Suponiendo que las filas de datos comienzan desde la fila 4
            Row dataRow = sheet.getRow(i);
            for (Cell cell : dataRow) {
                cell.setCellStyle(estiloInfoRow);
            }
        }
            }

 

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
    private javax.swing.JLabel Dolares;
    private javax.swing.JLabel Dolares1;
    private javax.swing.JComboBox<String> ListaClientesJCBox;
    private javax.swing.JLabel Soles;
    private javax.swing.JButton btnAsignarInternet;
    private javax.swing.JButton btnCobranza;
    private javax.swing.JButton btnCobranzaGlobal;
    private javax.swing.JButton btnDatosCredito;
    private javax.swing.JButton btnDetallePago;
    private javax.swing.JButton btnGeneral;
    private javax.swing.JButton btnInteres;
    private javax.swing.JButton btnLess;
    private javax.swing.JButton btnMore;
    private javax.swing.JButton btnMorosos;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnObservacion;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnReporteCreditos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTodoCredito;
    private javax.swing.JButton darBaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tbImporteInternet;
    private javax.swing.JTable tbImporteLuz;
    private javax.swing.JTable tbImporteVariado;
    private javax.swing.JTable tbMostrarAlquileres;
    private javax.swing.JTable tbMostrarCalculos;
    private javax.swing.JTextField txtAntes;
    private javax.swing.JTextField txtAntes1;
    private javax.swing.JTextField txtDolares;
    private javax.swing.JTextField txtMetodoPago;
    private javax.swing.JTextField txtSoles;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
