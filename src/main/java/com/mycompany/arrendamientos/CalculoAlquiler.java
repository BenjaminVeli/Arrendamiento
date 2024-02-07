package com.mycompany.arrendamientos;

import DAO.CalcularAlquilerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
public class CalculoAlquiler extends javax.swing.JFrame {
    
    public CalculoAlquiler() {
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
        }});

        this.setLocationRelativeTo(null);
        cargarNombres();
        cargarPisos();
        Limpiar();
        ructxt.setEnabled(false);
        direcciontxt.setEnabled(false);
        dnitxt.setEnabled(false);
        telefonotxt.setEnabled(false);
        idtxt.setEnabled(false);
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
        dao.MostrarAlquiler(tbTotalCalculo);
        btnExportar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
                exportarAExcel();
            }
        });
    }
    
    private void mostrarCalculos() {
        try {
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
            
        // Obtener los campos del formulario
         int cuotas = dao.obtenerNumeroCuotas(totaltxt.getText());
        java.util.Date utilFecha = fechatxt.getDate();
        double interes = Double.parseDouble(interesestxt.getText());
        double total_rent = Double.parseDouble(totalAlquilertxt.getText());
        
        // Verificar que todos los campos estén completos antes de continuar
        if (cuotas <= 0 || utilFecha == null || interes < 0 || total_rent <= 0) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos correctamente pi :'v.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convertir java.util.Date a java.sql.Date
        java.sql.Date fecha = new java.sql.Date(utilFecha.getTime());
        
        DefaultTableModel modelo = dao.MostrarCalculos(
            txtSumCapital,
            txtSumInteres,
            txtSumMensual,
            cuotas,
            fecha,
            total_rent,
            interes
        );
        
        double porPagar = dao.calcularPorPagar(total_rent, cuotas);
        porPagar = Math.round(porPagar * 100.0) / 100.0;
        System.out.println("porPagar en el JFRAME de mostrarCalculo: " + porPagar);
        mensualtxt.setText(String.valueOf(porPagar));
        
        tbCalculoAlquiler.setModel(modelo);
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void mostrarImporteDiario() {
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
        
        // Obtener los campos del formulario
         int cuotas = dao.obtenerNumeroCuotas(totaltxt.getText());
        java.util.Date utilFecha = fechaingresotxt.getDate();
        double total_rent = Double.parseDouble(totalAlquilertxt.getText());
        double sumaCapital = Double.parseDouble(txtSumCapital.getText());
        double sumaInteres = Double.parseDouble(txtSumInteres.getText());
        
        // Verificar que todos los campos estén completos antes de continuar
        if (cuotas <= 0 || utilFecha == null || totalAlquilertxt.getText().isEmpty() || txtSumCapital.getText().isEmpty() || txtSumInteres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos correctamente diario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convertir java.util.Date a java.sql.Date
        java.sql.Date fecha = new java.sql.Date(utilFecha.getTime());
        
        dao.MostrarImporteDiario(tbCalculoImporte, cuotas, fecha, total_rent, sumaCapital, sumaInteres);
        
        double importeDiario = dao.Importe(sumaCapital, sumaInteres);
        importeDiario = Math.round(importeDiario * 100.0) / 100.0;
        System.out.println("importeDiario en el JFRAME: " + importeDiario);
        txtSumImporte.setText(String.valueOf(importeDiario));
        
        double pagoDiario = dao.calcularImporteDiario(sumaCapital, sumaInteres, cuotas);
        pagoDiario = Math.round(pagoDiario * 100.0) / 100.0;
        pagoDiariotxt.setText(String.valueOf(pagoDiario));
        
        double pagoSemanal = dao.calcularImporteSemanal(sumaCapital, sumaInteres, cuotas);
        pagoSemanal = Math.round(pagoSemanal * 100.0) / 100.0;
        pagoSemtxt.setText(String.valueOf(pagoSemanal));
        
        double pagoQuincenal = dao.calcularImporteQuincenal(sumaCapital, sumaInteres, cuotas);
        pagoQuincenal = Math.round(pagoQuincenal * 100.0) / 100.0;
        pagoQuincenaltxt.setText(String.valueOf(pagoQuincenal));
    }
    
    private void mostrarImporteSemanal() {
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
        
        // Obtener los campos del formulario
         int cuotas = dao.obtenerNumeroCuotas(totaltxt.getText());
        java.util.Date utilFecha = fechaingresotxt.getDate();
        double total_rent = Double.parseDouble(totalAlquilertxt.getText());
        double sumaCapital = Double.parseDouble(txtSumCapital.getText());
        double sumaInteres = Double.parseDouble(txtSumInteres.getText());
        
         // Verificar que todos los campos estén completos antes de continuar
        if (cuotas <= 0 || utilFecha == null || total_rent <= 0) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos correctamente semanal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convertir java.util.Date a java.sql.Date
        java.sql.Date fecha = new java.sql.Date(utilFecha.getTime());
        
        dao.MostrarImporteSemanal(tbCalculoImporte, cuotas, fecha, total_rent, sumaCapital, sumaInteres);
        
        double importeSemanal = dao.Importe(sumaCapital, sumaInteres);
        importeSemanal = Math.round(importeSemanal * 100.0) / 100.0;
        txtSumImporte.setText(String.valueOf(importeSemanal));
        
        double pagoDiario = dao.calcularImporteDiario(sumaCapital, sumaInteres, cuotas);
        pagoDiario = Math.round(pagoDiario * 100.0) / 100.0;
        pagoDiariotxt.setText(String.valueOf(pagoDiario));
        
        double pagoSemanal = dao.calcularImporteSemanal(sumaCapital, sumaInteres, cuotas);
        pagoSemanal = Math.round(pagoSemanal * 100.0) / 100.0;
        pagoSemtxt.setText(String.valueOf(pagoSemanal));
        
        double pagoQuincenal = dao.calcularImporteQuincenal(sumaCapital, sumaInteres, cuotas);
        pagoQuincenal = Math.round(pagoQuincenal * 100.0) / 100.0;
        pagoQuincenaltxt.setText(String.valueOf(pagoQuincenal));
    }

    private void mostrarImporteQuincenal() {
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
        
        // Obtener los campos del formulario
         int cuotas = dao.obtenerNumeroCuotas(totaltxt.getText());
        java.util.Date utilFecha = fechaingresotxt.getDate();
        double total_rent = Double.parseDouble(totalAlquilertxt.getText());
        double sumaCapital = Double.parseDouble(txtSumCapital.getText());
        double sumaInteres = Double.parseDouble(txtSumInteres.getText());
        
         // Verificar que todos los campos estén completos antes de continuar
        if (cuotas <= 0 || utilFecha == null || total_rent <= 0) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos correctamente quincenal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convertir java.util.Date a java.sql.Date
        java.sql.Date fecha = new java.sql.Date(utilFecha.getTime());
        
        dao.MostrarImporteQuincenal(tbCalculoImporte, cuotas, fecha, total_rent, sumaCapital, sumaInteres);
        
        double importeQuincenal = dao.Importe(sumaCapital, sumaInteres);
        importeQuincenal = Math.round(importeQuincenal * 100.0) / 100.0;
        txtSumImporte.setText(String.valueOf(importeQuincenal));
        
        double pagoDiario = dao.calcularImporteDiario(sumaCapital, sumaInteres, cuotas);
        pagoDiario = Math.round(pagoDiario * 100.0) / 100.0;
        pagoDiariotxt.setText(String.valueOf(pagoDiario));
        
        double pagoSemanal = dao.calcularImporteSemanal(sumaCapital, sumaInteres, cuotas);
        pagoSemanal = Math.round(pagoSemanal * 100.0) / 100.0;
        pagoSemtxt.setText(String.valueOf(pagoSemanal));
        
        double pagoQuincenal = dao.calcularImporteQuincenal(sumaCapital, sumaInteres, cuotas);
        pagoQuincenal = Math.round(pagoQuincenal * 100.0) / 100.0;
        pagoQuincenaltxt.setText(String.valueOf(pagoQuincenal));
    }    
    
    private void mostrarImporteMensual() {
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
        
        // Obtener los campos del formulario
         int cuotas = dao.obtenerNumeroCuotas(totaltxt.getText());
        java.util.Date utilFecha = fechaingresotxt.getDate();
        double total_rent = Double.parseDouble(totalAlquilertxt.getText());
        double sumaCapital = Double.parseDouble(txtSumCapital.getText());
        double sumaInteres = Double.parseDouble(txtSumInteres.getText());
        
         // Verificar que todos los campos estén completos antes de continuar
        if (cuotas <= 0 || utilFecha == null || total_rent <= 0) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos correctamente mensual.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convertir java.util.Date a java.sql.Date
        java.sql.Date fecha = new java.sql.Date(utilFecha.getTime());
        
        dao.MostrarImporteMensual(tbCalculoImporte, cuotas, fecha, total_rent, sumaCapital, sumaInteres);
        
        double importeMensual = dao.Importe(sumaCapital, sumaInteres);
        importeMensual = Math.round(importeMensual * 100.0) / 100.0;
        txtSumImporte.setText(String.valueOf(importeMensual));
        
        double pagoDiario = dao.calcularImporteDiario(sumaCapital, sumaInteres, cuotas);
        pagoDiario = Math.round(pagoDiario * 100.0) / 100.0;
        pagoDiariotxt.setText(String.valueOf(pagoDiario));
        
        double pagoSemanal = dao.calcularImporteSemanal(sumaCapital, sumaInteres, cuotas);
        pagoSemanal = Math.round(pagoSemanal * 100.0) / 100.0;
        pagoSemtxt.setText(String.valueOf(pagoSemanal));
        
        double pagoQuincenal = dao.calcularImporteQuincenal(sumaCapital, sumaInteres, cuotas);
        pagoQuincenal = Math.round(pagoQuincenal * 100.0) / 100.0;
        pagoQuincenaltxt.setText(String.valueOf(pagoQuincenal));
    }    
    
    private void cargarNombres() {
        CalcularAlquilerDAO objetoNombres = new CalcularAlquilerDAO();
        ArrayList<String> cliente = objetoNombres.obtenerNombresClientes();
        
        for (String nombre : cliente) {
            search.addItem(nombre);
        }

        AutoCompleteDecorator.decorate(search);
    }
    
    private void cargarPisos(){
        CalcularAlquilerDAO objetoPiso = new CalcularAlquilerDAO();
        ArrayList<String> pisos = objetoPiso.obtenerPisos();
        
        for (String piso : pisos) {
            pisostxt.addItem(piso);
        }

        AutoCompleteDecorator.decorate(pisostxt);
        
        pisostxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarCuartosPorPiso();
            }
        });
    }
    
    private void cargarCuartosPorPiso() {
        CalcularAlquilerDAO objetoCuarto = new CalcularAlquilerDAO();
        
        String nombrePiso = (String) pisostxt.getSelectedItem();
        
        cuartostxt.removeAllItems();
        
        ArrayList<String> cuartos = objetoCuarto.obtenerCuartosPorPiso(nombrePiso);

        for (String cuarto : cuartos) {
            cuartostxt.addItem(cuarto);
        }

        AutoCompleteDecorator.decorate(cuartostxt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Grabarbtn = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTotalCalculo = new javax.swing.JTable();
        idtxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        registroClientebtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        alquilertxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        garantiatxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        totaltxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        interesestxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        mensualtxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pagoDiariotxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        pagoSemtxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pagoQuincenaltxt = new javax.swing.JTextField();
        selectPago = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        search = new javax.swing.JComboBox<>();
        totalAlquilertxt = new javax.swing.JTextField();
        pisostxt = new javax.swing.JComboBox<>();
        cuartostxt = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ructxt = new javax.swing.JTextField();
        fechatxt = new com.toedter.calendar.JDateChooser();
        fechaingresotxt = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        Separator = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        direcciontxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dnitxt = new javax.swing.JTextField();
        telefonotxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCalculoAlquiler = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSumCapital = new javax.swing.JTextField();
        txtSumInteres = new javax.swing.JTextField();
        txtSumMensual = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCalculoImporte = new javax.swing.JTable();
        txtSumImporte = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        crearPisotxt = new javax.swing.JButton();
        crearCuartobtn = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculo De Alquiler");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Grabarbtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Grabarbtn.setText("Guardar");
        Grabarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrabarbtnActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tbTotalCalculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbTotalCalculo.setToolTipText("");
        tbTotalCalculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTotalCalculoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTotalCalculo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idtxt)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Grabarbtn)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar)
                    .addComponent(Grabarbtn)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre");

        registroClientebtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        registroClientebtn.setText("...");
        registroClientebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroClientebtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Alquiler");

        alquilertxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        alquilertxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        alquilertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alquilertxtActionPerformed(evt);
            }
        });
        alquilertxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                alquilertxtKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Garantía");

        garantiatxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        garantiatxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Cuotas");

        totaltxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totaltxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        totaltxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaltxtActionPerformed(evt);
            }
        });
        totaltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totaltxtKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Pisos");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Interes (i)");

        interesestxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        interesestxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Mensual");

        mensualtxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mensualtxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        mensualtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensualtxtMouseClicked(evt);
            }
        });
        mensualtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mensualtxtActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Fecha");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Fecha inicio");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Pagos/tipo");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Pago diario");

        pagoDiariotxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pagoDiariotxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pagoDiariotxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pagoDiariotxtMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Pago Sem.");

        pagoSemtxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pagoSemtxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Moneda");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Quincenal");

        pagoQuincenaltxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pagoQuincenaltxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        selectPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        selectPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diario", "Semanal", "Quincenal", "Mensual" }));
        selectPago.setBorder(null);
        selectPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectPagoItemStateChanged(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles" }));
        jComboBox3.setToolTipText("");
        jComboBox3.setBorder(null);

        search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        totalAlquilertxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalAlquilertxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        totalAlquilertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAlquilertxtActionPerformed(evt);
            }
        });

        pisostxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pisostxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pisostxtActionPerformed(evt);
            }
        });

        cuartostxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cuartostxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuartostxtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Cuartos");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("RUC");

        ructxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Total");

        Separator.setBackground(new java.awt.Color(0, 0, 0));
        Separator.setPreferredSize(new java.awt.Dimension(3, 0));

        javax.swing.GroupLayout SeparatorLayout = new javax.swing.GroupLayout(Separator);
        Separator.setLayout(SeparatorLayout);
        SeparatorLayout.setHorizontalGroup(
            SeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        SeparatorLayout.setVerticalGroup(
            SeparatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Dirección");

        direcciontxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        direcciontxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        direcciontxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                direcciontxtMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("DNI");

        dnitxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dnitxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        dnitxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dnitxtMouseClicked(evt);
            }
        });

        telefonotxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        telefonotxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        telefonotxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                telefonotxtMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Celular");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel20)
                    .addComponent(jLabel5)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalAlquilertxt)
                    .addComponent(pisostxt, javax.swing.GroupLayout.Alignment.TRAILING, 0, 96, Short.MAX_VALUE)
                    .addComponent(garantiatxt)
                    .addComponent(alquilertxt)
                    .addComponent(totaltxt)
                    .addComponent(ructxt))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(interesestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(mensualtxt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechatxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(cuartostxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13)
                        .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(18, 18, 18)
                                    .addComponent(selectPago, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(fechaingresotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telefonotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pagoDiariotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(direcciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dnitxt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagoSemtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagoQuincenaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(search, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(registroClientebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ructxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel19))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registroClientebtn)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(alquilertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(mensualtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(fechatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cuartostxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(garantiatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalAlquilertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pisostxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(interesestxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(pagoSemtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pagoQuincenaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dnitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(telefonotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fechaingresotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(selectPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pagoDiariotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(direcciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbCalculoAlquiler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbCalculoAlquiler);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Totales");
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        txtSumCapital.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSumCapital.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSumInteres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSumInteres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSumMensual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSumMensual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbCalculoImporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbCalculoImporte);

        txtSumImporte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSumImporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumCapital, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtSumInteres, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtSumMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSumImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSumCapital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSumInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSumMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSumImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        crearPisotxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        crearPisotxt.setText("Crear Piso");
        crearPisotxt.setMaximumSize(new java.awt.Dimension(80, 27));
        crearPisotxt.setMinimumSize(new java.awt.Dimension(80, 27));
        crearPisotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPisotxtActionPerformed(evt);
            }
        });

        crearCuartobtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        crearCuartobtn.setText("Crear Cuarto");
        crearCuartobtn.setMaximumSize(new java.awt.Dimension(80, 27));
        crearCuartobtn.setMinimumSize(new java.awt.Dimension(80, 27));
        crearCuartobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCuartobtnActionPerformed(evt);
            }
        });

        btnExportar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportar.setText("Exportar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportar)
                .addGap(30, 30, 30)
                .addComponent(crearCuartobtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(crearPisotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crearPisotxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crearCuartobtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void GrabarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrabarbtnActionPerformed
        try {
            CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
            
            if (search.getSelectedItem() == null || alquilertxt.getText().isEmpty()
                    || totaltxt.getText().isEmpty() || pisostxt.getSelectedItem() == null
                    || cuartostxt.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener los campos del formulario
            int cuotas = dao.obtenerNumeroCuotas(totaltxt.getText());
            java.util.Date utilFecha = fechatxt.getDate();
            double interes = Double.parseDouble(interesestxt.getText());
            double total_rent = Double.parseDouble(totalAlquilertxt.getText());

            // Convertir java.util.Date a java.sql.Date
            java.sql.Date fecha = new java.sql.Date(utilFecha.getTime());
            
            dao.insertarCalculoAlquiler(search, alquilertxt,garantiatxt, pisostxt, cuartostxt,interesestxt,totaltxt,fechatxt,fechaingresotxt,mensualtxt, selectPago, pagoDiariotxt , pagoSemtxt, pagoQuincenaltxt);
            // Llamar al método para insertar los datos mensuales
            dao.insertarImporteMensual(search.getSelectedItem().toString(), cuotas, fecha, total_rent, interes, txtSumCapital, txtSumInteres, txtSumMensual);
            dao.MostrarAlquiler(tbTotalCalculo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los campos de alquiler y total deben contener valores numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al grabar el cálculo de alquiler: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_GrabarbtnActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
        dao.EliminarCalculoAlquiler(idtxt);
        dao.MostrarAlquiler(tbTotalCalculo);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // La funcion del buscador esta en el metodo CargarNombres, si falta algo agregalo
    }//GEN-LAST:event_searchActionPerformed

    private void registroClientebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroClientebtnActionPerformed
        RegistroDatosClientes rdc = new RegistroDatosClientes();
        rdc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_registroClientebtnActionPerformed

    private void totalAlquilertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAlquilertxtActionPerformed
        // calcular la multiplicación entre alguiler y total has las 
    }//GEN-LAST:event_totalAlquilertxtActionPerformed

    private void pisostxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pisostxtActionPerformed
        // La funcion del select de pisos  esta en el metodo cargarPisos, si falta algo agregalo
    }//GEN-LAST:event_pisostxtActionPerformed

    private void alquilertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alquilertxtActionPerformed
        // funcion para poner el monto del alquiler
    }//GEN-LAST:event_alquilertxtActionPerformed

    private void totaltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaltxtActionPerformed
        // funcion para poner el monto del total
    }//GEN-LAST:event_totaltxtActionPerformed

    private void alquilertxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alquilertxtKeyReleased
        calcularYActualizarTotal();
    }//GEN-LAST:event_alquilertxtKeyReleased

    private void totaltxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totaltxtKeyReleased
        calcularYActualizarTotal();
    }//GEN-LAST:event_totaltxtKeyReleased

    private void crearPisotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPisotxtActionPerformed
        CrearPiso piso = new CrearPiso();
        piso.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_crearPisotxtActionPerformed

    private void crearCuartobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearCuartobtnActionPerformed
        CrearCuarto cuarto = new CrearCuarto();
        cuarto.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_crearCuartobtnActionPerformed

    private void cuartostxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuartostxtActionPerformed

    }//GEN-LAST:event_cuartostxtActionPerformed

    private void tbTotalCalculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTotalCalculoMouseClicked
       CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
       dao.SeleccionarCalculoAlquiler(tbTotalCalculo,idtxt, dnitxt, search, alquilertxt,garantiatxt, pisostxt, cuartostxt,interesestxt,totaltxt,totalAlquilertxt,fechatxt,fechaingresotxt,mensualtxt, selectPago, pagoDiariotxt , pagoSemtxt, pagoQuincenaltxt,ructxt,direcciontxt,telefonotxt);

    }//GEN-LAST:event_tbTotalCalculoMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
       dao.ModificarCalculoAlquiler(tbTotalCalculo,idtxt, search, alquilertxt,garantiatxt, pisostxt, cuartostxt,interesestxt,totaltxt,totalAlquilertxt,fechatxt,fechaingresotxt,mensualtxt, selectPago, pagoDiariotxt , pagoSemtxt, pagoQuincenaltxt);
       dao.MostrarAlquiler(tbTotalCalculo);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void mensualtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensualtxtMouseClicked
        mostrarCalculos();
    }//GEN-LAST:event_mensualtxtMouseClicked

    private void mensualtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mensualtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mensualtxtActionPerformed

    private void pagoDiariotxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pagoDiariotxtMouseClicked

    }//GEN-LAST:event_pagoDiariotxtMouseClicked

    private void selectPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectPagoItemStateChanged
        String tiposPago = (String) selectPago.getSelectedItem();
        
        if(tiposPago.equals("Diario")) {
            mostrarImporteDiario();
        }
        if(tiposPago.equals("Semanal")) {
            mostrarImporteSemanal();
        }
        if(tiposPago.equals("Quincenal")) {
            mostrarImporteQuincenal();
        }
        if(tiposPago.equals("Mensual")) {
            mostrarImporteMensual();
        }
    }//GEN-LAST:event_selectPagoItemStateChanged

    private void direcciontxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_direcciontxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxtMouseClicked

    private void dnitxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dnitxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dnitxtMouseClicked

    private void telefonotxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_telefonotxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonotxtMouseClicked
    
    private void calcularYActualizarTotal() {
        // Obtener valores de los campos
        BigDecimal alquiler = new BigDecimal(alquilertxt.getText().trim());
        int total = Integer.parseInt(totaltxt.getText());

        // Calcular la multiplicación
        BigDecimal totalAlquiler = alquiler.multiply(BigDecimal.valueOf(total));

        // Actualizar el campo totalAlquilertxt
        totalAlquilertxt.setText(String.valueOf(totalAlquiler));
    }
    
     private void filterTable() {
    String searchText = txtSearch.getText().trim();
    CalcularAlquilerDAO dao = new CalcularAlquilerDAO();
    dao.FiltrarRentCalculation(tbTotalCalculo, searchText);
    }
     
