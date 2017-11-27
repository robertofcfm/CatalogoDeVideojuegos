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

    private String link = null;
    private String imagen = null;
    private String nombre = null;

    /**
     * Creates a new instance of Menu
     */
    public Menu() {
        link = "";
        imagen = "";
        nombre = "";
    }

    public Menu(String link, String imagen, String nombre) {
        this.link = link;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
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
