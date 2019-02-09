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
       
    private VentaUI ventaUI;
    private MediosPagoUI mediopagoUI;

    public ControladorSistema() {
        conn = Conexion.getConexion().getConn();
        ventaUI = new VentaUI();
        mediopagoUI =  new MediosPagoUI();
        
    }
    
    public ArrayList<MediosPagoUI> iniciar(){
        ventaUI.setVisible(true);
        mediopagoUI.setVisible(true);
    
} 
    /**
     *
     * @return
     */
    public ArrayList<MedioPago> getMedioPago() {
        
    
        ArrayList<MedioPago> listaMedioPago = new ArrayList<>();
        try {
            String query = "SELECT * FROM MEDIOPAGO";
            //Un Statement es quien le consulta la query a la base de datos
            Statement consultador = conn.createStatement();
            //Un ResultSet es el objeto que tiene el resultado de un select
            ResultSet resultadoSelect = consultador.executeQuery(query);
            //Obtener datos adicionales de una select: nombres columnas, numero de columnas, etc
            ResultSetMetaData metadata = resultadoSelect.getMetaData();

            while (resultadoSelect.next()) {
                Integer idmediopago = resultadoSelect.getInt("idmediopago");
                String nombre = resultadoSelect.getString("nombre");
                String descripcion = resultadoSelect.getString("descripcion");
                MedioPago var = new MedioPago(idmediopago, nombre, descripcion);
                listaMedioPago.add(var);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMedioPago;
    }
//    
//    public boolean eliminarMetodoPado(Integer idmediopago){
//        boolean resultado = false;
//        
//        try{
//            String query = "DELETE FROM mediopago WHERE idmediopago = " + idmediopago.toString();
//            
//            Statement eliminador = conn.createStatement();
//            eliminador.executeUpdate(query);
//            
//            resultado = true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return resultado;
//    }
//    
//    public boolean crearMedioPago(String nombre, String descripcion) {
//        boolean resultado = false;
//
//        try {
//            String query = "INSERT INTO mediopago (nombre, descripcion) VALUES " +
//                "('" + nombre + "', " + descripcion.toString()+ ")";
//            Statement creador = conn.createStatement();
//            creador.executeUpdate(query);
//            resultado = true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return resultado;
//    }
//
//    public boolean cambiarMedioPago(Integer idmediopago, String nombre, String descripcion) {
//        boolean resultado = false;
//        try {
//            String query = "UPDATE mediopago ";
//            if (nombre != null && descripcion != null) {
//                query += "SET nombre = '" + nombre + "', descripcion = " + descripcion.toString()
//                        + " WHERE idmediopago = " + idmediopago.toString();
//            } else if (nombre != null && descripcion == null) {
//                query += "SET nombre = '" + nombre
//                        + "' WHERE idmediopago = " + idmediopago.toString();
//            } else {
//                query += "SET descripcion = " + descripcion.toString()
//                        + " WHERE idmediopago = " + idmediopago.toString();
//            }
//
//            Statement actualizador = conn.createStatement();
//            actualizador.executeUpdate(query);
//            
//            resultado = true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return resultado;
//    }
    
    
}
