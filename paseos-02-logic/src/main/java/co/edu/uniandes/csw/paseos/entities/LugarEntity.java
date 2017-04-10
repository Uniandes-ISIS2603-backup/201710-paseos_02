/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author Andrea Lopez
 */
@Entity
public class LugarEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    
    @Column(length = 512)
    private String linkGoogleMaps;
   
    @Column(length = 512)
    private String imagen;
    
    /*
    *Atributo de la conexion con otra clase
    */
    @OneToMany(mappedBy = "lugarDeEncuentro")
    private List<PaseoEcologicoEntity> paseosEcologicosSalida;

    /*
    *Atributo de la conexion con otra clase
    */
    @OneToMany(mappedBy = "lugarDeDestino")
    private List<PaseoEcologicoEntity> paseosEcologicosLlegada;
    
    /*
    * da la lista de paseos ecologicos
    */
    public List<PaseoEcologicoEntity> getPaseosEcologicosSalida() {
        return paseosEcologicosSalida;
    }
    
    /*
    * para cambiar el paseo ecologico
    */
    public void setPaseosEcologicosSalida(List<PaseoEcologicoEntity> paseosEcologicosSalida) {
        this.paseosEcologicosSalida = paseosEcologicosSalida;
    }
    
    /*
    * La lista de los paeos ecologicos asociados al liugar
    */
    public List<PaseoEcologicoEntity> getPaseosEcologicosLlegada() {
        return paseosEcologicosLlegada;
    }
    
    /*
    * cambia el paseo ecologico
    */
    public void setPaseosEcologicosLlegada(List<PaseoEcologicoEntity> paseosEcologicosLlegada) {
        this.paseosEcologicosLlegada = paseosEcologicosLlegada;
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
    }      /*
    * devuelve la image
    */
    public String getImagen()
    {
        return imagen;
    }
    public void setImagen(String i)
    {
        this.imagen=i;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((LugarEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
