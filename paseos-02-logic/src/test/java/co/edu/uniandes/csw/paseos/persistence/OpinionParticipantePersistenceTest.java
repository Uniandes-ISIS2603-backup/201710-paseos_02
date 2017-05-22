package co.edu.uniandes.csw.paseos.persistence;/*
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

import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class OpinionParticipantePersistenceTest {


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OpinionParticipanteEntity.class.getPackage())
                .addPackage(OpinionParticipantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private OpinionParticipantePersistence persistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<OpinionParticipanteEntity> data = new ArrayList<OpinionParticipanteEntity>();

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

    private void clearData() {
        em.createQuery("delete from OpinionParticipanteEntity").executeUpdate();
    }

    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OpinionParticipanteEntity entity = factory.manufacturePojo(OpinionParticipanteEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    public OpinionParticipantePersistenceTest()
    {
    }

    @Test
    public void createOpinionParticipanteTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        OpinionParticipanteEntity entityParaPrueba = factory.manufacturePojo(OpinionParticipanteEntity.class);

        OpinionParticipanteEntity entityPersistido = persistence.create(entityParaPrueba);

        Assert.assertNotNull("No deberia retornar null al persistir una opinion", entityPersistido);

       OpinionParticipanteEntity entityEncontrado = em.find(OpinionParticipanteEntity.class, entityPersistido.getId());
        Assert.assertNotNull("La opinion deberia existir en la base de datos",entityEncontrado);

        //Se verifica que los valores persistidos sean correctos
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
    }

    @Test
    public void getOpinionParticipanteTest()
    {
        List <OpinionParticipanteEntity> opiniones = persistence.findAll();
        Assert.assertEquals(data.size(), opiniones.size());
        boolean encontrado;
        for (OpinionParticipanteEntity opinion : opiniones)
        {
            encontrado = false;
            for (OpinionParticipanteEntity seEspera: data)
            {
                if(opinion.getId().equals(seEspera.getId()))
                {
                    verificarConsistenciaAtributos(seEspera, opinion);
                    encontrado = true;
                    break;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    @Test
    public void getOpinionParticipanteTest1() {
        OpinionParticipanteEntity esperado = data.get(0);
        OpinionParticipanteEntity encontrado = persistence.find(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }

    @Test
    public void getOpinionParticipanteTest2()
    {
        OpinionParticipanteEntity respuesta = persistence.find(Long.MAX_VALUE);
        Assert.assertNull(respuesta);
    }

    @Test
    public void updateOpinionParticipanteTest1()
    {
        OpinionParticipanteEntity original = data.get(0);

        PodamFactory podam = new PodamFactoryImpl();
        OpinionParticipanteEntity actualizada = podam.manufacturePojo(OpinionParticipanteEntity.class);
        actualizada.setId(original.getId());

        OpinionParticipanteEntity mergeResult = persistence.update(actualizada);
        Assert.assertNotNull(mergeResult);

        OpinionParticipanteEntity encontrada = em.find(OpinionParticipanteEntity.class, original.getId());
        Assert.assertNotNull("El guía se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizada, encontrada);
    }

    @Test
    public void updateOpinionParticipanteTest2 ()
    {
        PodamFactory podam = new PodamFactoryImpl();
        OpinionParticipanteEntity newEntity = podam.manufacturePojo(OpinionParticipanteEntity.class);
        newEntity.setId(Long.MAX_VALUE);

        OpinionParticipanteEntity mergeResult = persistence.update(newEntity);

        OpinionParticipanteEntity idOriginal = em.find(OpinionParticipanteEntity.class, newEntity.getId());
        Assert.assertNull(idOriginal);

        OpinionParticipanteEntity idGenerado = em.find(OpinionParticipanteEntity.class, mergeResult.getId());
        Assert.assertNotNull("El caminate debería aparecer en la base de datos", idGenerado);
        verificarConsistenciaAtributos(newEntity, idGenerado);
    }

    @Test
    public void deleteOpinionParticipanteTest()
    {
        OpinionParticipanteEntity entity = data.get(0);
        persistence.delete(entity.getId());
        OpinionParticipanteEntity guiaBorrado = em.find(OpinionParticipanteEntity.class, entity.getId());
        Assert.assertNull(guiaBorrado);
    }

    private void verificarConsistenciaAtributos(OpinionParticipanteEntity entityParaPrueba, OpinionParticipanteEntity entityEncontrado) {

        Assert.assertEquals(entityParaPrueba.getComentario(),entityEncontrado.getComentario());
        Assert.assertEquals(entityParaPrueba.getFecha().getDay(), entityEncontrado.getFecha().getDay());
        Assert.assertEquals(entityParaPrueba.getImagen(),entityEncontrado.getImagen());
    }
}