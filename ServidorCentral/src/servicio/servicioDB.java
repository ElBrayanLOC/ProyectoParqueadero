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
import negocio.Persona;
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
    private void leerFlujos() throws ClassNotFoundException, SQLException {
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
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException {
        StringTokenizer tokens = new StringTokenizer(peticion, ",");
        String parametros[] = new String[15];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        String accion = parametros[0];
        procesarAccion(accion, parametros);
    }

    private void procesarAccion(String accion, String parametros[]) throws ClassNotFoundException, SQLException {
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
                miPersona = gestorUsuarios.IniciarSesion(usuario);
                if (miPersona == null) {
                    salidaDecorada.println("No se encontro a algun usuario con ese usuario.");
                } else {
                    JsonObject objJson = parseToJSONUsuarioS(miPersona);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            case "buscarDocumento":
                cod = Integer.parseInt(parametros[1]);
                System.out.println("Buscar documento: " + cod);
                miPersona = gestorVehPersona.buscarUsuarioDocumento(cod);
                if (miPersona == null) {
                    salidaDecorada.println("No se encoontro Persona.");
                } else {
                    JsonObject objJson = parseToJSONPersona(miPersona);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            case "buscarCodCarne":
                cod = Integer.parseInt(parametros[1]);
                System.out.println("Buscar documento: " + cod);
                miPersona = gestorVehPersona.buscarUsuarioCarne(cod);
                if (miPersona == null) {
                    salidaDecorada.println("No se encoontro Persona.");
                } else {
                    JsonObject objJson = parseToJSONPersona(miPersona);
                    salidaDecorada.println(objJson.toString());
                }
                break;
            case "vehiculo":
                cod = Integer.parseInt(parametros[1]);
                ArrayList<Vehiculo> objVehiculo = gestorVehPersona.buscarVehiculo(cod);
                if (objVehiculo == null) {
                    salidaDecorada.println("No se encontro Vehiculos.");
                } else {
                    salidaDecorada.println(serializarVehiculos(objVehiculo));
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
                persona = gestorVehPersona.buscarUsuarioDocumento(cod);
                if (persona == null) {
                    persona = new Persona(cod, codCarne, nombre, apellido, genero, fechaNac, rol);
                    gestorVehPersona.registrarConductor(persona);
                    salidaDecorada.println("Conductor agregado con exito");
                } else {
                    salidaDecorada.println("Conductor ya registrado");
                }
                break;
            case "registrarVehiculo":
                int idConductor = Integer.parseInt(parametros[1]);
                int usuCodCarne = Integer.parseInt(parametros[2]);
                String placa = parametros[3];
                String marca = parametros[4];
                String tipoVehiculo = parametros[5];
                Vehiculo vehi = gestorVehPersona.buscarVehPlaca(placa);
                if (vehi == null) {
                    Vehiculo veh = new Vehiculo(idConductor, usuCodCarne, placa, marca, tipoVehiculo);
                    gestorVehPersona.registrarVehiculo(veh);
                    salidaDecorada.println("Vehiculo agregado con exito");
                } else {
                    salidaDecorada.println("Vehiculo ya registrado");
                }
                break;
            case "buscar_Bahias":
                ArrayList<Bahia> miBahia = gestorParqueadero.buscarBahias();

                if (miBahia == null) {
                    salidaDecorada.println("No se encoontro mapa.");
                } else {
                    salidaDecorada.println(serializarBahia(miBahia));
                }
                break;
            case "regIngreso":
                String vehPlaca = parametros[1];
                String bahId = parametros[2];
                String fecIngreso = parametros[3];
                String fecSalida = parametros[4];
                Ingreso ing = new Ingreso(vehPlaca, bahId, fecIngreso, fecSalida);
                gestorParqueadero.registrarIngreso(ing);
                salidaDecorada.println("Ingreso Registrado con exito");
                break;
            case "regSalida":
                bahId = parametros[1];
                fecSalida = parametros[2];
                gestorParqueadero.registrarSalida(bahId, fecSalida);
                salidaDecorada.println("Salida Confirmada");
                break;
            case "buscarVigilante":
                int codVi = Integer.parseInt(parametros[1]);
                Vigilante vig = gestorUsuarios.buscarVigilante(codVi);
                if (vig == null) {
                    salidaDecorada.println("No se encontro a ningun vigilante con esa identificación.");
                } else {
                    salidaDecorada.println(parseToJSONUsuario(vig));
                }
            case "buscarVehPlaca":
                String placaVeh = parametros[1];
                Vehiculo vehiculo = gestorVehPersona.buscarVehPlaca(placaVeh);
                if (vehiculo == null) {
                    salidaDecorada.println("No se encontro vehiculo.");
                } else {
                    salidaDecorada.println(parseToJSONVehiculo(vehiculo));
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
                vigilante = gestorUsuarios.buscarVigilante(codV);
                try {
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
        //System.out.println("Vehiculos json serializado: " + array.toString());
        return array.toString();
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
