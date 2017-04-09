/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Vega
 */
@XmlRootElement
public class CaminanteDTO extends UsuarioDTO
{
     /**
     * Atributo que representa las condiciones fisicas del caminante. 
     * Se consideran 5 condiciones basicas y a cada una se asigna un valor en el rango (0 <= i <= 10). 
     * Este valor representa el nivel de rendimiento estimado del caminante.
     * A continuación se muestra la convencion seguida para almacenar los valores:
     * condicionesFisicas(0) --> Fuerza
     * condicionesFisicas(1) --> Velocidad
     * condicionesFisicas(2) --> Resistencia
     * condicionesFisicas(3) --> Flexibilidad
     * condicionesFisicas(4) --> Coordinacion
     */
    private List<Integer> condicionesFisicas;
    
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
            this.imagen = entity.getImagen();
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
        caminante.setImagen(this.getImagen());
        return caminante;
    }

    public List<Integer> getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(List<Integer> condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }
    
}
