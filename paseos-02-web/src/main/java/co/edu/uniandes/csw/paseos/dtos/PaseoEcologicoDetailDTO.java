/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class PaseoEcologicoDetailDTO extends PaseoEcologicoDTO {

    /**
     * Atributo que representa el lugar de encuentro del paseo
     */
    private LugarDTO lugarDeEncuentro;

    /**
     * Atributo que representa el lugar de destino del paseo
     */
    private LugarDTO lugarDeDestino;

    /**
     * Atributo que representa la instancia del paseo
     */
    private List<PaseoInstanciaDTO> instancias;

    /**
     * Atributo que representa la lista de actividades del paseo
     */
    private List<ActividadDTO> actividades;

    /**
     * Atributo que representa el guia del paseo
     */
    private GuiaDTO guia;

    /**
     * Atributo que representa la calificacion del guia del paseo
     */
    private List<CalificacionDTO> calificacionesGuia;

    /**
     * Atributo que representa la lista de opiniones del paseo
     */
    private List<OpinionParticipanteDTO> opiniones;

    /**
     * Constructor de la clase
     */
    public PaseoEcologicoDetailDTO() {
        super();
    }

    /**
     * Constructor de la clase
     */
    public PaseoEcologicoDetailDTO(PaseoEcologicoEntity entity) {
        super(entity);
        if (entity != null) {
            if(entity.getLugarDeEncuentro() != null)
            {
                this.lugarDeEncuentro = new LugarDTO(entity.getLugarDeEncuentro());
            }
            if(entity.getLugarDeDestino() != null)
            {
                this.lugarDeDestino = new LugarDTO(entity.getLugarDeDestino());
            }
            
            if(entity.getGuia() != null)
            {
                this.guia = new GuiaDTO(entity.getGuia());
            }
            
            if (entity.getInstancias() != null) {
                instancias = new ArrayList<>();
                for (PaseoInstanciaEntity instancia : entity.getInstancias()) {
                    instancias.add(new PaseoInstanciaDTO(instancia));
                }
            }

            if (entity.getCalificacionesGuia() != null) {
                calificacionesGuia = new ArrayList<>();
                for (CalificacionEntity calificacion : entity.getCalificacionesGuia()) {
                    calificacionesGuia.add(new CalificacionDTO(calificacion));
                }
            }

            if (entity.getActividades() != null) {

                actividades = new ArrayList<>();
                for (ActividadEntity actividad : entity.getActividades()) {
                    actividades.add(new ActividadDTO(actividad));
                }
            }

            if(entity.getOpiniones( ) != null)
            {
                opiniones = new ArrayList<>();
                for(OpinionParticipanteEntity opinion : entity.getOpiniones()) 
                {
                    opiniones.add(new OpinionParticipanteDTO(opinion));
                }
            }
        }
    }

    /**
     * Método que convierte un DTO en Entity
     * @return Instancia de PaseoEcologicoEntity
     */
    @Override
    public PaseoEcologicoEntity toEntity() {
        PaseoEcologicoEntity paseo = super.toEntity();
        if (this.getLugarDeEncuentro() != null) 
        {
            paseo.setLugarDeEncuentro(this.getLugarDeEncuentro().toEntity());
        }
        if(this.getLugarDeDestino() != null)
        {
            paseo.setLugarDeDestino(this.getLugarDeDestino().toEntity());
        }
        if (this.getGuia() != null) {
            paseo.setGuia(this.getGuia().toEntity());
        }

        if (this.getInstancias() != null) {
            List<PaseoInstanciaEntity> instancias = new ArrayList<>();
            for (PaseoInstanciaDTO instancia : this.getInstancias()) {
                instancias.add(instancia.toEntity());
            }
            paseo.setInstancias(instancias);
        }

        if (this.getCalificacionesGuia() != null) {

            List<CalificacionEntity> calificaciones = new ArrayList<>();
            for (CalificacionDTO calificacion : this.getCalificacionesGuia()) {
                calificaciones.add(calificacion.toEntity());
            }
            paseo.setCalificacionesGuia(calificaciones);
        }

        if (this.getActividades() != null) {
            List<ActividadEntity> actividades = new ArrayList<>();
            for (ActividadDTO actividad : this.getActividades()) {

                actividades.add(actividad.toEntity());
            }
            paseo.setActividades(actividades);
        }

        
        if(this.getOpiniones() != null)
        {
            List<OpinionParticipanteEntity> opinionesE = new ArrayList<>();
            for(OpinionParticipanteDTO opinion : this.getOpiniones()) {
                opinionesE.add(opinion.toEntity());
            }
            paseo.setOpiniones(opinionesE);
        }
         
        return paseo;
    }

    /**
     * Obtiene las instancias del paseo.
     * @return lista de instancias.
     */
    public List<PaseoInstanciaDTO> getInstancias() {
        return instancias;
    }

    /**
     * Modifica la lista de instancias del paseo
     * @param instancias nueva lista del paseo
     */
    public void setInstancias(List<PaseoInstanciaDTO> instancias) {
        this.instancias = instancias;
    }

    /**
     * Obtiene el lugar de encuentro del paseo.
     * @return lugar de encuentro.
     */
    public LugarDTO getLugarDeEncuentro() {
        return lugarDeEncuentro;
    }

    /**
     * Modifica el lugar de encuentro del paseo
     * @param lugarDeEncuentro nueva lista del paseo
     */
    public void setLugarDeEncuentro(LugarDTO lugarDeEncuentro) {
        this.lugarDeEncuentro = lugarDeEncuentro;
    }

    /**
     * Obtiene el lugar de destino.
     * @return lugar de destino.
     */
    public LugarDTO getLugarDeDestino() {
        return lugarDeDestino;
    }

    /**
     * Modifica el lugar de destino del paseo
     * @param lugarDeDestino nueva lista del paseo
     */
    public void setLugarDeDestino(LugarDTO lugarDeDestino) {
        this.lugarDeDestino = lugarDeDestino;
    }

    /**
     * Obtiene las actividades del paseo.
     * @return lista de actividades.
     */
    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    /**
     * Modifica las actividades del paseo
     * @param actividades nueva lista del paseo
     */
    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    /**
     * Obtiene el guía del paseo.
     * @return guía.
     */
    public GuiaDTO getGuia() {
        return guia;
    }

    /**
     * Modifica el guia del paseo
     * @param guia nueva lista del paseo
     */
    public void setGuia(GuiaDTO guia) {
        this.guia = guia;
    }


    /**
     * Obtiene las instancias del paseo.
     * @return lista de instancias.
     */
    public List<CalificacionDTO> getCalificacionesGuia() {
        return calificacionesGuia;
    }

    /**
     * Modifica la calificacionesGuia del paseo
     * @param calificacionesGuia nueva lista del paseo
     */
    public void setCalificacionesGuia(List<CalificacionDTO> calificacionesGuia) {
        this.calificacionesGuia = calificacionesGuia;
    }

    /**
     * Obtiene las opiniones sobre el paseo.
     * @return lista de opiniones.
     */
    public List<OpinionParticipanteDTO> getOpiniones() {
        return opiniones;
    }

    /**
     * Modifica las opiniones del paseo
     * @param opiniones nueva lista del paseo
     */
    public void setOpiniones(List<OpinionParticipanteDTO> opiniones) {
        this.opiniones = opiniones;
    }
}
