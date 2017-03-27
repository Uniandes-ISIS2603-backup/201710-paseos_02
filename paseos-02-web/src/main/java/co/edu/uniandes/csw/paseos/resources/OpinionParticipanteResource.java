/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.OpinionParticipanteDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.OpinionParticipanteLogic;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
     // TODO eliminar los atributos que no se necesitan
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords;

    /**
     * Convierte una lista de OpinionParticipanteEntity a una lista de OpinionParticipanteDTO
     * @param listaEntrada
     * @return lista de entities
     */
    private List<OpinionParticipanteDetailDTO> listEntity2DTO(List<OpinionParticipanteEntity> listaEntrada)
    {
        List <OpinionParticipanteDetailDTO> list = new ArrayList<>();
        for (OpinionParticipanteEntity entity : listaEntrada)
        {
            list.add(new OpinionParticipanteDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene todos las Opinion de un participante
     * @return lista de Opinion de un participante
     */
    @GET
    public List<OpinionParticipanteDetailDTO> getOpinionParticipantees( )
    {
        return listEntity2DTO(opinionLogic.getOpinionesParticipantes());
        
    }

    /**
     * Obtener una Opinion de un participante dada por parámetro
     * @param id de la Opinion de un participante que se quiere obtener
     * @return la Opinion de un participante dada por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public OpinionParticipanteDetailDTO getOpinionParticipante(@PathParam("id") Long id) throws BusinessLogicException
    {
        if (opinionLogic.getOpinionParticipante(id) == null)
        {
            throw new WebApplicationException("La opinion no existe", 404);
        }
        else {
            return new OpinionParticipanteDetailDTO(opinionLogic.getOpinionParticipante(id));
        }
        
    }

    /**
     * Crea una Opinion de un participante
     * @param dto instancia de Opinion de un participante que se quiere crear.
     * @return nueva instancia creada.
     */
    @POST
    public OpinionParticipanteDetailDTO createOpinionParticipante(OpinionParticipanteDetailDTO dto) 
    {
        return new OpinionParticipanteDetailDTO(opinionLogic.createOpinionParticipante(dto.toEntity()));
    }

    /**
     * Modifica la informacion de una Opinion de un participante
     * @param id id dela Opinion de un participante que se quiere modificar
     * @param dto Opinion de un participante que se quiere modificar
     * @return Opinion de un participante con la información actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public OpinionParticipanteDetailDTO updateOpinionParticipante(@PathParam("id") Long id, OpinionParticipanteDetailDTO dto) throws BusinessLogicException
    {
        if (opinionLogic.getOpinionParticipante(id) == null)
        {
            throw new WebApplicationException("La opinion no existe", 404);
        }
        else
        {
            OpinionParticipanteEntity entity = dto.toEntity();
            entity.setId(id);
            return new OpinionParticipanteDetailDTO(entity);
        }
        
    }

    /**
     * Elimina una Opinion de un participante dada por parametro.
     * @param id de la opinion a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteOpinionParticipante(@PathParam("id") Long id) throws BusinessLogicException
    {
        if (opinionLogic.getOpinionParticipante(id) == null)
        {
            throw new WebApplicationException("El guía no existe", 404);
        }
        else {
            opinionLogic.deleteOpinionParticipante(id);
        }
    }
    
    
}
