/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
     * @param id id de la actividad buscada.
     * @return actividad buscada.
     */
    public ActividadEntity find(Long id)
    {
        return em.find(ActividadEntity.class, id);
    }
    /**
     * Obtiene una lista de todas las actividades.
     * @return lista de todas las actividades.
     */
    public List<ActividadEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from ActividadEntity u");
        return solicitud.getResultList();
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
