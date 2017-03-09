/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Diego Chaves
 */
@Stateless
public class ActividadPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public ActividadEntity find(Long id)
    {
        return em.find(ActividadEntity.class, id);
    }
    
    public List<ActividadEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from ActividadEntity u");
        return solicitud.getResultList();
    }
    
    public ActividadEntity create(ActividadEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ActividadEntity update(ActividadEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        ActividadEntity eliminado = em.find(ActividadEntity.class, id);
        em.remove(eliminado);
    }
}
