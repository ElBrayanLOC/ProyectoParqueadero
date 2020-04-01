/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parqueadero.entidades;

/**
 *
 * @author Usuario
 */
public class BahiaE {
    private String bahId;
    private String zonId;
    private String bahEstado;

    public BahiaE() {
    }

    public BahiaE(String bahId, String zonId, String bahEstado) {
        this.bahId = bahId;
        this.zonId = zonId;
        this.bahEstado = bahEstado;
    }

    public String getBahId() {
        return bahId;
    }

    public void setBahId(String bahId) {
        this.bahId = bahId;
    }

    public String getZonId() {
        return zonId;
    }

    public void setZonId(String zonId) {
        this.zonId = zonId;
    }

    public String getBahEstado() {
        return bahEstado;
    }

    public void setBahEstado(String bahEstado) {
        this.bahEstado = bahEstado;
    }
    
}
