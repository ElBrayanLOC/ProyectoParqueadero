package negocio;

import acceso.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class GestorParqueadero extends java.util.Observable {

    private final IServicioIngreso servidorParqueadero;
    private ArrayList<Bahia> listaBahia;
    private ArrayList<Reporte> listaReporte;
    private Ingreso ingreso;
    private String respuesta;
    private Semaphore semaphore = new Semaphore(1);

    public GestorParqueadero() {
        this.servidorParqueadero = new ServicioServidor();
        this.ingreso = new Ingreso();
        listaBahia = new ArrayList<>();
        listaReporte = new ArrayList<>();
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

    public ArrayList<Bahia> Buscar_Bahias() throws InterruptedException {
        semaphore.acquire();
        String arrayJson = servidorParqueadero.buscarBahias();
        semaphore.release();
        if (!arrayJson.equals("No se encoontro mapa.")) {
            ArrayList<Bahia> misBahias = deserializarMisBahias(arrayJson);
            //this.notificar();
            return misBahias;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }

    public ArrayList<Reporte> reporteIngreso() {
        String arrayJson = servidorParqueadero.reporteIngreso();
        if (!arrayJson.equals("No se encontro reportes.")) {
            ArrayList<Reporte> misReportes = deserializarMisReportes(arrayJson);
            return misReportes;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }
    
    public ArrayList<Reporte> reporteIngresoVeh(String prmPlaca) {
        String arrayJson = servidorParqueadero.reporteIngresoVeh(prmPlaca);
        if (!arrayJson.equals("No se encontro reporte.")) {
            ArrayList<Reporte> reporte = parseToReporte(arrayJson);
            return reporte;
        } else {
            respuesta = arrayJson;
        }
        return null;
    }
    
    private ArrayList<Reporte> parseToReporte(String prmArrayJson) {
        Reporte[] reporte = new Gson().fromJson(prmArrayJson, Reporte[].class);
        ArrayList<Reporte> listreporte = new ArrayList<>();
        for (int i = 0; i < reporte.length; i++) {
            Reporte re = reporte[i];
            listreporte.add(re);
        }
        listaReporte = listreporte;
        return listreporte;
    }
    
    private ArrayList<Reporte> deserializarMisReportes(String prmArrayJson) {
        Reporte[] misReportes = new Gson().fromJson(prmArrayJson, Reporte[].class);
        ArrayList<Reporte> listReportes = new ArrayList<>();
        for (int i = 0; i < misReportes.length; i++) {
            Reporte rep = misReportes[i];
            listReportes.add(rep);
        }
        listaReporte = listReportes;
        return listReportes;
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
