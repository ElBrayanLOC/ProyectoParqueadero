/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Bahia;
import negocio.GestorParqueaderoBD;
import negocio.GestorUsuariosBD;
import negocio.GestorVehPersonaBD;
import negocio.Ingreso;
import negocio.Multa;
import negocio.Persona;
import negocio.Reporte;
import negocio.Vigilante;
import negocio.Vehiculo;

/**
 *
 * @author jhayber
 */
public class servicioDB implements Runnable {

    private final GestorUsuariosBD gestorUsuarios;
    private final GestorVehPersonaBD gestorVehPersona;
    private final GestorParqueaderoBD gestorParqueadero;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;

    public servicioDB() {
        gestorUsuarios = new GestorUsuariosBD();
        gestorVehPersona = new GestorVehPersonaBD();
        gestorParqueadero = new GestorParqueaderoBD();
    }

    public void iniciar() {
        abrirPuerto();
        while (true) {
            esperarCliente();
            lanzarHilo();
        }
    }

    /**
     * Lanzar Hilo
     */
    private static void lanzarHilo() {
        new Thread(new servicioDB()).start();
    }

    private static void abrirPuerto() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(servicioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Esperar que el cliente se conecta y le devuelve un SOCKET
     */
    private static void esperarCliente() {
        try {
            socket = serverSocket.accept();
            System.out.println("Cliente Conectado");;
        } catch (IOException ex) {
            Logger.getLogger(servicioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cuerpo del hilo
     */
    @Override
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(servicioDB.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(servicioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea los flujos con el Socket
     *
     * @throws IOException
     */
    public void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }

    /**
     * Lee los flujos del Socket
     */
    private void leerFlujos() throws ClassNotFoundException, SQLException, ParseException {
        if (entradaDecorada.hasNextLine()) {
            //Extrae el flujo que envia el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);
        } else {
            salidaDecorada.flush();
            salidaDecorada.println("No_Encontrado");
        }
    }

    /**
     * Cierra los flujos de entrada y salida.
     *
     * @throws IOException
     */
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }

    /**
     * Decodifica la petición, extrayendo la acción y los parámetros
     *
     * @param peticion petición completa al estilo "accion, informacion"
     */
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException, ParseException {
        StringTokenizer tokens = new StringTokenizer(peticion, ",");
        String parametros[] = new String[15];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        String accion = parametros[0];
        procesarAccion(accion, parametros);
    }

    private void procesarAccion(String accion, String parametros[]) throws ClassNotFoundException, SQLException, ParseException {
        /**
         * Atributos para usuario
         */
        String usuario;
        int cod;
        Vigilante miUsuario;
        Persona miPersona;
        switch (accion) {
            case "Iniciar Sesion":
                usuario = parametros[1];
                try {
                    miPersona = gestorUsuarios.IniciarSesion(usuario);
                    if (miPersona == null) {
                        salidaDecorada.println("No se encontro a algun usuario con ese usuario.");
                    } else {
                        JsonObject objJson = parseToJSONUsuarioS(miPersona);
                        salidaDecorada.println(objJson.toString());
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "buscarDocumento":
                cod = Integer.parseInt(parametros[1]);
                try {
                    miPersona = gestorVehPersona.buscarUsuarioDocumento(cod);
                    if (miPersona == null) {
                        salidaDecorada.println("No se encoontro Persona.");
                    } else {
                        JsonObject objJson = parseToJSONPersona(miPersona);
                        salidaDecorada.println(objJson.toString());
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "buscarCodCarne":
                cod = Integer.parseInt(parametros[1]);
                System.out.println("Buscar documento: " + cod);
                try {
                    miPersona = gestorVehPersona.buscarUsuarioCarne(cod);
                    if (miPersona == null) {
                        salidaDecorada.println("No se encoontro Persona.");
                    } else {
                        JsonObject objJson = parseToJSONPersona(miPersona);
                        salidaDecorada.println(objJson.toString());
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "vehiculo":
                cod = Integer.parseInt(parametros[1]);
                try {
                    ArrayList<Vehiculo> objVehiculo = gestorVehPersona.buscarVehiculo(cod);
                    if (objVehiculo == null) {
                        salidaDecorada.println("No se encontro Vehiculos.");
                    } else {
                        salidaDecorada.println(serializarVehiculos(objVehiculo));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "registrarConductor":
                Persona persona;
                cod = Integer.parseInt(parametros[1]);
                int codCarne = Integer.parseInt(parametros[2]);
                String nombre = parametros[3];
                String apellido = parametros[4];
                String genero = parametros[5];
                String fechaNac = parametros[6];
                String rol = parametros[7];
                try {
                    persona = gestorVehPersona.buscarUsuarioDocumento(cod);
                    if (persona == null) {
                        persona = new Persona(cod, codCarne, nombre, apellido, genero, fechaNac, rol);
                        gestorVehPersona.registrarConductor(persona);
                        salidaDecorada.println("Conductor agregado con exito");
                    } else {
                        salidaDecorada.println("Conductor ya registrado");
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "registrarVehiculo":
                int idConductor = Integer.parseInt(parametros[1]);
                int usuCodCarne = Integer.parseInt(parametros[2]);
                String placa = parametros[3];
                String marca = parametros[4];
                String tipoVehiculo = parametros[5];
                try {
                    Vehiculo vehi = gestorVehPersona.buscarVehPlaca(placa);
                    if (vehi == null) {
                        Vehiculo veh = new Vehiculo(idConductor, usuCodCarne, placa, marca, tipoVehiculo);
                        gestorVehPersona.registrarVehiculo(veh);
                        salidaDecorada.println("Vehiculo agregado con exito");
                    } else {
                        salidaDecorada.println("Vehiculo ya registrado");
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "buscar_Bahias":
                try {
                    ArrayList<Bahia> miBahia = gestorParqueadero.buscarBahias();

                    if (miBahia == null) {
                        salidaDecorada.println("No se encoontro mapa.");
                    } else {
                        salidaDecorada.println(serializarBahia(miBahia));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "regIngreso":
                String vehPlaca = parametros[1];
                String bahId = parametros[2];
                String fecIngreso = parametros[3];
                String fecSalida = parametros[4];
                try {
                    Ingreso ing = new Ingreso(vehPlaca, bahId, fecIngreso, fecSalida);
                    gestorParqueadero.registrarIngreso(ing);
                    salidaDecorada.println("Ingreso Registrado con exito");
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "regSalida":
                bahId = parametros[1];
                fecSalida = parametros[2];
                try {
                    gestorParqueadero.registrarSalida(bahId, fecSalida);
                    salidaDecorada.println("Salida Confirmada");
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "buscarVigilante":
                int codVi = Integer.parseInt(parametros[1]);
                try {
                    Vigilante vig = gestorUsuarios.buscarVigilante(codVi);
                    if (vig == null) {
                        salidaDecorada.println("No se encontro a ningun vigilante con esa identificación.");
                    } else {
                        salidaDecorada.println(parseToJSONUsuario(vig));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

            case "buscarVehPlaca":
                String placaVeh = parametros[1];
                Vehiculo vehiculo;
                try {
                    vehiculo = gestorVehPersona.buscarVehPlaca(placaVeh);
                    if (vehiculo == null) {
                        salidaDecorada.println("No se encontro vehiculo.");
                    } else {
                        salidaDecorada.println(parseToJSONVehiculo(vehiculo));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
            case "registrarVigilante":
                Vigilante vigilante;
                int codV = Integer.parseInt(parametros[1]);
                String nombreV = parametros[2];
                String apellidoV = parametros[3];
                String generoV = parametros[4];
                String fechaNacV = parametros[5];
                String empresaV = parametros[6];
                String usuarioV = parametros[7];
                String contraseniaV = parametros[8];
                try {
                    vigilante = gestorUsuarios.buscarVigilante(codV);
                    if (vigilante == null) {
                        gestorUsuarios.registrarVigilante(new Vigilante(codV, nombreV, apellidoV, generoV, fechaNacV, empresaV, usuarioV, contraseniaV));
                        salidaDecorada.println("Vigilante agregado con exito");
                    } else {
                        salidaDecorada.println("Vigilante ya registrado");
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "asociarVehiculo":
                int id = Integer.parseInt(parametros[1]);
                int codUsu = Integer.parseInt(parametros[2]);
                String placaVehi = parametros[3];
                try {
                    gestorVehPersona.registrarAsociacion(id, codUsu, placaVehi);
                    salidaDecorada.println("Asociacion registrada con exito");
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "registrarMulta":
                String placaMul = parametros[1];
                String mulDescripcion = parametros[2];
                String mulfecha = parametros[3];
                String mulfoto = parametros[4];
                Vehiculo vehi;
                try {
                    vehi = gestorVehPersona.buscarVehPlaca(placaMul);
                    if (vehi != null) {
                        Multa objMulta = new Multa(placaMul, mulDescripcion, mulfecha, mulfoto);
                        objMulta.getMulFecha();
                        gestorVehPersona.regMultaVehiculo(objMulta);
                        salidaDecorada.println("Multa agragada con exito.");
                    } else {
                        salidaDecorada.println("No se encontro vehiculo.");
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "verMultas":
                placaVeh = parametros[1];
                try {

                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                ArrayList<Multa> objMulta = gestorVehPersona.verMultas(placaVeh);
                if (objMulta == null) {
                    salidaDecorada.println("No se encontro multas.");
                } else {
                    salidaDecorada.println(serializarMultas(objMulta));
                }
                break;
            case "reporteIngreso":
                try {
                    ArrayList<Reporte> objReporte = gestorParqueadero.reporteIngreso();
                    if (objReporte == null) {
                        salidaDecorada.println("No se encontro reportes.");
                    } else {
                        salidaDecorada.println(serializarReporte(objReporte));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }
                break;
            case "reporteIngresoVeh":
                placaVeh = parametros[1];
                try {
                    ArrayList<Reporte> objreporte = gestorParqueadero.verReporte(placaVeh);
                    if (objreporte == null) {
                        salidaDecorada.println("No se encontro reporte.");
                    } else {
                        salidaDecorada.println(serializarReporteVeh(objreporte));
                    }
                } catch (Exception ex) {
                    salidaDecorada.println(ex.getMessage());
                }

                break;
        }
    }

    private JsonObject parseToJSONUsuarioS(Persona objPersona) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objPersona.getPerIdentificacion());
        jsonobj.addProperty("usucodcarne", objPersona.getCodCarne());
        jsonobj.addProperty("perNombre", objPersona.getPerNombre());
        jsonobj.addProperty("perApellido", objPersona.getPerApellido());
        jsonobj.addProperty("perUsuario", objPersona.getPerUsuario());
        jsonobj.addProperty("perContrasenia", objPersona.getPerContrasenia());
        jsonobj.addProperty("perRol", objPersona.getPerRol());
        return jsonobj;
    }

    private JsonObject parseToJSONUsuario(Vigilante objUsuario) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("idUsuario", objUsuario.getVigUsuario());
        jsonobj.addProperty("vigNombres", objUsuario.getVigNombres());
        jsonobj.addProperty("vigApellidos", objUsuario.getVigApellidos());
        jsonobj.addProperty("vigGenero", objUsuario.getVigGenero());
        jsonobj.addProperty("vigFechaNac", objUsuario.getVigFechaNac());
        jsonobj.addProperty("vigEmpresa", objUsuario.getVigEmpresa());
        jsonobj.addProperty("vigUsuario", objUsuario.getVigUsuario());
        jsonobj.addProperty("vigContrasenia", objUsuario.getContrasenia());
        return jsonobj;
    }

    private JsonObject parseToJSONPersona(Persona objPersona) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objPersona.getPerIdentificacion());
        jsonobj.addProperty("usucodcarne", objPersona.getCodCarne());
        jsonobj.addProperty("perNombre", objPersona.getPerNombre());
        jsonobj.addProperty("perApellido", objPersona.getPerApellido());
        jsonobj.addProperty("perGenero", objPersona.getPerGenero());
        jsonobj.addProperty("perFechaNac", objPersona.getPerFechaNac());
        jsonobj.addProperty("perRol", objPersona.getPerRol());
        return jsonobj;
    }

    private String serializarVehiculos(ArrayList<Vehiculo> prmVehiculos) {
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Vehiculo veh : prmVehiculos) {
            gsonObj = parseToJSONVehiculo(veh);
            array.add(gsonObj);
        }
        return array.toString();
    }

    private String serializarMultas(ArrayList<Multa> prmMultas) {
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Multa mul : prmMultas) {
            gsonObj = parseToJSONMulta(mul);
            array.add(gsonObj);
        }
        return array.toString();
    }

    private String serializarReporte(ArrayList<Reporte> prmReporte) {
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Reporte rep : prmReporte) {
            gsonObj = parseToJsonReporte(rep);
            array.add(gsonObj);
        }
        return array.toString();
    }

    private String serializarReporteVeh(ArrayList<Reporte> prmReporte) {
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Reporte re : prmReporte) {
            gsonObj = parseToJSONReporteVeh(re);
            array.add(gsonObj);
        }
        return array.toString();
    }

    private JsonObject parseToJSONReporteVeh(Reporte objReporte) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("cantidad", objReporte.getCantidad());
        jsonobj.addProperty("fecha", objReporte.getFecha());
        jsonobj.addProperty("placa", objReporte.getPlaca());
        return jsonobj;
    }

    private JsonObject parseToJsonReporte(Reporte objReporte) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("numHoras", objReporte.getNumHoras());
        jsonobj.addProperty("hora", objReporte.getHora());
        return jsonobj;
    }

    private JsonObject parseToJSONMulta(Multa objMulta) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("vehPlaca", objMulta.getVehPlaca());
        jsonobj.addProperty("mulDescripcion", objMulta.getMulDescripcion());
        jsonobj.addProperty("mulFecha", objMulta.getMulFecha());
        jsonobj.addProperty("mulFoto", objMulta.getMulFoto());
        return jsonobj;
    }

    private JsonObject parseToJSONVehiculo(Vehiculo objVehiculo) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("perIdentificacion", objVehiculo.getPerIdentificacion());
        jsonobj.addProperty("usuCodCarne", objVehiculo.getUsuCodCarne());
        jsonobj.addProperty("vehPlaca", objVehiculo.getVehPlaca());
        jsonobj.addProperty("vehMarca", objVehiculo.getVehMarca());
        jsonobj.addProperty("vehTipo", objVehiculo.getVehTipo());
        return jsonobj;
    }

    private String serializarBahia(ArrayList<Bahia> prmBah) {
        JsonArray array = new JsonArray();
        JsonObject gsonObj;
        for (Bahia bah : prmBah) {
            gsonObj = parseToJSONBahia(bah);
            array.add(gsonObj);
        }
        //System.out.println("Vehiculos json serializado: " + array.toString());
        return array.toString();
    }

    private JsonObject parseToJSONBahia(Bahia objbah) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("zonId", objbah.getZonId());
        jsonobj.addProperty("bahId", objbah.getBahId());
        jsonobj.addProperty("bahEstado", objbah.getBahEstado());
        return jsonobj;
    }
}
