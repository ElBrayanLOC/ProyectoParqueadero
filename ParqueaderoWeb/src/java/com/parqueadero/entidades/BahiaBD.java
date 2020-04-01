package com.parqueadero.entidades;

import acceso.IServicioIngreso;
import acceso.ServicioServidor;
import com.google.gson.Gson;
import java.util.ArrayList;

public class BahiaBD {
    private final IServicioIngreso servidorParqueadero;
    private ArrayList<BahiaE> listaBahia;
    private String respuesta;
    
    public BahiaBD() {
        this.servidorParqueadero = new ServicioServidor();
        listaBahia = new ArrayList<>();
        this.respuesta = null;
    }
    
    public ArrayList<BahiaE> Buscar_Bahias(){
        String arrayJson = servidorParqueadero.buscarBahias();
        if (!arrayJson.equals("No se encoontro mapa.")) {
            ArrayList<BahiaE> misBahias = deserializarMisBahias(arrayJson);
            //this.notificar();
            return misBahias;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }

    private ArrayList<BahiaE> deserializarMisBahias(String prmArrayJson) {
        BahiaE[] misBahias = new Gson().fromJson(prmArrayJson, BahiaE[].class);
        ArrayList<BahiaE> listBahias = new ArrayList<>();
        for (int i = 0; i < misBahias.length; i++) {
            BahiaE bah = misBahias[i];
            listBahias.add(bah);
        }
        listaBahia = listBahias;
        return listBahias;
    }
}
