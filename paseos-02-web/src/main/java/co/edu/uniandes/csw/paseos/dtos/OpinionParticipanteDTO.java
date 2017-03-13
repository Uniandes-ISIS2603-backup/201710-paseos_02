/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maria del Rosario Leon
 */
@XmlRootElement
public class OpinionParticipanteDTO implements Serializable
{
    private Long id;

    private String comentario;

    private String imagen;

    private Date fecha;

    public OpinionParticipanteDTO( )
    {
        
    }
    
    public OpinionParticipanteDTO(OpinionParticipanteEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.comentario = entity.getComentario();
            this.imagen = entity.getImagen();
            this.fecha = entity.getFecha();
        }
    }
     public OpinionParticipanteEntity toEntity( )
    {
        OpinionParticipanteEntity entity = new OpinionParticipanteEntity();
        entity.setId(this.getId());
        entity.setComentario(this.getComentario());
        entity.setFecha(this.getFecha());
        entity.setImagen(this.getImagen());
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFechaDelPaseo(Date fecha) {
        this.fecha = fecha;
    }
}
