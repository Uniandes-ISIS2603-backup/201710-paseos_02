/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Millan
 */
@XmlRootElement
public class CalificacionDetailDTO extends CalificacionDTO 
{
     public CalificacionDetailDTO( )
    {
        super( );
    }
    
    public CalificacionDetailDTO(CalificacionEntity entity)
    {
        super(entity);
    }
    
    @Override
    public CalificacionEntity toEntity() 
    {
        CalificacionEntity entity = super.toEntity();
        return entity;
    }
    
}
