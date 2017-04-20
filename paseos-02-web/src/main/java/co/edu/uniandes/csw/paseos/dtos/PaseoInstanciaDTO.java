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

import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class PaseoInstanciaDTO implements Serializable
{
    /**
     * Atributo que representa el id de la fecha
     */
    private Long id;

    /**
     * Atributo que representa la fecha de realizacion
     */
    private Date fechaRealizacion;

    /**
     * Constructor de la clase
     */
    public PaseoInstanciaDTO( )
    {
        
    }

    /**
     * Constructor de la clase
     */
    public PaseoInstanciaDTO(PaseoInstanciaEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.fechaRealizacion = entity.getFechaRealizacion();
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de OpinionParticipanteEntity
     */
    public PaseoInstanciaEntity toEntity( )
    {
        PaseoInstanciaEntity entity = new PaseoInstanciaEntity();
        entity.setId(this.getId());
        entity.setFechaRealizacion(this.getFechaRealizacion());
        return entity;        
    }

    /**
     * Obtiene id de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return id java.lang.Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica id de la clase FechaDTO por el dado por parámetro
     *
     * @param id el nuevo id de la clase FechaDTO
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene fechaRealizacion de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return fechaRealizacion java.util.Date
     */
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * Modifica fechaRealizacion de la clase FechaDTO por el dado por parámetro
     *
     * @param fechaRealizacion el nuevo fechaRealizacion de la clase FechaDTO
     */
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
}
