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
import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega 
 */
@XmlRootElement
public class PaseoInstanciaDetailDTO extends PaseoInstanciaDTO
{
    /**
     * Atributo que representa el paseo ecologico al que pertenece la instancia
     */
    private PaseoEcologicoDTO paseoEcologico;
    
    /**
     * Atributo que representa la lista de inscripciones de esta instancia del paseo
     */
    private List<InscripcionDTO> inscripciones;

    /**
     * Constructor de la clase
     */
    public PaseoInstanciaDetailDTO()
    {
        super();
    }

    /**
     * Constructor de la clase
     */
    public PaseoInstanciaDetailDTO(PaseoInstanciaEntity entity)
    {
        super(entity);
        if(entity != null)
        {
           this.paseoEcologico = new PaseoEcologicoDTO(entity.getPaseoEcologico());
        }
        
        if (entity.getInscripciones() != null) {
            inscripciones = new ArrayList<>();
            for (InscripcionEntity inscripcion : entity.getInscripciones()) {
                inscripciones.add(new InscripcionDTO(inscripcion));
            }
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return PaseoInstanciaEntity
     */
    @Override
    public PaseoInstanciaEntity toEntity() 
    {
        PaseoInstanciaEntity entity = super.toEntity();
        
        
         if(this.getPaseoEcologico() != null)
         {
            entity.setPaseoEcologico(this.getPaseoEcologico().toEntity());
         }
         
         if (this.getInscripciones() != null) {

            List<InscripcionEntity> inscripcionesE = new ArrayList<>();
            for (InscripcionDTO inscripcion : this.getInscripciones()) {
                inscripcionesE.add(inscripcion.toEntity());
            }
            entity.setInscripciones(inscripcionesE);
        }
       
        return entity;
    }


    /**    
     * @return PaseoEcologicoDTO
     */
    public PaseoEcologicoDTO getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica paseoEcologico por el dado por parámetro
     *
     * @param paseoEcologico el nuevo paseoEcologico
     */
    public void setPaseoEcologico(PaseoEcologicoDTO paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
}
