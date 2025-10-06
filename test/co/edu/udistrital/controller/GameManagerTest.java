package co.edu.udistrital.controller;

import co.edu.udistrital.model.*;
import co.edu.udistrital.persistence.ArchivoAccesoAleatorio;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para GameManager.
 * Prueba todas las funcionalidades del controlador principal del juego.
 * 
 * @author Sansantax
 * @version 3.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameManagerTest {
    
    private GameManager gameManager;
    private ConfiguracionJuego configuracion;
    private Equipo equipoTest;
    private Jugador jugadorTest;
    
    /**
     * Configuración inicial antes de todas las pruebas.
     */
    @BeforeAll
    static void setUpAll() {
        System.out.println("=== INICIANDO PRUEBAS DE GAMEMANAGER ===");
    }
    
    /**
     * Limpieza después de todas las pruebas.
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("=== FINALIZANDO PRUEBAS DE GAMEMANAGER ===");
    }
    
    /**
     * Configuración antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        System.out.println("Configurando prueba...");
        gameManager = new GameManager();
        configuracion = new ConfiguracionJuego();
        equipoTest = new Equipo("Equipo Test", "Azul");
        jugadorTest = new Jugador("Jugador Test");
    }
    
    /**
     * Limpieza después de cada prueba.
     */
    @AfterEach
    void tearDown() {
        System.out.println("Limpiando después de prueba...");
        gameManager = null;
        configuracion = null;
        equipoTest = null;
        jugadorTest = null;
    }
    
    /**
     * Prueba la creación exitosa de un equipo.
     */
    @Test
    @Order(1)
    @DisplayName("Crear equipo válido")
    void testCrearEquipoValido() {
        // Arrange
        String nombre = "Equipo A";
        String color = "Rojo";
        
        // Act
        Equipo equipo = gameManager.crearEquipo(nombre, color);
        
        // Assert
        assertNotNull(equipo, "El equipo creado no debe ser null");
        assertEquals(nombre, equipo.getNombre(), "El nombre del equipo debe coincidir");
        assertEquals(color, equipo.getColor(), "El color del equipo debe coincidir");
    }
    
    /**
     * Prueba la creación de un equipo con nombre nulo.
     */
    @Test
    @Order(2)
    @DisplayName("Crear equipo con nombre nulo")
    void testCrearEquipoNombreNulo() {
        // Arrange
        String nombre = null;
        String color = "Verde";
        
        // Act
        Equipo equipo = gameManager.crearEquipo(nombre, color);
        
        // Assert
        assertNull(equipo, "El equipo con nombre nulo debe ser null");
    }
    
    /**
     * Prueba la creación de un equipo con nombre vacío.
     */
    @Test
    @Order(3)
    @DisplayName("Crear equipo con nombre vacío")
    void testCrearEquipoNombreVacio() {
        // Arrange
        String nombre = "";
        String color = "Amarillo";
        
        // Act
        Equipo equipo = gameManager.crearEquipo(nombre, color);
        
        // Assert
        assertNull(equipo, "El equipo con nombre vacío debe ser null");
    }
    
    /**
     * Prueba la creación de un equipo con color nulo.
     */
    @Test
    @Order(4)
    @DisplayName("Crear equipo con color nulo")
    void testCrearEquipoColorNulo() {
        // Arrange
        String nombre = "Equipo B";
        String color = null;
        
        // Act
        Equipo equipo = gameManager.crearEquipo(nombre, color);
        
        // Assert
        assertNotNull(equipo, "El equipo creado no debe ser null");
        assertEquals("Azul", equipo.getColor(), "El color por defecto debe ser Azul");
    }
    
    /**
     * Prueba la creación exitosa de un jugador.
     */
    @Test
    @Order(5)
    @DisplayName("Crear jugador válido")
    void testCrearJugadorValido() {
        // Arrange
        String nombre = "Jugador A";
        
        // Act
        Jugador jugador = gameManager.crearJugador(nombre);
        
        // Assert
        assertNotNull(jugador, "El jugador creado no debe ser null");
        assertEquals(nombre, jugador.getNombre(), "El nombre del jugador debe coincidir");
        assertEquals(0, jugador.getPuntuacion(), "La puntuación inicial debe ser 0");
        assertEquals(0, jugador.getIntentos(), "Los intentos iniciales deben ser 0");
        assertEquals(0, jugador.getAciertos(), "Los aciertos iniciales deben ser 0");
    }
    
    /**
     * Prueba la creación de un jugador con nombre nulo.
     */
    @Test
    @Order(6)
    @DisplayName("Crear jugador con nombre nulo")
    void testCrearJugadorNombreNulo() {
        // Arrange
        String nombre = null;
        
        // Act
        Jugador jugador = gameManager.crearJugador(nombre);
        
        // Assert
        assertNull(jugador, "El jugador con nombre nulo debe ser null");
    }
    
    /**
     * Prueba la creación de un jugador con nombre vacío.
     */
    @Test
    @Order(7)
    @DisplayName("Crear jugador con nombre vacío")
    void testCrearJugadorNombreVacio() {
        // Arrange
        String nombre = "   ";
        
        // Act
        Jugador jugador = gameManager.crearJugador(nombre);
        
        // Assert
        assertNull(jugador, "El jugador con nombre vacío debe ser null");
    }
    
    /**
     * Prueba la inicialización de una nueva partida con configuración válida.
     */
    @Test
    @Order(8)
    @DisplayName("Iniciar nueva partida con configuración válida")
    void testIniciarNuevaPartidaValida() {
        // Arrange
        configuracion.setMaxEquiposPorPartida(4);
        configuracion.setMaxJugadoresPorEquipo(4);
        configuracion.setMaxRondasPorPartida(10);
        configuracion.setPuntosParaGanar(100);
        
        // Act
        boolean resultado = gameManager.iniciarNuevaPartida(configuracion);
        
        // Assert
        assertTrue(resultado, "La partida debe iniciarse exitosamente");
        assertNotNull(gameManager.getPartidaActual(), "Debe existir una partida actual");
        assertEquals(configuracion.getMaxRondasPorPartida(), 
                    gameManager.getPartidaActual().getMaxRondas(), 
                    "Las rondas máximas deben coincidir");
    }
    
    /**
     * Prueba la inicialización de una nueva partida con configuración nula.
     */
    @Test
    @Order(9)
    @DisplayName("Iniciar nueva partida con configuración nula")
    void testIniciarNuevaPartidaConfiguracionNula() {
        // Arrange
        ConfiguracionJuego configNula = null;
        
        // Act
        boolean resultado = gameManager.iniciarNuevaPartida(configNula);
        
        // Assert
        assertFalse(resultado, "No debe ser posible iniciar partida con configuración nula");
    }
    
    /**
     * Prueba agregar un equipo a la partida.
     */
    @Test
    @Order(10)
    @DisplayName("Agregar equipo a la partida")
    void testAgregarEquipoAPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        
        // Act
        boolean resultado = gameManager.agregarEquipo(equipoTest);
        
        // Assert
        assertTrue(resultado, "El equipo debe agregarse exitosamente");
        assertEquals(1, gameManager.getEquipos().size(), "Debe haber un equipo en la partida");
    }
    
    /**
     * Prueba agregar un equipo sin partida activa.
     */
    @Test
    @Order(11)
    @DisplayName("Agregar equipo sin partida activa")
    void testAgregarEquipoSinPartida() {
        // Act
        boolean resultado = gameManager.agregarEquipo(equipoTest);
        
        // Assert
        assertFalse(resultado, "No debe ser posible agregar equipo sin partida activa");
    }
    
    /**
     * Prueba agregar un jugador a un equipo.
     */
    @Test
    @Order(12)
    @DisplayName("Agregar jugador a equipo")
    void testAgregarJugadorAEquipo() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        
        // Act
        boolean resultado = gameManager.agregarJugadorAEquipo(equipoTest, jugadorTest);
        
        // Assert
        assertTrue(resultado, "El jugador debe agregarse exitosamente al equipo");
        assertEquals(1, equipoTest.getNumeroJugadores(), "El equipo debe tener un jugador");
    }
    
    /**
     * Prueba registrar un intento exitoso.
     */
    @Test
    @Order(13)
    @DisplayName("Registrar intento exitoso")
    void testRegistrarIntentoExitoso() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        gameManager.agregarJugadorAEquipo(equipoTest, jugadorTest);
        gameManager.iniciarPartida();
        
        // Act
        int puntos = gameManager.registrarIntento(jugadorTest, true);
        
        // Assert
        assertEquals(configuracion.getPuntosPorAcierto(), puntos, "Los puntos deben coincidir");
        assertEquals(1, jugadorTest.getIntentos(), "Debe incrementarse el número de intentos");
        assertEquals(1, jugadorTest.getAciertos(), "Debe incrementarse el número de aciertos");
        assertEquals(configuracion.getPuntosPorAcierto(), jugadorTest.getPuntuacion(), 
                    "La puntuación debe incrementarse");
    }
    
    /**
     * Prueba registrar un intento fallido.
     */
    @Test
    @Order(14)
    @DisplayName("Registrar intento fallido")
    void testRegistrarIntentoFallido() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        gameManager.agregarJugadorAEquipo(equipoTest, jugadorTest);
        gameManager.iniciarPartida();
        
        // Act
        int puntos = gameManager.registrarIntento(jugadorTest, false);
        
        // Assert
        assertEquals(configuracion.getPuntosPorIntento(), puntos, "Los puntos deben coincidir");
        assertEquals(1, jugadorTest.getIntentos(), "Debe incrementarse el número de intentos");
        assertEquals(0, jugadorTest.getAciertos(), "No debe incrementarse el número de aciertos");
        assertEquals(configuracion.getPuntosPorIntento(), jugadorTest.getPuntuacion(), 
                    "La puntuación debe incrementarse");
    }
    
    /**
     * Prueba avanzar de ronda.
     */
    @Test
    @Order(15)
    @DisplayName("Avanzar ronda")
    void testAvanzarRonda() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        gameManager.iniciarPartida();
        
        // Act
        boolean resultado = gameManager.avanzarRonda();
        
        // Assert
        assertTrue(resultado, "Debe ser posible avanzar de ronda");
        assertEquals(2, gameManager.getPartidaActual().getRondaActual(), 
                    "La ronda actual debe incrementarse");
    }
    
    /**
     * Prueba obtener estadísticas de la partida.
     */
    @Test
    @Order(16)
    @DisplayName("Obtener estadísticas de la partida")
    void testObtenerEstadisticasPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        
        // Act
        String estadisticas = gameManager.obtenerEstadisticasPartida();
        
        // Assert
        assertNotNull(estadisticas, "Las estadísticas no deben ser null");
        assertTrue(estadisticas.contains("ESTADÍSTICAS"), "Debe contener el título de estadísticas");
        assertTrue(estadisticas.contains("EQUIPOS"), "Debe contener información de equipos");
    }
    
    /**
     * Prueba obtener estadísticas sin partida.
     */
    @Test
    @Order(17)
    @DisplayName("Obtener estadísticas sin partida")
    void testObtenerEstadisticasSinPartida() {
        // Act
        String estadisticas = gameManager.obtenerEstadisticasPartida();
        
        // Assert
        assertNotNull(estadisticas, "Las estadísticas no deben ser null");
        assertEquals("No hay partida activa", estadisticas, "Debe mostrar mensaje de no partida activa");
    }
    
    /**
     * Prueba finalizar partida.
     */
    @Test
    @Order(18)
    @DisplayName("Finalizar partida")
    void testFinalizarPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        gameManager.iniciarPartida();
        
        // Act
        gameManager.finalizarPartida();
        
        // Assert
        assertTrue(gameManager.haTerminadoPartida(), "La partida debe estar terminada");
        assertFalse(gameManager.estaPartidaEnCurso(), "La partida no debe estar en curso");
    }
    
    /**
     * Prueba el gestor de archivos de acceso aleatorio.
     */
    @Test
    @Order(19)
    @DisplayName("Obtener gestor de archivos de acceso aleatorio")
    void testGetArchivoAccesoAleatorio() {
        // Act
        ArchivoAccesoAleatorio archivo = gameManager.getArchivoAccesoAleatorio();
        
        // Assert
        assertNotNull(archivo, "El gestor de archivos no debe ser null");
    }
    
    /**
     * Prueba guardar datos completos.
     */
    @Test
    @Order(20)
    @DisplayName("Guardar datos completos")
    void testGuardarDatosCompletos() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipoTest);
        gameManager.agregarJugadorAEquipo(equipoTest, jugadorTest);
        
        // Act
        boolean resultado = gameManager.guardarDatosCompletos();
        
        // Assert
        assertTrue(resultado, "Los datos deben guardarse exitosamente");
    }
}