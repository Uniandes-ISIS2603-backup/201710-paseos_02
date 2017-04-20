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

import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.LugarPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Andrea Lopez
 * lugar !=null.
 * debe de existir un paseo ecologico.
 */

// TODO cuáles son als reglas de negocio ? 

@Stateless
public class LugarLogic 
{
     @Inject private LugarPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de lugares.
     *
     * @return Colección de objetos de LugarEntity.
     * 
     */
    public List<LugarEntity> getLugares() {
        return persistence.findAll();
    }

    /**
     * Se encarga de crear un lugar en la base de datos.
     *
     * @param entity Objeto de LugarEntity con los datos nuevos
     * @return Objeto de lugar con los datos nuevos y su ID.
     * @generated
     */
    public LugarEntity createLugar(LugarEntity entity)  {
        
        return persistence.create(entity);
    }
    
    /**
     * Obtiene los datos de una instancia de lugar a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de LugarEntity con los datos de la Inscripcion consultada.
     *
     */
    public LugarEntity getLugar(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Actualiza la información de una instancia de Lugar.
     *
     * @param entity Instancia de LugarEntity con los nuevos datos.
     * @return Instancia de InscripcionEntity con los datos actualizados.
     * 
     */
   
    public LugarEntity updateLugar(LugarEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Lugar de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
   
    public void deleteLugar(Long id) {
        persistence.delete(id);
    }
}

