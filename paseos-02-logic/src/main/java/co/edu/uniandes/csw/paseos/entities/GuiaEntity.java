/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author María del Rosario León
 *
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class GuiaEntity extends UsuarioEntity
{
    /**
     * Atributo que representa la formación que tiene un guía.
     */
    private String formacion;

    /**
     * -atributo que representa la experiencia de un guía.
     */
    private String experiencia;

    /**
     * Lista de paseos ecologicos de un guía.
     */
    @OneToMany(mappedBy = "guia")
    private List<PaseoEcologicoEntity> paseosEcologico;

    /**
     * Lista de calificaciones de un guía.
     */
    @OneToMany(mappedBy = "guia")
    private List<CalificacionEntity> calificaciones;

    /**
     * Obtiene la formación de un guía.
     * @return formación del guía.
     */
    public String getFormacion() {
        return formacion;
    }

    /**
     * Cambia la formación de un guía.
     * @param formacion nueva formación.
     */
    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    /**
     * Obtiene la experiencia de un guía.
     * @return La experiencia del guía.
     */
    public String getExperiencia() {
        return experiencia;
    }

    /**
     * Cambia la experiencia de un guía.
     * @param experiencia nueva experiencia del guía.
     */
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * Obtiene los paseos ecologicos.
     * @return lista de paseos ecologicos.
     */
    public List<PaseoEcologicoEntity> getPaseosEcologico() {
        return paseosEcologico;
    }

    public void setPaseosEcologico(List<PaseoEcologicoEntity> paseosEcologico) {
        this.paseosEcologico = paseosEcologico;
    }

    /**
     * Obtiene las calificaciones de un guía.
     * @return lista de calififcaciones de un guía.
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones de un guía.
     * @param calificaciones Nueva lista de calificaciones de un guía.
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null) {
            return this.getId().equals(((GuiaEntity) obj).getId());
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
