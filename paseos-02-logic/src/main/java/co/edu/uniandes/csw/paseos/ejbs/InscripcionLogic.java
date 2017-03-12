/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.InscripcionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Millan
 */
@Stateless
public class InscripcionLogic 
{
     @Inject private InscripcionPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de Inscripciones.
     *
     * @return Colección de objetos de InscripcionEntity.
     * 
     */
    public List<InscripcionEntity> getInscripciones() {
        return persistence.findAll();
    }

    /**
     * Se encarga de crear una Inscripcion en la base de datos.
     *
     * @param entity Objeto de InscripcionEntity con los datos nuevos
     * @return Objeto de Inscripcion con los datos nuevos y su ID.
     * @generated
     */
    public InscripcionEntity createInscripcion(InscripcionEntity entity) throws BusinessLogicException {
        
        return persistence.create(entity);
    }
    
    /**
     * Obtiene los datos de una instancia de Inscripcion a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de InscripcionEntity con los datos de la Inscripcion consultada.
     *
     */
    public InscripcionEntity getInscripcion(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Actualiza la información de una instancia de Inscripcion.
     *
     * @param entity Instancia de InscripcionEntity con los nuevos datos.
     * @return Instancia de InscripcionEntity con los datos actualizados.
     * 
     */
   
    public InscripcionEntity updateInscripcion(InscripcionEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Inscripcion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
   
    public void deleteInscripcion(Long id) {
        persistence.delete(id);
    }
}
