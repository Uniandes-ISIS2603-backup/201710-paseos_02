/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.FechaDTO;
import co.edu.uniandes.csw.paseos.dtos.FechaDetailDTO;
import co.edu.uniandes.csw.paseos.dtos.PaseoEcologicoDTO;
import co.edu.uniandes.csw.paseos.dtos.PaseoEcologicoDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.FechaLogic;
import co.edu.uniandes.csw.paseos.ejbs.PaseoEcologicoLogic;
import co.edu.uniandes.csw.paseos.entities.FechaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
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
 * @author Juan David Vega
 */
@Path("/paseos")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class PaseoEcologicoResource 
{
    @Inject private PaseoEcologicoLogic paseoEcologicoLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    private List<PaseoEcologicoDetailDTO> listEntity2DTO(List<PaseoEcologicoEntity> listaEntrada)
    {
        List<PaseoEcologicoDetailDTO> l = new ArrayList<>( );
        for(PaseoEcologicoEntity entity : listaEntrada)
        {
            l.add(new PaseoEcologicoDetailDTO(entity));
        }
        return l;        
    }
    
    @GET
    public List<PaseoEcologicoDetailDTO> getPaseoEcologicos( )
    {
        return listEntity2DTO(paseoEcologicoLogic.getPaseos());        
    }
    
    @GET
    @Path("{id: \\d+}")
    public PaseoEcologicoDetailDTO getPaseoEcologico(@PathParam("id") Long id) 
    {
        return new PaseoEcologicoDetailDTO(paseoEcologicoLogic.getPaseo(id));
    }
    
    @POST
    public PaseoEcologicoDetailDTO createPaseoEcologico(PaseoEcologicoDetailDTO dto) 
    {
        return new PaseoEcologicoDetailDTO(paseoEcologicoLogic.createPaseo(dto.toEntity()));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public PaseoEcologicoDetailDTO updatePaseoEcologico(@PathParam("id") Long id, PaseoEcologicoDetailDTO dto) 
    {
        PaseoEcologicoEntity paseo = dto.toEntity();
        paseo.setId(id);
        return new PaseoEcologicoDetailDTO(paseoEcologicoLogic.updatePaseo(paseo));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deletePaseoEcologico(@PathParam("id") Long id)
    {
       paseoEcologicoLogic.deletePaseo(id);
    }
    
    
}
