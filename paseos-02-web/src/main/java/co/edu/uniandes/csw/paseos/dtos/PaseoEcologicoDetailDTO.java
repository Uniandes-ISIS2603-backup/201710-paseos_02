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

    private LugarDTO lugarDeEncuentro;

    private LugarDTO lugarDeDestino;

    private List<FechaDTO> fechas;

    private List<ActividadDTO> actividades;

    private GuiaDTO guia;

    private List<CalificacionDTO> calificacionesGuia;

    private List<InscripcionDTO> inscripciones;

    private List<OpinionParticipanteDTO> opiniones;

    public PaseoEcologicoDetailDTO() {
        super();
    }

    public PaseoEcologicoDetailDTO(PaseoEcologicoEntity entity) {
        super(entity);
        if (entity != null) {
            //this.lugarDeEncuentro = new LugarDTO(entity.getLugarDeEncuentro());
            //this.lugarDeDestino = new LugarDTO(entity.getLugarDeDestino());
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

            /*    
            if(entity.getOpiniones( ) != null)
            {
                opiniones = new ArrayList<OpinionParticipanteDTO>();
                for (OpinionParticipanteEntity opinion : entity.getOpiniones()) 
                {
                    opiniones.add(new OpinionParticipanteDTO(opinion));
                }
            }
            
             */
        }
    }

    @Override
    public PaseoEcologicoEntity toEntity() {
        PaseoEcologicoEntity paseo = super.toEntity();
        // paseo.setLugarDeEncuentro(this.getLugarDeEncuentro().toEntity());
        // paseo.setLugarDeDestino(this.getLugarDeDestino().toEntity());
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

        /*
        if(this.getOpiniones() != null)
        {
            List<OpinionParticipanteEntity> opiniones = new ArrayList<OpinionParticipanteEntity>();
            for (OpinionParticipanteDTO opinion : this.getOpiniones()) {
                opiniones.add(opinion.toEntity());
            }
            paseo.setOpiniones(opiniones);
        }
         */
        return paseo;
    }

    public List<FechaDTO> getFechas() {
        return fechas;
    }

    public void setFechas(List<FechaDTO> fechas) {
        this.fechas = fechas;
    }

    public LugarDTO getLugarDeEncuentro() {
        return lugarDeEncuentro;
    }

    public void setLugarDeEncuentro(LugarDTO lugarDeEncuentro) {
        this.lugarDeEncuentro = lugarDeEncuentro;
    }

    public LugarDTO getLugarDeDestino() {
        return lugarDeDestino;
    }

    public void setLugarDeDestino(LugarDTO lugarDeDestino) {
        this.lugarDeDestino = lugarDeDestino;
    }

    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    public GuiaDTO getGuia() {
        return guia;
    }

    public void setGuia(GuiaDTO guia) {
        this.guia = guia;
    }

    public List<CalificacionDTO> getCalificacionesGuia() {
        return calificacionesGuia;
    }

    public void setCalificacionesGuia(List<CalificacionDTO> calificacionesGuia) {
        this.calificacionesGuia = calificacionesGuia;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<OpinionParticipanteDTO> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<OpinionParticipanteDTO> opiniones) {
        this.opiniones = opiniones;
    }

}
