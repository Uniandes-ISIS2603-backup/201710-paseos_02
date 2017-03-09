/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class PaseoEcologicoDTO
{
    public PaseoEcologicoDTO( )
    {
        
    }
    
    public PaseoEcologicoDTO(PaseoEcologicoEntity entity)
    {
        if(entity != null)
        {
         
        }
    }
     public PaseoEcologicoEntity toEntity( )
    {
        return null;  
    }
    
}
