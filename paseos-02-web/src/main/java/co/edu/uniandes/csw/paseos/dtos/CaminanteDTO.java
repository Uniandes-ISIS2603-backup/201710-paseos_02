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

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class CaminanteDTO extends UsuarioDTO
{
     /**
     * Atributo que representa las condiciones fisicas del caminante. 
     * Se consideran 5 condiciones basicas y a cada una se asigna un valor en el rango (0 <= i <= 10). 
     * Este valor representa el nivel de rendimiento estimado del caminante.
     * A continuación se muestra la convencion seguida para almacenar los valores:
     * condicionesFisicas(0) --> Fuerza
     * condicionesFisicas(1) --> Velocidad
     * condicionesFisicas(2) --> Resistencia
     * condicionesFisicas(3) --> Flexibilidad
     * condicionesFisicas(4) --> Coordinacion
     */
    private List<Integer> condicionesFisicas;
    
    public CaminanteDTO( )
    {
        
    }
    
    public CaminanteDTO(CaminanteEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.identificacion = entity.getIdentificacion();
            this.tipoIdentificacion = entity.getTipoIdentificacion();
            this.edad = entity.getEdad();
            this.telefono = entity.getTelefono();
            this.direccion = entity.getDireccion();
            this.correoElectronico = entity.getCorreoElectronico();
            this.condicionesFisicas = entity.getCondicionesFisicas();
            this.imagen = entity.getImagen();
        }
    }
    public CaminanteEntity toEntity( )
    {
        CaminanteEntity caminante = new CaminanteEntity();
        caminante.setId(this.getId());
        caminante.setNombre(this.getNombre());
        caminante.setIdentificacion(this.getIdentificacion());
        caminante.setTipoIdentificacion(this.getTipoIdentificacion());
        caminante.setEdad(this.getEdad());
        caminante.setTelefono(this.getTelefono());
        caminante.setDireccion(this.getDireccion());
        caminante.setCorreoElectronico(this.getCorreoElectronico());
        caminante.setCondicionesFisicas(this.getCondicionesFisicas());
        caminante.setImagen(this.getImagen());
        return caminante;
    }

    public List<Integer> getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(List<Integer> condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }
    
}
