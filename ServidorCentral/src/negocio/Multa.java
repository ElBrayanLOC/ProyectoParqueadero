/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Personal
 */
public class Multa {
    private String vehPlaca;
    private String mulDescripcion;
    private String mulFecha;
    private String mulFoto;

    public Multa(String vehPlaca, String mulDescripcion, String mulFecha, String mulFoto) {
        this.vehPlaca = vehPlaca;
        this.mulDescripcion = mulDescripcion;
        this.mulFecha = mulFecha;
        this.mulFoto = mulFoto;
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getMulDescripcion() {
        return mulDescripcion;
    }

    public void setMulDescripcion(String mulDescripcion) {
        this.mulDescripcion = mulDescripcion;
    }

    public String getMulFecha() {
        return mulFecha;
    }

    public void setMulFecha(String mulFecha) {
        this.mulFecha = mulFecha;
    }

    public String getMulFoto() {
        return mulFoto;
    }

    public void setMulFoto(String mulFoto) {
        this.mulFoto = mulFoto;
    }
    
}
