/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.FechaEntity;
import co.edu.uniandes.csw.paseos.persistence.FechaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class FechaLogic {

    @Inject
    private FechaPersistence persistencia;

    public List<FechaEntity> getFechas() {
        return persistencia.findAll();
    }

    public FechaEntity getFecha(Long id) {
        return persistencia.find(id);
    }

    public FechaEntity createFecha(FechaEntity fecha) {
        persistencia.create(fecha);
        return fecha;
    }

    public FechaEntity updateFecha(FechaEntity fecha) {
        return persistencia.update(fecha);
    }

    public void deleteFecha(Long id) {
        persistencia.delete(id);
    }
}
