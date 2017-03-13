/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.FechaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Juan David Vega
 */
@Stateless
public class FechaPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public FechaEntity find(Long id)
    {
        return em.find(FechaEntity.class, id);
    }
    
    public List<FechaEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from FechaEntity u");
        return solicitud.getResultList();
    }
    
    public FechaEntity create(FechaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public FechaEntity update(FechaEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        FechaEntity eliminado = em.find(FechaEntity.class, id);
        em.remove(eliminado);
    }
    
}
