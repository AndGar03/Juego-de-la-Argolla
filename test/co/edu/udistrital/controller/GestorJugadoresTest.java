package co.edu.udistrital.controller;

import co.edu.udistrital.model.Jugador;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para GestorJugadores.
 * Prueba todas las funcionalidades del gestor de jugadores.
 * 
 * @author Sansantax
 * @version 3.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GestorJugadoresTest {
    
    private GestorJugadores gestorJugadores;
    private Jugador jugadorTest1;
    private Jugador jugadorTest2;
    private Jugador jugadorTest3;
    
    /**
     * Configuración inicial antes de todas las pruebas.
     */
    @BeforeAll
    static void setUpAll() {
        System.out.println("=== INICIANDO PRUEBAS DE GESTORJUGADORES ===");
    }
    
    /**
     * Limpieza después de todas las pruebas.
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("=== FINALIZANDO PRUEBAS DE GESTORJUGADORES ===");
    }
    
    /**
     * Configuración antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        System.out.println("Configurando prueba...");
        gestorJugadores = new GestorJugadores();
        jugadorTest1 = new Jugador("Jugador A");
        jugadorTest2 = new Jugador("Jugador B");
        jugadorTest3 = new Jugador("Jugador C");
    }
    
    /**
     * Limpieza después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        System.out.println("Limpiando después de prueba...");
        gestorJugadores = null;
        jugadorTest1 = null;
        jugadorTest2 = null;
        jugadorTest3 = null;
    }
    
    /**
     * Prueba agregar un jugador válido.
     */
    @Test
    @Order(1)
    @DisplayName("Agregar jugador válido")
    void testAgregarJugadorValido() {
        // Act
        boolean resultado = gestorJugadores.agregarJugador(jugadorTest1);
        
        // Assert
        assertTrue(resultado, "El jugador debe agregarse exitosamente");
        assertEquals(1, gestorJugadores.obtenerTodosLosJugadores().size(), "Debe haber un jugador en la lista");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest1), "El jugador debe estar en la lista");
    }
    
    /**
     * Prueba agregar un jugador nulo.
     */
    @Test
    @Order(2)
    @DisplayName("Agregar jugador nulo")
    void testAgregarJugadorNulo() {
        // Arrange
        Jugador jugadorNulo = null;
        
        // Act
        boolean resultado = gestorJugadores.agregarJugador(jugadorNulo);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar un jugador nulo");
        assertEquals(0, gestorJugadores.obtenerTodosLosJugadores().size(), "No debe haber jugadores en la lista");
    }
    
    /**
     * Prueba agregar el mismo jugador dos veces.
     */
    @Test
    @Order(3)
    @DisplayName("Agregar mismo jugador dos veces")
    void testAgregarMismoJugadorDosVeces() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        
        // Act
        boolean resultado = gestorJugadores.agregarJugador(jugadorTest1);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar el mismo jugador dos veces");
        assertEquals(1, gestorJugadores.obtenerTodosLosJugadores().size(), "Debe haber solo un jugador en la lista");
    }
    
    /**
     * Prueba agregar múltiples jugadores.
     */
    @Test
    @Order(4)
    @DisplayName("Agregar múltiples jugadores")
    void testAgregarMultiplesJugadores() {
        // Act
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        gestorJugadores.agregarJugador(jugadorTest3);
        
        // Assert
        assertEquals(3, gestorJugadores.obtenerTodosLosJugadores().size(), "Debe haber tres jugadores en la lista");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest1), "El primer jugador debe estar en la lista");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest2), "El segundo jugador debe estar en la lista");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest3), "El tercer jugador debe estar en la lista");
    }
    
    /**
     * Prueba remover un jugador existente.
     */
    @Test
    @Order(5)
    @DisplayName("Remover jugador existente")
    void testRemoverJugadorExistente() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        
        // Act
        boolean resultado = gestorJugadores.removerJugador(jugadorTest1);
        
        // Assert
        assertTrue(resultado, "El jugador debe removerse exitosamente");
        assertEquals(1, gestorJugadores.obtenerTodosLosJugadores().size(), "Debe quedar un jugador en la lista");
        assertFalse(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest1), "El primer jugador no debe estar en la lista");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest2), "El segundo jugador debe seguir en la lista");
    }
    
    /**
     * Prueba remover un jugador inexistente.
     */
    @Test
    @Order(6)
    @DisplayName("Remover jugador inexistente")
    void testRemoverJugadorInexistente() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        
        // Act
        boolean resultado = gestorJugadores.removerJugador(jugadorTest2);
        
        // Assert
        assertFalse(resultado, "No debe ser posible remover un jugador inexistente");
        assertEquals(1, gestorJugadores.obtenerTodosLosJugadores().size(), "Debe seguir habiendo un jugador en la lista");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugadorTest1), "El jugador original debe seguir en la lista");
    }
    
    /**
     * Prueba remover un jugador nulo.
     */
    @Test
    @Order(7)
    @DisplayName("Remover jugador nulo")
    void testRemoverJugadorNulo() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        Jugador jugadorNulo = null;
        
        // Act
        boolean resultado = gestorJugadores.removerJugador(jugadorNulo);
        
        // Assert
        assertFalse(resultado, "No debe ser posible remover un jugador nulo");
        assertEquals(1, gestorJugadores.obtenerTodosLosJugadores().size(), "Debe seguir habiendo un jugador en la lista");
    }
    
    /**
     * Prueba obtener la lista de jugadores.
     */
    @Test
    @Order(8)
    @DisplayName("Obtener lista de jugadores")
    void testGetJugadores() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        
        // Act
        List<Jugador> jugadores = gestorJugadores.obtenerTodosLosJugadores();
        
        // Assert
        assertNotNull(jugadores, "La lista de jugadores no debe ser null");
        assertEquals(2, jugadores.size(), "Debe haber dos jugadores en la lista");
        assertTrue(jugadores.contains(jugadorTest1), "El primer jugador debe estar en la lista");
        assertTrue(jugadores.contains(jugadorTest2), "El segundo jugador debe estar en la lista");
    }
    
    /**
     * Prueba obtener lista de jugadores vacía.
     */
    @Test
    @Order(9)
    @DisplayName("Obtener lista de jugadores vacía")
    void testGetJugadoresVacia() {
        // Act
        List<Jugador> jugadores = gestorJugadores.obtenerTodosLosJugadores();
        
        // Assert
        assertNotNull(jugadores, "La lista de jugadores no debe ser null");
        assertTrue(jugadores.isEmpty(), "La lista de jugadores debe estar vacía");
    }
    
    /**
     * Prueba buscar jugador por nombre existente.
     */
    @Test
    @Order(10)
    @DisplayName("Buscar jugador por nombre existente")
    void testBuscarJugadorPorNombreExistente() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        
        // Act
        Jugador jugadorEncontrado = gestorJugadores.buscarJugadorPorNombre("Jugador A");
        
        // Assert
        assertNotNull(jugadorEncontrado, "El jugador debe encontrarse");
        assertEquals(jugadorTest1, jugadorEncontrado, "Debe ser el mismo jugador");
        assertEquals("Jugador A", jugadorEncontrado.getNombre(), "El nombre debe coincidir");
    }
    
    /**
     * Prueba buscar jugador por nombre inexistente.
     */
    @Test
    @Order(11)
    @DisplayName("Buscar jugador por nombre inexistente")
    void testBuscarJugadorPorNombreInexistente() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        
        // Act
        Jugador jugadorEncontrado = gestorJugadores.buscarJugadorPorNombre("Jugador Inexistente");
        
        // Assert
        assertNull(jugadorEncontrado, "No debe encontrarse el jugador");
    }
    
    /**
     * Prueba buscar jugador por nombre nulo.
     */
    @Test
    @Order(12)
    @DisplayName("Buscar jugador por nombre nulo")
    void testBuscarJugadorPorNombreNulo() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        
        // Act
        Jugador jugadorEncontrado = gestorJugadores.buscarJugadorPorNombre(null);
        
        // Assert
        assertNull(jugadorEncontrado, "No debe encontrarse el jugador con nombre nulo");
    }
    
    /**
     * Prueba buscar jugador por nombre vacío.
     */
    @Test
    @Order(13)
    @DisplayName("Buscar jugador por nombre vacío")
    void testBuscarJugadorPorNombreVacio() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        
        // Act
        Jugador jugadorEncontrado = gestorJugadores.buscarJugadorPorNombre("");
        
        // Assert
        assertNull(jugadorEncontrado, "No debe encontrarse el jugador con nombre vacío");
    }
    
    /**
     * Prueba obtener el número de jugadores.
     */
    @Test
    @Order(14)
    @DisplayName("Obtener número de jugadores")
    void testGetNumeroJugadores() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        
        // Act
        int numeroJugadores = gestorJugadores.getNumeroJugadores();
        
        // Assert
        assertEquals(2, numeroJugadores, "Debe haber dos jugadores");
    }
    
    /**
     * Prueba obtener el número de jugadores en lista vacía.
     */
    @Test
    @Order(15)
    @DisplayName("Obtener número de jugadores en lista vacía")
    void testGetNumeroJugadoresVacia() {
        // Act
        int numeroJugadores = gestorJugadores.getNumeroJugadores();
        
        // Assert
        assertEquals(0, numeroJugadores, "No debe haber jugadores");
    }
    
    /**
     * Prueba verificar si existe un jugador.
     */
    @Test
    @Order(16)
    @DisplayName("Verificar existencia de jugador")
    void testExisteJugador() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        
        // Act & Assert
        assertTrue(gestorJugadores.existeJugador(jugadorTest1), "El jugador debe existir");
        assertFalse(gestorJugadores.existeJugador(jugadorTest2), "El jugador no debe existir");
        assertFalse(gestorJugadores.existeJugador(null), "Un jugador nulo no debe existir");
    }
    
    /**
     * Prueba limpiar todos los jugadores.
     */
    @Test
    @Order(17)
    @DisplayName("Limpiar todos los jugadores")
    void testLimpiarJugadores() {
        // Arrange
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        gestorJugadores.agregarJugador(jugadorTest3);
        
        // Act
        gestorJugadores.limpiarJugadores();
        
        // Assert
        assertEquals(0, gestorJugadores.getNumeroJugadores(), "No debe haber jugadores después de limpiar");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().isEmpty(), "La lista debe estar vacía");
    }
    
    /**
     * Prueba obtener jugadores con estadísticas.
     */
    @Test
    @Order(18)
    @DisplayName("Obtener jugadores con estadísticas")
    void testGetJugadoresConEstadisticas() {
        // Arrange
        jugadorTest1.agregarPuntos(10);
        jugadorTest1.incrementarIntentos();
        jugadorTest1.incrementarAciertos();
        
        jugadorTest2.agregarPuntos(5);
        jugadorTest2.incrementarIntentos();
        
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        
        // Act
        List<Jugador> jugadores = gestorJugadores.obtenerTodosLosJugadores();
        
        // Assert
        assertEquals(2, jugadores.size(), "Debe haber dos jugadores");
        assertEquals(10, jugadores.get(0).getPuntuacion(), "El primer jugador debe tener 10 puntos");
        assertEquals(1, jugadores.get(0).getIntentos(), "El primer jugador debe tener 1 intento");
        assertEquals(1, jugadores.get(0).getAciertos(), "El primer jugador debe tener 1 acierto");
        
        assertEquals(5, jugadores.get(1).getPuntuacion(), "El segundo jugador debe tener 5 puntos");
        assertEquals(1, jugadores.get(1).getIntentos(), "El segundo jugador debe tener 1 intento");
        assertEquals(0, jugadores.get(1).getAciertos(), "El segundo jugador debe tener 0 aciertos");
    }
    
    /**
     * Prueba obtener jugador con mayor puntuación.
     */
    @Test
    @Order(19)
    @DisplayName("Obtener jugador con mayor puntuación")
    void testGetJugadorConMayorPuntuacion() {
        // Arrange
        jugadorTest1.agregarPuntos(10);
        jugadorTest2.agregarPuntos(15);
        jugadorTest3.agregarPuntos(5);
        
        gestorJugadores.agregarJugador(jugadorTest1);
        gestorJugadores.agregarJugador(jugadorTest2);
        gestorJugadores.agregarJugador(jugadorTest3);
        
        // Act
        Jugador jugadorConMayorPuntuacion = gestorJugadores.getJugadorConMayorPuntuacion();
        
        // Assert
        assertNotNull(jugadorConMayorPuntuacion, "Debe existir un jugador con mayor puntuación");
        assertEquals(jugadorTest2, jugadorConMayorPuntuacion, "Debe ser el jugador con 15 puntos");
        assertEquals(15, jugadorConMayorPuntuacion.getPuntuacion(), "Debe tener 15 puntos");
    }
    
    /**
     * Prueba obtener jugador con mayor puntuación en lista vacía.
     */
    @Test
    @Order(20)
    @DisplayName("Obtener jugador con mayor puntuación en lista vacía")
    void testGetJugadorConMayorPuntuacionVacia() {
        // Act
        Jugador jugadorConMayorPuntuacion = gestorJugadores.getJugadorConMayorPuntuacion();
        
        // Assert
        assertNull(jugadorConMayorPuntuacion, "No debe existir jugador con mayor puntuación en lista vacía");
    }
}