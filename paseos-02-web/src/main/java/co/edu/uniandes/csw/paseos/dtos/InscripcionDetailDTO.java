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
    private CaminanteDTO caminante;
    
    private PaseoEcologicoDTO paseoEcologico;
    
    public InscripcionDetailDTO( )
    {
        super( );
    }
    
    public InscripcionDetailDTO(InscripcionEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getCaminante() != null) this.caminante = new CaminanteDTO(entity.getCaminante());
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
    }
    
    @Override
    public InscripcionEntity toEntity() 
    {
        InscripcionEntity entity = super.toEntity();
        if(this.getCaminante() != null) entity.setCaminante(this.getCaminante().toEntity());
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }

    public CaminanteDTO getCaminante() {
        return caminante;
    }

    public void setCaminante(CaminanteDTO caminante) {
        this.caminante = caminante;
    }

    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
    
    
}
