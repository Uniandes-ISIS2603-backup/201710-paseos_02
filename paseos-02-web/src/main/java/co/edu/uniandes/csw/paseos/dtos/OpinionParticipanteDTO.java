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
public class OpinionParticipanteDTO 
{
    public OpinionParticipanteDTO( )
    {
        
    }
    
    public OpinionParticipanteDTO(OpinionParticipanteEntity entity)
    {
        if(entity != null)
        {
         
        }
    }
     public OpinionParticipanteEntity toEntity( )
    {
        return null;  
    }
    
}
