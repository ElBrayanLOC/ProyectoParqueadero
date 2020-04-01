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
public class Reporte {
    private String cantidad;
    private String fecha;
    private String placa;
    private int numHoras;
    private String hora;
    
    public Reporte(){
        
    }
    public Reporte(int numHoras, String hora) {
        this.numHoras = numHoras;
        this.hora = hora;
    }
    public Reporte(String cantidad, String fecha, String placa) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.placa = placa;
    }
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
     
    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
