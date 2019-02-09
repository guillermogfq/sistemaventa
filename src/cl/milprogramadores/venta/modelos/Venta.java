/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author guillermofuentesquijada
 */
public class Venta {
    //Constantes
    public static final String NOMBRE_TABLA = "venta";
    public static final String CLAVE_PRIMARIA = "idventa";
    
    //Atributos
    private Integer idventa;
    private LocalDateTime fecha;
    private MedioPago mediopago;

    public Venta(Integer idventa, LocalDateTime fecha, MedioPago mediopago) {
        this.idventa = idventa;
        this.fecha = fecha;
        this.mediopago = mediopago;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MedioPago getMediopago() {
        return mediopago;
    }

    public void setMediopago(MedioPago mediopago) {
        this.mediopago = mediopago;
    }
    
    public DetalleVenta getDetalle(){
        return null;
    }
    
    public boolean guardar(){
        return false;
    }
    
    public boolean borrar(){
        return false;
    }
    
    public static ArrayList<Venta> obtnerTodos(){
        return null;
    }
    
    public static Venta buscar(Integer id){
        return null;
    }
}
