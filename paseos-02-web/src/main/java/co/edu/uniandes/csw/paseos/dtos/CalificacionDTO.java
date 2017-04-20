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
    /**
     * Atributo que representa el id de la calificación
     */
    private Long id;
    
    /**
     * Atributo que representa los comentarios de la calificación
     */
    private String comentario;
    
    /**
     * Atributo que representa la puntuación de la calificación
     */
    private Integer puntuacion;
    
    /**
     * Atributo que representa la fecha de la calificación
     */
    private Date fecha;
    
    /**
     * Constructor de la clase
     */
    public CalificacionDTO( )
    {
        
    }
    
    /**
     * Constructor de la clase
     * @param entity Entidad con la que se inicializa la clase
     */
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
    
    /**
     * Conviete una InscripcionDTO en Entity
     * @return Instancia de InscripcionEntity
     */
    public CalificacionEntity toEntity( )
    {
        CalificacionEntity entity = new CalificacionEntity();
        entity.setId(this.getId());
        entity.setComentario(this.getComentario());
        entity.setFecha(this.getFecha());
        entity.setPuntuacion(this.getPuntuacion());
        return entity;
    }    

    /**
     * Obtiene el id de la calificación.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id de la calificación.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene los comentarios de la calificación.
     * @return Comentarios inscripción
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Modifica el comentario de la calificación.
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtiene la puntuación de la calificación.
     * @return Puntuación inscripción
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * Modifica la puntuación de la calificación.
     * @param puntuacion
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Obtiene la fecha de la calificación.
     * @return Fecha inscripción
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de la calificación.
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
}
