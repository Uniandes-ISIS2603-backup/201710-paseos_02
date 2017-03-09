/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
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
public class InscripcionPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public InscripcionEntity find(Long id)
    {
        return em.find(InscripcionEntity.class, id);
    }
    
    public List<InscripcionEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from InscripcionEntity u");
        return solicitud.getResultList();
    }
    
    public InscripcionEntity create(InscripcionEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public InscripcionEntity update(InscripcionEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        InscripcionEntity eliminado = em.find(InscripcionEntity.class, id);
        em.remove(eliminado);
    }
    
}
