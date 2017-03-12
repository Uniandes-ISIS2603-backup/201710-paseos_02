/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.ActividadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Diego Chaves
 */
@Stateless
public class ActividadLogic 
{
    @Inject 
    private ActividadPersistence persistence;
    
     /**
     * Obtiene la lista de los registros de Actividad.
     *
     * @return Colección de objetos de ActividadEntity.
     *
     */
    public List<ActividadEntity> getActividades() {
        return persistence.findAll();
    }
    
      /**
     * Obtiene los datos de una instancia de Actividad a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ActividadEntity con los datos de la Actividad consultada.
     *
     */
    public ActividadEntity getActividad(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear una Actividad en la base de datos.
     *
     * @param entity Objeto de ActividadEntity con los datos nuevos
     * @return Objeto de ActividadEntity con los datos nuevos y su ID.
     *
     */
    public ActividadEntity createActividad(ActividadEntity entity) throws BusinessLogicException {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Actvidad.
     *
     * @param entity Instancia de ActividadEntity con los nuevos datos.
     * @return Instancia de ActividadEntity con los datos actualizados.
     *
     */
    public ActividadEntity updateActividad(ActividadEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Actividad de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    public void deleteActividad(Long id) {
        persistence.delete(id);
    }
}
