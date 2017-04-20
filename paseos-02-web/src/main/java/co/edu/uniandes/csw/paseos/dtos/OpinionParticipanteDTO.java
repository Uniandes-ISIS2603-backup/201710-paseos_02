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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
