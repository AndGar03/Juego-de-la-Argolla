package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.*;
import udistrital.avanzada.argolla.control.ArchivoAccesoAleatorio;
import java.util.List;

/**
 * Clase para ejecutar las pruebas unitarias sin dependencias externas.
 * Simula las funcionalidades de JUnit para las pruebas del proyecto.
 * 
 * @author Sansantax
 * @version 3.0
 */
public class TestRunner {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    private static int totalTests = 0;
    
    /**
     * Ejecuta todas las pruebas del proyecto.
     */
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DEL JUEGO DE LA ARGOLLA ===");
        System.out.println("Versión 3.0 - Autor: Sansantax");
        System.out.println("================================================");
        
        // Ejecutar pruebas de GameManager
        runGameManagerTests();
        
        // Ejecutar pruebas de GestorEquipos
        runGestorEquiposTests();
        
        // Ejecutar pruebas de GestorJugadores
        runGestorJugadoresTests();
        
        // Mostrar resumen
        System.out.println("\n=== RESUMEN DE PRUEBAS ===");
        System.out.println("Total de pruebas: " + totalTests);
        System.out.println("Pruebas exitosas: " + testsPassed);
        System.out.println("Pruebas fallidas: " + testsFailed);
        System.out.println("Porcentaje de éxito: " + String.format("%.1f", (double) testsPassed / totalTests * 100) + "%");
        
