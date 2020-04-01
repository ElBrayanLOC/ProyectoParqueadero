/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jhayber
 */
public class conectorJDBC {

    //Atributos
    private static conectorJDBC DBConn = new conectorJDBC();
    private Connection conexion;
    private ResultSet resultado;
    private Statement estamento;
    private final String url = "jdbc:postgresql://localhost:5432/bdProyectoFinal";
    private final String usuario = "postgres";
    private final String contrasenia = "admin";

    private conectorJDBC() {
    }
    
    public static conectorJDBC getConnection() {
        return DBConn;
    }
    /* Se conecta a la base de datos de java
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void conectarse() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasenia);
            if (conexion != null) {
                System.out.println("CONECTADO!!!!!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Ejecuta una consulta tipo select
     *
     * @param sql
     * @throws SQLException
     *
     */
    public void crearConsulta(String sql) throws SQLException {
        estamento = conexion.createStatement();
        resultado = estamento.executeQuery(sql);
    }

    /**
     * Ejecuta una consulta de tipo insert, update o delete
     *
     * @param sql
     * @throws SQLException
     */
    public void actualizar(String sql) throws SQLException {
        estamento = conexion.createStatement();
        estamento.executeUpdate(sql);

    }

    /**
     * Cierra las variables de rs, st y cn que estén abiertas
     *
     * @throws SQLException
     */
    public void desconectarse() throws SQLException {
        if (resultado != null) {
            resultado.close();
        }
        estamento.close();
        conexion.close();
    }

    /**
     * Devuelve todo el conjunto de resultados
     *
     * @return
     */
    public ResultSet getResultado() {
        return resultado;
    }
}
