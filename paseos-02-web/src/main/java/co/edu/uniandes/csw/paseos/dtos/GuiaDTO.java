/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.GuiaEntity;

/**
 *
 * @author Juan David Vega
 */
public class GuiaDTO
{
    private long id;
    private String formación;
    private String experiencia;


    public GuiaDTO()
    {

    }

     public GuiaDTO(GuiaEntity entity)
    {
        if (entity != null)
        {
            this.id = entity.getId();
            this.formación = entity.getFromación();
            this.experiencia = entity.getExperiencia();
        }
    }
    

     public GuiaEntity toEntity( )
    {
       GuiaEntity entity = new GuiaEntity();
       entity.setId(this.getId());
       entity.setExperiencia(this.getExperiencia());
       entity.setFromación(this.getFormación());
        return entity;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFormación()
    {
        return formación;
    }

    public void setFormación(String formación)
    {
        this.formación = formación;
    }

    public String getExperiencia()
    {
        return experiencia;
    }

    public void setExperiencia(String experiencia)
    {
        this.experiencia = experiencia;
    }
}