private void exportarAExcel() {
    try {
        DefaultTableModel modelo = (DefaultTableModel) tbCalculoAlquiler.getModel();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Detalle de la renta");

        // Crear estilo de celda con bordes y tamaño de letra 12
        XSSFCellStyle estiloCelda = workbook.createCellStyle();
        estiloCelda.setBorderBottom(BorderStyle.THIN);
        estiloCelda.setBorderTop(BorderStyle.THIN);
        estiloCelda.setBorderRight(BorderStyle.THIN);
        estiloCelda.setBorderLeft(BorderStyle.THIN);

        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        estiloCelda.setFont(font);
        
        XSSFCellStyle estiloSinBordes = workbook.createCellStyle();
        
        XSSFCellStyle estiloProforma = workbook.createCellStyle();
        XSSFFont fontProforma = workbook.createFont();
        fontProforma.setFontHeightInPoints((short) 20);
        fontProforma.setBold(true);
        fontProforma.setUnderline(FontUnderline.SINGLE);
        estiloProforma.setFont(fontProforma);

        Row proformaRow = sheet.createRow(0);
        Cell proformaCellC = proformaRow.createCell(2);
        proformaCellC.setCellValue("PROFORMA");
        Cell proformaCellD = proformaRow.createCell(3);
        proformaCellD.setCellValue(""); 
        Cell proformaCellE = proformaRow.createCell(4);
        proformaCellE.setCellValue(""); 
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4)); // Combinar las celdas C1 a E1
        proformaCellC.setCellStyle(estiloProforma);

        
        Row fechaEmisionRow = sheet.createRow(1);
        Cell fechaEmisionLabelCell = fechaEmisionRow.createCell(0);
        fechaEmisionLabelCell.setCellValue("Fecha de emisión :");
        fechaEmisionLabelCell.setCellStyle(estiloSinBordes);


        Cell fechaEmisionContentCell = fechaEmisionRow.createCell(1);


        Date sqlDate = fechatxt.getDate();
        Date fecha = new Date(sqlDate.getTime());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = formatoFecha.format(fecha);


        fechaEmisionContentCell.setCellValue(fechaFormateada);
        fechaEmisionContentCell.setCellStyle(estiloSinBordes);
        

        Row searchRow = sheet.createRow(2);


        Cell searchLabelCell = searchRow.createCell(0);
        searchLabelCell.setCellValue("Nombre :");
        searchLabelCell.setCellStyle(estiloSinBordes); 
        

        Cell searchContentCell = searchRow.createCell(1);
        searchContentCell.setCellValue(search.getSelectedItem().toString()); 
        searchContentCell.setCellStyle(estiloSinBordes); 
        
        Cell rucLabelCell = searchRow.createCell(5); 
        rucLabelCell.setCellValue("RUC :");
        rucLabelCell.setCellStyle(estiloSinBordes); 

        Cell rucContentCell = searchRow.createCell(6); 
        rucContentCell.setCellValue(ructxt.getText()); 
        rucContentCell.setCellStyle(estiloSinBordes);
        
        Row direccionRow = sheet.createRow(3);
        // Etiqueta para "Inicial :"
        Cell direccionLabelCell = direccionRow.createCell(0);
        direccionLabelCell.setCellValue("Dirección :");
        direccionLabelCell.setCellStyle(estiloSinBordes);

        Cell direccionContentCell = direccionRow.createCell(1);
        direccionContentCell.setCellValue(direcciontxt.getText());
        direccionContentCell.setCellStyle(estiloSinBordes);
        
        // Etiqueta para el DNI
        Cell dniLabelCell = direccionRow.createCell(5); 
        dniLabelCell.setCellValue("DNI :");
        dniLabelCell.setCellStyle(estiloSinBordes); 

        Cell dniContentCell = direccionRow.createCell(6); 
        dniContentCell.setCellValue(dnitxt.getText()); 
        dniContentCell.setCellStyle(estiloSinBordes); 
        
        Row totalrentRow = sheet.createRow(4);
        Cell totalrentLabelCell = totalrentRow.createCell(0);
        totalrentLabelCell.setCellValue("Precio contado :");
        totalrentLabelCell.setCellStyle(estiloSinBordes);

        Cell totalrentContentCell = totalrentRow.createCell(1);
        totalrentContentCell.setCellValue(totalAlquilertxt.getText());
        totalrentContentCell.setCellStyle(estiloSinBordes);
         
         // Etiqueta para el teléfono
        Cell telefLabelCell = totalrentRow.createCell(5); 
        telefLabelCell.setCellValue("Teléf :");
        telefLabelCell.setCellStyle(estiloSinBordes);

        Cell telefContentCell = totalrentRow.createCell(6); 
        telefContentCell.setCellValue(telefonotxt.getText()); 
        telefContentCell.setCellStyle(estiloSinBordes); 
        

        Row inicialRow = sheet.createRow(5);
        Cell inicialLabelCell = inicialRow.createCell(0);
        inicialLabelCell.setCellValue("Inicial :");
        inicialLabelCell.setCellStyle(estiloSinBordes);

  
        Cell inicialContentCell = inicialRow.createCell(1);
        inicialContentCell.setCellValue(garantiatxt.getText());
        inicialContentCell.setCellStyle(estiloSinBordes); 
        
        Cell tpagoLabelCell = inicialRow.createCell(5);
        tpagoLabelCell.setCellValue("Tipo pago :");
        tpagoLabelCell.setCellStyle(estiloSinBordes);

        Cell tpagoContentCell = inicialRow.createCell(6);

        Object selectedValue = selectPago.getSelectedItem();

        if (selectedValue != null) {
            tpagoContentCell.setCellValue(selectedValue.toString());
        }

        tpagoContentCell.setCellStyle(estiloSinBordes);
        
        // Crear fila de encabezado
        Row headerRow = sheet.createRow(8); // Incrementar el índice para la nueva fila 
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(modelo.getColumnName(i));
            cell.setCellStyle(estiloCelda); // Aplicar estilo de borde y tamaño de letra al encabezado
        }

        // Llenar la hoja de cálculo con los datos y aplicar estilo
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Row row = sheet.createRow(i + 9); // Iniciar desde la quinta fila para dejar espacio para el JComboBox y las etiquetas
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(modelo.getValueAt(i, j).toString());
                cell.setCellStyle(estiloCelda); // Aplicar estilo de borde y tamaño de letra a las celdas de datos
            }
        }

        // Crear fila adicional para los totales
        int numRows = modelo.getRowCount();
        Row totalRow = sheet.createRow(numRows + 9); // Ajustar el índice para dejar espacio para el JComboBox y las etiquetas

        // Espacio en blanco en las tres primeras columnas
        for (int i = 0; i < 8; i++) {
            totalRow.createCell(i);
        }

        // Totales
        Cell totalCell = totalRow.createCell(2);
        totalCell.setCellValue("Totales");
        totalCell.setCellStyle(estiloCelda);

        Cell sumCapitalValueCell = totalRow.createCell(3);
        sumCapitalValueCell.setCellValue(txtSumCapital.getText());
        sumCapitalValueCell.setCellStyle(estiloCelda);

        Cell sumInteresValueCell = totalRow.createCell(4);
        sumInteresValueCell.setCellValue(txtSumInteres.getText());
        sumInteresValueCell.setCellStyle(estiloCelda);

        Cell sumMensualValueCell = totalRow.createCell(5);
        sumMensualValueCell.setCellValue(txtSumMensual.getText());
        sumMensualValueCell.setCellStyle(estiloCelda);
        
        Row diasPorMesRow = sheet.createRow(numRows + 11); 
        Cell diasPorMesCellLabel = diasPorMesRow.createCell(1);
        diasPorMesCellLabel.setCellValue("Días por mes :");
        diasPorMesCellLabel.setCellStyle(estiloSinBordes);

        Cell diasPorMesCellContent = diasPorMesRow.createCell(2);
        diasPorMesCellContent.setCellValue(pagoDiariotxt.getText()); 
        diasPorMesCellContent.setCellStyle(estiloSinBordes);
        
        Row quincenalRow = sheet.createRow(numRows + 12); 
        Cell quincenalCellLabel = quincenalRow.createCell(1);
        quincenalCellLabel.setCellValue("Quincenal :");
        quincenalCellLabel.setCellStyle(estiloSinBordes);

        Cell quincenalCellContent = quincenalRow.createCell(2);
        quincenalCellContent.setCellValue(pagoQuincenaltxt.getText()); 
        quincenalCellContent.setCellStyle(estiloSinBordes);
        
        Row cuotasMensualesRow = sheet.createRow(numRows + 13); 
        Cell cuotasMensualesCellLabel = cuotasMensualesRow.createCell(1);
        cuotasMensualesCellLabel.setCellValue("Cuotas mensuales :");
        cuotasMensualesCellLabel.setCellStyle(estiloSinBordes);

        Cell cuotasMensualesCellContent = cuotasMensualesRow.createCell(2);
        cuotasMensualesCellContent.setCellValue(mensualtxt.getText()); 
        cuotasMensualesCellContent.setCellStyle(estiloSinBordes);
        
        Row semanalRow = sheet.createRow(numRows + 14); 
        Cell semanalCellLabel = semanalRow.createCell(1);
        semanalCellLabel.setCellValue("Semanal :");
        semanalCellLabel.setCellStyle(estiloSinBordes);

        Cell semanalCellContent = semanalRow.createCell(2);
        semanalCellContent.setCellValue(pagoSemtxt.getText()); 
        semanalCellContent.setCellStyle(estiloSinBordes);
        
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar el libro en un archivo
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        try (FileOutputStream fileOut = new FileOutputStream(desktopPath + "detalle.xlsx")) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Datos exportados correctamente a Excel.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar a Excel: " + e.toString());
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.toString());
    }
}

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CalculoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculoAlquiler().setVisible(true);
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Grabarbtn;
    private javax.swing.JPanel Separator;
    private javax.swing.JTextField alquilertxt;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton crearCuartobtn;
    private javax.swing.JButton crearPisotxt;
    private javax.swing.JComboBox<String> cuartostxt;
    private javax.swing.JTextField direcciontxt;
    private javax.swing.JTextField dnitxt;
    private com.toedter.calendar.JDateChooser fechaingresotxt;
    private com.toedter.calendar.JDateChooser fechatxt;
    private javax.swing.JTextField garantiatxt;
    private javax.swing.JTextField idtxt;
    private javax.swing.JTextField interesestxt;
    private javax.swing.JComboBox<String> jComboBox3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mensualtxt;
    private javax.swing.JTextField pagoDiariotxt;
    private javax.swing.JTextField pagoQuincenaltxt;
    private javax.swing.JTextField pagoSemtxt;
    private javax.swing.JComboBox<String> pisostxt;
    private javax.swing.JButton registroClientebtn;
    private javax.swing.JTextField ructxt;
    private javax.swing.JComboBox<String> search;
    private javax.swing.JComboBox<String> selectPago;
    private javax.swing.JTable tbCalculoAlquiler;
    private javax.swing.JTable tbCalculoImporte;
    private javax.swing.JTable tbTotalCalculo;
    private javax.swing.JTextField telefonotxt;
    private javax.swing.JTextField totalAlquilertxt;
    private javax.swing.JTextField totaltxt;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSumCapital;
    private javax.swing.JTextField txtSumImporte;
    private javax.swing.JTextField txtSumInteres;
    private javax.swing.JTextField txtSumMensual;
    // End of variables declaration//GEN-END:variables

    private void Limpiar() {
        ructxt.setText("");
        alquilertxt.setText("");
        dnitxt.setText("");
        garantiatxt.setText("");
        pisostxt.setSelectedIndex(-1);
        fechatxt.setDate(null); 
        fechaingresotxt.setDate(null); 
        totalAlquilertxt.setText("");
        totaltxt.setText("");
        interesestxt.setText("");
        mensualtxt.setText("");
        pagoDiariotxt.setText("");
        pagoSemtxt.setText("");
        pagoQuincenaltxt.setText("");
        txtSumCapital.setText("");
        txtSumInteres.setText("");
        txtSumMensual.setText("");
        txtSumImporte.setText("");
        telefonotxt.setText("");
        direcciontxt.setText("");
        
        // Limpiar la tabla
        DefaultTableModel model = new DefaultTableModel();
        tbCalculoAlquiler.setModel(model);
        tbCalculoImporte.setModel(model);
    }
}
