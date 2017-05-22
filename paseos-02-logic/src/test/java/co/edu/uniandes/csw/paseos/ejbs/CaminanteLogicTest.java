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

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.CaminantePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.vega11
 */
@RunWith(Arquillian.class)
public class CaminanteLogicTest 
{
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private CaminanteLogic caminanteLogic;

    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     */
    @Inject
    private UserTransaction utx;

  
    private List<CaminanteEntity> data = new ArrayList<>();

    private List<InscripcionEntity> inscripcionData = new ArrayList<>();    
    
    private List<CalificacionEntity> calificacionData = new ArrayList<>();    
    
    private List<OpinionParticipanteEntity> opinionData = new ArrayList<>();

    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CaminanteEntity.class.getPackage())
                .addPackage(CaminanteLogic.class.getPackage())              
                .addPackage(CaminantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * 
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * 
     */
    private void clearData() {
        em.createQuery("delete from InscripcionEntity").executeUpdate();        
        em.createQuery("delete from OpinionParticipanteEntity").executeUpdate();        
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from CaminanteEntity").executeUpdate();        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() 
    {
        for(int i = 0; i < 4; i++)
        {
            CaminanteEntity entity = factory.manufacturePojo(CaminanteEntity.class);
            entity.setCuentaActiva(Boolean.TRUE);           
            em.persist(entity);
            entity.setPaseosInscritos(new ArrayList<>());
            entity.setCalificacionesGuia(new ArrayList<>());
            entity.setOpiniones(new ArrayList<>());
            data.add(entity);
        }
        
        for(int i = 0; i < 15; i++)
        {
            InscripcionEntity inscripcion = factory.manufacturePojo(InscripcionEntity.class);
            inscripcion.setCaminante(data.get(i%3)); 
            data.get(i%3).getPaseosInscritos().add(inscripcion);
            em.persist(inscripcion);
            inscripcionData.add(inscripcion);
        }
        
        for(int i = 0; i < 15; i++)
        {
            CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
            calificacion.setCaminante(data.get(i%3));
            data.get(i%3).getCalificacionesGuia().add(calificacion);
            em.persist(calificacion);
            calificacionData.add(calificacion);
        }
        
        for(int i = 0; i < 15; i++)
        {
            OpinionParticipanteEntity opinion = factory.manufacturePojo(OpinionParticipanteEntity.class);
            opinion.setCaminante(data.get(i%3));
            data.get(i%3).getOpiniones().add(opinion);
            em.persist(opinion);
            opinionData.add(opinion);
        }       
    }
    
    @Test
    public void createCaminanteTest( ) throws BusinessLogicException
    {       
        CaminanteEntity entityParaPrueba = factory.manufacturePojo(CaminanteEntity.class);
        CaminanteEntity entityPersistido = caminanteLogic.createCaminante(entityParaPrueba);
        Assert.assertNotNull("No deberia retornar null al persistir un caminante", entityPersistido);
        
        construirRelaciones(entityPersistido);
        
        CaminanteEntity entityEncontrado = em.find(CaminanteEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El caminante deberia existir en la base de datos", entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos y que las relaciones sean coherentes
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
        verificarCoherenciaRelaciones(entityEncontrado, entityPersistido);
    }
    
     /**
     * Prueba para crear un caminante sin nombre
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest2() throws Exception {
        CaminanteEntity newEntity = factory.manufacturePojo(CaminanteEntity.class);
        newEntity.setNombre(null);
        caminanteLogic.createCaminante(newEntity);
    }
    
     /**
     * Prueba para crear un caminante sin identificacion
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest3() throws Exception {
        CaminanteEntity newEntity = factory.manufacturePojo(CaminanteEntity.class);
        newEntity.setIdentificacion(null);
        caminanteLogic.createCaminante(newEntity);
    }
    
     /**
     * Prueba para crear un caminante sin edad
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest4() throws Exception {
        CaminanteEntity newEntity = factory.manufacturePojo(CaminanteEntity.class);
        newEntity.setEdad(null);
        caminanteLogic.createCaminante(newEntity);
    }
    
     /**
     * Prueba para crear un caminante con una identificacion que ya existe
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest5() throws Exception {
        CaminanteEntity newEntity = factory.manufacturePojo(CaminanteEntity.class);         
        newEntity.setIdentificacion(data.get(0).getIdentificacion());
        caminanteLogic.createCaminante(newEntity);
    }
    
    @Test
    public void getCaminantesTest( )
    {
        List<CaminanteEntity> encontrados = caminanteLogic.getCaminantes();
        Assert.assertEquals(data.size(), encontrados.size());
        boolean found;
        for(CaminanteEntity encontrado : encontrados)
        {
            found = false;
            for(CaminanteEntity esperado : data)
            {
                if(encontrado.getId().equals(esperado.getId()))
                {
                    verificarConsistenciaAtributos(esperado, encontrado); 
                    verificarCoherenciaRelaciones(esperado, encontrado);
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }        
    }
    
    //Escenario 1: El caminante buscado existe en la base de datos
    @Test
    public void getCaminanteTest1( )
    {
        CaminanteEntity esperado = data.get(0);
        CaminanteEntity encontrado = caminanteLogic.getCaminante(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
        verificarCoherenciaRelaciones(esperado, encontrado);
    }
    
    //Escenario 2: No existe un caminante con el id dado 
    @Test
    public void getCaminanteTest2( )
    {
        CaminanteEntity respuesta = caminanteLogic.getCaminante(Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }    
   
    @Test 
    public void updateCaminanteTest( ) throws BusinessLogicException
    {
        CaminanteEntity original = data.get(0);        
            
        CaminanteEntity actualizada = factory.manufacturePojo(CaminanteEntity.class);
        actualizada.setId(original.getId());
        
        CaminanteEntity mergeResult = caminanteLogic.updateCaminante(actualizada);
        Assert.assertNotNull(mergeResult);
        
        CaminanteEntity encontrada = em.find(CaminanteEntity.class, original.getId());
        Assert.assertNotNull("El caminante se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizada, encontrada);
    }
    
     /**
     * Prueba para actualizar un caminante sin nombre
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCaminanteTest2() throws Exception
    {
        CaminanteEntity original = data.get(1);
        CaminanteEntity actualizada = factory.manufacturePojo(CaminanteEntity.class);
        actualizada.setId(original.getId());
        actualizada.setNombre(null);
        caminanteLogic.updateCaminante(actualizada);
    }
    
    /**
     * Prueba para actualizar un caminante sin identificacion
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCaminanteTest3() throws Exception
    {
        CaminanteEntity original = data.get(0);
        CaminanteEntity actualizada = factory.manufacturePojo(CaminanteEntity.class);
        actualizada.setId(original.getId());
        actualizada.setIdentificacion(null);
        actualizada.setTipoIdentificacion(null);
        caminanteLogic.updateCaminante(actualizada);
    }
    
    /**
     * Prueba para actualizar un caminante sin edad
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCaminanteTest4() throws Exception
    {
        CaminanteEntity original = data.get(0);
        CaminanteEntity actualizada = factory.manufacturePojo(CaminanteEntity.class);
        actualizada.setId(original.getId());
        actualizada.setEdad(null);
        caminanteLogic.updateCaminante(actualizada);
    }
    
    /**
     * Prueba para actualizar un caminante cambiando su id por otro ya existente.
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCaminanteTest5() throws Exception
    {
        CaminanteEntity original = data.get(0);
        CaminanteEntity actualizada = factory.manufacturePojo(CaminanteEntity.class);
        actualizada.setId(original.getId());
        actualizada.setIdentificacion(data.get(1).getIdentificacion());
        caminanteLogic.updateCaminante(actualizada);
    }
    
    /**
     * Prueba para borrar un caminante que no tiene relaciones con otras clases.
     */
    @Test
    public void deleteCaminanteTest( ) throws BusinessLogicException
    {   
        CaminanteEntity entity = data.get(3);
        caminanteLogic.deleteCaminante(entity.getId());
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
    
     /**
     * Prueba para borrar un caminante que tiene relaciones con otras clases.
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteCaminanteTest2( ) throws Exception
    {   
        CaminanteEntity entity = data.get(0);
        caminanteLogic.deleteCaminante(entity.getId());       
    }
    
    @Test
    public void desactivarCuentaCaminanteTest( )
    {
        CaminanteEntity entity = data.get(0);
        caminanteLogic.desactivarCuenta(entity.getId());
        CaminanteEntity desactivado = em.find(CaminanteEntity.class, entity.getId());
        Assert.assertFalse(desactivado.getCuentaActiva());        
    }
   
    
    /**
     * Crea las relaciones del caminante con las entidades correspondientes.
     */
    private void construirRelaciones(CaminanteEntity entity)
    {
        try {
            utx.begin();
            
            List<InscripcionEntity> inscripciones = new ArrayList<>();            
            for (int i = 0; i < 3; i++) {
                InscripcionEntity inscripcion = factory.manufacturePojo(InscripcionEntity.class);
                inscripcion.setCaminante(entity);
                em.persist(inscripcion);
                inscripciones.add(inscripcion);
            }
            entity.setPaseosInscritos(inscripciones);

             List<CalificacionEntity> calificaciones = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
                calificacion.setCaminante(entity);
                em.persist(calificacion);
                calificaciones.add(calificacion);
            }
            entity.setCalificacionesGuia(calificaciones);
            
            List<OpinionParticipanteEntity> opiniones = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                OpinionParticipanteEntity opinion = factory.manufacturePojo(OpinionParticipanteEntity.class);
                opinion.setCaminante(entity);
                em.persist(opinion);
                opiniones.add(opinion);
            }
            entity.setOpiniones(opiniones);

            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
    
    private void verificarConsistenciaAtributos(CaminanteEntity c1, CaminanteEntity c2)
    {
        Assert.assertEquals(c1.getNombre(), c2.getNombre());
        Assert.assertEquals(c1.getIdentificacion(), c2.getIdentificacion());
        Assert.assertEquals(c1.getTipoIdentificacion(), c2.getTipoIdentificacion());
        Assert.assertEquals(c1.getEdad(), c2.getEdad());
        Assert.assertEquals(c1.getDireccion(), c2.getDireccion());
        Assert.assertEquals(c1.getTelefono(), c2.getTelefono());
        Assert.assertEquals(c1.getCorreoElectronico(), c2.getCorreoElectronico());
        Assert.assertEquals(c1.getContrasenia(), c2.getContrasenia());
        Assert.assertEquals(c1.getImagen(), c2.getImagen()); 
       
        List<Integer> condFisicasOriginal = c1.getCondicionesFisicas();
        List<Integer> condfisicasEncontrado = c2.getCondicionesFisicas();
        
        for(Integer condFisica : condfisicasEncontrado)
        {
            Assert.assertTrue(condFisicasOriginal.contains(condFisica));
        }   
    }
    
    private void verificarCoherenciaRelaciones(CaminanteEntity c1, CaminanteEntity c2)
    {
        Assert.assertNotNull(c1.getPaseosInscritos());
        Assert.assertNotNull(c2.getPaseosInscritos());       
        Assert.assertEquals(c2.getPaseosInscritos().size(), c1.getPaseosInscritos().size());
        
        for(InscripcionEntity ins : c2.getPaseosInscritos())
        {
            Assert.assertTrue(c1.getPaseosInscritos().contains(ins));
        }
        
        Assert.assertNotNull(c1.getCalificacionesGuia());
        Assert.assertNotNull(c2.getCalificacionesGuia());
        Assert.assertEquals(c2.getCalificacionesGuia().size(), c1.getCalificacionesGuia().size());
        
        for(CalificacionEntity calif : c2.getCalificacionesGuia())
        {
            Assert.assertTrue(c1.getCalificacionesGuia().contains(calif));
        }
        
        Assert.assertNotNull(c1.getOpiniones());
        Assert.assertNotNull(c2.getOpiniones());
        Assert.assertEquals(c2.getOpiniones().size(), c1.getOpiniones().size());
        
        for(OpinionParticipanteEntity opin : c2.getOpiniones())
        {
            Assert.assertTrue(c1.getOpiniones().contains(opin));
        }
    }
    

    
   
}
