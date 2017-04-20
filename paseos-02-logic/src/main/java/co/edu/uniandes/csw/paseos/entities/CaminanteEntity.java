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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
     * Atributo que representa las condiciones fisicas del caminante. 
     * Se consideran 5 condiciones basicas y a cada una se asigna un valor i en el rango (0 <= i <= 10). 
     * Este valor representa el nivel de rendimiento estimado del caminante.
     * A continuación se muestra la convencion seguida para almacenar los valores:
     * condicionesFisicas(0) --> Fuerza
     * condicionesFisicas(1) --> Velocidad
     * condicionesFisicas(2) --> Resistencia
     * condicionesFisicas(3) --> Flexibilidad
     * condicionesFisicas(4) --> Coordinacion
     */
    @ElementCollection
    @CollectionTable(name="CONDFISICASCAMINANTE", joinColumns=@JoinColumn(name="CAMINANTE_ID"))
    @Column(name="VALOR")
    private List<Integer> condicionesFisicas;

    /**
     * Lista de paseos a los que un caminante se a inscrito.
     */
    @OneToMany(mappedBy = "caminante")
    private List<InscripcionEntity> paseosInscritos = new ArrayList<InscripcionEntity>();

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

    public List<Integer> getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(List<Integer> condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
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
