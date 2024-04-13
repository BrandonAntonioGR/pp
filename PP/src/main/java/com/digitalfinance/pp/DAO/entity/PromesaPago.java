/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.DAO.entity;

import java.sql.Date;

public class PromesaPago {
    private String contract;
    private String dpd;
    private String Algorithm;
    private String contactDate;
    private String promiseDate;
    private String promiseLimitDate;
    private double promiseAmount;
    private String agente;
    private String contact;
    private String contactChannel;
    private String channel;
    private String promiseType;
    private String amountType;
    private String comentarios;

    public PromesaPago() {
    }

    public PromesaPago(String contract, String dpd, String Algorithm, String contactDate, String promiseDate, String promiseLimitDate, double promiseAmount, String agente, String contact, String contactChannel, String channel, String promiseType, String amountType, String comentarios) {
        this.contract = contract;
        this.dpd = dpd;
        this.Algorithm = Algorithm;
        this.contactDate = contactDate;
        this.promiseDate = promiseDate;
        this.promiseLimitDate = promiseLimitDate;
        this.promiseAmount = promiseAmount;
        this.agente = agente;
        this.contact = contact;
        this.contactChannel = contactChannel;
        this.channel = channel;
        this.promiseType = promiseType;
        this.amountType = amountType;
        this.comentarios = comentarios;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getAlgorithm() {
        return Algorithm;
    }

    public void setAlgorithm(String Algorithm) {
        this.Algorithm = Algorithm;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
    }

    public String getPromiseLimitDate() {
        return promiseLimitDate;
    }

    public void setPromiseLimitDate(String promiseLimitDate) {
        this.promiseLimitDate = promiseLimitDate;
    }

    public double getPromiseAmount() {
        return promiseAmount;
    }

    public void setPromiseAmount(double promiseAmount) {
        this.promiseAmount = promiseAmount;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactChannel() {
        return contactChannel;
    }

    public void setContactChannel(String contactChannel) {
        this.contactChannel = contactChannel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPromiseType() {
        return promiseType;
    }

    public void setPromiseType(String promiseType) {
        this.promiseType = promiseType;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
