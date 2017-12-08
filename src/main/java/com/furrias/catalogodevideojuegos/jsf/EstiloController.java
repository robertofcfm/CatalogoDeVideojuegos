/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furrias.catalogodevideojuegos.jsf;

import com.furrias.catalogodevideojuegos.entidad.Estilo;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Roberto
 */
@Named("estiloController")
@RequestScoped
public class EstiloController {

    private Estilo estilo;

    public EstiloController() {
        estilo = new Estilo();
        estilo.setBgColorBody("bg-steel");
        estilo.setAppBarColor("darcula");
    }

    public String getBgColorBody() {
        return estilo.getBgColorBody();
    }

    public String getAppBarColor() {
        return estilo.getAppBarColor();
    }

}
