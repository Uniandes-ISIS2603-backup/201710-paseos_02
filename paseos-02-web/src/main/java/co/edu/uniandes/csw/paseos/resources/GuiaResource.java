/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.GuiaDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.GuiaLogic;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
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


/**
 *
 * @author María del Rosario León
 */
@Path("/guias")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class GuiaResource
{
    @Inject private GuiaLogic guiaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords;

    /**
     * Convierte una lista de guíaEntity a una lista de guíaDTO
     * @param listaEntrada
     * @return lista de entities
     */
    private List<GuiaDetailDTO> listEntity2DTO(List<GuiaEntity> listaEntrada)
    {
        List<GuiaDetailDTO> l = new ArrayList<>( );
        for(GuiaEntity entity : listaEntrada)
        {
            l.add(new GuiaDetailDTO(entity));
        }
        return l;  
        
    }

    /**
     * Obtiene todos los guía
     * @return lista de los guía
     */
    @GET
    public List<GuiaDetailDTO> getGuias( )
    {
        return listEntity2DTO(guiaLogic.getGuias()); 
    }

    /**
     * Obtener un guía dado por parámetro
     * @param id del guía que se quiere obtener
     * @return guía dada por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public GuiaDetailDTO getGuia(@PathParam("id") Long id) 
    {
        return new GuiaDetailDTO(guiaLogic.getGuia(id));
    }

    /**
     * Crea un guía
     * @param dto instancia del guía que se quiere crear.
     * @return nueva instancia creada.
     */
    @POST
    public GuiaDetailDTO createGuia(GuiaDetailDTO dto) 
    {
        return new GuiaDetailDTO(guiaLogic.createGuia(dto.toEntity()));
    }

    /**
     * Modifica la informacion de un guía
     * @param id id del guía que se quiere modificar
     * @param dto guía que se quiere modificar
     * @return guía con la información actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public GuiaDetailDTO updateGuia(@PathParam("id") Long id, GuiaDetailDTO dto) 
    {
        GuiaEntity guia = dto.toEntity();
        guia.setId(id);
        return new GuiaDetailDTO(guiaLogic.updateGuia(guia));
        
    }

    /**
     * Elimina un guía dado por parametro.
     * @param id del guía a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteGuia(@PathParam("id") Long id)
    {
       guiaLogic.deleteGuia(id);
    }
    
    
}
