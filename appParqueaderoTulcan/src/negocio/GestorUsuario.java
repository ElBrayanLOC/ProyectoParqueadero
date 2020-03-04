/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.ServicioServidor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;
import acceso.IServicioUsuario;

/**
 *
 * @author jhayber
 */
public class GestorUsuario extends AModel {

    private final IServicioUsuario servicioUsuario;
    private Vigilante usuario;
    private Persona persona;
    private String respuesta;

    public GestorUsuario() {
        servicioUsuario = new ServicioServidor();
        usuario = new Vigilante();
        persona = new Persona();
        respuesta = null;
    }

    public Persona Iniciar_Sesion(String prmUsuario) {
        String json = servicioUsuario.IniciarSecion(prmUsuario);
        if (!json.equals("No se encontro a algun usuario con ese usuario.")) {
            Persona miUsuario = parseToUsuarioS(json);
            persona = miUsuario;
            this.notificar();
            return miUsuario;
        } else {
            respuesta = json;
        }
        return null;
    }

    public Vigilante BuscarVigilante(int prmUsuario) {
        Vigilante vigilante = null;
        String json = servicioUsuario.buscarVigilante(prmUsuario);
        if (!json.equals("No se encontro a ningun vigilante con esa identificación.")) {
            vigilante = parseToUsuario(json);
            usuario = vigilante;
            return vigilante;
        } else {
            respuesta = json;
        }
        return vigilante;
    }

    public String registrarVigilante(Vigilante prmVigilante) {
        String json = servicioUsuario.registrarVigilante(prmVigilante);
        respuesta = json;
        return json;
    }

    private Persona parseToUsuarioS(String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        persona.setPerIdentificacion(Integer.parseInt(properties.getProperty("perIdentificacion")));
        persona.setPerNombre(properties.getProperty("perNombre"));
        persona.setPerApellido(properties.getProperty("perApellido"));
        persona.setPerUsuario(properties.getProperty("perUsuario"));
        persona.setPerContrasenia(properties.getProperty("perContrasenia"));
        persona.setPerRol(properties.getProperty("perRol"));
        return persona;
    }

    private Vigilante parseToUsuario(String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        usuario.setVigIdentificacion((properties.getProperty("vigIdentificacion")));
        usuario.setVigNombres(properties.getProperty("vigNombres"));
        usuario.setVigApellidos(properties.getProperty("vigApellidos"));
        usuario.setVigGenero(properties.getProperty("vigGenero"));
        usuario.setVigFechaNac(properties.getProperty("vigFechaNac"));
        usuario.setVigEmpresa(properties.getProperty("vigEmpresa"));
        usuario.setVigUsuario(properties.getProperty("vigUsuario"));
        usuario.setVigContrasenia(properties.getProperty("vigContrasenia"));
        return usuario;
    }

    public Vigilante getUsuario() {
        return usuario;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
