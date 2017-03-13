/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maria del Rosario Leon
 */
@XmlRootElement
public class OpinionParticipanteDTO
{
    private Long id;

    private String comentario;

    private List <String> imagenes;


    private Date fechaDelPaseo;

    public OpinionParticipanteDTO( )
    {
        
    }
    
    public OpinionParticipanteDTO(OpinionParticipanteEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.comentario = entity.getComentario();
            this.imagenes = entity.getImagenes();
            this.fechaDelPaseo = entity.getFechaDelPaseo();
        }
    }
     public OpinionParticipanteEntity toEntity( )
    {
        OpinionParticipanteEntity entity = new OpinionParticipanteEntity();
        entity.setId(this.getId());
        entity.setComentario(this.getComentario());
        entity.setFechaDelPaseo(this.getFechaDelPaseo());
        entity.setImagenes(this.getImagenes());

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

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public Date getFechaDelPaseo() {
        return fechaDelPaseo;
    }

    public void setFechaDelPaseo(Date fechaDelPaseo) {
        this.fechaDelPaseo = fechaDelPaseo;
    }
}
