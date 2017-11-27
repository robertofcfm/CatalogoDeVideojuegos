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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "division")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Division.findAll", query = "SELECT d FROM Division d")
    , @NamedQuery(name = "Division.findByIdDivision", query = "SELECT d FROM Division d WHERE d.idDivision = :idDivision")
    , @NamedQuery(name = "Division.findByDivision", query = "SELECT d FROM Division d WHERE d.division = :division")})
public class Division implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDivision")
    private Integer idDivision;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Division")
    private String division;
    @JoinColumn(name = "Compania_idDivision", referencedColumnName = "idCompania")
    @ManyToOne
    private Compania companiaidDivision;
    @OneToMany(mappedBy = "idDistribuidor")
    private List<Videojuego> videojuegoList;
    @OneToMany(mappedBy = "idDesarrollador")
    private List<Videojuego> videojuegoList1;

    public Division() {
    }

    public Division(Integer idDivision) {
        this.idDivision = idDivision;
    }

    public Division(Integer idDivision, String division) {
        this.idDivision = idDivision;
        this.division = division;
    }

    public Integer getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(Integer idDivision) {
        this.idDivision = idDivision;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Compania getCompaniaidDivision() {
        return companiaidDivision;
    }

    public void setCompaniaidDivision(Compania companiaidDivision) {
        this.companiaidDivision = companiaidDivision;
    }

    @XmlTransient
    public List<Videojuego> getVideojuegoList() {
        return videojuegoList;
    }

    public void setVideojuegoList(List<Videojuego> videojuegoList) {
        this.videojuegoList = videojuegoList;
    }

    @XmlTransient
    public List<Videojuego> getVideojuegoList1() {
        return videojuegoList1;
    }

    public void setVideojuegoList1(List<Videojuego> videojuegoList1) {
        this.videojuegoList1 = videojuegoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDivision != null ? idDivision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Division)) {
            return false;
        }
        Division other = (Division) object;
        if ((this.idDivision == null && other.idDivision != null) || (this.idDivision != null && !this.idDivision.equals(other.idDivision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.furrias.catalogodevideojuegos.entidad.Division[ idDivision=" + idDivision + " ]";
    }
    
}
