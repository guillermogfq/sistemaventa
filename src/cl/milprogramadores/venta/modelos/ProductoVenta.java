/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.modelos;

import java.util.Objects;

/**
 *
 * @author SENCE VESPERTINO
 */
public class ProductoVenta {
    
    private Integer idProductoVenta;
    private Integer cantidad;
    private Producto idProducto;
    private Venta idVenta;

    public ProductoVenta(){
        
    }

    public ProductoVenta(Integer idProductoVenta, Integer cantidad, Producto idProducto, Venta idVenta) {
        this.idProductoVenta = idProductoVenta;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
    }

    public Integer getIdProductoVenta() {
        return idProductoVenta;
    }

    public void setIdProductoVenta(Integer idProductoVenta) {
        this.idProductoVenta = idProductoVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoVenta other = (ProductoVenta) obj;
        if (!Objects.equals(this.idProductoVenta, other.idProductoVenta)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.idProducto, other.idProducto)) {
            return false;
        }
        if (!Objects.equals(this.idVenta, other.idVenta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductoVenta{" + "idProductoVenta=" + idProductoVenta + ", cantidad=" + cantidad + ", idProducto=" + idProducto + ", idVenta=" + idVenta + '}';
    }
    
    public Object[] toArray(){
        Object[] array = {idProducto, idProductoVenta, cantidad
                , idVenta};
        return array;
    }
    
    
}
