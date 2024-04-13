/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.DAO.model;

import com.digitalfinance.pp.DAO.entity.PromesaPago;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class InsertaPromesaPago {
    public static boolean RegistrarPromesaPago(PromesaPago promesaPago){
        PreparedStatement ps;
        boolean retorno = false;
        Connection con;
        try {
            con = ModelConection.getConnection();
            ps=con.prepareStatement("INSERT INTO [DC_PromesasPagoJava] "
                    + "([contract],[dpd],[Algorithm],[contactDate],"
                    + "[promiseDate],[promiseLimitDate],[promiseAmount],"
                    + "[agent],[contact],[contactChannel],[channel],"
                    + "[promiseType],[amountType],[comentarios])"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, promesaPago.getContract());
            ps.setString(2, promesaPago.getDpd());
            ps.setString(3, promesaPago.getAlgorithm());
            ps.setString(4, promesaPago.getContactDate());
            ps.setString(5, promesaPago.getPromiseDate());
            ps.setString(6, promesaPago.getPromiseLimitDate());
            ps.setDouble(7, promesaPago.getPromiseAmount());
            ps.setString(8, promesaPago.getAgente());
            ps.setString(9, promesaPago.getContact());
            ps.setString(10, promesaPago.getContactChannel());
            ps.setString(11, promesaPago.getChannel());
            ps.setString(12, promesaPago.getPromiseType());
            ps.setString(13, promesaPago.getAmountType());
            ps.setString(14, promesaPago.getComentarios());
            
            retorno = Boolean.parseBoolean(Integer.toString(ps.executeUpdate()));
            con.close();
            ps.close();
            JOptionPane.showMessageDialog(null, "¡Promesa de pago guardada exitosamente!", "Exito", JOptionPane.INFORMATION_MESSAGE);
            return retorno;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Los comentarios exceden los 1000 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Error: " +e, "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: "+e);
            return retorno;
        }
    }   
}
