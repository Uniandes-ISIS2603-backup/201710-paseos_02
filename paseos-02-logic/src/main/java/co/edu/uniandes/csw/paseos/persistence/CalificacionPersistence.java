/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sebastian Millan
 */
@Stateless
public class CalificacionPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    /**
     * Obtiene una Calificacion según el id dado por parámetro.
     * @param id id de la calificación buscada.
     * @return calificación buscada.
     */
    public CalificacionEntity find(Long id)
    {
        return em.find(CalificacionEntity.class, id);
    }
    
    /**
     * Obtiene todas las calificaciones de los participantes.
     * @return Lista con todas las calificaciones de los participantes.
     */
    public List<CalificacionEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from CalificacionEntity u");
        return solicitud.getResultList();
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
