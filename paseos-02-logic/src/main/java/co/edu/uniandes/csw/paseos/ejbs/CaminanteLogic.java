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

    public List<CaminanteEntity> getCaminantes() {
        return caminantePersistence.findAll();
    }

    public CaminanteEntity getCaminante(Long id) {
        return caminantePersistence.find(id);
    }

    public CaminanteEntity createCaminante(CaminanteEntity caminante) {
        caminantePersistence.create(caminante);
        return caminante;
    }

    public CaminanteEntity updateCaminante(CaminanteEntity caminante) {
        return caminantePersistence.update(caminante);
    }

    public void deleteCaminante(Long id) {
        caminantePersistence.delete(id);
    }

}
