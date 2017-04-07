/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
     * Atributo que representa la lista de instancias del paseo
     */
    @OneToMany(mappedBy = "paseoEcologico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaseoInstanciaEntity> instancias;

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
     * Atributo que representa las condiciones fisicas necesarias para participar en una paseo. 
     * Se consideran 5 condiciones basicas y a cada una se asigna un valor en el rango (0 <= i <= 10). 
     * Este valor representa el nivel de rendimiento requerido para participar en el paseo.
     * A continuación se muestra la convencion seguida para almacenar los valores:
     * condicionesFisicas(0) --> Fuerza
     * condicionesFisicas(1) --> Velocidad
     * condicionesFisicas(2) --> Resistencia
     * condicionesFisicas(3) --> Flexibilidad
     * condicionesFisicas(4) --> Coordinacion
     */
    private List<Integer> condicionesFisicas;

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
     * Atributo que representa la lista de opiniones sobre un paseo
     */
    @OneToMany(mappedBy = "paseoEcologico")
    private List<OpinionParticipanteEntity> opiniones;

    /**
     * Obtiene id de class 
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
     * Obtiene instancias de class 
     *
     * @return instancias
     */
    public List<PaseoInstanciaEntity> getInstancias() {
        return instancias;
    }

    /**
     * Modifica instancias de la clase PaseoEcologicoEntity por el dado por parámetro
     *
     * @param instancias el nuevo instancias de la clase PaseoEcologicoEntity
     */
    public void setInstancias(List<PaseoInstanciaEntity> instancias) {
        this.instancias = instancias;
    }

    /**
     * Obtiene tematica de class 
     *
     * @return tematica 
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
     * Obtiene nMinimCaminantes de class 
     *
     * @return nMinimCaminantes 
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
     * Obtiene nMaxCaminantes de class 
     *
     * @return nMaxCaminantes 
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
     * Obtiene hayTransporte de class 
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
     * Obtiene costo de class 
     *
     * @return costo 
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
     * Obtiene descripcion de class 
     *
     * @return descripcion 
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
     * Obtiene lugarDeEncuentro de class 
     *
     * @return lugarDeEncuentro 
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
     * Obtiene lugarDeDestino de class 
     *
     * @return lugarDeDestino 
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
     * Obtiene actividades de class 
     *
     * @return actividades 
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
     * @return calificacionesGuia 
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
     * Obtiene las opiniones que hicieron los caminantes sobre el paseo
     *
     * @return opiniones 
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

    public List<Integer> getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(List<Integer> condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
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
