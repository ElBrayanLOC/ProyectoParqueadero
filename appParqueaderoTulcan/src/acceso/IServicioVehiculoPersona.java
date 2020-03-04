/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import negocio.Persona;
import negocio.Vehiculo;

/**
 *
 * @author Personal
 */
public interface IServicioVehiculoPersona {
    public String registrarVehiculo(Vehiculo prmVehiculo);
    public String registrarPersona(Persona prmPersona);
    public String buscarUsuarioDocumento(int prmDocumento);
    public String buscarUsuarioCarne(int prmCodCarne);
    public String buscarVehiculo(int prmDocumento);
    public String buscarVehPlaca (String prmPlaca);
    public String registrarAsociacion(int prmIdentificacion, int prmCodCarne, String prmPlaca);
}
