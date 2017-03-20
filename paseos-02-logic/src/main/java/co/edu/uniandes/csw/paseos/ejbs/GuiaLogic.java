/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.persistence.GuiaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author María del Rosario León
 */

// TODO no hay reglas de negocio? ni siquiera para la creación de un guía?
@Stateless
public class GuiaLogic 
{
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
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase GuiaEntity, que se desea obtener.
     */
    public GuiaEntity getGuia(Long id) {
        return guiaPersisence.find(id);
    }

    /**
     * Crea en la persistencia una nueva instancia de la clase GuiaEntity.
     * @param guia instancia de la calse guiaEntity que se desea crear
     * @return La instancia creada.
     */
    public GuiaEntity createGuia(GuiaEntity guia) {
        guiaPersisence.create(guia);
        return guia;
    }

    /**
     * Actualizar información de una instancia GuiaEntity dada
     * @param guia instancia de la clase GuiaEntity que se desea actualizar.
     * @return Instancia de la clase GuiaEntity con la información actualizada.
     */
    public GuiaEntity updateGuia(GuiaEntity guia) {
        return guiaPersisence.update(guia);
    }

    /**
     * Elimina una instancia de la clase GuiaEnity dada por su id.
     * @param id id de la instancia que se quiere eliminar.
     */
    public void deleteGuia(Long id) {
        guiaPersisence.delete(id);
    }
}