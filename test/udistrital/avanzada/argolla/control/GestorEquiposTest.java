package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.Equipo;
import udistrital.avanzada.argolla.modelo.Jugador;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para GestorEquipos.
 * Prueba todas las funcionalidades del gestor de equipos.
 * 
 * @author Sansantax
 * @version 3.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GestorEquiposTest {
    
    private GestorEquipos gestorEquipos;
    private Equipo equipoTest1;
    private Equipo equipoTest2;
    private Jugador jugadorTest1;
    private Jugador jugadorTest2;
    
    /**
     * Configuración inicial antes de todas las pruebas.
     */
    @BeforeAll
    static void setUpAll() {
        System.out.println("=== INICIANDO PRUEBAS DE GESTOREQUIPOS ===");
    }
    
    /**
     * Limpieza después de todas las pruebas.
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("=== FINALIZANDO PRUEBAS DE GESTOREQUIPOS ===");
    }
    
    /**
     * Configuración antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        System.out.println("Configurando prueba...");
        gestorEquipos = new GestorEquipos();
        equipoTest1 = new Equipo("Equipo A", "Azul");
        equipoTest2 = new Equipo("Equipo B", "Rojo");
        jugadorTest1 = new Jugador("Jugador 1");
        jugadorTest2 = new Jugador("Jugador 2");
    }
    
    /**
     * Limpieza después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        System.out.println("Limpiando después de prueba...");
        gestorEquipos = null;
        equipoTest1 = null;
        equipoTest2 = null;
        jugadorTest1 = null;
        jugadorTest2 = null;
    }
    
    /**
     * Prueba agregar un equipo válido.
     */
    @Test
    @Order(1)
    @DisplayName("Agregar equipo válido")
    void testAgregarEquipoValido() {
        // Act
        boolean resultado = gestorEquipos.agregarEquipo(equipoTest1);
        
        // Assert
        assertTrue(resultado, "El equipo debe agregarse exitosamente");
        assertEquals(1, gestorEquipos.obtenerTodosLosEquipos().size(), "Debe haber un equipo en la lista");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipoTest1), "El equipo debe estar en la lista");
    }
    
    /**
     * Prueba agregar un equipo nulo.
     */
    @Test
    @Order(2)
    @DisplayName("Agregar equipo nulo")
    void testAgregarEquipoNulo() {
        // Arrange
        Equipo equipoNulo = null;
        
        // Act
        boolean resultado = gestorEquipos.agregarEquipo(equipoNulo);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar un equipo nulo");
        assertEquals(0, gestorEquipos.obtenerTodosLosEquipos().size(), "No debe haber equipos en la lista");
    }
    
    /**
     * Prueba agregar el mismo equipo dos veces.
     */
    @Test
    @Order(3)
    @DisplayName("Agregar mismo equipo dos veces")
    void testAgregarMismoEquipoDosVeces() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        
        // Act
        boolean resultado = gestorEquipos.agregarEquipo(equipoTest1);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar el mismo equipo dos veces");
        assertEquals(1, gestorEquipos.obtenerTodosLosEquipos().size(), "Debe haber solo un equipo en la lista");
    }
    
    /**
     * Prueba agregar múltiples equipos.
     */
    @Test
    @Order(4)
    @DisplayName("Agregar múltiples equipos")
    void testAgregarMultiplesEquipos() {
        // Act
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Assert
        assertEquals(2, gestorEquipos.obtenerTodosLosEquipos().size(), "Debe haber dos equipos en la lista");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipoTest1), "El primer equipo debe estar en la lista");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipoTest2), "El segundo equipo debe estar en la lista");
    }
    
    /**
     * Prueba remover un equipo existente.
     */
    @Test
    @Order(5)
    @DisplayName("Remover equipo existente")
    void testRemoverEquipoExistente() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Act
        boolean resultado = gestorEquipos.removerEquipo(equipoTest1);
        
        // Assert
        assertTrue(resultado, "El equipo debe removerse exitosamente");
        assertEquals(1, gestorEquipos.obtenerTodosLosEquipos().size(), "Debe quedar un equipo en la lista");
        assertFalse(gestorEquipos.obtenerTodosLosEquipos().contains(equipoTest1), "El primer equipo no debe estar en la lista");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipoTest2), "El segundo equipo debe seguir en la lista");
    }
    
    /**
     * Prueba remover un equipo inexistente.
     */
    @Test
    @Order(6)
    @DisplayName("Remover equipo inexistente")
    void testRemoverEquipoInexistente() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        
        // Act
        boolean resultado = gestorEquipos.removerEquipo(equipoTest2);
        
        // Assert
        assertFalse(resultado, "No debe ser posible remover un equipo inexistente");
        assertEquals(1, gestorEquipos.obtenerTodosLosEquipos().size(), "Debe seguir habiendo un equipo en la lista");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipoTest1), "El equipo original debe seguir en la lista");
    }
    
    /**
     * Prueba remover un equipo nulo.
     */
    @Test
    @Order(7)
    @DisplayName("Remover equipo nulo")
    void testRemoverEquipoNulo() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        Equipo equipoNulo = null;
        
        // Act
        boolean resultado = gestorEquipos.removerEquipo(equipoNulo);
        
        // Assert
        assertFalse(resultado, "No debe ser posible remover un equipo nulo");
        assertEquals(1, gestorEquipos.obtenerTodosLosEquipos().size(), "Debe seguir habiendo un equipo en la lista");
    }
    
    /**
     * Prueba obtener la lista de equipos.
     */
    @Test
    @Order(8)
    @DisplayName("Obtener lista de equipos")
    void testGetEquipos() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Act
        List<Equipo> equipos = gestorEquipos.obtenerTodosLosEquipos();
        
        // Assert
        assertNotNull(equipos, "La lista de equipos no debe ser null");
        assertEquals(2, equipos.size(), "Debe haber dos equipos en la lista");
        assertTrue(equipos.contains(equipoTest1), "El primer equipo debe estar en la lista");
        assertTrue(equipos.contains(equipoTest2), "El segundo equipo debe estar en la lista");
    }
    
    /**
     * Prueba obtener lista de equipos vacía.
     */
    @Test
    @Order(9)
    @DisplayName("Obtener lista de equipos vacía")
    void testGetEquiposVacia() {
        // Act
        List<Equipo> equipos = gestorEquipos.obtenerTodosLosEquipos();
        
        // Assert
        assertNotNull(equipos, "La lista de equipos no debe ser null");
        assertTrue(equipos.isEmpty(), "La lista de equipos debe estar vacía");
    }
    
    /**
     * Prueba buscar equipo por nombre existente.
     */
    @Test
    @Order(10)
    @DisplayName("Buscar equipo por nombre existente")
    void testBuscarEquipoPorNombreExistente() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorNombre("Equipo A");
        
        // Assert
        assertNotNull(equipoEncontrado, "El equipo debe encontrarse");
        assertEquals(equipoTest1, equipoEncontrado, "Debe ser el mismo equipo");
        assertEquals("Equipo A", equipoEncontrado.getNombre(), "El nombre debe coincidir");
    }
    
    /**
     * Prueba buscar equipo por nombre inexistente.
     */
    @Test
    @Order(11)
    @DisplayName("Buscar equipo por nombre inexistente")
    void testBuscarEquipoPorNombreInexistente() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorNombre("Equipo Inexistente");
        
        // Assert
        assertNull(equipoEncontrado, "No debe encontrarse el equipo");
    }
    
    /**
     * Prueba buscar equipo por nombre nulo.
     */
    @Test
    @Order(12)
    @DisplayName("Buscar equipo por nombre nulo")
    void testBuscarEquipoPorNombreNulo() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorNombre(null);
        
        // Assert
        assertNull(equipoEncontrado, "No debe encontrarse el equipo con nombre nulo");
    }
    
    /**
     * Prueba buscar equipo por nombre vacío.
     */
    @Test
    @Order(13)
    @DisplayName("Buscar equipo por nombre vacío")
    void testBuscarEquipoPorNombreVacio() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorNombre("");
        
        // Assert
        assertNull(equipoEncontrado, "No debe encontrarse el equipo con nombre vacío");
    }
    
    /**
     * Prueba obtener el número de equipos.
     */
    @Test
    @Order(14)
    @DisplayName("Obtener número de equipos")
    void testGetNumeroEquipos() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Act
        int numeroEquipos = gestorEquipos.getNumeroEquipos();
        
        // Assert
        assertEquals(2, numeroEquipos, "Debe haber dos equipos");
    }
    
    /**
     * Prueba obtener el número de equipos en lista vacía.
     */
    @Test
    @Order(15)
    @DisplayName("Obtener número de equipos en lista vacía")
    void testGetNumeroEquiposVacia() {
        // Act
        int numeroEquipos = gestorEquipos.getNumeroEquipos();
        
        // Assert
        assertEquals(0, numeroEquipos, "No debe haber equipos");
    }
    
    /**
     * Prueba verificar si existe un equipo.
     */
    @Test
    @Order(16)
    @DisplayName("Verificar existencia de equipo")
    void testExisteEquipo() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        
        // Act & Assert
        assertTrue(gestorEquipos.existeEquipo(equipoTest1), "El equipo debe existir");
        assertFalse(gestorEquipos.existeEquipo(equipoTest2), "El equipo no debe existir");
        assertFalse(gestorEquipos.existeEquipo(null), "Un equipo nulo no debe existir");
    }
    
    /**
     * Prueba limpiar todos los equipos.
     */
    @Test
    @Order(17)
    @DisplayName("Limpiar todos los equipos")
    void testLimpiarEquipos() {
        // Arrange
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Act
        gestorEquipos.limpiarEquipos();
        
        // Assert
        assertEquals(0, gestorEquipos.getNumeroEquipos(), "No debe haber equipos después de limpiar");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().isEmpty(), "La lista debe estar vacía");
    }
    
    /**
     * Prueba obtener equipos con jugadores.
     */
    @Test
    @Order(18)
    @DisplayName("Obtener equipos con jugadores")
    void testGetEquiposConJugadores() {
        // Arrange
        equipoTest1.agregarJugador(jugadorTest1);
        equipoTest1.agregarJugador(jugadorTest2);
        gestorEquipos.agregarEquipo(equipoTest1);
        gestorEquipos.agregarEquipo(equipoTest2);
        
        // Act
        List<Equipo> equipos = gestorEquipos.obtenerTodosLosEquipos();
        
        // Assert
        assertEquals(2, equipos.size(), "Debe haber dos equipos");
        assertEquals(2, equipos.get(0).getNumeroJugadores(), "El primer equipo debe tener dos jugadores");
        assertEquals(0, equipos.get(1).getNumeroJugadores(), "El segundo equipo no debe tener jugadores");
    }
}