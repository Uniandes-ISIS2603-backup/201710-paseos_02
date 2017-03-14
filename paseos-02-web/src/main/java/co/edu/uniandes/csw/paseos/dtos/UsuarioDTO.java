/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jd.vega11
 */
@XmlRootElement
public class UsuarioDTO implements Serializable
{
    /**
     * Atributo que representa elId del usuario.
     */
    protected Long id;

    /**
     * Atributo que representa el nombre del usuario.
     */
    protected String nombre;

    /**
     * Atributo que representa identificación del usuario
     */
    protected Integer identificacion;

    /**
     * tipo de identificación del usuario
     */
    protected String tipoIdentificacion;

    /**
     * Atributo que representa la edad del usuario
     */
    protected Integer edad;

    /**
     * Atributo que representa el telefono del usuario
     */
    protected Integer telefono;

    /**
     * Atributo que representa la dirección del usuario
     */
    protected String direccion;

    /**
     * Atributo que representa el correo electronico del usuario
     */
    protected String correoElectronico;

    /**
     * Constructor de la clase
     */
    public UsuarioDTO( )
    {
        
    }

    /**
     * Obtiene el id del usuario
     * @return id del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del usuario
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del usuario
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la identificación del usuario
     * @return identificacion del usuario
     */
    public Integer getIdentificacion() {
        return identificacion;
    }

    /**
     *
     * @param identificacion
     */
    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    
}
