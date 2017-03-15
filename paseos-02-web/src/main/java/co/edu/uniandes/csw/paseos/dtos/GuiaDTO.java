/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
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
