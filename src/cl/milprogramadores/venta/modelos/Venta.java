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

    
     public boolean guardar() {
        boolean resultado = true;
        Connection conn = Conexion.getConexion().getConn();
        if (this.idventa != null) {
            try {
                String query = "UPDATE " + NOMBRE_TABLA + " SET fecha = ?, idmediopago = ? WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setTimestamp(1, Timestamp.valueOf(this.fecha));
                sttm.setInt(2, this.mediopago.getIdmediopago());
                sttm.setInt(3, this.idventa);
                sttm.executeUpdate();

            } catch (SQLException ex) {
                resultado = false;
            }
        } else {
            try {
                String query = "INSERT INTO " + NOMBRE_TABLA + " (fecha, mediopago) VALUES (?,?) ";
                PreparedStatement sttm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                sttm.setTimestamp(1, Timestamp.valueOf(this.fecha));
                sttm.setInt(2, this.mediopago.getIdmediopago());
                sttm.executeUpdate();

                ResultSet result = sttm.getGeneratedKeys();
                if (result.next()) {
                    this.idventa = result.getInt(1);
                }

            } catch (SQLException ex) {
                resultado = false;
            }
        }

        return resultado;
    }
    
     public boolean borrar() {
        boolean resultado = true;

        if (this.idventa != null) {

            try {

                Connection conn = Conexion.getConexion().getConn();
                String query = "DELETE FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " =  ? ";
                PreparedStatement sttm = conn.prepareStatement(query);
                sttm.setInt(1, this.idventa);
                sttm.executeUpdate();

            } catch (SQLException ex) {
                resultado = false;
            }

        } else {
            resultado = false;
        }

        return resultado;
    }
     
     public static ArrayList<Venta> obtenerTodas() {
        ArrayList<Venta> lista;
        try {

            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " ORDER BY idventa";
            PreparedStatement sttm = conn.prepareStatement(query);
            ResultSet resultado = sttm.executeQuery();

            lista = new ArrayList<>();
            while (resultado.next()) {
                Integer id = resultado.getInt("idventa");
                Timestamp fecha = resultado.getTimestamp("fecha");
                Integer idmedioPago = resultado.getInt("medioPago");
                

                lista.add(new Venta(id, fecha, MedioPago.buscar(idmedioPago)));
            }

        } catch (SQLException ex) {
            lista = new ArrayList<>();
        }

        return lista;
    }
     
     public Object toArray() {
        Object[] array = {idventa, fecha, mediopago};
        return array;
    }
     
     public static Venta buscar(Integer idventa) {
        Venta venta = null;
        try {

            Connection conn = Conexion.getConexion().getConn();
            String query = "SELECT * FROM " + NOMBRE_TABLA + " WHERE " + CLAVE_PRIMARIA + " = ?";
            PreparedStatement sttm = conn.prepareStatement(query);
            sttm.setInt(1, idventa);
            ResultSet resultado = sttm.executeQuery();

            if (resultado.next()) {
                Integer id = resultado.getInt("idventa");
                Timestamp fecha = resultado.getTimestamp("fecha");
                Integer idmedioPago = resultado.getInt("medioPago");
                

                venta = new Venta(id, fecha, MedioPago.buscar(idmedioPago))
            }

        } catch (SQLException ex) {
            venta = null;
        }

        return venta;
    }

}
