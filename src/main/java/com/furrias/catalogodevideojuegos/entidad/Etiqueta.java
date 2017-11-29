/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furrias.catalogodevideojuegos.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "etiqueta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etiqueta.findAll", query = "SELECT e FROM Etiqueta e")
    , @NamedQuery(name = "Etiqueta.findByIdEtiqueta", query = "SELECT e FROM Etiqueta e WHERE e.idEtiqueta = :idEtiqueta")
    , @NamedQuery(name = "Etiqueta.findByEtiqueta", query = "SELECT e FROM Etiqueta e WHERE e.etiqueta = :etiqueta")})
public class Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEtiqueta")
    private Integer idEtiqueta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Etiqueta")
    private String etiqueta;
    @ManyToMany(mappedBy = "etiquetaList")
    private List<Videojuego> videojuegoList;
    @JoinTable(name = "etiqueta_has_grupoetiqueta", joinColumns = {
        @JoinColumn(name = "Etiqueta_idEtiqueta", referencedColumnName = "idEtiqueta")}, inverseJoinColumns = {
        @JoinColumn(name = "GrupoEtiqueta_idGrupoEtiqueta", referencedColumnName = "idGrupoEtiqueta")})
    @ManyToMany
    private List<Grupoetiqueta> grupoetiquetaList;

    public Etiqueta() {
    }

    public Etiqueta(Integer idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public Etiqueta(Integer idEtiqueta, String etiqueta) {
        this.idEtiqueta = idEtiqueta;
        this.etiqueta = etiqueta;
    }

    public Integer getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Integer idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @XmlTransient
    public List<Videojuego> getVideojuegoList() {
        return videojuegoList;
    }

    public void setVideojuegoList(List<Videojuego> videojuegoList) {
        this.videojuegoList = videojuegoList;
    }

    @XmlTransient
    public List<Grupoetiqueta> getGrupoetiquetaList() {
        return grupoetiquetaList;
    }

    public void setGrupoetiquetaList(List<Grupoetiqueta> grupoetiquetaList) {
        this.grupoetiquetaList = grupoetiquetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtiqueta != null ? idEtiqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etiqueta)) {
            return false;
        }
        Etiqueta other = (Etiqueta) object;
        if ((this.idEtiqueta == null && other.idEtiqueta != null) || (this.idEtiqueta != null && !this.idEtiqueta.equals(other.idEtiqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.furrias.catalogodevideojuegos.entidad.Etiqueta[ idEtiqueta=" + idEtiqueta + " ]";
    }

}
