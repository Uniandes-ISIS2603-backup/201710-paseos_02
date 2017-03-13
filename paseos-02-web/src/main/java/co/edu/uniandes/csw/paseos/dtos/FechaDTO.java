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
    private Long id;
   
    private Date fechaRealizacion;
    
    private String observaciones;
    
    public FechaDTO( )
    {
        
    }
    
    public FechaDTO(FechaEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.fechaRealizacion = entity.getFechaRealizacion();
            this.observaciones = entity.getObservaciones();
        }
    }
    
    public FechaEntity toEntity( )
    {
        FechaEntity entity = new FechaEntity();
        entity.setId(this.getId());
        entity.setFechaRealizacion(this.getFechaRealizacion());
        entity.setObservaciones(this.getObservaciones());
        return entity;        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    

}
