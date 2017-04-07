/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.PaseoEcologicoDTO;
import co.edu.uniandes.csw.paseos.dtos.PaseoInstanciaDTO;
import co.edu.uniandes.csw.paseos.dtos.PaseoInstanciaDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.PaseoEcologicoLogic;
import co.edu.uniandes.csw.paseos.ejbs.PaseoInstanciaLogic;
import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan David Vega
 */
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class PaseoInstanciaResource 
{
    @Inject private PaseoInstanciaLogic instanciaLogic;
    
    @Inject private PaseoEcologicoLogic paseoEcologicoLogic;
  

    /**
     * Convierte una lista de PaseoInstanciaEntity a una lista de PaseoInstanciaDTO
     * @param listaEntrada
     * @return lista de entities
     */
    private List<PaseoInstanciaDTO> listEntity2DTO(List<PaseoInstanciaEntity> listaEntrada)
    {
        List<PaseoInstanciaDTO> l = new ArrayList<>( );
        for(PaseoInstanciaEntity entity : listaEntrada)
        {
            l.add(new PaseoInstanciaDTO(entity));
        }
        return l;        
    }

    /**
     * Obtiene todos las fecha
     * @param idPaseo id del paseo al que pertenecen las instancias
     * @return lista de las instancias. 
     * Se retorna una lista de dtos (no detail) para evitar que se repita la informacion del paseo una y otra vez.
     */
    @GET
    public List<PaseoInstanciaDTO> getInstancias(@PathParam("idPaseo") Long idPaseo)
    {
        return listEntity2DTO(instanciaLogic.getInstancias(idPaseo));        
    }

    /**
     * Obtener una fecha dada por parámetro
     * @param idPaseo id del paseo al que pertenece la instancia que se busca
     * @param id de la instancia que se quiere obtener
     * @return Instancia buscada 
     */
    @GET
    @Path("{id: \\d+}")
    public PaseoInstanciaDetailDTO getInstancia(@PathParam("idPaseo") Long idPaseo, @PathParam("id") Long id) 
    {
        PaseoInstanciaEntity instancia = instanciaLogic.getInstancia(idPaseo, id);
        if (instancia == null) {
            throw new WebApplicationException("La instancia con id dado no existe", 404);
        }
        return new PaseoInstanciaDetailDTO(instancia);
    }

    /**
     * Crea una fecha
     * @param idPaseo id del paseo al que pertenece la instancia que se desea crear
     * @param dto instancia de la fecha que se quiere crear.
     * @return nueva instancia creada.
     */
    @POST
    public PaseoInstanciaDetailDTO createInstancia(@PathParam("idPaseo") Long idPaseo, PaseoInstanciaDetailDTO dto) 
    {
        dto.setPaseoEcologico(new PaseoEcologicoDTO(paseoEcologicoLogic.getPaseo(idPaseo)));
        
        return new PaseoInstanciaDetailDTO(instanciaLogic.createInstancia(dto.toEntity()));
    }

    /**
     * Modifica la informacion de una fecha
     * @param idPaseo id del paseo al que pertenece la instancia que se desea actualizar
     * @param id id de la fecha que se quiere modificar
     * @param dto fecha que se quiere modificar
     * @return fecha con la información actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public PaseoInstanciaDetailDTO updateInstancia(@PathParam("idPaseo") Long idPaseo, @PathParam("id") Long id, PaseoInstanciaDetailDTO dto) 
    {
        PaseoInstanciaEntity instanciaVerificar = instanciaLogic.getInstancia(idPaseo, id);
        if (instanciaVerificar == null) {
            throw new WebApplicationException("La instancia con id dado no existe", 404);
        }
        
        PaseoInstanciaEntity instancia = dto.toEntity();
        instancia.setId(id);
        return new PaseoInstanciaDetailDTO(instanciaLogic.updateInstancia(instancia));
    }

    /**
     * Elimina una fecha dada por parametro.
     * @param idPaseo id del paseo al que pertenece la instancia que se desea borrar
     * @param id de la fecha a borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInstancia(@PathParam("idPaseo") Long idPaseo, @PathParam("id") Long id)
    {
        PaseoInstanciaEntity instanciaVerificar = instanciaLogic.getInstancia(idPaseo, id);
        if (instanciaVerificar == null) {
            throw new WebApplicationException("La instancia con id dado no existe", 404);
        }
        
       instanciaLogic.deleteInstancia(id);
    }
}
