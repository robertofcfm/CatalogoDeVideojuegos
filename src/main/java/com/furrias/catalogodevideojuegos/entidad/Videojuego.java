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
import javax.persistence.ManyToOne;
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
@Table(name = "videojuego")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Videojuego.findAll", query = "SELECT v FROM Videojuego v")
    , @NamedQuery(name = "Videojuego.findByIdVideojuego", query = "SELECT v FROM Videojuego v WHERE v.idVideojuego = :idVideojuego")
    , @NamedQuery(name = "Videojuego.findByVideojuego", query = "SELECT v FROM Videojuego v WHERE v.videojuego = :videojuego")
    , @NamedQuery(name = "Videojuego.findByNumJugadores", query = "SELECT v FROM Videojuego v WHERE v.numJugadores = :numJugadores")
    , @NamedQuery(name = "Videojuego.findByLanzamiento", query = "SELECT v FROM Videojuego v WHERE v.lanzamiento = :lanzamiento")})
public class Videojuego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVideojuego")
    private Integer idVideojuego;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Videojuego")
    private String videojuego;
    @Column(name = "NumJugadores")
    private Integer numJugadores;
    @Column(name = "Lanzamiento")
    private Integer lanzamiento;
    @JoinTable(name = "videojuego_has_etiqueta", joinColumns = {
        @JoinColumn(name = "Videojuego_idVideojuego", referencedColumnName = "idVideojuego")}, inverseJoinColumns = {
        @JoinColumn(name = "Etiqueta_idEtiqueta", referencedColumnName = "idEtiqueta")})
    @ManyToMany
    private List<Etiqueta> etiquetaList;

    @JoinColumn(name = "idDistribuidor", referencedColumnName = "idDivision")
    @ManyToOne
    private Division idDistribuidor;
    @JoinColumn(name = "idDesarrollador", referencedColumnName = "idDivision")
    @ManyToOne
    private Division idDesarrollador;
    @JoinColumn(name = "Estado_idEstado", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    private Estado estadoidEstado;

    @JoinTable(name = "videojuego_has_saga", joinColumns = {
        @JoinColumn(name = "Videojuego_idVideojuego", referencedColumnName = "idVideojuego")}, inverseJoinColumns = {
        @JoinColumn(name = "Saga_idSaga", referencedColumnName = "idSaga")})
    @ManyToMany
    private List<Saga> sagaList;

    @JoinColumn(name = "Ubicacion_idUbicacion", referencedColumnName = "idUbicacion")
    @ManyToOne
    private Ubicacion ubicacionidUbicacion;

    public Videojuego() {
    }

    public Videojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public Videojuego(Integer idVideojuego, String videojuego) {
        this.idVideojuego = idVideojuego;
        this.videojuego = videojuego;
    }

    public Integer getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(String videojuego) {
        this.videojuego = videojuego;
    }

    public Integer getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(Integer numJugadores) {
        this.numJugadores = numJugadores;
    }

    public Integer getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(Integer lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    @XmlTransient
    public List<Etiqueta> getEtiquetaList() {
        return etiquetaList;
    }

    public void setEtiquetaList(List<Etiqueta> etiquetaList) {
        this.etiquetaList = etiquetaList;
    }

    public Division getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Division idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public Division getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(Division idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public Estado getEstadoidEstado() {
        return estadoidEstado;
    }

    public void setEstadoidEstado(Estado estadoidEstado) {
        this.estadoidEstado = estadoidEstado;
    }

    @XmlTransient
    public List<Saga> getSagaList() {
        return sagaList;
    }

    public void setSagaList(List<Saga> sagaList) {
        this.sagaList = sagaList;
    }

    public Ubicacion getUbicacionidUbicacion() {
        return ubicacionidUbicacion;
    }

    public void setUbicacionidUbicacion(Ubicacion ubicacionidUbicacion) {
        this.ubicacionidUbicacion = ubicacionidUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVideojuego != null ? idVideojuego.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Videojuego)) {
            return false;
        }
        Videojuego other = (Videojuego) object;
        if ((this.idVideojuego == null && other.idVideojuego != null) || (this.idVideojuego != null && !this.idVideojuego.equals(other.idVideojuego))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.furrias.catalogodevideojuegos.entidad.Videojuego[ idVideojuego=" + idVideojuego + " ]";
    }

}
