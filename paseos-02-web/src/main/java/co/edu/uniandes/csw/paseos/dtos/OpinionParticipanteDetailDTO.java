/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maria del Rosario Leon
 */
@XmlRootElement
public class OpinionParticipanteDetailDTO extends OpinionParticipanteDTO
{
    private CaminanteDTO caminante;
    private PaseoEcologicoDTO paseo;
    
    public OpinionParticipanteDetailDTO( )
    {
        super( );
    }
    
    public OpinionParticipanteDetailDTO(OpinionParticipanteEntity entity)
    {
        super(entity);
        if (entity != null)
        {
           if (entity.getCaminante()!= null )
           {
               this.caminante = new CaminanteDTO(entity.getCaminante());
           }
           if (entity.getPaseoEcologico() != null)
               {
                   this.paseo = new PaseoEcologicoDTO(entity.getPaseoEcologico());
               }
        }
    }
    
    @Override
    public OpinionParticipanteEntity toEntity() 
    {
        OpinionParticipanteEntity entity = super.toEntity();
        if (this.getCaminante() != null)
        {
            entity.setCaminante(this.getCaminante().toEntity());
        }
        if (this.getPaseo() != null)
        {
            entity.setPaseoEcologico(this.getPaseo().toEntity());
        }
        return entity;
    }

    public CaminanteDTO getCaminante() {
        return caminante;
    }

    public void setCaminante(CaminanteDTO caminante) {
        this.caminante = caminante;
    }

    public PaseoEcologicoDTO getPaseo() {
        return paseo;
    }

    public void setPaseo(PaseoEcologicoDTO paseo) {
        this.paseo = paseo;
    }
    
}
