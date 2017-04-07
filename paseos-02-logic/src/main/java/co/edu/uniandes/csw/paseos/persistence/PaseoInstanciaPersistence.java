/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
