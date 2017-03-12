/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Millan
 */
@XmlRootElement
public class CalificacionDTO implements Serializable
{
    private Long id;
    
    private String comentario;
    
    private Integer puntuacion;
    
    private Date fecha;
    
    public CalificacionDTO( )
    {
        
    }
    
    public CalificacionDTO(CalificacionEntity entity)
    {
        if(entity != null)
        {
         this.id = entity.getId();
         this.comentario = entity.getComentario();
         this.puntuacion = entity.getPuntuacion();
         this.fecha = entity.getFecha();
        }
    }
    
    public CalificacionEntity toEntity( )
    {
        CalificacionEntity entity = new CalificacionEntity();
        entity.setId(this.getId());
        entity.setComentario(this.getComentario());
        entity.setFecha(this.getFecha());
        entity.setPuntuacion(this.getPuntuacion());
        return entity;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
