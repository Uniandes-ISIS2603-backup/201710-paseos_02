/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.FechaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Juan David Vega
 */
@Stateless
public class FechaPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

    /**
     * Obtiene un Fecha según el id dado por parámetro.
     * @param id id de la fecha buscada.
     * @return Fecha buscada.
     */
    public FechaEntity find(Long id)
    {
        return em.find(FechaEntity.class, id);
    }

    /**
     * Obtiene todos las fechas.
     * @return Lista con todas las fechas.
     */
    public List<FechaEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from FechaEntity u");
        return solicitud.getResultList();
    }

    /**
     * Crea una nueva fecha.
     * @param entity fecha que se desea crear.
     * @return fecha que se creo.
     */
    public FechaEntity create(FechaEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica la información de una fecha ya existente.
     * @param entity fecha con la nueva informaciión.
     * @return fecha con la información actualizada.
     */
    public FechaEntity update(FechaEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina una fecha dado del id dado parametro.
     * @param id de la fecha que se quiere eliminar.
     */
    public void delete(Long id)
    {
        FechaEntity eliminado = em.find(FechaEntity.class, id);
        em.remove(eliminado);
    }
    
}
