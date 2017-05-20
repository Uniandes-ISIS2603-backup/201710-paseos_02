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
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Diego Chaves
 */
@Entity
public class ActividadEntity implements Serializable {

    /**
     * Atributo que representa el id de una actividad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Atributo que representa el nombre de una actividad
     */
    private String nombre;
    /**
     * Atributo que representa la duracion de una actividad
     */
    private Integer duracion;
    
    @ElementCollection
    @CollectionTable(name="EQUIPAMIENTO", joinColumns=@JoinColumn(name="ACTIVIDAD_ID"))
    @Column(name="EQUIPO")
    private List<String> equipamiento;

    /**
     * Atributo que representa la descripcion de una actividad
     */
    private String descripcion;

    /**
     * Atributo que representa el paseo que esta asociado a la una actividad
     */
    @PodamExclude
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;

    /**
     * Obtiene id de la actividad
     *
     * @return id de la actividad
     */
    public Long getId() {
        return id;
    }

    /**
     * modifica el id de la actividad
     *
     * @param id el id a poner
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene nombre de la actividad
     *
     * @return nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * modifica el nombre de la actividad
     *
     * @param nombre el nombre a poner
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene duracion de la actividad
     *
     * @return duracion de la actividad
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * modifica la duracion de la actividad
     *
     * @param duracion la duracion a poner
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene lista de equipamiento de la actividad
     *
     * @return lista de equipamiento de la actividad
     */
    public List<String> getEquipamiento() {
        return equipamiento;
    }

    /**
     * modifica la lista de equipamiento de la actividad
     *
     * @param equipamiento la lista de equipamiento a poner
     */
    public void setEquipamiento(List<String> equipamiento) {
        this.equipamiento = equipamiento;
    }

    /**
     * Obtiene descirpcion de la actividad
     *
     * @return descripcion de la actividad
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * modifica la descripcion de la actividad
     *
     * @param descripcion la descripcion a poner
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene Paseo Ecologico asociado de la actividad
     *
     * @return paseeo ecologico asociado de la actividad
     */
    public PaseoEcologicoEntity getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * modifica el paseo ecologico asociado a la actividad
     *
     * @param paseoEcologico el paseo ecologico a poner
     */
    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((ActividadEntity) obj).getId());
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
