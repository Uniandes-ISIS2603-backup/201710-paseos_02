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

import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.paseos.persistence.InscripcionPersistence;
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
 * @author Rol Positivo SAS
 */
public class InscripcionLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private InscripcionLogic inscripcionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<InscripcionEntity> data = new ArrayList<InscripcionEntity>();
    
    private CaminanteEntity caminante;
    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InscripcionEntity.class.getPackage())
                .addPackage(InscripcionLogic.class.getPackage())              
                .addPackage(InscripcionPersistence.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(CaminanteEntity.class.getPackage())
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
        em.createQuery("delete from InscripcionEntity").executeUpdate();             
        em.createQuery("delete from CaminanteEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
         CaminanteEntity nuevo = factory.manufacturePojo(CaminanteEntity.class);
         caminante = nuevo;
         em.persist(nuevo);
        for (int i = 0; i < 3; i++) {
            InscripcionEntity entity = factory.manufacturePojo(InscripcionEntity.class);
            entity.setCaminante(caminante);
            em.persist(entity);
            data.add(entity);
        }
        
        
    }

 @Test
    public void createInscripcionTest() {
        InscripcionEntity newEntity = factory.manufacturePojo(InscripcionEntity.class);
        InscripcionEntity result = null;
        newEntity.setCaminante(caminante);
        
     
     try{
         result = inscripcionLogic.createInscripcion(newEntity);
     
        Assert.assertNotNull(result);
        InscripcionEntity entity = em.find(InscripcionEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getRealizoPago(), entity.getRealizoPago());
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         Assert.fail();
     }
    }

     @Test
    public void getInscripcionesTest() {
        try
        {
            List<InscripcionEntity> list = inscripcionLogic.getInscripciones(caminante.getId());
        Assert.assertEquals(data.size(), list.size());
        for (InscripcionEntity entity : list) {
            boolean found = false;
            for (InscripcionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        }
     catch(Exception e){
         System.out.println(e.getMessage());
         Assert.fail();
     }
    }

    @Test
    public void getInscripcionTest() {
        InscripcionEntity entity = data.get(0);
        InscripcionEntity resultEntity = inscripcionLogic.getInscripcion(caminante.getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getRealizoPago(), resultEntity.getRealizoPago());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

     @Test
    public void deleteCalificacionTest() {
        InscripcionEntity entity = data.get(1);
        inscripcionLogic.deleteInscripcion(caminante.getId(),entity.getId());
        InscripcionEntity deleted = em.find(InscripcionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

      @Test
    public void updateInscripcionTest() {
        InscripcionEntity entity = data.get(0);
        InscripcionEntity pojoEntity = factory.manufacturePojo(InscripcionEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setCaminante(caminante);
                
        try{
        inscripcionLogic.updateInscripcion(pojoEntity);

        InscripcionEntity resp = em.find(InscripcionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getRealizoPago(), resp.getRealizoPago());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        }
        catch(Exception e){
        Assert.fail();
        }
    }
}

