/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrea Lopez
 */
@XmlRootElement
public class LugarDetailDTO extends LugarDTO
{
    
    private PaseoEcologicoDTO paseoEcologico;
    
    public LugarDetailDTO( )
    {
        super( );
    }
    
    public LugarDetailDTO(LugarEntity entity)
    {
        super(entity);
        if(entity != null)
     
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
    }
    
    
    @Override
    public LugarEntity toEntity() 
    {
        LugarEntity entity = super.toEntity();
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) 
    {
        this.paseoEcologico = paseoEcologico;
    }
}
