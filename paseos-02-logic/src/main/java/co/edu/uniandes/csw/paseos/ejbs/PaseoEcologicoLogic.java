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

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.InscripcionPersistence;
import co.edu.uniandes.csw.paseos.persistence.PaseoEcologicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class PaseoEcologicoLogic {

    @Inject
    private PaseoEcologicoPersistence persistencia;

    @Inject
    private InscripcionPersistence inscripcionPersistencia;

    /**
     * Obtiene la lista de los guias.
     *
     * @return colección de objetos de PaseoEcologicoEntity.
     */
    public List<PaseoEcologicoEntity> getPaseos() {
        return persistencia.findAll();
    }

    /**
     * Obtiene una instancia de la clase PaseoEcologicoEntity, a partir de un
     * id.
     *
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase PaseoEcologicoEntity, que se desea obtener.
     */
    public PaseoEcologicoEntity getPaseo(Long id) {
        return persistencia.find(id);
    }

    /**
     * Crea en la persistencia una nueva instancia de la clase
     * PaseoEcologicoEntity.
     *
     * @param paseo instancia de la calse PaseoEcologicoEntity que se desea
     * crear
     * @return La instancia creada.
     * @throws BusinessLogicException Si no hay suficiente informacion para persistir el paseo
     */
    public PaseoEcologicoEntity createPaseo(PaseoEcologicoEntity paseo) throws BusinessLogicException {
        verificarDatos(paseo);
        persistencia.create(paseo);
        return paseo;
    }

    /**
     * Actualizar información de una instancia PaseoEcologicoEntity dada
     *
     * @param paseo instancia de la clase PaseoEcologicoEntity que se desea
     * actualizar.
     * @return Instancia de la clase PaseoEcologicoEntity con la información
     * actualizada.
     * @throws BusinessLogicException Si hay errores en la verificacion de datos
     */
    public PaseoEcologicoEntity updatePaseo(PaseoEcologicoEntity paseo) throws BusinessLogicException {
        verificarDatos(paseo);
        verificarDatosUpdate(paseo);
        return persistencia.update(paseo);
    }

    /**
     * Elimina una instancia de la clase PaseoEcologicoEnity dada por su id.
     *
     * @param id de la instancia que se quiere eliminar.
     * @throws BusinessLogicException Si se intenta eliminar un paseo que ya tenga caminantes inscritos
     */
    public void deletePaseo(Long id) throws BusinessLogicException 
    {
        if (inscripcionPersistencia.inscripcionesPorPaseo(id).isEmpty()) {
            persistencia.delete(id);
        } else {
            throw new BusinessLogicException("No puede eliminar un paseo que tenga camiantes inscritos.");
        }

    }

    public List<PaseoEcologicoEntity> darPaseosSegunTematica(String tematica) {
        return persistencia.darPaseosSegunTematica(tematica);
    }

    public List<PaseoEcologicoEntity> darPaseosSegunCosto(Double costo) {
        return persistencia.darPaseosSegunCosto(costo);
    }

    public List<PaseoEcologicoEntity> darPaseosSegunLugarEncuentro(String nombre) {
        return persistencia.darPaseosSegunLugarDeEncuentro(nombre);
    }

    public List<PaseoEcologicoEntity> darPaseosSegunLugarDestino(String nombre) {
        return persistencia.darPaseosSegunLugarDeDestino(nombre);
    }

    private void verificarDatosUpdate(PaseoEcologicoEntity entity) throws BusinessLogicException 
    {
        boolean noTieneInscritos = inscripcionPersistencia.inscripcionesPorPaseo(entity.getId()).isEmpty();
        if (noTieneInscritos) {
            throw new BusinessLogicException("No puede realizar cambios sobre el paseo puesto que ya hay participantes inscritos.");
        }
    }

    private void verificarDatos(PaseoEcologicoEntity entity) throws BusinessLogicException {
        if (entity.getTematica() == null || entity.getCosto() == null || entity.getGuia() == null || entity.getLugarDeEncuentro() == null || entity.getLugarDeDestino() == null) {
            throw new BusinessLogicException("Un paseo minimo debe tener tematica, costo, guia asociado, lugar de encuentro y lugar de destino. \n"
                    + "Verifique que dichos campos esten llenos y vuelva a intentar.");
        }
    }
}
