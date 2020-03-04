/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.IServicioVehiculoPersona;
import acceso.ServicioServidor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;
import acceso.IServicioUsuario;
import java.util.Observable;

/**
 *
 * @author jhayber
 */
public class GestorVehiculoPersona extends Observable {

    private final IServicioVehiculoPersona servicioVehPersona;
    private Persona persona;
    private Vehiculo vehiculo;
    private ArrayList<Vehiculo> listaVehiculos;
    private String respuesta;

    public GestorVehiculoPersona() {
        servicioVehPersona = new ServicioServidor();
        persona = new Persona();
        vehiculo = new Vehiculo();
        listaVehiculos = new ArrayList<>();
        respuesta = null;
    }

    public String registrarConductor(Persona prmPersona) {
        String json = servicioVehPersona.registrarPersona(prmPersona);
        respuesta = json;
        setChanged();
        return json;
    }

    public String registrarVehiculo(Vehiculo prmVehiculo) {
        String json = servicioVehPersona.registrarVehiculo(prmVehiculo);
        respuesta = json;
        return json;
    }

    public Persona buscarUsuarioDocumento(int prmUsuario) {
        String json = servicioVehPersona.buscarUsuarioDocumento(prmUsuario);
        if (!json.equals("No se encoontro Persona.")) {
            Persona miPersona = parseToPersona(json);
            persona = miPersona;
            return miPersona;
        } else {
            respuesta = json;
        }
        return null;
    }

    public Persona buscarUsuarioCarne(int prmCodCarne) {
        String json = servicioVehPersona.buscarUsuarioCarne(prmCodCarne);
        if (!json.equals("No se encoontro Persona.")) {
            Persona miPersona = parseToPersona(json);
            persona = miPersona;
            return miPersona;
        } else {
            respuesta = json;
        }
        return null;
    }

    public ArrayList<Vehiculo> BuscarVeh(int prmUsuario) {
        String arrayJson = servicioVehPersona.buscarVehiculo(prmUsuario);
        if (!arrayJson.equals("No se encontro Vehiculos.")) {
            ArrayList<Vehiculo> misVehiculos = deserializarMisVehiculos(arrayJson);
            return misVehiculos;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }
    
    public Vehiculo buscarVehPlaca (String prmPlaca){
        String arrayJson = servicioVehPersona.buscarVehPlaca(prmPlaca);
        if (!arrayJson.equals("No se encontro vehiculo.")){
            vehiculo = parseToVehiculo(arrayJson);
            return vehiculo;
        }else{
            respuesta = arrayJson;
        }
        return null;
    }
    
    public String registrarAsociacion(int prmIdentificacion, int prmCodCarne, String prmPlaca){
        String json = servicioVehPersona.registrarAsociacion(prmIdentificacion, prmCodCarne, prmPlaca);
        respuesta = json;
        return json;
    }
    
    private ArrayList<Vehiculo> deserializarMisVehiculos(String prmArrayJson) {
        Vehiculo[] misVehiculos = new Gson().fromJson(prmArrayJson, Vehiculo[].class);
        ArrayList<Vehiculo> listVehiculos = new ArrayList<>();
        for (int i = 0; i < misVehiculos.length; i++) {
            Vehiculo veh = misVehiculos[i];
            listVehiculos.add(veh);
        }
        listaVehiculos = listVehiculos;
        return listVehiculos;
    }

    private Persona parseToPersona(String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        persona.setPerIdentificacion(Integer.parseInt(properties.getProperty("perIdentificacion")));
        persona.setCodCarne(Integer.parseInt(properties.getProperty("usucodcarne")));
        persona.setPerNombre(properties.getProperty("perNombre"));
        persona.setPerApellido(properties.getProperty("perApellido"));
        persona.setPerGenero(properties.getProperty("perGenero"));
        persona.setPerFechaNac(properties.getProperty("perFechaNac"));
        persona.setPerRol(properties.getProperty("perRol"));
        return persona;
    }
    
    private Vehiculo parseToVehiculo(String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        vehiculo.setPerIdentificacion(Integer.parseInt(properties.getProperty("perIdentificacion")));
        vehiculo.setUsuCodCarne(Integer.parseInt(properties.getProperty("usuCodCarne")));
        vehiculo.setVehPlaca(properties.getProperty("vehPlaca"));
        vehiculo.setVehMarca(properties.getProperty("vehMarca"));
        vehiculo.setVehTipo(properties.getProperty("vehTipo"));
        return vehiculo;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
