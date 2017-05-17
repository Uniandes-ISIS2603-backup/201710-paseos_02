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
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.CaminanteEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.persistence.GuiaPersistence;
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
public class GuiaPersistenceTest {


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private GuiaPersistence guiaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<GuiaEntity> data = new ArrayList<GuiaEntity>();
    
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
        em.createQuery("delete from GuiaEntity").executeUpdate();
    }

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GuiaEntity entity = factory.manufacturePojo(GuiaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    public GuiaPersistenceTest()
    {
    }

    @Test
    public void createGuiaTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        GuiaEntity entityParaPrueba = factory.manufacturePojo(GuiaEntity.class);

        GuiaEntity entityPersistido = guiaPersistence.create(entityParaPrueba);

        Assert.assertNotNull("No deberia retornar null al persistir un guía", entityPersistido);

        GuiaEntity entityEncontrado = em.find(GuiaEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El guía deberia existir en la base de datos",entityEncontrado);

        //Se verifica que los valores persistidos sean correctos
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
    }

    @Test
    public void getGuiaTest()
    {
        List <GuiaEntity> guias = guiaPersistence.findAll();
        Assert.assertEquals(data.size(), guias.size());
        boolean encontrado;
        for (GuiaEntity guia : guias)
        {
            encontrado = false;
            for (GuiaEntity seEspera: data)
            {
                if(guia.getId().equals(seEspera.getId()))
                {
                    verificarConsistenciaAtributos(seEspera, guia);
                    encontrado = true;
                    break;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    @Test
    public void getGuiaTest1()
    {
        GuiaEntity esperado = data.get(0);
        GuiaEntity encontrado = guiaPersistence.find(esperado.getId());
        Assert.assertNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
    }

    @Test
    public void getGuiaTest2()
    {
        GuiaEntity respuesta = guiaPersistence.find(Long.MAX_VALUE);
        Assert.assertNull(respuesta);
    }

    @Test
    public void updateGuiaTest1()
    {
        GuiaEntity original = data.get(0);

        PodamFactory podam = new PodamFactoryImpl();
        GuiaEntity actualizada = podam.manufacturePojo(GuiaEntity.class);
        actualizada.setId(original.getId());

        GuiaEntity mergeResult = guiaPersistence.update(actualizada);
        Assert.assertNotNull(mergeResult);

        GuiaEntity encontrada = em.find(GuiaEntity.class, original.getId());
        Assert.assertNotNull("El guía se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizada, encontrada);
    }

    @Test
    public void updateGuiaTest2 ()
    {
        PodamFactory podam = new PodamFactoryImpl();
        GuiaEntity newEntity = podam.manufacturePojo(GuiaEntity.class);
        newEntity.setId(Long.MAX_VALUE);

        GuiaEntity mergeResult = guiaPersistence.update(newEntity);

        GuiaEntity idOriginal = em.find(GuiaEntity.class, newEntity.getId());
        Assert.assertNull(idOriginal);

        GuiaEntity idGenerado = em.find(GuiaEntity.class, mergeResult.getId());
        Assert.assertNotNull("El caminate debería aparecer en la base de datos", idGenerado);
        verificarConsistenciaAtributos(newEntity, idGenerado);
    }

    @Test
    public void deleteGuiaTest()
    {
        GuiaEntity entity = data.get(0);
        guiaPersistence.delete(entity.getId());
        GuiaEntity guiaBorrado = em.find(GuiaEntity.class, entity.getId());
        Assert.assertNull(guiaBorrado);
    }

    private void verificarConsistenciaAtributos(GuiaEntity entityParaPrueba, GuiaEntity entityEncontrado) {
        Assert.assertEquals(entityParaPrueba.getId(), entityEncontrado.getId());
        Assert.assertEquals(entityParaPrueba.getIdentificacion(), entityEncontrado.getIdentificacion());
        Assert.assertEquals(entityParaPrueba.getTipoIdentificacion(), entityEncontrado.getTipoIdentificacion());
        Assert.assertEquals(entityParaPrueba.getNombre(), entityEncontrado.getNombre());
        Assert.assertEquals(entityParaPrueba.getEdad(), entityEncontrado.getEdad());
        Assert.assertEquals(entityParaPrueba.getTelefono(), entityEncontrado.getTelefono());
        Assert.assertEquals(entityParaPrueba.getDireccion(), entityEncontrado.getDireccion());
        Assert.assertEquals(entityParaPrueba.getCorreoElectronico(), entityEncontrado.getCorreoElectronico());
        Assert.assertEquals(entityParaPrueba.getImagen(), entityEncontrado.getImagen());
        Assert.assertEquals(entityParaPrueba.getExperiencia(), entityEncontrado.getExperiencia());
        Assert.assertEquals(entityParaPrueba.getFormacion(), entityEncontrado.getFormacion());

        List<CalificacionEntity> calificaciones = entityParaPrueba.getCalificaciones();
        List<CalificacionEntity> encontrado = entityEncontrado.getCalificaciones();
        for (CalificacionEntity calificacion: encontrado)
        {
            Assert.assertTrue(calificaciones.contains(calificacion));
        }

        List<PaseoEcologicoEntity> paseos = entityParaPrueba.getPaseosEcologico();
        List<PaseoEcologicoEntity> paseosEncontrados = entityEncontrado.getPaseosEcologico();
        for (PaseoEcologicoEntity paseo: paseosEncontrados)
        {
            Assert.assertTrue(paseos.contains(paseo));
        }


    }
}
