/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.bibl.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pawel
 */
@Stateless
public class WypozyczeniaFacade extends AbstractFacade<Wypozyczenia> {
    @PersistenceContext(unitName = "WebApplication5PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public WypozyczeniaFacade() {
        super(Wypozyczenia.class);
    }
    
}
