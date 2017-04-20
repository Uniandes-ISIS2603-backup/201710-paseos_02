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

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sebastian Millan
 */
@Stateless
public class CalificacionPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
     protected EntityManager getEntityManager()
   {
       return em;
   }
    
    protected Class<CalificacionEntity> getEntityClass()
   {
       return CalificacionEntity.class;
   }
    
    /**
     * Obtiene una Calificacion según el id dado por parámetro.
     * @param id id de la calificación buscada.
     * @return calificación buscada.
     */
    public CalificacionEntity find(Long guiaid, Long calificacionid)
    {
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.guia.id = :guiaid) and (p.id = :calificacionid)", CalificacionEntity.class);
        q.setParameter("guiaid", guiaid);
        q.setParameter("calificacionid", calificacionid);
        return q.getSingleResult();
    }
    
    /**
     * Obtiene todas las calificaciones de los participantes.
     * @return Lista con todas las calificaciones de los participantes.
     */
    public List<CalificacionEntity> findAll(Long guiaid)
    {
         TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.guia.id = :guiaid)", CalificacionEntity.class);
        q.setParameter("guiaid", guiaid);
        return q.getResultList();
    }
    
    /**
     * Crea una nueva calificacion.
     * @param entity calificacion que se desea crear.
     * @return calificacion que se creo.
     */
    public CalificacionEntity create(CalificacionEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * Modifica la información de una calificación ya existente.
     * @param entity Calificacion con la nueva información.
     * @return Calificación con la información actualizada.
     */
    public CalificacionEntity update(CalificacionEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     * Elimina una Calificacion dado el id por parametro.
     * @param id de la opinion que se quiere eliminar.
     */
    public void delete(Long id)
    {
        CalificacionEntity eliminado = em.find(CalificacionEntity.class, id);
        em.remove(eliminado);
    }
    
}
