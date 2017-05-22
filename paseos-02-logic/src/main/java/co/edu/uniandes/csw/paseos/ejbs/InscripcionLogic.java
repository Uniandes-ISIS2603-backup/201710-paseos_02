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

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.InscripcionPersistence;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author Sebastian Millan
 */
// TODO se deben definir la reglas de negocio. 
// TODO Las fecahs cualquiera sirve? la variable costo qué significa? por qué hay uno en el pasdeo y otro en la inscripción=? son distintos? cómo están relacionados?
@Stateless
public class InscripcionLogic {

    @Inject
    private InscripcionPersistence persistence;

    @Inject
    CaminanteLogic caminanteLogic;

    @Inject
    PaseoInstanciaLogic paseoLogic;

    /**
     * Obtiene la lista de los registros de Inscripciones.
     *
     * @return Colección de objetos de InscripcionEntity.
     *
     */
    public List<InscripcionEntity> getInscripciones(Long caminanteid) {

        return persistence.findAll(caminanteid);
    }

    /**
     * Se encarga de crear una Inscripcion en la base de datos.
     *
     * @param entity Objeto de InscripcionEntity con los datos nuevos
     * @return Objeto de Inscripcion con los datos nuevos y su ID.
     * @generated
     */
    public InscripcionEntity createInscripcion(InscripcionEntity entity) throws BusinessLogicException {
        System.out.println("reglas de negocio");
        verificarReglasNegocio(entity);
        
        return persistence.create(entity);
    }

    /**
     * Obtiene los datos de una instancia de Inscripcion a partir de su ID.
     *
     * @param caminanteid Identificador de la instancia a consultar
     * @return Instancia de InscripcionEntity con los datos de la Inscripcion
     * consultada.
     *
     */
    public InscripcionEntity getInscripcion(Long caminanteid, Long inscripcionid) {
        try {
            return persistence.find(caminanteid, inscripcionid);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La inscripcion no existe");
        }
    }

    /**
     * Actualiza la información de una instancia de Inscripcion.
     *
     * @param entity Instancia de InscripcionEntity con los nuevos datos.
     * @return Instancia de InscripcionEntity con los datos actualizados.
     *
     */
    public InscripcionEntity updateInscripcion(InscripcionEntity entity) throws BusinessLogicException {
        verificarReglasNegocio(entity);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Inscripcion de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    public void deleteInscripcion(Long caminanteid, Long id) {
        InscripcionEntity old = getInscripcion(caminanteid, id);
        persistence.delete(old.getId());
    }

    public void verificarReglasNegocio(InscripcionEntity entity) throws BusinessLogicException {
        try {
            long idCaminante = 0;
            idCaminante = entity.getCaminante().getId();
            if (entity.getFechaInscripcion() == null || entity.getObservaciones() == null || idCaminante == 0) {
                throw new BusinessLogicException("Para crear una inscripcion minimo debe enviar la fechaInscripcion, observaciones, el id del caminante, el id del paseo y si realizoPago");
            }
        } catch (Exception e) {
            throw new BusinessLogicException("Para crear una inscripcion minimo debe enviar la fechaInscripcion, observaciones, el id del caminante, el id del paseo y si realizoPago");
        }
    }
}
