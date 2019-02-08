/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.milprogramadores.venta.controladores;

import cl.milprogramadores.venta.vistas.MedioPagoUI;
import cl.milprogramadores.venta.vistas.VentaUI;

/**
 *
 * @author guillermofuentesquijada
 */
public class ControladorSistema {
    
    private VentaUI ventaUI;
    private MedioPagoUI mediopagoUI;

    public ControladorSistema() {
        ventaUI = new VentaUI();
        mediopagoUI = new MedioPagoUI();
    }
    
    public void iniciar(){
        ventaUI.setVisible(true);
        mediopagoUI.setVisible(true);
       
        
    
    }
}
