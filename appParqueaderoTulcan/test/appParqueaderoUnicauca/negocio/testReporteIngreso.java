/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import java.util.ArrayList;
import negocio.GestorParqueadero;
import negocio.Reporte;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Personal
 */
public class testReporteIngreso {
    
    public testReporteIngreso() {
    }
     @Test
     public void testReporte() {
         GestorParqueadero gestor = new GestorParqueadero();
         ArrayList<Reporte> result = gestor.reporteIngresoVeh("abc-123");
         assertEquals("abc-123", result.get(0).getPlaca());
         assertEquals("1", result.get(0).getCantidad());
         assertEquals("13", result.get(0).getFecha());
     }    
}
