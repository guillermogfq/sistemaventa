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
import java.util.ArrayList;

/**
 *
 * @author guillermofuentesquijada
 */
public class Producto {
    //Constantes
    public static final String NOMBRE_TABLA = "producto";
    public static final String CLAVE_PRIMARIA = "idproducto";
    
    //Atributos
    private Integer idproducto;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Integer stock_min;

    public Producto(Integer idproducto, String nombre, String descripcion, Integer stock, Integer stock_min) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stock_min = stock_min;
    }

    public Producto(String nombre, String descripcion, Integer stock, Integer stock_min) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stock_min = stock_min;
    }
    
    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock_min() {
        return stock_min;
    }

    public void setStock_min(Integer stock_min) {
        this.stock_min = stock_min;
    }
    
    public Precio getPrecio(){
        Precio precio = null;
        
        if(this.idproducto != null){
            
            try{
                
                Connection conn = Conexion.getConexion().getConn();
                String query = "SELECT precio.* FROM precio JOIN producto ON precio.idproducto = producto.idproducto WHERE producto.idproducto = ? ORDER BY precio.fecha DESC";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(1, this.idproducto);
                ResultSet resultado = sttm.executeQuery();
                if(resultado.next()){
                    Integer id = resultado.getInt("idprecio");
                    Integer monto = resultado.getInt("monto");
                    Timestamp fecha = resultado.getTimestamp("fecha");
                    precio = new Precio(id, monto, fecha.toLocalDateTime(), this);
                }
                
            } catch (SQLException ex) {
                precio = null;
            }
            
        }else{
            precio = null;
        }
        return precio;
    }
    
    public void setPrecio(Integer monto){
        
    }
    
    public boolean guardar(){
        boolean resultado = true;
        Connection conn = Conexion.getConexion().getConn();
        if(this.idproducto != null){
            try{
                String query = "UPDATE " + NOMBRE_TABLA + " SET nombre = ?, descripcion = ?, stock = ?, stock_min = ? WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setString(1, this.nombre);
                sttm.setString(2, this.descripcion);
                sttm.setInt(3, this.stock);
                sttm.setInt(4, this.stock_min);
                sttm.setInt(5, this.idproducto);
                sttm.executeUpdate();
                
            } catch (SQLException ex) {
                resultado = false;
            }
        }else{
            try{
                String query = "INSERT INTO " + NOMBRE_TABLA + " (nombre, descripcion, stock, stock_min) VALUES (?,?,?,?) ";
                PreparedStatement sttm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                sttm.setString(1, this.nombre);
                sttm.setString(2, this.descripcion);
                sttm.setInt(3, this.stock);
                sttm.setInt(4, this.stock_min);
                sttm.executeUpdate();
                
                ResultSet result = sttm.getGeneratedKeys();
                if(result.next()){
                    this.idproducto = result.getInt(1);
                }
                
            } catch (SQLException ex) {
                resultado = false;
            }
        }
        
        return resultado;
    }
    
    public boolean borrar(){
        boolean resultado = true;
        
        if(this.idproducto != null){
            
            try{
                
                Connection conn = Conexion.getConexion().getConn();
                String query = "DELETE FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(1, this.idproducto);
                sttm.executeUpdate();
                
            } catch (SQLException ex) {
                resultado = false;
            }
            
        }else{
            resultado = false;
        }
                
        return resultado;
    }
    
    public static ArrayList<Producto> obtenerTodos(){
        ArrayList<Producto> lista;
        try{
                
            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " ORDER BY idproducto";
            PreparedStatement sttm = conn.prepareStatement(query);
            ResultSet resultado = sttm.executeQuery();
            
            lista = new ArrayList<>();
            while(resultado.next()){
                Integer id = resultado.getInt("idproducto");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                Integer stock = resultado.getInt("stock");
                Integer stock_min = resultado.getInt("stock_min");
                
                lista.add(new Producto(stock, nombre, descripcion, stock, stock_min));
            }

        } catch (SQLException ex) {
            lista = new ArrayList<>();
        }
        
        return lista;
    }
    
    public Object toArray() {
        Object[] array = {idproducto, nombre, descripcion, stock, stock_min, getPrecio()};
        return array;
    }    
    
}
