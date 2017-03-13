/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Maria del Rosario Leon
 */
@Stateless
public class OpinionParticipantePersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    public OpinionParticipanteEntity find(Long id)
    {
        return em.find(OpinionParticipanteEntity.class, id);
    }
    
    public List <OpinionParticipanteEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from OpinionParticipanteEntity u");
        return solicitud.getResultList();
    }
    
    public OpinionParticipanteEntity create(OpinionParticipanteEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public OpinionParticipanteEntity update(OpinionParticipanteEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        OpinionParticipanteEntity eliminado = em.find(OpinionParticipanteEntity.class, id);
        em.remove(eliminado);
    }
    
}
