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
public class LugarDTO 
{
    public LugarDTO( )
    {
        
    }
    
    public LugarDTO(LugarEntity entity)
    {
        if(entity != null)
        {
         
        }
    }
    
    public LugarEntity toEntity( )
    {
        return null;  
    }
    
}
