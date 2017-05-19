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

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Diego Chaves
 */
@Stateless
public class ActividadPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    /**
     * Obtiene una actividad según el id dado por parámetro.
     * @param paseoId
     * @param actividadId
     * @return actividad buscada.
     */
     public ActividadEntity find(Long paseoId, Long actividadId) {
       TypedQuery<ActividadEntity> q = em.createQuery("select p from ActividadEntity p where p.paseoEcologico.id = :paseoid and p.id = :actividadid", ActividadEntity.class);
        q.setParameter("paseoid", paseoId);
        q.setParameter("actividadid", actividadId);
        return q.getSingleResult();
    }

    /**
     * Obtiene una lista de todas las actividades.
     * @return lista de todas las actividades.
     */
     public List<ActividadEntity> findAll(Long paseoEcologicoid) {
        TypedQuery<ActividadEntity> q = em.createQuery("select p from ActividadEntity p where p.paseoEcologico.id = :paseoEcologicoid", ActividadEntity.class);
        q.setParameter("paseoEcologicoid", paseoEcologicoid);
        return q.getResultList();
    }
    
      /**
     * Crea una nuevoa actividad
     * @param entity actividad que se desea crear.
     * @return actividad que se creo.
     */
    public ActividadEntity create(ActividadEntity entity)
    {
        em.persist(entity);
        return entity;
    }
     /**
     * Modifica una  actividad
     * @param entity actividad que se desea modificar.
     * @return actividad que se modifico actualizada.
     */
    public ActividadEntity update(ActividadEntity entity)
    {
        return em.merge(entity);
    }
    /**
     * Elimina una  actividad
     * @param id id de la actividad que se desea eliminar.
     */
    public void delete(Long id)
    {
        ActividadEntity eliminado = em.find(ActividadEntity.class, id);
        em.remove(eliminado);
    }
}
