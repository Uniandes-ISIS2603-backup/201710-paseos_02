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

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author María del Rosario León
 */
@XmlRootElement
public class GuiaDTO extends UsuarioDTO
{
    /**
     * Atributo que representa la formación del guia
     */
    private String formacion;

    /**
     * Atributo que representa la experiencia del guia
     */
    private String experiencia;

    /**
     * Constructor de la clase
     */
    public GuiaDTO( )
    {
        
    }

    /**
     * Constructor de la clase
     */
    public GuiaDTO(GuiaEntity entity)
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
            this.experiencia = entity.getExperiencia();
            this.formacion = entity.getFormacion();
            this.imagen = entity.getImagen();
            this.contrasenia = entity.getContrasenia();
            this.cuentaActiva = entity.getCuentaActiva();
        }
    }

    /**
     * Conviete un GuiaDTO en Entity
     * @return Instancia de GuíaEntity
     */
    public GuiaEntity toEntity( )
    {
        GuiaEntity guia = new GuiaEntity();
        guia.setId(this.getId());
        guia.setNombre(this.getNombre());
        guia.setIdentificacion(this.getIdentificacion());
        guia.setTipoIdentificacion(this.getTipoIdentificacion());
        guia.setEdad(this.getEdad());
        guia.setTelefono(this.getTelefono());
        guia.setDireccion(this.getDireccion());
        guia.setCorreoElectronico(this.getCorreoElectronico());
        guia.setExperiencia(this.getExperiencia());
        guia.setFormacion(this.getFormacion());
        guia.setImagen(this.getImagen());
        guia.setContrasenia(this.getContrasenia());
        guia.setCuentaActiva(this.getCuentaActiva());
        return guia;
    }

    /**
     * Obtiene la formación del guía
     * @return formación
     */
    public String getFormacion() {
        return formacion;
    }

    /**
     * Cambia la formación de un guía.
     * @param formacion
     */
    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    /**
     * Obtiene la experiencia del guía
     * @return experiencia
     */
    public String getExperiencia() {
        return experiencia;
    }

    /**
     * Cambia la experiencia de un guía.
     * @param experiencia
     */
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
