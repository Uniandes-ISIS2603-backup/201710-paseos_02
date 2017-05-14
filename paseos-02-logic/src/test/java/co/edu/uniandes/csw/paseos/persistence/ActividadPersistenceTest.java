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

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author juanchaves
 */
@RunWith(Arquillian.class)

public class ActividadPersistenceTest {
     /**
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(ActividadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }    
    @Inject
    private ActividadPersistence actividadPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ActividadEntity> data = new ArrayList<ActividadEntity>();
    
    private PaseoEcologicoEntity paseoActual;
    @Test
    public void createActividadTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);

        ActividadEntity result = actividadPersistence.create(newEntity);

        Assert.assertNotNull(result);
        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
        Assert.assertNotNull(entity);
        verificarConsistenciaAtributos(entity, newEntity);
    }
    @Test
    public void getActividadesTest() {
        List<ActividadEntity> list = actividadPersistence.findAll(paseoActual.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ActividadEntity ent : list) {
            boolean found = false;
            for (ActividadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getCompanyTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity newEntity = actividadPersistence.find(entity.getPaseoEcologico().getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        verificarConsistenciaAtributos(entity, newEntity);
    }
    public ActividadPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
            PodamFactory factory = new PodamFactoryImpl();
            paseoActual = factory.manufacturePojo(PaseoEcologicoEntity.class);
            paseoActual.setId((long)10);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class ActividadPersistence.
     */
    @Test
    public void testFind() throws Exception {
        fail("testFind");
    }

    /**
     * Test of findAll method, of class ActividadPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        fail("testFindAll");
    }

    /**
     * Test of create method, of class ActividadPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        fail("testCreate");
    }

    /**
     * Test of update method, of class ActividadPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        fail("testUpdate");
    }

    /**
     * Test of delete method, of class ActividadPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        fail("testDelete");
    }

    private void clearData() {
        em.createQuery("delete from ActividadEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    private void verificarConsistenciaAtributos(ActividadEntity p1, ActividadEntity p2)
    {
        Assert.assertEquals(p1.getId(), p2.getId());
        Assert.assertEquals(p1.getNombre(), p2.getNombre());
        Assert.assertEquals(p1.getDuracion(), p2.getDuracion());
        Assert.assertEquals(p1.getDescripcion(), p2.getDescripcion());
        Assert.assertEquals(p1.getPaseoEcologico(), p2.getPaseoEcologico());    
        
        List<String> list1 = p1.getEquipamiento();
        List<String> list2 = p2.getEquipamiento();
        for(String s:list1)
        {
            Assert.assertTrue(list2.contains(s));
        }
    }
    
}