        if (testsFailed == 0) {
            System.out.println("¡Todas las pruebas pasaron exitosamente!");
        } else {
            System.out.println("Algunas pruebas fallaron. Revisar la implementación.");
        }
    }
    
    /**
     * Ejecuta las pruebas de GameManager.
     */
    private static void runGameManagerTests() {
        System.out.println("\n--- Ejecutando pruebas de GameManager ---");
        
        GameManager gameManager = new GameManager();
        ConfiguracionJuego configuracion = new ConfiguracionJuego();
        Equipo equipoTest = new Equipo("Equipo Test", "Azul");
        Jugador jugadorTest = new Jugador("Jugador Test");
        
        // Prueba 1: Crear equipo válido
        test("Crear equipo válido", () -> {
            Equipo equipo = gameManager.crearEquipo("Equipo A", "Rojo");
            return equipo != null && "Equipo A".equals(equipo.getNombre()) && "Rojo".equals(equipo.getColor());
        });
        
        // Prueba 2: Crear equipo con nombre nulo
        test("Crear equipo con nombre nulo", () -> {
            Equipo equipo = gameManager.crearEquipo(null, "Verde");
            return equipo == null;
        });
        
        // Prueba 3: Crear jugador válido
        test("Crear jugador válido", () -> {
            Jugador jugador = gameManager.crearJugador("Jugador A");
            return jugador != null && "Jugador A".equals(jugador.getNombre()) && jugador.getPuntuacion() == 0;
        });
        
        // Prueba 4: Crear jugador con nombre nulo
        test("Crear jugador con nombre nulo", () -> {
            Jugador jugador = gameManager.crearJugador(null);
            return jugador == null;
        });
        
        // Prueba 5: Iniciar nueva partida
        test("Iniciar nueva partida", () -> {
            boolean resultado = gameManager.iniciarNuevaPartida(configuracion);
            return resultado && gameManager.getPartidaActual() != null;
        });
        
        // Prueba 6: Agregar equipo a partida
        test("Agregar equipo a partida", () -> {
            boolean resultado = gameManager.agregarEquipo(equipoTest);
            return resultado && gameManager.getEquipos().size() == 1;
        });
        
        // Prueba 7: Agregar jugador a equipo
        test("Agregar jugador a equipo", () -> {
            boolean resultado = gameManager.agregarJugadorAEquipo(equipoTest, jugadorTest);
            return resultado && equipoTest.getNumeroJugadores() == 1;
        });
        
        // Prueba 8: Obtener gestor de archivos
        test("Obtener gestor de archivos", () -> {
            ArchivoAccesoAleatorio archivo = gameManager.getArchivoAccesoAleatorio();
            return archivo != null;
        });
    }
    
    /**
     * Ejecuta las pruebas de GestorEquipos.
     */
    private static void runGestorEquiposTests() {
        System.out.println("\n--- Ejecutando pruebas de GestorEquipos ---");
        
        GestorEquipos gestorEquipos = new GestorEquipos();
        Equipo equipoTest1 = new Equipo("Equipo A", "Azul");
        Equipo equipoTest2 = new Equipo("Equipo B", "Rojo");
        
        // Prueba 1: Agregar equipo válido
        test("Agregar equipo válido", () -> {
            boolean resultado = gestorEquipos.agregarEquipo(equipoTest1);
            return resultado && gestorEquipos.obtenerTodosLosEquipos().size() == 1;
        });
        
        // Prueba 2: Agregar equipo nulo
        test("Agregar equipo nulo", () -> {
            boolean resultado = gestorEquipos.agregarEquipo(null);
            return !resultado && gestorEquipos.obtenerTodosLosEquipos().size() == 1;
        });
        
        // Prueba 3: Agregar mismo equipo dos veces
        test("Agregar mismo equipo dos veces", () -> {
            boolean resultado = gestorEquipos.agregarEquipo(equipoTest1);
            return !resultado && gestorEquipos.obtenerTodosLosEquipos().size() == 1;
        });
        
        // Prueba 4: Agregar múltiples equipos
        test("Agregar múltiples equipos", () -> {
            boolean resultado = gestorEquipos.agregarEquipo(equipoTest2);
            return resultado && gestorEquipos.obtenerTodosLosEquipos().size() == 2;
        });
        
        // Prueba 5: Remover equipo existente
        test("Remover equipo existente", () -> {
            boolean resultado = gestorEquipos.removerEquipo(equipoTest1);
            return resultado && gestorEquipos.obtenerTodosLosEquipos().size() == 1;
        });
        
        // Prueba 6: Buscar equipo por nombre
        test("Buscar equipo por nombre", () -> {
            Equipo equipo = gestorEquipos.buscarEquipoPorNombre("Equipo B");
            return equipo != null && "Equipo B".equals(equipo.getNombre());
        });
        
        // Prueba 7: Obtener número de equipos
        test("Obtener número de equipos", () -> {
            int numero = gestorEquipos.getNumeroEquipos();
            return numero == 1;
        });
    }
    
    /**
     * Ejecuta las pruebas de GestorJugadores.
     */
    private static void runGestorJugadoresTests() {
        System.out.println("\n--- Ejecutando pruebas de GestorJugadores ---");
        
        GestorJugadores gestorJugadores = new GestorJugadores();
        Jugador jugadorTest1 = new Jugador("Jugador A");
        Jugador jugadorTest2 = new Jugador("Jugador B");
        
        // Prueba 1: Agregar jugador válido
        test("Agregar jugador válido", () -> {
            boolean resultado = gestorJugadores.agregarJugador(jugadorTest1);
            return resultado && gestorJugadores.obtenerTodosLosJugadores().size() == 1;
        });
        
        // Prueba 2: Agregar jugador nulo
        test("Agregar jugador nulo", () -> {
            boolean resultado = gestorJugadores.agregarJugador(null);
            return !resultado && gestorJugadores.obtenerTodosLosJugadores().size() == 1;
        });
        
        // Prueba 3: Agregar mismo jugador dos veces
        test("Agregar mismo jugador dos veces", () -> {
            boolean resultado = gestorJugadores.agregarJugador(jugadorTest1);
            return !resultado && gestorJugadores.obtenerTodosLosJugadores().size() == 1;
        });
        
        // Prueba 4: Agregar múltiples jugadores
        test("Agregar múltiples jugadores", () -> {
            boolean resultado = gestorJugadores.agregarJugador(jugadorTest2);
            return resultado && gestorJugadores.obtenerTodosLosJugadores().size() == 2;
        });
        
        // Prueba 5: Remover jugador existente
        test("Remover jugador existente", () -> {
            boolean resultado = gestorJugadores.removerJugador(jugadorTest1);
            return resultado && gestorJugadores.obtenerTodosLosJugadores().size() == 1;
        });
        
        // Prueba 6: Buscar jugador por nombre
        test("Buscar jugador por nombre", () -> {
            Jugador jugador = gestorJugadores.buscarJugadorPorNombre("Jugador B");
            return jugador != null && "Jugador B".equals(jugador.getNombre());
        });
        
        // Prueba 7: Obtener número de jugadores
        test("Obtener número de jugadores", () -> {
            int numero = gestorJugadores.getNumeroJugadores();
            return numero == 1;
        });
    }
    
    /**
     * Ejecuta una prueba individual.
     */
    private static void test(String testName, TestFunction testFunction) {
        totalTests++;
        try {
            boolean result = testFunction.run();
            if (result) {
                testsPassed++;
                System.out.println("✓ " + testName + " - PASÓ");
            } else {
                testsFailed++;
                System.out.println("✗ " + testName + " - FALLÓ");
            }
        } catch (Exception e) {
            testsFailed++;
            System.out.println("✗ " + testName + " - ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Interfaz funcional para las pruebas.
     */
    @FunctionalInterface
    private interface TestFunction {
        boolean run() throws Exception;
    }
}
