/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.LugarDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.LugarLogic;
import co.edu.uniandes.csw.paseos.entities.LugarEntity;
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
 * @author Andrea Lopez
 */
@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class LugarResource
{
    @Inject private LugarLogic lugarLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    private List<LugarDetailDTO> listEntity2DTO(List<LugarEntity> listaEntrada)
    {
        return null;
        
    }
    
    @GET
    public List<LugarDetailDTO> getLugares( )
    {
        return null;
        
    }
    
    @GET
    @Path("{id: \\d+}")
    public LugarDetailDTO getLugar(@PathParam("id") Long id) 
    {
        return null;
        
    }
    
    @POST
    public LugarDetailDTO createLugar(LugarDetailDTO dto) 
    {
        return null;
       
    }
    
    @PUT
    @Path("{id: \\d+}")
    public LugarDetailDTO updateLugar(@PathParam("id") Long id, LugarDetailDTO dto) 
    {
        return null;
        
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id)
    {
       
    }
}
