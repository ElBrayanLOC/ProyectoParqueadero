package negocio;

import acceso.*;
import com.google.gson.Gson;
import java.util.ArrayList;

public class GestorParqueadero extends java.util.Observable {

    private final IServicioIngreso servidorParqueadero;
    private ArrayList<Bahia> listaBahia;
    private Ingreso ingreso;
    private String respuesta;

    public GestorParqueadero() {
        this.servidorParqueadero = new ServicioServidor();
        this.ingreso = new Ingreso();
        listaBahia = new ArrayList<>();
        this.respuesta = null;
    }

    public String registrarIngreso(String prmPlaca, String bahId, String fechaEntrada, String fechaSalida) {
        String json = servidorParqueadero.registrarIngreso(prmPlaca, bahId, fechaEntrada, fechaSalida);
        respuesta = json;
        return json;
    }

    public String registrarSalida(int bahId, String prmfechaSalida) {
        String json = servidorParqueadero.registrarSalida(bahId, prmfechaSalida);
        respuesta = json;
        return json;
    }

    public ArrayList<Bahia> Buscar_Bahias() {
        String arrayJson = servidorParqueadero.buscarBahias();
        if (!arrayJson.equals("No se encoontro mapa.")) {
            ArrayList<Bahia> misBahias = deserializarMisBahias(arrayJson);
            //this.notificar();
            return misBahias;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }

    private ArrayList<Bahia> deserializarMisBahias(String prmArrayJson) {
        Bahia[] misBahias = new Gson().fromJson(prmArrayJson, Bahia[].class);
        ArrayList<Bahia> listBahias = new ArrayList<>();
        for (int i = 0; i < misBahias.length; i++) {
            Bahia bah = misBahias[i];
            listBahias.add(bah);
        }
        listaBahia = listBahias;
        return listBahias;
    }
}