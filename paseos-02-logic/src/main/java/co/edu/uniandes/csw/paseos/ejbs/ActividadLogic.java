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
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.persistence.ActividadPersistence;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author Juan Diego Chaves
 */

@Stateless
public class ActividadLogic 
{
    @Inject 
    private ActividadPersistence persistence;
    @Inject 
    private PaseoEcologicoLogic paseoLogic; 
    
    
     /**
     * Obtiene la lista de los registros de Actividad.
     *
     * @return Colección de objetos de ActividadEntity.
     *
     */
  
    public List<ActividadEntity> getActividades(Long idPaseo) {
        PaseoEcologicoEntity paseo = paseoLogic.getPaseo(idPaseo);
        return paseo.getActividades();
    }
    
      /**
     * Obtiene los datos de una instancia de Actividad a partir de su ID.
     *
     * @param actividadid Identificador de la instancia a consultar
     * @return Instancia de ActividadEntity con los datos de la Actividad consultada.
     *
     */
     public ActividadEntity getActividad(Long paseoid, Long actividadid) {
        try {
            return persistence.find(paseoid, actividadid);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La actividad no existe en ese paseo");
        }
    }

    /**
     * Se encarga de crear una Actividad en la base de datos.
     *
     * @param entity Objeto de ActividadEntity con los datos nuevos
     * @return Objeto de ActividadEntity con los datos nuevos y su ID.
     *
     */
    public ActividadEntity createActividad( ActividadEntity entity)throws BusinessLogicException{
        if(entity.getDuracion()<=0)
            throw new BusinessLogicException("La duracion no puede ser negativa");
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Actvidad.
     *
     * @param entity Instancia de ActividadEntity con los nuevos datos.
     * @return Instancia de ActividadEntity con los datos actualizados.
     *
     */
    public ActividadEntity updateActividad(ActividadEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Actividad de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    public void deleteActividad(Long id) {
        persistence.delete(id);
    }
    
  
}
