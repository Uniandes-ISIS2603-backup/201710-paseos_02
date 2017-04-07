/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.persistence.OpinionParticipantePersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Maria del Rosario Leon
 */
@Stateless
public class OpinionParticipanteLogic {
    @Inject
    private OpinionParticipantePersistence persistence;
    private CaminanteLogic camLog;
    private InscripcionLogic inscrp;

    /**
     * Obtiene la lista de las opiniones de los participantes.
     *
     * @return colección de objetos de OpinionesParticipanesEntity.
     */
    public List<OpinionParticipanteEntity> getOpinionesParticipantes() {
        return persistence.findAll();
    }


    /**
     * Crea en la persistencia una nueva instancia de la clase OpinionParticipanteEntity.
     *
     * @param entity instancia de la calse OpinionParticipanteEntity que se desea crear
     * @return La instancia creada.
     */
//    Todo revizar
    public OpinionParticipanteEntity createOpinionParticipante(OpinionParticipanteEntity entity) throws Exception {

        Long idCam = entity.getCaminante().getId();
        long idPaseo = entity.getPaseoEcologico().getId();
        if ( camLog.getCaminante(idCam)!= null)
        {
           List<InscripcionEntity> list = inscrp.getInscripciones(idCam);
            for (InscripcionEntity inscripcion: list)
            {
                if(inscripcion.getCaminante().getId().equals(idCam) && inscripcion.getInstanciaPaseo().getPaseoEcologico().getId().equals(idPaseo))
                {
                    persistence.create(entity);
                }
                else
                {
                    throw new Exception("El caminante no se encuentra registrado al paseo correspondiente, no se puede crear la opinion");
                }
            }
        }
        else
        {
            throw new Exception("El caminante no existe, no se puede crear la opinion");
        }
        return entity;
    }

    /**
     * Actualizar información de una instancia OpinionParticipanteEntity dada
     *
     * @param entity instancia de la clase OpinionParticipanteEntity que se desea actualizar.
     * @return Instancia de la clase OpinionParticipanteEntity con la información actualizada.
     */
    public OpinionParticipanteEntity updateOpinionParticipante(OpinionParticipanteEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Obtiene una instancia de la clase OpinionParticipanteEntity, a partir de un id.
     *
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase OpinionParticipanteEntity, que se desea obtener.
     */
    public OpinionParticipanteEntity getOpinionParticipante(Long id) {
        return persistence.find(id);
    }

    /**
     * Elimina una instancia de la clase OpinionParticipanteEnity dada por su id.
     *
     * @param id de la instancia que se quiere eliminar.
     */
    public void deleteOpinionParticipante(Long id) {
        persistence.delete(id);
    }
}
