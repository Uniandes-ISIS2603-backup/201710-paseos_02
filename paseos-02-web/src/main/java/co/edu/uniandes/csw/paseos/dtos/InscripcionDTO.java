/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Millan
 */
@XmlRootElement
public class InscripcionDTO
{
    private Long id;
    
    private boolean realizoPago;
    
    private Date fechaInscripcion;
    
    private Date fechaDelPaseo;
    
    private String observaciones;
    
    private double costo;
    
     public InscripcionDTO( )
    {
        
    }
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getRealizoPago() {
        return realizoPago;
    }

    public void setRealizoPago(boolean realizoPago) {
        this.realizoPago = realizoPago;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaDelPaseo() {
        return fechaDelPaseo;
    }

    public void setFechaDelPaseo(Date fechaDelPaseo) {
        this.fechaDelPaseo = fechaDelPaseo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
     
     
}
