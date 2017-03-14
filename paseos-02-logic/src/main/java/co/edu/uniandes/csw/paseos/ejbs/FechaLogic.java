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

    /**
     * Obtiene una lista con todas las fechas.
     * @return lista de fechas.
     */
    public List<FechaEntity> getFechas() {
        return persistencia.findAll();
    }

    /**
     * Obtiene una fecha con el id dado por parámetro.
     * @param id de la fecha buscada.
     * @return fecha con el id dado por parametro.
     */
    public FechaEntity getFecha(Long id) {
        return persistencia.find(id);
    }

    /**
     * Crea la fecha que llega por parámetro.
     * @param fecha fecha que se quire crear
     * @return fecha creda
     */
    public FechaEntity createFecha(FechaEntity fecha) {
        persistencia.create(fecha);
        return fecha;
    }

    /**
     * Modifica la información de una fecha.
     * @param fecha fecha que se quiere actualiza.
     * @return fecha actualizada.
     */
    public FechaEntity updateFecha(FechaEntity fecha) {
        return persistencia.update(fecha);
    }

    /**
     * Elimina una fecha dad por parámetro
     * @param id de la fecha a eliminar.
     */
    public void deleteFecha(Long id) {
        persistencia.delete(id);
    }
}
