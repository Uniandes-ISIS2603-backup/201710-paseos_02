/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.InscripcionPersistence;
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
   
   @Inject private InscripcionPersistence inscripcionPersistencia;

    /**
     * Obtiene la lista de los guias.
     *
     * @return colección de objetos de PaseoEcologicoEntity.
     */
   public List<PaseoEcologicoEntity> getPaseos( )
   {
       return persistencia.findAll();
   }

    /**
     * Obtiene una instancia de la clase PaseoEcologicoEntity, a partir de un id.
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase PaseoEcologicoEntity, que se desea obtener.
     */
   public PaseoEcologicoEntity getPaseo(Long id)
   {
       return persistencia.find(id);
   }

    /**
     * Crea en la persistencia una nueva instancia de la clase PaseoEcologicoEntity.
     * @param paseo instancia de la calse PaseoEcologicoEntity que se desea crear
     * @return La instancia creada.
     */
   public PaseoEcologicoEntity createPaseo(PaseoEcologicoEntity paseo) throws BusinessLogicException
   {
       verificarDatos(paseo);
       persistencia.create(paseo);
       return paseo;
   }

    /**
     * Actualizar información de una instancia PaseoEcologicoEntity dada
     * @param paseo instancia de la clase PaseoEcologicoEntity que se desea actualizar.
     * @return Instancia de la clase PaseoEcologicoEntity con la información actualizada.
     */
   public PaseoEcologicoEntity updatePaseo(PaseoEcologicoEntity paseo)
   { //TODO debe haber algo para verificar cuando se actualiza la info de un paseo
       return persistencia.update(paseo);
   }

    /**
     * Elimina una instancia de la clase PaseoEcologicoEnity dada por su id.
     * @param id de la instancia que se quiere eliminar.
     */
   public void deletePaseo(Long id) throws BusinessLogicException
   {
       if(inscripcionPersistencia.inscripcionesPorPaseo(id).isEmpty())
       {
           persistencia.delete(id);
       }
       else
       {
           throw new BusinessLogicException("No puede eliminar un paseo que tenga camiantes inscritos");
       }
       
   }
   
   public List<PaseoEcologicoEntity> darPaseosSegunTematica( String tematica )
   {
       return persistencia.darPaseosSegunTematica(tematica);
   }
   
   public List<PaseoEcologicoEntity> darPaseosSegunCosto( Double costo )
   {
       return persistencia.darPaseosSegunCosto(costo);
   }
   
   public List<PaseoEcologicoEntity> darPaseosSegunLugarEncuentro( String nombre )
   {
       return persistencia.darPaseosSegunLugarDeEncuentro(nombre);
   }
   
   public List<PaseoEcologicoEntity> darPaseosSegunLugarDestino( String nombre )
   {
       return persistencia.darPaseosSegunLugarDeDestino(nombre);
   }

   private void verificarDatos(PaseoEcologicoEntity entity) throws BusinessLogicException 
   {
        if (entity.getTematica() == null || entity.getCosto() == null || entity.getGuia() == null || entity.getLugarDeEncuentro() == null || entity.getLugarDeDestino() == null)
        {
            throw new BusinessLogicException("Para crear un paseo, minimo debe tener tematica, costo, guia asociado, lugar de encuentro y lugar de destino. \n"
                    + "Verifique que dichos campos esten llenos y vuelva a intentar.");
        }
   }
}
