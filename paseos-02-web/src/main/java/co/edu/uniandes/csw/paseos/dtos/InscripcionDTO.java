/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     * Atributo que representa la fecha del paseo
     */
    private Date fechaDelPaseo; // TODO por qué esto e sun atributo de la inscripción y no del paseo?
    
    /**
     * Atributo que representa las observaciones de la inscripción
     */
    private String observaciones;
    
    /**
     * Atributo que representa el costo de la inscripción
     */
    private double costo;
    
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
            this.costo = entity.getCosto();
            this.fechaDelPaseo = entity.getFechaDelPaseo();
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
       entity.setCosto(this.getCosto());
       entity.setFechaDelPaseo(this.getFechaDelPaseo());
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
     * Obtiene la fecha en la que se hará el paseo.
     * @return Fecha del paseo
     */
    public Date getFechaDelPaseo() {
        return fechaDelPaseo;
    }

    /**
     * Modifica la fecha en la que se hará el paseo.
     * @param fechaDelPaseo
     */
    public void setFechaDelPaseo(Date fechaDelPaseo) {
        this.fechaDelPaseo = fechaDelPaseo;
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

    /**
     * Obtiene el costo de la insripcion.
     * @return Costo inscripción
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Modifica el costo de la inscrpción.
     * @param costo
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }     
}
