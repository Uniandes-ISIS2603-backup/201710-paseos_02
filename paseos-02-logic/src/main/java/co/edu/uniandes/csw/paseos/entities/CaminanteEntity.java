/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Juan David Vega
 */

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class CaminanteEntity extends UsuarioEntity
{
    private String condicionesFisicas;
    
    @OneToMany(mappedBy = "caminante")
    private List<InscripcionEntity> paseosInscritos;
    
    @OneToMany(mappedBy = "caminante")
    private List<CalificacionEntity> calificacionesGuia;
    
    
    @OneToMany(mappedBy = "caminante")
    private List<OpinionParticipanteEntity> opiniones;
    
    public String getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(String condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }
    
    
    public List<InscripcionEntity> getPaseosInscritos() {
        return paseosInscritos;
    }

    public void setPaseosInscritos(List<InscripcionEntity> paseosInscritos) {
        this.paseosInscritos = paseosInscritos;
    }

    public List<CalificacionEntity> getCalificacionesGuia() {
        return calificacionesGuia;
    }

    public void setCalificacionesGuia(List<CalificacionEntity> calificacionesGuia) {
        this.calificacionesGuia = calificacionesGuia;
    }
    
    public List<OpinionParticipanteEntity> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<OpinionParticipanteEntity> opiniones) {
        this.opiniones = opiniones;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null) {
            return this.getId().equals(((CaminanteEntity) obj).getId());
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
