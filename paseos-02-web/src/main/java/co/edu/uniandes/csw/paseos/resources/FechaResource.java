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
 * @author jd.vega11
 */
@Path("/fechas")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class FechaResource 
{
    @Inject private FechaLogic fechaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
    private List<FechaDetailDTO> listEntity2DTO(List<FechaEntity> listaEntrada)
    {
        List<FechaDetailDTO> l = new ArrayList<>( );
        for(FechaEntity entity : listaEntrada)
        {
            l.add(new FechaDetailDTO(entity));
        }
        return l;        
    }
    
    @GET
    public List<FechaDetailDTO> getFechas( )
    {
        return listEntity2DTO(fechaLogic.getFechas());        
    }
    
    @GET
    @Path("{id: \\d+}")
    public FechaDetailDTO getFecha(@PathParam("id") Long id) 
    {
        return new FechaDetailDTO(fechaLogic.getFecha(id));
    }
    
    @POST
    public FechaDetailDTO createFecha(FechaDetailDTO dto) 
    {
        return new FechaDetailDTO(fechaLogic.createFecha(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public FechaDetailDTO updateFecha(@PathParam("id") Long id, FechaDetailDTO dto) 
    {
        FechaEntity fecha = dto.toEntity();
        fecha.setId(id);
        return new FechaDetailDTO(fechaLogic.updateFecha(fecha));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFecha(@PathParam("id") Long id)
    {
       fechaLogic.deleteFecha(id);
    }
}
