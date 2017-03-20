/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

// TODO revisar los warnings y arreglar los problemas
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
     */
    public CaminanteDetailDTO(CaminanteEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            
            if(entity.getPaseosInscritos( ) != null)
            {  // TODO cambiar ArrayList<InscripcionDTO>() por ArrayList<>() y en todos los demás
                 inscripciones = new ArrayList<InscripcionDTO>();
                for (InscripcionEntity inscripcion : entity.getPaseosInscritos()) 
                {
                    inscripciones.add(new InscripcionDTO(inscripcion));
                }
            }
            
            if(entity.getCalificacionesGuia( )!= null)
            {
                calificaciones = new ArrayList<CalificacionDTO>();
                for (CalificacionEntity calificacion : entity.getCalificacionesGuia()) {
                    calificaciones.add(new CalificacionDTO(calificacion));
                }
            }
            
            
            if(entity.getOpiniones( ) != null)
            {
                opiniones = new ArrayList<OpinionParticipanteDTO>();
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
            List<InscripcionEntity> inscripcionesE = new ArrayList<InscripcionEntity>( );
            for(InscripcionDTO inscripcion : this.getInscripciones())
            {
                inscripcionesE.add(inscripcion.toEntity());
            }
            entity.setPaseosInscritos(inscripcionesE);
        }
        
        if(this.getCalificaciones() != null)
        {
            List<CalificacionEntity> calificacionesE = new ArrayList<CalificacionEntity>( );
            for(CalificacionDTO calificacion: this.getCalificaciones())
            {
                calificacionesE.add(calificacion.toEntity());
            }
            entity.setCalificacionesGuia(calificacionesE);
        }
        
        if(this.getOpiniones() != null)
        {
            List<OpinionParticipanteEntity> opinionesE = new ArrayList<OpinionParticipanteEntity>( );
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
     * @return Lista de calificaciones dadas por un caminante.
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }   
}
