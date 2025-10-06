package co.edu.udistrital.controller;

import co.edu.udistrital.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Pruebas unitarias para la clase GameManager.
 * Utiliza JUnit 5 con las anotaciones requeridas.
 * 
 * @author Sansantax
 * @version 1.0
 */
@DisplayName("Pruebas del GameManager")
public class GameManagerTest {
    
    private static GameManager gameManager;
    private static ConfiguracionJuego configuracion;
    private static Equipo equipo1;
    private static Equipo equipo2;
    private static Jugador jugador1;
    private static Jugador jugador2;
    
    @BeforeAll
    static void setUpClass() {
        // Configuración inicial para todas las pruebas
        configuracion = new ConfiguracionJuego();
        configuracion.setMaxJugadoresPorEquipo(2);
        configuracion.setMaxEquiposPorPartida(2);
        configuracion.setMaxRondasPorPartida(5);
        configuracion.setPuntosParaGanar(50);
        configuracion.setPuntosPorAcierto(10);
        configuracion.setPuntosPorIntento(1);
        
        // Crear equipos y jugadores de prueba
        equipo1 = new Equipo("Equipo Rojo", "Rojo");
        equipo2 = new Equipo("Equipo Azul", "Azul");
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
    }
    
    @AfterAll
    static void tearDownClass() {
        // Limpieza final después de todas las pruebas
        gameManager = null;
        configuracion = null;
        equipo1 = null;
        equipo2 = null;
        jugador1 = null;
        jugador2 = null;
    }
    
    @BeforeEach
    void setUp() {
        // Configuración antes de cada prueba
        gameManager = new GameManager();
        gameManager.setConfiguracion(configuracion);
    }
    
    @AfterEach
    void tearDown() {
        // Limpieza después de cada prueba
        gameManager = null;
    }
    
    @Test
    @DisplayName("Debería inicializar una nueva partida exitosamente")
    void testIniciarNuevaPartida() {
        // Arrange & Act
        boolean resultado = gameManager.iniciarNuevaPartida(configuracion);
        
        // Assert
        assertTrue(resultado, "Debería poder iniciar una nueva partida");
        assertNotNull(gameManager.getPartidaActual(), "Debería tener una partida actual");
        assertEquals(Partida.EstadoPartida.PREPARACION, 
                    gameManager.getPartidaActual().getEstado(), 
                    "La partida debería estar en preparación");
    }
    
    @Test
    @DisplayName("No debería inicializar partida con configuración nula")
    void testIniciarNuevaPartidaConConfiguracionNula() {
        // Arrange & Act
        boolean resultado = gameManager.iniciarNuevaPartida(null);
        
        // Assert
        assertFalse(resultado, "No debería poder iniciar partida con configuración nula");
        assertNull(gameManager.getPartidaActual(), "No debería tener partida actual");
    }
    
    @Test
    @DisplayName("Debería agregar equipo a la partida")
    void testAgregarEquipo() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        
        // Act
        boolean resultado = gameManager.agregarEquipo(equipo1);
        
