/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.controller;

import com.digitalfinance.pp.DAO.entity.PromesaPago;
import com.digitalfinance.pp.DAO.model.InsertaPromesaPago;

/**
 *
 * @author Brandon Garduño
 */
public class ControllerInsertarPromesaPago {
    public static boolean CargarPromesaPago(PromesaPago promesaPago){
        return InsertaPromesaPago.RegistrarPromesaPago(promesaPago);
    }
}
