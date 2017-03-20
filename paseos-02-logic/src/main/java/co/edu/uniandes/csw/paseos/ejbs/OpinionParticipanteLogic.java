/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.persistence.OpinionParticipantePersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Maria del Rosario Leon
 */
// TODO faltan las reglas de negocio. Al menos se debe verificar que el caminante existe, que el paseo existe y que ese caminante estuvo en un paseo.
@Stateless
public class OpinionParticipanteLogic
{
    @Inject private OpinionParticipantePersistence persistence;

    /**
     * Obtiene la lista de las opiniones de los participantes.
     *
     * @return colección de objetos de OpinionesParticipanesEntity.
     */
    public List<OpinionParticipanteEntity> getOpinionesParticipantes()
    {
        return persistence.findAll();
    }


    /**
     * Crea en la persistencia una nueva instancia de la clase OpinionParticipanteEntity.
     * @param entity instancia de la calse OpinionParticipanteEntity que se desea crear
     * @return La instancia creada.
     */
    public OpinionParticipanteEntity createOpinionParticipante (OpinionParticipanteEntity entity)
    {
       persistence.create(entity);
       return entity;
    }

    /**
     * Actualizar información de una instancia OpinionParticipanteEntity dada
     * @param entity instancia de la clase OpinionParticipanteEntity que se desea actualizar.
     * @return Instancia de la clase OpinionParticipanteEntity con la información actualizada.
     */
    public OpinionParticipanteEntity updateOpinionParticipante (OpinionParticipanteEntity entity)
    {
        return persistence.update(entity);
    }

    /**
     * Obtiene una instancia de la clase OpinionParticipanteEntity, a partir de un id.
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase OpinionParticipanteEntity, que se desea obtener.
     */
    public OpinionParticipanteEntity getOpinionParticipante (Long id)
    {
        return persistence.find(id);
    }

    /**
     * Elimina una instancia de la clase OpinionParticipanteEnity dada por su id.
     * @param id de la instancia que se quiere eliminar.
     */
    public void deleteOpinionParticipante (Long id)
    {
        persistence.delete(id);
    }
}
