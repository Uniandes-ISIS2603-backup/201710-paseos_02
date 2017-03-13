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
@Stateless
public class GuiaLogic 
{
    @Inject
    private GuiaPersistence guiaPersisence;

    public List<GuiaEntity> getGuias() {
        return guiaPersisence.findAll();
    }

    public GuiaEntity getGuia(Long id) {
        return guiaPersisence.find(id);
    }

    public GuiaEntity createGuia(GuiaEntity guia) {
        guiaPersisence.create(guia);
        return guia;
    }

    public GuiaEntity updateGuia(GuiaEntity guia) {
        return guiaPersisence.update(guia);
    }

    public void deleteGuia(Long id) {
        guiaPersisence.delete(id);
    }
}