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
 * @author Personal
 */
public class GestorVehPersonaBD {

    private conectorJDBC conector;

    public GestorVehPersonaBD() {
        conector = conector.getConnection();
    }

    public void registrarVehiculo(Vehiculo prmVehiculo) throws ClassNotFoundException, SQLException {
        String sql, sql2;
        int identificacion = prmVehiculo.getPerIdentificacion();
        int codCarne = prmVehiculo.getUsuCodCarne();
        String placa = prmVehiculo.getVehPlaca();
        String marca = prmVehiculo.getVehMarca();
        String tipoVehiculo = prmVehiculo.getVehTipo();
        conector.conectarse();
        sql = "INSERT INTO vehiculo(vehplaca, vehmarca, vehtipo)"
                + " VALUES ("
                + "'" + placa + "',"
                + "'" + marca + "',"
                + "'" + tipoVehiculo + "'"
                + ")";
        sql2 = "INSERT INTO asocia (peridentificacion, usucodcarne, vehplaca)"
                + " VALUES ("
                + identificacion + ","
                + codCarne + ","
                + "'" + placa + "'"
                + ")";
        conector.actualizar(sql);
        conector.actualizar(sql2);
        conector.desconectarse();
    }
    public void registrarAsociacion(int prmId, int prmCodCarnet, String prmPlacaVeh) throws ClassNotFoundException, SQLException{
        String sql;
        conector.conectarse();
        sql = "INSERT INTO asocia (peridentificacion, usucodcarne, vehplaca)"
                + " VALUES ("
                + prmId + ","
                + prmCodCarnet + ","
                + "'" + prmPlacaVeh + "'"
                + ")";
        conector.actualizar(sql);
        conector.desconectarse();
    }

    public void registrarConductor(Persona objConductor) throws ClassNotFoundException, SQLException {
        int id = objConductor.getPerIdentificacion();
        int codCarne = objConductor.getCodCarne();
        String nombre = objConductor.getPerNombre();
        String apellido = objConductor.getPerApellido();
        String genero = objConductor.getPerGenero();
        String fechaNac = objConductor.getPerFechaNac();
        String rol = objConductor.getPerRol();
        conector.conectarse();
        String sql, sql2, sql3;
        sql = "INSERT INTO persona (peridentificacion, pernombre, perapellido, pergenero, perfechanac)"
                + " VALUES ("
                + id + ","
                + "'" + nombre + "',"
                + "'" + apellido + "',"
                + "'" + genero + "',"
                + "'" + fechaNac + "'"
                + ")";
        sql2 = "INSERT INTO usuario (peridentificacion, usucodcarne, pernombre, perapellido, pergenero, perfechanac)"
                + " VALUES ("
                + id + ","
                + codCarne + ","
                + "'" + nombre + "',"
                + "'" + apellido + "',"
                + "'" + genero + "',"
                + "'" + fechaNac + "'"
                + ")";
        sql3 = "INSERT INTO rol (peridentificacion, rolnombre)"
                + " VALUES ("
                + id + ","
                + "'" + rol + "'"
                + ")";
        conector.actualizar(sql);
        conector.actualizar(sql2);
        conector.actualizar(sql3);
        conector.desconectarse();
    }

    public Persona buscarUsuarioDocumento(int documento) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("select P.PERIDENTIFICACION, P.usucodcarne, P.PERNOMBRE, P.PERAPELLIDO, P.PERGENERO, P.perFechaNac, r.ROLNOMBRE from usuario P inner join rol r on P.PERIDENTIFICACION = r.PERIDENTIFICACION where p.peridentificacion =" + documento);
        Persona miPersona = null;
        if (conector.getResultado().next()) {
            miPersona = new Persona(conector.getResultado().getInt("perIdentificacion"), conector.getResultado().getInt("usucodcarne"), conector.getResultado().getString("perNombre"), conector.getResultado().getString("perApellido"), conector.getResultado().getString("perGenero"), conector.getResultado().getString("perFechaNac"), conector.getResultado().getString("ROLNOMBRE"));
        }
        conector.desconectarse();
        return miPersona;
    }

    public Persona buscarUsuarioCarne(int prmCodCarne) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("select P.PERIDENTIFICACION, P.usucodcarne, P.PERNOMBRE, P.PERAPELLIDO, P.PERGENERO, P.perFechaNac, r.ROLNOMBRE from usuario P inner join rol r on P.PERIDENTIFICACION = r.PERIDENTIFICACION where p.usucodcarne=" + prmCodCarne);
        Persona miPersona = null;
        if (conector.getResultado().next()) {
            miPersona = new Persona(conector.getResultado().getInt("perIdentificacion"), conector.getResultado().getInt("usucodcarne"), conector.getResultado().getString("perNombre"), conector.getResultado().getString("perApellido"), conector.getResultado().getString("perGenero"), conector.getResultado().getString("perFechaNac"), conector.getResultado().getString("ROLNOMBRE"));
        }
        conector.desconectarse();
        return miPersona;
    }

    public ArrayList<Vehiculo> buscarVehiculo(int documento) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("select usu.peridentificacion, usu.usucodcarne, v.vehplaca, v.vehmarca, v.vehtipo from vehiculo v"
                + "inner join asocia aso on v.vehplaca = aso.vehplaca"
                + "inner join usuario usu on usu.peridentificacion = aso.peridentificacion"
                + "where usu.peridentificacion =" + documento + " or usu.usucodcarne=" + documento);

        ArrayList<Vehiculo> vehiculos = new ArrayList();
        while (conector.getResultado().next()) {
            Vehiculo veh = new Vehiculo(conector.getResultado().getInt("perIdentificacion"), conector.getResultado().getInt("usucodcarne"), conector.getResultado().getString("vehPlaca"), conector.getResultado().getString("vehMarca"), conector.getResultado().getString("vehTipo"));
            vehiculos.add(veh);
        }
        conector.desconectarse();
        return vehiculos;
    }

    public Vehiculo buscarVehPlaca(String placa) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("select usu.peridentificacion, usu.usucodcarne, v.vehplaca, v.vehmarca, v.vehtipo from vehiculo v "
                + "inner join asocia aso on v.vehplaca = aso.vehplaca "
                + "inner join usuario usu on usu.peridentificacion = aso.peridentificacion "
                + "where v.vehplaca = '" + placa + "'");
        Vehiculo veh = null;
        if (conector.getResultado().next()) {
            veh = new Vehiculo(conector.getResultado().getInt("perIdentificacion"), conector.getResultado().getInt("usucodcarne"), conector.getResultado().getString("vehPlaca"), conector.getResultado().getString("vehMarca"), conector.getResultado().getString("vehTipo"));
        }
        conector.desconectarse();
        return veh;
    }
}
