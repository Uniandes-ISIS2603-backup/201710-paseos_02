/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class CaminanteDetailDTO extends CaminanteDTO
{
    public CaminanteDetailDTO( )
    {
        super( );
    }
    
    public CaminanteDetailDTO(CaminanteEntity entity)
    {
        super(entity);
    }
    
    @Override
    public CaminanteEntity toEntity() 
    {
        CaminanteEntity entity = super.toEntity();
        return entity;
    }
    
}
