/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import negocio.Multa;

/**
 *
 * @author Personal
 */
public interface IServicioMulta {
    public String regMultaVehiculo(Multa objMulta);
    public String verMultas(String prmPlaca);
}
