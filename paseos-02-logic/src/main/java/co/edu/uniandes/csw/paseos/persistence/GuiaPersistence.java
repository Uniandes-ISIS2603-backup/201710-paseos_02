/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
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
public class GuiaPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public GuiaEntity find(Long id)
    {
        return em.find(GuiaEntity.class, id);
    }
    
    public List<GuiaEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from GuiaEntity u");
        return solicitud.getResultList();
    }
    
    public GuiaEntity create(GuiaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public GuiaEntity update(GuiaEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        GuiaEntity eliminado = em.find(GuiaEntity.class, id);
        em.remove(eliminado);
    }
    
}
