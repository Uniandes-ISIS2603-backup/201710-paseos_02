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
    private CaminanteDTO caminante;
    
    private GuiaDTO guia;
    
    private PaseoEcologicoDTO paseoEcologico;
    
    public CalificacionDetailDTO( )
    {
        super( );
    }
    
    public CalificacionDetailDTO(CalificacionEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getCaminante() != null) this.caminante = new CaminanteDTO(entity.getCaminante());
            if(entity.getGuia() != null) this.guia = new GuiaDTO(entity.getGuia());
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
    }
    
    @Override
    public CalificacionEntity toEntity() 
    {
        CalificacionEntity entity = super.toEntity();
        if(this.getCaminante() != null) entity.setCaminante(this.getCaminante().toEntity());
        if(this.getGuia() != null)entity.setGuia(this.getGuia().toEntity());
        if(this.getPaseoEcologico() != null)entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }

    public CaminanteDTO getCaminante() {
        return caminante;
    }

    public void setCaminante(CaminanteDTO caminante) {
        this.caminante = caminante;
    }

    public GuiaDTO getGuia() {
        return guia;
    }

    public void setGuia(GuiaDTO guia) {
        this.guia = guia;
    }

    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
    
    
    
}
