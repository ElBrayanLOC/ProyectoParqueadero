/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Usuario
 */
public class Vehiculo {
    private int perIdentificacion;
    private int usuCodCarne;
    private String vehPlaca;
    private String vehMarca;
    private String vehTipo;
    
    public Vehiculo(){
        
    }

    public Vehiculo(int perIdentificacion, int usuCodCarne, String vehPlaca, String vehMarca, String vehTipo) {
        this.perIdentificacion = perIdentificacion;
        this.usuCodCarne = usuCodCarne;
        this.vehPlaca = vehPlaca;
        this.vehMarca = vehMarca;
        this.vehTipo = vehTipo;
    }

    public int getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(int perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public int getUsuCodCarne() {
        return usuCodCarne;
    }

    public void setUsuCodCarne(int usuCodCarne) {
        this.usuCodCarne = usuCodCarne;
    }
    
    
    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getVehMarca() {
        return vehMarca;
    }

    public void setVehMarca(String vehMarca) {
        this.vehMarca = vehMarca;
    }

    public String getVehTipo() {
        return vehTipo;
    }

    public void setVehTipo(String vehTipo) {
        this.vehTipo = vehTipo;
    }
}
