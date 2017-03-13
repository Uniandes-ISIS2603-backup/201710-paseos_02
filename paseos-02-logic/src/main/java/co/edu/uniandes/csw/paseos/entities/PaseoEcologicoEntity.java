/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan David Vega
 */
@Entity
public class PaseoEcologicoEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "paseoEcologico")
    private List<FechaEntity> fechas;
   
    private String tematica;
    
    private Integer nMinimCaminantes;
    
    private Integer nMaxCaminantes;
    
    private String condicionesFisicas;
    
    private Boolean hayTransporte;
    
    private Double costo;
    
    private String descripcion;
    
    //@ManyToOne
    //private LugarEntity lugarDeEncuentro;
    
   // @ManyToOne
    //private LugarEntity lugarDeDestino;
    
    //@OneToMany(mappedBy = "paseoEcologico", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ActividadEntity> actividades;
    
    @ManyToOne
    private GuiaEntity guia;
    
    @OneToMany(mappedBy = "paseoEcologico")
    private List<CalificacionEntity> calificacionesGuia;
    
    @OneToMany(mappedBy = "paseoEcologico")
    private List<InscripcionEntity> inscripciones;
    
    //@OneToMany(mappedBy = "paseoEcologico")
    //private List<OpinionParticipanteEntity> opiniones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public List<FechaEntity> getFechas() {
        return fechas;
    }

    public void setFechas(List<FechaEntity> fechas) 
    {
        this.fechas = fechas;
    }
    

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Integer getnMinimCaminantes() {
        return nMinimCaminantes;
    }

    public void setnMinimCaminantes(Integer nMinimCaminantes) {
        this.nMinimCaminantes = nMinimCaminantes;
    }

    public Integer getnMaxCaminantes() {
        return nMaxCaminantes;
    }

    public void setnMaxCaminantes(Integer nMaxCaminantes) {
        this.nMaxCaminantes = nMaxCaminantes;
    }

    public String getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(String condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }

    public Boolean getHayTransporte() {
        return hayTransporte;
    }

    public void setHayTransporte(Boolean hayTransporte) {
        this.hayTransporte = hayTransporte;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
    public LugarEntity getLugarDeEncuentro() {
        return lugarDeEncuentro;
    }

    public void setLugarDeEncuentro(LugarEntity lugarDeEncuentro) {
        this.lugarDeEncuentro = lugarDeEncuentro;
    }

    public LugarEntity getLugarDeDestino() {
        return lugarDeDestino;
    }

    public void setLugarDeDestino(LugarEntity lugarDeDestino) {
        this.lugarDeDestino = lugarDeDestino;
    }

    public List<ActividadEntity> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }
    */
    public GuiaEntity getGuia() {
        return guia;
    }

    public void setGuia(GuiaEntity guia) {
        this.guia = guia;
    }

    
    public List<CalificacionEntity> getCalificacionesGuia() {
        return calificacionesGuia;
    }

    public void setCalificacionesGuia(List<CalificacionEntity> calificacionesGuia) {
        this.calificacionesGuia = calificacionesGuia;
    }

    public List<InscripcionEntity> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionEntity> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    /*
    public List<OpinionParticipanteEntity> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<OpinionParticipanteEntity> opiniones) {
        this.opiniones = opiniones;
    }
    */
     @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null) {
            return this.getId().equals(((PaseoEcologicoEntity) obj).getId());
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
