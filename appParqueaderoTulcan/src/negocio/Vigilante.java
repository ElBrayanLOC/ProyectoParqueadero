/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author jhayber
 */
public class Vigilante {
    private String vigIdentificacion;
    private String vigNombres;
    private String vigApellidos;
    private String vigGenero;
    private String vigFechaNac;
    private String vigEmpresa;
    private String vigUsuario;
    private String vigContrasenia;
    public Vigilante(){
    }

    public Vigilante(String vigIdentificacion, String vigNombres, String vigApellidos, String vigGenero, String vigFechaNac, String vigEmpresa, String vigUsuario, String vigContrasenia) {
        this.vigIdentificacion = vigIdentificacion;
        this.vigNombres = vigNombres;
        this.vigApellidos = vigApellidos;
        this.vigGenero = vigGenero;
        this.vigFechaNac = vigFechaNac;
        this.vigEmpresa = vigEmpresa;
        this.vigUsuario = vigUsuario;
        this.vigContrasenia = vigContrasenia;
    }

    public String getVigIdentificacion() {
        return vigIdentificacion;
    }

    public void setVigIdentificacion(String vigIdentificacion) {
        this.vigIdentificacion = vigIdentificacion;
    }

    public String getVigNombres() {
        return vigNombres;
    }

    public void setVigNombres(String vigNombres) {
        this.vigNombres = vigNombres;
    }

    public String getVigApellidos() {
        return vigApellidos;
    }

    public void setVigApellidos(String vigApellidos) {
        this.vigApellidos = vigApellidos;
    }

    public String getVigGenero() {
        return vigGenero;
    }

    public void setVigGenero(String vigGenero) {
        this.vigGenero = vigGenero;
    }

    public String getVigFechaNac() {
        return vigFechaNac;
    }

    public void setVigFechaNac(String vigFechaNac) {
        this.vigFechaNac = vigFechaNac;
    }

    public String getVigEmpresa() {
        return vigEmpresa;
    }

    public void setVigEmpresa(String vigEmpresa) {
        this.vigEmpresa = vigEmpresa;
    }

    public String getVigUsuario() {
        return vigUsuario;
    }

    public void setVigUsuario(String vigUsuario) {
        this.vigUsuario = vigUsuario;
    }

    public String getVigContrasenia() {
        return vigContrasenia;
    }

    public void setVigContrasenia(String vigContrasenia) {
        this.vigContrasenia = vigContrasenia;
    }
    
    
    
}
