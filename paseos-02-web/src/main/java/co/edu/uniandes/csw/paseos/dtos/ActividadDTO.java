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

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Diego Chaves
 */
@XmlRootElement
public class ActividadDTO implements Serializable {

    /**
     * Atributo que representa el id de una actividad
     */
    private Long id;
    /**
     * Atributo que representa el nombre de una actividad
     */
    private String nombre;
    /**
     * Atributo que representa la duracion de una actividad
     */
    private Integer duracion;
    /**
     * Atributo que representa el equipamento de una actividad
     */
    private List<String> equipamiento;

    /**
     * Atributo que representa la descripcion de una actividad
     */
    private String descripcion;

    /**
     * Atributo que representa el paseo que esta asociado a la una actividad
     */
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
     * Constructor de la clase
     */
    public ActividadDTO() {

    }

    /**
     * Constructor de la clase que retorna el DTO con los valores del entity
     * pasado por parametro
     *
     * @param entity
     */
    public ActividadDTO(ActividadEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.duracion = entity.getDuracion();
            this.equipamiento = entity.getEquipamiento();
            this.descripcion = entity.getDescripcion();
        }
    }

    /**
     * Metodo que convierte un DTO a entity
     *
     * @return la entidad
     */
    public ActividadEntity toEntity() {
        ActividadEntity entity = new ActividadEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setDuracion(this.getDuracion());
        entity.setEquipamiento(this.getEquipamiento());
        entity.setDescripcion(this.getDescripcion());
        return entity;
    }
}
