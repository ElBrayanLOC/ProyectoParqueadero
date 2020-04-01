/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parqueadero.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "bahia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bahia.findAll", query = "SELECT b FROM Bahia b")
    , @NamedQuery(name = "Bahia.findByBahid", query = "SELECT b FROM Bahia b WHERE b.bahid = :bahid")
    , @NamedQuery(name = "Bahia.findByBahestado", query = "SELECT b FROM Bahia b WHERE b.bahestado = :bahestado")})
public class Bahia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bahid")
    private Integer bahid;
    @Basic(optional = false)
    @Column(name = "bahestado")
    private String bahestado;
    @JoinColumn(name = "zonid", referencedColumnName = "zonid")
    @ManyToOne(optional = false)
    private Zona zonid;

    public Bahia() {
    }

    public Bahia(Integer bahid) {
        this.bahid = bahid;
    }

    public Bahia(Integer bahid, String bahestado) {
        this.bahid = bahid;
        this.bahestado = bahestado;
    }

    public Integer getBahid() {
        return bahid;
    }

    public void setBahid(Integer bahid) {
        this.bahid = bahid;
    }

    public String getBahestado() {
        return bahestado;
    }

    public void setBahestado(String bahestado) {
        this.bahestado = bahestado;
    }

    public Zona getZonid() {
        return zonid;
    }

    public void setZonid(Zona zonid) {
        this.zonid = zonid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bahid != null ? bahid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bahia)) {
            return false;
        }
        Bahia other = (Bahia) object;
        if ((this.bahid == null && other.bahid != null) || (this.bahid != null && !this.bahid.equals(other.bahid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parqueadero.entidades.Bahia[ bahid=" + bahid + " ]";
    }
    
}
