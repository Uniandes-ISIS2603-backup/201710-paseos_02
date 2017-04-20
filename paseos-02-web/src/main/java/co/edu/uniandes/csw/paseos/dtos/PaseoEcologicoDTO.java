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
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.io.Serializable;
import java.util.List;
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

    public List<Integer> getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(List<Integer> condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }
}
