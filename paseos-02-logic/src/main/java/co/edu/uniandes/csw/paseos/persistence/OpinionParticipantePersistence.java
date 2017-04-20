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
     * Obtiene todas las opiniones de los participantes.
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
     * @param entity Opinion con la nueva información.
     * @return Opinion con la información actualizada.
     */
    public OpinionParticipanteEntity update(OpinionParticipanteEntity entity)
    {
        return em.merge(entity);
    }


    /**
     * Elimina una Opinion dado el id por parametro.
     * @param id de la opinion que se quiere eliminar.
     */
    public void delete(Long id)
    {
        OpinionParticipanteEntity eliminado = em.find(OpinionParticipanteEntity.class, id);
        em.remove(eliminado);
    }
    
}
