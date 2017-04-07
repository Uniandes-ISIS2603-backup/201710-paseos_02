/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.CaminanteDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.CaminanteLogic;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan David Vega
 */
@Path("/caminantes")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class CaminanteResource
{
    @Inject private CaminanteLogic caminanteLogic;
    
    private List<CaminanteDetailDTO> listEntity2DTO(List<CaminanteEntity> listaEntrada)
    {
        List<CaminanteDetailDTO> l = new ArrayList<>( );
        for(CaminanteEntity entity : listaEntrada)
        {
            l.add(new CaminanteDetailDTO(entity));
        }
        return l; 
    }
    
    @GET
    public List<CaminanteDetailDTO> getCaminantes( )
    {
        return listEntity2DTO(caminanteLogic.getCaminantes());
    }
    
    @Path("caminantes/{idCaminante: \\d+}/inscripciones")
    public InscripcionResource getInscripcionResource(@PathParam("caminantesId") Long caminantesId) {
         CaminanteEntity entity = caminanteLogic.getCaminante(caminantesId);
        if (entity == null) {
            throw new WebApplicationException("La inscripción no existe", 404);
        }
        return new InscripcionResource();
    }
    
    @GET
    @Path("{id: \\d+}")
    public CaminanteDetailDTO getCaminante(@PathParam("id") Long id) 
    { 
        CaminanteEntity entity = caminanteLogic.getCaminante(id);
        if (entity == null) {
            throw new WebApplicationException("El caminante con id dado no existe", 404);
        }
        return new CaminanteDetailDTO(entity);
    }
    
    @POST
    public CaminanteDetailDTO createCaminante(CaminanteDetailDTO dto) throws BusinessLogicException
    {
         return new CaminanteDetailDTO(caminanteLogic.createCaminante(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CaminanteDetailDTO updateCaminante(@PathParam("id") Long id, CaminanteDetailDTO dto) throws BusinessLogicException 
    {
        CaminanteEntity entity = caminanteLogic.getCaminante(id);
        if (entity == null) {
            throw new WebApplicationException("El caminante con id dado no existe", 404);
        }
        CaminanteEntity caminanteUpdate = dto.toEntity();
        entity.setId(id);
        return new CaminanteDetailDTO(caminanteLogic.updateCaminante(caminanteUpdate));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCaminante(@PathParam("id") Long id)
    {
        CaminanteEntity entity = caminanteLogic.getCaminante(id);
        if (entity == null) {
            throw new WebApplicationException("El caminante con id dado no existe", 404);
        }
        caminanteLogic.deleteCaminante(id);
    }
    
}
