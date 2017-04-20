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

import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Juan David Vega
 */
@Stateless
public class PaseoInstanciaPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

     public PaseoInstanciaEntity find(Long paseoId, Long instanciaId) {
        TypedQuery<PaseoInstanciaEntity> q = em.createQuery("select p from PaseoInstanciaEntity p where (p.paseoEcologico.id = :paseoid) and (p.id = :instanciaid)", PaseoInstanciaEntity.class);
        q.setParameter("paseoid", paseoId);
        q.setParameter("instanciaid", instanciaId);
        return q.getSingleResult();
    }

    public List<PaseoInstanciaEntity> findAll(Long paseoId) {
        TypedQuery<PaseoInstanciaEntity> q = em.createQuery("select p from PaseoInstanciaEntity p where (p.paseoEcologico.id = :paseoid)", PaseoInstanciaEntity.class);
        q.setParameter("paseoid", paseoId);
        return q.getResultList();
    }

    /**
     * Crea una nueva fecha.
     * @param entity fecha que se desea crear.
     * @return fecha que se creo.
     */
    public PaseoInstanciaEntity create(PaseoInstanciaEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica la información de una fecha ya existente.
     * @param entity fecha con la nueva informaciión.
     * @return fecha con la información actualizada.
     */
    public PaseoInstanciaEntity update(PaseoInstanciaEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina una fecha dado del id dado parametro.
     * @param id de la fecha que se quiere eliminar.
     */
    public void delete(Long id)
    {
        PaseoInstanciaEntity eliminado = em.find(PaseoInstanciaEntity.class, id);
        em.remove(eliminado);
    }
    
}
