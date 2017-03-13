/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private Long id;
    private String nombre;
    private String direccion;
    private String infoAcceso;
    private String caracteristicas;
    private String imagen;
    
     public LugarDTO( )
    {
        
    }
    
    public LugarDTO(LugarEntity entity)
    {
        if(entity != null)
        {
            this.id=entity.getId();
            this.direccion=entity.getDireccion();
            this.caracteristicas=entity.getCaracteristicas();
            this.imagen=entity.getImagen();
            this.infoAcceso=entity.getInfoAcceso();
            this.nombre=entity.getNombre();            
        }
    }
     public LugarEntity toEntity( )
    {
       LugarEntity entity = new LugarEntity();
       entity.setCaracteristicas(this.caracteristicas);
       entity.setDireccion(this.direccion);
       entity.setId(this.id);
       entity.setImagen(this.imagen);
       entity.setInfoAcceso(this.infoAcceso);
       entity.setNombre(this.nombre);
       return entity;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }
    public String getDireccion()
    {
        return direccion;
    }
    public void setDireccion(String d)
    {
        this.direccion=d;
    }
    public String getInfoAcceso()
    {
        return infoAcceso;
    }
    public void setInfoAcceso(String i)
    {
        this.infoAcceso=i;
    }
    public String getCaracteristicas()
    {
        return caracteristicas;
    }
    public void setCaracteristicas(String c)
    {
       this.caracteristicas=c;
    }
    public String getImagen()
    {
        return imagen;
    }
    public void setImagen(String i)
    {
        this.imagen=i;
    }
     
     
}

