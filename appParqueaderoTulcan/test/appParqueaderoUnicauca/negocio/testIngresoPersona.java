/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appParqueaderoUnicauca.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import negocio.Bahia;
import negocio.GestorParqueadero;
import negocio.GestorVehiculoPersona;
import negocio.Persona;
import negocio.Vehiculo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 *
 * @author jhayber
 */
public class testIngresoPersona {
    
    public testIngresoPersona() {
    }
    
    @Test
    public void testIngreso(){
        /*
        1) Se verifica que la persona a ingresar este en la BD
        */
        GestorVehiculoPersona testPersona = new GestorVehiculoPersona();
        Persona resultPersona  = testPersona.buscarUsuarioDocumento(1002);
        
        assertEquals(1002, resultPersona.getPerIdentificacion());
        assertEquals(1046170, resultPersona.getCodCarne());
        assertEquals("Estudiante", resultPersona.getPerRol());
        assertEquals("Brayan Daniel", resultPersona.getPerNombre());
        /*
        2) Se mira si tiene Vehiculos asociados
        */
        ArrayList<Vehiculo> resultVehiculo = testPersona.BuscarVeh(1002);
        
        assertEquals("NBQ-17E", resultVehiculo.get(0).getVehPlaca());
        assertEquals(1002, resultVehiculo.get(0).getPerIdentificacion());
        /*
        3) Se Registra un al usuario en una bahia
        */
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaEntrada = Integer.toString(año) + "/" + Integer.toString(mes) + "/" + Integer.toString(dia);
        GestorParqueadero testParqueadero = new GestorParqueadero();
        String resultParqueadero = testParqueadero.registrarIngreso("NBQ-17E", "1", fechaEntrada, fechaEntrada);
        assertEquals("Ingreso Registrado con exito", resultParqueadero);
        /*
        4) Revisar que haya ingresado correctamente
        */
        ArrayList<Bahia> resultBahia = testParqueadero.Buscar_Bahias();  
        assertEquals("1", resultBahia.get(0).getBahId());
        assertEquals("1", resultBahia.get(0).getBahEstado());
        /*
        5) Por ultimo validamos la Salida del parqueadero
        */        
        resultParqueadero = testParqueadero.registrarSalida(1, fechaEntrada);
        
        assertEquals("Salida Confirmada", resultParqueadero);
    }
}
