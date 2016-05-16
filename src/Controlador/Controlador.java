
package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


class Controlador {

    Interfaz vista ; //PARA PODER ACCEDER A LA INTERFAZ DE LA APLICACIÓN
    Modelo modelo = new Modelo(); //PARA PODER ACCEDER AL MODELO Y A LOS DATOS DE LA BD

    public Controlador(Interfaz vista) {
        this.vista = vista;
        
    }
    
    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        this.vista.btnagregar.setActionCommand("_btnagregar");
        this.vista.btnagregar.addActionListener((ActionListener) this);
        
        this.vista.btnbuscar.setActionCommand("_btnbuscar");
        this.vista.btnbuscar.addActionListener((ActionListener) this);
        
        this.vista.btneliminar.setActionCommand("_btneliminar");
        this.vista.btneliminar.addActionListener((ActionListener) this);
        
        
        
        
        
        
        
        
        
        
    }
    public enum AccionMVC
    {
        __btnagregar,
        __btnbuscar,
        __btneliminar,
    }
    
}
