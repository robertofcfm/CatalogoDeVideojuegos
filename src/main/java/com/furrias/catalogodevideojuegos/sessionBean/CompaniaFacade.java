/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furrias.catalogodevideojuegos.sessionBean;

import com.furrias.catalogodevideojuegos.entidad.Compania;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Roberto
 */
@Stateless
public class CompaniaFacade extends AbstractFacade<Compania> {

    @PersistenceContext(unitName = "com.furrias_CatalogoDeVideojuegos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompaniaFacade() {
        super(Compania.class);
    }
    
}
