/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Juan David Vega
 */

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class CaminanteEntity extends UsuarioEntity
{
    private List<String> condicionesFisicas;        

    public List<String> getCondicionesFisicas() {
        return condicionesFisicas;
    }

    public void setCondicionesFisicas(List<String> condicionesFisicas) {
        this.condicionesFisicas = condicionesFisicas;
    }
    
}
