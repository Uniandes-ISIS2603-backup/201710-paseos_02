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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebastian Millan
 */
@Entity
public class InscripcionEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private boolean realizoPago;
    
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    
    @Temporal(TemporalType.DATE)
    private Date fechaDelPaseo;
    
    private String observaciones;
    
    private double costo;
    
    @ManyToOne
    private CaminanteEntity caminante;
    
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CaminanteEntity getCaminante() {
        return caminante;
    }

    public void setCaminante(CaminanteEntity caminante) {
        this.caminante = caminante;
    }

    public PaseoEcologicoEntity getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    
    
    public boolean getRealizoPago() {
        return realizoPago;
    }

    public void setRealizoPago(boolean realizoPago) {
        this.realizoPago = realizoPago;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaDelPaseo() {
        return fechaDelPaseo;
    }

    public void setFechaDelPaseo(Date fechaDelPaseo) {
        this.fechaDelPaseo = fechaDelPaseo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
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
