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
    /**
     * Atributo que representa el id de un paseo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa la lista de fechas de un paseo
     */
    @OneToMany(mappedBy = "paseoEcologico")
    private List<FechaEntity> fechas;

    /**
     * Atributo que representa la tematica de un paseo
     */
    private String tematica;

    /**
     * Atributo que representa el numero minimo de participantes en un paseo
     */
    private Integer nMinimCaminantes;

    /**
     * Atributo que representa el numero maximo de caminantes para un paseo
     */
    private Integer nMaxCaminantes;

    /**
     * Atributo que representa las condiciones fisicas necesarias para participar en una paseo
     */
    private String condicionesFisicas;

    /**
     * Atributo que representa si hay transporte en el paseo
     */
    private Boolean hayTransporte;

    /**
     * Atributo que representa el costo del paseo
     */
    private Double costo;

    /**
     * Atributo que representa la descripcion del paseo
     */
    private String descripcion;

    /**
     * Atributo que representa el lugar de encuentro del paseo
     */
    @ManyToOne
    private LugarEntity lugarDeEncuentro;

    /**
     * Atributo que representa el lugar de destino del paseo
     */
    @ManyToOne
    private LugarEntity lugarDeDestino;

    /**
     * Atributo que representa la lista de actividades del paseo
     */
    @OneToMany(mappedBy = "paseoEcologico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActividadEntity> actividades;

    /**
     * Atributo que representa el guía de un paseo
     */
    @ManyToOne
    private GuiaEntity guia;

    /**
     * Atributo que representa la lista de calificaciones del guia del paseo
     */
    @OneToMany(mappedBy = "paseoEcologico")
    private List<CalificacionEntity> calificacionesGuia;

    /**
     * Atributo que representa la lista de inscripciones a un paseo
     */
    @OneToMany(mappedBy = "paseoEcologico")
    private List<InscripcionEntity> inscripciones;

    /**
     * Atributo que representa la lista de opiniones sobre un paseo
     */
    @OneToMany(mappedBy = "paseoEcologico")
    private List<OpinionParticipanteEntity> opiniones;

    /**
     * Obtiene id de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return id java.lang.Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica id de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param id el nuevo id de la clase PaseoEcologicoEntity
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene fechas de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return fechas java.util.List<co.edu.uniandes.csw.paseos.entities.FechaEntity>
     */
    public List<FechaEntity> getFechas() {
        return fechas;
    }

    /**
     * Modifica fechas de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param fechas el nuevo fechas de la clase PaseoEcologicoEntity
     */
    public void setFechas(List<FechaEntity> fechas) {
        this.fechas = fechas;
    }

    /**
     * Obtiene tematica de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return tematica java.lang.String
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Modifica tematica de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param tematica el nuevo tematica de la clase PaseoEcologicoEntity
     */
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    /**
     * Obtiene nMinimCaminantes de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return nMinimCaminantes java.lang.Integer
     */
    public Integer getnMinimCaminantes() {
        return nMinimCaminantes;
    }

    /**
     * Modifica nMinimCaminantes de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param nMinimCaminantes el nuevo nMinimCaminantes de la clase PaseoEcologicoEntity
     */
    public void setnMinimCaminantes(Integer nMinimCaminantes) {
        this.nMinimCaminantes = nMinimCaminantes;
    }

    /**
     * Obtiene nMaxCaminantes de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return nMaxCaminantes java.lang.Integer
     */
    public Integer getnMaxCaminantes() {
        return nMaxCaminantes;
    }

    /**
     * Modifica nMaxCaminantes de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param nMaxCaminantes el nuevo nMaxCaminantes de la clase PaseoEcologicoEntity
     */
    public void setnMaxCaminantes(Integer nMaxCaminantes) {
        this.nMaxCaminantes = nMaxCaminantes;
    }

    /**
     * Obtiene condicionesFisicas de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return condicionesFisicas java.lang.String
     */
    public String getCondicionesFisicas() {
        return condicionesFisicas;
    }

    /**
     * Modifica condicionesFisicas de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param condicionesFisicas el nuevo condicionesFisicas de la clase PaseoEcologicoEntity
     */
    public void setCondicionesFisicas(String condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }

    /**
     * Obtiene hayTransporte de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return hayTransporte java.lang.Boolean
     */
    public Boolean getHayTransporte() {
        return hayTransporte;
    }

    /**
     * Modifica hayTransporte de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param hayTransporte el nuevo hayTransporte de la clase PaseoEcologicoEntity
     */
    public void setHayTransporte(Boolean hayTransporte) {
        this.hayTransporte = hayTransporte;
    }

    /**
     * Obtiene costo de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return costo java.lang.Double
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * Modifica costo de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param costo el nuevo costo de la clase PaseoEcologicoEntity
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * Obtiene descripcion de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return descripcion java.lang.String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica descripcion de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param descripcion el nuevo descripcion de la clase PaseoEcologicoEntity
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene lugarDeEncuentro de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return lugarDeEncuentro co.edu.uniandes.csw.paseos.entities.LugarEntity
     */
    public LugarEntity getLugarDeEncuentro() {
        return lugarDeEncuentro;
    }

    /**
     * Modifica lugarDeEncuentro de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param lugarDeEncuentro el nuevo lugarDeEncuentro de la clase PaseoEcologicoEntity
     */
    public void setLugarDeEncuentro(LugarEntity lugarDeEncuentro) {
        this.lugarDeEncuentro = lugarDeEncuentro;
    }

    /**
     * Obtiene lugarDeDestino de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return lugarDeDestino co.edu.uniandes.csw.paseos.entities.LugarEntity
     */
    public LugarEntity getLugarDeDestino() {
        return lugarDeDestino;
    }

    /**
     * Modifica lugarDeDestino de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param lugarDeDestino el nuevo lugarDeDestino de la clase PaseoEcologicoEntity
     */
    public void setLugarDeDestino(LugarEntity lugarDeDestino) {
        this.lugarDeDestino = lugarDeDestino;
    }

    /**
     * Obtiene actividades de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return actividades java.util.List<co.edu.uniandes.csw.paseos.entities.ActividadEntity>
     */
    public List<ActividadEntity> getActividades() {
        return actividades;
    }

    /**
     * Modifica actividades de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param actividades el nuevo actividades de la clase PaseoEcologicoEntity
     */
    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }

    /**
     * Obtiene guia de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return guia co.edu.uniandes.csw.paseos.entities.GuiaEntity
     */
    public GuiaEntity getGuia() {
        return guia;
    }

    /**
     * Modifica guia de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param guia el nuevo guia de la clase PaseoEcologicoEntity
     */
    public void setGuia(GuiaEntity guia) {
        this.guia = guia;
    }

    /**
     * Obtiene calificacionesGuia de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return calificacionesGuia java.util.List<co.edu.uniandes.csw.paseos.entities.CalificacionEntity>
     */
    public List<CalificacionEntity> getCalificacionesGuia() {
        return calificacionesGuia;
    }

    /**
     * Modifica calificacionesGuia de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param calificacionesGuia el nuevo calificacionesGuia de la clase PaseoEcologicoEntity
     */
    public void setCalificacionesGuia(List<CalificacionEntity> calificacionesGuia) {
        this.calificacionesGuia = calificacionesGuia;
    }

    /**
     * Obtiene inscripciones de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return inscripciones java.util.List<co.edu.uniandes.csw.paseos.entities.InscripcionEntity>
     */
    public List<InscripcionEntity> getInscripciones() {
        return inscripciones;
    }

    /**
     * Modifica inscripciones de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param inscripciones el nuevo inscripciones de la clase PaseoEcologicoEntity
     */
    public void setInscripciones(List<InscripcionEntity> inscripciones) {
        this.inscripciones = inscripciones;
    }

    /**
     * Obtiene opiniones de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return opiniones java.util.List<co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity>
     */
    public List<OpinionParticipanteEntity> getOpiniones() {
        return opiniones;
    }

    /**
     * Modifica opiniones de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param opiniones el nuevo opiniones de la clase PaseoEcologicoEntity
     */
    public void setOpiniones(List<OpinionParticipanteEntity> opiniones) {
        this.opiniones = opiniones;
    }

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
