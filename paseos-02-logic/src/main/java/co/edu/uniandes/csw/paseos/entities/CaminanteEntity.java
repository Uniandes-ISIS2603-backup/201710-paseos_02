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
    /**
     * Atributo que representa las condiciones físicas de un caminante.
     */
    private String condicionesFisicas;

    /**
     * Lista de paseos a los que un caminante se a inscrito.
     */
    @OneToMany(mappedBy = "caminante")
    private List<InscripcionEntity> paseosInscritos;

    /**
     * Lista de calificaciones dadas a los guias por un caminate.
     */
    @OneToMany(mappedBy = "caminante")
    private List<CalificacionEntity> calificacionesGuia;

    /**
     * Lista de opiniones dadas por un caminante.
     */
    @OneToMany(mappedBy = "caminante")
    private List<OpinionParticipanteEntity> opiniones;


    /**
     * Obtiene la condición física de un caminante.
     * @return string, condición fisica.
     */
    public String getCondicionesFisicas() {
        return condicionesFisicas;
    }

    /**
     * Modifica la condición fisica de un caminante.
     * @param condicionesFisicas nueva condición fisica.
     */
    public void setCondicionesFisicas(String condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }

    /**
     * Obtiene la lista de los paseos a los que se ha inscrito un caminante.
     * @return Lista de paseos a los que se ha inscrito el caminante.
     */
    public List<InscripcionEntity> getPaseosInscritos() {
        return paseosInscritos;
    }

    /**
     * Modifica la lista de paseos a los que se ha inscrito un caminante.
     * @param paseosInscritos nueva lista de paseos a los que se ha inscrito un caminante.
     */
    public void setPaseosInscritos(List<InscripcionEntity> paseosInscritos) {
        this.paseosInscritos = paseosInscritos;
    }

    /**
     * Obtiene la lista de calificaciones que un participante le ha dado a los guias.
     * @return lista de las calificaciones de los guias dadas por el caminante.
     */
    public List<CalificacionEntity> getCalificacionesGuia() {
        return calificacionesGuia;
    }

    /**
     * Modifica la lista de calificaciones que un caminante le ha dado a los guias.
     * @param calificacionesGuia nueva lista de calificaciones.
     */
    public void setCalificacionesGuia(List<CalificacionEntity> calificacionesGuia) {
        this.calificacionesGuia = calificacionesGuia;
    }

    /**
     * Obtiene la lista de las opiniones de un participante.
     * @return lista de opiniones del participante.
     */
    public List<OpinionParticipanteEntity> getOpiniones() {
        return opiniones;
    }

    /**
     * Cambia la lista de opiniones de un participante.
     * @param opiniones nueva lista de las opiniones de un participante.
     */
    public void setOpiniones(List<OpinionParticipanteEntity> opiniones) {
        this.opiniones = opiniones;
    }

    /**
     * Verifica si los caminantes son iguales.
     * @param obj tipo caminante.
     * @return true si sus id son iguales, false si sus id son diferentes.
     */
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
