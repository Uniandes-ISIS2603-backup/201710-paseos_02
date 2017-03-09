/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
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
public class PaseoEcologicoPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public PaseoEcologicoEntity find(Long id)
    {
        return em.find(PaseoEcologicoEntity.class, id);
    }
    
    public List<PaseoEcologicoEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from PaseoEcologicoEntity u");
        return solicitud.getResultList();
    }
    
    public PaseoEcologicoEntity create(PaseoEcologicoEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public PaseoEcologicoEntity update(PaseoEcologicoEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        PaseoEcologicoEntity eliminado = em.find(PaseoEcologicoEntity.class, id);
        em.remove(eliminado);
    }    
}
