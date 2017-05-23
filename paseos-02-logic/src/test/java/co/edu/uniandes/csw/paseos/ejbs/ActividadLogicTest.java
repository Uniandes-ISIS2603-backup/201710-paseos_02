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

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.ActividadPersistence;
import co.edu.uniandes.csw.paseos.persistence.GuiaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author d.narvaez11
 */
public class ActividadLogicTest
{
     /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private ActividadLogic actividadLogic;

    
    private PaseoEcologicoEntity paseoEcologico;
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

  
    private List<ActividadEntity> data = new ArrayList<>();  


    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(ActividadLogic.class.getPackage())              
                .addPackage(ActividadPersistence.class.getPackage())
                .addPackage(PaseoEcologicoEntity.class.getPackage())
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
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from PaseoEcologicoEntity").executeUpdate();      
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() 
    {
          PodamFactory factory = new PodamFactoryImpl();
          paseoEcologico = factory.manufacturePojo(PaseoEcologicoEntity.class);
            paseoEcologico.setId((long)10);
            em.persist(paseoEcologico);
        for(int i = 0; i < 4; i++)
        {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
            entity.setPaseoEcologico( paseoEcologico);
            em.persist(entity);
            System.out.println(em.find(GuiaEntity.class, entity.getId()).getId());
            data.add(entity);
        }
        
    }
    
    @Test
    public void createActividadTest( ) throws BusinessLogicException, Exception
    {       
        ActividadEntity entityParaPrueba = factory.manufacturePojo(ActividadEntity.class);
        ActividadEntity entityPersistido = actividadLogic.createActividad(entityParaPrueba);
        Assert.assertNotNull("No deberia retornar null al persistir una actividad", entityPersistido);
       
        
        ActividadEntity entityEncontrado = em.find(ActividadEntity.class, entityPersistido.getId());
        Assert.assertNotNull("La actividad deberia existir en la base de datos", entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos y que las relaciones sean coherentes
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createActividadTest1( ) throws BusinessLogicException, Exception
    {    
        
        ActividadEntity entityParaPrueba = factory.manufacturePojo(ActividadEntity.class);
        entityParaPrueba.setDuracion( -1);
        actividadLogic.createActividad( entityParaPrueba );
        ActividadEntity entityPersistido = actividadLogic.createActividad(entityParaPrueba);
        Assert.assertNotNull("No deberia retornar null al persistir una actividad", entityPersistido);
       
        
        ActividadEntity entityEncontrado = em.find(ActividadEntity.class, entityPersistido.getId());
        Assert.assertNotNull("La actividad deberia existir en la base de datos", entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos y que las relaciones sean coherentes
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
        
       
    }
    
   
    @Test
    public void getActividadTest( )
    {
        List<ActividadEntity> encontrados = actividadLogic.getActividades(paseoEcologico.getId());
        Assert.assertEquals(data.size(), encontrados.size());
        boolean found;
        for(ActividadEntity encontrado : encontrados)
        {
            found = false; 
            for(ActividadEntity esperado : data)
            {
                if(encontrado.getId().equals(esperado.getId()))
                {
                    verificarConsistenciaAtributos(esperado, encontrado); 
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }        
    }
    
    //Escenario 1: El caminante buscado existe en la base de datos
    @Test
    public void getActividadTest1( )
    {
        ActividadEntity esperado = data.get(0);
        ActividadEntity encontrado = actividadLogic.getActividad(paseoEcologico.getId(),esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }
    
    //Escenario 2: No existe un caminante con el id dado 
    @Test
    public void getActividadTest2( )
    {
        ActividadEntity respuesta = actividadLogic.getActividad(paseoEcologico.getId(), Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }    

    @Test
    public void deleteActividadTest() {
        ActividadEntity entity = data.get(1);
        actividadLogic.deleteActividad(entity.getId());
        InscripcionEntity deleted = em.find(InscripcionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateActividadTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity pojoEntity = factory.manufacturePojo(ActividadEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setPaseoEcologico(paseoEcologico);

        try {
            actividadLogic.updateActividad(pojoEntity);

            ActividadEntity resp = em.find(ActividadEntity.class, entity.getId());
            verificarConsistenciaAtributos( pojoEntity, resp);
        } catch (Exception e) {
            Assert.fail();
        }
    }
  
    private void verificarConsistenciaAtributos(ActividadEntity p1, ActividadEntity p2)
    {
        
        Assert.assertEquals(p1.getNombre(), p2.getNombre());
        Assert.assertEquals(p1.getDuracion(), p2.getDuracion());
        Assert.assertEquals(p1.getDescripcion(), p2.getDescripcion());   
        
        List<String> list1 = p1.getEquipamiento();
        List<String> list2 = p2.getEquipamiento();
        for(String s:list1)
        {
            Assert.assertTrue(list2.contains(s));
        }
    }
    
 

    
}
