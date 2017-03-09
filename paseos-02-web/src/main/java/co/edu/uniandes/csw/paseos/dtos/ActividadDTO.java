/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Diego Chaves
 */
@XmlRootElement
public class ActividadDTO implements Serializable
{
    public ActividadDTO( )
    {
        
    }
    
    public ActividadDTO(ActividadEntity entity)
    {
        if(entity != null)
        {
         
        }
    }
     public ActividadEntity toEntity( )
    {
        return null;  
    }
}
