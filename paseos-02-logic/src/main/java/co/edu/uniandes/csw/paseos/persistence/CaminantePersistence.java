/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class CaminantePersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public CaminanteEntity find(Long id)
    {
        return em.find(CaminanteEntity.class, id);
    }
    
    public List<CaminanteEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from CaminanteEntity u");
        return solicitud.getResultList();
    }
    
    public CaminanteEntity create(CaminanteEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public CaminanteEntity update(CaminanteEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, id);
        em.remove(eliminado);
    }        
}
