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
    private Long id;
    private String nombre;
    private String direccion;
    private String infoAcceso;
    private String caracteristicas;
    private String imagen;



    @OneToMany(mappedBy = "lugarDeEncuentro")
    private List<PaseoEcologicoEntity> paseosEcologicos1;

    @OneToMany(mappedBy = "lugarDeDestino")
    private List<PaseoEcologicoEntity> paseosEcologicos2;


    public List<PaseoEcologicoEntity> getPaseosEcologicos1() {
        return paseosEcologicos1;
    }

    public void setPaseosEcologicos1(List<PaseoEcologicoEntity> paseosEcologicos1) {
        this.paseosEcologicos1 = paseosEcologicos1;
    }

    public List<PaseoEcologicoEntity> getPaseosEcologicos2() {
        return paseosEcologicos2;
    }

    public void setPaseosEcologicos2(List<PaseoEcologicoEntity> paseosEcologicos2) {
        this.paseosEcologicos2 = paseosEcologicos2;
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
