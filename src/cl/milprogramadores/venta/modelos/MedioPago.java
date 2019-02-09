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

    public boolean guardarMedioPago() {
        boolean resultado = true;
        Connection conn = Conexion.getConexion().getConn();
        if (this.idmediopago != null) {
            try {
                String query = "UPDATE " + NOMBRE_TABLA + " SET nombre = ?, descripcion = ? WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setString(1, this.nombre);
                sttm.setString(2, this.descripcion);
                sttm.executeUpdate();

            } catch (SQLException ex) {
                resultado = false;
            }
        } else {
            try {
                String query = "INSERT INTO " + NOMBRE_TABLA + " (nombre, descripcion) VALUES (?,?) ";
                PreparedStatement sttm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                sttm.setString(1, this.nombre);
                sttm.setString(2, this.descripcion);
                sttm.executeUpdate();

                ResultSet result = sttm.getGeneratedKeys();
                if (result.next()) {
                    this.idmediopago = result.getInt(1);
                }

            } catch (SQLException ex) {
                resultado = false;
            }
        }

        return resultado;
    }

    public boolean borrar() {
        boolean resultado = true;

        if (this.idmediopago != null) {

            try {

                Connection conn = Conexion.getConexion().getConn();
                String query = "DELETE FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(1, this.idmediopago);
                sttm.executeUpdate();

            } catch (SQLException ex) {
                resultado = false;
            }

        } else {
            resultado = false;
        }

        return resultado;
    }

    public static ArrayList<MedioPago> obtenerTodos(){
        ArrayList<MedioPago> listaMedioPago;
        try{
                
            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " ORDER BY idmediopago";
            PreparedStatement sttm = conn.prepareStatement(query);
            ResultSet resultado = sttm.executeQuery();
            
            listaMedioPago = new ArrayList<>();
            while(resultado.next()){
                Integer id = resultado.getInt("idmediopago");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                              
                listaMedioPago.add(new MedioPago(id, nombre, descripcion));
            }

        } catch (SQLException ex) {
            listaMedioPago = new ArrayList<>();
        }
        
        return listaMedioPago;
    }
    
    public Object[] toArray() {
        Object[] array = {idmediopago, nombre, descripcion};
        return array;
    }

    @Override
    public String toString() {
        return "MedioPago{" + "idmediopago=" + idmediopago + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    public static MedioPago buscar(Integer idm){
       MedioPago medio = null;
        try{
                
            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " = ?";
            PreparedStatement sttm = conn.prepareStatement(query);
            sttm.setInt(1, idm);
            ResultSet resultado = sttm.executeQuery();
            
            if(resultado.next()){
                Integer id = resultado.getInt("idmediopago");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                medio = new MedioPago(id, nombre, descripcion);
            }

        } catch (SQLException ex) {
            medio = null;
        }
        
        return medio;
    }

}

