/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class PaseoEcologicoDTO implements Serializable
{
    /**
     * Atributo que representa el id de un paseo
     */
    private Long id;

    /**
     * Atributo que representa la tematica de un paseo
     */
    private String tematica;

    /**
     * Atributo que representa el numero minimo de caminantes para un paseo
     */
    private Integer nMinimCaminantes;

    /**
     * Atributo que representa el numero maximo de caminantes para un paseo
     */
    private Integer nMaxCaminantes;

    /**
     * Atributo que representa las condiciones fisicas requeridas para realizar el paseo
     */
    private String condicionesFisicas;

    /**
     * Atributo que representa si hay transporte en el paseo ecologico
     */
    private Boolean hayTransporte;

    /**
     * Atributo que representa el costo del paseo ecologico
     */
    private Double costo;

    /**
     * Atributo que representa la descripcón del paseo ecologico
     */
    private String descripcion;

    /**
     * Constructor de la clase
     */
    public PaseoEcologicoDTO( )
    {
        
    }

    /**
     * Constructor de la clase
     */
    public PaseoEcologicoDTO(PaseoEcologicoEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.condicionesFisicas = entity.getCondicionesFisicas();
            this.costo = entity.getCosto();
            this.descripcion = entity.getDescripcion();
            this.hayTransporte = entity.getHayTransporte();
            this.nMaxCaminantes = entity.getnMaxCaminantes();
            this.nMinimCaminantes = entity.getnMinimCaminantes();
            this.tematica = entity.getTematica();
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de PaseoEcologicoEntity
     */
    public PaseoEcologicoEntity toEntity( )
    {
        PaseoEcologicoEntity paseo = new PaseoEcologicoEntity();
        paseo.setId(this.getId());
        paseo.setCondicionesFisicas(this.getCondicionesFisicas());
        paseo.setCosto(this.getCosto());
        paseo.setDescripcion(this.getDescripcion());
        paseo.setHayTransporte(this.getHayTransporte());
        paseo.setnMinimCaminantes(this.getnMinimCaminantes());
        paseo.setnMaxCaminantes(this.getnMaxCaminantes());
        paseo.setTematica(this.getTematica());
        return paseo;
    }

    /**
     * Obtiene id de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return id java.lang.Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica id de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param id el nuevo id de la clase PaseoEcologicoDTO
     */
    public void setId(Long id) {
        this.id = id;
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
     * Modifica tematica de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param tematica el nuevo tematica de la clase PaseoEcologicoDTO
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
     * Modifica nMinimCaminantes de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param nMinimCaminantes el nuevo nMinimCaminantes de la clase PaseoEcologicoDTO
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
     * Modifica nMaxCaminantes de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param nMaxCaminantes el nuevo nMaxCaminantes de la clase PaseoEcologicoDTO
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
     * Modifica condicionesFisicas de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param condicionesFisicas el nuevo condicionesFisicas de la clase PaseoEcologicoDTO
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
     * Modifica hayTransporte de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param hayTransporte el nuevo hayTransporte de la clase PaseoEcologicoDTO
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
     * Modifica costo de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param costo el nuevo costo de la clase PaseoEcologicoDTO
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
     * Modifica descripcion de la clase PaseoEcologicoDTO por el dado por parámetro
     *
     * @param descripcion el nuevo descripcion de la clase PaseoEcologicoDTO
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
