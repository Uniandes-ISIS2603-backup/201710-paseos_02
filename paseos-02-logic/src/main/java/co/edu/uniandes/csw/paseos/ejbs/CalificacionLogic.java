/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import co.edu.uniandes.csw.paseos.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.CaminantePersistence;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author Sebastián Millán
 */
// TODO faltan las reglas de negocio. Al menos se debe verificar que el caminante existe, que el paseo existe y que ese caminante estuvo en un paseo con ese guía.

@Stateless
public class CalificacionLogic 
{
    @Inject private CalificacionPersistence persistence;
    @Inject CaminanteLogic CaminLogic;
    
    /**
     * Obtiene la lista de los registros de Calificaciones.
     *
     * @return Colección de objetos de CalificacionEntity.
     * 
     */
    public List<CalificacionEntity> getCalificaciones(Long caminanteId) throws BusinessLogicException
    {
        CaminanteEntity caminante = CaminLogic.getCaminante(caminanteId);
        if(caminante==null)
        {
            throw new WebApplicationException("No existe el caminante",404);
        }
        else
        {
            return persistence.findAll();
        }
    }

    /**
     * Se encarga de crear una Calificacion en la base de datos.
     *
     * @param entity Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de Calificacion con los datos nuevos y su ID.
     * @generated
     */
    public CalificacionEntity createCalificacion(CalificacionEntity entity) throws BusinessLogicException {
        
        return persistence.create(entity);
    }
    
    /**
     * Actualiza la información de una instancia de Calificacion.
     *
     * @param entity Instancia de CalificacionEntity con los nuevos datos.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     * 
     */
   
    public CalificacionEntity updateEmployee(CalificacionEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CalificacionEntity con los datos de la Calificacion consultada.
     *
     */
    public CalificacionEntity getCalificacion(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Elimina una instancia de Calificacion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
   
    public void deleteCalificacion(Long id) {
        persistence.delete(id);
    }
    
    public void verificarDatos(CalificacionEntity entity) throws BusinessLogicException     
    {
       if(persCamin.)
    }
}
