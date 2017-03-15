/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.FechaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class FechaDTO implements Serializable
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
     * Atributo que representa las observaciones de la fecha
     */
    private String observaciones;

    /**
     * Constructor de la clase
     */
    public FechaDTO( )
    {
        
    }

    /**
     * Constructor de la clase
     */
    public FechaDTO(FechaEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.fechaRealizacion = entity.getFechaRealizacion();
            this.observaciones = entity.getObservaciones();
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de OpinionParticipanteEntity
     */
    public FechaEntity toEntity( )
    {
        FechaEntity entity = new FechaEntity();
        entity.setId(this.getId());
        entity.setFechaRealizacion(this.getFechaRealizacion());
        entity.setObservaciones(this.getObservaciones());
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

    /**
     * Obtiene observaciones de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return observaciones java.lang.String
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Modifica observaciones de la clase FechaDTO por el dado por parámetro
     *
     * @param observaciones el nuevo observaciones de la clase FechaDTO
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
