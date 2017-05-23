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

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.GuiaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author María del Rosario León
 */

@Stateless
public class GuiaLogic {
    @Inject
    private GuiaPersistence guiaPersisence;

    /**
     * Obtiene la lista de los guias.
     *
     * @return colección de objetos de GuiasEntity.
     */
    public List<GuiaEntity> getGuias() {
        return guiaPersisence.findAll();
    }

    /**
     * Obtiene una instancia de la clase GuiaEntity, a partir de un id.
     *
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase GuiaEntity, que se desea obtener.
     */
    public GuiaEntity getGuia(Long id) {
        return guiaPersisence.find(id);
    }

    /**
     * Crea en la persistencia una nueva instancia de la clase GuiaEntity.
     *
     * @param guia instancia de la calse guiaEntity que se desea crear
     * @return La instancia creada.
     */
    public GuiaEntity createGuia(GuiaEntity guia) throws BusinessLogicException {
         verificarDatos(guia);
        
        if (!verificarMismaIdentificacion(guia)) 
        {
            guia.setCuentaActiva(Boolean.TRUE);
            guiaPersisence.create(guia);
            return guia;
        } else {
            throw new BusinessLogicException("El guía ya existe");
        }
    }

    /**
     * Actualizar información de una instancia GuiaEntity dada
     *
     * @param guia instancia de la clase GuiaEntity que se desea actualizar.
     * @return Instancia de la clase GuiaEntity con la información actualizada.
     */
    public GuiaEntity updateGuia(GuiaEntity guia) throws BusinessLogicException {
        
        verificarDatos(guia);        
       GuiaEntity original = guiaPersisence.find(guia.getId());
       boolean mismoIdentViejo = original.getIdentificacion().equals(guia.getIdentificacion());        
        if(!verificarMismaIdentificacion(guia) || mismoIdentViejo)
        {
        
            return guiaPersisence.update(guia);
        }
        else
        {
            throw new BusinessLogicException("Ya existe otro usuario con la misma identificacion");
        } 
    }

    /**
     * Elimina una instancia de la clase GuiaEnity dada por su id.
     *
     * @param id id de la instancia que se quiere eliminar.
     */
    public void deleteGuia(Long id) throws BusinessLogicException
    {
        GuiaEntity entity = guiaPersisence.find(id);
        verificarBorrado(entity);
        guiaPersisence.delete(id);
    }
    
     
    private void verificarDatos(GuiaEntity entity) throws BusinessLogicException
   {
       boolean nombre = entity.getNombre() == null;
       boolean identificacion = entity.getIdentificacion() == null;
       boolean tipoIden = entity.getTipoIdentificacion() == null;
       boolean edad = entity.getEdad() == null;
           if (nombre || identificacion || tipoIden || edad )
       {
           throw new BusinessLogicException("Para registrar un guia, minimo debe tener nombre, identificacion, tipoIdentificacion y edad. \n"
                   + "Verifique que dichos campos esten llenos y vuelva a intentar.");
       }
   }
    
    private void verificarBorrado(GuiaEntity entity) throws BusinessLogicException
   {
       boolean noTienePaseos = entity.getPaseosEcologicos().isEmpty();
       boolean noTieneCalificaciones = entity.getCalificaciones().isEmpty();
      
       if (!noTienePaseos || !noTieneCalificaciones) 
       {
           throw new BusinessLogicException("No puede eliminar el guia puesto que tiene paseos asociadas, \n"
                   + "calificaciones registradas.");
       }
   }
    
    private boolean verificarMismaIdentificacion(GuiaEntity guia)
    {
        List<GuiaEntity> guias = getGuias();
        boolean existe = false;
        for (GuiaEntity guiaAct : guias) {
            if (guiaAct.getIdentificacion().equals(guia.getIdentificacion())) {
                existe = true;
                break;
            }
        }
        return existe;
    }
}