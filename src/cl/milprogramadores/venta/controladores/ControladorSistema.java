/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.controladores;

import cl.milprogramadores.venta.coneccion.Conexion;
import cl.milprogramadores.venta.modelos.MedioPago;
import cl.milprogramadores.venta.vistas.MediosPagoUI;
import cl.milprogramadores.venta.vistas.VentaUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guillermofuentesquijada
 */
public class ControladorSistema {
    private Connection conn;
       
    private final VentaUI ventaUI;

    public ControladorSistema() {
        conn = Conexion.getConexion().getConn();
        ventaUI = new VentaUI();
        
    }
    
    public void iniciar(){
        ventaUI.setVisible(true);
    } 
     
}
