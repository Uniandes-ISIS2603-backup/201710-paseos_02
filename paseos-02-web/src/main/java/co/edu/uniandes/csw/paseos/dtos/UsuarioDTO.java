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

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
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
    
    protected String contrasenia;
    
    protected String imagen;

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
     * Cambia la identificacion del usuario.
     * @param identificacion nuevo
     */
    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Obtiene el Tipo de identificación del usuario.
     * @return Tipo de identificación
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * Cambia la tipoIdentificacion del usuario.
     * @param tipoIdentificacion nuevo
     */
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    /**
     * Obtiene el edad del usuario.
     * @return edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Cambia la edad del usuario.
     * @param edad nuevo
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el telefono del usuario.
     * @return telefono
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * Cambia el telefono del usuario.
     * @param telefono nuevo
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el dirección del usuario.
     * @return dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Cambia la direccion del usuario.
     * @param direccion nuevo
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el correo electronico del usuario.
     * @return correo electronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Cambia el correoElectronico del usuario.
     * @param correoElectronico nuevo
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
}
