/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.DAO.entity;

/**
 *
 * @author Brandon Garduño
 */
public class Plantilla {
    private int indicePlantilla;
    private String nombrePlantilla;
    private String descPlantilla;
    private String agente;

    public Plantilla() {
    }

    public Plantilla(int indicePlantilla, String nombrePlantilla, String descPlantilla, String agente) {
        this.indicePlantilla = indicePlantilla;
        this.nombrePlantilla = nombrePlantilla;
        this.descPlantilla = descPlantilla;
        this.agente = agente;
    }

    public int getIndicePlantilla() {
        return indicePlantilla;
    }

    public void setIndicePlantilla(int indicePlantilla) {
        this.indicePlantilla = indicePlantilla;
    }

    public String getNombrePlantilla() {
        return nombrePlantilla;
    }

    public void setNombrePlantilla(String nombrePlantilla) {
        this.nombrePlantilla = nombrePlantilla;
    }

    public String getDescPlantilla() {
        return descPlantilla;
    }

    public void setDescPlantilla(String descPlantilla) {
        this.descPlantilla = descPlantilla;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

        
}