        // Assert
        assertTrue(resultado, "Debería poder agregar equipo");
        assertEquals(1, gameManager.getEquipos().size(), "Debería tener un equipo");
        assertTrue(gameManager.getEquipos().contains(equipo1), "Debería contener el equipo agregado");
    }
    
    @Test
    @DisplayName("No debería agregar equipo sin partida activa")
    void testAgregarEquipoSinPartida() {
        // Act
        boolean resultado = gameManager.agregarEquipo(equipo1);
        
        // Assert
        assertFalse(resultado, "No debería poder agregar equipo sin partida");
    }
    
    @Test
    @DisplayName("No debería agregar equipo nulo")
    void testAgregarEquipoNulo() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        
        // Act
        boolean resultado = gameManager.agregarEquipo(null);
        
        // Assert
        assertFalse(resultado, "No debería poder agregar equipo nulo");
    }
    
    @Test
    @DisplayName("Debería remover equipo de la partida")
    void testRemoverEquipo() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        
        // Act
        boolean resultado = gameManager.removerEquipo(equipo1);
        
        // Assert
        assertTrue(resultado, "Debería poder remover equipo");
        assertEquals(0, gameManager.getEquipos().size(), "No debería tener equipos");
    }
    
    @Test
    @DisplayName("Debería agregar jugador a equipo")
    void testAgregarJugadorAEquipo() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        
        // Act
        boolean resultado = gameManager.agregarJugadorAEquipo(equipo1, jugador1);
        
        // Assert
        assertTrue(resultado, "Debería poder agregar jugador a equipo");
        assertTrue(equipo1.getJugadores().contains(jugador1), "El equipo debería contener el jugador");
    }
    
    @Test
    @DisplayName("Debería remover jugador de equipo")
    void testRemoverJugadorDeEquipo() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarJugadorAEquipo(equipo1, jugador1);
        
        // Act
        boolean resultado = gameManager.removerJugadorDeEquipo(equipo1, jugador1);
        
        // Assert
        assertTrue(resultado, "Debería poder remover jugador de equipo");
        assertFalse(equipo1.getJugadores().contains(jugador1), "El equipo no debería contener el jugador");
    }
    
    @Test
    @DisplayName("Debería iniciar partida con equipos suficientes")
    void testIniciarPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarEquipo(equipo2);
        
        // Act
        boolean resultado = gameManager.iniciarPartida();
        
        // Assert
        assertTrue(resultado, "Debería poder iniciar partida con equipos suficientes");
        assertTrue(gameManager.estaPartidaEnCurso(), "La partida debería estar en curso");
    }
    
    @Test
    @DisplayName("No debería iniciar partida sin equipos suficientes")
    void testIniciarPartidaSinEquiposSuficientes() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        
        // Act
        boolean resultado = gameManager.iniciarPartida();
        
        // Assert
        assertFalse(resultado, "No debería poder iniciar partida sin equipos suficientes");
        assertFalse(gameManager.estaPartidaEnCurso(), "La partida no debería estar en curso");
    }
    
    @Test
    @DisplayName("Debería finalizar partida en curso")
    void testFinalizarPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarEquipo(equipo2);
        gameManager.iniciarPartida();
        
        // Act
        gameManager.finalizarPartida();
        
        // Assert
        assertTrue(gameManager.haTerminadoPartida(), "La partida debería haber terminado");
        assertFalse(gameManager.estaPartidaEnCurso(), "La partida no debería estar en curso");
    }
    
    @Test
    @DisplayName("Debería registrar intento de jugador")
    void testRegistrarIntento() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarJugadorAEquipo(equipo1, jugador1);
        gameManager.iniciarPartida();
        
        // Act
        int puntosObtenidos = gameManager.registrarIntento(jugador1, true);
        
        // Assert
        assertEquals(configuracion.getPuntosPorAcierto(), puntosObtenidos, 
                    "Debería otorgar puntos por acierto");
        assertEquals(1, jugador1.getIntentos(), "Debería incrementar intentos");
        assertEquals(1, jugador1.getAciertos(), "Debería incrementar aciertos");
        assertEquals(configuracion.getPuntosPorAcierto(), jugador1.getPuntuacion(), 
                    "Debería actualizar puntuación");
    }
    
    @Test
    @DisplayName("Debería registrar intento fallido")
    void testRegistrarIntentoFallido() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarJugadorAEquipo(equipo1, jugador1);
        gameManager.iniciarPartida();
        
        // Act
        int puntosObtenidos = gameManager.registrarIntento(jugador1, false);
        
        // Assert
        assertEquals(configuracion.getPuntosPorIntento(), puntosObtenidos, 
                    "Debería otorgar puntos por intento");
        assertEquals(1, jugador1.getIntentos(), "Debería incrementar intentos");
        assertEquals(0, jugador1.getAciertos(), "No debería incrementar aciertos");
        assertEquals(configuracion.getPuntosPorIntento(), jugador1.getPuntuacion(), 
                    "Debería actualizar puntuación");
    }
    
    @Test
    @DisplayName("Debería avanzar ronda")
    void testAvanzarRonda() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarEquipo(equipo2);
        gameManager.iniciarPartida();
        
        // Act
        boolean resultado = gameManager.avanzarRonda();
        
        // Assert
        assertTrue(resultado, "Debería poder avanzar ronda");
        assertEquals(2, gameManager.getPartidaActual().getRondaActual(), 
                    "Debería incrementar ronda actual");
    }
    
    @Test
    @DisplayName("Debería obtener equipo ganador")
    void testObtenerEquipoGanador() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarEquipo(equipo2);
        gameManager.agregarJugadorAEquipo(equipo1, jugador1);
        gameManager.agregarJugadorAEquipo(equipo2, jugador2);
        gameManager.iniciarPartida();
        
        // Hacer que el equipo1 gane
        for (int i = 0; i < 6; i++) {
            gameManager.registrarIntento(jugador1, true);
        }
        
        // Act
        Equipo ganador = gameManager.getEquipoGanador();
        
        // Assert
        assertNotNull(ganador, "Debería haber un equipo ganador");
        assertEquals(equipo1, ganador, "El equipo ganador debería ser el equipo1");
    }
    
    @Test
    @DisplayName("Debería obtener estadísticas de la partida")
    void testObtenerEstadisticasPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarJugadorAEquipo(equipo1, jugador1);
        
        // Act
        String estadisticas = gameManager.obtenerEstadisticasPartida();
        
        // Assert
        assertNotNull(estadisticas, "Las estadísticas no deberían ser nulas");
        assertFalse(estadisticas.isEmpty(), "Las estadísticas no deberían estar vacías");
        assertTrue(estadisticas.contains("ESTADÍSTICAS"), "Debería contener información de estadísticas");
    }
    
    @Test
    @DisplayName("Debería verificar estado de partida en curso")
    void testEstaPartidaEnCurso() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarEquipo(equipo2);
        
        // Act & Assert - Sin iniciar
        assertFalse(gameManager.estaPartidaEnCurso(), "No debería estar en curso sin iniciar");
        
        // Act & Assert - Iniciada
        gameManager.iniciarPartida();
        assertTrue(gameManager.estaPartidaEnCurso(), "Debería estar en curso después de iniciar");
    }
    
    @Test
    @DisplayName("Debería verificar si partida ha terminado")
    void testHaTerminadoPartida() {
        // Arrange
        gameManager.iniciarNuevaPartida(configuracion);
        gameManager.agregarEquipo(equipo1);
        gameManager.agregarEquipo(equipo2);
        
        // Act & Assert - Sin iniciar
        assertFalse(gameManager.haTerminadoPartida(), "No debería haber terminado sin iniciar");
        
        // Act & Assert - Iniciada
        gameManager.iniciarPartida();
        assertFalse(gameManager.haTerminadoPartida(), "No debería haber terminado recién iniciada");
        
        // Act & Assert - Finalizada
        gameManager.finalizarPartida();
        assertTrue(gameManager.haTerminadoPartida(), "Debería haber terminado después de finalizar");
    }
}
