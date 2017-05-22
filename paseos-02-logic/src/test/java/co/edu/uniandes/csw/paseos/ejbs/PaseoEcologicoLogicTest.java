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

import co.edu.uniandes.csw.paseos.entities.ActividadEntity;
import co.edu.uniandes.csw.paseos.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseos.entities.GuiaEntity;
import co.edu.uniandes.csw.paseos.entities.InscripcionEntity;
import co.edu.uniandes.csw.paseos.entities.LugarEntity;
import co.edu.uniandes.csw.paseos.entities.OpinionParticipanteEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEcologicoEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoInstanciaEntity;
import co.edu.uniandes.csw.paseos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseos.persistence.PaseoEcologicoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
 * @author jd.vega11
 */
@RunWith(Arquillian.class)
public class PaseoEcologicoLogicTest
{
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private PaseoEcologicoLogic paseoLogic;

    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     */
    @Inject
    private UserTransaction utx;

  
    private List<PaseoEcologicoEntity> data = new ArrayList<>();
    
    LugarEntity lugarDeEncuentro;
    
    LugarEntity lugarDeDestino;
    
    GuiaEntity guia;
    
    private List<PaseoInstanciaEntity> instanciasData = new ArrayList<>( );
    
    private List<ActividadEntity> actividadesData = new ArrayList<>();
    
    private List<CalificacionEntity> calificacionData = new ArrayList<>();    
    
