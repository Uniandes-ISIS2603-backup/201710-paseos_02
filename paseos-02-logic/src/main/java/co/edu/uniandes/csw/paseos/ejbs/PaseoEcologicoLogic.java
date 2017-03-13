/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.persistence.PaseoEcologicoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class PaseoEcologicoLogic 
{
   @Inject private PaseoEcologicoPersistence persistencia;
      
   public List<PaseoEcologicoEntity> getPaseos( )
   {
       return persistencia.findAll();
   }
   
   public PaseoEcologicoEntity getPaseo(Long id)
   {
       return persistencia.find(id);
   }
   
   public PaseoEcologicoEntity createPaseo(PaseoEcologicoEntity paseo)
   {
       persistencia.create(paseo);
       return paseo;
   }
   
   public PaseoEcologicoEntity updatePaseo(PaseoEcologicoEntity paseo)
   {
       return persistencia.update(paseo);
   }
   
   public void deletePaseo(Long id)
   {
       persistencia.delete(id);
   }
    
}
