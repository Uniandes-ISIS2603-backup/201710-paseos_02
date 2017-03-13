/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.paseos.persistence.OpinionParticipantePersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Maria del Rosario Leon
 */
@Stateless
public class OpinionParticipanteLogic
{
    @Inject private OpinionParticipantePersistence persistence;

    public List<OpinionParticipanteEntity> getOpinionesParticipantes()
    {
        return persistence.findAll();
    }

    public OpinionParticipanteEntity createOpinionParticipante (OpinionParticipanteEntity entity)
    {
       persistence.create(entity);
       return entity;
    }

    public OpinionParticipanteEntity updateOpinionParticipante (OpinionParticipanteEntity entity)
    {
        return persistence.update(entity);
    }

    public OpinionParticipanteEntity getOpinionParticipante (Long id)
    {
        return persistence.find(id);
    }

    public void deleteOpinionParticipante (Long id)
    {
        persistence.delete(id);
    }
}
