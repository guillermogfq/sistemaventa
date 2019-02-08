/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.modelos;

/**
 *
 * @author guillermofuentesquijada
 */
public class MedioPago {
    //Constantes
    public static final String NOMBRE_TABLA = "mediopago";
    public static final String CLAVE_PRIMARIA = "idmediopago";
    
    //Atributos
    private Integer idmediopago;
    private String nombre;
    private String descripcion;

    public MedioPago(Integer idmediopago, String nombre, String descripcion) {
        this.idmediopago = idmediopago;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdmediopago() {
        return idmediopago;
    }

    public void setIdmediopago(Integer idmediopago) {
        this.idmediopago = idmediopago;
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
    
    public boolean guardar(){
        return false;
    }
    
    public boolean borrar(){
        return false;
    }
}
