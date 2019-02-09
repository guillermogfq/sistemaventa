/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.modelos;

import cl.milprogramadores.venta.coneccion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author guillermofuentesquijada
 */
public class Precio {
    //Constantes
    public static final String NOMBRE_TABLA = "ventaproducto";
    public static final String CLAVE_PRIMARIA = "idventaproducto";
    
    //Atributos
    private Integer idprecio;
    private Integer monto;
    private LocalDateTime fecha;
    private Producto producto;

    public Precio(Integer idprecio, Integer monto, LocalDateTime fecha, Producto producto) {
        this.idprecio = idprecio;
        this.monto = monto;
        this.fecha = fecha;
        this.producto = producto;
    }

    public Precio(Integer monto, LocalDateTime fecha, Producto producto) {
        this.monto = monto;
        this.fecha = fecha;
        this.producto = producto;
    }

    public Integer getIdprecio() {
        return idprecio;
    }

    public void setIdprecio(Integer idprecio) {
        this.idprecio = idprecio;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }
    
    public boolean guardar(){
        boolean resultado = true;
        Connection conn = Conexion.getConexion().getConn();
        if(this.idprecio != null){
            try{
                String query = "UPDATE " + NOMBRE_TABLA + " SET monto = ?, fecha = ? WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(5, this.idprecio);
                sttm.executeUpdate();
                
            } catch (SQLException ex) {
                resultado = false;
            }
        }else{
            try{
                String query = "INSERT INTO " + NOMBRE_TABLA + " (monto, fecha, idproducto) VALUES (?,?,?) ";
                PreparedStatement sttm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                sttm.setInt(1, this.monto);
                sttm.setTimestamp(2, Timestamp.valueOf(this.fecha));
                sttm.setInt(3, this.producto.getIdproducto());
                sttm.executeUpdate();
                
                ResultSet result = sttm.getGeneratedKeys();
                if(result.next()){
                    this.idprecio= result.getInt(1);
                }
                
            } catch (SQLException ex) {
                resultado = false;
            }
        }
        
        return resultado;
    }
    
    public boolean borrar(){
        boolean resultado = true;
        
        if(this.idprecio != null){
            
            try{
                
                Connection conn = Conexion.getConexion().getConn();
                String query = "DELETE FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(1, this.idprecio);
                sttm.executeUpdate();
                
            } catch (SQLException ex) {
                resultado = false;
            }
            
        }else{
            resultado = false;
        }
                
        return resultado;
    }
    
    
    
}
