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
    /*
    conexion con otra clase
    */
    private List<PaseoEcologicoDTO> paseosEcologicos;
    // TODO en el diagrama de clases hay dos relaciones a paseos ecologicos 
    // TODO Debería haber dos atributos distintos con nombres distintos
    // TODO el entioty si tiene los dos atributos con nombres 1 y 2 (deberia tener mejores nombres) 
    // TODO el dto no procesa los dos atributos del entity. 
    /*
    metodo costructor
    */
    public LugarDetailDTO( )
    {
        super( );
    }
    /*
    metodo costructor
    */
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
            // TODO entity.getPaseosEcologicos2 ? 
        }
    }
    /*
    cambia los elementos a entity
    */
    @Override
    public LugarEntity toEntity()
    {
        LugarEntity entity = super.toEntity();
        if (this.getPaseosEcologicos() != null) {
            List<PaseoEcologicoEntity> paseosE = new ArrayList<PaseoEcologicoEntity>();
            for (PaseoEcologicoDTO paseo : this.getPaseosEcologicos()) {
                paseosE.add(paseo.toEntity());
            }
             // TODO por qué asigna los mismos paseos a las dos variables? 
            entity.setPaseosEcologicos1(paseosE);
            entity.setPaseosEcologicos2(paseosE);
        }
        return entity;
    }
    /*
    debueve los paseos ecologicos
    */
    public List<PaseoEcologicoDTO> getPaseosEcologicos() {
        return paseosEcologicos;
    }
    /*
    cambia los paseos ecologicos
    */
    public void setPaseosEcologicos(List<PaseoEcologicoDTO> paseosEcologicos) {
        this.paseosEcologicos = paseosEcologicos;
    }



}
