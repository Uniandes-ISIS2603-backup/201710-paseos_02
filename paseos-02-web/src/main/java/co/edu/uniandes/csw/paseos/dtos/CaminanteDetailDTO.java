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
@XmlRootElement
public class CaminanteDetailDTO extends CaminanteDTO
{
    private List<InscripcionDTO> inscripciones;
    
    private List<OpinionParticipanteDTO> opiniones;
    
    private List<CalificacionDTO> calificaciones;
    
    public CaminanteDetailDTO( )
    {
        super( );
    }
    
    public CaminanteDetailDTO(CaminanteEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            /*
            if(entity.getPaseosInscritos( ) != null)
            {
                 inscripciones = new ArrayList<InscripcionDTO>();
                for (InscripcionEntity inscripcion : entity.getPaseosInscritos()) 
                {
                    inscripciones.add(new InscripcionDTO(inscripcion));
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

            if(entity.getCalificacionesGuia( )!= null)
            {
                calificaciones = new ArrayList<CalificacionDTO>();
                for (CalificacionEntity calificacion : entity.getCalificacionesGuia()) {
                    calificaciones.add(new CalificacionDTO(calificacion));
                }
            }
           
            */
        }
    }
    
    @Override
    public CaminanteEntity toEntity() 
    {
        CaminanteEntity entity = super.toEntity();
        /*
        if(this.getInscripciones() != null)
        {            
            List<InscripcionEntity> inscripcionesE = new ArrayList<InscripcionEntity>( );
            for(InscripcionDTO inscripcion : this.getInscripciones())
            {
                inscripcionesE.add(inscripcion.toEntity());
            }
            entity.setPaseosInscritos(inscripcionesE);
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
        
        if(this.getCalificaciones() != null)
        {
            List<CalificacionEntity> calificacionesE = new ArrayList<CalificacionEntity>( );
            for(CalificacionDTO calificacion: this.getCalificaciones())
            {
                calificacionesE.add(calificacion.toEntity());
            }
            entity.setCalificacionesGuia(calificacionesE);
        }
        */
        return entity;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<OpinionParticipanteDTO> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<OpinionParticipanteDTO> opiniones) {
        this.opiniones = opiniones;
    }

    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    
    
}
