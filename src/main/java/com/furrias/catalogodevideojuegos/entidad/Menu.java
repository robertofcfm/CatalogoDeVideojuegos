/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furrias.catalogodevideojuegos.entidad;

/**
 *
 * @author Roberto
 */
public class Menu {

    private String outcome = null;
    private String imagen = null;
    private String nombre = null;

    /**
     * Creates a new instance of Menu
     */
    public Menu() {
        outcome = "";
        nombre = "";
        imagen = "";
    }

    public Menu(String outcome, String nombre, String imagen) {
        this.outcome = outcome;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
