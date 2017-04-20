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
    private PaseoEcologicoDTO paseoEcologico;

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
           if(entity.getCaminante() != null) this.caminante = new CaminanteDTO(entity.getCaminante());
           if(entity.getPaseoEcologico() != null) this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
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
        if(this.getCaminante() != null) entity.setCaminante(this.getCaminante().toEntity());
        if(this.getPaseoEcologico() != null) entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
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

    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }
    
}
