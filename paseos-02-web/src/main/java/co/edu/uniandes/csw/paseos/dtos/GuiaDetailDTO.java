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

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author María del Rosario León
 */
@XmlRootElement
public class GuiaDetailDTO extends GuiaDTO {

    /**
     * Lista de paseos ecologicos de un guía.
     */
    private List<PaseoEcologicoDTO> paseosEcologicos;

    /**
     * Lista de calificaciones de un guía.
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * Constructor de la clase
     */
    public GuiaDetailDTO() {
        super();
    }

    /**
     * Constructor de la clase
     */
    public GuiaDetailDTO(GuiaEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getPaseosEcologicos() != null) {
                paseosEcologicos = new ArrayList<>();
                for (PaseoEcologicoEntity paseo : entity.getPaseosEcologicos()) {
                    paseosEcologicos.add(new PaseoEcologicoDTO(paseo));
                }
            }

            if (entity.getCalificaciones() != null) {
                calificaciones = new ArrayList<>();
                for (CalificacionEntity calificacion : entity.getCalificaciones()) {
                    calificaciones.add(new CalificacionDTO(calificacion));
                }
            }

        }
    }

    /**
     * Método que convierte un DTO en Entity
     *
     * @return Instancia de GuiaEntity
     */
    @Override
    public GuiaEntity toEntity() {
        GuiaEntity entity = super.toEntity();

        if (this.getPaseosEcologicos() != null) {
            List<PaseoEcologicoEntity> paseosE = new ArrayList<>();
            for (PaseoEcologicoDTO paseo : this.getPaseosEcologicos()) {
                paseosE.add(paseo.toEntity());
            }
            entity.setPaseosEcologicos(paseosE);
        }

        if (this.getCalificaciones() != null) {
            List<CalificacionEntity> calificacionesG = new ArrayList<>();
            for (CalificacionDTO calificacion : this.getCalificaciones()) {
                calificacionesG.add(calificacion.toEntity());
            }
            entity.setCalificaciones(calificacionesG);
        }

        return entity;
    }

    public List<PaseoEcologicoDTO> getPaseosEcologicos() {
        return paseosEcologicos;
    }

    public void setPaseosEcologicos(List<PaseoEcologicoDTO> paseosEcologicos) {
        this.paseosEcologicos = paseosEcologicos;
    }

    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

}
