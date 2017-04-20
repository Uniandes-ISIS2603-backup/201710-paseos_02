/* 
 * The MIT License
 *
 * Copyright 2017 Treamwork - Team software development - Los Andes University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
        if (this.getPaseosEcologicosSalida() != null) {
            List<PaseoEcologicoEntity> paseosE = new ArrayList<PaseoEcologicoEntity>();
            for (PaseoEcologicoDTO paseo : this.getPaseosEcologicosSalida()) {
                paseosE.add(paseo.toEntity());
            }
            // TODO por qué asigna los mismos paseos a las dos variables? 
            entity.setPaseosEcologicosSalida(paseosE);

            if (this.getPaseosEcologicosLlegada() != null) {
                paseosE = new ArrayList<PaseoEcologicoEntity>();
                for (PaseoEcologicoDTO paseo : this.getPaseosEcologicosLlegada()) {
                    paseosE.add(paseo.toEntity());
                }
                entity.setPaseosEcologicosLlegada(paseosE);
            }
        }
        return entity; 
    }
    

    public List<PaseoEcologicoDTO> getPaseosEcologicos() {
        return paseosEcologicos;
    }

    public void setPaseosEcologicos(List<PaseoEcologicoDTO> paseosEcologicos) {
        this.paseosEcologicos = paseosEcologicos;
    }

    public List<PaseoEcologicoDTO> getPaseosEcologicosSalida() {
        return paseosEcologicosSalida;
    }

    public void setPaseosEcologicosSalida(List<PaseoEcologicoDTO> paseosEcologicosSalida) {
        this.paseosEcologicosSalida = paseosEcologicosSalida;
    }

    public List<PaseoEcologicoDTO> getPaseosEcologicosLlegada() {
        return paseosEcologicosLlegada;
    }

    public void setPaseosEcologicosLlegada(List<PaseoEcologicoDTO> paseosEcologicosLlegada) {
        this.paseosEcologicosLlegada = paseosEcologicosLlegada;
    }
    
}
  
    




