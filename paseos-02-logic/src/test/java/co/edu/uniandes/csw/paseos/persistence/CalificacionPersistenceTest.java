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
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
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
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
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
    
    @Inject
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
        em.createQuery("delete from GuiaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        GuiaEntity nuevo = factory.manufacturePojo(GuiaEntity.class);
            guiaActual = nuevo;
            em.persist(nuevo);
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class); 
            entity.setGuia(guiaActual);
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
        
        Assert.assertNotNull("No deberia retornar null al persistir una calificación", entityPersistido);
        
        CalificacionEntity entityEncontrado = em.find(CalificacionEntity.class, entityPersistido.getId());
        Assert.assertNotNull("La calificación deberia existir en la base de datos",entityEncontrado);
        
        
        //Se verifica que los valores persistidos sean correctos
    }
    
    @Test
    public void getCalificacionesTest( )
    {
        List<CalificacionEntity> encontrados = calificacionPersistence.findAll();
        Assert.assertEquals(data.size(), encontrados.size());
        
    }
    
    @Test
    public void getCalificacionesGuiaTest( )
    {
        List<CalificacionEntity> encontrados = calificacionPersistence.findAll(guiaActual.getId());
        Assert.assertEquals(data.size(), encontrados.size());   
    }
    
    
    @Test 
    public void updateCalificacionTest( )
    {
        
        CalificacionEntity original = data.get(0);
        
        PodamFactory podam = new PodamFactoryImpl();        
        CalificacionEntity actualizada = podam.manufacturePojo(CalificacionEntity.class);
        actualizada.setId(original.getId());
        
        CalificacionEntity mergeResult = calificacionPersistence.update(actualizada);
        Assert.assertNotNull(mergeResult);
        
        CalificacionEntity encontrada = em.find(CalificacionEntity.class, original.getId());
        Assert.assertNotNull("La calificación se elimino en lugar de actualizarse", encontrada);
    }
    
    
    @Test
    public void deleteCalificacionTest( )
    {
        CalificacionEntity entity = data.get(0);
        calificacionPersistence.delete(entity.getId());
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
    
}
