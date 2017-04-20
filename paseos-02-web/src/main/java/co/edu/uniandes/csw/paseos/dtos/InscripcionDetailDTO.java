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
