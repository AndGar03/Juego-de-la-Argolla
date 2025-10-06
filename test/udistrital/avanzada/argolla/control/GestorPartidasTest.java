package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.Partida;
import udistrital.avanzada.argolla.modelo.Equipo;
import udistrital.avanzada.argolla.modelo.Jugador;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para GestorPartidas.
 * Prueba todas las funcionalidades del gestor de partidas.
 * 
 * @author And_Gar03
 * @version 2.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GestorPartidasTest {
    
    private GestorPartidas gestorPartidas;
    private Partida partidaTest1;
    private Partida partidaTest2;
    private Equipo equipoTest;
    private Jugador jugadorTest;
    
    /**
     * Configuración inicial antes de todas las pruebas.
     */
    @BeforeAll
    static void setUpAll() {
        System.out.println("=== INICIANDO PRUEBAS DE GESTORPARTIDAS ===");
    }
    
    /**
     * Limpieza después de todas las pruebas.
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("=== FINALIZANDO PRUEBAS DE GESTORPARTIDAS ===");
    }
    
    /**
     * Configuración antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        System.out.println("Configurando prueba...");
        gestorPartidas = new GestorPartidas();
        partidaTest1 = new Partida("PARTIDA001", 10, 100);
        partidaTest2 = new Partida("PARTIDA002", 15, 150);
        equipoTest = new Equipo("Equipo Test", "Azul");
        jugadorTest = new Jugador("Jugador Test");
    }
    
    /**
     * Limpieza después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        System.out.println("Limpiando después de prueba...");
        gestorPartidas = null;
        partidaTest1 = null;
        partidaTest2 = null;
        equipoTest = null;
        jugadorTest = null;
    }
    
    /**
     * Prueba agregar una partida válida.
     */
    @Test
    @Order(1)
    @DisplayName("Agregar partida válida")
    void testAgregarPartidaValida() {
        // Act
        boolean resultado = gestorPartidas.agregarPartida(partidaTest1);
        
        // Assert
        assertTrue(resultado, "La partida debe agregarse exitosamente");
        assertEquals(1, gestorPartidas.getPartidas().size(), "Debe haber una partida en la lista");
        assertTrue(gestorPartidas.getPartidas().contains(partidaTest1), "La partida debe estar en la lista");
    }
    
    /**
     * Prueba agregar una partida nula.
     */
    @Test
    @Order(2)
    @DisplayName("Agregar partida nula")
    void testAgregarPartidaNula() {
        // Arrange
        Partida partidaNula = null;
        
        // Act
        boolean resultado = gestorPartidas.agregarPartida(partidaNula);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar una partida nula");
        assertEquals(0, gestorPartidas.getPartidas().size(), "No debe haber partidas en la lista");
    }
    
    /**
     * Prueba agregar la misma partida dos veces.
     */
    @Test
    @Order(3)
    @DisplayName("Agregar misma partida dos veces")
    void testAgregarMismaPartidaDosVeces() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act
        boolean resultado = gestorPartidas.agregarPartida(partidaTest1);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar la misma partida dos veces");
        assertEquals(1, gestorPartidas.getPartidas().size(), "Debe haber solo una partida en la lista");
    }
    
    /**
     * Prueba agregar múltiples partidas.
     */
    @Test
    @Order(4)
    @DisplayName("Agregar múltiples partidas")
    void testAgregarMultiplesPartidas() {
        // Act
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Assert
        assertEquals(2, gestorPartidas.getPartidas().size(), "Debe haber dos partidas en la lista");
        assertTrue(gestorPartidas.getPartidas().contains(partidaTest1), "La primera partida debe estar en la lista");
        assertTrue(gestorPartidas.getPartidas().contains(partidaTest2), "La segunda partida debe estar en la lista");
    }
    
    /**
     * Prueba remover una partida existente.
     */
    @Test
    @Order(5)
    @DisplayName("Remover partida existente")
    void testRemoverPartidaExistente() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        boolean resultado = gestorPartidas.removerPartida(partidaTest1);
        
        // Assert
        assertTrue(resultado, "La partida debe removerse exitosamente");
        assertEquals(1, gestorPartidas.getPartidas().size(), "Debe quedar una partida en la lista");
        assertFalse(gestorPartidas.getPartidas().contains(partidaTest1), "La primera partida no debe estar en la lista");
        assertTrue(gestorPartidas.getPartidas().contains(partidaTest2), "La segunda partida debe seguir en la lista");
    }
    
    /**
     * Prueba remover una partida inexistente.
     */
    @Test
    @Order(6)
    @DisplayName("Remover partida inexistente")
    void testRemoverPartidaInexistente() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act
        boolean resultado = gestorPartidas.removerPartida(partidaTest2);
        
        // Assert
        assertFalse(resultado, "No debe ser posible remover una partida inexistente");
        assertEquals(1, gestorPartidas.getPartidas().size(), "Debe seguir habiendo una partida en la lista");
        assertTrue(gestorPartidas.getPartidas().contains(partidaTest1), "La partida original debe seguir en la lista");
    }
    
    /**
     * Prueba remover una partida nula.
     */
    @Test
    @Order(7)
    @DisplayName("Remover partida nula")
    void testRemoverPartidaNula() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        Partida partidaNula = null;
        
        // Act
        boolean resultado = gestorPartidas.removerPartida(partidaNula);
        
        // Assert
        assertFalse(resultado, "No debe ser posible remover una partida nula");
        assertEquals(1, gestorPartidas.getPartidas().size(), "Debe seguir habiendo una partida en la lista");
    }
    
    /**
     * Prueba obtener la lista de partidas.
     */
    @Test
    @Order(8)
    @DisplayName("Obtener lista de partidas")
    void testGetPartidas() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        List<Partida> partidas = gestorPartidas.getPartidas();
        
        // Assert
        assertNotNull(partidas, "La lista de partidas no debe ser null");
        assertEquals(2, partidas.size(), "Debe haber dos partidas en la lista");
        assertTrue(partidas.contains(partidaTest1), "La primera partida debe estar en la lista");
        assertTrue(partidas.contains(partidaTest2), "La segunda partida debe estar en la lista");
    }
    
    /**
     * Prueba obtener lista de partidas vacía.
     */
    @Test
    @Order(9)
    @DisplayName("Obtener lista de partidas vacía")
    void testGetPartidasVacia() {
        // Act
        List<Partida> partidas = gestorPartidas.getPartidas();
        
        // Assert
        assertNotNull(partidas, "La lista de partidas no debe ser null");
        assertTrue(partidas.isEmpty(), "La lista de partidas debe estar vacía");
    }
    
    /**
     * Prueba buscar partida por ID existente.
     */
    @Test
    @Order(10)
    @DisplayName("Buscar partida por ID existente")
    void testBuscarPartidaPorIdExistente() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        Partida partidaEncontrada = gestorPartidas.buscarPartidaPorId("PARTIDA001");
        
        // Assert
        assertNotNull(partidaEncontrada, "La partida debe encontrarse");
        assertEquals(partidaTest1, partidaEncontrada, "Debe ser la misma partida");
        assertEquals("PARTIDA001", partidaEncontrada.getId(), "El ID debe coincidir");
    }
    
    /**
     * Prueba buscar partida por ID inexistente.
     */
    @Test
    @Order(11)
    @DisplayName("Buscar partida por ID inexistente")
    void testBuscarPartidaPorIdInexistente() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act
        Partida partidaEncontrada = gestorPartidas.buscarPartidaPorId("PARTIDA_INEXISTENTE");
        
        // Assert
        assertNull(partidaEncontrada, "No debe encontrarse la partida");
    }
    
    /**
     * Prueba buscar partida por ID nulo.
     */
    @Test
    @Order(12)
    @DisplayName("Buscar partida por ID nulo")
    void testBuscarPartidaPorIdNulo() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act
        Partida partidaEncontrada = gestorPartidas.buscarPartidaPorId(null);
        
        // Assert
        assertNull(partidaEncontrada, "No debe encontrarse la partida con ID nulo");
    }
    
    /**
     * Prueba buscar partida por ID vacío.
     */
    @Test
    @Order(13)
    @DisplayName("Buscar partida por ID vacío")
    void testBuscarPartidaPorIdVacio() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act
        Partida partidaEncontrada = gestorPartidas.buscarPartidaPorId("");
        
        // Assert
        assertNull(partidaEncontrada, "No debe encontrarse la partida con ID vacío");
    }
    
    /**
     * Prueba obtener el número de partidas.
     */
    @Test
    @Order(14)
    @DisplayName("Obtener número de partidas")
    void testGetNumeroPartidas() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        int numeroPartidas = gestorPartidas.getNumeroPartidas();
        
        // Assert
        assertEquals(2, numeroPartidas, "Debe haber dos partidas");
    }
    
    /**
     * Prueba obtener el número de partidas en lista vacía.
     */
    @Test
    @Order(15)
    @DisplayName("Obtener número de partidas en lista vacía")
    void testGetNumeroPartidasVacia() {
        // Act
        int numeroPartidas = gestorPartidas.getNumeroPartidas();
        
        // Assert
        assertEquals(0, numeroPartidas, "No debe haber partidas");
    }
    
    /**
     * Prueba verificar si existe una partida.
     */
    @Test
    @Order(16)
    @DisplayName("Verificar existencia de partida")
    void testExistePartida() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act & Assert
        assertTrue(gestorPartidas.existePartida(partidaTest1), "La partida debe existir");
        assertFalse(gestorPartidas.existePartida(partidaTest2), "La partida no debe existir");
        assertFalse(gestorPartidas.existePartida(null), "Una partida nula no debe existir");
    }
    
    /**
     * Prueba limpiar todas las partidas.
     */
    @Test
    @Order(17)
    @DisplayName("Limpiar todas las partidas")
    void testLimpiarPartidas() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        gestorPartidas.limpiarPartidas();
        
        // Assert
        assertEquals(0, gestorPartidas.getNumeroPartidas(), "No debe haber partidas después de limpiar");
        assertTrue(gestorPartidas.getPartidas().isEmpty(), "La lista debe estar vacía");
    }
    
    /**
     * Prueba obtener partidas por estado.
     */
    @Test
    @Order(18)
    @DisplayName("Obtener partidas por estado")
    void testGetPartidasPorEstado() {
        // Arrange
        partidaTest1.iniciarPartida();
        partidaTest2.finalizarPartida();
        
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        List<Partida> partidasEnCurso = gestorPartidas.getPartidasPorEstado(Partida.EstadoPartida.EN_CURSO);
        List<Partida> partidasFinalizadas = gestorPartidas.getPartidasPorEstado(Partida.EstadoPartida.FINALIZADA);
        
        // Assert
        assertEquals(1, partidasEnCurso.size(), "Debe haber una partida en curso");
        assertTrue(partidasEnCurso.contains(partidaTest1), "La partida en curso debe estar en la lista");
        
        assertEquals(1, partidasFinalizadas.size(), "Debe haber una partida finalizada");
        assertTrue(partidasFinalizadas.contains(partidaTest2), "La partida finalizada debe estar en la lista");
    }
    
    /**
     * Prueba obtener partidas por estado inexistente.
     */
    @Test
    @Order(19)
    @DisplayName("Obtener partidas por estado inexistente")
    void testGetPartidasPorEstadoInexistente() {
        // Arrange
        gestorPartidas.agregarPartida(partidaTest1);
        
        // Act
        List<Partida> partidas = gestorPartidas.getPartidasPorEstado(Partida.EstadoPartida.CANCELADA);
        
        // Assert
        assertTrue(partidas.isEmpty(), "No debe haber partidas con estado cancelado");
    }
    
    /**
     * Prueba obtener partidas con equipos.
     */
    @Test
    @Order(20)
    @DisplayName("Obtener partidas con equipos")
    void testGetPartidasConEquipos() {
        // Arrange
        equipoTest.agregarJugador(jugadorTest);
        partidaTest1.agregarEquipo(equipoTest);
        
        gestorPartidas.agregarPartida(partidaTest1);
        gestorPartidas.agregarPartida(partidaTest2);
        
        // Act
        List<Partida> partidas = gestorPartidas.getPartidas();
        
        // Assert
        assertEquals(2, partidas.size(), "Debe haber dos partidas");
        assertEquals(1, partidas.get(0).getNumeroEquipos(), "La primera partida debe tener un equipo");
        assertEquals(0, partidas.get(1).getNumeroEquipos(), "La segunda partida no debe tener equipos");
    }
}
