/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parqueadero.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "zona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z")
    , @NamedQuery(name = "Zona.findByZonid", query = "SELECT z FROM Zona z WHERE z.zonid = :zonid")
    , @NamedQuery(name = "Zona.findByZonnombre", query = "SELECT z FROM Zona z WHERE z.zonnombre = :zonnombre")
    , @NamedQuery(name = "Zona.findByZoncantpuestos", query = "SELECT z FROM Zona z WHERE z.zoncantpuestos = :zoncantpuestos")})
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zonid")
    private Integer zonid;
    @Basic(optional = false)
    @Column(name = "zonnombre")
    private String zonnombre;
    @Basic(optional = false)
    @Column(name = "zoncantpuestos")
    private String zoncantpuestos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zonid")
    private Collection<Bahia> bahiaCollection;

    public Zona() {
    }

    public Zona(Integer zonid) {
        this.zonid = zonid;
    }

    public Zona(Integer zonid, String zonnombre, String zoncantpuestos) {
        this.zonid = zonid;
        this.zonnombre = zonnombre;
        this.zoncantpuestos = zoncantpuestos;
    }

    public Integer getZonid() {
        return zonid;
    }

    public void setZonid(Integer zonid) {
        this.zonid = zonid;
    }

    public String getZonnombre() {
        return zonnombre;
    }

    public void setZonnombre(String zonnombre) {
        this.zonnombre = zonnombre;
    }

    public String getZoncantpuestos() {
        return zoncantpuestos;
    }

    public void setZoncantpuestos(String zoncantpuestos) {
        this.zoncantpuestos = zoncantpuestos;
    }

    @XmlTransient
    public Collection<Bahia> getBahiaCollection() {
        return bahiaCollection;
    }

    public void setBahiaCollection(Collection<Bahia> bahiaCollection) {
        this.bahiaCollection = bahiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zonid != null ? zonid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.zonid == null && other.zonid != null) || (this.zonid != null && !this.zonid.equals(other.zonid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parqueadero.entidades.Zona[ zonid=" + zonid + " ]";
    }
    
}
