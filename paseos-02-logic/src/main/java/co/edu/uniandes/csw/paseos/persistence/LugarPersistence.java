/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andrea Lopez
 */
@Stateless
public class LugarPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public LugarEntity find(Long id)
    {
        return em.find(LugarEntity.class, id);
    }
    
    public List<LugarEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from LugarEntity u");
        return solicitud.getResultList();
    }
    
    public LugarEntity create(LugarEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public LugarEntity update(LugarEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        LugarEntity eliminado = em.find(LugarEntity.class, id);
        em.remove(eliminado);
    }
    
}
