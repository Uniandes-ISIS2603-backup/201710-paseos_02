/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.ActividadLogic;
import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Juan Diego Chaves
 */

// TODO Segun el diagrama de clases actividades es un subrecurso de paseo ecológico. 
// TODO entonces o se llama desde paseos ecologicos o se define el path que lo incluya: @Path("/paseos/{idPaseo \\d+}/actividades")
// TODO los métodos deben recibir el idPaseo y verificar que efectivamente este exista
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class ActividadResource
{
    @Inject private ActividadLogic actividadLogic;
    // TODO eliminar los atributos que no se necesitan
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page; 
    @QueryParam("limit") private Integer maxRecords; 
    
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
     * @param 
     * @return lista de actividades
     * @throws co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException
     */
    @GET
    @Path("paseos/{idPaseo}/actividades")
    public List<ActividadDetailDTO> getActividades( @PathParam( "idPaseo" ) Long idPaseo  )  
    {
        return listEntity2DTO(actividadLogic.getActividades(idPaseo));      
    }
    /**
     * Metodo que retorna una actividad cuyo id sea el pasado por parametro
     * @param id
     * @return actividad con id igual al parametro
     */
    @GET
    @Path("{id: \\d+}")
    public ActividadDetailDTO getActividad(@PathParam("idPaseoEcologico") Long idpaseo,@PathParam("id") Long id) 
    {
        try
           {
                ActividadDetailDTO ans = new ActividadDetailDTO(actividadLogic.getActividad(idpaseo, id));
                return ans;
            }
        catch(IllegalArgumentException e)
            {
            throw new WebApplicationException(404); 
        }  
    }
    /**
     * Metodo que crea una actividad con el dto pasado por parametro
     * @param dto
     * @return la nueva actividad
     */
    // TODO Revisar el comentario del principio sobre el subrecruso. Una actividad se crea asociada con un paseo ecológico
    @POST
    public ActividadDetailDTO createActividad(ActividadDetailDTO dto) throws Exception
    { 
        try
        { 
        return new ActividadDetailDTO(actividadLogic.createActividad(dto.toEntity()));
        }
       
        catch(BusinessLogicException e)
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
    public ActividadDetailDTO updateActividad(@PathParam("id") Long id, ActividadDetailDTO dto) 
    { // TODO si la actividad con el id dado no existe debe disparar una exception WebApplicationException
        ActividadEntity act = dto.toEntity();
        act.setId(id);
        return new ActividadDetailDTO(actividadLogic.updateActividad(act));
    }
    /**
     * Metodo que elimina la actividad con el id pasado por parametro
     * @param id el id de la actividad a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteActividad(@PathParam("id") Long id)
    {
       actividadLogic.deleteActividad(id);
    }
    
}
