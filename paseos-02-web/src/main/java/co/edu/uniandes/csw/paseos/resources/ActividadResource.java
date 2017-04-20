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

import co.edu.uniandes.csw.paseos.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.ActividadLogic;
import co.edu.uniandes.csw.paseos.ejbs.PaseoEcologicoLogic;
import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Juan Diego Chaves
 */

@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class ActividadResource
{
    @Inject private ActividadLogic actividadLogic;
    @Inject private PaseoEcologicoLogic paseoLogic;
    /**
     * Convierte una lista de AcctividadEntity a una lista de ActividadDTO
     * @param listaEntrada
     * @return lista de DTO
     */
    private List<ActividadDetailDTO> listEntity2DTO(List<ActividadEntity> listaEntrada)
    {
       List<ActividadDetailDTO> resp = new ArrayList<>();
        for(ActividadEntity e : listaEntrada)
        {
            resp.add(new ActividadDetailDTO(e));
        } 
        return resp;
        
    }
    
    /**
     * Metodo que retorna una lista con todas las actividades
     * @param idPaseo
     * @return lista de actividades
     */
    @GET
    public List<ActividadDetailDTO> getActividades( @PathParam( "idPaseo" ) Long idPaseo  )  
    {
        if(paseoLogic.getPaseo(idPaseo) == null)
                   throw new WebApplicationException(Response.Status.NOT_FOUND);
      return listEntity2DTO(actividadLogic.getActividades(idPaseo));
             
    }
    /**
     * Metodo que retorna una actividad cuyo id sea el pasado por parametro
     * @param id
     */
    @GET
    @Path("{id: \\d+}")
    public ActividadDetailDTO getActividad(@PathParam("idPaseo") Long idPaseo,@PathParam("id") Long id) 
    {
        try {
            if (paseoLogic.getPaseo(idPaseo) == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
            ActividadDetailDTO ans = new ActividadDetailDTO(actividadLogic.getActividad(idPaseo, id));
            return ans;
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException(404);
        }
    }
    /**
     * Metodo que crea una actividad con el dto pasado por parametro
     * @param dto
     * @return la nueva actividad
     */
   
    @POST
    public ActividadDetailDTO createActividad(@PathParam("idPaseo") Long idPaseo,ActividadDetailDTO dto) throws BusinessLogicException
    { 
        try
        { 
            if(paseoLogic.getPaseo(idPaseo) == null)
                   throw new WebApplicationException(Response.Status.NOT_FOUND);
            ActividadEntity en = dto.toEntity();
            PaseoEcologicoEntity pp = new PaseoEcologicoEntity();
            pp.setId(idPaseo);
            en.setPaseoEcologico(pp);
        return new ActividadDetailDTO(actividadLogic.createActividad(en));
        }
       
        catch(Exception e)
         {
             throw new WebApplicationException(500);
         }        
    }
    /**
     * Metodo que modifica la actividad con el id pasado por parametro usando los datos del dto pasado por parametro
     * @param id el id de la actividad a modificar
     * @param dto los datos para modificar la actividad
     * @return la actividad actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public ActividadDetailDTO updateActividad(@PathParam("idPaseo") Long idPaseo, @PathParam("id") Long id, ActividadDetailDTO dto) 
    { 
        if(getActividad(idPaseo, id)==null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        
        PaseoEcologicoEntity en = new PaseoEcologicoEntity();
        en.setId(idPaseo);
        
        ActividadEntity act = dto.toEntity();
        act.setId(id);
        act.setPaseoEcologico(en);
        return new ActividadDetailDTO(actividadLogic.updateActividad(act));
    }
    /**
     * Metodo que elimina la actividad con el id pasado por parametro
     * @param id el id de la actividad a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteActividad(@PathParam("idPaseo") Long idPaseo,@PathParam("id") Long id)
    {
        try{
         if(getActividad(idPaseo, id)==null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        catch(Exception e)
        {
             throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
       actividadLogic.deleteActividad(id);
    }
    
}
