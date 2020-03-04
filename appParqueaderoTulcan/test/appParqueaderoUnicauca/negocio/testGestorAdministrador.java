/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import negocio.GestorUsuario;
import negocio.Persona;
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
public class testGestorAdministrador {
    
    public testGestorAdministrador() {
    }
    
    @Test
    public void testPersona(){
        GestorUsuario gestor = new GestorUsuario();
        Persona result = gestor.Iniciar_Sesion("jmelo");
        assertEquals("jmelo", result.getPerUsuario());
        assertEquals("123", result.getPerContrasenia());
        assertEquals("Jhayber", result.getPerNombre());
        assertEquals("Melo", result.getPerApellido());
        assertEquals(1082749003, result.getPerIdentificacion());
        assertEquals("Administrador", result.getPerRol());
    }
}
