/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maria del Rosario Leon
 */
@XmlRootElement
public class OpinionParticipanteDetailDTO extends OpinionParticipanteDTO
{
    /**
     * Caminante que realiza la opinion.
     */
    private CaminanteDTO caminante;

    /**
     * Paseo sobre el que se realiza la opinion.
     */
    private PaseoEcologicoDTO paseo;

    /**
     * Constructor de la clase
     */
    public OpinionParticipanteDetailDTO( )
    {
        super( );
    }

    /**
     * Constructor de la clase
     */
    public OpinionParticipanteDetailDTO(OpinionParticipanteEntity entity)
    {
        super(entity);
        if (entity != null)
        {
           if (entity.getCaminante()!= null )
           {
               this.caminante = new CaminanteDTO(entity.getCaminante());
           }
           if (entity.getPaseoEcologico() != null)
               {
                   this.paseo = new PaseoEcologicoDTO(entity.getPaseoEcologico());
               }
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de OpinionParticipanteEntity
     */
    @Override
    public OpinionParticipanteEntity toEntity()
    {
        OpinionParticipanteEntity entity = super.toEntity();
        if (this.getCaminante() != null)
        {
            entity.setCaminante(this.getCaminante().toEntity());
        }
        if (this.getPaseo() != null)
        {
            entity.setPaseoEcologico(this.getPaseo().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el caminante que realizo la opinion.
     * @return caminante.
     */
    public CaminanteDTO getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la opinion
     * @param caminante
     */
    public void setCaminante(CaminanteDTO caminante) {
        this.caminante = caminante;
    }

    /**
     * Obtiene el paseo sobre el que se realizo la opinion.
     * @return paseo.
     */
    public PaseoEcologicoDTO getPaseo() {
        return paseo;
    }

    /**
     * Modifica el paseo sobre el que se realizo la opinion
     * @param paseo
     */
    public void setPaseo(PaseoEcologicoDTO paseo) {
        this.paseo = paseo;
    }
    
}
