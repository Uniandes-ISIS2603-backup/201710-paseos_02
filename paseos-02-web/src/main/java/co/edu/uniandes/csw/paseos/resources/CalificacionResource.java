/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.CalificacionLogic;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastián Millán
 */
@Path("/caminantes/îd \\\\d+}/calificaciones")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionResource 
{
    @Inject private CalificacionLogic calificacionLogic;
    
    /**
     * Convierte una lista de CalififcacionEntity a una lista de CalificacionDetailDTO
     * @param listaEntrada
     * @return Lista de entities
     */
    private List<CalificacionDetailDTO> listEntity2DTO(List<CalificacionEntity> listaEntrada)
    {
        List<CalificacionDetailDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : listaEntrada) {
            list.add(new CalificacionDetailDTO(entity));
        }
        return list;
        
    }
    
    /**
     * Obtiene todas las calificaciones
     * @return Lista de calificaciones
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones( ) throws BusinessLogicException
    {
        return listEntity2DTO(calificacionLogic.getCalificaciones()); 
    }
    
    /**
     * Obtener una calificacion dada por parámetro
     * @param id de la calificacion que se quiere obtener
     * @return La calificacion dada por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("id") Long id) throws BusinessLogicException
    {   
        if(calificacionLogic.getCalificacion(id)==null)
        {
            throw new WebApplicationException("El guía no existe", 404);
        }
        else
        {
            return new CalificacionDetailDTO(calificacionLogic.getCalificacion(id));
        }  
    }
    
    /**
     * Crea una calificación
     * @param dto instancia de calificación que se quiere crear.
     * @return Nueva instancia creada.
     */
    @POST
    public CalificacionDetailDTO createCalificacion(CalificacionDetailDTO dto) throws BusinessLogicException 
    {
        return new CalificacionDetailDTO(calificacionLogic.createCalificacion(dto.toEntity()));
    }
    
    /**
     * Modifica la informacion de una calificación
     * @param id id de la calificación que se quiere modificar
     * @param dto calificación que se quiere modificar
     * @return Calificación con la información actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public CalificacionDetailDTO updateCalificacion(@PathParam("id") Long id, CalificacionDetailDTO dto) throws BusinessLogicException
    {  
        if(calificacionLogic.getCalificacion(id)==null)
        {
            throw new WebApplicationException("La calificacion no existe",404);
        }
        else
        {
        CalificacionEntity entity = dto.toEntity();
        entity.setId(id);
        return new CalificacionDetailDTO(calificacionLogic.updateEmployee(entity));
        }
    }
    
    /**
     * Elimina una Calificación dada por parámetro.
     * @param id de la calificación a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id") Long id)
    {  
        if(calificacionLogic.getCalificacion(id)==null)
        {
            throw new WebApplicationException("La calificacion no existe",404);
        }
        else
        {
            calificacionLogic.deleteCalificacion(id);    
        }
        
    }
    
}
