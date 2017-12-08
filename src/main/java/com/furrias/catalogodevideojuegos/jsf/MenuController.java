/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furrias.catalogodevideojuegos.jsf;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.furrias.catalogodevideojuegos.entidad.Menu;

/**
 *
 * @author Roberto
 */
@Named("menuController")
@RequestScoped
public class MenuController {

    private List<Menu> menu = null;

    /**
     * Creates a new instance of Menu
     */
    public MenuController() {
        menu = new ArrayList();
        
        menu.add(new Menu("Videojuego", "Videojuego", "resources/imagenes/menu/Videojuego.jpg"));
        menu.add(new Menu("Saga", "Saga", "resources/imagenes/menu/Saga.jpg"));
        menu.add(new Menu("Etiqueta", "Etiqueta", "resources/imagenes/menu/Etiqueta.jpg"));
        menu.add(new Menu("GrupoDeEtiquetas", "Grupo/Etiquetas", "resources/imagenes/menu/GrupoEtiqueta.jpg"));
        menu.add(new Menu("Division", "Division", "resources/imagenes/menu/Division.jpg"));       
        menu.add(new Menu("Compañía", "Compañía", "resources/imagenes/menu/Compania.png"));
        menu.add(new Menu("Ubicación", "Ubicación", "resources/imagenes/menu/Ubicacion.png"));
        menu.add(new Menu("Plataforma", "Plataforma", "resources/imagenes/menu/Plataforma.png"));
        menu.add(new Menu("Estado", "Estado", "resources/imagenes/menu/Estado.png"));
    
    }
    public List<Menu> getMenu() {
        return menu;
    }
}
