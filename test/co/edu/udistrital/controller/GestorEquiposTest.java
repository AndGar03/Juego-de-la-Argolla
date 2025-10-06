package co.edu.udistrital.controller;

import co.edu.udistrital.model.Equipo;
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
 * Pruebas unitarias para la clase GestorEquipos.
 * Utiliza JUnit 5 con las anotaciones requeridas.
 * 
 * @author Sistema Juego de la Argolla
 * @version 1.0
 */
@DisplayName("Pruebas del GestorEquipos")
public class GestorEquiposTest {
    
    private static GestorEquipos gestorEquipos;
    private static Equipo equipo1;
    private static Equipo equipo2;
    private static Jugador jugador1;
    private static Jugador jugador2;
    
    @BeforeAll
    static void setUpClass() {
        // Configuración inicial para todas las pruebas
        equipo1 = new Equipo("Equipo Rojo", "Rojo");
        equipo2 = new Equipo("Equipo Azul", "Azul");
        jugador1 = new Jugador("Jugador 1");
        jugador2 = new Jugador("Jugador 2");
    }
    
    @AfterAll
    static void tearDownClass() {
        // Limpieza final después de todas las pruebas
        gestorEquipos = null;
        equipo1 = null;
        equipo2 = null;
        jugador1 = null;
        jugador2 = null;
    }
    
    @BeforeEach
    void setUp() {
        // Configuración antes de cada prueba
        gestorEquipos = new GestorEquipos();
    }
    
    @AfterEach
    void tearDown() {
        // Limpieza después de cada prueba
        gestorEquipos = null;
    }
    
