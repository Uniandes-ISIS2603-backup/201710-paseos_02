/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
