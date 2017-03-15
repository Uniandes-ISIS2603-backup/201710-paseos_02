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
 * @author Sebastián Millán
 */
@XmlRootElement
public class InscripcionDetailDTO extends InscripcionDTO
{
    /**
     * Caminante que realiza la inscripción.
     */
    private CaminanteDTO caminante;
    
    /**
     * Paseo sobre el que se realiza la inscripción.
     */
    private PaseoEcologicoDTO paseoEcologico;
    
    /**
     * Constructor de la clase
     */
    public InscripcionDetailDTO( )
    {
        super( );
    }
    
    /**
     * Constructor de la clase
     * @param entity entidad con la que se inicializa la clase
     */
    public InscripcionDetailDTO(InscripcionEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getCaminante() != null) this.caminante = new CaminanteDTO(entity.getCaminante());
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
    }
    
    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de InscripcionEntity
     */
    @Override
    public InscripcionEntity toEntity() 
    {
        InscripcionEntity entity = super.toEntity();
        if(this.getCaminante() != null) entity.setCaminante(this.getCaminante().toEntity());
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }

    /**
     * Obtiene el caminante que realizo la inscripción.
     * @return caminante.
     */
    public CaminanteDTO getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la inscrpción
     * @param caminante
     */
    public void setCaminante(CaminanteDTO caminante) {
        this.caminante = caminante;
    }

    /**
     * Obtiene el paseo sobre el que se realizo la inscrpción.
     * @return paseo.
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica el paseo sobre el que se realizo la inscrpción
     * @param paseoEcologico
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
    
}
