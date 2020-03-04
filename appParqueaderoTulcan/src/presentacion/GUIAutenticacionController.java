/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import Utilidades.Utilidades;
import java.awt.event.ActionEvent;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import negocio.GestorUsuario;
import negocio.Persona;
import negocio.Vigilante;

/**
 *
 * @author jhayber
 */
public class GUIAutenticacionController extends AActionController {

    private final GestorUsuario gestor;
    private final GUIAutenticacion vista;

    public GUIAutenticacionController(AModel myModel, AView myView) {
        super(myModel, myView);
        this.vista = (GUIAutenticacion) myView;
        this.gestor = (GestorUsuario) myModel;
    }

    @Override
    public void actualizar(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "iniciar":
                if (vista.getAccion().equals("IniciarSesion")) {
                    iniciarSesion();
                }
                break;
        }
        vista.setVisible(false);
    }

    private void iniciarSesion() {
        String usuario = vista.getUsuario();

        String contraseña = vista.getContraseña();
        try {
            Persona objUsuario = gestor.Iniciar_Sesion(usuario);

            if (objUsuario != null && objUsuario.getPerUsuario().equals(usuario) && objUsuario.getPerContrasenia().equals(contraseña)) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GUIInicio objInicio = new GUIInicio(objUsuario.getPerNombre(), objUsuario.getPerApellido(), objUsuario.getPerRol());
                        objInicio.setVisible(true);
                    }
                });
            } else {
                Utilidades.mensajeAdvertencia("Contraseña incorrecta", "Atencion");
            }
        } catch (Exception e) {
            Utilidades.mensajeAdvertencia(e.getMessage(), "Advertencia");
        }
    }
}