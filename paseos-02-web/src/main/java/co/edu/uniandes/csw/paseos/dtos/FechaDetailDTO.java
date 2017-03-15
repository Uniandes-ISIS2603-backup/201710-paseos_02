/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.FechaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega 
 */
@XmlRootElement
public class FechaDetailDTO extends FechaDTO
{
    /**
     * Atributo que representa el paseo ecologico que sucede en esa fecha
     */
    private PaseoEcologicoDTO paseoEcologico;

    /**
     * Constructor de la clase
     */
    public FechaDetailDTO()
    {
        super();
    }

    /**
     * Constructor de la clase
     */
    public FechaDetailDTO(FechaEntity entity)
    {
        super(entity);
        if(entity != null)
        {
           this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de OpinionParticipanteEntity
     */
    @Override
    public FechaEntity toEntity() 
    {
        FechaEntity entity = super.toEntity();
        
        
         if(this.getPaseoEcologico() != null)
         {
            entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
         }
       
        return entity;
    }


    /**
     * Obtiene paseoEcologico de class org.jetbrains.java.generate.element.FieldElement
     *
     * @return paseoEcologico co.edu.uniandes.csw.paseos.dtos.PaseoEcologicoDTO
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica paseoEcologico de la clase FechaDetailDTO por el dado por parámetro
     *
     * @param paseoEcologico el nuevo paseoEcologico de la clase FechaDetailDTO
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
}
