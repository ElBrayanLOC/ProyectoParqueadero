/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import negocio.Vigilante;

/**
 *
 * @author jhayber
 */
public interface IServicioUsuario {
    public String IniciarSecion(String prmUsuario);
    public String buscarVigilante(int prmIdVig);
    public String registrarVigilante(Vigilante vigilante);
}
