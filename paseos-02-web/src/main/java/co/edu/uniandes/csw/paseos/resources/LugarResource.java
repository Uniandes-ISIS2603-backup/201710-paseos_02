/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.resources;

import co.edu.uniandes.csw.paseos.dtos.LugarDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.LugarLogic;
import co.edu.uniandes.csw.paseos.entities.LugarEntity;
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

/**
 *
 * @author Andrea Lopez
 */
// TODO borrar los imports  que no se necesitan
// TODO revisar el concepto Lugar. No tiene info de qué ciudad es. La dirección es solo un string que no permitiría ubicarla en un mapa. 
// TODO qué es lo que se modela con el atributo caracteristicas o infoacceso dado que son string? 
@Path("/lugares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarResource {

    /*
    lista de atributos para realizar las operaciones
     */
    @Inject
    private LugarLogic lugarLogic;
    // TODO eliminar los atributos que no se necesitan
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    /*
    debuelve la lista de lugares
     */
    private List<LugarDetailDTO> listEntity2DTO(List<LugarEntity> listaEntrada) {
        List<LugarDetailDTO> l = new ArrayList<>();
        for (LugarEntity entity : listaEntrada) {
            l.add(new LugarDetailDTO(entity));
        }
        return l;

    }

    /*
    busca y obtiene un lugar
     */
    @GET
    public List<LugarDetailDTO> getLugares() {
        return listEntity2DTO(lugarLogic.getLugares());

    }

    /*
    busca y obtiene un lugar
     */
    @GET
    @Path("{id: \\d+}")
    public LugarDetailDTO getLugar(@PathParam("id") Long id) {
        // TODO si el lugar con el id dado no existe debe disparar una exception WebApplicationException 404
        return new LugarDetailDTO(lugarLogic.getLugar(id));

    }

    /*
    agrega el lugar
     */
    @POST
    public LugarDetailDTO createLugar(LugarDetailDTO dto) {
        return new LugarDetailDTO(lugarLogic.createLugar(dto.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public LugarDetailDTO updateLugar(@PathParam("id") Long id, LugarDetailDTO dto) {
         // TODO si el lugar con el id dado no existe debe disparar una exception WebApplicationException 404
        LugarEntity lugar = dto.toEntity();
        lugar.setId(id);
        return new LugarDetailDTO(lugarLogic.updateLugar(lugar));

    }

    /*
    elimina el lugar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id) {
         // TODO si el lugar con el id dado no existe debe disparar una exception WebApplicationException 404
        lugarLogic.deleteLugar(id);
    }
}
