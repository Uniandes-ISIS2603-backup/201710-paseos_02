/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
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
}
