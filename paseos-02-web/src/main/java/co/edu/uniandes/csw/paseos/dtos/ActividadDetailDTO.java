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
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Diego Chaves
 */
@XmlRootElement
public class ActividadDetailDTO extends ActividadDTO
{
     /**Atributo que representa el paseo ecologio al cual esta relacionada esta actividad
     */
    private PaseoEcologicoDTO paseoEcologico;
    /**Constructor de la clase 
     */
    public ActividadDetailDTO( )
    {
        super( );
    }
    /**Constructor de la clase 
      * @param entity
     */
    public ActividadDetailDTO(ActividadEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico()); 
        }
    }
    
    /**Metodo que convierte este DTO en un ActividadEntity
     */
    @Override
    public ActividadEntity toEntity() 
    {
        ActividadEntity entity = super.toEntity();
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }
    /**Metodo que retorna el paseo ecologico al cual esta asociada esta actividad
      * @return el paseo ecologico al cual esta asociada esta actividad
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }
    /**Metodo que modifica el paseo ecologico al cual esta asociada esta actividad
     * @param paseoEcologico el nuevo paseo ecologico al cual esta asociada esta actividad
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
}
