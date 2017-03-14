/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maria del Rosario Leon
 */
@Entity
public class OpinionParticipanteEntity implements Serializable
{
    /**
     * Id de la opinion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Opinion sobre el paseo realizado.
     */
    private String comentario;

    /**
     * Ruta de la imagen.
     */
    private String imagen;

    /**
     * Fecha en que se realiza la opinion.
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;

    /**
     * Paseo ecologico sobre el que se realiza la opinion.
     */
    @ManyToOne
    private PaseoEcologicoEntity paseoEcologico;

    /**
     * Caminante que realiza la opinion.
     */
    @ManyToOne
    private CaminanteEntity caminante;

    /**
     * Obtiene el id de la opinion.
     * @return id de la opinion.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica el id de la opinion.
     * @param id nuevo id.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Obtiene el comentario hecho por el caminante.
     * @return comentario.
     */
    public String getComentario()
    {
        return comentario;
    }

    /**
     * Modifica el comentario hecho por el caminante.
     * @param comentario
     */
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    /**
     * Obtiene la fecha en que se realizo la opinion.
     * @return fecha.
     */
    public Date getFecha()
    {
        return fecha;
    }

    /**
     * Modifica la fecha en la que se realizo la opinion.
     * @param fecha nueva.
     */
    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    /**
     * Obtiene la ruta de la imagen.
     * @return ruta imagen.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Cambia la ruta de la imagen.
     * @param imagen ruta nueva imagen.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el paseo ecológico sobre el cual se realizo la opinion.
     * @return paseo ecológico.
     */
    public PaseoEcologicoEntity getPaseoEcologico() {
        return paseoEcologico;
    }

    /**
     * Modifica el paseo ecológico sobre el cual se realiza la opinion
     * @param paseoEcologico Nuevo paseo ecológico.
     */
    public void setPaseoEcologico(PaseoEcologicoEntity paseoEcologico) {
        this.paseoEcologico = paseoEcologico;
    }

    /**
     * Obtiene el caminante que realizo la opinion.
     * @return caminante
     */
    public CaminanteEntity getCaminante() {
        return caminante;
    }

    /**
     * Modifica el caminante que realizo la opinion.
     * @param caminante nuevo caminante.
     */
    public void setCaminante(CaminanteEntity caminante) {
        this.caminante = caminante;
    }
    
    
}
