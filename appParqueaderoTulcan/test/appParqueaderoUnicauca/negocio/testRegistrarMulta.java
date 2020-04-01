/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import negocio.GestorVehiculoPersona;
import negocio.Multa;
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
public class testRegistrarMulta {
    
    public testRegistrarMulta() {
    }

     @Test
     public void testRegMulta() {
         GestorVehiculoPersona gestor = new GestorVehiculoPersona();
         String placa = "abc-123";
         String descripcion= "Mal Parqueado";
         String fecha = "31/03/2020 12:18:11";
         String ruta = "C:/Users/Personal/Documents/IMG_20200124_183239-EFFECTS.jpg";
         Multa objMulta = new Multa(placa, descripcion, fecha, ruta);
         String resultMulta = gestor.regMultaVehiculo(objMulta);
         assertEquals("Multa agragada con exito.", resultMulta);   
     }
}
