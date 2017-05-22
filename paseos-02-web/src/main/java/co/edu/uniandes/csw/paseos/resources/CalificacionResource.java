/* 
 * The MIT License
 *
 * Copyright 2017 Treamwork - Team software development - Los Andes University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.paseos.dtos.GuiaDTO;
import co.edu.uniandes.csw.paseos.ejbs.CalificacionLogic;
import co.edu.uniandes.csw.paseos.ejbs.GuiaLogic;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
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
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionResource 
{
    @Inject private CalificacionLogic calificacionLogic;
    @Inject private GuiaLogic guiaLogic;
    
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
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("idGuia") Long idGuia ) throws BusinessLogicException
    {
        GuiaEntity guia = guiaLogic.getGuia(idGuia);
        if(guia==null)
        {
            throw new WebApplicationException("El guia no existe",404);
        }
        return listEntity2DTO(calificacionLogic.getCalificaciones(idGuia)); 
    }
    
    /**
     * Obtener una calificacion dada por parámetro
     * @param id de la calificacion que se quiere obtener
     * @return La calificacion dada por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("idGuia") Long idGuia, @PathParam("id") Long id) throws BusinessLogicException
    {   
        GuiaEntity guia = guiaLogic.getGuia(idGuia);
        if(guia==null)
        {
            throw new WebApplicationException("El guía no existe", 404);
        }
        CalificacionEntity entity = calificacionLogic.getCalificacion(idGuia, id);
        if(entity==null)
        {
            throw new WebApplicationException("La calificación pedida no existe",404);
        }  
        return new CalificacionDetailDTO(entity);
      
    }
    
    /**
     * Crea una calificación
     * @param dto instancia de calificación que se quiere crear.
     * @return Nueva instancia creada.
     */
    @POST
    public CalificacionDetailDTO createCalificacion(@PathParam("idGuia") Long idGuia,CalificacionDetailDTO dto) throws BusinessLogicException 
    {
        GuiaEntity guia = guiaLogic.getGuia(idGuia);
        if(guia==null)
        {
            throw new WebApplicationException("El guía no existe", 404);
        }
        dto.setGuia(new GuiaDTO(guiaLogic.getGuia(idGuia)));
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
    public CalificacionDetailDTO updateCalificacion(@PathParam("idGuia") Long idGuia, @PathParam("id") Long id, CalificacionDetailDTO dto) throws BusinessLogicException
    {  
        GuiaEntity guia = guiaLogic.getGuia(idGuia);
        if(guia==null)
        {
            throw new WebApplicationException("El guía no existe", 404);
        }
        CalificacionEntity entity = dto.toEntity();
        entity.setId(id);
        CalificacionEntity result = calificacionLogic.updateCalificacion(entity);
        if(result==null)
        {
             throw new WebApplicationException("La calificación no existe",404);
        }
        return new CalificacionDetailDTO(result);
    }
    
    /**
     * Elimina una Calificación dada por parámetro.
     * @param id de la calificación a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("idGuia") Long idGuia,@PathParam("id") Long id)
    {  
        CalificacionEntity result = calificacionLogic.getCalificacion(idGuia,id);
        if(result==null)
        {
             throw new WebApplicationException("La calificación no existe",404);
        }
        calificacionLogic.deleteCalificacion(idGuia,id);    
        
        
    }
    
}
