/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import java.util.ArrayList;
import negocio.GestorVehiculoPersona;
import negocio.Vehiculo;
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
public class testVehiculosPersona {
    
    public testVehiculosPersona() {
    }
    @Test
    public void testVehiculosPersona(){
        GestorVehiculoPersona gestor = new GestorVehiculoPersona();
        ArrayList<Vehiculo> result = gestor.BuscarVeh(87490761);
        assertEquals("hk2-123", result.get(0).getVehPlaca());
        assertEquals("Automovil", result.get(0).getVehTipo());
        assertEquals("Susuki", result.get(0).getVehMarca());
    }
}