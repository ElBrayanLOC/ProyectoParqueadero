/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainServidor;

import java.sql.SQLException;
import negocio.Persona;
import negocio.conectorJDBC;
import servicio.servicioDB;

/**
 *
 * @author jhayber
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        servicioDB objServidor = new servicioDB();
        objServidor.iniciar();
    }
}
