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
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Juan David Vega
 */ 
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public  class UsuarioEntity implements Serializable
{
    /**
     * Atributo que representa el id del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo que representa el nombre del usuario.
     */
    private String nombre;

    /**
     * Atributo que representa la identificación del usuario.
     */
    private Integer identificacion;

    /**
     * Atributo que representa el tipo de identificación del usuario.
     */
    private String tipoIdentificacion;

    /**
     * Atributo que representa la edad del usuario.
     */
    private Integer edad;

    /**
     * Atributo que representa el telefono del usuario.
     */
    private Integer telefono;

    /**
     * Atributo que representa la dirección del usuario.
     */
    private String direccion;

    /**
     * Atributo que representa el correo electronico de l usuario.
     */
    private String correoElectronico;
    
    private String imagen;

    /**
     * Obtiene el id del usuario.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id del usuario
     * @param id nuevo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del usuario.
     * @param nombre nuevo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificación del usuario.
     * @return identificación
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
    public void setCorreoElectronico(String correoElectronico) 
    {
        this.correoElectronico = correoElectronico;
    }    

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
     @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null) {
            return this.getId().equals(((UsuarioEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
