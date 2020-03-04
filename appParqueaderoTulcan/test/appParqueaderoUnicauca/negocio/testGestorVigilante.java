/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import negocio.GestorUsuario;
import negocio.GestorVehiculoPersona;
import negocio.Persona;
import negocio.Vehiculo;
import negocio.Vigilante;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhayber
 */
public class testGestorVigilante {
    
    public testGestorVigilante() {
    }
    
    @Test
    public void testIniciarSesion(){
        GestorUsuario gestor = new GestorUsuario();
        Persona result = gestor.Iniciar_Sesion("clopez");
        assertEquals("clopez", result.getPerUsuario());
        assertEquals("123", result.getPerContrasenia());
        assertEquals("Carlos", result.getPerNombre());
        assertEquals("Lopez", result.getPerApellido());
        assertEquals("Vigilante", result.getPerRol());
    }
}