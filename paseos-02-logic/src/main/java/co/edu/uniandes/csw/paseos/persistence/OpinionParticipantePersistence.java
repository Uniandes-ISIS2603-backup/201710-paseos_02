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
 * @author Maria del Rosario León
 */
@Stateless
public class OpinionParticipantePersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

    /**
     * Obtiene una OpinionParticipante según el id dado por parámetro.
     * @param id id de la opinion buscada.
     * @return opinion buscada.
     */
    public OpinionParticipanteEntity find(Long id)
    {
        return em.find(OpinionParticipanteEntity.class, id);
    }

    /**
     * Obtiene todos las opiniones de los participantes.
     * @return Lista con todas las opiniones de los participantes.
     */
    public List <OpinionParticipanteEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from OpinionParticipanteEntity u");
        return solicitud.getResultList();
    }

    /**
     * Crea una nueva opinion.
     * @param entity opinionque se desea crear.
     * @return opinion que se creo.
     */
    public OpinionParticipanteEntity create(OpinionParticipanteEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica la información de una opinion ya existente.
     * @param entity Opinion con la nueva informaciión.
     * @return Opinion con la información actualizada.
     */
    public OpinionParticipanteEntity update(OpinionParticipanteEntity entity)
    {
        return em.merge(entity);
    }


    /**
     * Elimina una Opinion dado del id dado parametro.
     * @param id de la opinion que se quiere eliminar.
     */
    public void delete(Long id)
    {
        OpinionParticipanteEntity eliminado = em.find(OpinionParticipanteEntity.class, id);
        em.remove(eliminado);
    }
    
}
