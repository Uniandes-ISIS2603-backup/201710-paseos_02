/* 
 * The MIT License
 *
 * Copyright 2017 Treamwork - Team software development - Los Andes University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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

      
                    persistence.create(entity);
         
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
