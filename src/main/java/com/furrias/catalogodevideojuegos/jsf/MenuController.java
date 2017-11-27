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
        
        menu.add(new Menu("JSF-Pages/videojuego/List", "resources/imagenes/menu/Videojuego.jpg", "Videojuegos"));
        menu.add(new Menu("JSF-Pages/saga/List", "resources/imagenes/menu/Saga.jpg", "Saga"));
        menu.add(new Menu("JSF-Pages/etiqueta/List", "resources/imagenes/menu/Etiqueta.jpg", "Etiquetas"));
        menu.add(new Menu("JSF-Pages/grupoetiqueta/List", "resources/imagenes/menu/GrupoEtiqueta.jpg", "Grupo de etiquetas"));
        menu.add(new Menu("JSF-Pages/division/List", "resources/imagenes/menu/Division.jpg", "Division"));       
        menu.add(new Menu("JSF-Pages/compania/List", "resources/imagenes/menu/Compania.png", "Compañía"));
        menu.add(new Menu("JSF-Pages/ubicacion/List", "resources/imagenes/menu/Ubicacion.png", "Ubicación"));
        menu.add(new Menu("JSF-Pages/plataforma/List", "resources/imagenes/menu/Plataforma.png", "Plataforma"));
        menu.add(new Menu("JSF-Pages/estado/List", "resources/imagenes/menu/Estado.png", "Estado"));
    
    }
    public List<Menu> getMenu() {
        return menu;
    }
}
