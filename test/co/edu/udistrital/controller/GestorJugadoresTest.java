package co.edu.udistrital.controller;

import co.edu.udistrital.model.Jugador;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Pruebas unitarias para la clase GestorJugadores.
 * Utiliza JUnit 5 con las anotaciones requeridas.
 * 
 * @author Sistema Juego de la Argolla
 * @version 1.0
 */
@DisplayName("Pruebas del GestorJugadores")
public class GestorJugadoresTest {
    
    private static GestorJugadores gestorJugadores;
    private static Jugador jugador1;
    private static Jugador jugador2;
    private static Jugador jugador3;
    
    @BeforeAll
    static void setUpClass() {
        // Configuración inicial para todas las pruebas
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
        jugador3 = new Jugador("Jugador 3");
    }
    
    @AfterAll
    static void tearDownClass() {
        // Limpieza final después de todas las pruebas
        gestorJugadores = null;
        jugador1 = null;
        jugador2 = null;
        jugador3 = null;
    }
    
    @BeforeEach
    void setUp() {
        // Configuración antes de cada prueba
        gestorJugadores = new GestorJugadores();
    }
    
    @AfterEach
    void tearDown() {
        // Limpieza después de cada prueba
        gestorJugadores = null;
    }
    
    @Test
    @DisplayName("Debería crear jugador con nombre válido")
    void testCrearJugador() {
        // Arrange
        String nombre = "Nuevo Jugador";
        
        // Act
        Jugador jugador = gestorJugadores.crearJugador(nombre);
        
        // Assert
        assertNotNull(jugador, "El jugador no debería ser nulo");
        assertEquals(nombre, jugador.getNombre(), "El nombre debería coincidir");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugador), 
                  "El jugador debería estar en la lista");
    }
    
    @Test
    @DisplayName("No debería crear jugador con nombre nulo")
    void testCrearJugadorConNombreNulo() {
        // Act
        Jugador jugador = gestorJugadores.crearJugador(null);
        
        // Assert
        assertNull(jugador, "No debería crear jugador con nombre nulo");
    }
    
    @Test
    @DisplayName("No debería crear jugador con nombre vacío")
    void testCrearJugadorConNombreVacio() {
        // Act
        Jugador jugador = gestorJugadores.crearJugador("");
        
        // Assert
        assertNull(jugador, "No debería crear jugador con nombre vacío");
    }
    
    @Test
    @DisplayName("No debería crear jugador con nombre solo espacios")
    void testCrearJugadorConNombreSoloEspacios() {
        // Act
        Jugador jugador = gestorJugadores.crearJugador("   ");
        
        // Assert
        assertNull(jugador, "No debería crear jugador con nombre solo espacios");
    }
    
    @Test
    @DisplayName("Debería agregar jugador a la lista")
    void testAgregarJugador() {
        // Act
        boolean resultado = gestorJugadores.agregarJugador(jugador1);
        
        // Assert
        assertTrue(resultado, "Debería poder agregar jugador");
        assertEquals(1, gestorJugadores.getNumeroJugadores(), "Debería tener un jugador");
        assertTrue(gestorJugadores.obtenerTodosLosJugadores().contains(jugador1), 
                  "Debería contener el jugador agregado");
    }
    
    @Test
    @DisplayName("No debería agregar jugador nulo")
    void testAgregarJugadorNulo() {
        // Act
        boolean resultado = gestorJugadores.agregarJugador(null);
        
        // Assert
        assertFalse(resultado, "No debería poder agregar jugador nulo");
        assertEquals(0, gestorJugadores.getNumeroJugadores(), "No debería tener jugadores");
    }
    
    @Test
    @DisplayName("No debería agregar jugador duplicado")
    void testAgregarJugadorDuplicado() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        
        // Act
        boolean resultado = gestorJugadores.agregarJugador(jugador1);
        
        // Assert
        assertFalse(resultado, "No debería poder agregar jugador duplicado");
        assertEquals(1, gestorJugadores.getNumeroJugadores(), "Debería tener solo un jugador");
    }
    
    @Test
    @DisplayName("Debería remover jugador de la lista")
    void testRemoverJugador() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        
        // Act
        boolean resultado = gestorJugadores.removerJugador(jugador1);
        
        // Assert
        assertTrue(resultado, "Debería poder remover jugador");
        assertEquals(0, gestorJugadores.getNumeroJugadores(), "No debería tener jugadores");
        assertFalse(gestorJugadores.obtenerTodosLosJugadores().contains(jugador1), 
                   "No debería contener el jugador removido");
    }
    
    @Test
    @DisplayName("No debería remover jugador que no existe")
    void testRemoverJugadorInexistente() {
        // Act
        boolean resultado = gestorJugadores.removerJugador(jugador1);
        
        // Assert
        assertFalse(resultado, "No debería poder remover jugador inexistente");
    }
    
    @Test
    @DisplayName("Debería buscar jugador por nombre")
    void testBuscarJugadorPorNombre() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        // Act
        Jugador jugadorEncontrado = gestorJugadores.buscarJugadorPorNombre("Jugador 1");
        
        // Assert
        assertNotNull(jugadorEncontrado, "Debería encontrar el jugador");
        assertEquals(jugador1, jugadorEncontrado, "Debería ser el jugador correcto");
    }
    
    @Test
    @DisplayName("No debería encontrar jugador con nombre inexistente")
    void testBuscarJugadorPorNombreInexistente() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        
        // Act
        Jugador jugadorEncontrado = gestorJugadores.buscarJugadorPorNombre("Jugador Inexistente");
        
        // Assert
        assertNull(jugadorEncontrado, "No debería encontrar jugador inexistente");
    }
    
    @Test
    @DisplayName("Debería verificar existencia de jugador")
    void testExisteJugador() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        
        // Act & Assert
        assertTrue(gestorJugadores.existeJugador("Jugador 1"), 
                  "Debería existir el jugador agregado");
        assertFalse(gestorJugadores.existeJugador("Jugador Inexistente"), 
                   "No debería existir jugador no agregado");
    }
    
    @Test
    @DisplayName("Debería ordenar jugadores por puntuación")
    void testObtenerJugadoresOrdenadosPorPuntuacion() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        gestorJugadores.agregarJugador(jugador3);
        
        jugador1.agregarPuntos(100);
        jugador2.agregarPuntos(200);
        jugador3.agregarPuntos(50);
        
        // Act
        List<Jugador> jugadoresOrdenados = gestorJugadores.obtenerJugadoresOrdenadosPorPuntuacion();
        
        // Assert
        assertEquals(3, jugadoresOrdenados.size(), "Debería tener tres jugadores");
        assertEquals(jugador2, jugadoresOrdenados.get(0), "El primer jugador debería tener mayor puntuación");
        assertEquals(jugador1, jugadoresOrdenados.get(1), "El segundo jugador debería tener puntuación media");
        assertEquals(jugador3, jugadoresOrdenados.get(2), "El tercer jugador debería tener menor puntuación");
    }
    
    @Test
    @DisplayName("Debería ordenar jugadores por porcentaje de aciertos")
    void testObtenerJugadoresOrdenadosPorAciertos() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        // Configurar estadísticas
        jugador1.incrementarIntentos();
        jugador1.incrementarIntentos();
        jugador1.incrementarAciertos(); // 50% de aciertos
        
        jugador2.incrementarIntentos();
        jugador2.incrementarAciertos(); // 100% de aciertos
        
        // Act
        List<Jugador> jugadoresOrdenados = gestorJugadores.obtenerJugadoresOrdenadosPorAciertos();
        
        // Assert
        assertEquals(2, jugadoresOrdenados.size(), "Debería tener dos jugadores");
        assertEquals(jugador2, jugadoresOrdenados.get(0), "El primer jugador debería tener mejor porcentaje");
        assertEquals(jugador1, jugadoresOrdenados.get(1), "El segundo jugador debería tener menor porcentaje");
    }
    
    @Test
    @DisplayName("Debería obtener jugador con mayor puntuación")
    void testObtenerJugadorConMayorPuntuacion() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        jugador1.agregarPuntos(100);
        jugador2.agregarPuntos(200);
        
        // Act
        Jugador jugadorConMayorPuntuacion = gestorJugadores.obtenerJugadorConMayorPuntuacion();
        
        // Assert
        assertNotNull(jugadorConMayorPuntuacion, "Debería encontrar un jugador");
        assertEquals(jugador2, jugadorConMayorPuntuacion, "Debería ser el jugador con mayor puntuación");
    }
    
    @Test
    @DisplayName("Debería obtener jugador con mejor porcentaje de aciertos")
    void testObtenerJugadorConMejorAciertos() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        // Configurar estadísticas
        jugador1.incrementarIntentos();
        jugador1.incrementarAciertos(); // 100% de aciertos
        
        jugador2.incrementarIntentos();
        jugador2.incrementarIntentos();
        jugador2.incrementarAciertos(); // 50% de aciertos
        
        // Act
        Jugador jugadorConMejorAciertos = gestorJugadores.obtenerJugadorConMejorAciertos();
        
        // Assert
        assertNotNull(jugadorConMejorAciertos, "Debería encontrar un jugador");
        assertEquals(jugador1, jugadorConMejorAciertos, "Debería ser el jugador con mejor porcentaje");
    }
    
    @Test
    @DisplayName("Debería filtrar jugadores por puntuación mínima")
    void testObtenerJugadoresConPuntuacionMayorA() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        gestorJugadores.agregarJugador(jugador3);
        
        jugador1.agregarPuntos(50);
        jugador2.agregarPuntos(150);
        jugador3.agregarPuntos(200);
        
        // Act
        List<Jugador> jugadoresFiltrados = gestorJugadores.obtenerJugadoresConPuntuacionMayorA(100);
        
        // Assert
        assertEquals(2, jugadoresFiltrados.size(), "Debería tener dos jugadores con puntuación > 100");
        assertTrue(jugadoresFiltrados.contains(jugador2), "Debería contener jugador2");
        assertTrue(jugadoresFiltrados.contains(jugador3), "Debería contener jugador3");
        assertFalse(jugadoresFiltrados.contains(jugador1), "No debería contener jugador1");
    }
    
    @Test
    @DisplayName("Debería calcular puntuación total de todos los jugadores")
    void testCalcularPuntuacionTotal() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        jugador1.agregarPuntos(100);
        jugador2.agregarPuntos(200);
        
        // Act
        int puntuacionTotal = gestorJugadores.calcularPuntuacionTotal();
        
        // Assert
        assertEquals(300, puntuacionTotal, "La puntuación total debería ser 300");
    }
    
    @Test
    @DisplayName("Debería calcular total de intentos de todos los jugadores")
    void testCalcularTotalIntentos() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        jugador1.incrementarIntentos();
        jugador1.incrementarIntentos();
        jugador2.incrementarIntentos();
        
        // Act
        int totalIntentos = gestorJugadores.calcularTotalIntentos();
        
        // Assert
        assertEquals(3, totalIntentos, "El total de intentos debería ser 3");
    }
    
    @Test
    @DisplayName("Debería calcular total de aciertos de todos los jugadores")
    void testCalcularTotalAciertos() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        jugador1.incrementarAciertos();
        jugador1.incrementarAciertos();
        jugador2.incrementarAciertos();
        
        // Act
        int totalAciertos = gestorJugadores.calcularTotalAciertos();
        
        // Assert
        assertEquals(3, totalAciertos, "El total de aciertos debería ser 3");
    }
    
    @Test
    @DisplayName("Debería calcular porcentaje promedio de aciertos")
    void testCalcularPorcentajePromedioAciertos() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        // Configurar estadísticas
        jugador1.incrementarIntentos();
        jugador1.incrementarIntentos();
        jugador1.incrementarAciertos(); // 50% de aciertos
        
        jugador2.incrementarIntentos();
        jugador2.incrementarAciertos(); // 100% de aciertos
        
        // Act
        double porcentajePromedio = gestorJugadores.calcularPorcentajePromedioAciertos();
        
        // Assert
        assertEquals(75.0, porcentajePromedio, 0.1, "El porcentaje promedio debería ser 75%");
    }
    
    @Test
    @DisplayName("Debería reiniciar estadísticas de todos los jugadores")
    void testReiniciarEstadisticas() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        jugador1.agregarPuntos(100);
        jugador1.incrementarIntentos();
        jugador1.incrementarAciertos();
        
        // Act
        gestorJugadores.reiniciarEstadisticas();
        
        // Assert
        assertEquals(0, jugador1.getPuntuacion(), "La puntuación debería ser cero");
        assertEquals(0, jugador1.getIntentos(), "Los intentos deberían ser cero");
        assertEquals(0, jugador1.getAciertos(), "Los aciertos deberían ser cero");
    }
    
    @Test
    @DisplayName("Debería limpiar todos los jugadores")
    void testLimpiarJugadores() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        // Act
        gestorJugadores.limpiarJugadores();
        
        // Assert
        assertEquals(0, gestorJugadores.getNumeroJugadores(), "No debería tener jugadores");
        assertTrue(gestorJugadores.estaVacia(), "La lista debería estar vacía");
    }
    
    @Test
    @DisplayName("Debería verificar si la lista está vacía")
    void testEstaVacia() {
        // Act & Assert - Lista vacía
        assertTrue(gestorJugadores.estaVacia(), "La lista debería estar vacía inicialmente");
        
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        
        // Act & Assert - Lista con elementos
        assertFalse(gestorJugadores.estaVacia(), "La lista no debería estar vacía después de agregar");
    }
    
    @Test
    @DisplayName("Debería obtener nombres de jugadores")
    void testObtenerNombresJugadores() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        // Act
        List<String> nombres = gestorJugadores.obtenerNombresJugadores();
        
        // Assert
        assertEquals(2, nombres.size(), "Debería tener dos nombres");
        assertTrue(nombres.contains("Jugador 1"), "Debería contener el nombre del jugador1");
        assertTrue(nombres.contains("Jugador 2"), "Debería contener el nombre del jugador2");
    }
    
    @Test
    @DisplayName("Debería obtener estadísticas generales")
    void testObtenerEstadisticasGenerales() {
        // Arrange
        gestorJugadores.agregarJugador(jugador1);
        gestorJugadores.agregarJugador(jugador2);
        
        jugador1.agregarPuntos(100);
        jugador1.incrementarIntentos();
        jugador1.incrementarAciertos();
        
        jugador2.agregarPuntos(200);
        jugador2.incrementarIntentos();
        jugador2.incrementarIntentos();
        jugador2.incrementarAciertos();
        
        // Act
        String estadisticas = gestorJugadores.obtenerEstadisticasGenerales();
        
        // Assert
        assertNotNull(estadisticas, "Las estadísticas no deberían ser nulas");
        assertFalse(estadisticas.isEmpty(), "Las estadísticas no deberían estar vacías");
        assertTrue(estadisticas.contains("ESTADÍSTICAS"), "Debería contener información de estadísticas");
        assertTrue(estadisticas.contains("2"), "Debería contener el número de jugadores");
        assertTrue(estadisticas.contains("300"), "Debería contener la puntuación total");
    }
}
