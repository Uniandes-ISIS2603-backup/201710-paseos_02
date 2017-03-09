/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.InscripcionDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.InscripcionLogic;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
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
 * @author Sebastian Millan
 */
@Path("/inscripciones")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class InscripcionResource
{
    @Inject private InscripcionLogic inscripcionLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    private List<InscripcionDetailDTO> listEntity2DTO(List<InscripcionEntity> listaEntrada)
    {
        return null;
        
    }
    
    @GET
    public List<InscripcionDetailDTO> getInscripciones( )
    {
        return null;
        
    }
    
    @GET
    @Path("{id: \\d+}")
    public InscripcionDetailDTO getInscripcion(@PathParam("id") Long id) 
    {
        return null;
        
    }
    
    @POST
    public InscripcionDetailDTO createInscripcion(InscripcionDetailDTO dto) 
    {
        return null;
       
    }
    
    @PUT
    @Path("{id: \\d+}")
    public InscripcionDetailDTO updateInscripcion(@PathParam("id") Long id, InscripcionDetailDTO dto) 
    {
        return null;
        
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInscripcion(@PathParam("id") Long id)
    {
       
    }
    
}
