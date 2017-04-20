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

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;

/**
 *
 * @author Sebastian Millan
 */
@Stateless
public class InscripcionPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
   protected EntityManager getEntityManager()
   {
       return em;
   }
   
   protected Class<InscripcionEntity> getEntityClass()
   {
       return InscripcionEntity.class;
   }
    
    /**
     * Obtiene una Inscripcion según el id dado por parámetro.
     * @param id id de la inscripción buscada.
     * @return inscripción buscada.
     */
    public InscripcionEntity find(Long caminanteid, Long inscripcionid)
    {
        TypedQuery<InscripcionEntity> q = em.createQuery("select p from InscripionEntity p where (p.caminante.id = :caminanteid) and (p.id = :inscripcionid)", InscripcionEntity.class);
        q.setParameter("caminanteid", caminanteid);
        q.setParameter("inscripcionid", inscripcionid);
        return q.getSingleResult();
    }
    
    /**
     * Obtiene todas las inscripciones de los participantes.
     * @return Lista con todas las inscripciones de los participantes.
     */
    public List<InscripcionEntity> findAll(Long caminanteid)
    {
        TypedQuery<InscripcionEntity> q = em.createQuery("select p from InscripcionEntity p where (p.caminante.id = :caminanteid)", InscripcionEntity.class);
        q.setParameter("caminanteid", caminanteid);
        return q.getResultList();
    }
    
    /**
     * Crea una nueva inscripción.
     * @param entity inscripción que se desea crear.
     * @return inscripción que se creo.
     */
    public InscripcionEntity create(InscripcionEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * Modifica la información de una inscripción ya existente.
     * @param entity Inscripción con la nueva información.
     * @return Inscripción con la información actualizada.
     */
    public InscripcionEntity update(InscripcionEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     * Elimina una inscripción dado el id por parametro.
     * @param id de la opinion que se quiere eliminar.
     */
    public void delete(Long id)
    {
        InscripcionEntity eliminado = em.find(InscripcionEntity.class, id);
        em.remove(eliminado);
    }
    
    /**
     * Obtiene todas las inscripciones de un paseo ecologico.
     * @param id del paseo ecologico al que se le quiere obtener todas sus inscripciones.
     * @return Lista con todas las inscripciones de un paseo ecologico.
     */
    public List<InscripcionEntity> inscripcionesPorPaseo(Long id)
    {
        TypedQuery<InscripcionEntity> q = em.createQuery("SELECT A FROM InscripcionEntity A WHERE A.instanciaPaseo.paseoEcologico.id = :id", InscripcionEntity.class);

        q.setParameter("id", id);

        List<InscripcionEntity> res = q.getResultList();

        return res;
    }
    
}
