/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class CaminanteDTO extends UsuarioDTO
{
    private String condicionesFisicas;
    
    public CaminanteDTO( )
    {
        
    }
    
    public CaminanteDTO(CaminanteEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.identificacion = entity.getIdentificacion();
            this.tipoIdentificacion = entity.getTipoIdentificacion();
            this.edad = entity.getEdad();
            this.telefono = entity.getTelefono();
            this.direccion = entity.getDireccion();
            this.correoElectronico = entity.getCorreoElectronico();
            this.condicionesFisicas = entity.getCondicionesFisicas();
        }
    }
    public CaminanteEntity toEntity( )
    {
        CaminanteEntity caminante = new CaminanteEntity();
        caminante.setId(this.getId());
        caminante.setNombre(this.getNombre());
        caminante.setIdentificacion(this.getIdentificacion());
        caminante.setTipoIdentificacion(this.getTipoIdentificacion());
        caminante.setEdad(this.getEdad());
        caminante.setTelefono(this.getTelefono());
        caminante.setDireccion(this.getDireccion());
        caminante.setCorreoElectronico(this.getCorreoElectronico());
        caminante.setCondicionesFisicas(this.getCondicionesFisicas());
        return caminante;
    }

    public String getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(String condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }
     
}
