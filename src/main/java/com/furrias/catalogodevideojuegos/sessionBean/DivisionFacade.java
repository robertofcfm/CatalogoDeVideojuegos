/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furrias.catalogodevideojuegos.sessionBean;

import com.furrias.catalogodevideojuegos.entidad.Division;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Roberto
 */
@Stateless
public class DivisionFacade extends AbstractFacade<Division> {

    @PersistenceContext(unitName = "com.furrias_CatalogoDeVideojuegos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DivisionFacade() {
        super(Division.class);
    }
    
}
