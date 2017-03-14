/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea Lopez
 */
@XmlRootElement
public class LugarDetailDTO extends LugarDTO
{

    private List<PaseoEcologicoDTO> paseosEcologicos;
    
    public LugarDetailDTO( )
    {
        super( );
    }
    
    public LugarDetailDTO(LugarEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if (entity.getPaseosEcologicos1() != null) {
                paseosEcologicos = new ArrayList<PaseoEcologicoDTO>();
                for (PaseoEcologicoEntity paseoEcologico : entity.getPaseosEcologicos1()) {
                    paseosEcologicos.add(new PaseoEcologicoDTO(paseoEcologico));
                }
            }

        }
    }

    @Override
    public LugarEntity toEntity()
    {
        LugarEntity entity = super.toEntity();
        if (this.getPaseosEcologicos() != null) {
            List<PaseoEcologicoEntity> paseosE = new ArrayList<PaseoEcologicoEntity>();
            for (PaseoEcologicoDTO paseo : this.getPaseosEcologicos()) {
                paseosE.add(paseo.toEntity());
            }
            entity.setPaseosEcologicos1(paseosE);
            entity.setPaseosEcologicos2(paseosE);
        }
        return entity;
    }

    public List<PaseoEcologicoDTO> getPaseosEcologicos() {
        return paseosEcologicos;
    }

    public void setPaseosEcologicos(List<PaseoEcologicoDTO> paseosEcologicos) {
        this.paseosEcologicos = paseosEcologicos;
    }



}
