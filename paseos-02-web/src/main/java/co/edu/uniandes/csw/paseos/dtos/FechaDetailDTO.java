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
    private PaseoEcologicoDTO paseoEcologico;

    public FechaDetailDTO() 
    {
        super();
    }
    public FechaDetailDTO(FechaEntity entity)
    {
        super(entity);
        if(entity != null)
        {
           this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
    }
    
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
    
    
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }   
    

}
