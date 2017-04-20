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

import co.edu.uniandes.csw.paseos.dtos.PaseoEcologicoDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.PaseoEcologicoLogic;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * @author Juan David Vega
 */
@Path("/paseos")
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class PaseoEcologicoResource 
{
    @Inject private PaseoEcologicoLogic paseoEcologicoLogic;
    
    /**
     * Convierte una lista de PaseosEcologicosEntity a una lista de PaseosEcologicosDTO
     * @param listaEntrada
     * @return lista de entities
     */
    private List<PaseoEcologicoDetailDTO> listEntity2DTO(List<PaseoEcologicoEntity> listaEntrada)
    {
        List<PaseoEcologicoDetailDTO> l = new ArrayList<>( );
        for(PaseoEcologicoEntity entity : listaEntrada)
        {
            l.add(new PaseoEcologicoDetailDTO(entity));
        }
        return l;        
    }

    /**
     * Obtiene todos los paseos ecologicos
     * @return lista de paseos ecológicos
     */
    @GET
    public List<PaseoEcologicoDetailDTO> getPaseoEcologicos( )
    {
        return listEntity2DTO(paseoEcologicoLogic.getPaseos());        
    }

    /**
     * Obtener un paseo dado por parámetro
     * @param id del paseo que se quiere obtener
     * @return el paseo dado por parámetro
     */
    @GET
    @Path("{id: \\d+}")
    public PaseoEcologicoDetailDTO getPaseoEcologico(@PathParam("id") Long id) 
    { 
        PaseoEcologicoEntity entity = paseoEcologicoLogic.getPaseo(id);
        if (entity == null) {
            throw new WebApplicationException("El paseo con id dado no existe", 404);
        }
        return new PaseoEcologicoDetailDTO(entity);
    }
    
    @GET
    @Path("/tematica") 
    public List<PaseoEcologicoDetailDTO> getPaseosPorTematica(@QueryParam("tematica") String tematica) 
    {
        return listEntity2DTO(paseoEcologicoLogic.darPaseosSegunTematica(tematica));
    }
    
    @GET
    @Path("/costo")
    public List<PaseoEcologicoDetailDTO> getPaseosPorCosto(@QueryParam("costo") Double costo) 
    {
        return listEntity2DTO(paseoEcologicoLogic.darPaseosSegunCosto(costo));
    }
    
    @GET
    @Path("/lugarDeEncuentro")
    public List<PaseoEcologicoDetailDTO> getPaseosPorLugarDeEncuentro(@QueryParam("lugarDeEncuentro") String nombre) 
    {
        return listEntity2DTO(paseoEcologicoLogic.darPaseosSegunLugarEncuentro(nombre));
    }
    
    @GET
    @Path("/lugarDeDestino")
    public List<PaseoEcologicoDetailDTO> getPaseosPorLugarDeDestino(@QueryParam("lugarDeDestino") String nombre) 
    {
        return listEntity2DTO(paseoEcologicoLogic.darPaseosSegunLugarDestino(nombre));
    }
    

    /**
     * Crea un paseo ecológico
     * @param dto instancia de paseo ecologico que se quiere crear.
     * @return nueva instancia creada.
     * @throws BusinessLogicException Si hay problemas al validar las reglas de negocio
     */
    @POST
    public PaseoEcologicoDetailDTO createPaseoEcologico(PaseoEcologicoDetailDTO dto) throws BusinessLogicException 
    {
        return  new PaseoEcologicoDetailDTO(paseoEcologicoLogic.createPaseo(dto.toEntity()));
    }

    /**
     * Modifica la informacion de un paseo ecológico
     * @param id id del paseo ecologico que se quiere modificar
     * @param dto Paseo ecológico que se quiere modificar
     * @return Paseo con la información actualizada
     * @throws BusinessLogicException si hay problemas al validar las reglas de negocio
     */
    @PUT
    @Path("{id: \\d+}")
    public PaseoEcologicoDetailDTO updatePaseoEcologico(@PathParam("id") Long id, PaseoEcologicoDetailDTO dto) throws BusinessLogicException 
    {
        PaseoEcologicoEntity entity = paseoEcologicoLogic.getPaseo(id);
        if (entity == null) {
            throw new WebApplicationException("El paseo con id dado no existe", 404);
        }
        PaseoEcologicoEntity paseoUpdate = dto.toEntity();
        dto.setId(id);
        return new PaseoEcologicoDetailDTO(paseoEcologicoLogic.updatePaseo(paseoUpdate));
    }

    /**
     * Elimina un paseo ecológico dado por parametro.
     * @param id del paseo a borrar.
     * @throws BusinessLogicException si hay problemas al validar las reglas de negocio
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePaseoEcologico(@PathParam("id") Long id) throws BusinessLogicException
    {
        PaseoEcologicoEntity entity = paseoEcologicoLogic.getPaseo(id);
        if (entity == null) {
            throw new WebApplicationException("El paseo con id dado no existe", 404);
        }
        paseoEcologicoLogic.deletePaseo(id);
    }
    
    @Path("{idPaseo: \\d+}/instancias")
    public Class<PaseoInstanciaResource> getInstanciasResource(@PathParam("idPaseo") Long paseoId) {
        PaseoEcologicoEntity entity = paseoEcologicoLogic.getPaseo(paseoId);
        if (entity == null) {
            throw new WebApplicationException("El paseo con id dado no existe", 404);
        }
        return PaseoInstanciaResource.class;
    }
    
    @Path("{idPaseo: \\d+}/actividades")
    public Class<ActividadResource> getActividadesResource(@PathParam("idPaseo") Long paseoId) {
        PaseoEcologicoEntity entity = paseoEcologicoLogic.getPaseo(paseoId);
        if (entity == null) {
            throw new WebApplicationException("El paseo con id dado no existe", 404);
        }
        return ActividadResource.class;
    }

}
