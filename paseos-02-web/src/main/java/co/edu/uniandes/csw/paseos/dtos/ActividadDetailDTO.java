/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Diego Chaves
 */
@XmlRootElement
public class ActividadDetailDTO extends ActividadDTO
{
    private PaseoEcologicoDTO paseoEcologico;
    
    public ActividadDetailDTO( )
    {
        super( );
    }
    
    public ActividadDetailDTO(ActividadEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico()); 
        }
    }
    
    @Override
    public ActividadEntity toEntity() 
    {
        ActividadEntity entity = super.toEntity();
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }

    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
    
    
    
}
