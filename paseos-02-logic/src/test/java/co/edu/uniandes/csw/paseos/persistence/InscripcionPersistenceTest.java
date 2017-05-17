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
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
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
public class InscripcionPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InscripcionEntity.class.getPackage())
                .addPackage(InscripcionPersistence.class.getPackage())
                .addPackage(CaminanteEntity.class.getPackage())
                .addPackage(CaminantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }    
    
    @Inject
    private InscripcionPersistence inscripcionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<InscripcionEntity> data = new ArrayList<InscripcionEntity>();
    
    @Inject
    private CaminanteEntity caminanteActual;
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
        em.createQuery("delete from InscripcionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
          
        for (int i = 0; i < 3; i++) {
            InscripcionEntity entity = factory.manufacturePojo(InscripcionEntity.class);
            //entity.setGuia(guiaActual);   Problema al asignarle un guía
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createInscripcionTest( )
    {
        PodamFactory factory = new PodamFactoryImpl();
        InscripcionEntity entityParaPrueba = factory.manufacturePojo(InscripcionEntity.class);
        
        InscripcionEntity entityPersistido = inscripcionPersistence.create(entityParaPrueba);
        
        Assert.assertNotNull("No deberia retornar null al persistir una inscripción", entityPersistido);
        
        InscripcionEntity entityEncontrado = em.find(InscripcionEntity.class, entityPersistido.getId());
        Assert.assertNotNull("La inscripción deberia existir en la base de datos",entityEncontrado);
        
        
        //Se verifica que los valores persistidos sean correctos
    }
    
    @Test
    public void getInscripcionesTest( )
    {
        List<InscripcionEntity> encontrados = inscripcionPersistence.findAll();
        Assert.assertEquals(data.size(), encontrados.size());
        
    }
    
    @Test
    public void getInscripcionesCaminanteTest( )
    {
        Assert.assertNotNull(caminanteActual);
        List<InscripcionEntity> encontrados = inscripcionPersistence.findAll(caminanteActual.getId());
        Assert.assertEquals(data.size(), encontrados.size());   
        
    }
    
    @Test 
    public void updateInscripcionTest( )
    {
        InscripcionEntity original = data.get(0);
        
        PodamFactory podam = new PodamFactoryImpl();        
        InscripcionEntity actualizada = podam.manufacturePojo(InscripcionEntity.class);
        actualizada.setId(original.getId());
        
        InscripcionEntity mergeResult = inscripcionPersistence.update(actualizada);
        Assert.assertNotNull(mergeResult);
        
        InscripcionEntity encontrada = em.find(InscripcionEntity.class, original.getId());
        Assert.assertNotNull("La inscrpcion se elimino en lugar de actualizarse", encontrada);
    }
    
    
    @Test
    public void deleteInscripcionTest( )
    {
        InscripcionEntity entity = data.get(0);
        inscripcionPersistence.delete(entity.getId());
        CaminanteEntity eliminado = em.find(CaminanteEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
}
