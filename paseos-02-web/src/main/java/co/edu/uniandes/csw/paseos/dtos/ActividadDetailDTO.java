/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Diego Chaves
 */
@XmlRootElement
public class ActividadDetailDTO extends ActividadDTO
{
     /**Atributo que representa el paseo ecologio al cual esta relacionada esta actividad
     */
    private PaseoEcologicoDTO paseoEcologico;
    /**Constructor de la clase 
     */
    public ActividadDetailDTO( )
    {
        super( );
    }
    /**Constructor de la clase 
      * @param entity
     */
    public ActividadDetailDTO(ActividadEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico()); 
        }
    }
    
    /**Metodo que convierte este DTO en un ActividadEntity
     */
    @Override
    public ActividadEntity toEntity() 
    {
        ActividadEntity entity = super.toEntity();
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
        return entity;
    }
    /**Metodo que retorna el paseo ecologico al cual esta asociada esta actividad
      * @return el paseo ecologico al cual esta asociada esta actividad
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }
    /**Metodo que modifica el paseo ecologico al cual esta asociada esta actividad
     * @param paseoEcologico el nuevo paseo ecologico al cual esta asociada esta actividad
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
}
