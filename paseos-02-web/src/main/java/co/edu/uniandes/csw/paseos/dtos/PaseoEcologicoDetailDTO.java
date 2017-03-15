/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.FechaEntity;
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
     * Atributo que representa la fecha del paseo
     */
    private List<FechaDTO> fechas;

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
     * Atributo que representa la lista de inscripciones del paseo
     */
    private List<InscripcionDTO> inscripciones;

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
            this.lugarDeEncuentro = new LugarDTO(entity.getLugarDeEncuentro());
            this.lugarDeDestino = new LugarDTO(entity.getLugarDeDestino());
            this.guia = new GuiaDTO(entity.getGuia());

            if (entity.getFechas() != null) {
                fechas = new ArrayList<FechaDTO>();
                for (FechaEntity fecha : entity.getFechas()) {
                    fechas.add(new FechaDTO(fecha));
                }
            }

            if (entity.getInscripciones() != null) {
                inscripciones = new ArrayList<InscripcionDTO>();
                for (InscripcionEntity inscripcion : entity.getInscripciones()) {
                    inscripciones.add(new InscripcionDTO(inscripcion));
                }
            }

            if (entity.getCalificacionesGuia() != null) {
                calificacionesGuia = new ArrayList<CalificacionDTO>();
                for (CalificacionEntity calificacion : entity.getCalificacionesGuia()) {
                    calificacionesGuia.add(new CalificacionDTO(calificacion));
                }
            }

            if (entity.getActividades() != null) {

                actividades = new ArrayList<ActividadDTO>();
                for (ActividadEntity actividad : entity.getActividades()) {
                    actividades.add(new ActividadDTO(actividad));
                }
            }

               
            if(entity.getOpiniones( ) != null)
            {
                opiniones = new ArrayList<OpinionParticipanteDTO>();
                for (OpinionParticipanteEntity opinion : entity.getOpiniones()) 
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
        paseo.setLugarDeEncuentro(this.getLugarDeEncuentro().toEntity());
        paseo.setLugarDeDestino(this.getLugarDeDestino().toEntity());
        if (this.getGuia() != null) {
            paseo.setGuia(this.getGuia().toEntity());
        }

        if (this.getFechas() != null) {
            List<FechaEntity> fechas = new ArrayList<FechaEntity>();
            for (FechaDTO fecha : this.getFechas()) {
                fechas.add(fecha.toEntity());
            }
            paseo.setFechas(fechas);
        }

        if (this.getInscripciones() != null) {
            List<InscripcionEntity> inscripciones = new ArrayList<InscripcionEntity>();
            for (InscripcionDTO inscripcion : this.getInscripciones()) {
                inscripciones.add(inscripcion.toEntity());
            }
            paseo.setInscripciones(inscripciones);
        }

        if (this.getCalificacionesGuia() != null) {

            List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
            for (CalificacionDTO calificacion : this.getCalificacionesGuia()) {
                calificaciones.add(calificacion.toEntity());
            }
            paseo.setCalificacionesGuia(calificaciones);
        }

        if (this.getActividades() != null) {
            List<ActividadEntity> actividades = new ArrayList<ActividadEntity>();
            for (ActividadDTO actividad : this.getActividades()) {

                actividades.add(actividad.toEntity());
            }
            paseo.setActividades(actividades);
        }

        
        if(this.getOpiniones() != null)
        {
            List<OpinionParticipanteEntity> opiniones = new ArrayList<OpinionParticipanteEntity>();
            for (OpinionParticipanteDTO opinion : this.getOpiniones()) {
                opiniones.add(opinion.toEntity());
            }
            paseo.setOpiniones(opiniones);
        }
         
        return paseo;
    }

    /**
     * Obtiene las fechas del paseo.
     * @return lista de fechas.
     */
    public List<FechaDTO> getFechas() {
        return fechas;
    }

    /**
     * Modifica la lista de fechas del paseo
     * @param fechas nueva lista del paseo
     */
    public void setFechas(List<FechaDTO> fechas) {
        this.fechas = fechas;
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
     * Obtiene las fechas del paseo.
     * @return lista de fechas.
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
     * Obtiene las inscripciones del paseo.
     * @return lista de inscripciones.
     */
    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    /**
     * Modifica las inscripciones del paseo
     * @param inscripciones nueva lista del paseo
     */
    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
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
