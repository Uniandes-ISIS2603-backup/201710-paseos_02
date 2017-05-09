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

import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
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
 * @author js.millan10
 */
@RunWith(Arquillian.class)

public class CalificacionPersistenceTest {
    
     /**
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }    
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    private GuiaEntity guiaActual;
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
            PodamFactory factory = new PodamFactoryImpl();
            guiaActual = factory.manufacturePojo(GuiaEntity.class);
            guiaActual.setId((long)10);
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createCalificacionTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity entityParaPrueba = factory.manufacturePojo(CalificacionEntity.class);
        
        CalificacionEntity entityPersistido = calificacionPersistence.create(entityParaPrueba);
        
        Assert.assertNotNull("No deberia retornar null al persistir un caminante", entityPersistido);
        
        //CalificacionEntity entityEncontrado = em.find(CalificacionEntity.class, entityPersistido.getId());
        //Assert.assertNotNull("El caminante deberia existir en la base de datos",entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos
    }
    
    @Test
    public void getCalificacionesTest( )
    {
        List<CalificacionEntity> encontrados = calificacionPersistence.findAll(guiaActual.getId());
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
    public void getCalificacionesTestId( )
    {
        CaminanteEntity esperado = data.get(0);
        CaminanteEntity encontrado = caminantePersistence.find(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }
    
    
    //Escenario 1: Se va a actualizar un caminante que existe en la base de datos.
    @Test 
    public void updateCalificacionTest( )
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
    
    
    @Test
    public void deleteCaminanteTest( )
    {
        CaminanteEntity entity = data.get(0);
        caminantePersistence.delete(entity.getId());
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
    
}
