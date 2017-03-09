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
 * @author jd.vega11
 */

@Stateless
public class PaseoEcologicoLogic 
{
   @Inject private PaseoEcologicoPersistence persistencia;
      
   public List<PaseoEcologicoEntity> getCursos( )
   {
       return persistencia.findAll();
   }
   
   public PaseoEcologicoEntity getCurso(Long id)
   {
       return persistencia.find(id);
   }
   
   public PaseoEcologicoEntity createCurso(PaseoEcologicoEntity curso)
   {
       persistencia.create(curso);
       return curso;
   }
   
   public PaseoEcologicoEntity updateCurso(PaseoEcologicoEntity curso)
   {
       return persistencia.update(curso);
   }
   
   public void deleteCurso(Long id)
   {
       persistencia.delete(id);
   }
}
