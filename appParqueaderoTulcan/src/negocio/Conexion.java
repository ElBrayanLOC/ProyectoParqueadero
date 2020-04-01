/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author jhayber
 */
public class Conexion {

    //Atributos
    private Connection conexion = null;
    private final String url = "jdbc:postgresql://localhost:5432/bdProyectoFinal";
    private final String usuario = "postgres";
    private final String contrasenia = "admin";

    public Connection getConecion() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = (Connection) DriverManager.getConnection(url, usuario, contrasenia);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //return conexion;
        return conexion;
    }
}
