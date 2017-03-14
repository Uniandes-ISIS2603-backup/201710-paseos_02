/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author María del Rosario León
 */
@Stateless
public class GuiaPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

    /**
     * Obtiene un Guia según el id dado por parámetro.
     * @param id id del guía buscado.
     * @return Guía buscado.
     */
    public GuiaEntity find(Long id)
    {
        return em.find(GuiaEntity.class, id);
    }

    /**
     * Obtiene todos los guias.
     * @return Lista con todos los guías.
     */
    public List<GuiaEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from GuiaEntity u");
        return solicitud.getResultList();
    }

    /**
     * Crea un nuevo guía.
     * @param entity guía que se desea crear.
     * @return guía que se creo.
     */
    public GuiaEntity create(GuiaEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica la información de un guía ya existente.
     * @param entity Guía con la nueva informaciión.
     * @return Guía con la información actualizada.
     */
    public GuiaEntity update(GuiaEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina un guía dado del id dado parametro.
     * @param id del guía que se quiere eliminar.
     */
    public void delete(Long id)
    {
        GuiaEntity eliminado = em.find(GuiaEntity.class, id);
        em.remove(eliminado);
    }
    
}
