/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.ActividadLogic;
import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
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
 * @author Juan Diego Chaves
 */
@Path("/actividades")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class ActividadResource
{
    @Inject private ActividadLogic actividadLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    private List<ActividadDetailDTO> listEntity2DTO(List<ActividadEntity> listaEntrada)
    {
       List<ActividadDetailDTO> resp = new ArrayList<>();
        for(ActividadEntity e : listaEntrada)
        {
            resp.add(new ActividadDetailDTO(e));
        } 
        return resp;
        
    }
    
    @GET
    public List<ActividadDetailDTO> getActividades( )
    {
        return listEntity2DTO(actividadLogic.getActividades());      
    }
    
    @GET
    @Path("{id: \\d+}")
    public ActividadDetailDTO getActividad(@PathParam("id") Long id) 
    {
        return new ActividadDetailDTO(actividadLogic.getActividad(id));
        
    }
    
    @POST
    public ActividadDetailDTO createActividad(ActividadDetailDTO dto) 
    {
        return new ActividadDetailDTO(actividadLogic.createActividad(dto.toEntity())); 
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ActividadDetailDTO updateActividad(@PathParam("id") Long id, ActividadDetailDTO dto) 
    {
        ActividadEntity act = dto.toEntity();
        act.setId(id);
        return new ActividadDetailDTO(actividadLogic.updateActividad(act));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteActividad(@PathParam("id") Long id)
    {
       actividadLogic.deleteActividad(id);
    }
    
}
