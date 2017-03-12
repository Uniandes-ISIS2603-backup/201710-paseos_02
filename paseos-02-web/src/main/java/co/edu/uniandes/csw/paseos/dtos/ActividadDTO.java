/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ActividadDTO implements Serializable
{
    private Long id;    
    private String nombre;
    private Integer duracion;
    private List<String> equipamiento;
    private List<String> reglas;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public List<String> getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(List<String> equipamiento) {
        this.equipamiento = equipamiento;
    }

    public List<String> getReglas() {
        return reglas;
    }

    public void setReglas(List<String> reglas) {
        this.reglas = reglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public ActividadDTO( )
    {
        
    }
    
    public ActividadDTO(ActividadEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.duracion = entity.getDuracion();
            this.equipamiento = entity.getEquipamiento();
            this.reglas = entity.getReglas();
            this.descripcion = entity.getDescripcion();
        }
    }
     public ActividadEntity toEntity( )
    {
        ActividadEntity entity = new ActividadEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setDuracion(this.getDuracion());
        entity.setEquipamiento(this.getEquipamiento());
        entity.setReglas(this.getReglas());
        entity.setDescripcion(this.getDescripcion());
        return entity;  
    }
}
