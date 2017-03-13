/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class PaseoEcologicoDTO implements Serializable
{
    private Long id;
    
    private String tematica;
    
    private Integer nMinimCaminantes;
    
    private Integer nMaxCaminantes;
    
    private String condicionesFisicas;
    
    private Boolean hayTransporte;
    
    private Double costo;
    
    private String descripcion;
    
    public PaseoEcologicoDTO( )
    {
        
    }
    
    public PaseoEcologicoDTO(PaseoEcologicoEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.condicionesFisicas = entity.getCondicionesFisicas();
            this.costo = entity.getCosto();
            this.descripcion = entity.getDescripcion();
            this.hayTransporte = entity.getHayTransporte();
            this.nMaxCaminantes = entity.getnMaxCaminantes();
            this.nMinimCaminantes = entity.getnMinimCaminantes();
            this.tematica = entity.getTematica();
        }
    }
    
    public PaseoEcologicoEntity toEntity( )
    {
        PaseoEcologicoEntity paseo = new PaseoEcologicoEntity();
        paseo.setId(this.getId());
        paseo.setCondicionesFisicas(this.getCondicionesFisicas());
        paseo.setCosto(this.getCosto());
        paseo.setDescripcion(this.getDescripcion());
        paseo.setHayTransporte(this.getHayTransporte());
        paseo.setnMinimCaminantes(this.getnMinimCaminantes());
        paseo.setnMaxCaminantes(this.getnMaxCaminantes());
        paseo.setTematica(this.getTematica());
        return paseo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Integer getnMinimCaminantes() {
        return nMinimCaminantes;
    }

    public void setnMinimCaminantes(Integer nMinimCaminantes) {
        this.nMinimCaminantes = nMinimCaminantes;
    }

    public Integer getnMaxCaminantes() {
        return nMaxCaminantes;
    }

    public void setnMaxCaminantes(Integer nMaxCaminantes) {
        this.nMaxCaminantes = nMaxCaminantes;
    }

    public String getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(String condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }

    public Boolean getHayTransporte() {
        return hayTransporte;
    }

    public void setHayTransporte(Boolean hayTransporte) {
        this.hayTransporte = hayTransporte;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
     
    
}
