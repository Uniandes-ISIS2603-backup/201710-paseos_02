/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sebastian Millan
 */
@Stateless
public class CalificacionPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public CalificacionEntity find(Long id)
    {
        return em.find(CalificacionEntity.class, id);
    }
    
    public List<CalificacionEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from CalificacionEntity u");
        return solicitud.getResultList();
    }
    
    public CalificacionEntity create(CalificacionEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public CalificacionEntity update(CalificacionEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        CalificacionEntity eliminado = em.find(CalificacionEntity.class, id);
        em.remove(eliminado);
    }
    
}
