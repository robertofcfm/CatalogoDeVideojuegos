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
@Table(name = "grupoetiqueta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupoetiqueta.findAll", query = "SELECT g FROM Grupoetiqueta g")
    , @NamedQuery(name = "Grupoetiqueta.findByIdGrupoEtiqueta", query = "SELECT g FROM Grupoetiqueta g WHERE g.idGrupoEtiqueta = :idGrupoEtiqueta")
    , @NamedQuery(name = "Grupoetiqueta.findByGrupoEtiqueta", query = "SELECT g FROM Grupoetiqueta g WHERE g.grupoEtiqueta = :grupoEtiqueta")})
public class Grupoetiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoEtiqueta")
    private Integer idGrupoEtiqueta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "GrupoEtiqueta")
    private String grupoEtiqueta;
    @ManyToMany(mappedBy = "grupoetiquetaList")
    private List<Etiqueta> etiquetaList;

    public Grupoetiqueta() {
    }

    public Grupoetiqueta(Integer idGrupoEtiqueta) {
        this.idGrupoEtiqueta = idGrupoEtiqueta;
    }

    public Grupoetiqueta(Integer idGrupoEtiqueta, String grupoEtiqueta) {
        this.idGrupoEtiqueta = idGrupoEtiqueta;
        this.grupoEtiqueta = grupoEtiqueta;
    }

    public Integer getIdGrupoEtiqueta() {
        return idGrupoEtiqueta;
    }

    public void setIdGrupoEtiqueta(Integer idGrupoEtiqueta) {
        this.idGrupoEtiqueta = idGrupoEtiqueta;
    }

    public String getGrupoEtiqueta() {
        return grupoEtiqueta;
    }

    public void setGrupoEtiqueta(String grupoEtiqueta) {
        this.grupoEtiqueta = grupoEtiqueta;
    }

    @XmlTransient
    public List<Etiqueta> getEtiquetaList() {
        return etiquetaList;
    }

    public void setEtiquetaList(List<Etiqueta> etiquetaList) {
        this.etiquetaList = etiquetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoEtiqueta != null ? idGrupoEtiqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoetiqueta)) {
            return false;
        }
        Grupoetiqueta other = (Grupoetiqueta) object;
        if ((this.idGrupoEtiqueta == null && other.idGrupoEtiqueta != null) || (this.idGrupoEtiqueta != null && !this.idGrupoEtiqueta.equals(other.idGrupoEtiqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.furrias.catalogodevideojuegos.entidad.Grupoetiqueta[ idGrupoEtiqueta=" + idGrupoEtiqueta + " ]";
    }
    
}
