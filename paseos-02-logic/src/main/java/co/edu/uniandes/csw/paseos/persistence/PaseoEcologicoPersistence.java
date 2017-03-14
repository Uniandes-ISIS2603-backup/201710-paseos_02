/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
}
