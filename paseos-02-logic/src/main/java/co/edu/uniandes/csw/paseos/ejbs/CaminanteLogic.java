/* 
 * The MIT License
 *
 * Copyright 2017 Treamwork - Team software development - Los Andes University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.paseos.ejbs;

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.CaminantePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan David Vega
 */
@Stateless
public class CaminanteLogic {

    @Inject
    private CaminantePersistence caminantePersistence;

    /**
     * Obtiene la lista de los caminates.
     *
     * @return colección de objetos de CaminanteEntity.
     */
    public List<CaminanteEntity> getCaminantes()
    {
        return caminantePersistence.findAll();
    }

    /**
     * Obtiene una instancia de la clase CaminanteaEntity, a partir de un id.
     * @param id identificador de la instancia que se desea obtener.
     * @return instancia de la clase CaminanteEntity, que se desea obtener.
     */
    public CaminanteEntity getCaminante(Long id) 
    {
        return caminantePersistence.find(id);
    }

    /**
     * Crea en la persistencia una nueva instancia de la clase CaminanteEntity.
     * @param caminante instancia de la calse caminanteEntity que se desea crear
     * @return La instancia creada.
     * @throws BusinessLogicException si ya existe otro usuario con la misma identificacion
     */
    public CaminanteEntity createCaminante(CaminanteEntity caminante) throws BusinessLogicException 
    {
        //Al crear un caminante se establece que su cuenta está activa.
        caminante.setCuentaActiva(Boolean.TRUE);
        
        verificarDatos(caminante);
        if(!existeCaminanteConMismaIdentificacion(caminante.getIdentificacion()))
        {
             return caminantePersistence.create(caminante);
        }
        else
        {
            throw new BusinessLogicException("Ya existe otro usuario con la misma identificacion");
        }
       
    }

    /**
     * Actualizar información de una instancia CaminanteEntity dada
     * @param caminante instancia de la clase GuiaEntity que se desea actualizar.
     * @return Instancia de la clase CaminanteEntity con la información actualizada.
     * @throws BusinessLogicException si existe otro usuario con la misma identificacion
     */
    public CaminanteEntity updateCaminante(CaminanteEntity caminante) throws BusinessLogicException
    {
        verificarDatos(caminante);        
        CaminanteEntity original = caminantePersistence.find(caminante.getId());
        
        //Existen dos opciones: El caminante conserva la identificacion que ya estaba persistida o
        // el caminante cambia su identificacion.
        //Si cambio su identificacion, hay que verificar que la nueva no sea igual a la de otro caminante.
        boolean mismoIdentViejo = original.getIdentificacion().equals(caminante.getIdentificacion());        
        if(!existeCaminanteConMismaIdentificacion(caminante.getIdentificacion()) || mismoIdentViejo)
        {
            return caminantePersistence.update(caminante);
        }
        else
        {
            throw new BusinessLogicException("Ya existe otro usuario con la misma identificacion");
        }         
    }
    
     /**
     * Desactiva la cuenta de un caminante marcando como false su atributo cuentaActiva.
     * @param id id del caminante cuya cuenta sera desactivada.
     * @return Caminante cuya cuenta se desactivo
     */
    public CaminanteEntity desactivarCuenta(Long id)
    {
        CaminanteEntity entity = caminantePersistence.find(id);
        entity.setCuentaActiva(Boolean.FALSE);
        return caminantePersistence.update(entity);
    }

    /**
     * Elimina una instancia de la clase CaminanteEnity dada por su id.
     * @param id id de la instancia que se quiere eliminar.
     */
    public void deleteCaminante(Long id) throws BusinessLogicException 
    {
        CaminanteEntity entity = caminantePersistence.find(id);
        verificarBorrado(entity);
        caminantePersistence.delete(id);        
    }
    
    public boolean existeCaminanteConMismaIdentificacion(Integer identificacion)
    {
        return caminantePersistence.encontrarPorIdentificacion(identificacion) != null;
    }
    
   private void verificarDatos(CaminanteEntity entity) throws BusinessLogicException 
   {
       boolean nombre = entity.getNombre() == null;
       boolean identificacion = entity.getIdentificacion() == null;
       boolean tipoIden = entity.getTipoIdentificacion() == null;
       boolean edad = entity.getEdad() == null;
           if (nombre || identificacion || tipoIden || edad )
       {
           throw new BusinessLogicException("Para registrar un caminante, minimo debe tener nombre, identificacion, tipoIdentificacion y edad. \n"
                   + "Verifique que dichos campos esten llenos y vuelva a intentar.");
       }
   }
   
   private void verificarBorrado(CaminanteEntity entity) throws BusinessLogicException
   {
       boolean noTieneOpiniones = entity.getOpiniones().isEmpty();
       boolean noTieneInscripciones = entity.getPaseosInscritos().isEmpty();
       boolean noTieneCalificacionesGuia = entity.getCalificacionesGuia().isEmpty();
      
       if (!noTieneOpiniones || !noTieneCalificacionesGuia || !noTieneInscripciones) 
       {
           throw new BusinessLogicException("No puede eliminar el caminante puesto que tiene inscripciones asociadas, \n"
                   + "opiniones registradas o calificaciones hechas.");
       }
   }

}
