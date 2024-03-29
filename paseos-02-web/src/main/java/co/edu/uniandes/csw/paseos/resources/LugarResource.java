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

import co.edu.uniandes.csw.paseos.dtos.LugarDetailDTO;
import co.edu.uniandes.csw.paseos.ejbs.LugarLogic;
import co.edu.uniandes.csw.paseos.entities.LugarEntity;

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
//    @Context
//    private HttpServletResponse response;
//    @QueryParam("page")
//    private Integer page;
//    @QueryParam("limit")
//    private Integer maxRecords;

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
    public LugarDetailDTO getLugar(@PathParam("id") Long id) throws WebApplicationException
    {
        // TODO si el lugar con el id dado no existe debe disparar una exception WebApplicationException 404
        LugarDetailDTO s=new LugarDetailDTO(lugarLogic.getLugar(id));
        if(s!=null)
        {
            return new LugarDetailDTO(lugarLogic.getLugar(id));
        }
        else
        {
            throw new WebApplicationException("El lugar no existe 404");
        }
        

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
    public LugarDetailDTO updateLugar(@PathParam("id") Long id, LugarDetailDTO dto)
    {
         // TODO si el lugar con el id dado no existe debe disparar una exception WebApplicationException 404
        LugarDetailDTO s=new LugarDetailDTO(lugarLogic.getLugar(id));
        if(s!=null)
        {
            LugarEntity lugar = dto.toEntity();
            lugar.setId(id);
            return new LugarDetailDTO(lugarLogic.updateLugar(lugar));
        }
        else
        {
            throw new WebApplicationException("El lugar no existe 404");
        }

    }

    /*
    elimina el lugar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id) {
         // TODO si el lugar con el id dado no existe debe disparar una exception WebApplicationException 404
        LugarDetailDTO s=new LugarDetailDTO(lugarLogic.getLugar(id));
        if(s!=null)
        {
           lugarLogic.deleteLugar(id);
        }
        else
        {
            throw new WebApplicationException("El lugar no existe 404");
        }
    }
}
