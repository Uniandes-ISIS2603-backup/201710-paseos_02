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
public class GuiaLogic {
    @Inject
    private GuiaPersistence persistence;

    public List<GuiaEntity> getGuias() {
        return persistence.findAll();
    }

    public GuiaEntity createGuia (GuiaEntity entity)
    {
       return persistence.create(entity);
    }

    public GuiaEntity updateGuia (GuiaEntity entity)
    {
        return persistence.update(entity);
    }

    public GuiaEntity getGuia (Long id)
    {
        return persistence.find(id);
    }

    public void deleteGuia (Long id)
    {
        persistence.delete(id);
    }
}