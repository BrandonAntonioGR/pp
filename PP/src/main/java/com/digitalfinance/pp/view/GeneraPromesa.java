/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.digitalfinance.pp.view;

import com.digitalfinance.pp.DAO.entity.Macro;
import com.digitalfinance.pp.DAO.entity.Plantilla;
import com.digitalfinance.pp.DAO.entity.PromesaPago;
import com.digitalfinance.pp.DAO.model.ConsultarPlantilas;
import com.digitalfinance.pp.controller.ControllerCreaPromesaPago;
import com.digitalfinance.pp.controller.ControllerInsertarPromesaPago;
import com.opencsv.exceptions.CsvValidationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class GeneraPromesa extends javax.swing.JFrame {

    ControllerInsertarPromesaPago cargarPromesaPago = new ControllerInsertarPromesaPago();
    ControllerCreaPromesaPago consultaPromesa = new ControllerCreaPromesaPago();
    ConsultarPlantilas consultarPlantillas = new ConsultarPlantilas();
    boolean contratoEncontrado = false;
    boolean fecharango = false;
    int adeudo = 0;
    int saldoHoy = 0;
    int ext10 = 0;
    int ext20 = 0;
    int ext30 = 0;
    String agnt;
    ArrayList<Plantilla> lstPlantillas = new ArrayList<>();
    String referenciaOxxo;
    String referenciaSpei;
    Date fechaPago;

    String contrato;
    String nombre;
    String dpd;
    String algoritmo;

    public GeneraPromesa(String agente) {
        initComponents();
        txtComentarios.setWrapStyleWord(true);
        this.agnt = agente;
        try {
            ArrayList<Plantilla> listaPlantillas = consultarPlantillas.leerCSV();
            lstPlantillas = listaPlantillas;
            //cmbSelec.addItem("---Seleccione la plantilla---");
            for (int i = 0; i < lstPlantillas.size(); i++) {
                Plantilla plantilla = new Plantilla();
                plantilla = lstPlantillas.get(i);
                cmbCargarPlantilla.addItem(plantilla.getNombrePlantilla());
            }
        } catch (CsvValidationException ex) {
            Logger.getLogger(GeneraPromesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelDerecha = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cmbTipoMonto = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbCargarPlantilla = new javax.swing.JComboBox<>();
        cmbTipoPromesa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        panelIzquierda = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbContacto = new javax.swing.JComboBox<>();
        cmbCanal = new javax.swing.JComboBox<>();
        cmbMedioContacto = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFechaPromesa = new com.toedter.calendar.JDateChooser();
        panelAbajo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        btnGuardarPromesa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        panelBuscaContrato = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtContrato = new javax.swing.JTextField();
        btnBuscarContrato = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel7.setText("T ipo Monto:");

        cmbTipoMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoMontoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel9.setText("Monto:");

        txtMonto.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel11.setText("Cargar plantilla:");

        cmbCargarPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCargarPlantillaActionPerformed(evt);
            }
        });

        cmbTipoPromesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAGO TOTAL", "PAGO DESCUENTO", "EXTENSION" }));
        cmbTipoPromesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoPromesaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel2.setText("Tipo promesa:");

        javax.swing.GroupLayout panelDerechaLayout = new javax.swing.GroupLayout(panelDerecha);
        panelDerecha.setLayout(panelDerechaLayout);
        panelDerechaLayout.setHorizontalGroup(
            panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDerechaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoPromesa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelDerechaLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbCargarPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDerechaLayout.createSequentialGroup()
                            .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelDerechaLayout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDerechaLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbTipoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelDerechaLayout.setVerticalGroup(
            panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoPromesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbTipoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbCargarPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel6.setText("Contacto:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel5.setText("Canal:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel8.setText("Medio de contacto:");

        cmbContacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONTACTO TITULAR", "CONTACTO TERCERO", "CONTACTO REP. LEGAL" }));

        cmbCanal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CELULAR", "CASA", "TRABAJO" }));

        cmbMedioContacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MITROL", "MSGBIRD", "MACRO", "SMS", "CORREO ELECTRONICO" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel4.setText("Fecha  de promesa:");

        txtFechaPromesa.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout panelIzquierdaLayout = new javax.swing.GroupLayout(panelIzquierda);
        panelIzquierda.setLayout(panelIzquierdaLayout);
        panelIzquierdaLayout.setHorizontalGroup(
            panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdaLayout.createSequentialGroup()
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel4))
                .addGap(38, 38, 38)
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMedioContacto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbContacto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCanal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFechaPromesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelIzquierdaLayout.setVerticalGroup(
            panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbMedioContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbCanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaPromesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel1.setText("Comentarios:");

        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        jScrollPane1.setViewportView(txtComentarios);

        btnGuardarPromesa.setBackground(new java.awt.Color(0, 109, 56));
        btnGuardarPromesa.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnGuardarPromesa.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarPromesa.setText("Guardar");
        btnGuardarPromesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPromesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAbajoLayout = new javax.swing.GroupLayout(panelAbajo);
        panelAbajo.setLayout(panelAbajoLayout);
        panelAbajoLayout.setHorizontalGroup(
            panelAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAbajoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelAbajoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarPromesa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAbajoLayout.setVerticalGroup(
            panelAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAbajoLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(panelAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAbajoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAbajoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnGuardarPromesa)))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(panelIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(panelDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAbajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 109, 56));

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Generación de promesas de pago ");

        btnRegresar.setBackground(new java.awt.Color(0, 109, 56));
        btnRegresar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jLabel10.setText("Ingresa contrato:");

        btnBuscarContrato.setBackground(new java.awt.Color(0, 109, 56));
        btnBuscarContrato.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnBuscarContrato.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarContrato.setText("Buscar");
        btnBuscarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscaContratoLayout = new javax.swing.GroupLayout(panelBuscaContrato);
        panelBuscaContrato.setLayout(panelBuscaContratoLayout);
        panelBuscaContratoLayout.setHorizontalGroup(
            panelBuscaContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaContratoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarContrato)
                .addGap(175, 175, 175))
        );
        panelBuscaContratoLayout.setVerticalGroup(
            panelBuscaContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscaContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarContrato))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBuscaContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBuscaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarPromesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPromesaActionPerformed
        Date fechaSeleccionada = txtFechaPromesa.getDate();
        if (fechaSeleccionada != null) {
            Date fechaPromesa = fechaPromesa(fechaSeleccionada);
            if (fecharango == true) {
                if (!txtMonto.getText().isEmpty() && !txtMonto.getText().equals("0")) {
                    if (!txtComentarios.getText().isEmpty()) {
                        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea continuar?", "Se guardara la promesa", JOptionPane.YES_NO_OPTION);
                        if (respuesta == JOptionPane.YES_OPTION) {
                            //fecha actual
                            String fechaActual = obtenerFechaActual();
                            //fecha de la promesa
                            String promiseDateS = obtenerFechaFormateada(fechaPromesa);
                            //calculo del promiselimitdate
                            Calendar calendario = Calendar.getInstance();
                            calendario.setTime(fechaPromesa);
                            calendario.add(Calendar.DAY_OF_YEAR, 1);
                            Date promiseLimitDate = calendario.getTime();
                            String promiseLimitDateS = obtenerFechaFormateada(promiseLimitDate);
                            //monto promesado
                            double montoPromesado = Double.parseDouble(txtMonto.getText());
                            //contacto
                            String contacto = cmbContacto.getSelectedItem().toString();
                            //medio de contacto
                            String medioContacto = cmbMedioContacto.getSelectedItem().toString();
                            //canal
                            String canal = cmbCanal.getSelectedItem().toString();
                            //tipo de promesa
                            String tipoPromesa = cmbTipoPromesa.getSelectedItem().toString();
                            //tipo de monto
                            String tipoMonto = cmbTipoMonto.getSelectedItem().toString();
                            //comentarios
                            String comentarios = txtComentarios.getText();

                            PromesaPago pp = new PromesaPago();
                            pp.setContract(contrato);
                            pp.setDpd(dpd);
                            pp.setAlgorithm(algoritmo);
                            pp.setContactDate(fechaActual);
                            pp.setPromiseDate(promiseDateS);
                            pp.setPromiseLimitDate(promiseLimitDateS);
                            pp.setPromiseAmount(montoPromesado);
                            pp.setAgente(agnt);
                            pp.setContact(contacto);
                            pp.setContactChannel(medioContacto);
                            pp.setChannel(canal);
                            pp.setPromiseType(tipoPromesa);
                            pp.setAmountType(tipoMonto);
                            pp.setComentarios(comentarios);
                            cargarPromesaPago.CargarPromesaPago(pp);
                            limpiarDatos();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El campo comentarios debe ser ingresado para continuar", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El campo Tipo de monto debe ser seleccionado", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Solo es posible ingresar fechas en el rango de hoy a 5 dias posteriores", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una fecha valida formato:dd/MM/yyyy", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarPromesaActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarContratoActionPerformed
        String obtenercontrato = txtContrato.getText();
        Macro macro = consultaPromesa.consultaCreaPromesaPago(obtenercontrato);
        String adeudos = macro.getAdeudo();
        String saldoHoys = macro.getSaldoHoy();
        String ext20s = macro.getExtension20();
        String ext30s = macro.getExtension30();
        String openprincipal = macro.getOpen_principal();
        if (adeudos != null && saldoHoys != null && openprincipal != null && ext20s != null && ext30s != null) {
            double capitalDouble = Double.parseDouble(openprincipal);
            double valorminimoExt = capitalDouble * 0.075;
            double aux;
            if (valorminimoExt < 150) {
                aux = valorminimoExt;
            } else {
                aux = 150;
            }
            double ext10double = (10 * 0.018 * capitalDouble * 1.16) + aux;
            int extencion10 = (int) Math.ceil(ext10double);
            ext10 = extencion10;

            double adeudodouble = Double.parseDouble(adeudos);
            int adeudoint = (int) Math.ceil(adeudodouble);
            adeudo = adeudoint;

            double saldoHoydouble = Double.parseDouble(saldoHoys);
            int saldohoyint = (int) Math.ceil(saldoHoydouble);
            saldoHoy = saldohoyint;

            double ext20double = Double.parseDouble(ext20s);
            int ext20int = (int) Math.ceil(ext20double);
            ext20 = ext20int;

            double ext30double = Double.parseDouble(ext30s);
            int ext30int = (int) Math.ceil(ext30double);
            ext30 = ext30int;

            //bandera para saber si encontro el contrato
            contratoEncontrado = true;

            //seteo de datos extra
            dpd = macro.getDpd();
            referenciaOxxo = macro.getOxxoReference();
            referenciaSpei = macro.getSpeiClave();
            fechaPago = macro.getFechaPago();
            contrato = macro.getNumber();
            nombre = macro.getNombre();
            algoritmo = macro.getAlgoritmo();
        } else {
            JOptionPane.showMessageDialog(null, "Contrato ya promesado", "Advertencia", JOptionPane.ERROR_MESSAGE);
            contratoEncontrado = false;
        }
    }//GEN-LAST:event_btnBuscarContratoActionPerformed

    private void cmbTipoMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMontoActionPerformed
        String opcTipoMonto = (String) cmbTipoMonto.getSelectedItem();
        if (opcTipoMonto != null) {
            switch (opcTipoMonto) {
                case "SALDO TOTAL":
                    //SALDO TOTAL
                    txtMonto.setText(adeudo + "");
                    break;
                case "SALDO HOY":
                    //SALDO HOY
                    txtMonto.setText(saldoHoy + "");
                    break;
                case "EXT 10":
                    //EXT 10
                    txtMonto.setText(ext10 + "");
                    break;
                case "EXT 20":
                    //EXT 20
                    txtMonto.setText(ext20 + "");
                    break;
                case "EXT 30":
                    //EXT 30
                    txtMonto.setText(ext30 + "");
                    break;
            }
        }
    }//GEN-LAST:event_cmbTipoMontoActionPerformed

    private void cmbCargarPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCargarPlantillaActionPerformed
        if (!txtContrato.getText().isEmpty()) {
            try {
                if (!txtContrato.getText().isEmpty()) {
                    ArrayList<Plantilla> listaPlantillas = consultarPlantillas.leerCSV();
                    int opc = cmbCargarPlantilla.getSelectedIndex();
                    Plantilla pl = listaPlantillas.get(opc);
                    String descPlantilla = pl.getDescPlantilla();

                    String patronNombreVariable = "/NOMBRE_VARIABLE/";
                    String NombreCteStr = nombre;
                    String descPlantilla1 = modificarCoincidencias(patronNombreVariable, descPlantilla, NombreCteStr);
                    //obteniendo credito del clinete
                    String patronCreditoVariable = "/CREDITO_VARIABLE/";
                    String contratoStr = contrato;
                    String descPlantilla2 = modificarCoincidencias(patronCreditoVariable, descPlantilla1, contratoStr);
                    //obteniendo fecha de pago del clinete
                    String patronFechaPagoVariable = "/FECHA_PAGO/";
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                    String oFechPago = formatoFecha.format(fechaPago);
                    String descPlantilla3 = modificarCoincidencias(patronFechaPagoVariable, descPlantilla2, oFechPago);
                    //obteniendo nombre del clinete
                    String patronAdeudoVariable = "/ADEUDO_VARIABLE/";
                    String AdeudoVariableStr = adeudo + "";
                    String descPlantilla4 = modificarCoincidencias(patronAdeudoVariable, descPlantilla3, AdeudoVariableStr);

                    //obteniendo saldo del clinete
                    String patronSaldoVariable = "/SALDO_VARIABLE/";
                    String SaldoVariableStr = saldoHoy + "";
                    String descPlantilla5 = modificarCoincidencias(patronSaldoVariable, descPlantilla4, SaldoVariableStr);

                    //obteniendo ext20 del clinete
                    String patronExt20NombreVariable = "/EXT_20/";
                    String ext20Str = ext20 + "";
                    String descPlantilla6 = modificarCoincidencias(patronExt20NombreVariable, descPlantilla5, ext20Str);

                    //obteniendo ext20 del clinete
                    String patronExt20Variable = "/EXT_30/";
                    String ext30Str = ext30 + "";
                    String descPlantilla7 = modificarCoincidencias(patronExt20Variable, descPlantilla6, ext30Str);

                    String patronSpeiVariable = "/REF_SPEI/";
                    String oSpei = referenciaSpei;
                    String descPlantilla8 = modificarCoincidencias(patronSpeiVariable, descPlantilla7, oSpei);

                    String patronOxxoVariable = "/REF_OXXO/";
                    String oOxxo = referenciaOxxo;
                    String descPlantilla9 = modificarCoincidencias(patronOxxoVariable, descPlantilla8, oOxxo);

                    txtComentarios.setText(descPlantilla9);
                }

            } catch (CsvValidationException ex) {
                Logger.getLogger(GeneraPromesa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmbCargarPlantillaActionPerformed

    private void cmbTipoPromesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoPromesaActionPerformed
        String opSeleccionTipoPromesa = cmbTipoPromesa.getSelectedItem().toString();
        switch (opSeleccionTipoPromesa) {
            case "PAGO TOTAL":
                cmbTipoMonto.removeAllItems();
                cmbTipoMonto.addItem("SALDO TOTAL");
                break;
            case "PAGO DESCUENTO":
                cmbTipoMonto.removeAllItems();
                cmbTipoMonto.addItem("SALDO HOY");
                break;
            case "EXTENSION":
                cmbTipoMonto.removeAllItems();
                cmbTipoMonto.addItem("EXT 10");
                cmbTipoMonto.addItem("EXT 20");
                cmbTipoMonto.addItem("EXT 30");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cmbTipoPromesaActionPerformed
    public String modificarCoincidencias(String patronNombreVariable, String descPlantilla, String OBJETONOMBRE) {
        Pattern patternNombreVariable = Pattern.compile(patronNombreVariable);
        Matcher similitudNombreVariable = patternNombreVariable.matcher(descPlantilla);
        String textoNuevo1 = similitudNombreVariable.replaceAll(OBJETONOMBRE);
        return textoNuevo1;
    }

    public Date fechaPromesa(Date fechaIngresada) {
        // Obtener la fecha de ayer
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date fechaAyer = calendar.getTime();

        // Obtener la fecha de hace 5 días
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        Date fechaHaceCincoDias = calendar.getTime();

        // Comparar las fechas
        if (fechaIngresada.after(fechaAyer) && fechaIngresada.before(fechaHaceCincoDias)) {
            System.out.println("okey");
            fecharango = true;
            return fechaIngresada;
        } else {
            fecharango = false;
            System.out.println("no cumple");
            return null;
        }
    }

    public String obtenerFechaActual() {
        // Obteniendo la fecha y hora actual utilizando Calendar
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        // Convirtiendo la fecha y hora actual a un objeto Timestamp
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        // Creando un objeto Date a partir del objeto Timestamp
        Date currentDateSQL = new Date(currentTimestamp.getTime());

        // Formateando la fecha y hora actual
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDateSQL);

        return formattedDateTime;
    }

    public String obtenerFechaFormateada(Date fecha) {
        // Convirtiendo la fecha y hora actual a un objeto Timestamp
        Timestamp currentTimestamp = new Timestamp(fecha.getTime());

        // Creando un objeto Date a partir del objeto Timestamp
        Date currentDateSQL = new Date(currentTimestamp.getTime());

        // Formateando la fecha y hora actual
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDateSQL);

        return formattedDateTime;
    }

    public void limpiarDatos() {
        contratoEncontrado = false;
        fecharango = false;
        adeudo = 0;
        saldoHoy = 0;
        ext10 = 0;
        ext20 = 0;
        ext30 = 0;
        referenciaOxxo = "";
        referenciaSpei = "";
//        fechaPago = null;
        contrato = "";
        nombre = "";
        dpd = "";
        algoritmo = "";
        cmbContacto.setSelectedIndex(0);
        cmbMedioContacto.setSelectedIndex(0);
        cmbCanal.setSelectedIndex(0);
        txtFechaPromesa.setDate(null);
        cmbTipoPromesa.setSelectedIndex(0);
        cmbTipoMonto.removeAllItems();
        txtMonto.setText("");
        cmbCargarPlantilla.setSelectedIndex(0);
        txtComentarios.setText("");
        txtContrato.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarContrato;
    private javax.swing.JButton btnGuardarPromesa;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbCanal;
    private javax.swing.JComboBox<String> cmbCargarPlantilla;
    private javax.swing.JComboBox<String> cmbContacto;
    private javax.swing.JComboBox<String> cmbMedioContacto;
    private javax.swing.JComboBox<String> cmbTipoMonto;
    private javax.swing.JComboBox<String> cmbTipoPromesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAbajo;
    private javax.swing.JPanel panelBuscaContrato;
    private javax.swing.JPanel panelDerecha;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextArea txtComentarios;
    private javax.swing.JTextField txtContrato;
    private com.toedter.calendar.JDateChooser txtFechaPromesa;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
/*
1 050 832 850/herasc/administrador
Equipo1.
HERASC\Administrador
Contraseña: X6cgtPdld75z14U8v2Ra
 */
