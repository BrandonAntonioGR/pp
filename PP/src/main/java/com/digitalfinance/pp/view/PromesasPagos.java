/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.digitalfinance.pp.view;

import com.digitalfinance.DAO.entity.Algoritmo;
import com.digitalfinance.DAO.entity.Dpd;
import com.digitalfinance.DAO.entity.GeneraExcelMacro;
import com.digitalfinance.DAO.entity.GenerarExcelPromesas;
import com.digitalfinance.DAO.entity.Macro;
import com.digitalfinance.DAO.entity.REF_GPT1;
import com.digitalfinance.DAO.entity.Plantilla;
import com.digitalfinance.DAO.entity.Promesa;
import com.digitalfinance.DAO.entity.lista_REFGPT1;
import com.digitalfinance.DAO.model.ConsultarPlantilas;
import com.digitalfinance.controller.ControllerCCCone;
import com.digitalfinance.controller.ControllerCargadosWhatsApp;
import com.digitalfinance.controller.ControllerInsertaMsgWhatsApp;
import com.digitalfinance.controller.ControllerConsultaREFGPT1;
import com.digitalfinance.controller.ControllerInsertarCarga;
import com.digitalfinance.controller.ControllerMacro;
import com.digitalfinance.controller.ControllerPantallas;
import com.digitalfinance.controller.ControllerPostNewRecord;
import com.digitalfinance.controller.ControllerPromesados;
import com.digitalfinance.plantilla.crearexcelmasivo;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Brandon Garduño
 */
public class PromesasPagos extends javax.swing.JFrame implements ClipboardOwner {

