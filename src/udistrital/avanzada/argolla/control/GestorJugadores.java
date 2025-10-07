package udistrital.avanzada.argolla.control;

import udistrital.avanzada.argolla.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor especializado para la administración de jugadores.
 * Implementa el principio de responsabilidad única (SRP) al manejar
 * únicamente las operaciones relacionadas con jugadores.
 * 
 * @author Sansantax, And_Gar03
 * @version 3.0
 */
public class GestorJugadores {
    
    /** Lista de jugadores registrados */
    private List<Jugador> jugadores;
    
    /**
     * Constructor del gestor de jugadores.
     */
    public GestorJugadores() {
        this.jugadores = new ArrayList<>();
    }
    
    /**
     * Crea un nuevo jugador con el nombre especificado.
     * 
     * @param nombre Nombre del jugador
     * @return Jugador creado, null si no se pudo crear
     */
    public Jugador crearJugador(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        
        Jugador nuevoJugador = new Jugador(nombre.trim());
        
        if (agregarJugador(nuevoJugador)) {
            return nuevoJugador;
        }
        
        return null;
    }
    
    /**
     * Agrega un jugador a la lista de jugadores.
     * 
     * @param jugador Jugador a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    public boolean agregarJugador(Jugador jugador) {
        if (jugador == null || jugadores.contains(jugador)) {
            return false;
        }
        
        jugadores.add(jugador);
        return true;
    }
    
    /**
     * Remueve un jugador de la lista de jugadores.
     * 
     * @param jugador Jugador a remover
     * @return true si se removió exitosamente, false en caso contrario
     */
    public boolean removerJugador(Jugador jugador) {
        return jugadores.remove(jugador);
    }
    
    /**
     * Obtiene un jugador por su nombre.
     * 
     * @param nombre Nombre del jugador a buscar
     * @return Jugador encontrado, null si no existe
     */
    public Jugador buscarJugadorPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        
        return jugadores.stream()
                .filter(jugador -> jugador.getNombre().equals(nombre.trim()))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todos los jugadores registrados.
     * 
     * @return Lista de jugadores
     */
    public List<Jugador> obtenerTodosLosJugadores() {
        return new ArrayList<>(jugadores);
    }
    
    /**
     * Obtiene el número de jugadores registrados.
     * 
     * @return Número de jugadores
     */
    public int getNumeroJugadores() {
        return jugadores.size();
    }
    
    /**
     * Verifica si existe un jugador con el nombre especificado.
     * 
     * @param nombre Nombre del jugador
     * @return true si existe, false en caso contrario
     */
    public boolean existeJugador(String nombre) {
        return buscarJugadorPorNombre(nombre) != null;
    }
    
    /**
     * Obtiene los jugadores ordenados por puntuación (descendente).
     * 
     * @return Lista de jugadores ordenados por puntuación
     */
    public List<Jugador> obtenerJugadoresOrdenadosPorPuntuacion() {
        List<Jugador> jugadoresOrdenados = new ArrayList<>(jugadores);
        jugadoresOrdenados.sort((j1, j2) -> Integer.compare(j2.getPuntuacion(), j1.getPuntuacion()));
        return jugadoresOrdenados;
    }
    
    /**
     * Obtiene los jugadores ordenados por porcentaje de aciertos (descendente).
     * 
     * @return Lista de jugadores ordenados por porcentaje de aciertos
     */
    public List<Jugador> obtenerJugadoresOrdenadosPorAciertos() {
        List<Jugador> jugadoresOrdenados = new ArrayList<>(jugadores);
        jugadoresOrdenados.sort((j1, j2) -> Double.compare(j2.calcularPorcentajeAciertos(), j1.calcularPorcentajeAciertos()));
        return jugadoresOrdenados;
    }
    
