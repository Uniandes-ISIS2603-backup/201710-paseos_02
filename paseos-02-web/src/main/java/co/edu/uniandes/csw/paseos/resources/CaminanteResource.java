/* 
 * The MIT License
 *
 * Copyright 2017 Treamwork - Team software development - Los Andes University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
    
    @Path("{idCaminante: \\d+}/inscripciones")
    public Class<InscripcionResource> getInscripcionResource(@PathParam("idCaminante") Long idCaminante) {
        System.out.print("Entre aqui");
        CaminanteEntity entity = caminanteLogic.getCaminante(idCaminante);
        if (entity == null) {
            System.out.print("Pro aqui");
            throw new WebApplicationException("La inscripción no existe", 404);
        }
        System.out.print("sali de aqui");
        return InscripcionResource.class;
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
        dto.setId(id);
        CaminanteEntity caminanteUpdate = dto.toEntity();        
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
