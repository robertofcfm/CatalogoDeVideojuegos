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
public class Estilo {

    private String bgColorBody = null;
    private String appBarColor = null;

    public Estilo() {
        bgColorBody = "";
        appBarColor = "";
    }

    public void setBgColorBody(String bgColorBody) {
        this.bgColorBody = bgColorBody;
    }
    
    public void setAppBarColor(String appBarColor){
        this.appBarColor = appBarColor;
    }

    public String getBgColorBody() {
        return bgColorBody;
    }
    
    public String getAppBarColor(){
        return appBarColor;
    }
    
}
