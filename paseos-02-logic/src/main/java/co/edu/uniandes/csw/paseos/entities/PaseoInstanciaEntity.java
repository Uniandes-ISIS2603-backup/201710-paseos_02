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
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan David Vega 
 */
@Entity
public class PaseoInstanciaEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fechaRealizacion;
    
    /**
     * Atributo que representa el paseo ecológico al que pertenece la instancia
     */
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;

     /**
     * Atributo que representa la lista de inscripciones a un paseo
     */
    @OneToMany(mappedBy = "instanciaPaseo")
    private List<InscripcionEntity> inscripciones;

    /**
     * Obtiene el id de la instancia.
     * @return Long, id de la instancia.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id de la instancia.
     * @param id, id que se quiere modificar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * obtiene el PaseoEcologico.
     * @return paseoEcologico
     */
    public PaseoEcologicoEntity getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica el PaseoEcologico dado por parámetro.
     * @param paseoEcologico PaseoEcologico que se desea modificar.
     */
    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    /**
     * Obtiene la fecha de realización.
     * @return Fecha.
     */
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * Modifica la fecha de realización.
     * @param fechaRealizacion nueva fecha.
     */
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    
    /**
     * Obtiene las inscripciones a esta instancia del paseo
     *
     * @return inscripciones 
     */
    public List<InscripcionEntity> getInscripciones() {
        return inscripciones;
    }

    /**
     * Modifica la lista de inscripciones 
     *
     * @param inscripciones el nuevo inscripciones de la clase PaseoEcologicoEntity
     */
    public void setInscripciones(List<InscripcionEntity> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null) {
            return this.getId().equals(((PaseoInstanciaEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

}
