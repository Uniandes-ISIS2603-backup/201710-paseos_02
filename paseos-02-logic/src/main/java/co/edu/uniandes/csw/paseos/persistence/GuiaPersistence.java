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
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author María del Rosario León
 */
@Stateless
public class GuiaPersistence 
{
    @PersistenceContext(unitName="paseosPU")
    protected EntityManager em;

    /**
     * Obtiene un Guia según el id dado por parámetro.
     * @param id id del guía buscado.
     * @return Guía buscado.
     */
    public GuiaEntity find(Long id)
    {
        return em.find(GuiaEntity.class, id);
    }

    /**
     * Obtiene todos los guias.
     * @return Lista con todos los guías.
     */
    public List<GuiaEntity> findAll( )
    {
        Query solicitud = em.createQuery("select u from GuiaEntity u where u.cuentaActiva = true");
        return solicitud.getResultList();
    }

    /**
     * Crea un nuevo guía.
     * @param entity guía que se desea crear.
     * @return guía que se creo.
     */
    public GuiaEntity create(GuiaEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * Modifica la información de un guía ya existente.
     * @param entity Guía con la nueva informaciión.
     * @return Guía con la información actualizada.
     */
    public GuiaEntity update(GuiaEntity entity)
    {
        return em.merge(entity);
    }

    /**
     * Elimina un guía dado del id dado parametro.
     * @param id del guía que se quiere eliminar.
     */
    public void delete(Long id)
    {
        GuiaEntity eliminado = em.find(GuiaEntity.class, id);
        em.remove(eliminado);
    }
    
}
