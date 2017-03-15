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
    /**
     * Atributo que representa el id del usuario
     */
    private Long id;

    /**
     * Atributo que representa el comentario de la opinion
     */
    private String comentario;

    /**
     * Atributo que representa la ruta de la imagen
     */
    private String imagen;

    /**
     * Atributo que representa la fecha de la opinion
     */
    private Date fecha;

    /**
     * Constructor de la clase
     */
    public OpinionParticipanteDTO( )
    {
        
    }

    /**
     * Constructor de la clase
     */
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

    /**
     * Conviete un GuiaDTO en Entity
     * @return Instancia de OpinionParticipanteEntity
     */
    public OpinionParticipanteEntity toEntity( )
    {
        OpinionParticipanteEntity entity = new OpinionParticipanteEntity();
        entity.setId(this.getId());
        entity.setComentario(this.getComentario());
        entity.setFecha(this.getFecha());
        entity.setImagen(this.getImagen());
        return entity;
    }

    /**
     * Obtiene el id de la opinion.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id de la opinion.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el comentario de la opinion.
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Modifica el comentario de la opinion.
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtiene la ruta de la imagen.
     * @return ruta de la imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Modifica la ruta de la imagen imagen de la opinion.
     * @param imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la fecha en que fue hecha la opinion.
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de la opinion.
     * @param fecha
     */
    public void setFechaDelPaseo(Date fecha) {
        this.fecha = fecha;
    }
}
