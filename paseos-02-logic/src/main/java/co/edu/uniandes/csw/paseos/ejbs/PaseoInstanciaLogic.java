/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import co.edu.uniandes.csw.paseos.persistence.PaseoEcologicoPersistence;
import co.edu.uniandes.csw.paseos.persistence.PaseoInstanciaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class PaseoInstanciaLogic {

    @Inject
    private PaseoInstanciaPersistence instanciaPersistence;

    /**
     * Obtiene una lista con todas las instancias.
     * @param paseoId id del paseo cuyas instancias se desea obtener
     * @return lista de instancias.
     */
    public List<PaseoInstanciaEntity> getInstancias(Long paseoId) {
        return instanciaPersistence.findAll(paseoId);
    }

    /**
     * Obtiene una instancia con el id dado por parámetro.
     * @param paseoId id del paseo que posee la instancia
     * @param instanciaId id de la instancia que se desea obtener
     * @return instancia con el id dado por parametro.
     */
    public PaseoInstanciaEntity getInstancia(Long paseoId, Long instanciaId) {
        return instanciaPersistence.find(paseoId, instanciaId);
    }

    /**
     * Crea la instancia que llega por parámetro.
     * @param instancia instancia que se quire crear
     * @return instancia creda
     */
    public PaseoInstanciaEntity createInstancia(PaseoInstanciaEntity instancia)
    {
        instanciaPersistence.create(instancia);
        return instancia;
    }

    /**
     * Modifica la información de una instancia.
     * @param instancia instancia que se quiere actualiza.
     * @return instancia actualizada.
     */
    public PaseoInstanciaEntity updateInstancia(PaseoInstanciaEntity instancia) {
        return instanciaPersistence.update(instancia);
    }

    /**
     * Elimina una instancia dad por parámetro
     * @param id de la instancia a eliminar.
     */
    public void deleteInstancia(Long id) {
        instanciaPersistence.delete(id);
    }
}
