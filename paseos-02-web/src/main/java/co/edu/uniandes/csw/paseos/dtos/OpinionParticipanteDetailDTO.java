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
 * @author jd.vega11
 */
@XmlRootElement
public class OpinionParticipanteDetailDTO extends OpinionParticipanteDTO
{
    public OpinionParticipanteDetailDTO( )
    {
        super( );
    }
    
    public OpinionParticipanteDetailDTO(OpinionParticipanteEntity entity)
    {
        super(entity);
    }
    
    @Override
    public OpinionParticipanteEntity toEntity() 
    {
        OpinionParticipanteEntity entity = super.toEntity();
        return entity;
    }
    
}
