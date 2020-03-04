/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jhayber
 */
public class GestorUsuariosBD {

    private  conectorJDBC conector;

    public GestorUsuariosBD() {
        conector = conector.getConnection();
    }

    public Persona IniciarSesion(String prmUsuario) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("select p.peridentificacion, p.pernombre, p.perapellido, ac.accusuario, ac.acccontrasenia, r.rolnombre from persona p inner join acceso ac on ac.peridentificacion = p.peridentificacion inner join rol r on r.peridentificacion = p.peridentificacion where ac.accusuario ='" + prmUsuario + "'");
        Persona usuarios = null;
        if (conector.getResultado().next()) {
            usuarios = new Persona(conector.getResultado().getInt("peridentificacion"), conector.getResultado().getString("pernombre"), conector.getResultado().getString("perapellido"), conector.getResultado().getString("accusuario"), conector.getResultado().getString("acccontrasenia"), conector.getResultado().getString("rolnombre"));
        }
        conector.desconectarse();
        return usuarios;
    }

    public Vigilante buscarVigilante(int documento) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("select * from vigilante v inner join acceso ac on ac.peridentificacion = v.peridentificacion where v.peridentificacion = " + documento);
        Vigilante vigilante = null;
        if (conector.getResultado().next()) {
            vigilante = new Vigilante(conector.getResultado().getInt("peridentificacion"), conector.getResultado().getString("pernombre"), conector.getResultado().getString("perapellido"), conector.getResultado().getString("pergenero"), conector.getResultado().getString("perfechanac"), conector.getResultado().getString("vigempresa"), conector.getResultado().getString("accusuario"), conector.getResultado().getString("acccontrasenia"));
        }
        conector.desconectarse();
        return vigilante;
    }

    public void registrarVigilante(Vigilante prmVigilante) throws ClassNotFoundException, SQLException {
        int identificacion = prmVigilante.getVigIdentificacion();
        String nombre = prmVigilante.getVigNombres();
        String apellido = prmVigilante.getVigApellidos();
        String genero = prmVigilante.getVigGenero();
        String fechaNac = prmVigilante.getVigFechaNac();
        String empresa = prmVigilante.getVigEmpresa();
        String usuario = prmVigilante.getVigUsuario();
        String contrasenia = prmVigilante.getContrasenia();
        String rol = "Vigilante";
        conector.conectarse();
        String sql, sql2, sql3, sql4;
        sql = "INSERT INTO persona(peridentificacion, pernombre, perapellido, pergenero, perfechanac)"
                + " VALUES ("
                + identificacion + ","
                + "'" + nombre + "',"
                + "'" + apellido + "',"
                + "'" + genero + "',"
                + "'" + fechaNac + "'"
                + ")";
        sql2 = "INSERT INTO vigilante(peridentificacion, pernombre, perapellido, pergenero, perfechanac, vigempresa)"
                + " VALUES ("
                + identificacion + ","
                + "'" + nombre + "',"
                + "'" + apellido + "',"
                + "'" + genero + "',"
                + "'" + fechaNac + "',"
                + "'" + empresa + "'"
                + ")";
        
        sql3 = "INSERT INTO acceso(accusuario, peridentificacion, acccontrasenia)"
                + " VALUES ("
                + "'" + usuario + "',"
                + identificacion + ","
                + "'" + contrasenia + "'"
                + ")";
        sql4 = "INSERT INTO rol (peridentificacion, rolnombre)"
                + " VALUES ("
                +  identificacion + ","
                + "'" + rol + "'"        
                + ")";
        conector.actualizar(sql);
        conector.actualizar(sql2);
        conector.actualizar(sql3);
        conector.desconectarse();
    }
}
