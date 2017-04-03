/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.InscripcionDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.InscripcionLogic;
import co.edu.uniandes.csw.paseos.ejbs.CaminanteLogic;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
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
 * @author Sebastian Millan
 */

// TODO inscripcion debería ser un subrecurso de caminante /caminantes/îd \\d+}/inscripciones
@Path("/caminantes/idCaminante \\\\d+}/inscripciones")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class InscripcionResource
{  
    @Inject private InscripcionLogic inscripcionLogic;
    @Inject private CaminanteLogic caminanteLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    /**
     * Convierte una lista de InscripcionEntity a una lista de InscripcionDetailDTO
     * @param entityList
     * @return Lista de entities
     */
    private List<InscripcionDetailDTO> listEntity2DTO(List<InscripcionEntity> entityList)
    {
        List<InscripcionDetailDTO> list = new ArrayList<>();
        for (InscripcionEntity entity : entityList) {
            list.add(new InscripcionDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtiene todas las inscripciones
     * @return Lista de inscripciones
     */
    @GET
    public List<InscripcionDetailDTO> getInscripciones(@PathParam("idCaminante") Long idCaminante) throws BusinessLogicException
    {
        if(caminanteLogic.getCaminante(idCaminante)==null)
        {
            throw new WebApplicationException("El caminante no existe",404);
        }
        return listEntity2DTO(inscripcionLogic.getInscripciones(idCaminante));
        
    }
    
    /**
     * Obtener una inscripción dada por parámetro
     * @param id de la inscripción que se quiere obtener
     * @return La inscripción dada por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public InscripcionDetailDTO getInscripcion(@PathParam("idCaminante") Long idCaminante, @PathParam("id") Long id) throws BusinessLogicException 
    {// TODO si la inscripción con el id dado no existe debe disparar una exception WebApplicationException 404
        if(caminanteLogic.getCaminante(idCaminante)==null)
        {
            throw new WebApplicationException("El caminante no existe",404);
        }
        InscripcionEntity entity = inscripcionLogic.getInscripcion(idCaminante,id);
        if(entity==null)
        {
            throw new WebApplicationException("La inscripción pedida no existe",404);
        }
        return new InscripcionDetailDTO(entity);
        
    }
    
    /**
     * Crea una inscripción
     * @param dto instancia de inscripción que se quiere crear.
     * @return Nueva instancia creada.
     */
    @POST
    public InscripcionDetailDTO createInscripcion(@PathParam("idCaminante") Long idCaminante,InscripcionDetailDTO dto) throws BusinessLogicException 
    {
        if(caminanteLogic.getCaminante(idCaminante)==null)
        {
            throw new WebApplicationException("El caminante no existe",404);
        }
        return new InscripcionDetailDTO(inscripcionLogic.createInscripcion(dto.toEntity()));
    }
    
    /**
     * Modifica la informacion de una inscripción
     * @param id id de la inscripción que se quiere modificar
     * @param dto inscripción que se quiere modificar
     * @return Inscripción con la información actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public InscripcionDetailDTO updateInscripcion(@PathParam("idCaminante") Long idCaminante, @PathParam("id") Long id, InscripcionDetailDTO dto) 
    {   // TODO si la inscripción con el id dado no existe debe disparar una exception WebApplicationException 404
        InscripcionEntity entity = dto.toEntity();
        entity.setId(id);
        InscripcionEntity result = inscripcionLogic.updateInscripcion(idCaminante,id,entity);
        if(result==null)
        {
            throw new WebApplicationException("La inscripción no existe",404);
        }
        return new InscripcionDetailDTO(result);
        
    }
    
    /**
     * Elimina una inscrpción dada por parámetro.
     * @param id de la inscrpción a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInscripcion(@PathParam("idCaminante") Long idCaminante, @PathParam("id") Long id)
    {// TODO si la inscripción con el id dado no existe debe disparar una exception WebApplicationException 404
       InscripcionEntity result = inscripcionLogic.getInscripcion(idCaminante,id);
        if(result==null)
        {
            throw new WebApplicationException("La inscripción no existe",404);
        }
        inscripcionLogic.deleteInscripcion(idCaminante,id);
    }
    
}
