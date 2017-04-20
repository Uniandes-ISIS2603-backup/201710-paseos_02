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

import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrea Lopez
 */
@XmlRootElement
public class LugarDTO implements Serializable
{
    /*
    *Atributo del id
    */
    private Long id;
    /*
    *Atributo del nombre
    */
    private String nombre;
    /*
    *Atributo de la dirreccion
    */
    private String direccion;
    /*
    *Atributo del info ACCESO
    */
    private String linkGoogleMaps;
    /*
    *Atributo de la imagen
    */
    private String imagen;
    
    /*
    inicializador
    */
     public LugarDTO( )
    {
        
    }
     /*
    inicializador
    */
    public LugarDTO(LugarEntity entity)
    {
        if(entity != null)
        {
            this.id=entity.getId();
            this.direccion=entity.getDireccion();
            this.imagen=entity.getImagen();
            this.linkGoogleMaps=entity.getLinkGoogleMaps();
            this.nombre=entity.getNombre();            
        }
    }
     /*
    cambia los atributos a tipo entity
    */
     public LugarEntity toEntity( )
    {
       LugarEntity entity = new LugarEntity();
       entity.setDireccion(this.direccion);
       entity.setId(this.id);
       entity.setImagen(this.imagen);
       entity.setLinkGoogleMaps(this.linkGoogleMaps);
       entity.setNombre(this.nombre);
       return entity;
    }

    /*
    * devuelve el id
    */
    public Long getId() 
    {
        return id;
    }
    
    /*
    * cambia el id
    */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /*
    * devuelve el nombre
    */
    public String getNombre()
    {
        return nombre;
    }
    
    /*
    * cambia el nombre
    */
    public void setNombre(String n) {
        this.nombre = n;
    }
    
    /*
    * devuelve la dirrecion
    */
    public String getDireccion()
    {
        return direccion;
    }
    /*
    * cambia la direccion
    */
    public void setDireccion(String d)
    {
        this.direccion=d;
    }
    /*
    * devuelve el info del acceso
    */
    public String getLinkGoogleMaps()
    {
        return linkGoogleMaps;
    }
    /*
    * cambia la info
    */
    public void setLinkGoogleMaps(String i)
    {
      this.linkGoogleMaps=i;
    }
    /*
    * devuelve la image
    */
    public String getImagen()
    {
        return imagen;
    }
    /*
    cambia la imagen
    */
    public void setImagen(String i)
    {
        this.imagen=i;
    }    
}

