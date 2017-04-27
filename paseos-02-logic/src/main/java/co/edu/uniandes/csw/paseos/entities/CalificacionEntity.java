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
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sebastian Millan
 */
@Entity
public class CalificacionEntity implements Serializable
{
    /**
     * Atributo que representa el id de la calificación
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Caminante que realiza la calificación.
     */
    @PodamExclude
    @ManyToOne
    private CaminanteEntity caminante;
    
    /**
     * Guía que recibe la calificación.
     */
    @ManyToOne
    private GuiaEntity guia;
    
    /**
     * Paseo sobre el que se realiza la calificación.
     */
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;
    
    /**
     * Atributo que representa la fecha de la calificación
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;

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
     * Obtiene el caminante que realizo la calificación.
     * @return caminante.
     */
    public CaminanteEntity getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la calificación.
     * @param caminante
     */
    public void setCaminante(CaminanteEntity caminante) {
        this.caminante = caminante;
    }

    /**
     * Obtiene el guía que recibe la calificación.
     * @return caminante.
     */
    public GuiaEntity getGuia() {
        return guia;
    }

    /**
     * Modifica el guía que recibio la calificación.
     * @param guia
     */
    public void setGuia(GuiaEntity guia) {
        this.guia = guia;
    }

    /**
     * Obtiene el paseo sobre el que se realizo la calificación.
     * @return paseo.
     */
    public PaseoEcologicoEntity getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica el paseo sobre el que se realizo la calificación.
     * @param paseoEcologico
     */
    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((InscripcionEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
