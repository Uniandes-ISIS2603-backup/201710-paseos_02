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
 * @author Sebastián Millán
 */
@XmlRootElement
public class CalificacionDetailDTO extends CalificacionDTO 
{
    /**
     * Caminante que realiza la calificación.
     */
    private CaminanteDTO caminante;
    
    /**
     * Guía que recibe la calificación.
     */
    private GuiaDTO guia;
    
    /**
     * Paseo sobre el que se realiza la calificación.
     */
    private PaseoEcologicoDTO paseoEcologico;
    
    /**
     * Constructor de la clase
     */
    public CalificacionDetailDTO( )
    {
        super( );
    }
    
    /**
     * Constructor de la clase
     * @param entity entidad con la que se inicializa la clase
     */
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
    
    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de CalificacionEntity
     */
    @Override
    public CalificacionEntity toEntity() 
    {
        CalificacionEntity entity = super.toEntity();
        if(this.getCaminante() != null) entity.setCaminante(this.getCaminante().toEntity());
        if(this.getGuia() != null)entity.setGuia(this.getGuia().toEntity());
        if(this.getPaseoEcologico() != null)entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }

    /**
     * Obtiene el caminante que realizo la calificación.
     * @return caminante.
     */
    public CaminanteDTO getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la calificación.
     * @param caminante
     */
    public void setCaminante(CaminanteDTO caminante) {
        this.caminante = caminante;
    }

    /**
     * Obtiene el guía que recibe la calificación.
     * @return caminante.
     */
    public GuiaDTO getGuia() {
        return guia;
    }

    /**
     * Modifica el guía que recibio la calificación.
     * @param guia
     */
    public void setGuia(GuiaDTO guia) {
        this.guia = guia;
    }

    /**
     * Obtiene el paseo sobre el que se realizo la calificación.
     * @return paseo.
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica el paseo sobre el que se realizo la calificación.
     * @param paseoEcologico
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
  
}
