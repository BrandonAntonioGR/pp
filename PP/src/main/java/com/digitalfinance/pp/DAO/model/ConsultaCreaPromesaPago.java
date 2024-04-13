/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.DAO.model;

import com.digitalfinance.pp.DAO.entity.Macro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Brandon Garduño
 */
public class ConsultaCreaPromesaPago {
    public static Macro consultaCreaPromesa(String contrato){
        Connection con;
        PreparedStatement ps;
        try{
            con=ModelConection.getConnection();
            ps=con.prepareStatement("use [DC REPORT]\n" +
                "SELECT TOP 1\n" +
                "a.Creation_Day\n" +
                " ,r.DPD  AS DPD\n" +
                " ,r.open_principal  AS OPENPRINCIPAL\n" +
                " ,r.NOMBRE AS NOMBRE\n" +
                " ,r.number AS CONTRATO \n" +
                " ,r.Fecha_Pago  AS FECHAPAGO\n" +
                " ,r.SPEI_CLABE  AS SPEI\n" +
                " ,r.OXXOReference  AS OXXO\n" +
                " ,CEILING(r.EXTENSION_20)  AS EXTENSION_20\n" +
                " ,CEILING(r.EXTENSION_30)  AS EXTENSION_30\n" +
                " ,CEILING(r.Saldo_hoy)  AS Saldo_hoy\n" +
                " ,CEILING(r.Adeudo)  AS Adeudo\n" +
                " ,a.ALGORITMO AS ALGORITMO\n" +
                " FROM dm_REF_GPT1 AS r \n" +
                " LEFT JOIN DC_HIS_ASIG AS a  \n" +
                " ON  a.CONTRATO=r.number  \n" +
                " where r.number='"+contrato+"'\n" +
                "AND a.[CONTRATO] NOT IN (\n" +
                "SELECT  [ContractNumber] FROM [DC REPORT].[dbo].[PaymentPromises]  WHERE CONVERT(DATE,GETDATE()) between CONVERT(DATE,[ContactDate]) and CONVERT(DATE,[PromiseLimitDate])\n" +
                "UNION\n" +
                "SELECT [Contrato] FROM [DC REPORT].[dbo].[CurrentDatePayment]\n" +
                "UNION\n" +
                "SELECT [number] FROM [DC REPORT].[dbo].[dm_PJava]\n" +
                "UNION\n" +
                "SELECT [loan_id] FROM [DC REPORT].[dbo].[MR_Payments] WHERE CONVERT(DATE,[booking_date]) BETWEEN CONVERT(DATE,DATEADD(DAY, -5, GETDATE())) AND CONVERT(DATE,GETDATE())\n" +
                "UNION\n" +
                "SELECT [contract] FROM [DC_PromesasPagoJava] WHERE CONVERT(DATE,GETDATE()) between CONVERT(DATE,[contactDate]) and CONVERT(DATE,[promiseLimitDate])\n" +
                ")");
            ResultSet rs=ps.executeQuery();
            Macro macro=new Macro();
            while(rs.next()){
                macro.setDpd(rs.getString("DPD"));
                macro.setExtension20(rs.getString("EXTENSION_20"));
                macro.setExtension30(rs.getString("EXTENSION_30"));
                macro.setSaldoHoy(rs.getString("Saldo_hoy"));
                macro.setOpen_principal(rs.getString("OPENPRINCIPAL"));
                macro.setAdeudo(rs.getString("ADEUDO"));               
                macro.setAlgoritmo(rs.getString("ALGORITMO"));
                macro.setNombre(rs.getString("NOMBRE"));
                macro.setNumber(rs.getString("CONTRATO"));
                macro.setFechaPago(rs.getDate("FECHAPAGO"));
                macro.setSpeiClave(rs.getString("SPEI"));
                macro.setOxxoReference(rs.getString("OXXO"));
            }
            JOptionPane.showMessageDialog(null, "Promesa disponible continue llenando el formulario", "Exito", JOptionPane.INFORMATION_MESSAGE);
            cerrarConexion(con, ps, rs);
            return macro;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en ConsultaCreaPromesasPago:"+e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }        
    }
    
    public static void cerrarConexion(Connection con,PreparedStatement ps,ResultSet rs) throws SQLException{
        con.close();
        ps.close();
        rs.close();
    }    
}
