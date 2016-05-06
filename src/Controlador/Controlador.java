
package Controlador;

import Modelo.Modelo;
import Vista.Interfaz;


class Controlador {

    Interfaz vista; //PARA PODER ACCEDER A LA INTERFAZ DE LA APLICACIÓN
    Modelo modelo; //PARA PODER ACCEDER AL MODELO Y A LOS DATOS DE LA BD

    public Controlador(Interfaz i) {
        vista = i;
    }
    
    public void iniciar() {
        
    }
    
}