    ControllerConsultaREFGPT1 consultaREFGPT1 = new ControllerConsultaREFGPT1();
    ControllerPantallas cp = new ControllerPantallas();
    ControllerMacro cm = new ControllerMacro();
    ControllerCCCone cc = new ControllerCCCone();
    ControllerInsertarCarga cargarCCCone = new ControllerInsertarCarga();
    ControllerPromesados cPromesados = new ControllerPromesados();
    ControllerInsertaMsgWhatsApp cWhatsapp = new ControllerInsertaMsgWhatsApp();
    ControllerCargadosWhatsApp CWhatsappLst = new ControllerCargadosWhatsApp();
    ConsultarPlantilas consultarPlantillas = new ConsultarPlantilas();
    GenerarExcelPromesas gExcel = new GenerarExcelPromesas();
    ArrayList<Macro> lstMacro = new ArrayList<>();
    lista_REFGPT1 listaContrato = new lista_REFGPT1();
    Color botonSeleccionado = new Color(84, 88, 47);
    Color botonNoSeleccionado = new Color(39, 43, 0);
    Color letraBlanca = new Color(255, 255, 255);
    Color letraNegra = new Color(255, 255, 255);
    String agente;
    DefaultTableModel modeloMacro = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel modeloCargados = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel modeloPromesados = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel modeloWhatsApp = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public PromesasPagos(String agnt) throws SQLException {
        setIconImage(new ImageIcon(getClass().getResource("/LogoCobranzaExpress.png")).getImage());
        initComponents();
        cmbOpcCopiar.setBackground(botonNoSeleccionado);
        cmbOpcCopiar.setForeground(letraBlanca);
        ImageIcon iconCCCone = new ImageIcon(getClass().getResource("/LogoCCC.png"));
        ImageIcon iconTec = new ImageIcon(getClass().getResource("/LogoTEC.png"));
        ImageIcon iconWhatsapp = new ImageIcon(getClass().getResource("/LogoWhatsApp.png"));
        ImageIcon iconCopiar = new ImageIcon(getClass().getResource("/LogoCopiar.png"));
        ImageIcon iconPromesaPago = new ImageIcon(getClass().getResource("/promesaPago.png"));
        //ImageIcon iconvivus = new ImageIcon(getClass().getResource("/LogoVivus.png"));
        btnCargaCccOne.setIcon(iconCCCone);
        btnHipervinculoTec.setIcon(iconTec);
        btnWhatsapp.setIcon(iconWhatsapp);
        btnCopiarPlantilla.setIcon(iconCopiar);
        btnGeneraPromesa.setIcon(iconPromesaPago);

        this.agente = agnt;
        btnCargaCccOne.setEnabled(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtCliente.setLineWrap(true);
        txtCliente.setWrapStyleWord(true);
        try {
            ArrayList<Plantilla> listaPlantillas = consultarPlantillas.leerCSV();
            //cmbSelec.addItem("---Seleccione la plantilla---");
            for (int i = 0; i < listaPlantillas.size(); i++) {
                Plantilla plantilla = new Plantilla();
                plantilla = listaPlantillas.get(i);
                cmbSelec.addItem(plantilla.getNombrePlantilla());
            }
        } catch (CsvValidationException ex) {
            Logger.getLogger(PromesasPagos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //llena el combobox de dpd de ambas tablas 
        llenarOpcFiltroDpdMacro(agente);
        //codigo donde se obtiene el valor del los combobox de dpd y algorimo de la Macro
        int selectedIndexDpd = cmbDpd.getSelectedIndex();
        String itemSeleccionadoDpd = cmbDpd.getItemAt(selectedIndexDpd);
        //llena el combobox de algoritmo con base a la cargados del agente
        llenarOpcFiltroAlgoritmoMacro(agente);
        //se obtinee el dato seleccionado en el combo
        int selectedIndexAlgoritmo = cmbAlgoritmo.getSelectedIndex();
        String itemSeleccionadoAlgoritmo = cmbAlgoritmo.getItemAt(selectedIndexAlgoritmo);
        //llenado de la tabla con base a dpd agente y algoritmo
        //llenarTablaAscMacro(agente, itemSeleccionadoDpd, itemSeleccionadoAlgoritmo);
        //se le añaden estilos a ambas tablas 
        //estilosTablaMacro();

        //llenado de la tabla de Cargado a CCCone 
        llenarTablaAscCargadosCCCone(agente);
        //se le añaden estilos a ambas tablas 
        estilosTablaCargadosCCCone();

        //llenado de tabla de promesados
        int selectedIndexPromesados = cmbDiasPromesa.getSelectedIndex();
        String itemSeleccionadoPromesados = cmbDiasPromesa.getItemAt(selectedIndexPromesados);
        llenarTablaPromesados(agente, itemSeleccionadoPromesados);
        //se le añaden estilos a la tabla
        estilosTablaPromesados();

        llenarTablaEnvWhatsApp();
        estilosTablaWhats();

        btnAscendente.setBackground(botonSeleccionado);
        btnAscendente.setForeground(letraBlanca);

        tblMacro.getTableHeader().setReorderingAllowed(false);
        tblCargadosCCC.getTableHeader().setReorderingAllowed(false);
        tblMsgWhatsApp.getTableHeader().setReorderingAllowed(false);
        tblPromesadas.getTableHeader().setReorderingAllowed(false);
    }

    public void estilosTablaMacro() {
        tblMacro.getColumnModel().getColumn(0).setMaxWidth(0);
        tblMacro.getColumnModel().getColumn(0).setMinWidth(0);
        tblMacro.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblMacro.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblMacro.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblMacro.getTableHeader().setOpaque(false);
        tblMacro.getTableHeader().setBackground(new Color(102, 0, 102));
        tblMacro.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    public void estilosTablaWhats() {
        tblMsgWhatsApp.getColumnModel().getColumn(0).setMaxWidth(0);
        tblMsgWhatsApp.getColumnModel().getColumn(0).setMinWidth(0);
        tblMsgWhatsApp.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblMsgWhatsApp.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblMsgWhatsApp.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblMsgWhatsApp.getTableHeader().setOpaque(false);
        tblMsgWhatsApp.getTableHeader().setBackground(new Color(102, 0, 102));
        tblMsgWhatsApp.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    public void estilosTablaCargadosCCCone() {
        tblCargadosCCC.getColumnModel().getColumn(0).setMaxWidth(0);
        tblCargadosCCC.getColumnModel().getColumn(0).setMinWidth(0);
        tblCargadosCCC.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblCargadosCCC.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblCargadosCCC.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblCargadosCCC.getTableHeader().setOpaque(false);
        tblCargadosCCC.getTableHeader().setBackground(new Color(102, 0, 102));
        tblCargadosCCC.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    public void estilosTablaPromesados() {
        tblPromesadas.getColumnModel().getColumn(5).setMaxWidth(0);
        tblPromesadas.getColumnModel().getColumn(5).setMinWidth(0);
        tblPromesadas.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tblPromesadas.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        tblPromesadas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPromesadas.getTableHeader().setOpaque(false);
        tblPromesadas.getTableHeader().setBackground(new Color(102, 0, 102));
        tblPromesadas.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    public void limpiarTabla(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(0); // Eliminar todas las columnas
        model.setRowCount(0);    // Eliminar todas las filas
    }

    public void llenarOpcFiltroDpdMacro(String agente) {
        ArrayList<Dpd> listaDpds = cm.consultaDpdComboBox(agente);
        //cmbSelec.addItem("---Seleccione la plantilla---");
        for (int i = 0; i < listaDpds.size(); i++) {
            Dpd dpd = new Dpd();
            dpd = listaDpds.get(i);
            cmbDpd.addItem(dpd.getDpd());
        }
    }

    public void llenarOpcFiltroAlgoritmoMacro(String agente) {
        ArrayList<Algoritmo> listaAlgoritmos = cm.consultaAlgoritmoComboBox(agente);
        //cmbSelec.addItem("---Seleccione la plantilla---");
        for (int i = 0; i < listaAlgoritmos.size(); i++) {
            Algoritmo algoritmo = new Algoritmo();
            algoritmo = listaAlgoritmos.get(i);
            cmbAlgoritmo.addItem(algoritmo.getAlgoritmo());
        }
    }

    // Renderizador personalizado para mostrar hipervínculos en una celda
//    class HyperlinkRenderer extends DefaultTableCellRenderer {
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            final JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//            // Establecemos el texto como un enlace HTML
//            label.setText("<html><a href=\"" + value + "\">" + value + "</a></html>");
//
//            // Abrir el enlace en el navegador al hacer clic
//            label.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    try {
//                        Desktop.getDesktop().browse(new URI(value.toString()));
//                    } catch (URISyntaxException | IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            });
//
//            return label;
//        }
//    }
    public void llenarTablaAscMacro(String agente, String dpd, String algoritmo) {
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("AGENTE");
        nombreColumnas.add("TELEFONO");
        nombreColumnas.add("CONTRATO");
        nombreColumnas.add("DPD");
        nombreColumnas.add("ADEUDO");
        nombreColumnas.add("NOMBRE");
        nombreColumnas.add("CORREO");
        nombreColumnas.add("SPEI");
        nombreColumnas.add("OXXO");
        nombreColumnas.add("ALGORITMO");
        nombreColumnas.add("TIPO");
        nombreColumnas.add("WHATSAPP");
        nombreColumnas.add("MITROL");
        for (Object columnas : nombreColumnas) {
            modeloMacro.addColumn(columnas);
        }
        this.tblMacro.setModel(modeloMacro);
        ArrayList<Macro> listaMacro = cm.consultaMacroAsc(agente, dpd, algoritmo);
        lstMacro.removeAll(lstMacro);
        lstMacro.addAll(listaMacro);
        String lstMacro = listaMacro.size() + "";
        txtRegistrosTotales.setText(lstMacro);
        modeloMacro = (DefaultTableModel) tblMacro.getModel();
        Object[] ob = new Object[13];
        for (int i = 0; i < listaMacro.size(); i++) {
            Macro macro = listaMacro.get(i);
            ob[0] = macro.getAgente();
            ob[1] = macro.getTel();
            ob[2] = macro.getNumber();
            ob[3] = macro.getDpd();
            ob[4] = macro.getAdeudo();
            ob[5] = macro.getNombre();
            ob[6] = macro.getCorreo();
            ob[7] = macro.getSpeiClave();
            ob[8] = macro.getOxxoReference();
            ob[9] = macro.getAlgoritmo();
            ob[10] = macro.getTipoCte();
            ob[11] = macro.getEnviado();
            ob[12] = macro.getConteoMit();
            modeloMacro.addRow(ob);
        }
        //tblMacro.getColumnModel().getColumn(1).setCellRenderer(new HyperlinkRenderer());
    }

    public void llenarTablaDescMacro(String agente, String dpd, String algoritmo) {
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("AGENTE");
        nombreColumnas.add("TELEFONO");
        nombreColumnas.add("CONTRATO");
        nombreColumnas.add("DPD");
        nombreColumnas.add("ADEUDO");
        nombreColumnas.add("NOMBRE");
        nombreColumnas.add("CORREO");
        nombreColumnas.add("SPEI");
        nombreColumnas.add("OXXO");
        nombreColumnas.add("ALGORITMO");
        nombreColumnas.add("TIPO");
        nombreColumnas.add("WHATSAPP");
        nombreColumnas.add("MITROL");

        for (Object columnas : nombreColumnas) {
            modeloMacro.addColumn(columnas);
        }
        this.tblMacro.setModel(modeloMacro);
        ArrayList<Macro> listaMacro = cm.consultaMacroDesc(agente, dpd, algoritmo);
        String lstMacro1 = listaMacro.size() + "";
        lstMacro.removeAll(lstMacro);
        lstMacro.addAll(listaMacro);
        txtRegistrosTotales.setText(lstMacro1);
        modeloMacro = (DefaultTableModel) tblMacro.getModel();
        Object[] ob = new Object[13];
        for (int i = 0; i < listaMacro.size(); i++) {
            Macro macro = listaMacro.get(i);
            ob[0] = macro.getAgente();
            ob[1] = macro.getTel();
            ob[2] = macro.getNumber();
            ob[3] = macro.getDpd();
            ob[4] = macro.getAdeudo();
            ob[5] = macro.getNombre();
            ob[6] = macro.getCorreo();
            ob[7] = macro.getSpeiClave();
            ob[8] = macro.getOxxoReference();
            ob[9] = macro.getAlgoritmo();
            ob[10] = macro.getTipoCte();
            ob[11] = macro.getEnviado();
            ob[12] = macro.getConteoMit();
            modeloMacro.addRow(ob);
        }
    }

    public void llenarTablaEnvWhatsApp() {
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("AGENTE");
        nombreColumnas.add("TELEFONO");
        nombreColumnas.add("CONTRATO");
        nombreColumnas.add("DPD");
        nombreColumnas.add("ADEUDO");
        nombreColumnas.add("NOMBRE");
        nombreColumnas.add("SPEI");
        nombreColumnas.add("OXXO");
        nombreColumnas.add("ALGORITMO");
        nombreColumnas.add("ENVIADO");
        for (Object columnas : nombreColumnas) {
            modeloWhatsApp.addColumn(columnas);
        }
        this.tblMsgWhatsApp.setModel(modeloWhatsApp);
        ArrayList<Macro> listaMacro = CWhatsappLst.consultaCargadosWhatsapp(agente);
        String lstMacro = listaMacro.size() + "";
        txtRegistrosTotalesMsgWhats.setText(lstMacro);
        modeloWhatsApp = (DefaultTableModel) tblMsgWhatsApp.getModel();
        Object[] ob = new Object[10];
        for (int i = 0; i < listaMacro.size(); i++) {
            Macro macro = listaMacro.get(i);
            ob[0] = macro.getAgente();
            ob[1] = macro.getTel();
            ob[2] = macro.getNumber();
            ob[3] = macro.getDpd();
            ob[4] = macro.getAdeudo();
            ob[5] = macro.getNombre();
            ob[6] = macro.getSpeiClave();
            ob[7] = macro.getOxxoReference();
            ob[8] = macro.getAlgoritmo();
            ob[9] = macro.getEnviado();
            modeloWhatsApp.addRow(ob);
        }
    }

    public void llenarTablaAscCargadosCCCone(String agente) {
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("AGENTE");
        nombreColumnas.add("TELEFONO");
        nombreColumnas.add("CONTRATO");
        nombreColumnas.add("DPD");
        nombreColumnas.add("ADEUDO");
        nombreColumnas.add("NOMBRE");
        nombreColumnas.add("SPEI");
        nombreColumnas.add("OXXO");
        nombreColumnas.add("ALGORITMO");
        for (Object columnas : nombreColumnas) {
            modeloCargados.addColumn(columnas);
        }
        this.tblCargadosCCC.setModel(modeloCargados);
        ArrayList<Macro> listaCargadosCCCone = cc.consultaCCCone(agente);
        String lstCargados = listaCargadosCCCone.size() + "";
        txtRegistrosTotalesCargados.setText(lstCargados);
        modeloCargados = (DefaultTableModel) tblCargadosCCC.getModel();
        Object[] ob = new Object[9];
        for (int i = 0; i < listaCargadosCCCone.size(); i++) {
            Macro cargados = listaCargadosCCCone.get(i);
            ob[0] = cargados.getAgente();
            ob[1] = cargados.getTel();
            ob[2] = cargados.getNumber();
            ob[3] = cargados.getDpd();
            ob[4] = cargados.getAdeudo();
            ob[5] = cargados.getNombre();
            ob[6] = cargados.getSpeiClave();
            ob[7] = cargados.getOxxoReference();
            ob[8] = cargados.getAlgoritmo();
            modeloCargados.addRow(ob);
        }
    }

//    public void llenarTablaDescCargadosCCCone(String agente) {
//        ArrayList<Object> nombreColumnas = new ArrayList<>();
//        nombreColumnas.add("AGENTE");
//        nombreColumnas.add("TELEFONO");
//        nombreColumnas.add("CONTRATO");
//        nombreColumnas.add("DPD");
//        nombreColumnas.add("ADEUDO");
//        nombreColumnas.add("NOMBRE");
//        nombreColumnas.add("SPEI");
//        nombreColumnas.add("OXXO");
//        nombreColumnas.add("ALGORITMO");
//        for (Object columnas : nombreColumnas) {
//            modeloCargados.addColumn(columnas);
//        }
//        this.tblCargadosCCC.setModel(modeloCargados);
//        ArrayList<Macro> listaCargadosCCCone = cc.consultaMacroDesc(agente);
//        String lstCargados = listaCargadosCCCone.size() + "";
//        txtRegistrosTotalesCargados.setText(lstCargados);
//        modeloCargados = (DefaultTableModel) tblCargadosCCC.getModel();
//        Object[] ob = new Object[9];
//        for (int i = 0; i < listaCargadosCCCone.size(); i++) {
//            Macro macro = listaCargadosCCCone.get(i);
//            ob[0] = macro.getAgente();
//            ob[1] = macro.getTel();
//            ob[2] = macro.getNumber();
//            ob[3] = macro.getDpd();
//            ob[4] = macro.getAdeudo();
//            ob[5] = macro.getNombre();
//            ob[6] = macro.getSpeiClave();
//            ob[7] = macro.getOxxoReference();
//            ob[8] = macro.getAlgoritmo();
//            modeloCargados.addRow(ob);
//        }
//    }

    public void llenarTablaPromesados(String agente, String DiasPromesa) {
        ArrayList<Object> nombreColumnas = new ArrayList<>();
        nombreColumnas.add("Pago");
        nombreColumnas.add("Contrato");
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Dias promesa");
        nombreColumnas.add("Telefono");
        nombreColumnas.add("Agente");
        nombreColumnas.add("Correo");
        nombreColumnas.add("Monto Promesado");
        nombreColumnas.add("OXXO");
        nombreColumnas.add("SPEI");
        nombreColumnas.add("Fecha Promesa");
        nombreColumnas.add("Ext 20");
        for (Object columnas : nombreColumnas) {
            modeloPromesados.addColumn(columnas);
        }
        this.tblPromesadas.setModel(modeloPromesados);
        ArrayList<Promesa> listaPromesados = cPromesados.consultaPromesados(agente, DiasPromesa);
        String lstPromesados = listaPromesados.size() + "";
        txtRegistrosTotalesPromesados.setText(lstPromesados);
        modeloPromesados = (DefaultTableModel) tblPromesadas.getModel();
        Object[] ob = new Object[12];
        for (int i = 0; i < listaPromesados.size(); i++) {
            Promesa promesa = listaPromesados.get(i);
            ob[0] = promesa.getPago();
            ob[1] = promesa.getContrato();
            ob[2] = promesa.getNombreCte();
            ob[3] = promesa.getDiasPromesa();
            ob[4] = promesa.getTelCte();
            ob[5] = promesa.getAgente();
            ob[6] = promesa.getCorreoCte();
            ob[7] = promesa.getMontoPromesado();
            ob[8] = promesa.getOxxoReference();
            ob[9] = promesa.getSpeiReference();
            ob[10] = promesa.getFechaPromesada();

            double numeroDouble = Double.parseDouble(promesa.getExtencion20());
            String ext = DosDecimales(numeroDouble);
            ob[11] = ext;
            modeloPromesados.addRow(ob);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        tbl1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMacro = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnDescendente = new javax.swing.JButton();
        btnAscendente = new javax.swing.JButton();
        cmbDpd = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbAlgoritmo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnCargarCCCone = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtRegistrosTotales = new javax.swing.JLabel();
        btnCopiarCorreo1 = new javax.swing.JButton();
        btnCopiarCorreo2 = new javax.swing.JButton();
        cmbOpcCopiar = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCargadosCCC = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtRegistrosTotalesCargados = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMsgWhatsApp = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        txtRegistrosTotalesMsgWhats = new javax.swing.JLabel();
        btnCargarCCCone1 = new javax.swing.JButton();
        btnAscendente1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btnDescendenteCargados1 = new javax.swing.JButton();
        cmbDiasPromesa = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPromesadas = new javax.swing.JTable();
        btnExcel = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtRegistrosTotalesPromesados = new javax.swing.JLabel();
        btnCargarCCCone2 = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtContrato = new javax.swing.JTextField();
        btnBuscarContrato = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtDpd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnLimpiaContrato = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        cmbSelec = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCapitalMoratorio = new javax.swing.JTextField();
        btnCalcCostoMonetario = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMoratorios = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiasMoratorio = new javax.swing.JTextField();
        btnLimpiaMoratorio = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCapitalExt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtExt10 = new javax.swing.JTextField();
        txtExt20 = new javax.swing.JTextField();
        txtExt30 = new javax.swing.JTextField();
        btnCalcularExt = new javax.swing.JButton();
        btnLimpiaExt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCliente = new javax.swing.JTextArea();
        cmbContratoTelefono = new javax.swing.JComboBox<>();
        lblTelefono = new javax.swing.JLabel();
        lblcontrato = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnHipervinculoTec = new javax.swing.JButton();
        btnCargaCccOne = new javax.swing.JButton();
        btnCopiarPlantilla = new javax.swing.JButton();
        btnWhatsapp = new javax.swing.JButton();
        btnGeneraPromesa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(155, 214, 47));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl1.setBackground(new java.awt.Color(255, 255, 255));
        tbl1.setForeground(new java.awt.Color(39, 43, 0));
        tbl1.setFont(new java.awt.Font("Segoe UI Emoji", 2, 14)); // NOI18N

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));

        tblMacro.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jScrollPane3.setViewportView(tblMacro);

        jPanel9.setBackground(new java.awt.Color(0, 75, 59));

        jPanel11.setBackground(new java.awt.Color(0, 75, 59));
        jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Adeudo");

        btnDescendente.setBackground(new java.awt.Color(39, 43, 0));
        btnDescendente.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnDescendente.setForeground(new java.awt.Color(255, 255, 255));
        btnDescendente.setText("Buscar Desc");
        btnDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescendenteActionPerformed(evt);
            }
        });

        btnAscendente.setBackground(new java.awt.Color(39, 43, 0));
        btnAscendente.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnAscendente.setForeground(new java.awt.Color(255, 255, 255));
        btnAscendente.setText("Buscar Asc");
        btnAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscendenteActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Filtrar DPD");

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Algoritmo");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(13, 13, 13))
                    .addComponent(cmbDpd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAscendente)
                        .addGap(18, 18, 18)
                        .addComponent(btnDescendente)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(187, 187, 187)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(12, 12, 12)))
                .addGap(53, 53, 53))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDpd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDescendente)
                            .addComponent(btnAscendente))))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Filtros");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(347, 347, 347))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        btnCargarCCCone.setBackground(new java.awt.Color(39, 43, 0));
        btnCargarCCCone.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnCargarCCCone.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarCCCone.setText("Cargar Contrato");
        btnCargarCCCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCCConeActionPerformed(evt);
            }
        });

        jLabel6.setText("Registros Totales:");

        txtRegistrosTotales.setText("  ");

        btnCopiarCorreo1.setBackground(new java.awt.Color(39, 43, 0));
        btnCopiarCorreo1.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnCopiarCorreo1.setForeground(new java.awt.Color(255, 255, 255));
        btnCopiarCorreo1.setText("Descargar Excel por filtro ");
        btnCopiarCorreo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarCorreo1ActionPerformed(evt);
            }
        });

        btnCopiarCorreo2.setBackground(new java.awt.Color(39, 43, 0));
        btnCopiarCorreo2.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnCopiarCorreo2.setForeground(new java.awt.Color(255, 255, 255));
        btnCopiarCorreo2.setText("Descargar Excel Macro ");
        btnCopiarCorreo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarCorreo2ActionPerformed(evt);
            }
        });

        cmbOpcCopiar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Copiar correo", "Copiar contrato" }));
        cmbOpcCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOpcCopiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCargarCCCone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbOpcCopiar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCopiarCorreo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCopiarCorreo2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCopiarCorreo2)
                            .addComponent(cmbOpcCopiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCopiarCorreo1)
                            .addComponent(btnCargarCCCone)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtRegistrosTotales)))))
        );

        tbl1.addTab("Macro", jPanel7);

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));

        tblCargadosCCC.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jScrollPane4.setViewportView(tblCargadosCCC);

        jPanel10.setBackground(new java.awt.Color(0, 75, 59));

        jLabel19.setFont(new java.awt.Font("Segoe UI Emoji", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Registros cargados a CCCone");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel19)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel19)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel20.setText("Registros Totales: ");

        txtRegistrosTotalesCargados.setText("  ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrosTotalesCargados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtRegistrosTotalesCargados)))
        );

        tbl1.addTab("CCCone", jPanel8);

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        jPanel15.setBackground(new java.awt.Color(0, 75, 59));

        jLabel26.setFont(new java.awt.Font("Segoe UI Emoji", 1, 15)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Mensajes enviados por whatsapp");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel26)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tblMsgWhatsApp.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jScrollPane6.setViewportView(tblMsgWhatsApp);

        jLabel27.setText("Registros Totales: ");

        txtRegistrosTotalesMsgWhats.setText("  ");

        btnCargarCCCone1.setBackground(new java.awt.Color(39, 43, 0));
        btnCargarCCCone1.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnCargarCCCone1.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarCCCone1.setText("Cargar Contrato");
        btnCargarCCCone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCCCone1ActionPerformed(evt);
            }
        });

        btnAscendente1.setBackground(new java.awt.Color(39, 43, 0));
        btnAscendente1.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnAscendente1.setForeground(new java.awt.Color(255, 255, 255));
        btnAscendente1.setText("Actualizar tabla");
        btnAscendente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscendente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCargarCCCone1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAscendente1)
                .addGap(156, 156, 156)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegistrosTotalesMsgWhats, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(txtRegistrosTotalesMsgWhats)
                        .addComponent(btnAscendente1))
                    .addComponent(btnCargarCCCone1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl1.addTab("WhatsApp", jPanel6);

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));

        jPanel13.setBackground(new java.awt.Color(0, 75, 59));

        jPanel14.setBackground(new java.awt.Color(0, 75, 59));
        jPanel14.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        btnDescendenteCargados1.setBackground(new java.awt.Color(39, 43, 0));
        btnDescendenteCargados1.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnDescendenteCargados1.setForeground(new java.awt.Color(255, 255, 255));
        btnDescendenteCargados1.setText("Filtrar");
        btnDescendenteCargados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescendenteCargados1ActionPerformed(evt);
            }
        });

        cmbDiasPromesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-1", "0", "1" }));

        jLabel22.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Dias Promesa:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDiasPromesa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnDescendenteCargados1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDescendenteCargados1)
                    .addComponent(cmbDiasPromesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Segoe UI Emoji", 1, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Filtro por dias promesa");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        tblPromesadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPromesadas.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jScrollPane5.setViewportView(tblPromesadas);

        btnExcel.setBackground(new java.awt.Color(39, 43, 0));
        btnExcel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setText("Descargar Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        jLabel25.setText("Registros Totales:   ");

        txtRegistrosTotalesPromesados.setText("  ");

        btnCargarCCCone2.setBackground(new java.awt.Color(39, 43, 0));
        btnCargarCCCone2.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnCargarCCCone2.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarCCCone2.setText("Cargar Contrato");
        btnCargarCCCone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCCCone2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnCargarCCCone2)
                .addGap(145, 145, 145)
                .addComponent(btnExcel)
                .addGap(170, 170, 170)
                .addComponent(jLabel25)
                .addGap(0, 0, 0)
                .addComponent(txtRegistrosTotalesPromesados, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcel)
                        .addComponent(btnCargarCCCone2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtRegistrosTotalesPromesados))))
                .addGap(6, 6, 6))
        );

        tbl1.addTab("Promesas", jPanel5);

        jPanel3.add(tbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 770, 410));

        btnRegresar.setBackground(new java.awt.Color(39, 43, 0));
        btnRegresar.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar a Menu");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 6, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 770, 450));

        jPanel1.setBackground(new java.awt.Color(0, 109, 56));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        txtContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContratoKeyTyped(evt);
            }
        });

        btnBuscarContrato.setBackground(new java.awt.Color(39, 43, 0));
        btnBuscarContrato.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnBuscarContrato.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarContrato.setText("Buscar");
        btnBuscarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarContratoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cliente: ");

        txtDpd.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DPD: ");

        btnLimpiaContrato.setBackground(new java.awt.Color(39, 43, 0));
        btnLimpiaContrato.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnLimpiaContrato.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiaContrato.setText("Limpiar");
        btnLimpiaContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiaContratoActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jPanel16.setBackground(new java.awt.Color(0, 109, 56));

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Segoe UI Emoji", 0, 13)); // NOI18N
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        cmbSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(cmbSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSelec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Plantilla", jPanel16);

        jPanel4.setBackground(new java.awt.Color(0, 109, 56));

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Capital:");

        txtCapitalMoratorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapitalMoratorioKeyTyped(evt);
            }
        });

        btnCalcCostoMonetario.setBackground(new java.awt.Color(39, 43, 0));
        btnCalcCostoMonetario.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnCalcCostoMonetario.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcCostoMonetario.setText("Calcular");
        btnCalcCostoMonetario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcCostoMonetarioActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Moratorios: ");

        txtMoratorios.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Dias:");

        txtDiasMoratorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasMoratorioKeyTyped(evt);
            }
        });

        btnLimpiaMoratorio.setBackground(new java.awt.Color(39, 43, 0));
        btnLimpiaMoratorio.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnLimpiaMoratorio.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiaMoratorio.setText("Limpiar");
        btnLimpiaMoratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiaMoratorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCapitalMoratorio)
                            .addComponent(txtDiasMoratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMoratorios, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnLimpiaMoratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCalcCostoMonetario, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapitalMoratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDiasMoratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcCostoMonetario)
                    .addComponent(btnLimpiaMoratorio))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMoratorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Calcular costo moratorio", jPanel4);

        jPanel2.setBackground(new java.awt.Color(0, 109, 56));

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Capital: ");

        txtCapitalExt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCapitalExt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapitalExtKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("EXT 10");

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EXT 20:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("EXT 30:");

        txtExt10.setEditable(false);
        txtExt10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtExt20.setEditable(false);
        txtExt20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtExt30.setEditable(false);
        txtExt30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnCalcularExt.setBackground(new java.awt.Color(0, 0, 0));
        btnCalcularExt.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnCalcularExt.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcularExt.setText("Calcular");
        btnCalcularExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularExtActionPerformed(evt);
            }
        });

        btnLimpiaExt.setBackground(new java.awt.Color(0, 0, 0));
        btnLimpiaExt.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        btnLimpiaExt.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiaExt.setText("Limpiar");
        btnLimpiaExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiaExtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnLimpiaExt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnCalcularExt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtExt10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(3, 3, 3)
                                    .addComponent(txtExt20))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(3, 3, 3)
                                    .addComponent(txtExt30, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCapitalExt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapitalExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExt30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiaExt)
                    .addComponent(btnCalcularExt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Calcular extension", jPanel2);

        txtCliente.setEditable(false);
        txtCliente.setColumns(20);
        txtCliente.setRows(5);
        jScrollPane2.setViewportView(txtCliente);

        cmbContratoTelefono.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contrato", "Telefono" }));

        lblTelefono.setBackground(new java.awt.Color(0, 109, 56));
        lblTelefono.setForeground(new java.awt.Color(0, 109, 56));

        lblcontrato.setBackground(new java.awt.Color(0, 109, 56));
        lblcontrato.setForeground(new java.awt.Color(0, 109, 56));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(cmbContratoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnBuscarContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLimpiaContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDpd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblcontrato, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbContratoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscarContrato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiaContrato)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDpd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcontrato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 450));

        jPanel17.setBackground(new java.awt.Color(0, 109, 56));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHipervinculoTec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHipervinculoTecActionPerformed(evt);
            }
        });
        jPanel17.add(btnHipervinculoTec, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 50));

        btnCargaCccOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargaCccOneActionPerformed(evt);
            }
        });
        jPanel17.add(btnCargaCccOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 50, 50));

        btnCopiarPlantilla.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnCopiarPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarPlantillaActionPerformed(evt);
            }
        });
        jPanel17.add(btnCopiarPlantilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 50, 50));

        btnWhatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWhatsappActionPerformed(evt);
            }
        });
        jPanel17.add(btnWhatsapp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 50, 50));

        btnGeneraPromesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneraPromesaActionPerformed(evt);
            }
        });
        jPanel17.add(btnGeneraPromesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 50, 50));

        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 0, 70, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void setClipBoard(String texto) {
        StringSelection txt = new StringSelection(texto);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(txt, (ClipboardOwner) this);
    }

    public String modificarCoincidencias(String patronNombreVariable, String descPlantilla, String OBJETONOMBRE) {
        Pattern patternNombreVariable = Pattern.compile(patronNombreVariable);
        Matcher similitudNombreVariable = patternNombreVariable.matcher(descPlantilla);
        String textoNuevo1 = similitudNombreVariable.replaceAll(OBJETONOMBRE);
        return textoNuevo1;
    }
    private void cmbSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelecActionPerformed
        String contrato = txtContrato.getText();

        try {
            if (!txtContrato.getText().isEmpty()) {
                ArrayList<REF_GPT1> listacliente = listaContrato.getListacliente();
                ArrayList<Plantilla> listaPlantillas = consultarPlantillas.leerCSV();
                int opc = cmbSelec.getSelectedIndex();
                Plantilla pl = listaPlantillas.get(opc);
                String descPlantilla = pl.getDescPlantilla();

                //obteniendo los datos del clinete
                REF_GPT1 ObjContrato = listacliente.get(0);

                String patronNombreVariable = "/NOMBRE_VARIABLE/";
                String oNombreCte = ObjContrato.getNombre();
                String descPlantilla1 = modificarCoincidencias(patronNombreVariable, descPlantilla, oNombreCte);
                //obteniendo credito del clinete
                String patronCreditoVariable = "/CREDITO_VARIABLE/";
                String oCreditoVariable = ObjContrato.getNumber();
                String descPlantilla2 = modificarCoincidencias(patronCreditoVariable, descPlantilla1, oCreditoVariable);
                //obteniendo fecha de pago del clinete
                String patronFechaPagoVariable = "/FECHA_PAGO/";
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                String oFechPago = formatoFecha.format(ObjContrato.getFechaPago());
                String descPlantilla3 = modificarCoincidencias(patronFechaPagoVariable, descPlantilla2, oFechPago);
                //obteniendo nombre del clinete
                String patronAdeudoVariable = "/ADEUDO_VARIABLE/";
                String oAdeudoVariable = ObjContrato.getAdeudo();
                double oAdeudoVariableSDouble = Double.parseDouble(oAdeudoVariable);
                int adeudo = (int) Math.ceil(oAdeudoVariableSDouble);
                String AdeudoVariableStr = adeudo + "";

                String descPlantilla4 = modificarCoincidencias(patronAdeudoVariable, descPlantilla3, AdeudoVariableStr);
                //obteniendo saldo del clinete
                String patronSaldoVariable = "/SALDO_VARIABLE/";
                String oSaldoVariable = ObjContrato.getSaldoHoy();
                double oSaldoVariableSDouble = Double.parseDouble(oSaldoVariable);
                int saldo = (int) Math.ceil(oSaldoVariableSDouble);
                String SaldoVariableStr = saldo + "";

                String descPlantilla5 = modificarCoincidencias(patronSaldoVariable, descPlantilla4, SaldoVariableStr);
                //obteniendo ext20 del clinete
                String patronExt20NombreVariable = "/EXT_20/";
                String oExt20 = ObjContrato.getExtension20();
                double oExt20Double = Double.parseDouble(oExt20);
                int ext20 = (int) Math.ceil(oExt20Double);
                String ext20Str = ext20 + "";
                String descPlantilla6 = modificarCoincidencias(patronExt20NombreVariable, descPlantilla5, ext20Str);
                //obteniendo ext20 del clinete
                String patronExt20Variable = "/EXT_30/";
                String oExt30 = ObjContrato.getExtension30();
                double oExt30Double = Double.parseDouble(oExt30);
                int ext30 = (int) Math.ceil(oExt30Double);
                String ext30Str = ext30 + "";
                String descPlantilla7 = modificarCoincidencias(patronExt20Variable, descPlantilla6, ext30Str);

                String patronSpeiVariable = "/REF_SPEI/";
                String oSpei = ObjContrato.getSpeiClave();
                String descPlantilla8 = modificarCoincidencias(patronSpeiVariable, descPlantilla7, oSpei);

                String patronOxxoVariable = "/REF_OXXO/";
                String oOxxo = ObjContrato.getOxxoReference();
                String descPlantilla9 = modificarCoincidencias(patronOxxoVariable, descPlantilla8, oOxxo);

                txtArea.setText(descPlantilla9);
            }

        } catch (CsvValidationException ex) {
            Logger.getLogger(PromesasPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbSelecActionPerformed

    private void btnCargaCccOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaCccOneActionPerformed
        String contrato = txtContrato.getText();

        if (!txtContrato.getText().isEmpty()) {
            cargaDatosCCConeApi();
        } else {
            JOptionPane.showMessageDialog(null, "Campo de contrato requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCargaCccOneActionPerformed
    public void cargaDatosCCConeApi() {
        // Mostrar mensaje de carga en un hilo aparte
        final JDialog loadingDialog = new JDialog(this, "Cargando datos... ", true);
        SwingUtilities.invokeLater(() -> {
            loadingDialog.setSize(300, 100);
            loadingDialog.setLocationRelativeTo(this);
            //loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JLabel loadingLabel = new JLabel("Recargando tablas Macro y CCCone...");
            loadingLabel.setHorizontalAlignment(JLabel.CENTER);
            loadingDialog.add(loadingLabel);

            loadingDialog.setVisible(true);
        });

        // Simulando la carga del JFrame "macro" en un hilo aparte
        new Thread(() -> {
            //carga aa la api en segundo plano asi como la carga del contrato al bd y recarga de las tablas de macro y CCCone
            String contrato = txtContrato.getText();
            ArrayList<REF_GPT1> lista = consultaREFGPT1.consultaREFGPT1(contrato);
            System.out.println("tamaño de la lista :" + lista.size());
            ArrayList<REF_GPT1> lstcliente = lista;
            REF_GPT1 objContrato = lstcliente.get(0);
            String number = objContrato.getNumber();
            String dpd = objContrato.getDpd();
            String tipoAsignacion = objContrato.getTipoCliente();
            String adeudo = objContrato.getAdeudo();
            String tel = objContrato.getTel();
            String nameClient = objContrato.getClientFullName();
            String algoritmo = objContrato.getAlgoritmo();
            //            System.out.println("number" + number);
            //            System.out.println("dpd" + dpd);
            //            System.out.println("tipoAsignacion" + tipoAsignacion);
            //            System.out.println("adeudo" + adeudo);
            //            System.out.println("tel" + tel);
            //            System.out.println("nameClient" + nameClient);
            //            System.out.println("algoritmo" + algoritmo);
            try {
                ControllerPostNewRecord.PostOkHttp(number, dpd, tipoAsignacion, adeudo, tel, nameClient, algoritmo);
                //se hace un insert de los datos 
                cargarCCCone.CargarCCCone(contrato, agente);
                //actualizar la tabla de cargados y agente 
                cargaDatosMacroAsc(agente);
                cargaDatosCCConeAsc(agente);
                ///cambio de color al los botones al seleccionar
                limpiarTabla(tblCargadosCCC);
                llenarTablaAscCargadosCCCone(agente);
                estilosTablaMacro();

                btnCargaCccOne.setEnabled(false);
            } catch (IOException ex) {
                Logger.getLogger(PromesasPagos.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.invokeLater(() -> {
                loadingDialog.dispose();
            });
        }).start();
    }
    private void txtContratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContratoKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
        }
        if (txtContrato.getText().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtContratoKeyTyped

    private void btnBuscarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarContratoActionPerformed

        int indiceOpc = cmbContratoTelefono.getSelectedIndex();
        switch (indiceOpc) {
            case 0:
                ///Aqui ira la funcionalidad de buscar cliente
                //se crea variable auxiliar
                txtArea.setText("");
                String contrato = txtContrato.getText();
                lblcontrato.setText(contrato);
                if (!txtContrato.getText().isEmpty()) {
                    ArrayList<REF_GPT1> lista = consultaREFGPT1.consultaREFGPT1(contrato);
                    listaContrato.setListacliente(lista);
                    for (int i = 0; i < listaContrato.getListacliente().size(); i++) {
                        REF_GPT1 datoContrato = new REF_GPT1();
                        //obtener el objeto de la lista
                        datoContrato = listaContrato.getListacliente().get(i);
                        //asignar valores al label
                        String nombreCliente = datoContrato.getClientFullName();
                        txtCliente.setText(nombreCliente);
                        String dpd = datoContrato.getDpd();
                        txtDpd.setText(dpd);
                        String capital = datoContrato.getOpen_principal();

                        //convirtiendo string a decimal
                        double capitalDouble = Double.parseDouble(capital);
                        int capitalInt = (int) Math.ceil(capitalDouble);
                        //calculando el valor minimo
                        double valorminimoExt = capitalDouble * 0.075;
                        //se agigna el valor a la variable aux con base a si es mayor de 150 o mayor
                        double aux;
                        if (valorminimoExt < 150) {
                            aux = valorminimoExt;
                        } else {
                            aux = 150;
                        }
                        double ext10 = (10 * 0.018 * capitalDouble * 1.16) + aux;
                        int extencion10 = (int) Math.ceil(ext10);
                        String ext_10 = extencion10 + "";
                        txtExt10.setText(ext_10);
                        //obtiene el dato de capital para ambas moratorio y extencion
                        txtCapitalMoratorio.setText(capitalInt + "");
                        txtCapitalExt.setText(capitalInt + "");

                        String ext20 = datoContrato.getExtension20();
                        Double ext2 = Double.valueOf(ext20);
                        int extencion20 = (int) Math.ceil(ext2);
                        txtExt20.setText(extencion20 + "");

                        String ext30 = datoContrato.getExtension30();
                        Double ext3 = Double.valueOf(ext30);
                        int extencion30 = (int) Math.ceil(ext3);
                        txtExt30.setText(extencion30 + "");
                        String telefono = datoContrato.getTel();
                        lblTelefono.setText(telefono);
                        btnCargaCccOne.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campo de contrato requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 1:
                ///Aqui ira la funcionalidad de buscar cliente
                //se crea variable auxiliar
                txtArea.setText("");
                String telefono = txtContrato.getText();
                if (!txtContrato.getText().isEmpty()) {
                    ArrayList<REF_GPT1> lista = consultaREFGPT1.consultaREFGPT1Tel(telefono);
                    listaContrato.setListacliente(lista);
                    for (int i = 0; i < listaContrato.getListacliente().size(); i++) {
                        REF_GPT1 datoContrato = new REF_GPT1();
                        //obtener el objeto de la lista
                        datoContrato = listaContrato.getListacliente().get(i);
                        //asignar valores al label
                        String nombreCliente = datoContrato.getClientFullName();
                        txtCliente.setText(nombreCliente);
                        String dpd = datoContrato.getDpd();
                        txtDpd.setText(dpd);
                        String capital = datoContrato.getOpen_principal();
                        //convirtiendo string a decimal
                        double capitalInt = Double.parseDouble(capital);
                        //calculando el valor minimo
                        double valorminimoExt = capitalInt * 0.075;
                        //se agigna el valor a la variable aux con base a si es mayor de 150 o mayor
                        double aux;
                        if (valorminimoExt < 150) {
                            aux = valorminimoExt;
                        } else {
                            aux = 150;
                        }
                        double ext10 = (10 * 0.018 * capitalInt * 1.16) + aux;
                        String ext_10 = DosDecimales(ext10);
                        txtExt10.setText(ext_10);
                        txtCapitalMoratorio.setText(capital);
                        txtCapitalExt.setText(capital);
                        String ext20 = datoContrato.getExtension20();
                        Double ext2 = Double.parseDouble(ext20);
                        String Ext20RedondeadoStr = DosDecimales(ext2);
                        txtExt20.setText(Ext20RedondeadoStr);

                        String ext30 = datoContrato.getExtension30();
                        Double ext3 = Double.parseDouble(ext30);
                        String Ext30RedondeadoStr = DosDecimales(ext3);
                        txtExt30.setText(Ext30RedondeadoStr);
                        lblTelefono.setText(telefono);
                        btnCargaCccOne.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campo de contrato requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                break;

        }


    }//GEN-LAST:event_btnBuscarContratoActionPerformed

    private void btnLimpiaContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiaContratoActionPerformed
        //se limpia la caja de texto contrato
        txtContrato.setText("");
        txtCliente.setText("");
        txtDpd.setText("");
        txtCapitalMoratorio.setText("");
        txtDiasMoratorio.setText("");
        txtMoratorios.setText("");
        txtCapitalExt.setText("");
        txtExt10.setText("");
        txtExt20.setText("");
        txtExt30.setText("");
    }//GEN-LAST:event_btnLimpiaContratoActionPerformed

    private void txtCapitalMoratorioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapitalMoratorioKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCapitalMoratorioKeyTyped

    private void btnCalcCostoMonetarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcCostoMonetarioActionPerformed
        if (!txtCapitalMoratorio.getText().isEmpty() || !txtDiasMoratorio.getText().isEmpty()) {
            //se obtiene el valor del capital ingresasdo
            double capital = Integer.parseInt(txtCapitalMoratorio.getText());
            //se obtiene el valor del dia ingresado
            double dia = Integer.parseInt(txtDiasMoratorio.getText());
            //se obtiene el costo multiplicando =dia *capital *0.0232
            double costo = capital * dia * 0.0232;
            //se formatea a dos decimales y se convierte a string
            int costoInt = (int) Math.ceil(costo);
            //se añade el valor a la caja de texto moratorios
            txtMoratorios.setText(costoInt + "");
        }

    }//GEN-LAST:event_btnCalcCostoMonetarioActionPerformed

    private void txtDiasMoratorioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasMoratorioKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDiasMoratorioKeyTyped

    private void btnLimpiaMoratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiaMoratorioActionPerformed
        //se limpia la caja de texto capital moratorio
        txtCapitalMoratorio.setText("");
        txtDiasMoratorio.setText("");
        txtMoratorios.setText("");
    }//GEN-LAST:event_btnLimpiaMoratorioActionPerformed

    private void txtCapitalExtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapitalExtKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCapitalExtKeyTyped
    public String DosDecimales(double numero) {
        //Se crea el objeto formato decimal para darle formato de dos decimales
        //a el resultado de extencion
        DecimalFormat formatoDecimal = new DecimalFormat("#.##");
        //se redondea el valor a 2 decimales a oartir del objeto formatoDecimal
        double numeroRedondeado = Double.parseDouble(formatoDecimal.format(numero));
        //se convierte el valor a texto para añadirlo a la caja de texto de la vista
        String ext = numeroRedondeado + "";
        return ext;

    }
    private void btnCalcularExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularExtActionPerformed
        //se crea variable auxiliar
        double aux;
        double capital = Integer.parseInt(txtCapitalExt.getText());
        double valorminimoExt = capital * 0.075;
        //se agigna el valor a la variable aux con base a si es mayor de 150 o mayor
        if (valorminimoExt < 150) {
            aux = valorminimoExt;
        } else {
            aux = 150;
        }
        //calculos de las extenciones de 10, 20 y 30 dias
        double ext10 = (10 * 0.018 * capital * 1.16) + aux;
        double ext20 = (20 * 0.016 * capital * 1.16) + aux;
        double ext30 = (30 * 0.015 * capital * 1.16) + aux;
        //se formatea a dos decimales y se convierte a string
        String ext_10 = DosDecimales(ext10);
        String ext_20 = DosDecimales(ext20);
        String ext_30 = DosDecimales(ext30);
        //se añade el valor a la caja de texto
        txtExt10.setText(ext_10);
        txtExt20.setText(ext_20);
        txtExt30.setText(ext_30);
    }//GEN-LAST:event_btnCalcularExtActionPerformed

    private void btnLimpiaExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiaExtActionPerformed
        //se limpia la caja de texto de capital y sus extenciones
        txtCapitalExt.setText("");
        txtExt10.setText("");
        txtExt20.setText("");
        txtExt30.setText("");
    }//GEN-LAST:event_btnLimpiaExtActionPerformed

    private void btnAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscendenteActionPerformed
        // Mostrar mensaje de carga en un hilo aparte
        final JDialog loadingDialog = new JDialog(this, "Cargando datos... ", true);
        SwingUtilities.invokeLater(() -> {
            loadingDialog.setSize(300, 100);
            loadingDialog.setLocationRelativeTo(this);
            //loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JLabel loadingLabel = new JLabel("Cargando datos Macro...");
            loadingLabel.setHorizontalAlignment(JLabel.CENTER);
            loadingDialog.add(loadingLabel);

            loadingDialog.setVisible(true);
        });

        // Simulando la carga del JFrame "macro" en un hilo aparte
        new Thread(() -> {
            cargaDatosMacroAsc(agente);
            SwingUtilities.invokeLater(() -> {
                loadingDialog.dispose();
            });
        }).start();

    }//GEN-LAST:event_btnAscendenteActionPerformed
    public void cargaDatosMacroAsc(String agente) {
        ///cambio de color al los botones al seleccionar
        btnAscendente.setBackground(botonSeleccionado);
        btnAscendente.setForeground(letraBlanca);
        btnDescendente.setBackground(botonNoSeleccionado);
        btnDescendente.setForeground(letraNegra);
        limpiarTabla(tblMacro);
        //obtiene los valores de los combo box
        int selectedIndexDpd = cmbDpd.getSelectedIndex();
        String itemSeleccionadoDpd = cmbDpd.getItemAt(selectedIndexDpd);

        int selectedIndexAlgoritmo = cmbAlgoritmo.getSelectedIndex();
        String itemSeleccionadoAlgoritmo = cmbAlgoritmo.getItemAt(selectedIndexAlgoritmo);
//        System.out.println("Dpd: "+itemSeleccionadoDpd);
//        System.out.println("Algoritmo: "+itemSeleccionadoAlgoritmo);
        llenarTablaAscMacro(agente, itemSeleccionadoDpd, itemSeleccionadoAlgoritmo);
        estilosTablaMacro();
    }
    private void btnDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescendenteActionPerformed
        // Mostrar mensaje de carga en un hilo aparte
        final JDialog loadingDialog = new JDialog(this, "Cargando datos... ", true);
        SwingUtilities.invokeLater(() -> {
            loadingDialog.setSize(300, 100);
            loadingDialog.setLocationRelativeTo(this);
            //loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JLabel loadingLabel = new JLabel("Cargando datos Macro...");
            loadingLabel.setHorizontalAlignment(JLabel.CENTER);
            loadingDialog.add(loadingLabel);

            loadingDialog.setVisible(true);
        });

        // Simulando la carga del JFrame "macro" en un hilo aparte
        new Thread(() -> {
            cargaDatosMacroDesc(agente);
            SwingUtilities.invokeLater(() -> {
                loadingDialog.dispose();
            });
        }).start();
    }//GEN-LAST:event_btnDescendenteActionPerformed
    public void cargaDatosMacroDesc(String agente) {
        ///cambio de color al los botones al seleccionar
        btnDescendente.setBackground(botonSeleccionado);
        btnDescendente.setForeground(letraBlanca);
        btnAscendente.setBackground(botonNoSeleccionado);
        btnAscendente.setForeground(letraNegra);
        limpiarTabla(tblMacro);
        //obtiene los valores de los combo box
        int selectedIndexDpd = cmbDpd.getSelectedIndex();
        int selectedIndexAlgoritmo = cmbAlgoritmo.getSelectedIndex();
        String itemSeleccionadoDpd = cmbDpd.getItemAt(selectedIndexDpd);
        String itemSeleccionadoAlgoritmo = cmbAlgoritmo.getItemAt(selectedIndexAlgoritmo);
//        System.out.println("Dpd: "+itemSeleccionadoDpd);
//        System.out.println("Algoritmo: "+itemSeleccionadoAlgoritmo);
        llenarTablaDescMacro(agente, itemSeleccionadoDpd, itemSeleccionadoAlgoritmo);
        estilosTablaMacro();
    }

//    public void cargaDatosCCConeDesc(String agente) {
//        ///cambio de color al los botones al seleccionar
//        limpiarTabla(tblCargadosCCC);
//        llenarTablaDescCargadosCCCone(agente);
//        estilosTablaMacro();
//    }

    public void cargaDatosCCConeAsc(String agente) {
        ///cambio de color al los botones al seleccionar
        limpiarTabla(tblCargadosCCC);
        llenarTablaAscCargadosCCCone(agente);
        estilosTablaMacro();
    }
    private void btnCargarCCConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCCConeActionPerformed
        int fila = tblMacro.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para cargar");
        } else {
            String contrato = tblMacro.getValueAt(fila, 2).toString();
            //System.out.println("Contrato :"+contrato);
            txtContrato.setText(contrato);
        }
    }//GEN-LAST:event_btnCargarCCConeActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        cp.Menu(agente);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnDescendenteCargados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescendenteCargados1ActionPerformed
        cargaDatosPromesados(agente);
    }//GEN-LAST:event_btnDescendenteCargados1ActionPerformed
    public void cargaDatosPromesados(String agente) {

        limpiarTabla(tblPromesadas);
        //obtiene los valores de los combo box
        //llenado de tabla de promesados
        int selectedIndexPromesados = cmbDiasPromesa.getSelectedIndex();
        String itemSeleccionadoPromesados = cmbDiasPromesa.getItemAt(selectedIndexPromesados);
        llenarTablaPromesados(agente, itemSeleccionadoPromesados);
        estilosTablaPromesados();
    }
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        ArrayList<Promesa> consultaPromesados = cPromesados.consultaPromesados(agente);
        gExcel.generaExcelPromesas(consultaPromesados);
    }//GEN-LAST:event_btnExcelActionPerformed

    public String textoApiTransf(String patronEspacio, String textoNuevo, String textoPlantilla) {
        Pattern patternNombreVariable = Pattern.compile(patronEspacio);
        Matcher similitudNombreVariable = patternNombreVariable.matcher(textoPlantilla);
        String textoNuevo1 = similitudNombreVariable.replaceAll(textoNuevo);
        return textoNuevo1;
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

    public void cargaDatosWhatsapp(String agente) {

        limpiarTabla(tblMsgWhatsApp);
        //obtiene los valores de los combo box
        //llenado de tabla de promesados
        llenarTablaEnvWhatsApp();
        estilosTablaWhats();
    }
    private void btnHipervinculoTecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHipervinculoTecActionPerformed
        String contrato = txtContrato.getText();
        if (!txtContrato.getText().isEmpty()) {
            String urlTecIncompleta = "https://tk4f.mx/VIVUS/tekrecovery/wbTEKSSO.aspx?ContractNumber=";
            String urlTec = urlTecIncompleta + contrato;
            System.out.println(urlTec);
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI url1 = new java.net.URI(urlTec);
                        desktop.browse(url1);
                        System.out.print("Si entro");
                    } catch (URISyntaxException | IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error en " + ex, "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campo de contrato requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnHipervinculoTecActionPerformed

    private void btnWhatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhatsappActionPerformed
        String telefono = lblTelefono.getText();
        String mensaje = txtArea.getText();
        try {
            String textoLimpiado = URLEncoder.encode(mensaje, "UTF-8");
            String urlApiWhatsApp="https://api.whatsapp.com/send?phone=521"+telefono+"&text="+textoLimpiado;
//            String urlApiWhatsApp = "whatsapp://send?phone=521" + telefono + "&text=" + textoLimpiado;
            //System.out.println(urlApiWhatsApp);
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI url1 = new java.net.URI(urlApiWhatsApp);
                        desktop.browse(url1);
//                        desktop.browse(url1);
                        String CreateDate = obtenerFechaActual();
                        desktop.browse(url1);
                        String contrato = lblcontrato.getText();
//                        System.out.println("FechaAct: " + CreateDate);
//                        System.out.println("Contrato: " + contrato);
//                        System.out.println("Agente: " + agente);
                        cWhatsapp.CargarMsgWhatsApp(CreateDate, contrato, agente, "1");
                        //recargando datos de la tabla de cargados a whastapp
                        //cargaDatosWhatsapp(agente);
                        //cargaDatosMacroAsc(agente);
                    } catch (URISyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Error en " + ex, "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } catch (IOException ex) {
                        Logger.getLogger(PromesasPagos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Error al convertir a url causa:" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnWhatsappActionPerformed

    private void btnCopiarPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarPlantillaActionPerformed
        setClipBoard(txtArea.getText());
    }//GEN-LAST:event_btnCopiarPlantillaActionPerformed

    private void btnCargarCCCone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCCCone1ActionPerformed
        //tblMsgWhatsApp
        int fila = tblMsgWhatsApp.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para cargar");
        } else {
            String contrato = tblMsgWhatsApp.getValueAt(fila, 2).toString();
            //System.out.println("Contrato :"+contrato);
            txtContrato.setText(contrato);
        }
    }//GEN-LAST:event_btnCargarCCCone1ActionPerformed

    private void btnCopiarCorreo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarCorreo1ActionPerformed
        GeneraExcelMacro gExcelMacro = new GeneraExcelMacro();
        gExcelMacro.generaExcelPromesas(lstMacro);
    }//GEN-LAST:event_btnCopiarCorreo1ActionPerformed

    private void btnCopiarCorreo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarCorreo2ActionPerformed
        crearexcelmasivo macro = new crearexcelmasivo();
        macro.cargarDatosMacroExcel(agente);
    }//GEN-LAST:event_btnCopiarCorreo2ActionPerformed

    private void btnCargarCCCone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCCCone2ActionPerformed
        //tblMsgWhatsApp
        int fila = tblPromesadas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para cargar");
        } else {
            String contrato = tblPromesadas.getValueAt(fila, 1).toString();
            //System.out.println("Contrato :"+contrato);
            txtContrato.setText(contrato);
        }
    }//GEN-LAST:event_btnCargarCCCone2ActionPerformed

    private void cmbOpcCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOpcCopiarActionPerformed
        int opcCopiado = cmbOpcCopiar.getSelectedIndex();
        int fila = tblMacro.getSelectedRow();
        switch (opcCopiado) {
            case 0:
                //copiar portapapeles contrato
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro para copiar");
                } else {
                    String correo = tblMacro.getValueAt(fila, 6).toString();
                    System.out.println("Correo :" + correo);
                    setClipBoard(correo);
                }
                break;
            case 1:
                //copiar portapapeles contrato
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro para copiar");
                } else {
                    String contrato = tblMacro.getValueAt(fila, 2).toString();
                    setClipBoard(contrato);
                }
                break;
        }
    }//GEN-LAST:event_cmbOpcCopiarActionPerformed

    private void btnAscendente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscendente1ActionPerformed
        cargaDatosWhatsapp(agente);
    }//GEN-LAST:event_btnAscendente1ActionPerformed

    private void btnGeneraPromesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneraPromesaActionPerformed
        cp.agregaPromesaPago(agente);
        //this.dispose();
    }//GEN-LAST:event_btnGeneraPromesaActionPerformed
