/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.persistence.CaminantePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class CaminanteLogic {

    @Inject
    private CaminantePersistence caminantePersistence;

    /**
     * Obtiene la lista de los caminates.
     *
     * @return colección de objetos de CaminanteEntity.
     */
    public List<CaminanteEntity> getCaminantes() {
        return caminantePersistence.findAll();
    }

    /**
     * Obtiene una instancia de la clase CaminanteaEntity, a partir de un id.
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase CaminanteEntity, que se desea obtener.
     */
    public CaminanteEntity getCaminante(Long id) {
        return caminantePersistence.find(id);
    }

    /**
     * Crea en la persistencia una nueva instancia de la clase CaminanteEntity.
     * @param caminante instancia de la calse caminanteEntity que se desea crear
     * @return La instancia creada.
     */
    public CaminanteEntity createCaminante(CaminanteEntity caminante) {
        caminantePersistence.create(caminante);
        return caminante;
    }

    /**
     * Actualizar información de una instancia CaminanteEntity dada
     * @param caminante instancia de la clase GuiaEntity que se desea actualizar.
     * @return Instancia de la clase CaminanteEntity con la información actualizada.
     */
    public CaminanteEntity updateCaminante(CaminanteEntity caminante) {
        return caminantePersistence.update(caminante);
    }

    /**
     * Elimina una instancia de la clase CaminanteEnity dada por su id.
     * @param id id de la instancia que se quiere eliminar.
     */
    public void deleteCaminante(Long id) {
        caminantePersistence.delete(id);
    }

}
