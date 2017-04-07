/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebastian Millan
 */
@Entity
public class InscripcionEntity implements Serializable
{
    /**
     * Atributo que representa el id de la inscripción
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Atributo que representa el estado del pago
     */
    private boolean realizoPago;
    
    /**
     * Atributo que representa la fecha en la que se hizo la inscripción
     */
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    
    /**
     * Atributo que representa las observaciones de la inscripción
     */
    private String observaciones;
    
    /**
     * Caminante que realiza la inscripción.
     */
    @ManyToOne
    private CaminanteEntity caminante;
    
    /**
     * Instancia del paseo sobre el que se realiza la inscripción.
     */
    @ManyToOne
    private PaseoInstanciaEntity instanciaPaseo;

    /**
     * Obtiene el id de la insripcion.
     * @return Id inscripcion
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id de la inscrpción.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el caminante que realizo la inscripción.
     * @return caminante.
     */
    public CaminanteEntity getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la inscrpción
     * @param caminante
     */
    public void setCaminante(CaminanteEntity caminante) {
        this.caminante = caminante;
    }

    /**
     * Obtiene el estado del pago de la insripcion.
     * @return El estado del pago 
     */
    public boolean getRealizoPago() {
        return realizoPago;
    }

    /**
     * Modifica el estado de pago de la inscrpción.
     * @param realizoPago
     */
    public void setRealizoPago(boolean realizoPago) {
        this.realizoPago = realizoPago;
    }

    /**
     * Obtiene la fecha en la que se hizo la insripción.
     * @return Fecha de inscripción
     */
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Modifica el la fecha de la inscrpción.
     * @param fechaInscripcion
     */
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Obtiene las observaciones de la insripcion.
     * @return Las observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Modifica las observaciones de la inscrpción.
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public PaseoInstanciaEntity getInstanciaPaseo() {
        return instanciaPaseo;
    }

    public void setInstanciaPaseo(PaseoInstanciaEntity instanciaPaseo) {
        this.instanciaPaseo = instanciaPaseo;
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
