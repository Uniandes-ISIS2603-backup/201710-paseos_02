/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Millan
 */
@XmlRootElement
public class InscripcionDTO
{
     public InscripcionDTO( )
    {
        
    }
    
    public InscripcionDTO(InscripcionEntity entity)
    {
        if(entity != null)
        {
         
        }
    }
     public InscripcionEntity toEntity( )
    {
        return null;  
    }
}
