/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.List;
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
    /*
    *Atributo del info ACCESO
    */
    private String infoAcceso;
    /*
    *Atributo de las caracteristicas
    */
    private String caracteristicas;
    /*
    *Atributo de la imagen
    */
    private String imagen;
    
    /*
    *Atributo de la conexion con otra clase
    */
    @OneToMany(mappedBy = "lugarDeEncuentro")
    private List<PaseoEcologicoEntity> paseosEcologicos1;

    /*
    *Atributo de la conexion con otra clase
    */
    @OneToMany(mappedBy = "lugarDeDestino")
    private List<PaseoEcologicoEntity> paseosEcologicos2;
    
    /*
    * da la lista de paseos ecologicos
    */
    public List<PaseoEcologicoEntity> getPaseosEcologicos1() {
        return paseosEcologicos1;
    }
    
    /*
    * para cambiar el paseo ecologico
    */
    public void setPaseosEcologicos1(List<PaseoEcologicoEntity> paseosEcologicos1) {
        this.paseosEcologicos1 = paseosEcologicos1;
    }
    
    /*
    * La lista de los paeos ecologicos asociados al liugar
    */
    public List<PaseoEcologicoEntity> getPaseosEcologicos2() {
        return paseosEcologicos2;
    }
    
    /*
    * cambia el paseo ecologico
    */
    public void setPaseosEcologicos2(List<PaseoEcologicoEntity> paseosEcologicos2) {
        this.paseosEcologicos2 = paseosEcologicos2;
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
    public String getInfoAcceso()
    {
        return infoAcceso;
    }
    /*
    * cambia la info
    */
    public void setInfoAcceso(String i)
    {
        this.infoAcceso=i;
    }
    /*
    * devuelve las caracteristicas
    */
    public String getCaracteristicas()
    {
        return caracteristicas;
    }
    /*
    * cambia las caracteristicas
    */
    public void setCaracteristicas(String c)
    {
       this.caracteristicas=c;
    }
    
    /*
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
