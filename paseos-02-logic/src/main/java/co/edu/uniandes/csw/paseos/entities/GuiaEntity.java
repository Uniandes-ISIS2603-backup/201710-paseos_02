/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author María del Rosario León
 *
 */
@Entity
public class GuiaEntity extends UsuarioEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromación;

    private String experiencia;


    public Long getId()
    {
        return id;
    }

    @java.lang.Override
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFromación()
    {
        return fromación;
    }

    public void setFromación(String fromación)
    {
        this.fromación = fromación;
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
