/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


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
    
    
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public PaseoEcologicoEntity getPaseoEcologico() 
    {
        return paseoEcologico;
    }

    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) 
    {
        this.paseoEcologico = paseoEcologico;
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
