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
 * @author Juan David Vega
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class GuiaEntity extends UsuarioEntity
{
    private String formacion;
    
    private String experiencia;
    
    @OneToMany(mappedBy = "guia")
    private List<PaseoEcologicoEntity> paseosEcologico;
    
    @OneToMany(mappedBy = "guia")
    private List<CalificacionEntity> calificaciones;

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public List<PaseoEcologicoEntity> getPaseosEcologico() {
        return paseosEcologico;
    }

    public void setPaseosEcologico(List<PaseoEcologicoEntity> paseosEcologico) {
        this.paseosEcologico = paseosEcologico;
    }
    
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

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
