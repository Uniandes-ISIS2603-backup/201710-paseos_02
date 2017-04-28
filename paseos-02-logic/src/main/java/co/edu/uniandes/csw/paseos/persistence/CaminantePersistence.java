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

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class CaminantePersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

    /**
     * Obtiene un caminante según el id dado por parámetro.
     * @param id id del caminante buscado.
     * @return caminante buscado.
     */
    public CaminanteEntity find(Long id)
    {
        return em.find(CaminanteEntity.class, id);
    }

    /**
     * Obtiene todos los caminantes.
     * @return Lista con todos los caminantes.
     */
    public List<CaminanteEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from CaminanteEntity u");
        return solicitud.getResultList();
    }

    /**
     * Crea un nuevo caminante
     * @param entity caminante que se desea crear.
     * @return caminante que se creo.
     */
    public CaminanteEntity create(CaminanteEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica la información de un caminante ya existente.
     * @param entity Caminante con la nueva informaciión.
     * @return caminante con la información actualizada.
     */
    public CaminanteEntity update(CaminanteEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina un caminante dado del id dado parametro.
     * @param id del caminante que se quiere eliminar.
     */
    public void delete(Long id)
    {
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, id);
        em.remove(eliminado);
    }        
    
    public CaminanteEntity encontrarPorIdentificacion( Integer identificacion )
    {
        TypedQuery<CaminanteEntity> q
                = em.createQuery("select u from CaminanteEntity u where u.identificacion = :identificacion", CaminanteEntity.class);
        q = q.setParameter("identificacion", identificacion);
        
       List<CaminanteEntity> mismoId = q.getResultList();
        if (mismoId.isEmpty() ) {
            return null; 
        } else {
            return mismoId.get(0);
        }
    }
}
