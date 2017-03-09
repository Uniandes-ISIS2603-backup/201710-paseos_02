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
public class InscripcionDetailDTO extends InscripcionDTO
{
    public InscripcionDetailDTO( )
    {
        super( );
    }
    
    public InscripcionDetailDTO(InscripcionEntity entity)
    {
        super(entity);
    }
    
    @Override
    public InscripcionEntity toEntity() 
    {
        InscripcionEntity entity = super.toEntity();
        return entity;
    }
    
}