    private List<OpinionParticipanteEntity> opinionData = new ArrayList<>();
    

    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaseoEcologicoEntity.class.getPackage())
                .addPackage(PaseoEcologicoLogic.class.getPackage())              
                .addPackage(PaseoEcologicoPersistence.class.getPackage())
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(LugarEntity.class.getPackage())            
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
        em.createQuery("delete from PaseoInstanciaEntity").executeUpdate();        
        em.createQuery("delete from OpinionParticipanteEntity").executeUpdate();        
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from PaseoEcologicoEntity").executeUpdate(); 
        em.createQuery("delete from LugarEntity").executeUpdate(); 
        em.createQuery("delete from GuiaEntity").executeUpdate(); 
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() 
    {
        guia = factory.manufacturePojo(GuiaEntity.class);
        guia.setId(1L);
        em.persist(guia);
        
        lugarDeEncuentro = factory.manufacturePojo(LugarEntity.class);
        lugarDeEncuentro.setId(1L);
        em.persist(lugarDeEncuentro);
        
        lugarDeDestino = factory.manufacturePojo(LugarEntity.class);
        lugarDeDestino.setId(1L);
        em.persist(lugarDeDestino);
        
        Random rn = new Random();
        for(int i = 0; i < 4; i++)
        {
            PaseoEcologicoEntity entity = factory.manufacturePojo(PaseoEcologicoEntity.class);
            
            //Es necesario definir estos numeros dentro de rangos especificos para evitar 
            //problemas si nMaxCaminantes < nMinimCaminantes           
            entity.setnMaxCaminantes(rn.nextInt(101) + 100);
            entity.setnMinimCaminantes(rn.nextInt(91) + 10);
            
            entity.setGuia(guia);
            entity.setLugarDeDestino(lugarDeDestino);
            entity.setLugarDeEncuentro(lugarDeEncuentro);
            
            em.persist(entity);
            
            entity.setActividades(new ArrayList<>());
            entity.setCalificacionesGuia(new ArrayList<>());
            entity.setOpiniones(new ArrayList<>());
            entity.setInstancias(new ArrayList<>());
            
            data.add(entity);
        }
         
        for(int i = 0; i < 15; i++)
        {
            CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
            calificacion.setPaseoEcologico(data.get(i%3));
            data.get(i%3).getCalificacionesGuia().add(calificacion);
            em.persist(calificacion);
            calificacionData.add(calificacion);
        }
        
        for(int i = 0; i < 15; i++)
        {
            OpinionParticipanteEntity opinion = factory.manufacturePojo(OpinionParticipanteEntity.class);
            opinion.setPaseoEcologico(data.get(i%3));
            data.get(i%3).getOpiniones().add(opinion);
            em.persist(opinion);
            opinionData.add(opinion);
        }
        
        for(int i = 0; i < 15; i++)
        {
            PaseoInstanciaEntity instancia = factory.manufacturePojo(PaseoInstanciaEntity.class);
            instancia.setPaseoEcologico(data.get(i%3));
            data.get(i%3).getInstancias().add(instancia);
            em.persist(instancia);
            instanciasData.add(instancia);
        }
        
        InscripcionEntity inscripcion = factory.manufacturePojo(InscripcionEntity.class);
        inscripcion.setInstanciaPaseo(instanciasData.get(0));
        em.persist(inscripcion);
        
        for(int i = 0; i < 15; i++)
        {
            ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
            actividad.setPaseoEcologico(data.get(i%3));
            data.get(i%3).getActividades().add(actividad);
            em.persist(actividad);
            actividadesData.add(actividad);
        }       
       
    }
    
    /**
     * Prueba  para crear un paseo. Caso exitoso
     */
    @Test
    public void createPaseoTest( ) throws BusinessLogicException
    {       
        PaseoEcologicoEntity entityParaPrueba = factory.manufacturePojo(PaseoEcologicoEntity.class);  
        
        //Es necesario definir estos numeros dentro de rangos especificos para evitar la excepcion que se
        //genera cuando nMaxCaminantes < nMinimCaminantes   
        Random rn = new Random();    
        entityParaPrueba.setnMaxCaminantes(rn.nextInt(101) + 100);
        entityParaPrueba.setnMinimCaminantes(rn.nextInt(91) + 10);
        
        entityParaPrueba.setGuia(guia);
        entityParaPrueba.setLugarDeEncuentro(lugarDeEncuentro);
        entityParaPrueba.setLugarDeDestino(lugarDeDestino);        
        
        PaseoEcologicoEntity entityPersistido = paseoLogic.createPaseo(entityParaPrueba);
        Assert.assertNotNull("No deberia retornar null al persistir un paseo", entityPersistido);
        
        construirRelaciones(entityPersistido);
        
        PaseoEcologicoEntity entityEncontrado = em.find(PaseoEcologicoEntity.class, entityPersistido.getId());
        Assert.assertNotNull("El paseo deberia existir en la base de datos", entityEncontrado);
        
        //Se verifica que los valores persistidos sean correctos y que las relaciones sean coherentes
        verificarConsistenciaAtributos(entityParaPrueba, entityEncontrado);
        verificarCoherenciaRelaciones(entityEncontrado, entityPersistido);
    }
    
    /**
     * Prueba para crear un paseo sin tematica
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest2() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        newEntity.setTematica(null);
        paseoLogic.createPaseo(newEntity);
    }
    /**
     * Prueba para crear un paseo sin costo
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest3() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        newEntity.setCosto(null);
        paseoLogic.createPaseo(newEntity);
    }
    
     /**
     * Prueba para crear un paseo sin guia
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest4() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        newEntity.setGuia(null);
        paseoLogic.createPaseo(newEntity);
    }
    
     /**
     * Prueba para crear un paseo sin lugares de encuentro y destino
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest5() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        newEntity.setLugarDeEncuentro(null);
        newEntity.setLugarDeDestino(null);
        paseoLogic.createPaseo(newEntity);
    }
    
     /**
     * Prueba para crear un paseo con nMaxCaminantes < nMinimCaminantes
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest6() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        newEntity.setnMaxCaminantes(Integer.MIN_VALUE);
        newEntity.setnMinimCaminantes(Integer.MAX_VALUE);
        paseoLogic.createPaseo(newEntity);
    }
    
    /**
     * Prueba para crear un paseo con un guia que no existe en la base de datos
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest7() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        
        GuiaEntity guiaFallido = factory.manufacturePojo(GuiaEntity.class);
        guiaFallido.setId(Long.MAX_VALUE);
        
        newEntity.setGuia(guiaFallido);
        
        paseoLogic.createPaseo(newEntity);
    }
    
     /**
     * Prueba para crear un paseo con un lugar de encuentro que no existe en la base de datos
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest8() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        
        LugarEntity lugarFallido = factory.manufacturePojo(LugarEntity.class);
        lugarFallido.setId(Long.MAX_VALUE);
        
        newEntity.setLugarDeEncuentro(lugarFallido);
        
        paseoLogic.createPaseo(newEntity);
    }
    
     /**
     * Prueba para crear un paseo con un lugar de destino que no existe en la base de datos
     */
    @Test(expected = BusinessLogicException.class)
    public void createCaminanteTest9() throws Exception {
        PaseoEcologicoEntity newEntity = factory.manufacturePojo(PaseoEcologicoEntity.class);
        
        LugarEntity lugarFallido = factory.manufacturePojo(LugarEntity.class);
        lugarFallido.setId(Long.MAX_VALUE);
        
        newEntity.setLugarDeDestino(lugarFallido);
        
        paseoLogic.createPaseo(newEntity);
    }
    
    @Test
    public void getPaseosTest( )
    {
        List<PaseoEcologicoEntity> encontrados = paseoLogic.getPaseos();
        Assert.assertEquals(data.size(), encontrados.size());
        boolean found;
        for(PaseoEcologicoEntity encontrado : encontrados)
        {
            found = false;
            for(PaseoEcologicoEntity esperado : data)
            {
                if(encontrado.getId().equals(esperado.getId()))
                {
                    verificarConsistenciaAtributos(esperado, encontrado);
                    verificarCoherenciaRelaciones(esperado, encontrado);
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
        PaseoEcologicoEntity esperado = data.get(0);
        PaseoEcologicoEntity encontrado = paseoLogic.getPaseo(esperado.getId());
        Assert.assertNotNull(encontrado);
        verificarConsistenciaAtributos(esperado, encontrado);
        verificarCoherenciaRelaciones(esperado, encontrado);
    }
    
    //Escenario 2: No existe un paseo con el id dado 
    @Test
    public void getPaseoTest2( )
    {
        PaseoEcologicoEntity respuesta = paseoLogic.getPaseo(Long.MAX_VALUE);
        Assert.assertNull(respuesta);      
    }
    
     //Escenario 1: Se va a actualizar un paseo que existe en la base de datos.
    @Test 
    public void updatePaseoTest1( ) throws BusinessLogicException
    {
        PaseoEcologicoEntity original = data.get(3);        
              
        PaseoEcologicoEntity actualizado = factory.manufacturePojo(PaseoEcologicoEntity.class);

        actualizado.setId(original.getId());
        actualizado.setGuia(original.getGuia());
        actualizado.setLugarDeDestino(original.getLugarDeDestino());
        actualizado.setLugarDeEncuentro(original.getLugarDeEncuentro());
        
        actualizado.setnMaxCaminantes(original.getnMaxCaminantes());
        actualizado.setnMinimCaminantes(original.getnMinimCaminantes());
        
        PaseoEcologicoEntity mergeResult = paseoLogic.updatePaseo(actualizado);
        Assert.assertNotNull(mergeResult);
        
        PaseoEcologicoEntity encontrada = em.find(PaseoEcologicoEntity.class, original.getId());
        Assert.assertNotNull("El paseo se elimino en lugar de actualizarse", encontrada);
        verificarConsistenciaAtributos(actualizado, encontrada);
    }
    
    /**
     * Prueba para actualizar un paseo sin tematica
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest2() throws Exception
    {
        PaseoEcologicoEntity original = data.get(3);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        actualizada.setTematica(null);
        paseoLogic.updatePaseo(actualizada);
    }
    
     /**
     * Prueba para actualizar un paseo sin costo
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest3() throws Exception
    {
        PaseoEcologicoEntity original = data.get(3);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        actualizada.setCosto(null);
        paseoLogic.updatePaseo(actualizada);
    }
    
    /**
     * Prueba para actualizar un paseo sin guia
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest4() throws Exception
    {
        PaseoEcologicoEntity original = data.get(3);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        actualizada.setGuia(null);
        paseoLogic.updatePaseo(actualizada);
    }
    
     /**
     * Prueba para actualizar un paseo sin lugares de encuentro y destino
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest5() throws Exception
    {
        PaseoEcologicoEntity original = data.get(3);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        actualizada.setLugarDeEncuentro(null);
        actualizada.setLugarDeDestino(null);
        paseoLogic.updatePaseo(actualizada);
    }
    
    /**
     * Prueba para actualizar un paseo que tenga caminantes inscritos
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest6() throws Exception
    {
        PaseoEcologicoEntity original = data.get(0);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());       
        paseoLogic.updatePaseo(actualizada);
    }
    
    /**
     * Prueba para actualizar un paseo con nMaxCaminantes < nMinimCaminantes
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest7() throws Exception
    {
        PaseoEcologicoEntity original = data.get(0);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        
        actualizada.setnMaxCaminantes(Integer.MIN_VALUE);
        actualizada.setnMinimCaminantes(Integer.MAX_VALUE);
        
        paseoLogic.updatePaseo(actualizada);
    }
   
     /**
     * Prueba para actualizar un paseo con un guia que no existe
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest8() throws Exception
    {
        PaseoEcologicoEntity original = data.get(0);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        
        GuiaEntity guiaFallido = factory.manufacturePojo(GuiaEntity.class);
        guiaFallido.setId(Long.MAX_VALUE);
        
        actualizada.setGuia(guiaFallido);
        
        paseoLogic.updatePaseo(actualizada);
    }
    
    /**
     * Prueba para actualizar un paseo con un lugar de encuentro que no existe
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest9() throws Exception
    {
        PaseoEcologicoEntity original = data.get(0);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        
        LugarEntity lugarFallido = factory.manufacturePojo(LugarEntity.class);
        lugarFallido.setId(Long.MAX_VALUE);
        
        actualizada.setLugarDeEncuentro(lugarFallido);
        
        paseoLogic.updatePaseo(actualizada);
    }
    
    /**
     * Prueba para actualizar un paseo con un lugar de destino que no existe
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePaseoTest10() throws Exception
    {
        PaseoEcologicoEntity original = data.get(0);
        PaseoEcologicoEntity actualizada = factory.manufacturePojo(PaseoEcologicoEntity.class);
        actualizada.setId(original.getId());
        
        LugarEntity lugarFallido = factory.manufacturePojo(LugarEntity.class);
        lugarFallido.setId(Long.MAX_VALUE);
        
        actualizada.setLugarDeDestino(lugarFallido);
        
        paseoLogic.updatePaseo(actualizada);
    }
    
    /**
     * Prueba para eliminar un paseo. Caso exitoso
     */
    @Test
    public void deletePaseoTest( ) throws BusinessLogicException
    {
        PaseoEcologicoEntity entity = data.get(3);
        paseoLogic.deletePaseo(entity.getId());
        PaseoEcologicoEntity eliminado = em.find(PaseoEcologicoEntity.class, entity.getId());
        Assert.assertNull(eliminado);
    }
    
    /**
     * Prueba para eliminar un paseo que tiene participantes inscritos
     */
    @Test(expected = BusinessLogicException.class)
    public void deletePaseoTest2( ) throws Exception
    {
        PaseoEcologicoEntity entity = data.get(0);
        paseoLogic.deletePaseo(entity.getId());       
    }
    
    /**
     * Prueba para eliminar un paseo sobre el cual ya se han hecho opiniones o calificaciones.
     */
    @Test(expected = BusinessLogicException.class)
    public void deletePaseoTest3( ) throws Exception
    {
        PaseoEcologicoEntity entity = data.get(1);
        paseoLogic.deletePaseo(entity.getId());       
    }

    /**
     * Crea las relaciones del paseo con las entidades correspondientes.
     */
    private void construirRelaciones(PaseoEcologicoEntity entity)
    {
        try {
            utx.begin();            
           
            List<CalificacionEntity> calificaciones = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
                calificacion.setPaseoEcologico(entity);
                em.persist(calificacion);
                calificaciones.add(calificacion);
            }
            entity.setCalificacionesGuia(calificaciones);
            
            List<OpinionParticipanteEntity> opiniones = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                OpinionParticipanteEntity opinion = factory.manufacturePojo(OpinionParticipanteEntity.class);
                opinion.setPaseoEcologico(entity);
                em.persist(opinion);
                opiniones.add(opinion);
            }
            entity.setOpiniones(opiniones);
            
            List<ActividadEntity> actividades = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
                actividad.setPaseoEcologico(entity);
                em.persist(actividad);
                actividades.add(actividad);
            }
            entity.setActividades(actividades);
            
            List<PaseoInstanciaEntity> instancias = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                PaseoInstanciaEntity instancia = factory.manufacturePojo(PaseoInstanciaEntity.class);
                instancia.setPaseoEcologico(entity);
                em.persist(instancia);
                instancias.add(instancia);
            }
            entity.setInstancias(instancias);

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
    
    private void verificarConsistenciaAtributos(PaseoEcologicoEntity p1, PaseoEcologicoEntity p2)
    {
        Assert.assertEquals(p1.getDescripcion(), p2.getDescripcion());
        Assert.assertEquals(p1.getCosto(), p2.getCosto());
        Assert.assertEquals(p1.getHayTransporte(), p2.getHayTransporte());
        Assert.assertEquals(p1.getTematica(), p2.getTematica());
        Assert.assertEquals(p1.getnMaxCaminantes(), p2.getnMaxCaminantes());
        Assert.assertEquals(p1.getnMinimCaminantes(), p2.getnMinimCaminantes());
        
        List<Integer> condFisicasOriginal = p1.getCondicionesFisicas();
        List<Integer> condfisicasEncontrado = p2.getCondicionesFisicas();
        
        for(Integer condFisica : condfisicasEncontrado)
        {
            Assert.assertTrue(condFisicasOriginal.contains(condFisica));
        }       
    }
    
    private void verificarCoherenciaRelaciones(PaseoEcologicoEntity c1, PaseoEcologicoEntity c2)
    {      
        Assert.assertEquals(c1.getGuia(), c2.getGuia());
        Assert.assertEquals(c1.getLugarDeEncuentro(), c2.getLugarDeEncuentro());
        Assert.assertEquals(c1.getLugarDeDestino(), c2.getLugarDeDestino());
        
        Assert.assertNotNull(c1.getCalificacionesGuia());
        Assert.assertNotNull(c2.getCalificacionesGuia());       
        Assert.assertEquals(c2.getCalificacionesGuia().size(), c1.getCalificacionesGuia().size());
        
        for(CalificacionEntity calif : c2.getCalificacionesGuia())
        {
            Assert.assertTrue(c1.getCalificacionesGuia().contains(calif));
        }
        
        Assert.assertNotNull(c1.getOpiniones());
        Assert.assertNotNull(c2.getOpiniones());
        Assert.assertEquals(c2.getOpiniones().size(), c1.getOpiniones().size());
        
        for(OpinionParticipanteEntity opin : c2.getOpiniones())
        {
            Assert.assertTrue(c1.getOpiniones().contains(opin));
        }
        
        Assert.assertNotNull(c1.getActividades());
        Assert.assertNotNull(c2.getActividades());
        Assert.assertEquals(c2.getActividades().size(), c1.getActividades().size());
        
        for(ActividadEntity act : c2.getActividades())
        {
            Assert.assertTrue(c1.getActividades().contains(act));
        }
        
        Assert.assertNotNull(c1.getInstancias());
        Assert.assertNotNull(c2.getInstancias());
        Assert.assertEquals(c2.getInstancias().size(), c1.getInstancias().size());
        
        for(PaseoInstanciaEntity inst : c2.getInstancias())
        {
            Assert.assertTrue(c1.getInstancias().contains(inst));
        }
    }

}
