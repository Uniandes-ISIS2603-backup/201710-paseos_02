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


import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.persistence.OpinionParticipantePersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author mdr.leon10
 */

@RunWith(Arquillian.class)
public class OpinionesParticipanteLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private OpinionParticipanteLogic opiLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<OpinionParticipanteEntity> data = new ArrayList<OpinionParticipanteEntity>();
    
    private CaminanteEntity caminante;
    
    private PaseoEcologicoEntity paseo;
    
    private InscripcionEntity inscripcion;
    
    private PaseoInstanciaEntity instancia;
    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OpinionParticipanteEntity.class.getPackage())
                .addPackage(OpinionParticipanteLogic.class.getPackage())              
                .addPackage(OpinionParticipantePersistence.class.getPackage())
                .addPackage(CaminanteEntity.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
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
        em.createQuery("delete from OpinionParticipanteEntity").executeUpdate();             
        em.createQuery("delete from CaminanteEntity").executeUpdate();
        em.createQuery("delete from PaseoEcologicoEntity").executeUpdate();
        em.createQuery("delete from InscripcionEntity").executeUpdate();
        em.createQuery("delete from PaseoInstanciaEntity").executeUpdate();
    }
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
         CaminanteEntity nuevo = factory.manufacturePojo(CaminanteEntity.class);
         caminante = nuevo;
         em.persist(nuevo);
         PaseoEcologicoEntity nuevoPaseo = factory.manufacturePojo(PaseoEcologicoEntity.class);
         paseo = nuevoPaseo;
         em.persist(nuevoPaseo);
         PaseoInstanciaEntity instanciaTemp = factory.manufacturePojo(PaseoInstanciaEntity.class);
         instancia = instanciaTemp;
         instancia.setPaseoEcologico(paseo);
         em.persist(instanciaTemp);
         InscripcionEntity nuevaInscripcion = factory.manufacturePojo(InscripcionEntity.class);
         inscripcion = nuevaInscripcion;
         inscripcion.setCaminante(caminante);
         inscripcion.setInstanciaPaseo(instancia);
         em.persist(nuevaInscripcion);
         
        for (int i = 0; i < 3; i++) {
            OpinionParticipanteEntity entity = factory.manufacturePojo(OpinionParticipanteEntity.class);
            entity.setCaminante(caminante);
            entity.setPaseoEcologico(paseo);
            em.persist(entity);
            data.add(entity);
        }
        
        
    }

 @Test
    public void createOpinionesParticipantesTest() {
        OpinionParticipanteEntity newEntity = factory.manufacturePojo(OpinionParticipanteEntity.class);
        OpinionParticipanteEntity result = null;
        newEntity.setCaminante(caminante);
        newEntity.setPaseoEcologico(paseo);
        System.out.println(caminante.getId());
     
     try{
         result = opiLogic.createOpinionParticipante(newEntity);
     
        Assert.assertNotNull(result);
        OpinionParticipanteEntity entity = em.find(OpinionParticipanteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getComentario(), result.getComentario());
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
            List<OpinionParticipanteEntity> list = opiLogic.getOpinionesParticipantes();
        Assert.assertEquals(data.size(), list.size());
        for (OpinionParticipanteEntity entity : list) {
            boolean found = false;
            for (OpinionParticipanteEntity storedEntity : data) {
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
}