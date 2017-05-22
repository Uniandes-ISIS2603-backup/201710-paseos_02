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
package co.edu.uniandes.csw.paseos.persistence;

import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
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
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.vega11
 */
@RunWith(Arquillian.class)
public class PaseoEcologicoPersistenceTest
{       
    /**
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaseoEcologicoEntity.class.getPackage())
                .addPackage(PaseoEcologicoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }    
    
    @Inject
    private PaseoEcologicoPersistence paseoPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<PaseoEcologicoEntity> data = new ArrayList<PaseoEcologicoEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
     */
    private void clearData() {
        em.createQuery("delete from PaseoEcologicoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PaseoEcologicoEntity entity = factory.manufacturePojo(PaseoEcologicoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPaseoTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
        PaseoEcologicoEntity entityParaPrueba = factory.manufacturePojo(PaseoEcologicoEntity.class);
        
        PaseoEcologicoEntity entityPersistido = paseoPersistence.create(entityParaPrueba);
        
        Assert.assertNotNull("No deberia retornar null al persistir un paseo", entityPersistido);
        
        PaseoEcologicoEntity entityEncontrado = em.find(PaseoEcologicoEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El paseo deberia existir en la base de datos",entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
    }
    
    @Test
    public void getPaseosTest( )
    {
        List<PaseoEcologicoEntity> encontrados = paseoPersistence.findAll();
        Assert.assertEquals(data.size(), encontrados.size());
        boolean found;
        for(PaseoEcologicoEntity encontrado : encontrados)
        {
            found = false;
            for(PaseoEcologicoEntity esperado : data)
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
    
    //Escenario 1: El paseo buscado existe en la base de datos
    @Test
    public void getPaseoTest1( )
    {
        PaseoEcologicoEntity esperado = data.get(0);
        PaseoEcologicoEntity encontrado = paseoPersistence.find(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }
    
    //Escenario 2: No existe un paseo con el id dado 
    @Test
    public void getPaseoTest2( )
    {
        PaseoEcologicoEntity respuesta = paseoPersistence.find(Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }
    
    
    //Escenario 1: Se va a actualizar un paseo que existe en la base de datos.
    @Test 
    public void updatePaseoTest1( )
    {
        PaseoEcologicoEntity original = data.get(0);
        
        PodamFactory podam = new PodamFactoryImpl();        
        PaseoEcologicoEntity actualizado = podam.manufacturePojo(PaseoEcologicoEntity.class);
        actualizado.setId(original.getId());
        
        PaseoEcologicoEntity mergeResult = paseoPersistence.update(actualizado);
        Assert.assertNotNull(mergeResult);
        
        PaseoEcologicoEntity encontrada = em.find(PaseoEcologicoEntity.class, original.getId());
        Assert.assertNotNull("El paseo se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizado, encontrada);
    }
    
    //Escenario 2: Se va a actualizar un paseo que no existe en la base de datos.
    @Test 
    public void updatePaseoTest2( )
    {      
        PodamFactory podam = new PodamFactoryImpl();        
        PaseoEcologicoEntity newEntity = podam.manufacturePojo(PaseoEcologicoEntity.class);
        newEntity.setId(Long.MAX_VALUE);
        
        PaseoEcologicoEntity mergeResult = paseoPersistence.update(newEntity);
        
        //Como no hay un paseo con el mismo id en la BD, al hacer merge el newEntity se debe persistir.
       //No obstante, el newEntity no se persiste con el id que se puso manualmente, puesto que se definio que los id's son generados por la BD.
       //Vale la pena aclarar que el newEntity no se sincroniza con la BD, por tanto su id no es el generado automaticamente.
       
       
       //Por lo anterior, si se busca un paseo con el id que se puso manualmente, no se encuentran resultados.
        PaseoEcologicoEntity rtaIdOriginal = em.find(PaseoEcologicoEntity.class, newEntity.getId());
        Assert.assertNull(rtaIdOriginal);
        
        //Sin embargo, al buscar con el id que genero la base de datos, se encuentra el paseo efectivamente persistido.
        PaseoEcologicoEntity rtaIdGenerado = em.find(PaseoEcologicoEntity.class, mergeResult.getId());
        Assert.assertNotNull("Debio persistirse el paseo en la base de datos", rtaIdGenerado);
        verificarConsistenciaAtributos(newEntity, rtaIdGenerado);
    }
    
    @Test
    public void deletePaseoTest( )
    {
        PaseoEcologicoEntity entity = data.get(0);
        paseoPersistence.delete(entity.getId());
        PaseoEcologicoEntity eliminado = em.find(PaseoEcologicoEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }    
    
    private void verificarConsistenciaAtributos(PaseoEcologicoEntity p1, PaseoEcologicoEntity p2)
    {
        Assert.assertEquals(p1.getDescripcion(), p2.getDescripcion());
        Assert.assertEquals(p1.getCosto(), p2.getCosto());
        Assert.assertEquals(p1.getHayTransporte(), p2.getHayTransporte());
        Assert.assertEquals(p1.getTematica(), p2.getTematica());
        Assert.assertEquals(p1.getnMaxCaminantes(), p2.getnMaxCaminantes());
        Assert.assertEquals(p1.getnMinimCaminantes(), p2.getnMinimCaminantes());
        
        List<Integer> condFisicasOriginal = p1.getCondicionesFisicas();
        List<Integer> condfisicasEncontrado = p2.getCondicionesFisicas();
        
        for(Integer condFisica : condfisicasEncontrado)
        {
            Assert.assertTrue(condFisicasOriginal.contains(condFisica));
        }       
    }
    
    
  
    
}