    /**
     * Obtiene el jugador con mayor puntuación.
     * 
     * @return Jugador con mayor puntuación, null si no hay jugadores
     */
    public Jugador obtenerJugadorConMayorPuntuacion() {
        return jugadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getPuntuacion(), j2.getPuntuacion()))
                .orElse(null);
    }
    
    /**
     * Obtiene el jugador con mejor porcentaje de aciertos.
     * 
     * @return Jugador con mejor porcentaje de aciertos, null si no hay jugadores
     */
    public Jugador obtenerJugadorConMejorAciertos() {
        return jugadores.stream()
                .max((j1, j2) -> Double.compare(j1.calcularPorcentajeAciertos(), j2.calcularPorcentajeAciertos()))
                .orElse(null);
    }
    
    /**
     * Obtiene los jugadores con puntuación mayor a un valor específico.
     * 
     * @param puntuacionMinima Puntuación mínima
     * @return Lista de jugadores con puntuación mayor a la especificada
     */
    public List<Jugador> obtenerJugadoresConPuntuacionMayorA(int puntuacionMinima) {
        List<Jugador> jugadoresFiltrados = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntuacion() > puntuacionMinima) {
                jugadoresFiltrados.add(jugador);
            }
        }
        return jugadoresFiltrados;
    }
    
    /**
     * Obtiene los jugadores con porcentaje de aciertos mayor a un valor específico.
     * 
     * @param porcentajeMinimo Porcentaje mínimo de aciertos
     * @return Lista de jugadores con porcentaje mayor al especificado
     */
    public List<Jugador> obtenerJugadoresConAciertosMayorA(double porcentajeMinimo) {
        List<Jugador> jugadoresFiltrados = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            if (jugador.calcularPorcentajeAciertos() > porcentajeMinimo) {
                jugadoresFiltrados.add(jugador);
            }
        }
        return jugadoresFiltrados;
    }
    
    /**
     * Calcula la puntuación total de todos los jugadores.
     * 
     * @return Puntuación total
     */
    public int calcularPuntuacionTotal() {
        return jugadores.stream()
                .mapToInt(Jugador::getPuntuacion)
                .sum();
    }
    
    /**
     * Calcula el número total de intentos de todos los jugadores.
     * 
     * @return Total de intentos
     */
    public int calcularTotalIntentos() {
        return jugadores.stream()
                .mapToInt(Jugador::getIntentos)
                .sum();
    }
    
    /**
     * Calcula el número total de aciertos de todos los jugadores.
     * 
     * @return Total de aciertos
     */
    public int calcularTotalAciertos() {
        return jugadores.stream()
                .mapToInt(Jugador::getAciertos)
                .sum();
    }
    
    /**
     * Calcula el porcentaje promedio de aciertos de todos los jugadores.
     * 
     * @return Porcentaje promedio de aciertos
     */
    public double calcularPorcentajePromedioAciertos() {
        if (jugadores.isEmpty()) {
            return 0.0;
        }
        
        double sumaPorcentajes = jugadores.stream()
                .mapToDouble(Jugador::calcularPorcentajeAciertos)
                .sum();
        
        return sumaPorcentajes / jugadores.size();
    }
    
    /**
     * Reinicia las estadísticas de todos los jugadores.
     */
    public void reiniciarEstadisticas() {
        jugadores.forEach(Jugador::reiniciarEstadisticas);
    }
    
    /**
     * Limpia la lista de jugadores.
     */
    public void limpiarJugadores() {
        jugadores.clear();
    }
    
    /**
     * Verifica si la lista de jugadores está vacía.
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return jugadores.isEmpty();
    }
    
    /**
     * Obtiene los nombres de todos los jugadores.
     * 
     * @return Lista de nombres de jugadores
     */
    public List<String> obtenerNombresJugadores() {
        List<String> nombres = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            nombres.add(jugador.getNombre());
        }
        return nombres;
    }
    
    /**
     * Obtiene las estadísticas generales de todos los jugadores.
     * 
     * @return String con las estadísticas generales
     */
    public String obtenerEstadisticasGenerales() {
        if (jugadores.isEmpty()) {
            return "No hay jugadores registrados";
        }
        
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("=== ESTADÍSTICAS GENERALES ===\n");
        estadisticas.append("Total de jugadores: ").append(jugadores.size()).append("\n");
        estadisticas.append("Puntuación total: ").append(calcularPuntuacionTotal()).append("\n");
        estadisticas.append("Total de intentos: ").append(calcularTotalIntentos()).append("\n");
        estadisticas.append("Total de aciertos: ").append(calcularTotalAciertos()).append("\n");
        estadisticas.append("Porcentaje promedio de aciertos: ")
                   .append(String.format("%.1f", calcularPorcentajePromedioAciertos()))
                   .append("%\n");
        
        return estadisticas.toString();
    }
}
