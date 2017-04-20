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

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Millan
 */
@XmlRootElement
public class InscripcionDTO implements Serializable
{
    /**
     * Atributo que representa el id de la inscripción
     */
    private Long id;
    
    /**
     * Atributo que representa el estado del pago
     */
    private boolean realizoPago;
    
    /**
     * Atributo que representa la fecha en la que se hizo la inscripción
     */
    private Date fechaInscripcion;
    
    /**
     * Atributo que representa las observaciones de la inscripción
     */
    private String observaciones;
   
    /**
     * Constructor de la clase
     */
     public InscripcionDTO( )
    {
        
    }
    
     /**
     * Constructor de la clase
     * @param entity Entidad con la que se inicializa la clase
     */
    public InscripcionDTO(InscripcionEntity entity)
    {
        if(entity != null)
        {
            this.fechaInscripcion = entity.getFechaInscripcion();
            this.id = entity.getId();
            this.observaciones = entity.getObservaciones();
            this.realizoPago = entity.getRealizoPago();
        }
    }
    
    /**
     * Conviete una InscripcionDTO en Entity
     * @return Instancia de InscripcionEntity
     */
     public InscripcionEntity toEntity( )
    {
       InscripcionEntity entity = new InscripcionEntity();
       entity.setFechaInscripcion(this.getFechaInscripcion());
       entity.setId(this.getId());
       entity.setObservaciones(this.getObservaciones());
       entity.setRealizoPago(this.getRealizoPago());
       return entity;
    }

    /**
     * Obtiene el id de la insripcion.
     * @return Id inscripcion
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id de la inscrpción.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el estado del pago de la insripcion.
     * @return El estado del pago 
     */
    public boolean getRealizoPago() {
        return realizoPago;
    }

    /**
     * Modifica el estado de pago de la inscrpción.
     * @param realizoPago
     */
    public void setRealizoPago(boolean realizoPago) {
        this.realizoPago = realizoPago;
    }

    /**
     * Obtiene la fecha en la que se hizo la insripción.
     * @return Fecha de inscripción
     */
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Modifica el la fecha de la inscrpción.
     * @param fechaInscripcion
     */
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Obtiene las observaciones de la insripcion.
     * @return Las observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Modifica las observaciones de la inscrpción.
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }   
}
