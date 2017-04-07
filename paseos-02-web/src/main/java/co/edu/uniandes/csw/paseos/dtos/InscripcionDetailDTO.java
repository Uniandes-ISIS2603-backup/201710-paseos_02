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
    private PaseoInstanciaDTO instanciaPaseo;
    
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
            if(entity.getInstanciaPaseo() != null) this.instanciaPaseo = new PaseoInstanciaDTO(entity.getInstanciaPaseo());
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
        if(this.getInstanciaPaseo() != null) entity.setInstanciaPaseo(this.getInstanciaPaseo().toEntity());
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

    public PaseoInstanciaDTO getInstanciaPaseo() {
        return instanciaPaseo;
    }

    public void setInstanciaPaseo(PaseoInstanciaDTO instanciaPaseo) {
        this.instanciaPaseo = instanciaPaseo;
    }
    
}
