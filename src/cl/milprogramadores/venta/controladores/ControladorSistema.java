/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.controladores;

import cl.milprogramadores.venta.vistas.VentaUI;
import cl.milprogramadores.venta.modelos.ProductoVenta;

/**
 *
 * @author guillermofuentesquijada
 */
public class ControladorSistema {

    private VentaUI ventaUI;

    public ControladorSistema() {
        ventaUI = new VentaUI();
    }

    public void iniciar() {
        ventaUI.setVisible(true);
    }   
}

public boolean registrarVenta() {
        boolean resultado = false;

        try {
            String query = "INSERT INTO PRODUCTOVENTA (idProductoVenta, cantidad, idProducto, idVenta) VALUES "
                    + "('" + idProductoVenta + "', '" + cantidad + "', '" + idVenta + "')";
            Statement registrarVenta = conn.createStatement();
            crearLibro.executeUpdate(query);
            resultado = true;
        } catch (SQLException ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }
