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

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */

@XmlRootElement
public class CaminanteDetailDTO extends CaminanteDTO
{
    /**
     * Lista de paseos a los que un caminante se a inscrito.
     */
    private List<InscripcionDTO> inscripciones;

    /**
     * Lista de las opiniones de un camiante.
     */
    private List<OpinionParticipanteDTO> opiniones;

    /**
     * Lista de calificación dadas por el caminante.
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * Constructor de la clase
     */
    public CaminanteDetailDTO( )
    {
        super( );
    }

    /**
     * Constructor de la clase
     * @param entity
     */
    public CaminanteDetailDTO(CaminanteEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            
            if(entity.getPaseosInscritos( ) != null)
            {  
                inscripciones = new ArrayList<>();
                for (InscripcionEntity inscripcion : entity.getPaseosInscritos()) 
                {
                    inscripciones.add(new InscripcionDTO(inscripcion));
                }
            }
            
            if(entity.getCalificacionesGuia( )!= null)
            {
                calificaciones = new ArrayList<>();
                for (CalificacionEntity calificacion : entity.getCalificacionesGuia()) {
                    calificaciones.add(new CalificacionDTO(calificacion));
                }
            }
            
            
            if(entity.getOpiniones( ) != null)
            {
                opiniones = new ArrayList<>();
                for (OpinionParticipanteEntity opinion : entity.getOpiniones()) 
                {
                    opiniones.add(new OpinionParticipanteDTO(opinion));
                }
            }
        }
    }

    /**
     * Método que convierte un DTO en un Entity.
     * @return Entity del caminante.
     */
    @Override
    public CaminanteEntity toEntity() 
    {
        CaminanteEntity entity = super.toEntity();
        
        if(this.getInscripciones() != null)
        {            
            List<InscripcionEntity> inscripcionesE = new ArrayList<>( );
            for(InscripcionDTO inscripcion : this.getInscripciones())
            {
                inscripcionesE.add(inscripcion.toEntity());
            }
            entity.setPaseosInscritos(inscripcionesE);
        }
        
        if(this.getCalificaciones() != null)
        {
            List<CalificacionEntity> calificacionesE = new ArrayList<>( );
            for(CalificacionDTO calificacion: this.getCalificaciones())
            {
                calificacionesE.add(calificacion.toEntity());
            }
            entity.setCalificacionesGuia(calificacionesE);
        }
        
        if(this.getOpiniones() != null)
        {
            List<OpinionParticipanteEntity> opinionesE = new ArrayList<>( );
            for(OpinionParticipanteDTO opinion: this.getOpiniones())
            {
                opinionesE.add(opinion.toEntity());
            }
            entity.setOpiniones(opinionesE);
        }
        return entity;
    }

    /**
     * Obtiene la lista de inscripciones de un caminante.
     * @return Lista de inscripciones
     */
    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    /**
     * Modifica la lista de inscripciones de un caminante
     * @param inscripciones nueva lista de inscripciones
     */
    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }

    /**
     * Modifica la lista de opiniones hechas por un caminante.
     * @return Lista de opiniones hechas por un caminante.
     */
    public List<OpinionParticipanteDTO> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<OpinionParticipanteDTO> opiniones) {
        this.opiniones = opiniones;
    }

    /**
     * Obtiene la lista de calificaciones dadas por un caminante.
     * @return Lista de calificaciones dadas por un caminante.
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica la lista de calificaciones dadas por un caminante.
     * @param calificaciones
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }   
}
