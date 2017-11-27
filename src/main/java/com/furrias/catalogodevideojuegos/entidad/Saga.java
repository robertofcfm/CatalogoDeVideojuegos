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
@Table(name = "saga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saga.findAll", query = "SELECT s FROM Saga s")
    , @NamedQuery(name = "Saga.findByIdSaga", query = "SELECT s FROM Saga s WHERE s.idSaga = :idSaga")
    , @NamedQuery(name = "Saga.findBySaga", query = "SELECT s FROM Saga s WHERE s.saga = :saga")})
public class Saga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSaga")
    private Integer idSaga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Saga")
    private String saga;
    @OneToMany(mappedBy = "sagaidSaga")
    private List<Videojuego> videojuegoList;

    public Saga() {
    }

    public Saga(Integer idSaga) {
        this.idSaga = idSaga;
    }

    public Saga(Integer idSaga, String saga) {
        this.idSaga = idSaga;
        this.saga = saga;
    }

    public Integer getIdSaga() {
        return idSaga;
    }

    public void setIdSaga(Integer idSaga) {
        this.idSaga = idSaga;
    }

    public String getSaga() {
        return saga;
    }

    public void setSaga(String saga) {
        this.saga = saga;
    }

    @XmlTransient
    public List<Videojuego> getVideojuegoList() {
        return videojuegoList;
    }

    public void setVideojuegoList(List<Videojuego> videojuegoList) {
        this.videojuegoList = videojuegoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSaga != null ? idSaga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saga)) {
            return false;
        }
        Saga other = (Saga) object;
        if ((this.idSaga == null && other.idSaga != null) || (this.idSaga != null && !this.idSaga.equals(other.idSaga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.furrias.catalogodevideojuegos.entidad.Saga[ idSaga=" + idSaga + " ]";
    }
    
}
