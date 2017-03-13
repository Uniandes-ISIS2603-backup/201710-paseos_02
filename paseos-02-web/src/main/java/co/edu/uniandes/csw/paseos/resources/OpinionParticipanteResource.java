/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.OpinionParticipanteDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.OpinionParticipanteLogic;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
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
 * @author Maria del Rosario Leon
 */
@Path("/opiniones")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class OpinionParticipanteResource 
{
    @Inject private OpinionParticipanteLogic opinionLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    private List<OpinionParticipanteDetailDTO> listEntity2DTO(List<OpinionParticipanteEntity> listaEntrada)
    {
        List <OpinionParticipanteDetailDTO> list = new ArrayList<>();
        for (OpinionParticipanteEntity entity : listaEntrada)
        {
            list.add(new OpinionParticipanteDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<OpinionParticipanteDetailDTO> getOpinionParticipantees( )
    {
        return listEntity2DTO(opinionLogic.getOpinionesParticipantes());
        
    }
    
    @GET
    @Path("{id: \\d+}")
    public OpinionParticipanteDetailDTO getOpinionParticipante(@PathParam("id") Long id) 
    {
        return new OpinionParticipanteDetailDTO(opinionLogic.getOpinionParticipante(id));
        
    }
    
    @POST
    public OpinionParticipanteDetailDTO createOpinionParticipante(OpinionParticipanteDetailDTO dto) 
    {
        return new OpinionParticipanteDetailDTO(opinionLogic.createOpinionParticipante(dto.toEntity()));
       
    }
    
    @PUT
    @Path("{id: \\d+}")
    public OpinionParticipanteDetailDTO updateOpinionParticipante(@PathParam("id") Long id, OpinionParticipanteDetailDTO dto) 
    {
        OpinionParticipanteEntity entity = dto.toEntity();
        entity.setId(id);
        return new OpinionParticipanteDetailDTO(entity);
        
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteOpinionParticipante(@PathParam("id") Long id)
    {
       opinionLogic.deleteOpinionParticipante(id);
    }
    
    
}
