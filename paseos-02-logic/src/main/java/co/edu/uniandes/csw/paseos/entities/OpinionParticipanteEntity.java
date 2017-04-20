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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;



/**
 *
 * @author Maria del Rosario Leon
 */
@Entity
public class OpinionParticipanteEntity implements Serializable
{
    /**
     * Id de la opinion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Opinion sobre el paseo realizado.
     */
    private String comentario;

    /**
     * Ruta de la imagen.
     */
    private String imagen;

    /**
     * Fecha en que se realiza la opinion.
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;

    /**
     * Paseo ecologico sobre el que se realiza la opinion.
     */
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;

    /**
     * Caminante que realiza la opinion.
     */
    @ManyToOne
    private CaminanteEntity caminante;

    /**
     * Obtiene el id de la opinion.
     * @return id de la opinion.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica el id de la opinion.
     * @param id nuevo id.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Obtiene el comentario hecho por el caminante.
     * @return comentario.
     */
    public String getComentario()
    {
        return comentario;
    }

    /**
     * Modifica el comentario hecho por el caminante.
     * @param comentario
     */
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    /**
     * Obtiene la fecha en que se realizo la opinion.
     * @return fecha.
     */
    public Date getFecha()
    {
        return fecha;
    }

    /**
     * Modifica la fecha en la que se realizo la opinion.
     * @param fecha nueva.
     */
    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    /**
     * Obtiene la ruta de la imagen.
     * @return ruta imagen.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Cambia la ruta de la imagen.
     * @param imagen ruta nueva imagen.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el paseo ecológico sobre el cual se realizo la opinion.
     * @return paseo ecológico.
     */
    public PaseoEcologicoEntity getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica el paseo ecológico sobre el cual se realiza la opinion
     * @param paseoEcologico Nuevo paseo ecológico.
     */
    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    /**
     * Obtiene el caminante que realizo la opinion.
     * @return caminante
     */
    public CaminanteEntity getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la opinion.
     * @param caminante nuevo caminante.
     */
    public void setCaminante(CaminanteEntity caminante) {
        this.caminante = caminante;
    }
    
    
}
