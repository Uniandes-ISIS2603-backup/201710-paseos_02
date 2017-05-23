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
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.GuiaPersistence;
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
 * @author mdr.leon10
 */
@RunWith(Arquillian.class)
public class GuiaLogicTest 
{
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private GuiaLogic guiaLogic;

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

  
    private List<GuiaEntity> data = new ArrayList<>();

    private  List<PaseoEcologicoEntity> paseosEcologicos;    
    
    private List<CalificacionEntity> calificaciones;

    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaLogic.class.getPackage())              
                .addPackage(GuiaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from PaseoEcologicoEntity").executeUpdate();       
        em.createQuery("delete from GuiaEntity").executeUpdate();        
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
            GuiaEntity entity = factory.manufacturePojo(GuiaEntity.class);
            entity.setCuentaActiva(Boolean.TRUE);           
            em.persist(entity);
            System.out.println(em.find(GuiaEntity.class, entity.getId()));
            entity.setPaseosEcologicos(new ArrayList<>());
            entity.setCalificaciones(new ArrayList<>());
            data.add(entity);
        }
        
        for(int i = 0; i < 4; i++)
        {
            PaseoEcologicoEntity entity = factory.manufacturePojo(PaseoEcologicoEntity.class);          
            entity.setGuia(data.get(i%3));            
            em.persist(entity);
            data.get(i%3).getPaseosEcologicos().add(entity);
            paseosEcologicos.add(entity);

        }
        
        for(int i = 0; i < 15; i++)
        {
            CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
            calificacion.setGuia(data.get(i%3));            
            em.persist(calificacion);
            data.get(i%3).getCalificaciones().add(calificacion);
            calificaciones.add(calificacion);
        }
              
    }
    
    @Test
    public void createGuiaTest( ) throws BusinessLogicException, Exception
    {       
        GuiaEntity entityParaPrueba = factory.manufacturePojo(GuiaEntity.class);
        GuiaEntity entityPersistido = guiaLogic.createGuia(entityParaPrueba);
        Assert.assertNotNull("No deberia retornar null al persistir un guia", entityPersistido);
        
        construirRelaciones(entityPersistido);
        
        GuiaEntity entityEncontrado = em.find(GuiaEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El guia deberia existir en la base de datos", entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos y que las relaciones sean coherentes
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
        verificarCoherenciaRelaciones(entityEncontrado, entityPersistido);
    }
    
     /**
     * Prueba para crear un caminante sin nombre
     */
    @Test(expected = BusinessLogicException.class)
    public void createGuiaTest2() throws Exception {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setNombre(null);
        guiaLogic.createGuia(newEntity);
    }
    
     /**
     * Prueba para crear un caminante sin identificacion
     */
    @Test(expected = BusinessLogicException.class)
    public void createGuiaTest3() throws Exception {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setIdentificacion(null);
        guiaLogic.createGuia(newEntity);
    }
    
     /**
     * Prueba para crear un caminante sin edad
     */
    @Test(expected = BusinessLogicException.class)
    public void createGuiaTest4() throws Exception {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        newEntity.setEdad(null);
        guiaLogic.createGuia(newEntity);
    }
    
     /**
     * Prueba para crear un caminante con una identificacion que ya existe
     */
    @Test(expected = BusinessLogicException.class)
    public void createGuiaTest5() throws BusinessLogicException {
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        int id = data.get(0).getIdentificacion();
        newEntity.setIdentificacion(id);
        //System.out.println("id data" + data.get(0).getIdentificacion()+ "," + "nuevo entity"+ newEntity.getIdentificacion());
        guiaLogic.createGuia(newEntity);
        System.out.println(em.find(GuiaEntity.class, newEntity.getId()));
    }
    
    @Test
    public void getGuiaTest( )
    {
        List<GuiaEntity> encontrados = guiaLogic.getGuias();
        Assert.assertEquals(data.size(), encontrados.size());
        boolean found;
        for(GuiaEntity encontrado : encontrados)
        {
            found = false; 
            for(GuiaEntity esperado : data)
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
    public void getGuiaTest1( )
    {
        GuiaEntity esperado = data.get(0);
        GuiaEntity encontrado = guiaLogic.getGuia(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
        verificarCoherenciaRelaciones(esperado, encontrado);
    }
    
    //Escenario 2: No existe un caminante con el id dado 
    @Test
    public void getGuiaTest2( )
    {
        GuiaEntity respuesta = guiaLogic.getGuia(Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }    
   
    @Test 
    public void updateGuiaTest( ) throws BusinessLogicException
    {
        GuiaEntity original = data.get(0);        
            
        GuiaEntity actualizada = factory.manufacturePojo(GuiaEntity.class);
        actualizada.setId(original.getId());
        
        GuiaEntity mergeResult = guiaLogic.updateGuia(actualizada);
        Assert.assertNotNull(mergeResult);
        
        GuiaEntity encontrada = em.find(GuiaEntity.class, original.getId());
        Assert.assertNotNull("El guia se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizada, encontrada);
    }
    
     /**
     * Prueba para actualizar un caminante sin nombre
     */
    @Test(expected = BusinessLogicException.class)
    public void updateGuiaTest2() throws Exception
    {
        GuiaEntity original = data.get(1);
        GuiaEntity actualizada = factory.manufacturePojo(GuiaEntity.class);
        actualizada.setId(original.getId());
        actualizada.setNombre(null);
        guiaLogic.updateGuia(actualizada);
    }
    
    /**
     * Prueba para actualizar un caminante sin identificacion
     */
    @Test(expected = BusinessLogicException.class)
    public void updateGuiaTest3() throws Exception
    {
        GuiaEntity original = data.get(0);
        GuiaEntity actualizada = factory.manufacturePojo(GuiaEntity.class);
        actualizada.setId(original.getId());
        actualizada.setIdentificacion(null);
        actualizada.setTipoIdentificacion(null);
        guiaLogic.updateGuia(actualizada);
    }
    
    /**
     * Prueba para actualizar un caminante sin edad
     */
    @Test(expected = BusinessLogicException.class)
    public void updateGuiaTest4() throws Exception
    {
        GuiaEntity original = data.get(0);
        GuiaEntity actualizada = factory.manufacturePojo(GuiaEntity.class);
        actualizada.setId(original.getId());
        actualizada.setEdad(null);
        guiaLogic.updateGuia(actualizada);
    }
    
    /**
     * Prueba para actualizar un caminante cambiando su id por otro ya existente.
     */
    @Test(expected = BusinessLogicException.class)
    public void updateGuiaTest5() throws Exception
    {
        GuiaEntity original = data.get(0);
        GuiaEntity actualizada = factory.manufacturePojo(GuiaEntity.class);
        actualizada.setId(original.getId());
        actualizada.setIdentificacion(data.get(1).getIdentificacion());
        guiaLogic.updateGuia(actualizada);
    }
    
    /**
     * Prueba para borrar un caminante que no tiene relaciones con otras clases.
     */
    @Test
    public void deleteGuiaTest( ) throws BusinessLogicException
    {   
        GuiaEntity entity = data.get(3);
        guiaLogic.deleteGuia(entity.getId());
        GuiaEntity eliminado = em.find(GuiaEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
    
     /**
     * Prueba para borrar un caminante que tiene relaciones con otras clases.
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteGuiaTest2( ) throws Exception
    {   
        GuiaEntity entity = data.get(0);
        guiaLogic.deleteGuia(entity.getId());
    }
    
    @Test
    public void desactivarCuentaGuiaTest( ) throws BusinessLogicException
    {
        GuiaEntity entity = data.get(0);
        //System.out.println("esta soy yo jajaja" + em.find(GuiaEntity.class, entity.getId())+ "," + entity.getId());
        entity.setCuentaActiva(Boolean.FALSE);
        guiaLogic.updateGuia(entity);
        GuiaEntity desactivado = em.find(GuiaEntity.class, entity.getId());
        //System.out.println(desactivado.getId());
        Assert.assertFalse(desactivado.getCuentaActiva());
    }
   
    
    /**
     * Crea las relaciones del caminante con las entidades correspondientes.
     */
    private void construirRelaciones(GuiaEntity entity)
    {
        try {
            utx.begin();
            
            List<PaseoEcologicoEntity> paseos = new ArrayList<>();            
            for (int i = 0; i < 3; i++) {
                PaseoEcologicoEntity paseo = factory.manufacturePojo(PaseoEcologicoEntity.class);
                paseo.setGuia(entity);
                em.persist(paseo);
                paseos.add(paseo);
            }
            entity.setPaseosEcologicos(paseos);

             List<CalificacionEntity> calificaciones = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
                calificacion.setGuia(entity);
                em.persist(calificacion);
                calificaciones.add(calificacion);
            }
            entity.setCalificaciones(calificaciones);

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
    
    private void verificarConsistenciaAtributos(GuiaEntity c1, GuiaEntity c2)
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
        Assert.assertEquals(c1.getExperiencia(), c2.getExperiencia());
        Assert.assertEquals(c1.getFormacion(), c2.getFormacion());  
    }
    
    private void verificarCoherenciaRelaciones(GuiaEntity c1, GuiaEntity c2)
    {
        Assert.assertNotNull(c1.getCalificaciones());
        Assert.assertNotNull(c2.getCalificaciones());       
        Assert.assertEquals(c2.getCalificaciones().size(), c1.getCalificaciones().size());
        
        for(CalificacionEntity cal : c2.getCalificaciones())
        {
            Assert.assertTrue(c1.getCalificaciones().contains(cal));
        }
        
        Assert.assertNotNull(c1.getPaseosEcologicos());
        Assert.assertNotNull(c2.getPaseosEcologicos());
        Assert.assertEquals(c2.getPaseosEcologicos().size(), c1.getPaseosEcologicos().size());
        
        for(PaseoEcologicoEntity pas : c2.getPaseosEcologicos())
        {
            Assert.assertTrue(c1.getPaseosEcologicos().contains(pas));
        }
    }
    

    
   
}
