/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega 
 */
@XmlRootElement
public class PaseoInstanciaDetailDTO extends PaseoInstanciaDTO
{
    /**
     * Atributo que representa el paseo ecologico al que pertenece la instancia
     */
    private PaseoEcologicoDTO paseoEcologico;
    
    /**
     * Atributo que representa la lista de inscripciones de esta instancia del paseo
     */
    private List<InscripcionDTO> inscripciones;

    /**
     * Constructor de la clase
     */
    public PaseoInstanciaDetailDTO()
    {
        super();
    }

    /**
     * Constructor de la clase
     */
    public PaseoInstanciaDetailDTO(PaseoInstanciaEntity entity)
    {
        super(entity);
        if(entity != null)
        {
           this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
        
        if (entity.getInscripciones() != null) {
            inscripciones = new ArrayList<>();
            for (InscripcionEntity inscripcion : entity.getInscripciones()) {
                inscripciones.add(new InscripcionDTO(inscripcion));
            }
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return PaseoInstanciaEntity
     */
    @Override
    public PaseoInstanciaEntity toEntity() 
    {
        PaseoInstanciaEntity entity = super.toEntity();
        
        
         if(this.getPaseoEcologico() != null)
         {
            entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
         }
         
         if (this.getInscripciones() != null) {

            List<InscripcionEntity> inscripcionesE = new ArrayList<>();
            for (InscripcionDTO inscripcion : this.getInscripciones()) {
                inscripcionesE.add(inscripcion.toEntity());
            }
            entity.setInscripciones(inscripcionesE);
        }
       
        return entity;
    }


    /**    
     * @return PaseoEcologicoDTO
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica paseoEcologico por el dado por parámetro
     *
     * @param paseoEcologico el nuevo paseoEcologico
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
}
