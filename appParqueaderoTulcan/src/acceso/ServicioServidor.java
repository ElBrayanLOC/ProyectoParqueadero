/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Ingreso;
import negocio.Persona;
import negocio.Vehiculo;
import negocio.Vigilante;

/**
 *
 * @author jhayber
 */
public class ServicioServidor implements IServicioUsuario, IServicioVehiculoPersona, IServicioIngreso {

    private final String IP_SERVIDOR = "localhost";
    private PrintStream salidaDecorada;
    private Scanner entradaDecorada;
    private Socket socket;
    private final int PUERTO = 5000;

    /**
     * Conecta al cleinte con el servidor, para que pueda empezar la
     * comunicacion por medio de flujos
     *
     * @param address
     * @param port
     * @throws IOException
     */
    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }

    /**
     * Se encarga de enviar la informacion de salida y nos retorna la respuesta
     * del servidor
     *
     * @param accion
     * @return
     * @throws IOException
     */
    private String leerFlujoEntradaSalida(String accion) throws IOException {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println(accion);
        if (entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        return respuesta;
    }

    /**
     * Cierra los flujos de informacion entre el cliente y el servidor
     */
    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }

    /**
     * desconecta al cleinte del servidor
     */
    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String IniciarSecion(String prmUsuario) {
        String respuesta = null;
        String accion = "Iniciar Sesion";
        String informacionCliente;
        informacionCliente = prmUsuario;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String buscarUsuarioDocumento(int prmDocumento) {
        String respuesta = null;
        String accion = "buscarDocumento";
        int informacionCliente;
        informacionCliente = prmDocumento;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String buscarVehiculo(int prmDocumento) {
        String respuesta = null;
        String accion = "vehiculo";
        int informacionCliente;
        informacionCliente = prmDocumento;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String registrarPersona(Persona prmConductor) {
        String respuesta = null;
        String accion = "registrarConductor";
        String informacionConductor;
        informacionConductor = prmConductor.getPerIdentificacion() + "," + prmConductor.getCodCarne() + "," + prmConductor.getPerNombre() + "," + prmConductor.getPerApellido() + "," + prmConductor.getPerGenero() + "," + prmConductor.getPerFechaNac() + "," + prmConductor.getPerRol();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionConductor);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String registrarVehiculo(Vehiculo prmVehiculo) {
        String respuesta = null;
        String accion = "registrarVehiculo";
        String informacionVehiculo;
        informacionVehiculo = prmVehiculo.getPerIdentificacion() + "," + prmVehiculo.getUsuCodCarne() + "," + prmVehiculo.getVehPlaca() + "," + prmVehiculo.getVehMarca() + "," + prmVehiculo.getVehTipo();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVehiculo);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public String buscarBahias() {
        String respuesta = null;
        String accion = "buscar_Bahias";
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String registrarIngreso(String prmPlaca, String bahId, String fechaEntrada, String fechaSalida) {
        String respuesta = null;
        String accion = "regIngreso";
        String infoIngreso;
        infoIngreso = prmPlaca + "," + bahId + "," + fechaEntrada + "," + fechaSalida;

        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + infoIngreso);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String registrarSalida(int bahId, String prmFechaSalida) {
        String respuesta = null;
        String accion = "regSalida";
        String infoSalida;
        infoSalida = bahId + "," + prmFechaSalida;

        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + infoSalida);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String registrarVigilante(Vigilante prmVigilante) {
        String respuesta = null;
        String accion = "registrarVigilante";
        String informacionVigilante;
        informacionVigilante = prmVigilante.getVigIdentificacion() + "," + prmVigilante.getVigNombres() + "," + prmVigilante.getVigApellidos() + "," + prmVigilante.getVigGenero() + "," + prmVigilante.getVigFechaNac() + "," + prmVigilante.getVigEmpresa() + "," + prmVigilante.getVigUsuario() + "," + prmVigilante.getVigContrasenia();
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVigilante);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String buscarVigilante(int prmIdVig) {
        String respuesta = null;
        String accion = "buscarVigilante";
        int informacionVigilante;
        informacionVigilante = prmIdVig;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionVigilante);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String buscarUsuarioCarne(int prmCodCarne) {
        String respuesta = null;
        String accion = "buscarCodCarne";
        int informacionCliente;
        informacionCliente = prmCodCarne;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public String buscarVehPlaca(String prmPlaca) {
        String respuesta = null;
        String accion = "buscarVehPlaca";
        String informacionCliente;
        informacionCliente = prmPlaca;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }
    @Override
    public String registrarAsociacion(int prmIdentificacion, int prmCodCarne, String prmPlaca){
        String respuesta = null;
        String accion = "asociarVehiculo";
        String informacionCliente = prmIdentificacion + "," + prmCodCarne + "," + prmPlaca;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion + "," + informacionCliente);
            cerrarFlujos();
            desconectar();
        } catch (IOException ex) {
            Logger.getLogger(ServicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
