/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author María del Rosario León
 */
@XmlRootElement
public class GuiaDetailDTO extends GuiaDTO
{
    /**
     * Lista de los paseos del guía.
     */
    private List<PaseoEcologicoDTO> paseos;

    /**
     * Lista de las calificaciones del guía.
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * Constructor de la clase
     */
    public GuiaDetailDTO( )
    {
        super( );
    }

    /**
     * Constructor de la clase
     */
    public GuiaDetailDTO(GuiaEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getPaseosEcologico() != null)
            {
                paseos = new ArrayList<PaseoEcologicoDTO>();
                for (PaseoEcologicoEntity paseo : entity.getPaseosEcologico()) 
                {
                    paseos.add(new PaseoEcologicoDTO(paseo));
                }                
            }
            
            if(entity.getCalificaciones( ) != null)
            {
                calificaciones = new ArrayList<CalificacionDTO>();
                for (CalificacionEntity calificacion : entity.getCalificaciones())
                {
                    calificaciones.add(new CalificacionDTO(calificacion));
                }
            }
         
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de GuiaEntity
     */
    @Override
    public GuiaEntity toEntity() 
    {
        GuiaEntity entity = super.toEntity();
        
        if(this.getPaseos() != null)
        {            
            List<PaseoEcologicoEntity> paseosE = new ArrayList<PaseoEcologicoEntity>( );
            for(PaseoEcologicoDTO paseo : this.getPaseos())
            {
                paseosE.add(paseo.toEntity());
            }
            entity.setPaseosEcologico(paseosE);
        }
        
        
        if(this.getCalificaciones( ) != null)
        {
            List<CalificacionEntity> calificacionesG = new ArrayList<CalificacionEntity>();
            for (CalificacionDTO calificacion : this.getCalificaciones()) 
            {
                calificacionesG.add(calificacion.toEntity());
            }
            entity.setCalificaciones(calificacionesG);
        }
       
        
        return entity;
    }

    /**
     * Obtiene los paseos del guía.
     * @return lista de paseos
     */
    public List<PaseoEcologicoDTO> getPaseos() {
        return paseos;
    }

    /**
     * Modifica la lista de paseos de un guía
     * @param paseos Nueva lista de paseos.
     */
    public void setPaseos(List<PaseoEcologicoDTO> paseos) {
        this.paseos = paseos;
    }

    /**
     * Obtiene las calificaciones del guía.
     * @return lista de calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica la lista de calificaciones de un guía
     * @param calificaciones Nueva lista de paseos.
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
