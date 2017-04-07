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
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
/**
 *deleteInscripcion
 * @author Sebastián Millán
 */
// TODO faltan las reglas de negocio. Al menos se debe verificar que el caminante existe, que el paseo existe y que ese caminante estuvo en un paseo con ese guía.

@Stateless
public class CalificacionLogic 
{
    @Inject private CalificacionPersistence persistence;
    @Inject GuiaLogic guiaLogic;
    
    /**
     * Obtiene la lista de los registros de Calificaciones.
     *
     * @return Colección de objetos de CalificacionEntity.
     * 
     */
    public List<CalificacionEntity> getCalificaciones(Long guiaid) throws BusinessLogicException
    {
            GuiaEntity guia = guiaLogic.getGuia(guiaid);
            return guia.getCalificaciones();    
    }

    /**
     * Se encarga de crear una Calificacion en la base de datos.
     *
     * @param entity Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de Calificacion con los datos nuevos y su ID.
     * @generated
     */
    public CalificacionEntity createCalificacion(CalificacionEntity entity) throws BusinessLogicException {
        
        verificarReglasNegocio(entity);
        return persistence.create(entity);
    }
    
    /**
     * Actualiza la información de una instancia de Calificacion.
     *
     * @param entity Instancia de CalificacionEntity con los nuevos datos.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     * 
     */
   
    public CalificacionEntity updateCalificacion(CalificacionEntity entity) throws BusinessLogicException{
        verificarReglasNegocio(entity);
        return persistence.update(entity);
    }
    
    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CalificacionEntity con los datos de la Calificacion consultada.
     *
     */
    public CalificacionEntity getCalificacion(Long guiaid, Long id) {
        try{
            return persistence.find(guiaid,id);
        }
        catch(NoResultException e)
        {
            throw new IllegalArgumentException("La calificacion no existe");
        }
        
    }
    
    /**
     * Elimina una instancia de Calificacion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
   
    public void deleteCalificacion(Long guiaid, Long id) {
        CalificacionEntity old  = getCalificacion(guiaid,id);
        persistence.delete(id);
    }
    
    /*public void verificarDatos(CalificacionEntity entity) throws BusinessLogicException     
    {
       if(persCamin.)
    }*/
    
    public void verificarReglasNegocio(CalificacionEntity entity) throws BusinessLogicException
    {
       boolean id = entity.getId() == null;
       boolean puntuacion = entity.getPuntuacion()>5;
       if (id || puntuacion)
       {
           throw new BusinessLogicException("No se están cumpliendo las reglas de negocio.");
       }
    }
}
