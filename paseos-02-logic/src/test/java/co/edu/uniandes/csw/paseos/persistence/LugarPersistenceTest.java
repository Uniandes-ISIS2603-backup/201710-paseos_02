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

import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.persistence.LugarPersistence;
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
 * @author ac.lopez
 */
@RunWith(Arquillian.class)
public class LugarPersistenceTest
{       
    /**
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }    
    
    @Inject
    private LugarPersistence lugarPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<LugarEntity> data = new ArrayList<LugarEntity>();
    
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
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
           LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPaseoTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
       LugarEntity entityParaPrueba = factory.manufacturePojo(LugarEntity.class);
        
        LugarEntity entityPersistido = lugarPersistence.create(entityParaPrueba);
        
        Assert.assertNotNull("No deberia retornar null al persistir un Lugar", entityPersistido);
        
        LugarEntity entityEncontrado = em.find(LugarEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El Lugar deberia existir en la base de datos",entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
    }
    
    @Test
    public void getPaseosTest( )
    {
        List<LugarEntity> encontrados = lugarPersistence.findAll();
        Assert.assertEquals(data.size(), encontrados.size());
        boolean found;
        for(LugarEntity encontrado : encontrados)
        {
            found = false;
            for(LugarEntity esperado : data)
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
        LugarEntity esperado = data.get(0);
        LugarEntity encontrado = lugarPersistence.find(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }
    
    //Escenario 2: No existe un paseo con el id dado 
    @Test
    public void getPaseoTest2( )
    {
        LugarEntity respuesta = lugarPersistence.find(Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }
    
    
    //Escenario 1: Se va a actualizar un paseo que existe en la base de datos.
    @Test 
    public void updatePaseoTest1( )
    {
       LugarEntity original = data.get(0);
        
        PodamFactory podam = new PodamFactoryImpl();        
        LugarEntity actualizado = podam.manufacturePojo(LugarEntity.class);
        actualizado.setId(original.getId());
        
        LugarEntity mergeResult = lugarPersistence.update(actualizado);
        Assert.assertNotNull(mergeResult);
        
        LugarEntity encontrada = em.find(LugarEntity.class, original.getId());
        Assert.assertNotNull("El paseo se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizado, encontrada);
    }
    @Test
    public void deletePaseoTest( )
    {
       LugarEntity entity = data.get(0);
        lugarPersistence.delete(entity.getId());
        LugarEntity eliminado = em.find(LugarEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
    
    
    private void verificarConsistenciaAtributos(LugarEntity p1, LugarEntity p2)
    {
        Assert.assertEquals(p1.getId(), p2.getId());
        Assert.assertEquals(p1.getNombre(), p2.getNombre());
        Assert.assertEquals(p1.getDireccion(), p2.getDireccion());
        Assert.assertEquals(p1.getLinkGoogleMaps(), p2.getLinkGoogleMaps());
        Assert.assertEquals(p1.getImagen(), p2.getImagen());        
    }
    
    
  
    
}