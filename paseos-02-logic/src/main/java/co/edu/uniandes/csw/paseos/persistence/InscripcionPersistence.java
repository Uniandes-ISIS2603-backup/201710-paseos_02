/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.persistence;

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
public class InscripcionPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;
    
    /**
     * Obtiene una Inscripcion según el id dado por parámetro.
     * @param id id de la inscripción buscada.
     * @return inscripción buscada.
     */
    public InscripcionEntity find(Long id)
    {
        return em.find(InscripcionEntity.class, id);
    }
    
    /**
     * Obtiene todas las inscripciones de los participantes.
     * @return Lista con todas las inscripciones de los participantes.
     */
    public List<InscripcionEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from InscripcionEntity u");
        return solicitud.getResultList();
    }
    
    /**
     * Crea una nueva inscripción.
     * @param entity inscripción que se desea crear.
     * @return inscripción que se creo.
     */
    public InscripcionEntity create(InscripcionEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * Modifica la información de una inscripción ya existente.
     * @param entity Inscripción con la nueva información.
     * @return Inscripción con la información actualizada.
     */
    public InscripcionEntity update(InscripcionEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     * Elimina una inscripción dado el id por parametro.
     * @param id de la opinion que se quiere eliminar.
     */
    public void delete(Long id)
    {
        InscripcionEntity eliminado = em.find(InscripcionEntity.class, id);
        em.remove(eliminado);
    }
    
    /**
     * Obtiene todas las inscripciones de un paseo ecologico.
     * @param id del paseo ecologico al que se le quiere obtener todas sus inscripciones.
     * @return Lista con todas las inscripciones de un paseo ecologico.
     */
    public List<InscripcionEntity> inscripcionesPorPaseo(Long id)
    {
        TypedQuery<InscripcionEntity> q = em.createQuery("SELECT A FROM InscripcionEntity A WHERE A.paseoEcologico.id = :id", InscripcionEntity.class);

        q.setParameter("id", id);

        List<InscripcionEntity> res = q.getResultList();

        return res;
    }
    
}
