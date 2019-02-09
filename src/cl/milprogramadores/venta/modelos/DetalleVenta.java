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
import java.util.ArrayList;

/**
 *
 * @author guillermofuentesquijada
 */
public class DetalleVenta {

    //Constantes
    public static final String NOMBRE_TABLA = "productoventa";
    public static final String CLAVE_PRIMARIA = "idproductoventa";

    //Atributos
    private Integer idproductoventa;
    private Integer cantidad;
    private Producto producto;
    private Venta venta;

    public DetalleVenta(Integer idproductoventa, Integer cantidad, Producto producto, Venta venta) {
        this.idproductoventa = idproductoventa;
        this.cantidad = cantidad;
        this.producto = producto;
        this.venta = venta;
    }

    public DetalleVenta(Integer cantidad, Producto producto, Venta venta) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.venta = venta;
    }

    
    public Integer getIdproductoventa() {
        return idproductoventa;
    }

    public void setIdproductoventa(Integer idproductoventa) {
        this.idproductoventa = idproductoventa;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto idproducto) {
        this.producto = idproducto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta idventa) {
        this.venta = idventa;
    }

    public boolean guardar() {
        boolean resultado = true;
        Connection conn = Conexion.getConexion().getConn();
        if (this.idproductoventa != null) {
            try {
                String query = "UPDATE " + NOMBRE_TABLA + "SET idproducto = ?, idventa = ?, cantidad = ? WHERE " + CLAVE_PRIMARIA + " = ?";
                PreparedStatement stmm = conn.prepareStatement(query);
                stmm.setInt(1, this.producto.getIdproducto());
                stmm.setInt(2, this.venta.getIdventa());
                stmm.setInt(3, this.cantidad);
                stmm.setInt(4, this.idproductoventa);

            } catch (SQLException ex) {
                resultado = false;
            }

        } else {
            try {
                String query = "INSERT INTO " + NOMBRE_TABLA + " (idproducto, idventa, cantidad) VALUES (?,?,?) ";
                PreparedStatement sttm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                sttm.setInt(1, this.producto.getIdproducto());
                sttm.setInt(2, this.venta.getIdventa());
                sttm.setInt(3, this.cantidad);
                sttm.executeUpdate();

                ResultSet result = sttm.getGeneratedKeys();
                if (result.next()) {
                    this.idproductoventa = result.getInt(1);
                }
            } catch (SQLException ex) {
                resultado = false;
            }
        }
        return resultado;
    }

    public boolean borrar() {
        boolean resultado = true;

        if (this.idproductoventa != null) {

            try {
                Connection conn = Conexion.getConexion().getConn();
                String query = "DELETE FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " = ?";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(1, this.idproductoventa);
                sttm.executeUpdate();
            } catch (SQLException ex) {
                resultado = false;
            }

        } else {
            resultado = false;
        }

        return resultado;
    }
    
    public static ArrayList<DetalleVenta> obtenerTodos(){
        ArrayList<DetalleVenta> lista;
        try{
            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " ORDER BY idproductoventa";
            PreparedStatement sttm = conn.prepareStatement(query);
            ResultSet resultado = sttm.executeQuery();
            
            lista = new ArrayList<>();
            while(resultado.next()){
                Integer id = resultado.getInt("idproductoventa");
                Integer producto = resultado.getInt("idproducto");
                Integer venta = resultado.getInt("idventa");   
                Integer cantidad = resultado.getInt("cantidad");
                
                lista.add(new DetalleVenta(cantidad, Producto.buscar(producto), Venta.buscar(venta)));
            }
            
        } catch (SQLException ex) {
            lista = new ArrayList<>();
        }
        return lista;
    }
    
    public static DetalleVenta buscar (Integer idproductoventa){
        DetalleVenta detalleVenta = null;
        try{
            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " = ?";
            PreparedStatement sttm = conn.prepareStatement(query);
            sttm.setInt(1, idproductoventa);
            ResultSet resultado = sttm.executeQuery();
            
            if(resultado.next()){
                Integer id = resultado.getInt("idproductoventa");
                Integer producto = resultado.getInt("idproducto");
                Integer venta = resultado.getInt("idventa");   
                Integer cantidad = resultado.getInt("cantidad");
                
                detalleVenta = new DetalleVenta(cantidad, Producto.buscar(producto), Venta.buscar(venta));
            }
        } catch (SQLException ex) {
            detalleVenta = null;
        }
        return detalleVenta;
    }
    
}
