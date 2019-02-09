/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.modelos;

import cl.milprogramadores.venta.coneccion.Conexion;
import java.sql.Connection;

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
    private Producto idproducto;
    private Venta idventa;

    public DetalleVenta(Integer idproductoventa, Integer cantidad, Producto idproducto, Venta idventa) {
        this.idproductoventa = idproductoventa;
        this.cantidad = cantidad;
        this.idproducto = idproducto;
        this.idventa = idventa;
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

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Venta getIdventa() {
        return idventa;
    }

    public void setIdventa(Venta idventa) {
        this.idventa = idventa;
    }

   
    
    public boolean guardar(){
        boolean resultado = true;
        Connection conn = Conexion.getConexion().getConn();
        if(this.idproductoventa != null){
            String query = "UPDATE " + NOMBRE_TABLA + "SET idproducto = ?, idventa = ?, cantidad = ? WHERE " + CLAVE_PRIMARIA + " = ?"; 
        }else{
            
        }
        return false;
    }
    
    public boolean borrar(){
        return false;
    }
}
