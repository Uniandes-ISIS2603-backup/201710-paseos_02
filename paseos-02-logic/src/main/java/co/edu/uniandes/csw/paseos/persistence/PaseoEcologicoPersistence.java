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

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
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
public class PaseoEcologicoPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

    /**
     * Obtiene el paseo ecológico dado por parámetro.
     * @param id del paeo ecológico
     * @return paseo ecológico buscado.
     */
    public PaseoEcologicoEntity find(Long id)
    {
        return em.find(PaseoEcologicoEntity.class, id);
    }

    /**
     * Obtiene todos los paseos ecológicos.
     * @return lista de paseos ecológicos.
     */
    public List<PaseoEcologicoEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from PaseoEcologicoEntity u");
        return solicitud.getResultList();
    }

    /**
     * Crea un nuevo paseo ecológico.
     * @param entity paseo ecológico que se desea crear.
     * @return nuevo paseo ecológico.
     */
    public PaseoEcologicoEntity create(PaseoEcologicoEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica el paseo ecologico
     * @param entity paseo con la informacion actualizada.
     * @return paseo con la informacion actualizada.
     */
    public PaseoEcologicoEntity update(PaseoEcologicoEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina un Paseo Ecológico
     * @param id del peseo que se desea eliminar.
     */
    public void delete(Long id)
    {
        PaseoEcologicoEntity eliminado = em.find(PaseoEcologicoEntity.class, id);
        em.remove(eliminado);
    }    
    
    public List<PaseoEcologicoEntity> darPaseosSegunTematica(String tematica)
    {
        TypedQuery<PaseoEcologicoEntity> q = em.createQuery("SELECT A FROM PaseoEcologicoEntity A WHERE A.tematica = :tematica", PaseoEcologicoEntity.class);

        q.setParameter("tematica", tematica);

        List<PaseoEcologicoEntity> res = q.getResultList();

        return res;
    }
    
    public List<PaseoEcologicoEntity> darPaseosSegunCosto(Double costo)
    {
        TypedQuery<PaseoEcologicoEntity> q = em.createQuery("SELECT A FROM PaseoEcologicoEntity A WHERE A.costo = :costo", PaseoEcologicoEntity.class);

        q.setParameter("costo", costo);

        List<PaseoEcologicoEntity> res = q.getResultList();

        return res;
    }
    
    public List<PaseoEcologicoEntity> darPaseosSegunLugarDeEncuentro(String nombre)
    {
        TypedQuery<PaseoEcologicoEntity> q = em.createQuery("SELECT A FROM PaseoEcologicoEntity A WHERE A.lugarDeEncuentro.nombre = :nombre", PaseoEcologicoEntity.class);

        q.setParameter("nombre", nombre);

        List<PaseoEcologicoEntity> res = q.getResultList();

        return res;
    }
    
    public List<PaseoEcologicoEntity> darPaseosSegunLugarDeDestino(String nombre)
    {
        TypedQuery<PaseoEcologicoEntity> q = em.createQuery("SELECT A FROM PaseoEcologicoEntity A WHERE A.lugarDeDestino.nombre = :nombre", PaseoEcologicoEntity.class);

        q.setParameter("nombre", nombre);

        List<PaseoEcologicoEntity> res = q.getResultList();

        return res;
    }
}
