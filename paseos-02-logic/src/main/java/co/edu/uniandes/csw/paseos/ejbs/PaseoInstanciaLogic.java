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
