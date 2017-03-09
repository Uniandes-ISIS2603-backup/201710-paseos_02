/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class GuiaDetailDTO extends GuiaDTO
{
    public GuiaDetailDTO( )
    {
        super( );
    }
    
    public GuiaDetailDTO(GuiaEntity entity)
    {
        super(entity);
    }
    
    @Override
    public GuiaEntity toEntity() 
    {
        GuiaEntity entity = super.toEntity();
        return entity;
    }
    
}
