/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