    @Test
    @DisplayName("Debería crear equipo con nombre y color válidos")
    void testCrearEquipo() {
        // Arrange
        String nombre = "Equipo Verde";
        String color = "Verde";
        
        // Act
        Equipo equipo = gestorEquipos.crearEquipo(nombre, color);
        
        // Assert
        assertNotNull(equipo, "El equipo no debería ser nulo");
        assertEquals(nombre, equipo.getNombre(), "El nombre debería coincidir");
        assertEquals(color, equipo.getColor(), "El color debería coincidir");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipo), 
                  "El equipo debería estar en la lista");
    }
    
    @Test
    @DisplayName("Debería crear equipo con color por defecto si no se especifica")
    void testCrearEquipoConColorPorDefecto() {
        // Arrange
        String nombre = "Equipo Test";
        String color = "";
        
        // Act
        Equipo equipo = gestorEquipos.crearEquipo(nombre, color);
        
        // Assert
        assertNotNull(equipo, "El equipo no debería ser nulo");
        assertEquals("Azul", equipo.getColor(), "Debería usar color por defecto");
    }
    
    @Test
    @DisplayName("No debería crear equipo con nombre nulo")
    void testCrearEquipoConNombreNulo() {
        // Act
        Equipo equipo = gestorEquipos.crearEquipo(null, "Rojo");
        
        // Assert
        assertNull(equipo, "No debería crear equipo con nombre nulo");
    }
    
    @Test
    @DisplayName("No debería crear equipo con nombre vacío")
    void testCrearEquipoConNombreVacio() {
        // Act
        Equipo equipo = gestorEquipos.crearEquipo("", "Rojo");
        
        // Assert
        assertNull(equipo, "No debería crear equipo con nombre vacío");
    }
    
    @Test
    @DisplayName("Debería agregar equipo a la lista")
    void testAgregarEquipo() {
        // Act
        boolean resultado = gestorEquipos.agregarEquipo(equipo1);
        
        // Assert
        assertTrue(resultado, "Debería poder agregar equipo");
        assertEquals(1, gestorEquipos.getNumeroEquipos(), "Debería tener un equipo");
        assertTrue(gestorEquipos.obtenerTodosLosEquipos().contains(equipo1), 
                  "Debería contener el equipo agregado");
    }
    
    @Test
    @DisplayName("No debería agregar equipo nulo")
    void testAgregarEquipoNulo() {
        // Act
        boolean resultado = gestorEquipos.agregarEquipo(null);
        
        // Assert
        assertFalse(resultado, "No debería poder agregar equipo nulo");
        assertEquals(0, gestorEquipos.getNumeroEquipos(), "No debería tener equipos");
    }
    
    @Test
    @DisplayName("No debería agregar equipo duplicado")
    void testAgregarEquipoDuplicado() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        
        // Act
        boolean resultado = gestorEquipos.agregarEquipo(equipo1);
        
        // Assert
        assertFalse(resultado, "No debería poder agregar equipo duplicado");
        assertEquals(1, gestorEquipos.getNumeroEquipos(), "Debería tener solo un equipo");
    }
    
    @Test
    @DisplayName("Debería remover equipo de la lista")
    void testRemoverEquipo() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        
        // Act
        boolean resultado = gestorEquipos.removerEquipo(equipo1);
        
        // Assert
        assertTrue(resultado, "Debería poder remover equipo");
        assertEquals(0, gestorEquipos.getNumeroEquipos(), "No debería tener equipos");
        assertFalse(gestorEquipos.obtenerTodosLosEquipos().contains(equipo1), 
                   "No debería contener el equipo removido");
    }
    
    @Test
    @DisplayName("No debería remover equipo que no existe")
    void testRemoverEquipoInexistente() {
        // Act
        boolean resultado = gestorEquipos.removerEquipo(equipo1);
        
        // Assert
        assertFalse(resultado, "No debería poder remover equipo inexistente");
    }
    
    @Test
    @DisplayName("Debería buscar equipo por nombre")
    void testBuscarEquipoPorNombre() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorNombre("Equipo Rojo");
        
        // Assert
        assertNotNull(equipoEncontrado, "Debería encontrar el equipo");
        assertEquals(equipo1, equipoEncontrado, "Debería ser el equipo correcto");
    }
    
    @Test
    @DisplayName("No debería encontrar equipo con nombre inexistente")
    void testBuscarEquipoPorNombreInexistente() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorNombre("Equipo Inexistente");
        
        // Assert
        assertNull(equipoEncontrado, "No debería encontrar equipo inexistente");
    }
    
    @Test
    @DisplayName("Debería buscar equipo por color")
    void testBuscarEquipoPorColor() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        // Act
        Equipo equipoEncontrado = gestorEquipos.buscarEquipoPorColor("Rojo");
        
        // Assert
        assertNotNull(equipoEncontrado, "Debería encontrar el equipo");
        assertEquals(equipo1, equipoEncontrado, "Debería ser el equipo correcto");
    }
    
    @Test
    @DisplayName("Debería verificar existencia de equipo")
    void testExisteEquipo() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        
        // Act & Assert
        assertTrue(gestorEquipos.existeEquipo("Equipo Rojo"), 
                  "Debería existir el equipo agregado");
        assertFalse(gestorEquipos.existeEquipo("Equipo Inexistente"), 
                   "No debería existir equipo no agregado");
    }
    
    @Test
    @DisplayName("Debería verificar existencia de color")
    void testExisteColor() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        
        // Act & Assert
        assertTrue(gestorEquipos.existeColor("Rojo"), 
                  "Debería existir el color del equipo agregado");
        assertFalse(gestorEquipos.existeColor("Verde"), 
                   "No debería existir color no usado");
    }
    
    @Test
    @DisplayName("Debería ordenar equipos por puntuación")
    void testObtenerEquiposOrdenadosPorPuntuacion() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        // Agregar jugadores y puntuaciones
        equipo1.agregarJugador(jugador1);
        equipo2.agregarJugador(jugador2);
        jugador1.agregarPuntos(100);
        jugador2.agregarPuntos(50);
        
        // Act
        List<Equipo> equiposOrdenados = gestorEquipos.obtenerEquiposOrdenadosPorPuntuacion();
        
        // Assert
        assertEquals(2, equiposOrdenados.size(), "Debería tener dos equipos");
        assertEquals(equipo1, equiposOrdenados.get(0), "El primer equipo debería tener mayor puntuación");
        assertEquals(equipo2, equiposOrdenados.get(1), "El segundo equipo debería tener menor puntuación");
    }
    
    @Test
    @DisplayName("Debería obtener equipo con mayor puntuación")
    void testObtenerEquipoConMayorPuntuacion() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        equipo1.agregarJugador(jugador1);
        equipo2.agregarJugador(jugador2);
        jugador1.agregarPuntos(100);
        jugador2.agregarPuntos(50);
        
        // Act
        Equipo equipoConMayorPuntuacion = gestorEquipos.obtenerEquipoConMayorPuntuacion();
        
        // Assert
        assertNotNull(equipoConMayorPuntuacion, "Debería encontrar un equipo");
        assertEquals(equipo1, equipoConMayorPuntuacion, "Debería ser el equipo con mayor puntuación");
    }
    
    @Test
    @DisplayName("Debería reiniciar estadísticas de todos los equipos")
    void testReiniciarEstadisticas() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        equipo1.agregarJugador(jugador1);
        jugador1.agregarPuntos(100);
        jugador1.incrementarIntentos();
        jugador1.incrementarAciertos();
        
        // Act
        gestorEquipos.reiniciarEstadisticas();
        
        // Assert
        assertEquals(0, equipo1.getPuntuacionTotal(), "La puntuación debería ser cero");
        assertEquals(0, equipo1.getTotalIntentos(), "Los intentos deberían ser cero");
        assertEquals(0, equipo1.getTotalAciertos(), "Los aciertos deberían ser cero");
    }
    
    @Test
    @DisplayName("Debería limpiar todos los equipos")
    void testLimpiarEquipos() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        // Act
        gestorEquipos.limpiarEquipos();
        
        // Assert
        assertEquals(0, gestorEquipos.getNumeroEquipos(), "No debería tener equipos");
        assertTrue(gestorEquipos.estaVacia(), "La lista debería estar vacía");
    }
    
    @Test
    @DisplayName("Debería verificar si la lista está vacía")
    void testEstaVacia() {
        // Act & Assert - Lista vacía
        assertTrue(gestorEquipos.estaVacia(), "La lista debería estar vacía inicialmente");
        
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        
        // Act & Assert - Lista con elementos
        assertFalse(gestorEquipos.estaVacia(), "La lista no debería estar vacía después de agregar");
    }
    
    @Test
    @DisplayName("Debería obtener nombres de equipos")
    void testObtenerNombresEquipos() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        // Act
        List<String> nombres = gestorEquipos.obtenerNombresEquipos();
        
        // Assert
        assertEquals(2, nombres.size(), "Debería tener dos nombres");
        assertTrue(nombres.contains("Equipo Rojo"), "Debería contener el nombre del equipo1");
        assertTrue(nombres.contains("Equipo Azul"), "Debería contener el nombre del equipo2");
    }
    
    @Test
    @DisplayName("Debería obtener colores de equipos")
    void testObtenerColoresEquipos() {
        // Arrange
        gestorEquipos.agregarEquipo(equipo1);
        gestorEquipos.agregarEquipo(equipo2);
        
        // Act
        List<String> colores = gestorEquipos.obtenerColoresEquipos();
        
        // Assert
        assertEquals(2, colores.size(), "Debería tener dos colores");
        assertTrue(colores.contains("Rojo"), "Debería contener el color del equipo1");
        assertTrue(colores.contains("Azul"), "Debería contener el color del equipo2");
    }
}
