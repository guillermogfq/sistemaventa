/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.coneccion;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author guillermofuentesquijada
 */
public class Conexion {
    private Connection conn;
    private static Conexion _this;

    private final String tableName = "u531602409_venta";
    private final String host = "";
    private final String userDB = "";
    private final String passDB = "";

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(userDB);
            dataSource.setPassword(passDB);
            dataSource.setServerName(host);
            dataSource.setAutoReconnect(true);
            dataSource.setAutoReconnectForPools(true);
            dataSource.setDatabaseName(tableName); 
            conn = dataSource.getConnection();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error, no encontramos los archivos necesarios para iniciar.", "Sistema Venta", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ups... al parecer no cuentas con conexión a internet.", "Sistema Venta", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public static Conexion getConexion() {
        if (_this == null) {
            _this = new Conexion();
        } else {

        }
        return _this;
    }

    public Connection getConn() {
        return conn;
    }

    public void closeConn() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
