/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.DAO.entity;

import java.sql.Date;


public class REF_GPT1 {
    private String number;//
    private String dpd;//
    private String clientFullName;//
    private String nombre;
    private String open_principal;
    private String speiClave;
    private String oxxoReference;
    private String extension20;
    private String extension30;
    private String saldoHoy;
    private String adeudo;//
    private Date fechaPago;
    private String tel;
    private String algoritmo;
    private String tipoCliente;
    private String correo;
    

    public REF_GPT1() {
    }

    public REF_GPT1(String number, String dpd, String clientFullName, String nombre, String open_principal, String speiClave, String oxxoReference, String extension20, String extension30, String saldoHoy, String adeudo, Date fechaPago, String tel, String algoritmo, String tipoCliente,String correo) {
        this.number = number;
        this.dpd = dpd;
        this.clientFullName = clientFullName;
        this.nombre = nombre;
        this.open_principal = open_principal;
        this.speiClave = speiClave;
        this.oxxoReference = oxxoReference;
        this.extension20 = extension20;
        this.extension30 = extension30;
        this.saldoHoy = saldoHoy;
        this.adeudo = adeudo;
        this.fechaPago = fechaPago;
        this.tel = tel;
        this.algoritmo = algoritmo;
        this.tipoCliente = tipoCliente;
        this.correo=correo;
    }

    public REF_GPT1(String number, String dpd, String clientFullName, String nombre, String speiClave, String oxxoReference, String adeudo, String tel, String algoritmo, String tipoCliente,String correo) {
        this.number = number;
        this.dpd = dpd;
        this.clientFullName = clientFullName;
        this.nombre = nombre;
        this.speiClave = speiClave;
        this.oxxoReference = oxxoReference;
        this.adeudo = adeudo;
        this.tel = tel;
        this.algoritmo = algoritmo;
        this.tipoCliente = tipoCliente;
        this.correo=correo;
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOpen_principal() {
        return open_principal;
    }

    public void setOpen_principal(String open_principal) {
        this.open_principal = open_principal;
    }

    public String getSpeiClave() {
        return speiClave;
    }

    public void setSpeiClave(String speiClave) {
        this.speiClave = speiClave;
    }

    public String getOxxoReference() {
        return oxxoReference;
    }

    public void setOxxoReference(String oxxoReference) {
        this.oxxoReference = oxxoReference;
    }

    public String getExtension20() {
        return extension20;
    }

    public void setExtension20(String extension20) {
        this.extension20 = extension20;
    }

    public String getExtension30() {
        return extension30;
    }

    public void setExtension30(String extension30) {
        this.extension30 = extension30;
    }

    public String getSaldoHoy() {
        return saldoHoy;
    }

    public void setSaldoHoy(String saldoHoy) {
        this.saldoHoy = saldoHoy;
    }

    public String getAdeudo() {
        return adeudo;
    }

    public void setAdeudo(String adeudo) {
        this.adeudo = adeudo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

        
}
