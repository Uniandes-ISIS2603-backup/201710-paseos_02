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

import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
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
public class CaminantePersistenceTest 
{
    /**
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CaminanteEntity.class.getPackage())
                .addPackage(CaminantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }    
    
    @Inject
    private CaminantePersistence caminantePersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<CaminanteEntity> data = new ArrayList<CaminanteEntity>();
    
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
        em.createQuery("delete from CaminanteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 4; i++) {
            CaminanteEntity entity = factory.manufacturePojo(CaminanteEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCaminanteTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
        CaminanteEntity entityParaPrueba = factory.manufacturePojo(CaminanteEntity.class);
        
        CaminanteEntity entityPersistido = caminantePersistence.create(entityParaPrueba);
        
        Assert.assertNotNull("No deberia retornar null al persistir un caminante", entityPersistido);
        
        CaminanteEntity entityEncontrado = em.find(CaminanteEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El caminante deberia existir en la base de datos",entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
    }
    
    @Test
    public void getCaminantesTest( )
    {
        List<CaminanteEntity> encontrados = caminantePersistence.findAll();
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
        CaminanteEntity encontrado = caminantePersistence.find(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }
    
    //Escenario 2: No existe un caminante con el id dado 
    @Test
    public void getCaminanteTest2( )
    {
        CaminanteEntity respuesta = caminantePersistence.find(Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }
    
    //Escenario 1: Se va a actualizar un caminante que existe en la base de datos.
    @Test 
    public void updateCaminanteTest1( )
    {
        CaminanteEntity original = data.get(0);
        
        PodamFactory podam = new PodamFactoryImpl();        
        CaminanteEntity actualizada = podam.manufacturePojo(CaminanteEntity.class);
        actualizada.setId(original.getId());
        
        CaminanteEntity mergeResult = caminantePersistence.update(actualizada);
        Assert.assertNotNull(mergeResult);
        
        CaminanteEntity encontrada = em.find(CaminanteEntity.class, original.getId());
        Assert.assertNotNull("El caminante se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizada, encontrada);
    }
    
    //Escenario 2: Se va a actualizar un caminante que no existe en la base de datos.
    @Test 
    public void updateCaminanteTest2( )
    {      
        PodamFactory podam = new PodamFactoryImpl();        
        CaminanteEntity newEntity = podam.manufacturePojo(CaminanteEntity.class);
        newEntity.setId(Long.MAX_VALUE);
        
        CaminanteEntity mergeResult = caminantePersistence.update(newEntity);
        
        //Como no hay un caminante con el mismo id en la BD, al hacer merge el newEntity se debe persistir.
       //No obstante, el newEntity no se persiste con el id que se puso manualmente, puesto que se definio que los id's son generados por la BD.
       //Vale la pena aclarar que el newEntity no se sincroniza con la BD, por tanto su id no es el generado automaticamente.
       
       
       //Por lo anterior, si se busca un caminante con el id que se puso manualmente, no se encuentran resultados.
        CaminanteEntity rtaIdOriginal = em.find(CaminanteEntity.class, newEntity.getId());
        Assert.assertNull(rtaIdOriginal);
        
        //Sin embargo, al buscar con el id que genero la base de datos, se encuentra el caminante efectivamente persistido.
        CaminanteEntity rtaIdGenerado = em.find(CaminanteEntity.class, mergeResult.getId());
        Assert.assertNotNull("Debio persistirse el caminante en la base de datos", rtaIdGenerado);
        verificarConsistenciaAtributos(newEntity, rtaIdGenerado);
    }
    
    @Test
    public void deleteCaminanteTest( )
    {
        CaminanteEntity entity = data.get(0);
        caminantePersistence.delete(entity.getId());
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, entity.getId());
        Assert.assertNull(eliminado);
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
        Assert.assertEquals(c1.getImagen(), c2.getImagen()); 
       
        List<Integer> condFisicasOriginal = c1.getCondicionesFisicas();
        List<Integer> condfisicasEncontrado = c2.getCondicionesFisicas();
        
        for(Integer condFisica : condfisicasEncontrado)
        {
            Assert.assertTrue(condFisicasOriginal.contains(condFisica));
        }       
    }
   
}
