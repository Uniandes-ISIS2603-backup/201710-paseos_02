/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import javax.persistence.*;

/**
 *
 * @author Maria del Rosario Leon
 */
@Entity
public class OpinionParticipanteEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;

    private List <String> imagenes;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDelPaseo;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getComentario()
    {
        return comentario;
    }

    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    public List<String> getImagenes()
    {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes)
    {
        this.imagenes = imagenes;
    }

    public Date getFechaDelPaseo()
    {
        return fechaDelPaseo;
    }

    public void setFechaDelPaseo(Date fechaDelPaseo)
    {
        this.fechaDelPaseo = fechaDelPaseo;
    }
}
