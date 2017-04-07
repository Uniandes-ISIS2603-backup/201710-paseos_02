/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
