/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;

/**
 *
 * @author Juan David Vega
 */
public class GuiaDTO 
{
     public GuiaDTO( )
    {
        
    }
    
    public GuiaDTO(GuiaEntity entity)
    {
        if(entity != null)
        {
         
        }
    }
     public GuiaEntity toEntity( )
    {
        return null;  
    }
    
}
