/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.controller;

import com.digitalfinance.pp.view.GeneraPromesa;


public class ControllerPantallas {
    
    public void agregaPromesaPago(String agente){
        GeneraPromesa gp= new GeneraPromesa(agente);
        gp.setVisible(true);
        gp.setLocationRelativeTo(null);
        gp.setResizable(false);
    }
}
