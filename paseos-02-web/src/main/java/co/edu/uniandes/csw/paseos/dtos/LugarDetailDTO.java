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
    private List<PaseoEcologicoDTO> paseosEcologicosSalida;
    private List<PaseoEcologicoDTO> paseosEcologicosLlegada;
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
            if (entity.getPaseosEcologicosSalida() != null) {
                paseosEcologicos = new ArrayList<PaseoEcologicoDTO>();
                for (PaseoEcologicoEntity paseoEcologico : entity.getPaseosEcologicosSalida()) {
                    paseosEcologicos.add(new PaseoEcologicoDTO(paseoEcologico));
                }
            }
            if (entity.getPaseosEcologicosLlegada() != null) {
                paseosEcologicos = new ArrayList<PaseoEcologicoDTO>();
                for (PaseoEcologicoEntity paseoEcologico : entity.getPaseosEcologicosLlegada()) {
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
        if (this.getPaseosEcologicosSalida() != null)
        {
            List<PaseoEcologicoEntity> paseosE = new ArrayList<PaseoEcologicoEntity>();
            for (PaseoEcologicoDTO paseo : this.getPaseosEcologicosSalida()) 
            {
                paseosE.add(paseo.toEntity());
            }
             // TODO por qué asigna los mismos paseos a las dos variables? 
            entity.setPaseosEcologicosSalida(paseosE);
            
             if (this.getPaseosEcologicosLlegada() != null)
        {
            paseosE = new ArrayList<PaseoEcologicoEntity>();
            for (PaseoEcologicoDTO paseo : this.getPaseosEcologicosLlegada()) 
            {
                paseosE.add(paseo.toEntity());
            }
            entity.setPaseosEcologicosLlegada(paseosE);
        }
        }
        return entity; 
    }
    /*
    debueve los paseos ecologicos
    */
    public List<PaseoEcologicoDTO> getPaseosEcologicosLlegada() {
        return paseosEcologicosLlegada;
    }
    /*
    cambia los paseos ecologicos
    */
    public void setPaseosEcologicosLlegada(List<PaseoEcologicoDTO> paseosEcologicos) {
        this.paseosEcologicosLlegada = paseosEcologicos;
    }
    
    /*
    cambia los paseos ecologicos
    */
    public void setPaseosEcologicosSalida(List<PaseoEcologicoDTO> paseosEcologicos) {
        this.paseosEcologicosSalida = paseosEcologicos;
    }

    /*
    debueve los paseos ecologicos
    */
    public List<PaseoEcologicoDTO> getPaseosEcologicosSalida() {
        return paseosEcologicosSalida;
    }
}
