/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.InscripcionPersistence;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author Sebastian Millan
 */

// TODO se deben definir la reglas de negocio. 
// TODO Las fecahs cualquiera sirve? la variable costo qué significa? por qué hay uno en el pasdeo y otro en la inscripción=? son distintos? cómo están relacionados?
@Stateless
public class InscripcionLogic 
{
     @Inject private InscripcionPersistence persistence;
     
     @Inject CaminanteLogic caminanteLogic;
    
    /**
     * Obtiene la lista de los registros de Inscripciones.
     *
     * @return Colección de objetos de InscripcionEntity.
     * 
     */
    public List<InscripcionEntity> getInscripciones(Long caminanteid) {
        CaminanteEntity caminante = caminanteLogic.getCaminante(caminanteid);
        return caminante.getPaseosInscritos();
    }

    /**
     * Se encarga de crear una Inscripcion en la base de datos.
     *
     * @param entity Objeto de InscripcionEntity con los datos nuevos
     * @return Objeto de Inscripcion con los datos nuevos y su ID.
     * @generated
     */
    public InscripcionEntity createInscripcion(InscripcionEntity entity) throws BusinessLogicException {
        
        Date actual = new Date();
        if(entity.getFechaDelPaseo().compareTo(actual)<0)
        {
            throw new BusinessLogicException("No cumple con las reglas del negocio");
        }
        return persistence.create(entity);
    }
    
    /**
     * Obtiene los datos de una instancia de Inscripcion a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de InscripcionEntity con los datos de la Inscripcion consultada.
     *
     */
    public InscripcionEntity getInscripcion(Long caminanteid, Long inscripcionid) {
        try {
            return persistence.find(caminanteid, inscripcionid);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La inscripcion no existe");
        }
    }
    
    /**
     * Actualiza la información de una instancia de Inscripcion.
     *
     * @param entity Instancia de InscripcionEntity con los nuevos datos.
     * @return Instancia de InscripcionEntity con los datos actualizados.
     * 
     */
   
    public InscripcionEntity updateInscripcion(Long caminanteid, Long inscripcionid, InscripcionEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Inscripcion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
   
    public void deleteInscripcion(Long caminanteid, Long id) {
        InscripcionEntity old  = getInscripcion(caminanteid, id);
        persistence.delete(old.getId());
    }
}