//    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        // Obtener el modelo de la tabla
//        DefaultTableModel model = (DefaultTableModel) tblMacro.getModel();
//        // Obtener las filas seleccionadas
//        int[] selectedRows = tblMacro.getSelectedRows();
//        
//        // Especifica la columna de la que deseas obtener los datos
//        int columnIndex = 0; // Por ejemplo, la primera columna
//        
//        // Iterar sobre las filas seleccionadas
//        for (int i = 0; i < selectedRows.length; i++) {
//            int rowIndex = selectedRows[i];
//            // Obtener el valor de la columna especificada
//            Object value = model.getValueAt(rowIndex, columnIndex);
//            
//            // Haz lo que necesites con el valor obtenido
//            System.out.println("Fila " + rowIndex + ", Columna " + columnIndex + ": " + value);
//        }
//    }                                        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAscendente;
    private javax.swing.JButton btnAscendente1;
    private javax.swing.JButton btnBuscarContrato;
    private javax.swing.JButton btnCalcCostoMonetario;
    private javax.swing.JButton btnCalcularExt;
    private javax.swing.JButton btnCargaCccOne;
    private javax.swing.JButton btnCargarCCCone;
    private javax.swing.JButton btnCargarCCCone1;
    private javax.swing.JButton btnCargarCCCone2;
    private javax.swing.JButton btnCopiarCorreo1;
    private javax.swing.JButton btnCopiarCorreo2;
    private javax.swing.JButton btnCopiarPlantilla;
    private javax.swing.JButton btnDescendente;
    private javax.swing.JButton btnDescendenteCargados1;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnGeneraPromesa;
    private javax.swing.JButton btnHipervinculoTec;
    private javax.swing.JButton btnLimpiaContrato;
    private javax.swing.JButton btnLimpiaExt;
    private javax.swing.JButton btnLimpiaMoratorio;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnWhatsapp;
    private javax.swing.JComboBox<String> cmbAlgoritmo;
    private javax.swing.JComboBox<String> cmbContratoTelefono;
    private javax.swing.JComboBox<String> cmbDiasPromesa;
    private javax.swing.JComboBox<String> cmbDpd;
    private javax.swing.JComboBox<String> cmbOpcCopiar;
    private javax.swing.JComboBox<String> cmbSelec;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblcontrato;
    private javax.swing.JTabbedPane tbl1;
    private javax.swing.JTable tblCargadosCCC;
    public javax.swing.JTable tblMacro;
    private javax.swing.JTable tblMsgWhatsApp;
    private javax.swing.JTable tblPromesadas;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtCapitalExt;
    private javax.swing.JTextField txtCapitalMoratorio;
    private javax.swing.JTextArea txtCliente;
    private javax.swing.JTextField txtContrato;
    private javax.swing.JTextField txtDiasMoratorio;
    private javax.swing.JTextField txtDpd;
    private javax.swing.JTextField txtExt10;
    private javax.swing.JTextField txtExt20;
    private javax.swing.JTextField txtExt30;
    private javax.swing.JTextField txtMoratorios;
    private javax.swing.JLabel txtRegistrosTotales;
    private javax.swing.JLabel txtRegistrosTotalesCargados;
    private javax.swing.JLabel txtRegistrosTotalesMsgWhats;
    private javax.swing.JLabel txtRegistrosTotalesPromesados;
    // End of variables declaration//GEN-END:variables
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
