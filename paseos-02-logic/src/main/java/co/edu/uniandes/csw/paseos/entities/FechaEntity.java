/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan David Vega 
 */
@Entity
public class FechaEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fechaRealizacion;
    
    private String observaciones;
    
    
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;


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
     * Obtiene las observaciones.
     * @return las observaciones.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Modifica las observaciones.
     * @param observaciones Nuevas observaciones.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null) {
            return this.getId().equals(((FechaEntity) obj).getId());
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
