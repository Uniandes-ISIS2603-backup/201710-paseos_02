/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.FechaDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.FechaLogic;
import co.edu.uniandes.csw.paseos.entities.FechaEntity;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan David Vega
 */
@Path("/fechas")
//TODO este recurso no está en el diagrama de clases? qué quiere decir? 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class FechaResource 
{
    @Inject private FechaLogic fechaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords;

    /**
     * Convierte una lista de fechaEntity a una lista de fechaDTO
     * @param listaEntrada
     * @return lista de entities
     */
    private List<FechaDetailDTO> listEntity2DTO(List<FechaEntity> listaEntrada)
    {
        List<FechaDetailDTO> l = new ArrayList<>( );
        for(FechaEntity entity : listaEntrada)
        {
            l.add(new FechaDetailDTO(entity));
        }
        return l;        
    }

    /**
     * Obtiene todos las fecha
     * @return lista de las fecha
     */
    @GET
    public List<FechaDetailDTO> getFechas( )
    {
        return listEntity2DTO(fechaLogic.getFechas());        
    }

    /**
     * Obtener una fecha dada por parámetro
     * @param id de la fecha que se quiere obtener
     * @return fecha dada por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public FechaDetailDTO getFecha(@PathParam("id") Long id) 
    {
        return new FechaDetailDTO(fechaLogic.getFecha(id));
    }

    /**
     * Crea una fecha
     * @param dto instancia de la fecha que se quiere crear.
     * @return nueva instancia creada.
     */
    @POST
    public FechaDetailDTO createFecha(FechaDetailDTO dto) 
    {
        return new FechaDetailDTO(fechaLogic.createFecha(dto.toEntity()));
    }

    /**
     * Modifica la informacion de una fecha
     * @param id id de la fecha que se quiere modificar
     * @param dto fecha que se quiere modificar
     * @return fecha con la información actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public FechaDetailDTO updateFecha(@PathParam("id") Long id, FechaDetailDTO dto) 
    {
        FechaEntity fecha = dto.toEntity();
        fecha.setId(id);
        return new FechaDetailDTO(fechaLogic.updateFecha(fecha));
    }

    /**
     * Elimina una fecha dada por parametro.
     * @param id de la fecha a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFecha(@PathParam("id") Long id)
    {
       fechaLogic.deleteFecha(id);
    }
}
